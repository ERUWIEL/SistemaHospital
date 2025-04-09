package interfazGrafica.utilidades;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Clase que permite crear paneles pre-personalizados
 * @author erubiel diseñador de interfazes
 */
public class PMenu extends JPanel{

    private String pnlTitulo = "";
    private JPanel pnlEncabezado;
    /**
     * metodo constructor de la clase
     * @param titulo
     */
    public PMenu(String titulo){
        super(new BorderLayout());
        this.pnlTitulo = titulo;
        initComponents();
    }

    /**
     * Metodo que permite separar la estructura de la clase y el aspecto
     */
    private void initComponents(){
        setBackground(new Color(3, 2, 54));
        pnlEncabezado = new JPanel(new GridLayout(2, 1));
        pnlEncabezado.setOpaque(false);
        pnlEncabezado.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
 
        // titulo de encabezado
        JLabel titulo = new JLabel(pnlTitulo, JLabel.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        pnlEncabezado.add(titulo);
        add(pnlEncabezado, BorderLayout.NORTH);
    }

    /**
     * Metodo que nos permite obtener una instancia del JPanel que posteriormente se agregara con modificaciones
     * @return
     */
    public JPanel getPanelTop(){
        return new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
    }

    /**
     * metodo que nos permite agregar un nuevo set de botones al encabezado
     * NOTA: los listeners de los botones se definiran en sus clases propias
     * @param panelNuevo
     */
    public void setPanelOpciones(JPanel panelNuevo){
        panelNuevo.setOpaque(false);
        pnlEncabezado.add(panelNuevo);
        repaint();
    }
}
