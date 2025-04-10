package interfazGrafica.control;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import interfazGrafica.utilidades.PButton;
import interfazGrafica.utilidades.PMenu; 

public class PMenuEquipo extends PMenu{
    public PMenuEquipo() {
        super("OPCIONES SOBRE EQUIPO MEDICO");
        JPanel pnlOpciones = super.getPanelTop();
        PButton btnAgregar = new PButton("Agregar");
        PButton btnInventariar = new PButton("Inventariar");
        PButton btnDesinventariar = new PButton("Desinventariar");
        PButton btnConsultar = new PButton("Consultar");
        PButton btnInventario = new PButton("Inventario");
        pnlOpciones.add(btnAgregar);
        pnlOpciones.add(btnInventariar);
        pnlOpciones.add(btnDesinventariar);
        pnlOpciones.add(btnConsultar);
        pnlOpciones.add(btnInventario);
        super.setPanelOpciones(pnlOpciones);

        // JPanel pnlContenido = new JPanel();
        JPanel conte = new JPanel();
        conte.setBackground(new Color(0, 0, 0));
        add(conte, BorderLayout.CENTER);

    }
}
