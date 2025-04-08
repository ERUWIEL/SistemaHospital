
package persistencias;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import entidades.Consulta;

/**
 *
 * @author angel
 */
public class PersistenciaConsultas {
    //Ruta abstracta donde se guarda cada equipo médico 
    private Path ruta = Paths.get("SistemaHospital/src/main/resources/consultas.txt");
    
    /**
     * Método que agrega un paciente a un archivo de texto utilizando la clase FileWriter 
     * @param especialidad
     */
    public void agregarConsulta(Consulta consulta) throws IOException{

    //recordar que la consulta guarda cada atributo de paciente y medico y medico el de especialidad
    //quedaría así consulta id, id paciente, nombre paciente, edad paciente, dirección paciente, id medico, nombre medico, especialidad medico id, 
    //especialidad medico nombre, consulta fecha 
    String linea = consulta.getId() + "-" + consulta.getPaciente().getId() + "-" + consulta.getPaciente().getNombre() 
    + "-" + consulta.getPaciente().getEdad() + "-" + consulta.getPaciente().getDireccion() + "-" 
    + consulta.getMedico().getId() + "-" + consulta.getMedico().getNombre() + "-" 
    + consulta.getMedico().getEspecialidad().getId() + "-" + consulta.getMedico().getEspecialidad().getNombre() 
    + "-" + consulta.getFecha() + "\n"; 
    try(FileWriter writer = new FileWriter(ruta.toFile(), true)){
        writer.write(linea);
    }catch(IOException ex){
        throw new IOException("Hubo un error en el sistema al agregar una consulta");
        }
    }
}
