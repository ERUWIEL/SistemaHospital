package interfazGrafica;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interfazGrafica.control.PMenuConsultas;
import interfazGrafica.control.PMenuEquipo;
import interfazGrafica.control.PMenuMedicos;
import interfazGrafica.control.PMenuPacientes;
import interfazGrafica.utilidades.PButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * clase para iniciar la interfaz grafica
 * 
 * @author angel
 */
public class SistemaHospital extends JFrame {

    private JPanel pnlContenido;

    /**
     * metodo main para inicar la ventana
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new SistemaHospital().setVisible(true);
    }

    /**
     * metodo constructor de la ventana
     */
    private SistemaHospital() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1180, 770);
        setTitle("Sistema Hospital");
        setIconImage(new ImageIcon("src/main/resources/icons/icono-hospital.png").getImage());
        setLayout(new BorderLayout());
        setResizable(false);
        initComponents();
    }

    /**
     * metoco que inicializa los componentes
     */
    private void initComponents() {
        JPanel nav = new JPanel(new BorderLayout());
        nav.setBackground(new Color(6, 41, 112));

        // configuracion del nav/izquierdo
        JPanel navIzquierdo = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 14));
        navIzquierdo.setOpaque(false);
        PButton imgMenu = new PButton("src/main/resources/icons/icono-menu.png", "");
        PButton imgHospital = new PButton("src/main/resources/icons/icono-hospital-negativo.png", "");
        imgMenu.setOpaque(false);
        imgHospital.setOpaque(false);

        navIzquierdo.add(imgMenu);
        navIzquierdo.add(imgHospital);

        // configuracion del nav/derecho
        JPanel navDerecho = new JPanel(new FlowLayout(FlowLayout.RIGHT, 40, 14));
        navDerecho.setOpaque(false);
        JLabel titulo = new JLabel("Hospital San Jose", JLabel.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        PButton imgInformacion = new PButton("src/main/resources/icons/icono-info.png", "");
        imgInformacion.setOpaque(false);
        navDerecho.add(imgInformacion);
        navDerecho.add(titulo);

        // agregaciones del nav
        nav.add(navIzquierdo, BorderLayout.WEST);
        nav.add(navDerecho, BorderLayout.CENTER);
        add(nav, BorderLayout.NORTH);

        // configuracion del panel de botones
        JPanel pnlBotones = new JPanel(new GridLayout(4, 1));
        pnlBotones.setPreferredSize(new Dimension(120, getHeight()));
        // botones personalizados
        PButton btnConsultas = new PButton("src/main/resources/icons/icono-consulta.png", "Consultas");
        PButton btnPacientes = new PButton("src/main/resources/icons/icono-paciente.png", "Pacientes");
        PButton btnDoctores = new PButton("src/main/resources/icons/icono-doctor.png", "Medicos");
        PButton btnEquipo = new PButton("src/main/resources/icons/icono-equipo.png", "Equipo Medico");
        pnlBotones.add(btnConsultas);
        pnlBotones.add(btnPacientes);
        pnlBotones.add(btnDoctores);
        pnlBotones.add(btnEquipo);
        add(pnlBotones, BorderLayout.WEST);

        // congiguracion del panel central
        JPanel pnlInicio = new JPanel();
        ImageIcon icono = new ImageIcon("src/main/resources/icons/hospital-img.png");
        Image iconoFormateado = icono.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        JLabel lblImagen = new JLabel(new ImageIcon(iconoFormateado));
        pnlInicio.add(lblImagen);

        pnlContenido = pnlInicio;
        add(pnlContenido, BorderLayout.CENTER);

        // pre creacion de instancias para mejor rendimiento
        JPanel pnlMenuMedico = new PMenuMedicos();
        JPanel pnlMenuPaciente = new PMenuPacientes();
        JPanel pnlMenuConsulta = new PMenuConsultas();
        JPanel pnlMenuEquipo = new PMenuEquipo();

        // listeners del nav
        imgMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pnlBotones.setVisible(!pnlBotones.isVisible());
            }
        });
        imgHospital.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                remove(pnlContenido);
                pnlContenido = pnlInicio;
                add(pnlContenido, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        // listener del panel de botones
        btnConsultas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                remove(pnlContenido);
                pnlContenido = pnlMenuConsulta;
                add(pnlContenido, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        btnPacientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                remove(pnlContenido);
                pnlContenido = pnlMenuPaciente;
                add(pnlContenido, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        btnDoctores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                remove(pnlContenido);
                pnlContenido = pnlMenuMedico;
                add(pnlContenido, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        btnEquipo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                remove(pnlContenido);
                pnlContenido = pnlMenuEquipo;
                add(pnlContenido, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });
    }
}
