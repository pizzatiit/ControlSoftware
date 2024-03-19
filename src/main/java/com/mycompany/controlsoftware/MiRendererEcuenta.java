package com.mycompany.controlsoftware;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class MiRendererEcuenta extends DefaultTableCellRenderer {

    @Override
     
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
        Color azulfuerte=new Color(195, 180, 225);
        JTableHeader header = table.getTableHeader();
        header.setBackground(azulfuerte);
        header.setForeground(Color.black);
        header.setFont(new java.awt.Font("SansSerif", 1, 12)); 
        if (column == 6 || column == 7 || column==8 || column==9) {
            setHorizontalAlignment(SwingConstants.RIGHT);
        } else {
            setHorizontalAlignment(SwingConstants.LEFT);
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
    }
    
}
