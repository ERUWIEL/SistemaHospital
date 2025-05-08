
package persistencias;

import interfaz.IPersistenciaFachada;
import entidades.Consulta;
import entidades.EquipoMedico;
import entidades.Especialidad;
import entidades.Medico;
import entidades.Paciente;
import excepciones.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * clase que desarrolla las persistencias
 * 
 * @author angel
 */
public class PersistenciaFachada implements IPersistenciaFachada {

    // instancias de persistencias
    PersistenciaPacientes PP = new PersistenciaPacientes();
    PersistenciaMedicos PM = new PersistenciaMedicos();
    PersistenciaEspecialidades PE = new PersistenciaEspecialidades();
    PersistenciaInventarios PI = new PersistenciaInventarios();
    PersistenciaConsultas PC = new PersistenciaConsultas();

    public PersistenciaFachada() {
    }

    // METODOS DE PACIENTES

    @Override
    public void agregarPaciente(Paciente paciente) throws ObjetoExistenteException, IOException {
        // validaciones
        if(PP.consultarPacienteId(paciente.getId()) != null){
            throw new ObjetoExistenteException("paciente existente");
        }
        if (!Paciente.validaNombrePaciente(paciente.getNombre())){
            throw new ObjetoExistenteException("nombre de paciente invalido");
        }
        if (!Paciente.validaEdad(paciente.getEdad())){
            throw new ObjetoExistenteException("edad de paciente invalida");
        }
        if (!Paciente.validaDireccion(paciente.getDireccion())){
            throw new ObjetoExistenteException("direccion de paciente invalida");
        }
        
        PP.agregarPaciente(paciente);
    }

    @Override
    public boolean actualizarPaciente(Paciente paciente) throws ObjetoExistenteException, IOException {
        return PP.actualizarPaciente(paciente);
    }

    @Override
    public void eliminarPaciente(int id) throws ObjetoExistenteException, IOException {
        //validacion
        if(PP.consultarPacienteId(id) == null){
            throw new ObjetoExistenteException("paciente inexistente");
        }
        PP.eliminarPaciente(id);
    }

    @Override
    public Paciente obtenerPacientePorId(int id) throws ObjetoInexistenteException, IOException {
        Paciente paciente  = PP.consultarPacienteId(id);
        if(paciente == null){
            throw new ObjetoInexistenteException("paciente inexistente");
        }
        return paciente;
    }

    @Override
    public List<Paciente> listarPacientes() throws IOException {
        return PP.listarPacientes();
    }

    public List<Paciente> listarPacientes(Integer edadInicial, Integer edadFinal, String direccion) throws IOException {
        List<Paciente> pacientes = PP.listarPacientes();
        return pacientes.stream()
                .filter(Objects::nonNull)
                .filter(p -> (edadInicial == null || p.getEdad() >= edadInicial) &&
                        (edadFinal == null || p.getEdad() <= edadFinal) &&
                        (direccion == null ||
                        (p.getDireccion() != null && p.getDireccion().equalsIgnoreCase(direccion))))
                .collect(Collectors.toList());
    }

    // METODOS DE MEDICOS

    @Override
    public void agregarMedico(Medico medico) throws ObjetoExistenteException, IOException {

        if(PM.consultarMedicoId(medico.getId()) != null){
            throw new ObjetoExistenteException("medico existente");
        }
        if (!Medico.validaNombreMedico(medico.getNombre())){
            throw new ObjetoExistenteException("nombre de medico invalido");
        }
        if (!Medico.validaEspecialidad(medico.getEspecialidad().getNombre())){
            throw new ObjetoExistenteException("especialidad de medico invalida");
        }

        PM.agregarMedico(medico);
    }

    @Override
    public Medico obtenerMedicoPorId(int id) throws ObjetoInexistenteException, IOException {
        return PM.consultarMedicoId(id);
    }

    @Override
    public List<Medico> listarMedicos() throws IOException {
        return PM.listarMedicos();
    }

    // METODOS DE ESPECIALIDADES

    @Override
    public void agregarEspecialidad(Especialidad especialidad) throws ObjetoExistenteException, IOException {
        PE.agregarEspecialidad(especialidad);
    }

    @Override
    public void actualizarEspecialidad(Especialidad especialidad) throws ObjetoExistenteException, IOException {
        PE.actualizarEspecialidad(especialidad);
    }

    @Override
    public void eliminarEspecialidad(int id) throws ObjetoExistenteException, IOException {
        PE.eliminarEspecialidad(id);
    }

    @Override
    public Especialidad obtenerEspecialidadPorId(int id) throws ObjetoInexistenteException, IOException {
        return PE.consultarEspecialidadId(id);
    }

    @Override
    public List<Especialidad> listarEspecialidades() throws IOException {
        return PE.listarEspecialidades();
    }

    // METODOS DE EQUIPOS MEDICOS

    @Override
    public EquipoMedico consultarEquipoMedicoId(int id) throws IOException, ObjetoInexistenteException {
        return PI.consultarEquipoId(id);
    }

    @Override
    public void inventariarEquipo(int id, int cantidad) throws ObjetoInexistenteException, IOException {
        EquipoMedico equipo = PI.consultarEquipoId(id);
        PI.agregarEquipo(new EquipoMedico(id, equipo.getNombre(), cantidad));
    }

    @Override
    public void desinventariarEquipo(int id, int cantidad) throws ObjetoInexistenteException, IOException {
        EquipoMedico equipo = PI.consultarEquipoId(id);
        int existencias = equipo.getCantidad() - cantidad;

        if (existencias < 0) {
            throw new ObjetoInexistenteException("error: cantidad sobre girada de elementos");
        } else if (existencias == 0) {
            PI.eliminarEquipo(id); // eliminar si ya no queda nada
        } else {
            PI.actualizarEquipo(new EquipoMedico(id, equipo.getNombre(), cantidad));
        }
    }

    // METODOS DE CONSULTAS

    @Override
    public void programarConsulta(Consulta consulta) throws ObjetoExistenteException, IOException {
        PC.programarConsulta(consulta);

        throw new UnsupportedOperationException("Unimplemented method 'desinventariarEquipo'");
    }

    @Override
    public void cancelarConsulta(int id) throws ObjetoExistenteException, IOException {
        PC.cancelarConsulta(id);
    }

    @Override
    public List<Consulta> listarConsultas() throws ObjetoExistenteException, IOException {
        return PC.listarConsultas();
    }

}
