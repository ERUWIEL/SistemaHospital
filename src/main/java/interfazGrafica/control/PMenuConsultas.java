package interfazGrafica.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import interfazGrafica.utilidades.PButton;
import interfazGrafica.utilidades.PMenu;
import interfazGrafica.utilidades.PTextField;

public class PMenuConsultas extends PMenu {

    public PMenuConsultas() {
        super("OPCIONES SOBRE CONSULTAS MEDICAS");
        JPanel pnlOpciones = super.getPanelTop();
        PButton btnProgramar = new PButton("Programar Consulta");
        PButton btnListar = new PButton("Listar Consultas");
        pnlOpciones.add(btnProgramar);
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
        pnlScroll.setBounds(330,300,530,200);
        add(pnlContenido, BorderLayout.CENTER);

        // metodo que permite programar una consulta
        btnProgramar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // formateo del nuevo menu
                pnlContenido.removeAll();
                titulo.setText("Programar Nueva Consulta");
                JPanel pnlEntradas = new JPanel(new GridLayout(2, 2, 20, 10));
                pnlEntradas.setOpaque(false);
                // contenido de label y texfield
                JLabel lblText = new JLabel("Nombre", JLabel.RIGHT);
                lblText.setForeground(new Color(255, 255, 255));
                PTextField campoTexto = new PTextField();
                JLabel lblText1 = new JLabel("Direccion", JLabel.RIGHT);
                lblText1.setForeground(new Color(255, 255, 255));
                PTextField campoTexto1 = new PTextField();
                pnlEntradas.add(lblText);
                pnlEntradas.add(campoTexto);
                pnlEntradas.add(lblText1);
                pnlEntradas.add(campoTexto1);
                pnlEntradas.setBounds(30, 70, 750, 75);

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

        btnListar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // formateo del nuevo menu
                pnlContenido.removeAll();
                titulo.setText("Programar Nueva Consulta");
                JPanel pnlEntradas = new JPanel(new GridLayout(2, 2, 20, 10));
                pnlEntradas.setOpaque(false);
                // contenido de label y texfield
                JLabel lblText = new JLabel("Nombre", JLabel.RIGHT);
                lblText.setForeground(new Color(255, 255, 255));
                PTextField campoTexto = new PTextField();
                JLabel lblText1 = new JLabel("Direccion", JLabel.RIGHT);
                lblText1.setForeground(new Color(255, 255, 255));
                PTextField campoTexto1 = new PTextField();
                pnlEntradas.add(lblText);
                pnlEntradas.add(campoTexto);
                pnlEntradas.add(lblText1);
                pnlEntradas.add(campoTexto1);
                pnlEntradas.setBounds(30, 70, 750, 75);

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
