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

import entidades.Especialidad;
import entidades.Medico;
import interfazGrafica.utilidades.PButton;
import interfazGrafica.utilidades.PMenu;
import interfazGrafica.utilidades.POptionPane;
import interfazGrafica.utilidades.PTable;
import interfazGrafica.utilidades.PTextField;
import persistencias.PersistenciaFachada;

public class PMenuMedicos extends PMenu {
    private PButton btnLimpiar;
    private PButton btnAceptar;
    private PButton btnAgregar;
    private PButton btnConsultar;
    private PButton btnListar;
    private PButton btnAgregarEspecialidad;
    private PButton btnListarEspecialidad;
    private PButton btnConsultarEspecialidad;

    private PersistenciaFachada manegador = new PersistenciaFachada();
    private String estado;
    private List<PTextField> entradas = new LinkedList<>();
    private JPanel pnlContenido;
    private JLabel titulo;
    private PTable<Medico> tabla;
    private PTable<Especialidad> tablaEsp;

    public PMenuMedicos() {
        super("OPCIONES SOBRE MEDICOS");
        JPanel pnlOpciones = super.getPanelTop();
        btnAgregar = new PButton("Agregar Medico");
        btnConsultar = new PButton("Consultar Medico");
        btnListar = new PButton("Listar Medicos");
        btnAgregarEspecialidad = new PButton("Agregar Especialidad");
        btnListarEspecialidad = new PButton("Listar Especialidades");
        btnConsultarEspecialidad = new PButton("Consultar Especialidad");

        pnlOpciones.add(btnAgregar);
        pnlOpciones.add(btnConsultar);
        pnlOpciones.add(btnListar);
        pnlOpciones.add(btnAgregarEspecialidad);
        pnlOpciones.add(btnListarEspecialidad);
        pnlOpciones.add(btnConsultarEspecialidad);
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
        this.btnLimpiar = new PButton("limpiar");
        this.btnAceptar = new PButton("aceptar");

        runBtnAceptar();
        runBtnLimpiar();
        runBtnAgregar();
        runBtnConsultar();
        runBtnListar();
        runBtnAgregarEspecialidad();
        runBtnListarEspecialidad();
        runBtnConsultarEspecialidad();
    }

