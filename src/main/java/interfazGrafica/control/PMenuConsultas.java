package interfazGrafica.control;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import interfazGrafica.utilidades.PButton;
import interfazGrafica.utilidades.PMenu;
import interfazGrafica.utilidades.PTextField;

//import persistencias.PersistenciaFachada;

public class PMenuConsultas extends PMenu {

    //botones
    private PButton btnCancelar;
    private PButton btnAceptar;
    private PButton btnProgramar;
    private PButton btnListar;

    //elementos
    //private PersistenciaFachada manejador = new PersistenciaFachada();
    private String estado;
    private List<PTextField> entradas = new LinkedList<>();
    private JLabel titulo;
    private JScrollPane pnlScroll;
    private JPanel pnlContenido;
    private JTextArea areaTexto;

    /**
     * Metodo constructor que permite ver las opciones, entradas y salidas de Consultas
     */
    public PMenuConsultas() {
        super("OPCIONES SOBRE CONSULTAS MEDICAS");
        JPanel pnlOpciones = super.getPanelTop();
        btnProgramar = new PButton("Programar Consulta");
        btnListar = new PButton("Listar Consultas");
        pnlOpciones.add(btnProgramar);
        pnlOpciones.add(btnListar);
        super.setPanelOpciones(pnlOpciones);

        // panel del formato
        pnlContenido = new JPanel();
        pnlContenido.setLayout(null);
        pnlContenido.setBackground(new Color(3, 2, 20));

        // creacion y posicionamiento del titulo temporal
        titulo = new JLabel("");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBounds(450, 10, 400, 40);

        // creacion y posicionamiento delos botones
        btnCancelar = new PButton("cancelar");
        this.btnAceptar = new PButton("aceptar");
        btnCancelar.setBounds(420, 200, 100, 30);
        btnAceptar.setBounds(680, 200, 100, 30);

        // creacion y posicionamiento del panel de salida
        areaTexto = new JTextArea();
        areaTexto.setBackground(new Color(23, 2, 99));
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Arial", Font.PLAIN, 12));
        areaTexto.setForeground(Color.WHITE);
        pnlScroll = new JScrollPane(areaTexto);
        pnlScroll.setBounds(330,300,530,200);
        add(pnlContenido, BorderLayout.CENTER);

        //inicializacion de botones
        runBtnCancelar();
        runBtnAceptar();
        runBtnProgramar();
        runBtnListar();
    }

    /**
     * Metodo encargado de empezar el boton aceptar y almacenar la logica
     */
    private void runBtnAceptar(){
        
        //boton para egecutar la accion en curso
        btnAceptar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }            
        });
    }

    /**
     * Metodo encargado de empezar el boton de cancelar y borrar
     */
    private void runBtnCancelar(){
        //boton para cancelar la operacion en curso
        btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                estado = null;
                pnlContenido.removeAll();
                areaTexto.setText("");
                entradas.clear();
                pnlContenido.revalidate();
                pnlContenido.repaint();
            }
        });
    }

    /**
     * metodo que permite programar una consulta graficamente
     */
    private void runBtnProgramar(){
        btnProgramar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // formateo del nuevo menu
                if (estado == "programar") return;
                pnlContenido.removeAll();
                estado = "programar";
                areaTexto.setText("");
                entradas.clear();

                // creacion del nuevo menu
                titulo.setText("Programar Nueva Consulta");
                JPanel pnlEntradas = new JPanel(new GridLayout(2, 2, 20, 10));
                pnlEntradas.setOpaque(false);

                // contenido de label y texfield
                JLabel lblNombre = new JLabel("Nombre", JLabel.RIGHT);
                lblNombre.setForeground(Color.WHITE);
                PTextField txtNombre = new PTextField();

                JLabel lblDireccion = new JLabel("Direccion", JLabel.RIGHT);
                lblDireccion.setForeground(Color.WHITE);
                PTextField txtDireccion = new PTextField();

                pnlEntradas.add(lblNombre);
                pnlEntradas.add(txtNombre);
                pnlEntradas.add(lblDireccion);
                pnlEntradas.add(txtDireccion);
                pnlEntradas.setBounds(30, 70, 750, 75);
                
                //lista de componentes
                entradas.add(txtNombre);
                entradas.add(txtDireccion);

                // agregacion y actualizacion
                pnlContenido.add(titulo);
                pnlContenido.add(pnlEntradas);
                pnlContenido.add(btnCancelar);
                pnlContenido.add(btnAceptar);
                pnlContenido.add(pnlScroll);
                pnlContenido.revalidate();
                pnlContenido.repaint();
            }
        });
    }

    /**
     * Metodo que permite listar las consultas graficamente
     */
    private void runBtnListar(){
        btnListar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // formateo del nuevo menu
                if (estado == "listar") return;
                pnlContenido.removeAll();
                estado = "listar";
                areaTexto.setText("");
                entradas.clear();

                // creacion del nuevo menu
                titulo.setText("Listar Consultas");
                JPanel pnlEntradas = new JPanel(new GridLayout(2, 2, 20, 10));
                pnlEntradas.setOpaque(false);

                // contenido de label y texfield
                JLabel lblNombre = new JLabel("Nombre", JLabel.RIGHT);
                lblNombre.setForeground(Color.WHITE);
                PTextField txtNombre = new PTextField();

                JLabel lblDireccion = new JLabel("Direccion", JLabel.RIGHT);
                lblDireccion.setForeground(Color.WHITE);
                PTextField txtDireccion = new PTextField();

                pnlEntradas.add(lblNombre);
                pnlEntradas.add(txtNombre);
                pnlEntradas.add(lblDireccion);
                pnlEntradas.add(txtDireccion);
                pnlEntradas.setBounds(30, 70, 750, 75);
                
                //lista de componentes
                entradas.add(txtNombre);
                entradas.add(txtDireccion);

                // agregacion y actualizacion
                pnlContenido.add(titulo);
                pnlContenido.add(pnlEntradas);
                pnlContenido.add(btnCancelar);
                pnlContenido.add(btnAceptar);
                pnlContenido.add(pnlScroll);
                pnlContenido.revalidate();
                pnlContenido.repaint();
            }
        });
    }
}
