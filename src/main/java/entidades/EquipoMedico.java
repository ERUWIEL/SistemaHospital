
package entidades;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * clase que permite modelar los equipos medicos
 * @author angel erubiel flores jimenez
 */
public class EquipoMedico implements Serializable{

    private int id;
    private String nombre;
    private int cantidad;
    /**
     * metodo constructor de la clase equipos medicos
     * @param id
     * @param nombre
     * @param cantidad 
     */
    public EquipoMedico(int id, String nombre, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }
    /**
     * metodo que regresa el id del equipo medico
     * @return 
     */
    public int getId() {
        return id;
    }
    /**
     * metodo que regresa el nombre del equipo medico
     * @return 
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * metodo que regresa la cantidad del equipo medico
     * @return 
     */
    public int getCantidad() {
        return cantidad;
    }
    /**
     * metodo que permite settar una cantidad de equipos
     * @param cantidad 
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Metodo que permite consultar el formato valido para el nombre de un equipo
     * @return formato nombre
     */
    public static boolean validaNombreEquipo(String nombre){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9áéíóúüÁÉÍÓÚÜñÑ]{1,30}$");
        Matcher matcher = pattern.matcher(nombre);
        return matcher.find();
    }

    /**
     * Metodo que permite consultar el formato valido para la cantidad de un existencias de un equipo
     * @return formato cantidad
     */
    public static boolean validaCantidadEquipo(int cantidad){
        Pattern pattern = Pattern.compile("^[1-9][0-9]*$");
        Matcher matcher = pattern.matcher(Integer.toString(cantidad));
        return matcher.find();
    }
}
