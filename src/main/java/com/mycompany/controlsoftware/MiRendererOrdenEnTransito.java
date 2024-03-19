package com.mycompany.controlsoftware;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class MiRendererOrdenEnTransito extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
        Color azulfuerte=new Color(195, 180, 225);
        JTableHeader header = table.getTableHeader();
        header.setBackground(azulfuerte);
        header.setForeground(Color.BLACK);
        header.setFont(new java.awt.Font("SansSerif", 1, 12));
        String estado = (String) table.getValueAt(row, 6);
        if (estado.equals("INGRESADA")) {
            setBackground(Color.CYAN);
            setForeground(Color.BLACK);
        } else if (estado.equals("PAGADA")) {
            setBackground(Color.GREEN);
            setForeground(Color.BLACK);
        } else if (estado.equals("ANULADA")) {
            setBackground(Color.RED);
            setForeground(Color.BLACK);
        }
        else if (estado.equals("ABONADA")) {
            setBackground(Color.ORANGE);
            setForeground(Color.BLACK);
        }
        
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
