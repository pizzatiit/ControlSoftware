package com.mycompany.controlsoftware;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class MiRenderer extends DefaultTableCellRenderer {

    public enum ColoresEstado{
        INGRESADA(Color.YELLOW,Color.BLACK),
        DESPACHADA(Color.BLUE,Color.BLACK),
        ABONADA(Color.ORANGE,Color.BLACK),
        ANULADA(Color.RED,Color.BLACK);
        private final Color b;
        private final Color f;
        
        private ColoresEstado(Color back, Color front){
            this.b = back;
            this.f = front;
        }
        
        public Color getB(){
            return this.b;
        }
        
        public Color getF(){
            return this.f;
        }
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) { 
        Color azulfuerte = new Color(195, 180, 225);
        JTableHeader header = table.getTableHeader();
        header.setBackground(azulfuerte);
        header.setForeground(Color.black);
        header.setFont(new java.awt.Font("SansSerif", 1, 12));
        if (column == 8 || column == 9) {
            setHorizontalAlignment(SwingConstants.RIGHT);
        } else {
            setHorizontalAlignment(SwingConstants.LEFT);
        }

        String estado = (String) table.getValueAt(row, 10);
        
        for(ColoresEstado c:ColoresEstado.values()){
            if(c.toString().equals(estado)){
                setBackground(c.b);
                setForeground(c.f);              
                break;
            }
        }   

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
