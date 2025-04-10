package interfazGrafica.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import interfazGrafica.utilidades.PButton;
import interfazGrafica.utilidades.PMenu;
import interfazGrafica.utilidades.PTextField;

public class PMenuConsultas extends PMenu {

    public PMenuConsultas() {
        super("OPCIONES SOBRE CONSULTAS MEDICAS");
        JPanel pnlOpciones = super.getPanelTop();
        PButton btnProgramar = new PButton("Programar");
        PButton btnListar = new PButton("Listar");
        pnlOpciones.add(btnProgramar);
        pnlOpciones.add(btnListar);
        super.setPanelOpciones(pnlOpciones);

        JPanel pnlContenido = new JPanel();
        pnlContenido.add(new JLabel("TEXTO 1"));
        pnlContenido.setBackground(new Color(0, 0, 0));
        add(pnlContenido, BorderLayout.CENTER);

        btnProgramar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pnlContenido.removeAll();
                JPanel pnlEntradas = new JPanel(new GridLayout(2, 2, 20,10));
                pnlEntradas.setBackground(new Color(3, 2, 54));
                pnlEntradas.setPreferredSize(new Dimension(900, 500));

                //contenido
                JLabel lblText = new JLabel("Nombre", JLabel.RIGHT);
                lblText.setForeground(new Color(255,255,255));
                PTextField campoTexto = new PTextField();
                JLabel lblText1 = new JLabel("Direccion", JLabel.RIGHT);
                lblText1.setForeground(new Color(255,255,255));
                PTextField campoTexto1 = new PTextField();
                pnlEntradas.add(lblText);
                pnlEntradas.add(campoTexto);
                pnlEntradas.add(lblText1);
                pnlEntradas.add(campoTexto1);

                //agregacion y actualizacion
                pnlContenido.add(pnlEntradas);
                pnlContenido.revalidate();
                pnlContenido.repaint();
            }
        });

        btnListar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {                
                pnlContenido.removeAll();
                JPanel pnlEntradas = new JPanel(new GridLayout(2, 2, 20,10));
                pnlEntradas.setBackground(new Color(3, 2, 54));
                pnlEntradas.setPreferredSize(new Dimension(900, 500));

                //contenido
                JLabel lblText = new JLabel("Nombre", JLabel.RIGHT);
                lblText.setForeground(new Color(255,255,255));
                PTextField campoTexto = new PTextField();
                JLabel lblText1 = new JLabel("Id", JLabel.RIGHT);
                lblText1.setForeground(new Color(255,255,255));
                PTextField campoTexto1 = new PTextField();
                campoTexto1.setCampoNumerico();
                pnlEntradas.add(lblText);
                pnlEntradas.add(campoTexto);
                pnlEntradas.add(lblText1);
                pnlEntradas.add(campoTexto1);

                //agregacion y actualizacion
                pnlContenido.add(pnlEntradas);
                pnlContenido.revalidate();
                pnlContenido.repaint();
            }
        });
    }
}
