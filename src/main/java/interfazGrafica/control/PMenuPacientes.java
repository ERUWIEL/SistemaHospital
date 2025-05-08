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

import entidades.Paciente;
import interfazGrafica.utilidades.PButton;
import interfazGrafica.utilidades.PMenu;
import interfazGrafica.utilidades.POptionPane;
import interfazGrafica.utilidades.PTable;
import interfazGrafica.utilidades.PTextField;
import persistencias.PersistenciaFachada;

public class PMenuPacientes extends PMenu {
    // botones
    private PButton btnLimpiar;
    private PButton btnAceptar;
    private PButton btnAgregar;
    private PButton btnEliminar;
    private PButton btnActualizar;
    private PButton btnConsultar;
    private PButton btnListar;

    // elementos
    private PersistenciaFachada manegador = new PersistenciaFachada();
    private String estado;
    private List<PTextField> entradas = new LinkedList<>();
    private JPanel pnlContenido;
    private JLabel titulo;
    private PTable<Paciente> tabla;

    /**
     * metodo encargado de posicionar los elementos con absolute layout
     */
    public PMenuPacientes() {
        super("OPCIONES SOBRE PACIENTES");
        JPanel pnlOpciones = super.getPanelTop();
        btnAgregar = new PButton("Agregar Paciente");
        btnEliminar = new PButton("Eliminar Paciente");
        btnActualizar = new PButton("Actualizar Paciente");
        btnConsultar = new PButton("Consultar Paciente");
        btnListar = new PButton("Listar Pacientes");
        pnlOpciones.add(btnAgregar);
        pnlOpciones.add(btnEliminar);
        pnlOpciones.add(btnActualizar);
        pnlOpciones.add(btnConsultar);
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
        this.btnLimpiar = new PButton("limpiar");
        this.btnAceptar = new PButton("aceptar");

        // inicializacion de listeners de botones
        runBtnAceptar();
        runBtnLimpiar();

        runBtnAgregar();
        runBtnEliminar();
        runBtnActualizar();
        runBtnConsultar();
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
                if (estado == "agregar") {
                    agregar();
                }
                if (estado == "eliminar") {
                    eliminar();
                }
                if (estado == "actualizar") {
                    actualizar();
                }
                if (estado == "consultar") {
                    consultar();
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

    /**
     * metodo que permite agregar un paciente graficamente
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
                titulo.setText("Agregar Nuevo Paciente");
                JPanel pnlEntradas = new JPanel(new GridLayout(5, 2, 20, 10));
                pnlEntradas.setOpaque(false);

                // contenido de label y texfield
                JLabel lblId = new JLabel("ID", JLabel.RIGHT);
                lblId.setForeground(Color.WHITE);
                PTextField txtId = new PTextField();
                txtId.setCampoNumerico();

                JLabel lblNombre = new JLabel("Nombre", JLabel.RIGHT);
                lblNombre.setForeground(Color.WHITE);
                PTextField txtNombre = new PTextField();
                txtNombre.setCampoLetra();

                JLabel lblEdad = new JLabel("Edad", JLabel.RIGHT);
                lblEdad.setForeground(Color.WHITE);
                PTextField txtEdad = new PTextField();
                txtEdad.setCampoNumerico();

                JLabel lblDireccion = new JLabel("Direccion", JLabel.RIGHT);
                lblDireccion.setForeground(Color.WHITE);
                PTextField txtDireccion = new PTextField();

                pnlEntradas.add(lblId);
                pnlEntradas.add(txtId);
                pnlEntradas.add(lblNombre);
                pnlEntradas.add(txtNombre);
                pnlEntradas.add(lblEdad);
                pnlEntradas.add(txtEdad);
                pnlEntradas.add(lblDireccion);
                pnlEntradas.add(txtDireccion);
                pnlEntradas.setBounds(30, 70, 750, 190);
                // actualizacion de posicion de botones
                btnLimpiar.setBounds(420, 240, 100, 30);
                btnAceptar.setBounds(680, 240, 100, 30);
                // lista de componentes
                entradas.add(txtId);
                entradas.add(txtNombre);
                entradas.add(txtEdad);
                entradas.add(txtDireccion);

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

    private void runBtnEliminar() {
        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // formateo del nuevo menu
                if (estado == "eliminar")
                    return;
                pnlContenido.removeAll();
                estado = "eliminar";
                entradas.clear();

                // creacion del nuevo menu
                titulo.setText("Eliminar Paciente Existente");
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

    private void runBtnActualizar() {
        btnActualizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // formateo del nuevo menu
                if (estado == "actualizar")
                    return;
                pnlContenido.removeAll();
                estado = "actualizar";
                entradas.clear();

                // creacion del nuevo menu
                titulo.setText("Actualizar Paciente Existente");
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
                titulo.setText("Consultar Paciente Existente");
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
                titulo.setText("Listar Pacientes Existentes");
                JPanel pnlEntradas = new JPanel(new GridLayout(3, 2, 20, 10));
                pnlEntradas.setOpaque(false);

                // contenido de label y texfield
                JLabel lblDireccion = new JLabel("Direccion", JLabel.RIGHT);
                lblDireccion.setForeground(Color.WHITE);
                PTextField txtDireccion = new PTextField();

                JLabel lblEdadMin = new JLabel("Edad Minima", JLabel.RIGHT);
                lblEdadMin.setForeground(Color.WHITE);
                PTextField txtEdadMin = new PTextField();
                txtEdadMin.setCampoNumerico();

                JLabel lblEdadMax = new JLabel("Edad Maxima", JLabel.RIGHT);
                lblEdadMax.setForeground(Color.WHITE);
                PTextField txtEdadMax = new PTextField();
                txtEdadMax.setCampoNumerico();

                pnlEntradas.add(lblDireccion);
                pnlEntradas.add(txtDireccion);
                pnlEntradas.add(lblEdadMin);
                pnlEntradas.add(txtEdadMin);
                pnlEntradas.add(lblEdadMax);
                pnlEntradas.add(txtEdadMax);
                pnlEntradas.setBounds(30, 70, 750, 105);

                // crear una tabla con columnas personalizadas
                List<PTable.Columna<Paciente>> columnas = Arrays.asList(
                        new PTable.Columna<>("ID", Integer.class, Paciente::getId, false),
                        new PTable.Columna<>("Nombre", String.class, Paciente::getNombre, false),
                        new PTable.Columna<>("Edad", Integer.class, Paciente::getEdad, false),
                        new PTable.Columna<>("Direccion", String.class, Paciente::getDireccion, false));
                tabla = new PTable<>(columnas);
                JScrollPane pnlScroll = new JScrollPane(tabla);
                pnlScroll.setBounds(300, 300, 580, 200);

                // actualizacion de posicion de botones
                btnLimpiar.setBounds(420, 200, 100, 30);
                btnAceptar.setBounds(680, 200, 100, 30);
                // lista de componentes
                entradas.add(txtDireccion);
                entradas.add(txtEdadMin);
                entradas.add(txtEdadMax);

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

    /**
     * metodo que permite agregar un paciente logicamente
     */
    private void agregar() {
        // desempaquetado
        Integer id = entradas.get(0).getInt();
        String nombre = entradas.get(1).getText();
        Integer edad = entradas.get(2).getInt();
        String direccion = entradas.get(3).getText();

        // ejecucion
        try {
            manegador.agregarPaciente(new Paciente(id, nombre, edad, direccion));
            new POptionPane("paciente agregado").notificar();
        } catch (Exception ex) {
            new POptionPane(ex.getMessage()).error();
        }
    }

    private void eliminar() {
        Integer id = entradas.get(0).getInt();
        // ejecucion
        try {
            Paciente p = manegador.obtenerPacientePorId(id);
            String informacion = "ID: " + p.getId() + "\nNombre: " + p.getNombre() + "\nEdad: " + p.getEdad()
            + "\nDireccion: " + p.getDireccion();
            if (new POptionPane("Desea Eliminar Permanentemente a:\n" + informacion).confirmacion()) {
                manegador.eliminarPaciente(id);
                new POptionPane("paciente eliminado").notificar();
            }
        } catch (Exception ex) {
            new POptionPane(ex.getMessage()).error();
        }
    }

    private void actualizar() {
        Integer id = entradas.get(0).getInt();
        // ejecucion
        try {
            Paciente p = manegador.obtenerPacientePorId(id);
            String informacion = "ID: " + p.getId() + "\nNombre: " + p.getNombre() + "\nEdad: " + p.getEdad()
                    + "\nDireccion: " + p.getDireccion();
            if (new POptionPane("Desea Modificar al Paciente :\n" + informacion).confirmacion()) {
                String nombre = new POptionPane("Nuevo Nombre").obtener();
                int edad = Integer.parseInt(new POptionPane("Nueva Edad").obtener());
                String direccion = new POptionPane("Nueva Direccion").obtener();
                manegador.actualizarPaciente(new Paciente(id, nombre, edad, direccion));
                new POptionPane("paciente actualizado").notificar();
            }
        } catch (Exception ex) {
            new POptionPane(ex.getMessage()).error();
        }
    }

    private void consultar() {
        Integer id = entradas.get(0).getInt();
        // ejecucion
        try {
            Paciente p = manegador.obtenerPacientePorId(id);
            String informacion = "ID: " + p.getId() + "\nNombre: " + p.getNombre() + "\nEdad: " + p.getEdad()
                    + "\nDireccion: " + p.getDireccion();
            new POptionPane(informacion).notificar();
        } catch (Exception ex) {
            new POptionPane(ex.getMessage()).error();
        }
    }

    private void listar() {
        // desempaquetado
        String direccion = entradas.get(0).getText();
        Integer edadMin = entradas.get(1).getInt();;
        Integer edadMax = entradas.get(2).getInt();;

        //operacion
        try {
            List<Paciente> lista = manegador.listarPacientes(edadMin, edadMax, direccion);
            tabla.setDatos(lista);
        } catch (Exception ex) {
            new POptionPane(ex.getMessage()).error();
        }
    }
}
