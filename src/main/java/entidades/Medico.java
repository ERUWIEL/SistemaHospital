
package entidades;

/**
 * clase que modela un personal medico
 * @author angel
 */
public class Medico {
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
}
