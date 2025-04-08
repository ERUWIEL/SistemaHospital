package GUI.utilidades;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PMenuConsultas extends JPanel {

    public PMenuConsultas() {
        super(new BorderLayout());
        setBackground(new Color(3, 2, 54));

        // panel norte
        JPanel pnlEncabezado = new JPanel(new GridLayout(2, 1));
        pnlEncabezado.setOpaque(false);
        pnlEncabezado.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));

        // titulo de encabezado
        JLabel titulo = new JLabel("OPCIONES SOBRE CONSULTAS MEDICAS", JLabel.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        // botones de opciones
        JPanel pnlOpciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        pnlOpciones.setOpaque(false);
        PButton btnProgramar = new PButton("Programar Una Consulta");
        PButton btnListar = new PButton("Listar Consultas");
        pnlOpciones.add(btnProgramar);
        pnlOpciones.add(btnListar);
        
        // agregaciones
        pnlEncabezado.add(titulo);
        pnlEncabezado.add(pnlOpciones);
        add(pnlEncabezado, BorderLayout.NORTH);

  
        // JPanel pnlContenido = new JPanel();
        JPanel conte = new JPanel();
        conte.setBackground(new Color(0, 0, 0));
        add(conte, BorderLayout.CENTER);
    }
}
