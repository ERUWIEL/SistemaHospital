
package interfaz;

import entidades.Consulta;
import entidades.EquipoMedico;
import entidades.Especialidad;
import entidades.Medico;
import entidades.Paciente;
import excepciones.*;
import java.io.IOException;

import java.util.List;

/**
 * Clase que permite controlar las demas persistencias
 * NOTA las persistencias tienen la capacidad de hacer mas tareas
 * @author angel
 */
public interface IPersistenciaFachada {
    //Definicion de los metodos sobre pacientes
    /**
     * metodo que permite agregar un paciente ala persistencia
     * @param paciente 
     * @throws excepciones.ObjetoExistenteException 
     * @throws java.io.IOException 
     */
    abstract void agregarPaciente(Paciente paciente)throws ObjetoExistenteException, IOException;
    /**
     * metodo que permite modificar un paciente de ala persistencia
     * @param paciente 
     * @throws excepciones.ObjetoExistenteException 
     * @throws java.io.IOException 
     */
    abstract boolean actualizarPaciente(Paciente paciente)throws ObjetoExistenteException, IOException;
    /**
     * metodo que permite eliminar un paciente ala persistencia
     * @param paciente 
     * @throws excepciones.ObjetoExistenteException 
     * @throws java.io.IOException 
     */
    abstract void eliminarPaciente(Paciente paciente)throws ObjetoExistenteException, IOException;
    /**
     * Metodo que permite obtener un paciente de la persistencia en base un id
     * @param id
     * @return 
     * @throws excepciones.ObjetoInexistenteException 
     * @throws java.io.IOException 
     */
    abstract Paciente obtenerPacientePorId(int id)throws ObjetoInexistenteException, IOException;
    /**
     * Metodo que permite consultar todos los pacientes de la persistencia
     * @return 
     * @throws java.io.IOException 
     */
    abstract List<Paciente> listarPacientes()throws IOException;
    

    //Definicion de los metodos sobre medicos
    /**
     * metodo que permite agregar un medico ala persitencia
     * @param medico 
     * @throws excepciones.ObjetoExistenteException 
     * @throws java.io.IOException 
     */
    abstract void agregarMedico(Medico medico)throws ObjetoExistenteException, IOException;
    /**
     * metodo que permite obtener un paciente de la persistencia en base un id
     * @param id
     * @return 
     * @throws excepciones.ObjetoInexistenteException 
     * @throws java.io.IOException 
     */
    abstract Medico obtenerMedicoPorId(int id)throws ObjetoInexistenteException, IOException;


    //Definicion de los metodos sobre especialidades
    /**
     * metodo que permite agregar una especialidad ala persistencia
     * @param especialidad 
     * @throws excepciones.ObjetoExistenteException 
     * @throws java.io.IOException 
     */
    abstract void agregarEspecialidad(Especialidad especialidad)throws ObjetoExistenteException, IOException;
    /**
     * metodo que permite modificar una especialidad ala persistencia
     * @param especialidad 
     * @throws excepciones.ObjetoExistenteException 
     * @throws java.io.IOException 
     */
    abstract void actualizarEspecialidad(Especialidad especialidad)throws ObjetoExistenteException, IOException;
    /**
     * metodo que permite eliminar una especialidad ala persistencia
     * @param especialidad 
     * @throws excepciones.ObjetoExistenteException 
     * @throws java.io.IOException 
     */
    abstract void eliminarEspecialidad(int id)throws ObjetoExistenteException, IOException;
    /**
     * metodo que permite obtener una especialidad de la persistencia en base un id
     * @param id
     * @return 
     * @throws excepciones.ObjetoInexistenteException 
     * @throws java.io.IOException 
     */
    abstract Especialidad obtenerEspecialidadPorId(int id)throws ObjetoInexistenteException, IOException;
    /**
     * Metodo que permite consultar todos los medicos de la persistencia
     * @return 
     * @throws java.io.IOException 
     */
    abstract List<Especialidad> listarEspecialidades()throws IOException;
    

    //Definicion de los metodos sobre equipos medicos
    /**
     * metodo que permite obtener todos los equipos medicos de la persistencia
     * @param id
     * @return 
     * @throws java.io.IOException 
     * @throws excepciones.ObjetoInexistenteException 
     */
    abstract EquipoMedico consultarEquipoMedicoId(int id) throws IOException, ObjetoInexistenteException;
    /**
     * metodo que permite actualizar la cantidad de existencias de un equipo ala persistencia
     * @param id
     * @param cantidad 
     * @throws excepciones.ObjetoInexistenteException 
     * @throws java.io.IOException 
     */
    abstract void inventariarEquipo(int id, int cantidad)throws ObjetoInexistenteException, IOException;
    /**
     * 
     * @param id
     * @param cantidad
     * @throws ObjetoInexistenteException
     * @throws IOException 
     */
    abstract void desinventariarEquipo(int id, int cantidad)throws ObjetoInexistenteException, IOException;
    

    //Definicion de los metodos sobre consultas
    /**
     * metodo que permite agregar una consulta ala persistencia
     * @param consulta 
     * @throws excepciones.ObjetoExistenteException 
     * @throws java.io.IOException 
     */
    abstract void programarConsulta(Consulta consulta)throws ObjetoExistenteException, IOException;
    /**
     * metodo que permite cancelar  una consulta ala persistencia
     * @param consulta 
     * @throws excepciones.ObjetoExistenteException 
     * @throws java.io.IOException 
     */
    abstract void cancelarConsulta(int id)throws ObjetoExistenteException, IOException;
    /**
     * metodo que permite obtener todas las consultas de la persistencia
     * @return 
     */
    abstract List<Consulta> listarConsultas();
}