    /**
     * boton aceptar
     */
    private void runBtnAceptar() {
        // boton para egecutar la accion en curso
        btnAceptar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (estado == "agregar") {
                    agregar();
                }
                if (estado == "consultar") {
                    consultar();
                }
                if (estado == "listar"){
                    listar();
                }
                if (estado == "agregarEsp"){
                    agregarEspecialidad();
                }
                if (estado == "listarEsp"){
                    listarEspecialidades();
                }
                if (estado == "consultarEsp") {
                    consultarEspecialidad();
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

    //construccion grafica

    /**
     * metodo que permite agregar un medico graficamente
     */
    private void runBtnAgregar() {
        btnAgregar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // formateo del nuevo menu
                if (estado == "agregar")
                    return;
                pnlContenido.removeAll();
                estado = "agregar";
                entradas.clear();

                // creacion del nuevo menu
                titulo.setText("Agregar Nuevo Medico");
                JPanel pnlEntradas = new JPanel(new GridLayout(3, 2, 20, 10));
                pnlEntradas.setOpaque(false);
                // contenido de label y texfield
                JLabel lblId = new JLabel("ID Medico", JLabel.RIGHT);
                lblId.setForeground(Color.WHITE);
                PTextField txtId = new PTextField();
                txtId.setCampoNumerico();
                JLabel lblNombre = new JLabel("Nombre", JLabel.RIGHT);
                lblNombre.setForeground(Color.WHITE);
                PTextField txtNombre = new PTextField();
                txtNombre.setCampoLetra();
                JLabel lblIdEsp = new JLabel("ID Especialidad", JLabel.RIGHT);
                lblIdEsp.setForeground(Color.WHITE);
                PTextField txtIdEsp = new PTextField();
                txtIdEsp.setCampoNumerico();

                pnlEntradas.add(lblId);
                pnlEntradas.add(txtId);
                pnlEntradas.add(lblNombre);
                pnlEntradas.add(txtNombre);
                pnlEntradas.add(lblIdEsp);
                pnlEntradas.add(txtIdEsp);
                pnlEntradas.setBounds(30, 70, 750, 105);

                // actualizacion de posicion de botones
                btnLimpiar.setBounds(420, 240, 100, 30);
                btnAceptar.setBounds(680, 240, 100, 30);
                // lista de componentes
                entradas.add(txtId);
                entradas.add(txtNombre);
                entradas.add(txtIdEsp);

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

    private void runBtnConsultar() {
        btnConsultar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // formateo del nuevo menu
                if (estado == "consultar")
                    return;
                pnlContenido.removeAll();
                estado = "consultar";
                entradas.clear();

                // creacion del nuevo menu
                titulo.setText("Consultar Medico Existente");
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
                btnLimpiar.setBounds(420, 200, 100, 30);
                btnAceptar.setBounds(680, 200, 100, 30);
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
                titulo.setText("Listar Medicos Existentes");

                // crear una tabla con columnas personalizadas
                List<PTable.Columna<Medico>> columnas = Arrays.asList(
                        new PTable.Columna<>("ID", Integer.class, Medico::getId, false),
                        new PTable.Columna<>("Nombre", String.class, Medico::getNombre, false),
                        new PTable.Columna<>("Especialidad", String.class, m -> m.getEspecialidad().getNombre(), false));
                tabla = new PTable<>(columnas);

                JScrollPane pnlScroll = new JScrollPane(tabla);
                pnlScroll.setBounds(300, 300, 580, 200);

                // actualizacion de posicion de botones
                btnLimpiar.setBounds(420, 200, 100, 30);
                btnAceptar.setBounds(680, 200, 100, 30);

                // agregacion y actualizacion
                pnlContenido.add(titulo);
                pnlContenido.add(pnlScroll);
                pnlContenido.add(btnLimpiar);
                pnlContenido.add(btnAceptar);
                pnlContenido.revalidate();
                pnlContenido.repaint();
            }
        });
    }

