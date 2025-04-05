
package interfaz;

import entidades.Consulta;
import entidades.EquipoMedico;
import entidades.Especialidad;
import entidades.Medico;
import entidades.Paciente;

import java.util.List;

/**
 * Clase que permite controlar las demas persistencias
 * @author angel
 */
public interface IPersistenciaFachada {
    //Definicion de los metodos sobre pacientes
    /**
     * metodo que permite agregar un paciente ala persistencia
     * @param paciente 
     */
    abstract void agregarPaciente(Paciente paciente);
    /**
     * Metodo que permite obtener un paciente de la persistencia en base un id
     * @param id
     * @return 
     */
    abstract Paciente obtenerPacientePorId(int id);
    /**
     * Metodo que permite consultar todos los pacientes de la persistencia
     * @return 
     */
    abstract List<Paciente> listarPacientes();
    
    //Definicion de los metodos sobre medicos
    /**
     * metodo que permite agregar un medico ala persitencia
     * @param medico 
     */
    abstract void agregarMedico(Medico medico);
    /**
     * metodo que permite obtener un paciente de la persistencia en base un id
     * @param id
     * @return 
     */
    abstract Medico obtenerMedicoPorId(int id);
    /**
     * metodo que permite consultar todos medicos de la persistencia
     * @return 
     */
    abstract List<Medico> listarMedicos();
    
    //Definicion de los metodos sobre especialidades
    /**
     * metodo que permite agregar una especialidad ala persistencia
     * @param especialidad 
     */
    abstract void agregarEspecialidad(Especialidad especialidad);
    /**
     * metodo que permite obtener una especialidad de la persistencia en base un id
     * @param id
     * @return 
     */
    abstract Especialidad obtenerEspecialidadPorId(int id);
    /**
     * metodo que permite consultar todas las especialidades de la persistencia
     * @return 
     */
    abstract List<Especialidad> listarEspecialidades();
    
    //Definicion de los metodos sobre equipos medicos
    /**
     * metodo que permite agregar un equipo ala persistencia
     * @param equipo 
     */
    abstract void agregarEquipoMedico(EquipoMedico equipo);
    /**
     * metodo que permite actualizar la cantidad de existencias de un equipo ala persistencia
     * @param id
     * @param cantidad 
     */
    abstract void actualizarCantidadEquipo(int id, int cantidad);
    /**
     * metodo que permite obtener todos los equipos medicos de la persistencia
     * @return 
     */
    abstract List<EquipoMedico> listarEquiposMedicos();
    
    //Definicion de los metodos sobre consultas
    /**
     * metodo que permite agregar una consulta ala persistencia
     * @param consulta 
     */
    abstract void programarConsulta(Consulta consulta);
    /**
     * metodo que permite obtener todas las consultas de la persistencia
     * @return 
     */
    abstract List<Consulta> listarConsultas();
}
