
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
        if (PP.consultarPacienteId(paciente.getId()) != null) {
            throw new ObjetoExistenteException("paciente existente");
        }
        if (!Paciente.validaNombrePaciente(paciente.getNombre())) {
            throw new ObjetoExistenteException("nombre de paciente invalido");
        }
        if (!Paciente.validaEdad(paciente.getEdad())) {
            throw new ObjetoExistenteException("edad de paciente invalida");
        }
        if (!Paciente.validaDireccion(paciente.getDireccion())) {
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
        // validacion
        if (PP.consultarPacienteId(id) == null) {
            throw new ObjetoExistenteException("paciente inexistente");
        }
        PP.eliminarPaciente(id);
    }

    @Override
    public Paciente obtenerPacientePorId(int id) throws ObjetoInexistenteException, IOException {
        Paciente paciente = PP.consultarPacienteId(id);
        if (paciente == null) {
            throw new ObjetoInexistenteException("paciente inexistente");
        }
        return paciente;
    }

    @Override
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
        if (PM.consultarMedicoId(medico.getId()) != null) {
            throw new ObjetoExistenteException("medico existente");
        }
        if (!Medico.validaNombreMedico(medico.getNombre())) {
            throw new ObjetoExistenteException("nombre de medico invalido");
        }
        PM.agregarMedico(medico);
    }

    @Override
    public Medico obtenerMedicoPorId(int id) throws ObjetoInexistenteException, IOException {
        Medico medico = PM.consultarMedicoId(id);
        if (medico == null) {
            throw new ObjetoInexistenteException("medico inexistente");
        }
        return medico;
    }

    @Override
    public List<Medico> listarMedicos() throws IOException {
        return PM.listarMedicos();
    }

    // METODOS DE ESPECIALIDADES
    @Override
    public void agregarEspecialidad(Especialidad especialidad) throws ObjetoExistenteException, IOException {
        if (PE.consultarEspecialidadId(especialidad.getId()) != null) {
            throw new ObjetoExistenteException("especialidad existente");
        }
        if (!Medico.validaEspecialidad(especialidad.getNombre())) {
            throw new ObjetoExistenteException("nombre invalido");
        }
        PE.agregarEspecialidad(especialidad);
    }

    @Override
    public Especialidad obtenerEspecialidadPorId(int id) throws ObjetoInexistenteException, IOException {
        Especialidad especialidad = PE.consultarEspecialidadId(id);
        if (especialidad == null) {
            throw new ObjetoInexistenteException("especialidad inexistente");
        }
        return especialidad;
    }

    @Override
    public List<Especialidad> listarEspecialidades() throws IOException {
        return PE.listarEspecialidades();
    }

    // METODOS DE EQUIPOS MEDICOS
    @Override
    public EquipoMedico consultarEquipoMedicoId(int id) throws IOException, ObjetoInexistenteException {
        EquipoMedico equipo = PI.consultarEquipoId(id);
        if (equipo == null) {
            throw new ObjetoInexistenteException("equipo no registrado");
        } else {
            return equipo;
        }
    }

    @Override
    public void inventariarEquipo(int id, int cantidad) throws ObjetoInexistenteException, IOException {
        EquipoMedico equipo = PI.consultarEquipoId(id);
        if (equipo == null) {
            throw new ObjetoInexistenteException("equipo no cuenta con registro");
        }
        if (!EquipoMedico.validaCantidadEquipo(cantidad)) {
            throw new ObjetoInexistenteException("cantidad invalida");
        }
        equipo.setCantidad(cantidad + equipo.getCantidad());
        PI.actualizarEquipo(equipo);
    }

    @Override
    public void desinventariarEquipo(int id, int cantidad) throws ObjetoInexistenteException, IOException {
        EquipoMedico equipo = PI.consultarEquipoId(id);
        if (equipo == null) {
            throw new ObjetoInexistenteException("equipo no registrado");
        }
        if (!EquipoMedico.validaCantidadEquipo(cantidad)) {
            throw new ObjetoInexistenteException("cantidad invalida");
        }
        int existencias = equipo.getCantidad() - cantidad;
        if (existencias < 0) {
            throw new ObjetoInexistenteException("cantidad sobre girada");
        }
        equipo.setCantidad(existencias);
        PI.actualizarEquipo(equipo);
    }

    @Override
    public void registrarEquipo(EquipoMedico equipo) throws ObjetoExistenteException, IOException {
        if (PI.consultarEquipoId(equipo.getId()) != null) {
            throw new ObjetoExistenteException("equipo ya registrado");
        }
        if (!EquipoMedico.validaNombreEquipo(equipo.getNombre())) {
            throw new ObjetoExistenteException("nombre invalido");
        }
        PI.agregarEquipo(equipo);
    }

    @Override
    public List<EquipoMedico> listarEquipos(Integer id) throws IOException {
        List<EquipoMedico> equipos = PI.listarEquipoMedicos();
        return equipos.stream()
                .filter(Objects::nonNull)
                .filter(em -> (id == null || em.getId() == id))
                .collect(Collectors.toList());
    }

    // METODOS DE CONSULTAS
    @Override
    public void programarConsulta(Consulta consulta) throws ObjetoExistenteException, IOException {
        if (!Consulta.validaIdConsulta(consulta.getId())) {
            throw new ObjetoExistenteException("id invalido");
        }
        if (PC.consultarConsultasId(consulta.getId()) != null) {
            throw new ObjetoExistenteException("consulta existente");
        }
        if (!Consulta.validaFechaConsulta(consulta.getFecha())) {
            throw new ObjetoExistenteException("fecha invalida");
        }
        PC.programarConsulta(consulta);
    }
    @Override
    public void cancelarConsulta(int id) throws ObjetoInexistenteException, IOException {
        if (!Consulta.validaIdConsulta(id)) {
            throw new ObjetoInexistenteException("id invalido");
        }
        if (PC.consultarConsultasId(id) == null) {
            throw new ObjetoInexistenteException("consulta inexistente");
        }
        PC.cancelarConsulta(id);
    }
    @Override
    public List<Consulta> listarConsultas(Integer idMedico, Integer idPaciente, String fechaInicio, String fechaFin)
            throws ObjetoExistenteException, IOException {
        List<Consulta> consultas = PC.listarConsultas();
        return consultas.stream()
                .filter(Objects::nonNull)
                .filter(c -> (idMedico == null || c.getMedico().getId() == idMedico))
                .filter(c -> (idPaciente == null || c.getPaciente().getId() == idPaciente))
                .filter(c -> { //filtro para fechas insano vivan las labmdas!
                    if (fechaInicio == null || fechaFin == null) {
                        return true;
                    }

                    String[] fInicio = fechaInicio.split("/");
                    int diaI = Integer.parseInt(fInicio[0]);
                    int mesI = Integer.parseInt(fInicio[1]);
                    int anioI = Integer.parseInt(fInicio[2]);
                    String[] fFinal = fechaFin.split("/");
                    int diaF = Integer.parseInt(fFinal[0]);
                    int mesF = Integer.parseInt(fFinal[1]);
                    int anioF = Integer.parseInt(fFinal[2]);
                    String[] fConsulta = c.getFecha().split("/");
                    int diaC = Integer.parseInt(fConsulta[0]);
                    int mesC = Integer.parseInt(fConsulta[1]);
                    int anioC = Integer.parseInt(fConsulta[2]);
                    
                    if (anioC < anioI || anioC > anioF) {
                        return false;
                    }

                    if (anioC == anioI) {
                        if (mesC < mesI)
                            return false;
                        if (mesC == mesI && diaC < diaI)
                            return false;
                    }

                    if (anioC == anioF) {
                        if (mesC > mesF)
                            return false;
                        if (mesC == mesF && diaC > diaF)
                            return false;
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }
    @Override
    public Consulta obtenerConsultaPorId(int id) throws ObjetoExistenteException, IOException{
        Consulta consulta = PC.consultarConsultasId(id);
        if(consulta == null){
            throw new ObjetoExistenteException("consulta inexistente");
        }
        return consulta;
    }
}
