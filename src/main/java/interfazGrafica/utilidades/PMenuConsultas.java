package interfazGrafica.utilidades;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class PMenuConsultas extends PMenu {

    public PMenuConsultas() {
        super("OPCIONES SOBRE CONSULTAS MEDICAS");
        JPanel pnlOpciones = super.getPanelTop();
        PButton btnProgramar = new PButton("Programar");
        PButton btnListar = new PButton("Listar");
        pnlOpciones.add(btnProgramar);
        pnlOpciones.add(btnListar);
        super.setPanelOpciones(pnlOpciones);

        // JPanel pnlContenido = new JPanel();
        JPanel conte = new JPanel();
        conte.setBackground(new Color(0, 0, 0));
        add(conte, BorderLayout.CENTER);
    }
}
