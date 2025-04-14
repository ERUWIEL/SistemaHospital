package interfazGrafica.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
import persistencias.PersistenciaFachada;

public class PMenuPacientes extends PMenu {

    private PersistenciaFachada manegador = new PersistenciaFachada();
    private String estado;
    private List<PTextField> entradas = new LinkedList<>();

    public PMenuPacientes() {
        super("OPCIONES SOBRE PACIENTES");
        JPanel pnlOpciones = super.getPanelTop();
        PButton btnAgregar = new PButton("Agregar Paciente");
        PButton btnEliminar = new PButton("Eliminar Paciente");
        PButton btnBuscar = new PButton("Buscar Paciente");
        PButton btnListar = new PButton("Listar Paciente");
        pnlOpciones.add(btnAgregar);
        pnlOpciones.add(btnEliminar);
        pnlOpciones.add(btnBuscar);
        pnlOpciones.add(btnListar);
        super.setPanelOpciones(pnlOpciones);

        // panel del formato
        JPanel pnlContenido = new JPanel();
        pnlContenido.setLayout(null);
        pnlContenido.setBackground(new Color(3, 2, 20));

        // creacion y posicionamiento del titulo temporal
        JLabel titulo = new JLabel("");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBounds(450, 10, 400, 40);
        // creacion y posicionamiento delos botones
        PButton btnCancelar = new PButton("cancelar");
        PButton btnAceptar = new PButton("aceptar");
        btnCancelar.setBounds(420, 200, 100, 30);
        btnAceptar.setBounds(680, 200, 100, 30);
        // creacion y posicionamiento del panel de salida
        JTextArea txt = new JTextArea();
        txt.setBackground(new Color(23, 2, 99));
        txt.setEditable(false);
        txt.setFont(new Font("Arial", Font.PLAIN, 12));
        txt.setForeground(Color.WHITE);
        JScrollPane pnlScroll = new JScrollPane(txt);
        pnlScroll.setBounds(330, 300, 530, 200);
        add(pnlContenido, BorderLayout.CENTER);

        // boton para cancelar la operacion en curso
        btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                estado = null;
                pnlContenido.removeAll();
                txt.setText("");
                entradas.clear();
                pnlContenido.revalidate();
                pnlContenido.repaint();
            }
        });

        // boton para egecutar la accion en curso
        btnAceptar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                // logica de evento

            }
        });
    }
}
