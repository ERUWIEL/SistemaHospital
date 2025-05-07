package interfazGrafica.utilidades;

import javax.swing.JOptionPane;

public class POptionPane extends JOptionPane {
    private String mensaje;

    public POptionPane(String mensaje) {
        super();
        this.mensaje = mensaje;
    }

    public boolean confirmacion(){
        int r = JOptionPane.showConfirmDialog(null,mensaje,"Confirmar Operacion",JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE); 
        return r == 0;
    }

    /**
     * metodo que permite mostrar un mensaje de error al usuario
     * @param causa
     */
    public void error(){
        showMessageDialog(null, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public void notificar(){
        showMessageDialog(null, mensaje, "Notificacion", JOptionPane.INFORMATION_MESSAGE);
    }

    public String obtener(){
        return showInputDialog(null,mensaje,"Datos", JOptionPane.QUESTION_MESSAGE);
    }

}
