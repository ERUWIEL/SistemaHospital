
package entidades;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * clase que modela un paciente
 * @author angel erubiel flores jimenez
 */
public class Paciente implements Serializable{
    
    private int id;
    private String nombre;
    private int edad;
    private String direccion;
    
    /**
     * metodo constructor de un paciente
     * @param id
     * @param nombrePaciente
     * @param edad
     * @param direccion 
     */
    public Paciente(int id, String nombre, int edad, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
    }
    /**
     * metodo que regresa el id de un paciente
     * @return 
     */
    public int getId() {
        return id;
    }
    /**
     * metodo que regresa el nombre de un paciente
     * @return 
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * metodo que regresa la edad de un paciente
     * @return 
     */
    public int getEdad() {
        return edad;
    }
    /**
     * metodo que regresa la direccion de un paciente
     * @return 
     */
    public String getDireccion() {
        return direccion;
    }
    /**
     * metodo que permite settear una direccion al paciente
     * @param direccion 
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Metodo que valida un nombre con regex solo letras y maxima de 50 caracteres
     * @param nombre
     * @return
     */
    public static boolean validaNombrePaciente(String nombre){
        Pattern pattern = Pattern.compile("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{1,50}$"); 
        Matcher matcher = pattern.matcher(nombre);
        return matcher.find();
    }

    /**
     * Metodo que valida una edad con regex positivos de 0 a 120
     * @param edad
     * @return
     */
    public static boolean validaEdad(int edad){
        Pattern pattern = Pattern.compile("^(120|[0-9]|[1-9][0-9]|1[01][0-9])$"); 
        Matcher matcher = pattern.matcher(Integer.toString(edad));
        return matcher.find();
    }

    /**
     * Metodo que valida una direccion con regex positivos de 0 a 120
     * @param direccion
     * @return
     */
    public static boolean validaDireccion(String direccion){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s.,]+$");
        Matcher matcher = pattern.matcher(direccion);
        return matcher.find();
    }

    public boolean buscarPorNombre(String patron){
        Pattern pattern = Pattern.compile(patron, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(nombre);
        return matcher.find();
    }
}
