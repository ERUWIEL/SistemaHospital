
package persistencias;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import entidades.Paciente;
import java.io.BufferedReader;
import java.io.FileReader;
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
    private final Path ruta = Paths.get("SistemaHospital/src/main/resources/pacientes.txt");
    
    /**
     * Método que agrega un paciente a un archivo de texto utilizando la clase FileWriter 
     * @param paciente
     * @throws java.io.IOException
     */
    public void agregarPaciente(Paciente paciente) throws IOException{
    
        String linea = paciente.getId() + "-" + paciente.getNombre() + "-" + paciente.getEdad() + "-" + paciente.getDireccion() + "\n";
        try(FileWriter writer = new FileWriter(ruta.toFile(), true)){
            writer.write(linea);
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
        String linea;
        String[] atributos;
        try(BufferedReader reader = new BufferedReader(new FileReader(ruta.toFile()))){
            while((linea = reader.readLine()) != null){
                atributos = linea.split("-");
                if(parseInt(atributos[0]) == idBuscado){
                    Paciente paciente = new Paciente(parseInt(atributos[0]), atributos[1], parseInt(atributos[2]), atributos[3]);
                    return paciente;
                }
            }
            
        }catch(IOException ex){
            throw new IOException("Error en el sistema al consultar paciente");
        }
        return null;
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

