
package persistencias;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import entidades.Paciente;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Integer.parseInt;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author angel
 */
public class PersistenciaPacientes {
    //Ruta abstracta donde se guarda cada paciente 
    private final Path ruta = Paths.get("src", "main", "resources", "pacientes.dat");
    
    /**
     * Método que agrega un paciente a un archivo de texto utilizando la clase FileWriter 
     * @param paciente
     * @throws java.io.IOException
     */
    public void agregarPaciente(Paciente paciente) throws IOException{
        ObjectOutputStream oss;
        try(FileOutputStream writer = new FileOutputStream(ruta.toFile(), true)){
        {
            if(Files.exists(ruta) && Files.size(ruta) > 0){
                oss = new MyObjectOutputStream(writer);
            }else{
                oss = new ObjectOutputStream(writer);
            }
            oss.writeObject(paciente);
        }
        }catch(IOException ex){
            throw new IOException("Error en el sistema al agregar paciente");
        }
    }
    
    /**
     * Método que consulta un paciente mediante id
     * @param idBuscado
     * @return un objeto Paciente 
     * @throws java.io.IOException 
     */
    public Paciente consultarPacienteId(int idBuscado) throws IOException{
if (!Files.exists(ruta) || Files.size(ruta) == 0) {
        return null; // No hay archivo o está vacío
    }

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta.toFile()))) {
        while (true) {
            try {
                Paciente paciente = (Paciente) ois.readObject();
                if (paciente.getId() == idBuscado) {
                    return paciente;
                }
            } catch (EOFException e) {
                break; // Fin del archivo
            } catch (ClassNotFoundException e) {
                throw new IOException("No se pudo leer el objeto paciente.");
            }
        }
    } catch (IOException ex) {
        throw new IOException("Error en el sistema al consultar paciente.");
    }

    return null; // No se encontró el paciente
    }
    
    public void actualizarPaciente(Paciente nuevoPaciente) throws IOException{
        List<String> nuevasLineas = new ArrayList<>();
        String[] atributos;
        
        try{
            List<String> lineas = Files.readAllLines(ruta);
            for(String linea : lineas){
                atributos = linea.split("-");
                int id = Integer.parseInt(atributos[0]);
                if(id == nuevoPaciente.getId()){
                    String nuevaLinea = nuevoPaciente.getId() + "-" + nuevoPaciente.getNombre() + "-" + nuevoPaciente.getEdad() 
                            + "-" + nuevoPaciente.getDireccion();
                    nuevasLineas.add(nuevaLinea);
                }else{
                    nuevasLineas.add(linea);
                }
                
            }
            Files.write(ruta, nuevasLineas, StandardOpenOption.TRUNCATE_EXISTING);
            
        }catch(IOException ex){
            throw new IOException("Error en el sistema al actualizar paciente");
        }
    }
    
    public void eliminarPaciente(int idBuscado) throws IOException{
        List<String> nuevasLineas = new ArrayList<>();
        String[] atributos;
        int id;
        try{
            List<String> lineas = Files.readAllLines(ruta);
            for(String linea : lineas){
                atributos = linea.split("-");
                id = Integer.parseInt(atributos[0]);
                if(id != idBuscado){
                    nuevasLineas.add(linea);
                }
            }
            Files.write(ruta, nuevasLineas, StandardOpenOption.TRUNCATE_EXISTING);
        }catch(IOException ex){
            throw new IOException("Error en el sistema al eliminar paciente");
        }
        
    }
    
    public List<Paciente> listarPacientes() throws IOException{
        List<Paciente> listaPacientes = null;
        String[] atributos;
        try{
            List<String> lineas = Files.readAllLines(ruta);
            for(String linea : lineas){
                atributos = linea.split("-");
                listaPacientes.add(new Paciente(Integer.parseInt(atributos[0]), atributos[1], Integer.parseInt(atributos[2]), atributos[3]));
            }
        }catch(IOException ex){
            listaPacientes = null;
            throw new IOException("Error en el sistema al listar paciente");
        }
        return listaPacientes;
    }
    
    public List<Paciente> listarPacientes(String direccion) throws IOException {
        List<Paciente> listaPacientes = null;
        String[] atributos;
        try {
            List<String> lineas = Files.readAllLines(ruta);
            for (String linea : lineas) {
                atributos = linea.split("-");
                if (atributos[3].equals(direccion)) {
                    listaPacientes.add(new Paciente(Integer.parseInt(atributos[0]), atributos[1], Integer.parseInt(atributos[2]), atributos[3]));
                }
            }
        } catch (IOException ex) {
            listaPacientes = null;
            throw new IOException("Error en el sistema al listar paciente");
        }
        return listaPacientes;
    }
    
    public List<Paciente> listarPacientes(int edadInicial, int edadFinal) throws IOException {
        List<Paciente> listaPacientes = null;
        String[] atributos;
        try {
            List<String> lineas = Files.readAllLines(ruta);
            for (String linea : lineas) {
                atributos = linea.split("-");
                if (Integer.parseInt(atributos[2]) <= edadFinal && Integer.parseInt(atributos[2]) >= edadInicial) {
                    listaPacientes.add(new Paciente(Integer.parseInt(atributos[0]), atributos[1], Integer.parseInt(atributos[2]), atributos[3]));
                }
            }
        } catch (IOException ex) {
            listaPacientes = null;
            throw new IOException("Error en el sistema al listar paciente");
        }
        return listaPacientes;
    }
    
    public List<Paciente> listarPacientes(String direccion, int edadInicial, int edadFinal) throws IOException {
        List<Paciente> listaPacientes = null;
        String[] atributos;
        try {
            List<String> lineas = Files.readAllLines(ruta);
            for (String linea : lineas) {
                atributos = linea.split("-");
                if (Integer.parseInt(atributos[2]) <= edadFinal && Integer.parseInt(atributos[2]) >= edadInicial && atributos[3].equals(direccion)) {
                    listaPacientes.add(new Paciente(Integer.parseInt(atributos[0]), atributos[1], Integer.parseInt(atributos[2]), atributos[3]));
                }
            }
        } catch (IOException ex) {
            listaPacientes = null;
            throw new IOException("Error en el sistema al listar paciente");
        }
        return listaPacientes;
    }
}

