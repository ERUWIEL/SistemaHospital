/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencias;

import entidades.Especialidad;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import entidades.Medico;
import java.io.BufferedReader;
import java.io.FileReader;
import static java.lang.Integer.parseInt;

/**
 *
 * @author angel
 */
public class PersistenciaMedicos {
    //Ruta abstracta donde se guarda cada médico
    private Path ruta = Paths.get("SistemaHospital/src/main/resources/medicos.txt");

    /**
     * Método que agrega un médico a un archivo de texto utilizando la clase FileWriter 
     * @param medico
     */
    public void agregarMedico(Medico medico) throws IOException{
    
    //recordar que la especialidad abarca dos indices en medicos
    String linea = medico.getId() + "-" + medico.getNombre() + "-" + medico.getEspecialidad().getId() + "-" + medico.getEspecialidad().getNombre() + "\n"; 
    try(FileWriter writer = new FileWriter(ruta.toFile(), true)){
        writer.write(linea);
    }catch(IOException ex){
        throw new IOException("Un problema en el sistema para agregar medico");
        }

    }

    public Medico consultarMedicoId(int idBuscado) throws IOException {
        String linea;
        String[] atributos;
        try(BufferedReader reader = new BufferedReader(new FileReader(ruta.toFile()))){
            while((linea = reader.readLine()) != null){
                atributos = linea.split("-");
                if(parseInt(atributos[0]) == idBuscado){
                    Medico medico = new Medico(parseInt(atributos[0]), atributos[1], new Especialidad(Integer.parseInt(atributos[2]), atributos[3]));
                    return medico;
                }
            }
        }catch(IOException ex){
            throw new IOException("Un problema en el sistema para consultar medico");
        }
        return null;
    }
    
    
    
}
