package interfazGrafica.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import entidades.EquipoMedico;
import interfazGrafica.utilidades.PButton;
import interfazGrafica.utilidades.PMenu;
import interfazGrafica.utilidades.POptionPane;
import interfazGrafica.utilidades.PTable;
import interfazGrafica.utilidades.PTextField;
import persistencias.PersistenciaFachada; 

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PMenuEquipo extends PMenu{
    private PButton btnLimpiar;
    private PButton btnAceptar;
    private PButton btnRegistrar;
    private PButton btnInventariar;
    private PButton btnDesinventariar;
    private PButton btnConsultar;
    // elemento
    private PersistenciaFachada manegador = new PersistenciaFachada();
    private String estado;
    private List<PTextField> entradas = new LinkedList<>();
    private JPanel pnlContenido;
    private JLabel titulo;
    private PTable<EquipoMedico> tabla;

    public PMenuEquipo() {
        super("OPCIONES SOBRE EQUIPO MEDICO");
        JPanel pnlOpciones = super.getPanelTop();
        btnRegistrar = new PButton("Registrar Nuevo Equipo");
        btnInventariar = new PButton("Inventariar Equipo");
        btnDesinventariar = new PButton("Desinventariar Equipo");
        btnConsultar = new PButton("Consultar Inventario");
        pnlOpciones.add(btnRegistrar);
        pnlOpciones.add(btnInventariar);
        pnlOpciones.add(btnDesinventariar);
        pnlOpciones.add(btnConsultar);
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

        runBtnInventariar();
        runBtnDesinventariar();
        runBtnConsultar();
        runBtnRegistrar();
    }

    /**
     * Metodo encargado de empezar el boton aceptar y almacenar la logica
     */
    private void runBtnAceptar() {
        // boton para egecutar la accion en curso
        btnAceptar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (estado == "inventariar") {
                    inventariar();
                }
                if (estado == "desinventariar") {
                    desinventariar();
                }
                if (estado == "consultar") {
                    consultar();
                }
                if (estado == "registrar"){
                    registrar();
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


    private void runBtnRegistrar() {
        btnRegistrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // formateo del nuevo menu
                if (estado == "registrar")
                    return;
                pnlContenido.removeAll();
                estado = "registrar";
                entradas.clear();

                // creacion del nuevo menu
                titulo.setText("Registrar Nuevo Equipo");
                JPanel pnlEntradas = new JPanel(new GridLayout(2, 2, 20, 10));
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

    private void runBtnInventariar() {
        btnInventariar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // formateo del nuevo menu
                if (estado == "inventariar")
                    return;
                pnlContenido.removeAll();
                estado = "inventariar";
                entradas.clear();

                // creacion del nuevo menu
                titulo.setText("Inventariar Equipo Existente");
                JPanel pnlEntradas = new JPanel(new GridLayout(2, 2, 20, 10));
                pnlEntradas.setOpaque(false);

                // contenido de label y texfield
                JLabel lblId = new JLabel("ID", JLabel.RIGHT);
                lblId.setForeground(Color.WHITE);
                PTextField txtId = new PTextField();
                txtId.setCampoNumerico();

                JLabel lblCantidad = new JLabel("Cantidad", JLabel.RIGHT);
                lblCantidad.setForeground(Color.WHITE);
                PTextField txtCantidad = new PTextField();
                txtCantidad.setCampoNumerico();

                pnlEntradas.add(lblId);
                pnlEntradas.add(txtId);
                pnlEntradas.add(lblCantidad);
                pnlEntradas.add(txtCantidad);

                pnlEntradas.setBounds(30, 70, 750, 75);
                // actualizacion de posicion de botones
                btnLimpiar.setBounds(420, 240, 100, 30);
                btnAceptar.setBounds(680, 240, 100, 30);
                // lista de componentes
                entradas.add(txtId);
                entradas.add(txtCantidad);

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

    private void runBtnDesinventariar() {
        btnDesinventariar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // formateo del nuevo menu
                if (estado == "desinventariar")
                    return;
                pnlContenido.removeAll();
                estado = "desinventariar";
                entradas.clear();

                // creacion del nuevo menu
                titulo.setText("DesInventariar Equipo Existente");
                JPanel pnlEntradas = new JPanel(new GridLayout(2, 2, 20, 10));
                pnlEntradas.setOpaque(false);

                // contenido de label y texfield
                JLabel lblId = new JLabel("ID", JLabel.RIGHT);
                lblId.setForeground(Color.WHITE);
                PTextField txtId = new PTextField();
                txtId.setCampoNumerico();

                JLabel lblCantidad = new JLabel("Cantidad", JLabel.RIGHT);
                lblCantidad.setForeground(Color.WHITE);
                PTextField txtCantidad = new PTextField();
                txtCantidad.setCampoNumerico();

                pnlEntradas.add(lblId);
                pnlEntradas.add(txtId);
                pnlEntradas.add(lblCantidad);
                pnlEntradas.add(txtCantidad);

                pnlEntradas.setBounds(30, 70, 750, 75);
                // actualizacion de posicion de botones
                btnLimpiar.setBounds(420, 240, 100, 30);
                btnAceptar.setBounds(680, 240, 100, 30);
                // lista de componentes
                entradas.add(txtId);
                entradas.add(txtCantidad);

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
                titulo.setText("Consultar Inventario Equipos Medicos");
                JPanel pnlEntradas = new JPanel(new GridLayout(1, 2, 20, 10));
                pnlEntradas.setOpaque(false);

                // contenido de label y texfield
                JLabel lblId = new JLabel("ID EQUIPO", JLabel.RIGHT);
                lblId.setForeground(Color.WHITE);
                PTextField txtId = new PTextField();
                txtId.setCampoNumerico();

                pnlEntradas.add(lblId);
                pnlEntradas.add(txtId);
                pnlEntradas.setBounds(30, 70, 750, 35);

                // crear una tabla con columnas personalizadas
                List<PTable.Columna<EquipoMedico>> columnas = Arrays.asList(
                        new PTable.Columna<>("ID", Integer.class, EquipoMedico::getId, false),
                        new PTable.Columna<>("Nombre", String.class, EquipoMedico::getNombre, false),
                        new PTable.Columna<>("Cantidad", Integer.class, EquipoMedico::getCantidad, false));
                tabla = new PTable<>(columnas);
                JScrollPane pnlScroll = new JScrollPane(tabla);
                pnlScroll.setBounds(300, 300, 580, 200);

                // actualizacion de posicion de botones
                btnLimpiar.setBounds(420, 200, 100, 30);
                btnAceptar.setBounds(680, 200, 100, 30);
                // lista de componentes
                entradas.add(txtId);

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


    private void registrar() {
        // desempaquetado
        Integer id = entradas.get(0).getInt();
        String nombre = entradas.get(1).getText();

        // ejecucion
        try {
            manegador.registrarEquipo(new EquipoMedico(id, nombre, 0));
            new POptionPane("equipo agregado").notificar();
        } catch (Exception ex) {
            new POptionPane(ex.getMessage()).error();
        }
    }

    private void inventariar() {
        // desempaquetado
        Integer id = entradas.get(0).getInt();
        Integer cantidad = entradas.get(1).getInt();
        // ejecucion
        try {
            EquipoMedico em = manegador.consultarEquipoMedicoId(id);
            String informacion = "ID: " + em.getId() + "\nNombre: " + em.getNombre() + "\nCantidad: " + em.getCantidad();
            if (new POptionPane("Desea Inventariar a:\n" + informacion).confirmacion()){
                manegador.inventariarEquipo(id, cantidad);
                new POptionPane("equipo inventariado").notificar();
            }
        } catch (Exception ex) {
            new POptionPane(ex.getMessage()).error();
        }
    }

    private void consultar() { 
        //operacion
        Integer id = entradas.get(0).getInt();
        try {
            List<EquipoMedico> lista = manegador.listarEquipos(id);
            tabla.setDatos(lista);
        } catch (Exception ex) {
            new POptionPane(ex.getMessage()).error();
        }
    }

    private void desinventariar() {
        // desempaquetado
        Integer id = entradas.get(0).getInt();
        Integer cantidad = entradas.get(1).getInt();
        // ejecucion
        try {
            EquipoMedico em = manegador.consultarEquipoMedicoId(id);
            String informacion = "ID: " + em.getId() + "\nNombre: " + em.getNombre() + "\nCantidad: " + em.getCantidad();
            if (new POptionPane("Desea Desinventariar a:\n" + informacion).confirmacion()){
                manegador.desinventariarEquipo(id, cantidad);
                new POptionPane("equipo inventariado").notificar();
            }
        } catch (Exception ex) {
            new POptionPane(ex.getMessage()).error();
        }
    }
}
