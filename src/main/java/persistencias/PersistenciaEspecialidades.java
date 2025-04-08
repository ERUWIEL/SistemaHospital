/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencias;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import entidades.Especialidad;
import java.io.BufferedReader;
import java.io.FileReader;
import static java.lang.Integer.parseInt;

/**
 *
 * @author angel
 */
public class PersistenciaEspecialidades {
    //Ruta abstracta donde se guarda cada equipo médico 
    private Path ruta = Paths.get("SistemaHospital/src/main/resources/especialidades.txt");
    
    /**
     * Método que agrega un paciente a un archivo de texto utilizando la clase FileWriter 
     * @param especialidad
     */
    public void agregarEspecialidad(Especialidad especialidad){

    String linea = especialidad.getId() + "-" + especialidad.getNombre() + "\n"; //recordar que la especialidad abarca dos indices en medicos
    try(FileWriter writer = new FileWriter(ruta.toFile(), true)){
        writer.write(linea);
    }catch(IOException ex){
        ex.printStackTrace();
        }

    }
    
        public Especialidad consultarEspecialidadId(int idBuscado) {
        String linea;
        String[] atributos;
        try(BufferedReader reader = new BufferedReader(new FileReader(ruta.toFile()))){
            while((linea = reader.readLine()) != null){
                atributos = linea.split("-");
                if(parseInt(atributos[0]) == idBuscado){
                    Especialidad especialidad = new Especialidad(parseInt(atributos[0]), atributos[1]);
                    return especialidad;
                }
            }
            
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return null;
    }
        
}
