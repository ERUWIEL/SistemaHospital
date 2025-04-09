package interfazGrafica.utilidades;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * clase de utilidad que ahorrara codigo Panel Button, es un panel que actua como boton PButton!
 */
public class PButton extends JPanel {

    /**
     * recibe la ruta del icono y opcionalmente del titulo del icono para mostrarse
     * @param rutaIcono
     * @param etiqueta
     */
    public PButton(String rutaIcono, String etiqueta) {
        // configuraciones de diseño
        super(new BorderLayout());
        setBackground(new Color(173, 216, 230));
        JLabel texto = new JLabel(etiqueta, JLabel.CENTER);
        texto.setFont(new Font("Calibri", Font.BOLD, 18));
        texto.setForeground(new Color(0, 0, 0));
        add(texto, BorderLayout.CENTER);

        // configuracion del icono
        ImageIcon icono = new ImageIcon(rutaIcono);
        Image iconoFormateado = icono.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel lblImagen = new JLabel(new ImageIcon(iconoFormateado));
        add(lblImagen,BorderLayout.NORTH);

        // metodo hover del boton
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                setBackground(new Color(23, 2, 99));
                texto.setForeground(new Color(255, 255, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(173, 216, 230));
                texto.setForeground(new Color(0, 0, 0));
            }
        });
    }

    public PButton(String etiqueta){
        super();
        setBackground(new Color(23, 2, 99));
        JLabel texto = new JLabel(etiqueta, JLabel.CENTER);
        texto.setFont(new Font("Calibri", Font.BOLD, 18));
        texto.setForeground(new Color(255, 255, 255));
        add(texto, BorderLayout.CENTER);

        // metodo hover del boton
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                setBackground(new Color(173, 216, 230));
                texto.setForeground(new Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(23, 2, 99));
                texto.setForeground(new Color(255, 255, 255));
            }
        });
    }
}
