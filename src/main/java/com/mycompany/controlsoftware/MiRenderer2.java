package com.mycompany.controlsoftware;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class MiRenderer2 extends DefaultTableCellRenderer {

    @Override

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
       Color azulfuerte = new Color(195, 180, 225);
       Color azulsuave=new Color(234, 224, 254);
        JTableHeader header = table.getTableHeader();
        header.setBackground(azulfuerte);
        header.setForeground(Color.black);
        header.setFont(new java.awt.Font("SansSerif", 1, 12));
        if (column == 3 || column == 4 || column == 5 || column == 6) {
            setHorizontalAlignment(SwingConstants.RIGHT);
        } else {
            setHorizontalAlignment(SwingConstants.LEFT);
        }
        if (row % 2 == 0) {
            setBackground(azulsuave);
            setForeground(Color.BLACK);
        } else {
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
        }

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }

}
