
package entidades;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * clase que modela un personal medico
 * @author angel
 */
public class Medico implements Serializable{
    private int id;
    private String nombre;
    private Especialidad especialidad;

    /**
     * Metodo constructor de un medico
     * @param id
     * @param nombre
     * @param especialidad 
     */
    public Medico(int id, String nombre, Especialidad especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }
    /**
     * metodo que regresa el id del medico
     * @return 
     */
    public int getId() {
        return id;
    }
    /**
     * metodo que regresa el nombre del medico
     * @return 
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * metodo que regresa la especialidad del medico
     * @return 
     */
    public Especialidad getEspecialidad() {
        return especialidad;
    }
    /**
     * metodo que permite settear una especialidad al medico
     * @param especialidad 
     */
    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Metodo que permite consultar el formato valido para un nombre de medico
     * @return formato nombre medico
     */
    public static boolean validaNombreMedico(String nombre){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s.,]{1,50}");
        Matcher matcher = pattern.matcher(nombre);
        return matcher.find();
    }

    /**
     * MEtodo que permite consultar el formato calido para un nombre de especialidad medica
     * @return formato nombre especialidad
     */
    public static boolean validaEspecialidad(String especialidad){
        Pattern pattern = Pattern.compile("^[a-zA-ZáéíóúüÁÉÍÓÚÜñÑ\\s]{1,30}$");
        Matcher matcher = pattern.matcher(especialidad);
        return matcher.find();
    }
}
