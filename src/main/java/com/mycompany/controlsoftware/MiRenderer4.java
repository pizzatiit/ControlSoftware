package com.mycompany.controlsoftware;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class MiRenderer4 extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
        Color azulfuerte=new Color(195, 180, 225);
        JTableHeader header = table.getTableHeader();
        header.setBackground(azulfuerte);
        header.setForeground(Color.white);
        header.setFont(new java.awt.Font("SansSerif", 1, 12));
        int estado = Integer.parseInt(""+table.getValueAt(row, 5));
        if (estado==0) {
            setBackground(Color.RED);
            setForeground(Color.BLACK);
        } else {
            setBackground(Color.GREEN);
            setForeground(Color.BLACK);
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
