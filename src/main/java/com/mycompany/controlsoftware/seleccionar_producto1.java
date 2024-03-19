/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.controlsoftware;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Usuario
 */
public class seleccionar_producto1 extends javax.swing.JInternalFrame {

    /**
     * Creates new form Productos
     */
    BaseDeDatos bd = new BaseDeDatos();

    public seleccionar_producto1() {
        super("Usuario: " + new BaseDeDatos().valor("select user from usuario where cod_user=" + ControlSoftware.us) + ", Sucursal: " + new BaseDeDatos().valor("select company from company where id_company=" + ControlSoftware.cia));

        UIManager.put("TableHeaderUI", "javax.swing.plaf.basic.BasicTableHeaderUI");
        initComponents();
         
        Productos.setDefaultRenderer(Object.class, new MiRenderer3());
        bd.LlenarTabla("select * from producto where cia=" + ControlSoftware.cia, Productos);

//        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh72.png")));
//        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/show72.png")));
//        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back72.png")));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        codigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        descripcion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        existencia = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        Productos = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("CÓDIGO");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("DESCRIPCIÓN");

        existencia.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        existencia.setForeground(new java.awt.Color(0, 0, 0));
        existencia.setText("SOLO EXISTENCIAS");

        jScrollPane1.setAutoscrolls(true);

        Productos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        Productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        Productos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        Productos.setSelectionBackground(new java.awt.Color(0, 0, 0));
        Productos.setSelectionForeground(new java.awt.Color(255, 255, 255));
        Productos.getTableHeader().setResizingAllowed(false);
        Productos.getTableHeader().setReorderingAllowed(false);
        Productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProductosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ProductosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(Productos);

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("SELECCIONAR PRODUCTO-FACTURA");

        jPanel6.setBackground(new java.awt.Color(195, 180, 225));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.setForeground(new java.awt.Color(0, 0, 0));
        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.LINE_AXIS));

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 0, 0));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back24.png"))); // NOI18N
        jButton6.setText("REGRESAR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton6);

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 0, 0));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/see24.png"))); // NOI18N
        jButton5.setText("VER");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton5);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh24.png"))); // NOI18N
        jButton1.setText("REFRESCAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(descripcion)
                                .addGap(25, 25, 25)
                                .addComponent(existencia))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(8, 8, 8))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(existencia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.setForeground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("CONTROLSOFTWARE - COPYRIGHT 2023 - ING. JUNIOR MORALES");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel19)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
           if (!codigo.getText().equals("") && descripcion.getText().equals("") && !existencia.isSelected()) {
            bd.LlenarTabla("select * from producto where cia=" + ControlSoftware.cia + " and codigo like '%" + codigo.getText() + "%'", Productos);
        } else if (codigo.getText().equals("") && !descripcion.getText().equals("")&& !existencia.isSelected()) {
            bd.LlenarTabla("select * from producto where cia=" + ControlSoftware.cia + " and descripcion like '%" + descripcion.getText() + "%'", Productos);
        } else if (codigo.getText().equals("") && descripcion.getText().equals("")&& !existencia.isSelected()) {
            bd.LlenarTabla("select * from producto where cia=" + ControlSoftware.cia, Productos);
        }else if (!codigo.getText().equals("") && descripcion.getText().equals("") && existencia.isSelected()) {
            bd.LlenarTabla("select * from producto where cia=" + ControlSoftware.cia + " and codigo like '%" + codigo.getText() + "%' and (existencia-reserva)>0", Productos);
        } else if (codigo.getText().equals("") && !descripcion.getText().equals("")&& existencia.isSelected()) {
            bd.LlenarTabla("select * from producto where cia=" + ControlSoftware.cia + " and descripcion like '%" + descripcion.getText() + "%' and (existencia-reserva)>0", Productos);
        } else if (codigo.getText().equals("") && descripcion.getText().equals("")&& existencia.isSelected()) {
            bd.LlenarTabla("select * from producto where cia=" + ControlSoftware.cia+" and (existencia-reserva)>0", Productos);
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void ProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProductosMouseClicked
        // TODO add your handling code here:
        if (Productos.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Ninguna fila seleccionada!");
            return;
        }
        int pick = Productos.getSelectedRow();
         
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            evt.consume();
            if (Integer.parseInt(bd.valor("select existencia-reserva from producto where cia=" + ControlSoftware.cia + " and idproducto=" + Productos.getValueAt(pick, 0))) == 0) {
                JOptionPane.showMessageDialog(null, "No se puede agregar este producto debido a que no hay existencia");
                return;
            }
            for (int i = 0; i < ingresarfactura.detalle.getRowCount(); i++) {
                if (ingresarfactura.detalle.getValueAt(i, 0).toString().equals(Productos.getValueAt(pick, 0).toString())) {
                    JOptionPane.showMessageDialog(null, "PRODUCTO YA AGREGADO, SELECCIONE OTRO");
                    return;
                }
            }
            ingresarfactura.idprod.setText("" + Productos.getValueAt(pick, 0));
            ingresarfactura.codprod.setText("" + Productos.getValueAt(pick, 1));
            ingresarfactura.desprod.setText("" + Productos.getValueAt(pick, 2));

            String x = "" + Productos.getValueAt(pick, 8).toString();
            String[] costoenletras1 = x.split("", 0);
            String costoenletras = "";
            for (int i = 0; i < costoenletras1.length; i++) {
                if (costoenletras1[i].equals("1")) {
                    costoenletras += "F";
                }
                if (costoenletras1[i].equals("2")) {
                    costoenletras += "R";
                }
                if (costoenletras1[i].equals("3")) {
                    costoenletras += "A";
                }
                if (costoenletras1[i].equals("4")) {
                    costoenletras += "N";
                }
                if (costoenletras1[i].equals("5")) {
                    costoenletras += "K";
                }
                if (costoenletras1[i].equals("6")) {
                    costoenletras += "L";
                }
                if (costoenletras1[i].equals("7")) {
                    costoenletras += "I";
                }
                if (costoenletras1[i].equals("8")) {
                    costoenletras += "H";
                }
                if (costoenletras1[i].equals("9")) {
                    costoenletras += "E";
                }
                if (costoenletras1[i].equals("0")) {
                    costoenletras += "C";
                }
                if (costoenletras1[i].equals(".")) {
                    costoenletras += ".";
                }
            }
            ingresarfactura.costo.setText("" + costoenletras);
            ingresarfactura.p1.setText(Productos.getValueAt(pick, 14).toString());
            ingresarfactura.p2.setText(Productos.getValueAt(pick, 16).toString());
            ingresarfactura.p3.setText(Productos.getValueAt(pick, 18).toString());
            ingresarfactura.existencia.setText("" + (Integer.parseInt(Productos.getValueAt(pick, 5).toString()) - Integer.parseInt(Productos.getValueAt(pick, 6).toString())));
            ingresarfactura.costonumeros = Productos.getValueAt(pick, 8).toString();
            ingresarfactura.pventa.setText(Productos.getValueAt(pick, 14).toString());
            dispose();
            ingresarfactura.cantidad.requestFocus();
        }
    }//GEN-LAST:event_ProductosMouseClicked

    private void ProductosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProductosMousePressed
        // TODO add your handling code here:


    }//GEN-LAST:event_ProductosMousePressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (Productos.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Ninguna fila seleccionada!");
            return;
        }
        new ficha_producto(Productos.getSelectedRow()).setVisible(true);

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(seleccionar_producto1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(seleccionar_producto1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(seleccionar_producto1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(seleccionar_producto1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new seleccionar_producto1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Productos;
    private javax.swing.JTextField codigo;
    private javax.swing.JTextField descripcion;
    private javax.swing.JCheckBox existencia;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
