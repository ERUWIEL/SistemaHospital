package interfazGrafica.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import entidades.Consulta;
import entidades.Medico;
import entidades.Paciente;
import interfazGrafica.utilidades.PButton;
import interfazGrafica.utilidades.PMenu;
import interfazGrafica.utilidades.POptionPane;
import interfazGrafica.utilidades.PTable;
import interfazGrafica.utilidades.PTextField;
import persistencias.PersistenciaFachada;

//import persistencias.PersistenciaFachada;

public class PMenuConsultas extends PMenu {

    // botones
    private PButton btnLimpiar;
    private PButton btnAceptar;
    private PButton btnProgramar;
    private PButton btnCancelar;
    private PButton btnListar;

    // elementos
    private PersistenciaFachada manejador = new PersistenciaFachada();
    private String estado;
    private List<PTextField> entradas = new LinkedList<>();
    private JPanel pnlContenido;
    private JLabel titulo;
    private PTable<Consulta> tabla;

    /**
     * Metodo constructor que permite ver las opciones, entradas y salidas de
     * Consultas
     */
    public PMenuConsultas() {
        super("OPCIONES SOBRE CONSULTAS MEDICAS");
        JPanel pnlOpciones = super.getPanelTop();
        btnProgramar = new PButton("Programar Consulta");
        btnCancelar = new PButton("Cancelar Consulta");
        btnListar = new PButton("Listar Consultas");
        pnlOpciones.add(btnProgramar);
        pnlOpciones.add(btnCancelar);
        pnlOpciones.add(btnListar);
        super.setPanelOpciones(pnlOpciones);

        // panel del formato
        this.pnlContenido = new JPanel();
        pnlContenido.setLayout(null);
        pnlContenido.setBackground(new Color(3, 2, 20));
        add(pnlContenido, BorderLayout.CENTER);

        // creacion y posicionamiento del titulo temporal
        titulo = new JLabel("");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBounds(450, 10, 400, 40);

        // creacion y posicionamiento delos botones
        btnLimpiar = new PButton("limpiar");
        btnAceptar = new PButton("aceptar");

        // inicializacion de listeners de botones
        runBtnAceptar();
        runBtnLimpiar();
        runBtnProgramar();
        runBtnCancelar();
        runBtnListar();
    }
 

