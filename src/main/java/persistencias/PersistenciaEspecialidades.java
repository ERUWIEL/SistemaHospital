
package persistencias;
import entidades.Especialidad;

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
public class PersistenciaEspecialidades {
    //Ruta abstracta donde se guarda cada equipo médico 
    private Path ruta = Paths.get("src/main/resources/especialidades.txt");
    
    /**
     * Método que agrega una especialidad a un archivo de texto utilizando la clase
     * FileWriter
     * @param especialidad
     * @throws java.io.IOException
     */
    public void agregarEspecialidad(Especialidad especialidad) throws IOException {
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

            oos.writeObject(especialidad);
            oos.flush();
        } catch (IOException ex) {
            throw new IOException("Error al agregar la especialidad: " + ex.getMessage(), ex);
        }
    }

    /**
     * Metodo que crea una copia del archivo y copia los elementos a excepcion del objetivo
     * donde en lugar de copiar el original copia el nuevo, se copia la base y se elimina el temporal
     * @param especialidad
     * @return
     * @throws IOException
     */
    public boolean actualizarEspecialidad(Especialidad nuevaEspecialidad) throws IOException {
        // Verificar si el archivo existe y tiene contenido
        if (Files.size(ruta) == 0) {
            return false;
        }
        // Crear archivo temporal
        Path tempFile = Files.createTempFile(ruta.getParent(), "temp_especialidades", ".tmp");
        boolean especialidadEncontrada = false;

        try {
            try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(ruta));
                    ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(tempFile))) {
                while (true) {
                    try {
                        Especialidad especialidad = (Especialidad) ois.readObject();
                        // Reemplazar si es el paciente a actualizar
                        if (especialidad.getId() == nuevaEspecialidad.getId()) {
                            oos.writeObject(nuevaEspecialidad);
                            especialidadEncontrada = true;
                        } else {
                            oos.writeObject(especialidad);
                        }
                    } catch (EOFException e) {
                        break; // Fin del archivo
                    }
                }
            }
            // Solo reemplazar si encontramos la especialidad
            if (especialidadEncontrada) {
                Files.move(tempFile, ruta, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (ClassNotFoundException | ClassCastException e) {
            throw new IOException("Error en el formato de datos", e);
        } finally {
            Files.deleteIfExists(tempFile);
        }
        return especialidadEncontrada;
    }

    /**
     * Metodo que crea una copia del archivo y copia los elementos a excepcion del objetivo
     * si llega a encontrarse se copia el contenido temporal al base y se elimina la copia
     * @param idBuscado
     * @throws IOException
     */
    public void eliminarEspecialidad(int idBuscado) throws IOException {
        if (Files.size(ruta) == 0) return;
        Path tempFile = Files.createTempFile(ruta.getParent(), "temp_especialidad", ".tmp");
        boolean eliminado = false;
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(ruta));
             ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(tempFile))) {
            while (true) {
                try {
                    Especialidad especialidad = (Especialidad) ois.readObject();
                    if (especialidad.getId() != idBuscado) {
                        oos.writeObject(especialidad);
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
     * Método que consulta una especialidad  mediante id
     * @param idBuscado
     * @return un objeto Paciente
     * @throws java.io.IOException
     */
    public Especialidad consultarEspecialidadId(int idBuscado) throws IOException {
        if (Files.size(ruta) == 0) {
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(ruta))) {
            while (true) {
                try {
                    Especialidad especialidad = (Especialidad) ois.readObject();
                    if (especialidad.getId() == idBuscado) {
                        return especialidad;
                    }
                } catch (EOFException e) {
                    break; // Fin del archivo alcanzado
                } catch (ClassNotFoundException | ClassCastException e) {
                    throw new IOException("Formato de datos inválido en el archivo de pacientes", e);
                }
            }
        } catch (IOException ex) {
            throw new IOException("Error al leer el archivo de especialidad: " + ex.getMessage(), ex);
        }
        return null; // especialidad no encontrada
    }

    /**
     * lista todos las especialidades del achivo .dat
     * @return
     * @throws IOException
     */
    public List<Especialidad> listarEspecialidades() throws IOException {
        if (Files.size(ruta) == 0) {
            return null;
        }
        List<Especialidad> listaEspecialidades = new LinkedList<>();
        
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(ruta))) {
            while (true) {
                try {
                    Especialidad especialidad = (Especialidad) ois.readObject();
                    listaEspecialidades.add(especialidad);
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException | ClassCastException e) {
                    throw new IOException("Formato de datos inválido en el archivo de especialidades", e);
                }
            }
        } catch (IOException ex) {
            throw new IOException("Error al leer el archivo de especialidades: " + ex.getMessage(), ex);
        }
        return listaEspecialidades;
    }
}
