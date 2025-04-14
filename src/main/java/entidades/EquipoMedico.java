
package entidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * clase que permite modelar los equipos medicos
 * @author angel erubiel flores jimenez
 */
public class EquipoMedico {
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

    public boolean validarNombre(String nombre){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9áéíóúüÁÉÍÓÚÜñÑ]{1,30}$");
        Matcher matcher = pattern.matcher(nombre);
        return matcher.find();
    }

    public boolean validaCantidad(int cantidad){
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher matcher = pattern.matcher(String.valueOf(cantidad));
        return matcher.find();
    }
}
