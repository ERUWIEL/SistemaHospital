
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

/**
 * clase que desarrolla las persistencias
 * @author angel
 */
public class PersistenciaFachada implements IPersistenciaFachada {
    
    //instancias de persistencias
    PersistenciaPacientes persistenciaPacientes = new PersistenciaPacientes();
    PersistenciaMedicos persistenciaMedicos = new PersistenciaMedicos();
    PersistenciaEspecialidades persistenciaEspecialidad = new PersistenciaEspecialidades();
    PersistenciaInventarios persistenciaInventarios = new PersistenciaInventarios();

    public PersistenciaFachada(){}

    @Override
    public void agregarPaciente(Paciente paciente) throws ObjetoExistenteException, IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarPaciente'");
    }
    @Override
    public boolean actualizarPaciente(Paciente paciente) throws ObjetoExistenteException, IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarPaciente'");
    }
    @Override
    public void eliminarPaciente(Paciente paciente) throws ObjetoExistenteException, IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarPaciente'");
    }
    @Override
    public Paciente obtenerPacientePorId(int id) throws ObjetoInexistenteException, IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPacientePorId'");
    }
    @Override
    public List<Paciente> listarPacientes() throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarPacientes'");
    }



    @Override
    public void agregarMedico(Medico medico) throws ObjetoExistenteException, IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarMedico'");
    }
    @Override
    public Medico obtenerMedicoPorId(int id) throws ObjetoInexistenteException, IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerMedicoPorId'");
    }



    @Override
    public void agregarEspecialidad(Especialidad especialidad) throws ObjetoExistenteException, IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarEspecialidad'");
    }
    @Override
    public void actualizarEspecialidad(Especialidad especialidad) throws ObjetoExistenteException, IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarEspecialidad'");
    }
    @Override
    public void eliminarEspecialidad(int id) throws ObjetoExistenteException, IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarEspecialidad'");
    }
    @Override
    public Especialidad obtenerEspecialidadPorId(int id) throws ObjetoInexistenteException, IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerEspecialidadPorId'");
    }
    @Override
    public List<Especialidad> listarEspecialidades() throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarEspecialidades'");
    }



    @Override
    public EquipoMedico consultarEquipoMedicoId(int id) throws IOException, ObjetoInexistenteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consultarEquipoMedicoId'");
    }
    @Override
    public void inventariarEquipo(int id, int cantidad) throws ObjetoInexistenteException, IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inventariarEquipo'");
    }
    @Override
    public void desinventariarEquipo(int id, int cantidad) throws ObjetoInexistenteException, IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'desinventariarEquipo'");
    }



    @Override
    public void programarConsulta(Consulta consulta) throws ObjetoExistenteException, IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'programarConsulta'");
    }
    @Override
    public void cancelarConsulta(int id) throws ObjetoExistenteException, IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelarConsulta'");
    }
    @Override
    public List<Consulta> listarConsultas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarConsultas'");
    }
}
