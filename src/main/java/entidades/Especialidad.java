
package entidades;

/**
 * clase que modela una especialidad medica
 * @author angel erubiel flores jimenez
 */
public class Especialidad {
    private int id;
    private String nombre;
    /**
     * Metodo constructor de la especialidad
     * @param id
     * @param nombre 
     */
    public Especialidad(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    /**
     * Metodo que regresa el id de la especialidad
     * @return 
     */
    public int getId() {
        return id;
    }
    /**
     * Metodo que regresa el nombre de la especialidad
     * @return 
     */
    public String getNombre() {
        return nombre;
    }
}
