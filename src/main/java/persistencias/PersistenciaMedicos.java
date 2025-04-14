
package persistencias;
import entidades.Medico;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.EOFException;
import java.io.FileOutputStream;

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
public class PersistenciaMedicos {
    //Ruta abstracta donde se guarda cada médico
    private Path ruta = Paths.get("src/main/resources/medicos.dat");
    public PersistenciaMedicos(){}
    
    /**
     * Método que agrega un médico a un archivo de texto utilizando la clase FileWriter 
     * @param medico
     */
    public void agregarMedico(Medico medico) throws IOException {
        // Verificar si el archivo existe y no está vacío para settear la propiedad append
        boolean append = Files.size(ruta) > 0;
        // si ya existe crea un OOS que no modifica la cabezara si no crea una
        try (FileOutputStream file = new FileOutputStream(ruta.toFile(), append);
                ObjectOutputStream oos = append ? new ObjectOutputStream(file) {
                    @Override
                    protected void writeStreamHeader() throws IOException {
                        reset();
                    }
                } : new ObjectOutputStream(file)) {

            oos.writeObject(medico);
            oos.flush();
        } catch (IOException ex) {
            throw new IOException("Error al agregar medico: " + ex.getMessage(), ex);
        }
    }

    /**
     * Metodo que crea una copia del archivo y copia los elementos a excepcion del objetivo
     * donde en lugar de copiar el original copia el nuevo, se copia la base y se elimina el temporal
     * @param nuevoMedico
     * @return
     * @throws IOException
     */
    public boolean actualizarMedico(Medico nuevoMedico) throws IOException {
        // Verificar si tiene contenido
        if (Files.size(ruta) == 0) {
            return false;
        }
        // Crear archivo temporal
        Path tempFile = Files.createTempFile(ruta.getParent(), "temp_medicos", ".tmp");
        boolean medicoEncontrado = false;

        try {
            try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(ruta));
                    ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(tempFile))) {
                while (true) {
                    try {
                        Medico medico = (Medico) ois.readObject();
                        // Reemplazar si es el medico a actualizar
                        if (medico.getId() == nuevoMedico.getId()) {
                            oos.writeObject(nuevoMedico);
                            medicoEncontrado = true;
                        } else {
                            oos.writeObject(medico);
                        }
                    } catch (EOFException e) {
                        break; // Fin del archivo
                    }
                }
            }
            // Solo reemplazar si encontramos el medico
            if (medicoEncontrado) {
                Files.move(tempFile, ruta, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (ClassNotFoundException | ClassCastException e) {
            throw new IOException("Error en el formato de datos", e);
        } finally {
            Files.deleteIfExists(tempFile);
        }
        return medicoEncontrado;
    }
    
    /**
     * Metodo que crea una copia del archivo y copia los elementos a excepcion del objetivo
     * si llega a encontrarse se copia el contenido temporal al base y se elimina la copia
     * @param idBuscado
     * @throws IOException
     */
    public void eliminarMedico(int idBuscado) throws IOException {
        if (Files.size(ruta) == 0) return;
        Path tempFile = Files.createTempFile(ruta.getParent(), "temp_medicos", ".tmp");
        boolean eliminado = false;
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(ruta));
             ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(tempFile))) {
            while (true) {
                try {
                    Medico medico = (Medico) ois.readObject();
                    if (medico.getId() != idBuscado) {
                        oos.writeObject(medico);
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
     * Método que consulta un medico mediante id
     * @param idBuscado
     * @return un objeto Paciente
     * @throws java.io.IOException
     */
    public Medico consultarMedicoId(int idBuscado) throws IOException {
        if (Files.size(ruta) == 0) {
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(ruta))) {
            while (true) {
                try {
                    Medico medico = (Medico) ois.readObject();
                    if (medico.getId() == idBuscado) {
                        return medico;
                    }
                } catch (EOFException e) {
                    break; // Fin del archivo alcanzado
                } catch (ClassNotFoundException | ClassCastException e) {
                    throw new IOException("Formato de datos inválido en el archivo de medicos", e);
                }
            }
        } catch (IOException ex) {
            throw new IOException("Error al leer el archivo de medicos: " + ex.getMessage(), ex);
        }
        return null; // Medico no encontrado
    }
    
    /**
     * lista todos los medicos del achivo .dat
     * @return
     * @throws IOException
     */
    public List<Medico> listarMedicos() throws IOException {
        if (Files.size(ruta) == 0) {
            return null;
        }
        List<Medico> listaMedicos = new LinkedList<>();
        
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(ruta))) {
            while (true) {
                try {
                    Medico medico = (Medico) ois.readObject();
                    listaMedicos.add(medico);
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException | ClassCastException e) {
                    throw new IOException("Formato de datos inválido en el archivo de medicos", e);
                }
            }
        } catch (IOException ex) {
            throw new IOException("Error al leer el archivo de medicos: " + ex.getMessage(), ex);
        }
        return listaMedicos;
    }
}