    /**
     * Metodo encargado de empezar el boton aceptar y almacenar la logica
     */
    private void runBtnAceptar() {
        // boton para egecutar la accion en curso
        btnAceptar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (estado == "programar") {
                    programar();
                }
                if (estado == "cancelar") {
                    cancelar();
                }
                if (estado == "listar") {
                    listar();
                }
            }
        });
    }
    /**
     * Metodo encargado de empezar el boton de cancelar y borrar
     */
    private void runBtnLimpiar() {
        btnLimpiar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                entradas.forEach(panel -> {
                    panel.setText("");
                });
                pnlContenido.revalidate();
                pnlContenido.repaint();
            }
        });
    }

    private void runBtnProgramar() {
        btnProgramar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // formateo del nuevo menu
                if (estado == "programar")
                    return;
                pnlContenido.removeAll();
                estado = "programar";
                entradas.clear();
                // creacion del nuevo menu
                titulo.setText("Programar Nueva Consulta");
                JPanel pnlEntradas = new JPanel(new GridLayout(4, 2, 20, 10));
                pnlEntradas.setOpaque(false);

                JLabel lblId = new JLabel("ID", JLabel.RIGHT);
                lblId.setForeground(Color.WHITE);
                PTextField txtId = new PTextField();
                txtId.setCampoNumerico();

                // contenido de label y texfield
                JLabel lblIdPaciente = new JLabel("ID Paciente", JLabel.RIGHT);
                lblIdPaciente.setForeground(Color.WHITE);
                PTextField txtIdPaciente = new PTextField();
                txtIdPaciente.setCampoNumerico();

                JLabel lblIdMedico = new JLabel("ID Medico", JLabel.RIGHT);
                lblIdMedico.setForeground(Color.WHITE);
                PTextField txtIdMedico = new PTextField();
                txtIdMedico.setCampoNumerico();

                JLabel lblFecha = new JLabel("Fecha", JLabel.RIGHT);
                lblFecha.setForeground(Color.WHITE);
                PTextField txtFecha = new PTextField();
                txtFecha.setCampoFecha();

                pnlEntradas.add(lblId);
                pnlEntradas.add(txtId);
                pnlEntradas.add(lblIdPaciente);
                pnlEntradas.add(txtIdPaciente);
                pnlEntradas.add(lblIdMedico);
                pnlEntradas.add(txtIdMedico);
                pnlEntradas.add(lblFecha);
                pnlEntradas.add(txtFecha);
                pnlEntradas.setBounds(30, 70, 750, 150);

                // actualizacion de posicion de botones
                btnLimpiar.setBounds(420, 240, 100, 30);
                btnAceptar.setBounds(680, 240, 100, 30);
                // lista de componentes
                entradas.add(txtId);
                entradas.add(txtIdPaciente);
                entradas.add(txtIdMedico);
                entradas.add(txtFecha);

                // agregacion y actualizacion
                pnlContenido.add(titulo);
                pnlContenido.add(pnlEntradas);
                pnlContenido.add(btnLimpiar);
                pnlContenido.add(btnAceptar);
                pnlContenido.revalidate();
                pnlContenido.repaint();
            }
        });
    }

    private void runBtnListar() {
        btnListar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // formateo del nuevo menu
                if (estado == "listar")
                    return;
                pnlContenido.removeAll();
                estado = "listar";
                entradas.clear();

                // creacion del nuevo menu
                titulo.setText("Listar Consultas Agendadas");
                JPanel pnlEntradas = new JPanel(new GridLayout(4, 2, 20, 10));
                pnlEntradas.setOpaque(false);

                // contenido de label y texfield
                JLabel lblIdPaciente = new JLabel("ID Medico", JLabel.RIGHT);
                lblIdPaciente.setForeground(Color.WHITE);
                PTextField txtIdPaciente = new PTextField();
                txtIdPaciente.setCampoNumerico();

                JLabel lblIdMedico = new JLabel("ID Paciente", JLabel.RIGHT);
                lblIdMedico.setForeground(Color.WHITE);
                PTextField txtIdMedico = new PTextField();
                txtIdMedico.setCampoNumerico();

                JLabel lblFechaInicial = new JLabel("Fecha Inicial", JLabel.RIGHT);
                lblFechaInicial.setForeground(Color.WHITE);
                PTextField txtFechaInicial = new PTextField();
                txtFechaInicial.setCampoFecha();
                JLabel lblFechaFinal = new JLabel("Fecha Final", JLabel.RIGHT);
                lblFechaFinal.setForeground(Color.WHITE);
                PTextField txtFechaFinal = new PTextField();
                txtFechaFinal.setCampoFecha();
  
                pnlEntradas.add(lblIdPaciente);
                pnlEntradas.add(txtIdPaciente);
                pnlEntradas.add(lblIdMedico);
                pnlEntradas.add(txtIdMedico);
                pnlEntradas.add(lblFechaInicial);
                pnlEntradas.add(txtFechaInicial);
                pnlEntradas.add(lblFechaFinal);
                pnlEntradas.add(txtFechaFinal);
                pnlEntradas.setBounds(30, 70, 750, 150);

                // crear una tabla con columnas personalizadas

                List<PTable.Columna<Consulta>> columnas = Arrays.asList(
                        new PTable.Columna<>("ID", Integer.class, Consulta::getId, false),
                        new PTable.Columna<>("ID Paciente", Integer.class, c -> c.getPaciente().getId(), false),
                        new PTable.Columna<>("ID Medico", Integer.class, c -> c.getMedico().getId(), false),
                        new PTable.Columna<>("Fecha", String.class, Consulta::getFecha, false));
                tabla = new PTable<>(columnas);
                JScrollPane pnlScroll = new JScrollPane(tabla);
                pnlScroll.setBounds(300, 300, 580, 200);

                // actualizacion de posicion de botones
                btnLimpiar.setBounds(420, 240, 100, 30);
                btnAceptar.setBounds(680, 240, 100, 30);
                // lista de componentes
                entradas.add(txtIdPaciente);
                entradas.add(txtIdMedico);
                entradas.add(txtFechaInicial);
                entradas.add(txtFechaFinal);

                // agregacion y actualizacion
                pnlContenido.add(titulo);
                pnlContenido.add(pnlEntradas);
                pnlContenido.add(pnlScroll);
                pnlContenido.add(btnLimpiar);
                pnlContenido.add(btnAceptar);
                pnlContenido.revalidate();
                pnlContenido.repaint();
            }
        });
    }

    private void runBtnCancelar() {
        btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // formateo del nuevo menu
                if (estado == "cancelar")
                    return;
                pnlContenido.removeAll();
                estado = "cancelar";
                entradas.clear();

                // creacion del nuevo menu
                titulo.setText("Cancelar Consulta Agendada");
                JPanel pnlEntradas = new JPanel(new GridLayout(1, 2, 20, 10));
                pnlEntradas.setOpaque(false);

                // contenido de label y texfield
                JLabel lblId = new JLabel("ID", JLabel.RIGHT);
                lblId.setForeground(Color.WHITE);
                PTextField txtId = new PTextField();
                txtId.setCampoNumerico();

                pnlEntradas.add(lblId);
                pnlEntradas.add(txtId);
                pnlEntradas.setBounds(30, 70, 750, 35);
                // actualizacion de posicion de botones
                btnLimpiar.setBounds(420, 240, 100, 30);
                btnAceptar.setBounds(680, 240, 100, 30);
                // lista de componentes
                entradas.add(txtId);

                // agregacion y actualizacion
                pnlContenido.add(titulo);
                pnlContenido.add(pnlEntradas);
                pnlContenido.add(btnLimpiar);
                pnlContenido.add(btnAceptar);
                pnlContenido.revalidate();
                pnlContenido.repaint();
            }
        });
    }


    private void programar(){
        int id = entradas.get(0).getInt();
        int idPaciente = entradas.get(1).getInt();
        int idMedico = entradas.get(2).getInt();
        String fecha = entradas.get(3).getText();

        try{
            Paciente p = manejador.obtenerPacientePorId(idPaciente);
            Medico m = manejador.obtenerMedicoPorId(idMedico);
            manejador.programarConsulta(new Consulta(id,p,m,fecha));
            new POptionPane("consulta programada").notificar();
        } catch(Exception ex){
            new POptionPane(ex.getMessage()).error();
        }
    }

    private void listar() { 
        // desempaquetado
        Integer idMedico = entradas.get(0).getInt();
        Integer idPaciente = entradas.get(1).getInt();
        String fechaInicio = entradas.get(2).getText();
        String fechaFin = entradas.get(3).getText();

        //operacion
        try {
            List<Consulta> lista = manejador.listarConsultas(idMedico, idPaciente, fechaInicio, fechaFin);
            tabla.setDatos(lista);
        } catch (Exception ex) {
            new POptionPane(ex.getMessage()).error();
        }
    }

    private void cancelar(){
        Integer idConsulta = entradas.get(0).getInt();
        try{
            Consulta c = manejador.obtenerConsultaPorId(idConsulta);
            String informacion = "ID: " + c.getId() + "\nMedico: " + c.getMedico().getNombre() + "\nPaciente: " + c.getPaciente().getNombre() 
            + "\nFecha: " + c.getFecha();
            if (new POptionPane("Desea Eliminar Permanentemente a:\n" + informacion).confirmacion()) {
                manejador.cancelarConsulta(idConsulta);
                new POptionPane("paciente eliminado").notificar();
            }
        }catch(Exception ex){
            new POptionPane(ex.getMessage()).error();
        }
    }

}
