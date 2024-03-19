package com.mycompany.controlsoftware;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class MiRendererReciboProveedor extends DefaultTableCellRenderer {

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
         Font boldFont = new Font("SansSerif", Font.BOLD, 12);
        header.setFont(boldFont);
        String estado = (String) table.getValueAt(row, 7);

        if (estado.equals("ACTIVO")) {
            setBackground(Color.green);
            setForeground(Color.BLACK);

        } else if (estado.equals("ANULADO")) {
            setBackground(Color.RED);
            setForeground(Color.BLACK);
        }

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }

}
