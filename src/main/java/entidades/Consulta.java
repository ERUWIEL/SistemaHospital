package entidades;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * clase que permite modelar una consulta medica
 * @author angel erubiel flores jimenez
 */
public class Consulta implements Serializable{

    private int id; 
    private Paciente paciente;
    private Medico medico;
    private String fecha;
    
    /**
     * metodo constructor que permite crear una consulta
     * @param id
     * @param paciente
     * @param medico
     * @param fecha 
     */
    public Consulta(int id, Paciente paciente, Medico medico, String fecha) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
    }
    /**
     * metodo que regresa el id de una consulta
     * @return 
     */
    public int getId() {
        return id;
    }
    /**
     * metodo que regresa el Paciente de una consulta
     * @return 
     */
    public Paciente getPaciente() {
        return paciente;
    }
    /**
     * metodo que regresa el Medico de una consulta
     * @return 
     */
    public Medico getMedico() {
        return medico;
    }
    /**
     * metodo que regresa la fecha de una consulta
     * @return 
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Metodo que permite consultar el formato pattern valido de una fecha en una consulta
     * @return formato fecha
     */
    public static boolean validaFechaConsulta(String fecha){
        Pattern pattern = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])\\/(0[1-9]|1[0-2])\\/([0-9]{4})$");
        Matcher matcher = pattern.matcher(fecha);
        return matcher.find();
    }

    /**
     * Metodo que permite consultar el formato pattern valido de un id en una consulta
     * @return formato id
     */
    public static boolean validaIdConsulta(int id){
        Pattern pattern = Pattern.compile("^[1-9][0-9]*$");
        Matcher matcher = pattern.matcher(Integer.toString(id));
        return matcher.find();
    }

    public boolean buscarConsultasPorFecha(String fechaBuscada){
        Pattern pattern = Pattern.compile(fechaBuscada);
        Matcher matcher = pattern.matcher(fecha);
        return matcher.find();
    }
}
