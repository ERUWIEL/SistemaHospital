package interfazGrafica.utilidades;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel; 

public class PMenuMedicos extends PMenu {

    public PMenuMedicos() {
        super("OPCIONES SOBRE MEDICOS");
        JPanel pnlOpciones = super.getPanelTop();
        PButton btnAgregar = new PButton("Agregar");
        PButton btnBuscar = new PButton("Buscar");
        PButton btnListar = new PButton("Listar");
        pnlOpciones.add(btnAgregar);
        pnlOpciones.add(btnBuscar);
        pnlOpciones.add(btnListar);
        super.setPanelOpciones(pnlOpciones);

        // JPanel pnlContenido = new JPanel();
        JPanel conte = new JPanel();
        conte.setBackground(new Color(0, 0, 0));
        add(conte, BorderLayout.CENTER);
    }
}
