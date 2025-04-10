
package entidades;

import java.io.Serializable;

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
     * @param nombre
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
}
