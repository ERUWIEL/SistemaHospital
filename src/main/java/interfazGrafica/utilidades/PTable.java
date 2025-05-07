package interfazGrafica.utilidades;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.function.Function;

public class PTable<T> extends JTable {
    private DefaultTableModel modelo;
    private List<Columna<T>> columnas;

    public PTable(List<Columna<T>> columnas) {
        // Crear array con los nombres de las columnas
        String[] nombresColumnas = columnas.stream()
                .map(Columna::getTitulo)
                .toArray(String[]::new);

        // Crear el modelo con las columnas pero sin datos
        modelo = new DefaultTableModel(nombresColumnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return columnas.get(column).isEditable();
            }
        };
        
        this.columnas = columnas;
        this.setModel(modelo);
    }

    public void setDatos(List<T> datos) {
        // Limpiar datos existentes
        modelo.setRowCount(0);

        // Agregar nuevos datos
        for (T dato : datos) {
            Object[] fila = new Object[columnas.size()];
            for (int i = 0; i < columnas.size(); i++) {
                fila[i] = columnas.get(i).getValor().apply(dato);
            }
            modelo.addRow(fila);    
        }
    }

    // Clase interna para definir columnas
    public static class Columna<T> {
        private String titulo;
        private Class<?> tipo;
        private Function<T, Object> valor;
        private boolean editable;

        public Columna(String titulo, Class<?> tipo, Function<T, Object> valor, boolean editable) {
            this.titulo = titulo;
            this.tipo = tipo;
            this.valor = valor;
            this.editable = editable;
        }

        public String getTitulo() {
            return titulo;
        }

        public Class<?> getTipo() {
            return tipo;
        }

        public Function<T, Object> getValor() {
            return valor;
        }

        public boolean isEditable() {
            return editable;
        }
    }
}