    private void runBtnAgregarEspecialidad(){
        btnAgregarEspecialidad.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // formateo del nuevo menu
                if (estado == "agregarEsp")
                    return;
                pnlContenido.removeAll();
                estado = "agregarEsp";
                entradas.clear();

                // creacion del nuevo menu
                titulo.setText("Agregar Nueva Especialidad");
                JPanel pnlEntradas = new JPanel(new GridLayout(2, 2, 20, 10));
                pnlEntradas.setOpaque(false);
                // contenido de label y texfield
                JLabel lblId = new JLabel("ID Especialidad", JLabel.RIGHT);
                lblId.setForeground(Color.WHITE);
                PTextField txtId = new PTextField();
                txtId.setCampoNumerico();
                JLabel lblNombre = new JLabel("Nombre Especialidad", JLabel.RIGHT);
                lblNombre.setForeground(Color.WHITE);
                PTextField txtNombre = new PTextField();
                txtNombre.setCampoLetra();

                pnlEntradas.add(lblId);
                pnlEntradas.add(txtId);
                pnlEntradas.add(lblNombre);
                pnlEntradas.add(txtNombre);
                pnlEntradas.setBounds(30, 70, 750, 75);

                // actualizacion de posicion de botones
                btnLimpiar.setBounds(420, 240, 100, 30);
                btnAceptar.setBounds(680, 240, 100, 30);
                // lista de componentes
                entradas.add(txtId);
                entradas.add(txtNombre);
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

    private void runBtnListarEspecialidad() {
        btnListarEspecialidad.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // formateo del nuevo menu
                if (estado == "listarEsp")
                    return;
                pnlContenido.removeAll();
                estado = "listarEsp";
                entradas.clear();

                // creacion del nuevo menu
                titulo.setText("Listar Especialidades Existentes");

                // crear una tabla con columnas personalizadas
                List<PTable.Columna<Especialidad>> columnas = Arrays.asList(
                        new PTable.Columna<>("ID", Integer.class, Especialidad::getId, false),
                        new PTable.Columna<>("Nombre", String.class, Especialidad::getNombre, false));
                tablaEsp = new PTable<>(columnas);

                JScrollPane pnlScroll = new JScrollPane(tablaEsp);
                pnlScroll.setBounds(300, 300, 580, 200);

                // actualizacion de posicion de botones
                btnLimpiar.setBounds(420, 200, 100, 30);
                btnAceptar.setBounds(680, 200, 100, 30);

                // agregacion y actualizacion
                pnlContenido.add(titulo);
                pnlContenido.add(pnlScroll);
                pnlContenido.add(btnLimpiar);
                pnlContenido.add(btnAceptar);
                pnlContenido.revalidate();
                pnlContenido.repaint();
            }
        });
    }

    private void runBtnConsultarEspecialidad() {
        btnConsultarEspecialidad.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // formateo del nuevo menu
                if (estado == "consultarEsp")
                    return;
                pnlContenido.removeAll();
                estado = "consultarEsp";
                entradas.clear();

                // creacion del nuevo menu
                titulo.setText("Consultar Especialidad Existente");
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
                btnLimpiar.setBounds(420, 200, 100, 30);
                btnAceptar.setBounds(680, 200, 100, 30);
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


    //opciones logicas

    private void agregar() {
        // desempaquetado
        Integer id = entradas.get(0).getInt();
        String nombre = entradas.get(1).getText();
        Integer idEsp = entradas.get(2).getInt();
        // ejecucion
        try {
            manegador.agregarMedico(new Medico(id, nombre, manegador.obtenerEspecialidadPorId(idEsp)));
            new POptionPane("medico agregado").notificar();
        } catch (Exception ex) {
            new POptionPane(ex.getMessage()).error();
        }
    }

    private void consultar() {
        Integer id = entradas.get(0).getInt();
        // ejecucion
        try {
            Medico m = manegador.obtenerMedicoPorId(id);
            String informacion = "ID: " + m.getId() + "\nNombre: " + m.getNombre() + "\nEspecialidad: " + m.getEspecialidad().getNombre();
            new POptionPane(informacion).notificar();
        } catch (Exception ex) {
            new POptionPane(ex.getMessage()).error();
        }
    }

    private void listar() {
        //operacion
        try {
            List<Medico> lista = manegador.listarMedicos();
            tabla.setDatos(lista);
        } catch (Exception ex) {
            new POptionPane(ex.getMessage()).error();
        }
    }

    private void agregarEspecialidad() {
        // desempaquetado
        Integer id = entradas.get(0).getInt();
        String nombre = entradas.get(1).getText();
        // ejecucion
        try {
            manegador.agregarEspecialidad(new Especialidad(id, nombre));
            new POptionPane("especialidad agregada").notificar();
        } catch (Exception ex) {
            new POptionPane(ex.getMessage()).error();
        }
    }

    private void consultarEspecialidad() {
        Integer id = entradas.get(0).getInt();
        // ejecucion
        try {
            Especialidad e = manegador.obtenerEspecialidadPorId(id);
            String informacion = "ID: " + e.getId() + "\nNombre: " + e.getNombre();
            new POptionPane(informacion).notificar();
        } catch (Exception ex) {
            new POptionPane(ex.getMessage()).error();
        }
    }

    private void listarEspecialidades() {
        //operacion
        try {
            List<Especialidad> lista = manegador.listarEspecialidades();
            tablaEsp.setDatos(lista);
        } catch (Exception ex) {
            new POptionPane(ex.getMessage()).error();
        }
    }
}
