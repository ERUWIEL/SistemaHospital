
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
    PersistenciaPacientes persistenciaPacientes = new PersistenciaPacientes();
    PersistenciaMedicos persistenciaMedicos = new PersistenciaMedicos();
    PersistenciaEspecialidades persistenciaEspecialidad = new PersistenciaEspecialidades();
    PersistenciaInventarios persistenciaInventarios = new PersistenciaInventarios();
    
    @Override
    public void agregarPaciente(Paciente paciente) throws ObjetoExistenteException, IOException {
        if(persistenciaPacientes.consultarPacienteId(paciente.getId()) == null){
        persistenciaPacientes.agregarPaciente(paciente);
        }else{
            throw new ObjetoExistenteException("El id de este paciente ya está en uso");
        }
    }

    @Override
    public Paciente obtenerPacientePorId(int id) throws ObjetoInexistenteException, IOException{
        if(persistenciaPacientes.consultarPacienteId(id) == null){
            return persistenciaPacientes.consultarPacienteId(id);
        }else{
            throw new ObjetoInexistenteException("Id del paciente no encontrado");
        }
    }

    @Override
    public List<Paciente> listarPacientes() throws IOException{
        return persistenciaPacientes.listarPacientes();
    }

    @Override
    public List<Paciente> listarPacientes(String direccion) throws IOException{
        return persistenciaPacientes.listarPacientes(direccion);
    }
    
    @Override
    public List<Paciente> listarPacientes(int edadInicial, int edadFinal)throws IOException {
        return persistenciaPacientes.listarPacientes(edadInicial, edadFinal);
    }
    
    @Override
    public List<Paciente> listarPacientes(String direccion, int edadInicial, int edadFinal)throws IOException {
        return persistenciaPacientes.listarPacientes(direccion, edadInicial, edadFinal);
    }
    
    
    //Medicos
    
    @Override
    public void agregarMedico(Medico medico) throws ObjetoExistenteException, IOException{
        if(persistenciaMedicos.consultarMedicoId(medico.getId()) == null){
            persistenciaMedicos.agregarMedico(medico);
        }else{
            throw new ObjetoExistenteException("El id de este medico ya está en uso");
        }
    }

    @Override
    public Medico obtenerMedicoPorId(int id) throws IOException, ObjetoInexistenteException {
        if(persistenciaMedicos.consultarMedicoId(id) == null){
            return persistenciaMedicos.consultarMedicoId(id);
        }else{
            throw new ObjetoInexistenteException("Id del medico no encontrado");
        }
    }

    @Override
    public void agregarEspecialidad(Especialidad especialidad) throws ObjetoExistenteException {
        if(persistenciaEspecialidad.consultarEspecialidadId(especialidad.getId()) == null){
            persistenciaEspecialidad.agregarEspecialidad(especialidad);
        }else{
            throw new ObjetoExistenteException("El id de esta especialidad ya está en uso");
        }
    }

    @Override
    public Especialidad obtenerEspecialidadPorId(int id) throws ObjetoInexistenteException {
        if(persistenciaEspecialidad.consultarEspecialidadId(id) == null){
            return persistenciaEspecialidad.consultarEspecialidadId(id);
        }else{
            throw new ObjetoInexistenteException("Id del paciente no encontrado");
        }
    }

    @Override
    public void agregarEquipoMedico(EquipoMedico equipo) throws ObjetoExistenteException, IOException {
        if(persistenciaInventarios.consultarEquipoMedicoId(equipo.getId()) == null){
            persistenciaInventarios.agregarEquipoMedico(equipo);
        }else{
            throw new ObjetoExistenteException("El id de este equipo medico ya está en uso");
        }
    }

    @Override
    public void inventariarEquipo(int id, int cantidad) throws ObjetoInexistenteException, IOException {
        EquipoMedico equipoNuevo = consultarEquipoMedicoId(id);
        equipoNuevo.setCantidad(cantidad + equipoNuevo.getCantidad());
        persistenciaInventarios.actualizarInventario(equipoNuevo);
    }
    
    @Override
    public void desinventariarEquipo(int id, int cantidad) throws ObjetoInexistenteException, IOException {
        EquipoMedico equipoNuevo = consultarEquipoMedicoId(id);
        if(cantidad - equipoNuevo.getCantidad() >= 0){
            equipoNuevo.setCantidad(cantidad - equipoNuevo.getCantidad());
            persistenciaInventarios.actualizarInventario(equipoNuevo);
        }

    }

    @Override
    public EquipoMedico consultarEquipoMedicoId(int id) throws ObjetoInexistenteException, IOException {
        if(persistenciaInventarios.consultarEquipoMedicoId(id) == null){
            return persistenciaInventarios.consultarEquipoMedicoId(id);
        }else{
            throw new ObjetoInexistenteException("Id del equipo medico no encontrado");
        }
    }
    
        @Override
    public List<EquipoMedico> consultarInventario(int cantidad) throws IOException {
        return persistenciaInventarios.consultarInventario(cantidad);
    }
    
        @Override
    public List<EquipoMedico> consultarInventario() throws IOException {
        return persistenciaInventarios.consultarInventario();
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
