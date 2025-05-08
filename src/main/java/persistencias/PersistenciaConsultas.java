
package persistencias;
import entidades.Consulta;

import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.LinkedList;
import java.util.List;

/**
 * clase que implemenenta metodos basicos CRUD en un archivo .dat
 * @author angel
 */
public class PersistenciaConsultas {
    //Ruta abstracta donde se guarda cada equipo médico 
    private Path ruta = Paths.get("src/main/resources/consultas.dat");
    public PersistenciaConsultas(){

    }
    
    /**
     * Método que agrega un paciente a un archivo de texto utilizando la clase
     * FileWriter
     * @param consulta
     * @throws java.io.IOException
     */
    public void programarConsulta(Consulta consulta) throws IOException {
        // Verificar si no está vacío para settear la propiedad append
        boolean append = Files.size(ruta) > 0;
        // si ya existe crea un OOS que no modifica la cabezara si no crea una
        try (FileOutputStream file = new FileOutputStream(ruta.toFile(), append);
                ObjectOutputStream oos = append ? new ObjectOutputStream(file) {
                    @Override
                    protected void writeStreamHeader() throws IOException {
                        reset();
                    }
                } : new ObjectOutputStream(file)) {

            oos.writeObject(consulta);
            oos.flush();
        } catch (IOException ex) {
            throw new IOException("Error al agregar consulta: " + ex.getMessage(), ex);
        }
    }

    /**
     * Metodo que crea una copia del archivo y copia los elementos a excepcion del objetivo
     * donde en lugar de copiar el original copia el nuevo, se copia la base y se elimina el temporal
     * @param nuevaConsulta
     * @return
     * @throws IOException
     */
    public boolean actualizarConsulta(Consulta nuevaConsulta) throws IOException {
        // Verificar si tiene contenido
        if (Files.size(ruta) == 0) {
            return false;
        }
        // Crear archivo temporal
        Path tempFile = Files.createTempFile(ruta.getParent(), "temp_consulta", ".tmp");
        boolean consultaEncontrada = false;

        try {
            try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(ruta));
                    ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(tempFile))) {
                while (true) {
                    try {
                        Consulta consulta = (Consulta) ois.readObject();
                        // Reemplazar si es la consulta a actualizar
                        if (consulta.getId() == nuevaConsulta.getId()) {
                            oos.writeObject(nuevaConsulta);
                            consultaEncontrada = true;
                        } else {
                            oos.writeObject(consulta);
                        }
                    } catch (EOFException e) {
                        break; // Fin del archivo
                    }
                }
            }
            // Solo reemplazar si encontramos la consulta
            if (consultaEncontrada) {
                Files.move(tempFile, ruta, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (ClassNotFoundException | ClassCastException e) {
            throw new IOException("Error en el formato de datos", e);
        } finally {
            Files.deleteIfExists(tempFile);
        }
        return consultaEncontrada;
    }

    /**
     * Metodo que crea una copia del archivo y copia los elementos a excepcion del objetivo
     * si llega a encontrarse se copia el contenido temporal al base y se elimina la copia
     * @param idBuscado
     * @throws IOException
     */
    public void cancelarConsulta(int idBuscado) throws IOException {
        if (Files.size(ruta) == 0) return;
        Path tempFile = Files.createTempFile(ruta.getParent(), "temp_consulta", ".tmp");
        boolean eliminado = false;
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(ruta));
             ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(tempFile))) {
            while (true) {
                try {
                    Consulta consulta = (Consulta) ois.readObject();
                    if (consulta.getId() != idBuscado) {
                        oos.writeObject(consulta);
                    } else {
                        eliminado = true;
                    }
                } catch (EOFException e) {
                    break;
                }
            }
            if(eliminado){
                Files.move(tempFile, ruta, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (ClassNotFoundException e) {
            throw new IOException("Formato de archivo inválido", e);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    /**
     * Método que consulta una consulta mediante id
     * @param idBuscado
     * @return un objeto Paciente
     * @throws java.io.IOException
     */
    public Consulta consultarConsultasId(int idBuscado) throws IOException {
        if (Files.size(ruta) == 0) {
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(ruta))) {
            while (true) {
                try {
                    Consulta consulta = (Consulta) ois.readObject();
                    if (consulta.getId() == idBuscado) {
                        return consulta;
                    }
                } catch (EOFException e) {
                    break; // Fin del archivo alcanzado
                } catch (ClassNotFoundException | ClassCastException e) {
                    throw new IOException("Formato de datos inválido en el archivo de consulta", e);
                }
            }
        } catch (IOException ex) {
            throw new IOException("Error al leer el archivo de consulta: " + ex.getMessage(), ex);
        }
        return null; // consulta no encontrado
    }

    /**
     * lista todos los consulta del achivo .dat
     * @return
     * @throws IOException
     */
    public List<Consulta> listarConsultas() throws IOException {
        if (Files.size(ruta) == 0) {
            return null;
        }
        List<Consulta> listaConsultas = new LinkedList<>();
        
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(ruta))) {
            while (true) {
                try {
                    Consulta consulta = (Consulta) ois.readObject();
                    listaConsultas.add(consulta);
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException | ClassCastException e) {
                    throw new IOException("Formato de datos inválido en el archivo de consultas", e);
                }
            }
        } catch (IOException ex) {
            throw new IOException("Error al leer el archivo de consultas: " + ex.getMessage(), ex);
        }
        return listaConsultas;
    }
}
