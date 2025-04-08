/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencias;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import entidades.EquipoMedico;
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
public class PersistenciaInventarios {
    //Ruta abstracta donde se guarda cada equipo médico 
    private Path ruta = Paths.get("SistemaHospital/src/main/resources/inventarios.txt");

    /**
     * Método que agrega un equipo nédico a un archivo de texto utilizando la clase FileWriter 
     * @param equipoMedico
     */
    public void agregarEquipoMedico(EquipoMedico equipoMedico) throws IOException{

        String linea = equipoMedico.getId() + "-" + equipoMedico.getNombre() + "-" + equipoMedico.getCantidad();

        try(FileWriter writer = new FileWriter(ruta.toFile(), true)){
            writer.write(linea);
        } catch (IOException ex) {
            throw new IOException("Fallo en el sistema al agregar equipo medico");
        }
    }
    
    public EquipoMedico consultarEquipoMedicoId(int idBuscado) throws IOException {
        String linea;
        String[] atributos;
        try(BufferedReader reader = new BufferedReader(new FileReader(ruta.toFile()))){
            while((linea = reader.readLine()) != null){
                atributos = linea.split("-");
                if(parseInt(atributos[0]) == idBuscado){
                    EquipoMedico equipoMedico = new EquipoMedico(parseInt(atributos[0]), atributos[1],Integer.parseInt(atributos[2]));
                    return equipoMedico;
                }
            }
            
        }catch(IOException ex){
            throw new IOException("Fallo en el sistema al consultar equipo medico");
        }
        return null;
    }
    
    
    public List<EquipoMedico> consultarInventario(int cantidad) throws IOException{
        List<EquipoMedico> listaEquipoMedico = null;
        String[] atributos;
        try {
            List<String> lineas = Files.readAllLines(ruta);
            for (String linea : lineas) {
                atributos = linea.split("-");
                if (Integer.parseInt(atributos[2]) == cantidad) {
                    listaEquipoMedico.add(new EquipoMedico(Integer.parseInt(atributos[0]), atributos[1], Integer.parseInt(atributos[2])));
                }
            }
        } catch (IOException ex) {
            throw new IOException("Hubo un problema en el sistema");
        }
        return listaEquipoMedico;
    }
    
    public List<EquipoMedico> consultarInventario() throws IOException{
                List<EquipoMedico> listaEquipoMedico = null;
        String[] atributos;
        try {
            List<String> lineas = Files.readAllLines(ruta);
            for (String linea : lineas) {
                atributos = linea.split("-");
                listaEquipoMedico.add(new EquipoMedico(Integer.parseInt(atributos[0]), atributos[1], Integer.parseInt(atributos[2])));
            }
        } catch (IOException ex) {
            throw new IOException("Hubo un problema en el sistema al consultar inventario");
        }
        return listaEquipoMedico;
    }
    
    public void inventariarEquipo(){
        
    }
 
    /**
     * 
     * @param nuevoEquipo 
     * @throws java.io.IOException 
     */
    public void actualizarInventario(EquipoMedico nuevoEquipo) throws IOException {
        List<String> nuevasLineas = new ArrayList<>();
        String[] atributos;
        try {
            List<String> lineas = Files.readAllLines(ruta);
            for (String linea : lineas) {
                atributos = linea.split("-");

                if (Integer.parseInt(atributos[0]) == nuevoEquipo.getId()) {
                    String nuevaLinea = nuevoEquipo.getId() + "-" + nuevoEquipo.getNombre() + "-" + nuevoEquipo.getCantidad();
                    nuevasLineas.add(nuevaLinea);
                } else {
                    nuevasLineas.add(linea);
                }

            }
            Files.write(ruta, nuevasLineas, StandardOpenOption.TRUNCATE_EXISTING);

        } catch (IOException ex) {
            throw new IOException("Error en el sistema al actualizar inventario");
        }

    }

}
