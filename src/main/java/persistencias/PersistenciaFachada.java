
package persistencias;

import entidades.Consulta;
import entidades.EquipoMedico;
import entidades.Especialidad;
import entidades.Medico;
import entidades.Paciente;
import excepciones.*;
import interfaz.IPersistenciaFachada;
import java.io.IOException;
import java.util.List;
import persistencias.PersistenciaPacientes;

/**
 *
 * @author angel
 */
public class PersistenciaFachada implements IPersistenciaFachada {
    
    //Pacientes
    
    @Override
    public void agregarPaciente(Paciente paciente) throws ObjetoExistenteException, IOException {
        PersistenciaPacientes persistencia = new PersistenciaPacientes();
        if(persistencia.consultarPacienteId(paciente.getId()) == null){
        agregarPaciente(paciente);
        }else{
            throw new ObjetoExistenteException("El id de este paciente ya está en uso");
        }
    }

    @Override
    public Paciente obtenerPacientePorId(int id) throws ObjetoInexistenteException, IOException{
        PersistenciaPacientes persistencia = new PersistenciaPacientes();
        if(persistencia.consultarPacienteId(id) == null){
            return persistencia.consultarPacienteId(id);
        }else{
            throw new ObjetoInexistenteException("Id del paciente no encontrado");
        }
    }

    @Override
    public List<Paciente> listarPacientes() {
        return listarPacientes();
    }

    @Override
    public List<Paciente> listarPacientes(String direccion) {
        return listarPacientes(direccion);
    }
    
    @Override
    public List<Paciente> listarPacientes(int edadInicial, int edadFinal) {
        return listarPacientes(edadInicial, edadFinal);
    }
    
    @Override
    public List<Paciente> listarPacientes(String direccion, int edadInicial, int edadFinal) {
        return listarPacientes(direccion, edadInicial, edadFinal);
    }
    
    
    //Medicos
    
    @Override
    public void agregarMedico(Medico medico) throws ObjetoExistenteException, IOException{
        PersistenciaMedicos persistencia = new PersistenciaMedicos();
        if(persistencia.consultarMedicoId(medico.getId()) == null){
            agregarMedico(medico);
        }else{
            throw new ObjetoExistenteException("El id de este medico ya está en uso");
        }
    }

    @Override
    public Medico obtenerMedicoPorId(int id) throws IOException, ObjetoInexistenteException {
        PersistenciaMedicos persistencia = new PersistenciaMedicos();
        if(persistencia.consultarMedicoId(id) == null){
            return persistencia.consultarMedicoId(id);
        }else{
            throw new ObjetoInexistenteException("Id del medico no encontrado");
        }
    }

    @Override
    public void agregarEspecialidad(Especialidad especialidad) throws ObjetoExistenteException {
                PersistenciaEspecialidades persistencia = new PersistenciaEspecialidades();
        if(persistencia.consultarEspecialidadId(especialidad.getId()) == null){
            agregarEspecialidad(especialidad);
        }else{
            throw new ObjetoExistenteException("El id de esta especialidad ya está en uso");
        }
    }

    @Override
    public Especialidad obtenerEspecialidadPorId(int id) throws ObjetoInexistenteException {
        
        PersistenciaEspecialidades persistencia = new PersistenciaEspecialidades();
        if(persistencia.consultarEspecialidadId(id) == null){
            return persistencia.consultarEspecialidadId(id);
        }else{
            throw new ObjetoInexistenteException("Id del paciente no encontrado");
        }
    }

    @Override
    public void agregarEquipoMedico(EquipoMedico equipo) throws ObjetoExistenteException, IOException {
        PersistenciaInventarios persistencia = new PersistenciaInventarios();
        if(persistencia.consultarEquipoMedicoId(equipo.getId()) == null){
            agregarEquipoMedico(equipo);
        }else{
            throw new ObjetoExistenteException("El id de este equipo medico ya está en uso");
        }
    }

    @Override
    public void inventariarEquipo(int id, int cantidad) throws ObjetoInexistenteException, IOException {
        PersistenciaInventarios inventarios = null;
        EquipoMedico equipoNuevo = consultarEquipoMedicoId(id);
        equipoNuevo.setCantidad(cantidad + equipoNuevo.getCantidad());
        inventarios.actualizarInventario(equipoNuevo);
    }
    
    @Override
    public void desinventariarEquipo(int id, int cantidad) throws ObjetoInexistenteException, IOException {
        PersistenciaInventarios inventarios = null;
        EquipoMedico equipoNuevo = consultarEquipoMedicoId(id);
        if(cantidad - equipoNuevo.getCantidad() >= 0){
            equipoNuevo.setCantidad(cantidad - equipoNuevo.getCantidad());
            inventarios.actualizarInventario(equipoNuevo);
        }

    }

    @Override
    public EquipoMedico consultarEquipoMedicoId(int id) throws ObjetoInexistenteException, IOException {
               PersistenciaInventarios persistencia = new PersistenciaInventarios();
        if(persistencia.consultarEquipoMedicoId(id) == null){
            return persistencia.consultarEquipoMedicoId(id);
        }else{
            throw new ObjetoInexistenteException("Id del equipo medico no encontrado");
        }
    }
    
        @Override
    public List<EquipoMedico> consultarInventario(int cantidad) {
        return consultarInventario(cantidad);
    }
    
        @Override
    public List<EquipoMedico> consultarInventario() {
        return consultarInventario();
    }

    @Override
    public void programarConsulta(Consulta consulta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Consulta> listarConsultas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
