
package persistencias;
import entidades.Paciente;

import java.io.IOException;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.Path;
import java.nio.file.Files;

import java.util.LinkedList;
import java.util.List;

/**
 * clase que implemenenta metodos basicos CRUD en un archivo .dat
 * @author angel
 */
public class PersistenciaPacientes {
    // Ruta abstracta donde se guarda cada paciente
    private final Path ruta = Paths.get("src/main/resources/pacientes.dat");
    public PersistenciaPacientes(){}
    
    /**
     * Método que agrega un paciente a un archivo de texto utilizando la clase
     * FileWriter
     * @param paciente
     * @throws java.io.IOException
     */
    public void agregarPaciente(Paciente paciente) throws IOException {
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

            oos.writeObject(paciente);
            oos.flush();
        } catch (IOException ex) {
            throw new IOException("Error al agregar paciente: " + ex.getMessage(), ex);
        }
    }

    /**
     * Metodo que crea una copia del archivo y copia los elementos a excepcion del objetivo
     * donde en lugar de copiar el original copia el nuevo, se copia la base y se elimina el temporal
     * @param nuevoPaciente
     * @return
     * @throws IOException
     */
    public boolean actualizarPaciente(Paciente nuevoPaciente) throws IOException {
        // Verificar si tiene contenido
        if (Files.size(ruta) == 0) {
            return false;
        }
        // Crear archivo temporal
        Path tempFile = Files.createTempFile(ruta.getParent(), "temp_pacientes", ".tmp");
        boolean pacienteEncontrado = false;

        try {
            try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(ruta));
                    ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(tempFile))) {
                while (true) {
                    try {
                        Paciente paciente = (Paciente) ois.readObject();
                        // Reemplazar si es el paciente a actualizar
                        if (paciente.getId() == nuevoPaciente.getId()) {
                            oos.writeObject(nuevoPaciente);
                            pacienteEncontrado = true;
                        } else {
                            oos.writeObject(paciente);
                        }
                    } catch (EOFException e) {
                        break; // Fin del archivo
                    }
                }
            }
            // Solo reemplazar si encontramos el paciente
            if (pacienteEncontrado) {
                Files.move(tempFile, ruta, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (ClassNotFoundException | ClassCastException e) {
            throw new IOException("Error en el formato de datos", e);
        } finally {
            Files.deleteIfExists(tempFile);
        }
        return pacienteEncontrado;
    }

    /**
     * Metodo que crea una copia del archivo y copia los elementos a excepcion del objetivo
     * si llega a encontrarse se copia el contenido temporal al base y se elimina la copia
     * @param idBuscado
     * @throws IOException
     */
    public void eliminarPaciente(int idBuscado) throws IOException {
        if (Files.size(ruta) == 0) return;
        Path tempFile = Files.createTempFile(ruta.getParent(), "temp_pacientes", ".tmp");
        boolean eliminado = false;
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(ruta));
             ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(tempFile))) {
            while (true) {
                try {
                    Paciente p = (Paciente) ois.readObject();
                    if (p.getId() != idBuscado) {
                        oos.writeObject(p);
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
     * Método que consulta un paciente mediante id
     * @param idBuscado
     * @return un objeto Paciente
     * @throws java.io.IOException
     */
    public Paciente consultarPacienteId(int idBuscado) throws IOException {
        if (Files.size(ruta) == 0) {
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(ruta))) {
            while (true) {
                try {
                    Paciente paciente = (Paciente) ois.readObject();
                    if (paciente.getId() == idBuscado) {
                        return paciente;
                    }
                } catch (EOFException e) {
                    break; // Fin del archivo alcanzado
                } catch (ClassNotFoundException | ClassCastException e) {
                    throw new IOException("Formato de datos inválido en el archivo de pacientes", e);
                }
            }
        } catch (IOException ex) {
            throw new IOException("Error al leer el archivo de pacientes: " + ex.getMessage(), ex);
        }
        return null; // Paciente no encontrado
    }

    /**
     * lista todos los pacientes del achivo .dat
     * @return
     * @throws IOException
     */
    public List<Paciente> listarPacientes() throws IOException {
        if (Files.size(ruta) == 0) {
            return null;
        }
        List<Paciente> listaPacientes = new LinkedList<>();
        
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(ruta))) {
            while (true) {
                try {
                    Paciente paciente = (Paciente) ois.readObject();
                    listaPacientes.add(paciente);
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException | ClassCastException e) {
                    throw new IOException("Formato de datos inválido en el archivo de pacientes", e);
                }
            }
        } catch (IOException ex) {
            throw new IOException("Error al leer el archivo de pacientes: " + ex.getMessage(), ex);
        }
        return listaPacientes;
    }
}
