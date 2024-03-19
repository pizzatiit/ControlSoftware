/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.controlsoftware;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class recibo1 extends javax.swing.JInternalFrame {

    /**
     * Creates new form formato
     */
    int selected = -1;
    BaseDeDatos bd = new BaseDeDatos();
    static DefaultTableModel modelo;

    public recibo1(String cl, String f) {
        super("Usuario: " + new BaseDeDatos().valor("select user from usuario where cod_user=" + ControlSoftware.us) + ", Sucursal: " + new BaseDeDatos().valor("select company from company where id_company=" + ControlSoftware.cia));

        initComponents();

        modelo = (DefaultTableModel) recibos.getModel();
        recibos.setDefaultRenderer(Object.class, new MiRendererMrecibos());
        gtotal.setText("0.0");
        codcliente.setText("0");
        descliente.setText("seleccione");

        if (bd.valor("select MAX(cast(numero_recibo as unsigned))+1 from recibopago_proveedor where tipo=1 and cia=" + ControlSoftware.cia) == null) {
            nrecibo.setText("1");
        } else {
            nrecibo.setText(String.format("%.0f", (Double.parseDouble(bd.valor("select MAX(cast(numero_recibo as unsigned))+1 from recibopago_proveedor where tipo=1 and cia=" + ControlSoftware.cia)))));
        }

        if (!cl.equals("") || !f.equals("")) {
            codcliente.setText(cl);
            descliente.setText(bd.valor("select proveedor from proveedor where idproveedor=" + cl + " and cia=" + ControlSoftware.cia));
            String facts[] = bd.Llenar("select id_ordencompra from ordencompra where saldo>0 and estado!=0 and id_proveedor=" + cl + " and cia=" + ControlSoftware.cia, "id_ordencompra");
            recibo1.modelo.setRowCount(0);
            for (int i = 0; i < facts.length; i++) {
                Object[] fila = new Object[5];
                fila[0] = "" + facts[i];
                fila[1] = bd.valor("select nfactura from ordencompra where id_ordencompra=" + facts[i] + " and cia=0" + ControlSoftware.cia);
                fila[2] = bd.valor("select saldo from ordencompra where id_ordencompra=" + facts[i] + " and cia=0" + ControlSoftware.cia);
                fila[3] = "0.00";
                fila[4] = bd.valor("select saldo from ordencompra where id_ordencompra=" + facts[i] + " and cia=0" + ControlSoftware.cia);
                recibo1.modelo.addRow(fila);
            }

        }
        // jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png")));
        // jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/limpiar.png")));
        //REGRESAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png")));
        
        modelo.addTableModelListener(e -> {
            if (e.getColumn() == 3) {
                int row = e.getFirstRow();
                Object abonoValue = modelo.getValueAt(row, 3);

                if (abonoValue != null) {
                    String abonoString = abonoValue.toString();

                    if (!abonoString.matches("\\d+(\\.\\d*)?|\\.\\d+")) {
                        JOptionPane.showMessageDialog(
                                null,
                                "El valor de Abono debe ser un número válido.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        SwingUtilities.invokeLater(() -> {
                            recibos.editCellAt(row, 3);
                            recibos.getEditorComponent().requestFocusInWindow();
                        });
                    } else {
                        Object santerior = modelo.getValueAt(row, 2);
                        Object sfinal = Double.parseDouble("" + santerior) - Double.parseDouble("" + abonoValue);
                        if (Double.parseDouble("" + santerior) < Double.parseDouble("" + abonoValue)) {
                            JOptionPane.showMessageDialog(null, "El abono no puede ser mayor que el saldo.");
                            SwingUtilities.invokeLater(() -> {
                                recibos.editCellAt(row, 3);
                                recibos.getEditorComponent().requestFocusInWindow();
                            });
                        } else {
                            modelo.setValueAt(sfinal, row, 4);
                            double sumaAbono = 0.0;
                            for (int u = 0; u < modelo.getRowCount(); u++) {
                                Object abonoValue1 = modelo.getValueAt(u, 3);
                                if (abonoValue1 != null) {
                                    try {
                                        double abono = Double.parseDouble(abonoValue1.toString());
                                        sumaAbono += abono;
                                    } catch (NumberFormatException ignored) {
                                    }
                                }
                            }
                            gtotal.setText("" + sumaAbono);
                        }
                    }
                }
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        nrecibo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        recibos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        REGRESAR = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JTextArea();
        codcliente = new javax.swing.JTextField();
        fecha = new com.toedter.calendar.JDateChooser();
        clie = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        descliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        gtotal = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("INGRESAR RECIBO");

        nrecibo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nreciboKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nreciboKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("FECHA");

        recibos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID FACTURA", "NUMERO", "SALDO ANTERIOR", "ABONO", "NUEVO SALDO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        recibos.setRowHeight(30);
        recibos.getTableHeader().setReorderingAllowed(false);
        recibos.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                recibosHierarchyChanged(evt);
            }
        });
        recibos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                recibosMouseClicked(evt);
            }
        });
        recibos.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                recibosInputMethodTextChanged(evt);
            }
        });
        recibos.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                recibosPropertyChange(evt);
            }
        });
        recibos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                recibosKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                recibosKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(recibos);

        jPanel2.setBackground(new java.awt.Color(195, 180, 225));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        REGRESAR.setBackground(new java.awt.Color(255, 255, 255));
        REGRESAR.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        REGRESAR.setForeground(new java.awt.Color(0, 0, 0));
        REGRESAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back24.png"))); // NOI18N
        REGRESAR.setText("REGRESAR");
        REGRESAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REGRESARActionPerformed(evt);
            }
        });
        jPanel2.add(REGRESAR);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save24.png"))); // NOI18N
        jButton1.setText("GUARDAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        descripcion.setColumns(20);
        descripcion.setRows(5);
        descripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                descripcionKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(descripcion);

        codcliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                codclienteFocusLost(evt);
            }
        });
        codcliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                codclienteMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                codclienteMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                codclienteMouseReleased(evt);
            }
        });
        codcliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codclienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                codclienteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                codclienteKeyTyped(evt);
            }
        });

        clie.setBackground(new java.awt.Color(195, 180, 225));
        clie.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        clie.setForeground(new java.awt.Color(0, 0, 0));
        clie.setText("CLIENTE");
        clie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clieActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("DESCRIPCION");

        descliente.setEditable(false);

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("NUMERO DE RECIBO");

        gtotal.setEditable(false);
        gtotal.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        gtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("TOTAL");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(clie, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descliente))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane1)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(nrecibo, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel4)
                                            .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(61, 61, 61)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(gtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(16, 16, 16))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clie, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(codcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(descliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(6, 6, 6)
                                .addComponent(nrecibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(6, 6, 6)
                                .addComponent(fecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(gtotal)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("CONTROLSOFTWARE - COPYRIGHT 2023 - ING. JUNIOR MORALES");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void recibosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recibosMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_recibosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int tip = 0;

        if (bd.versiesta("select numero_recibo from recibopago_proveedor where numero_recibo=" + nrecibo.getText() + " and cia=" + ControlSoftware.cia + " and tipo=" + tip)) {
            JOptionPane.showMessageDialog(null, "No puede ingresar un numero de recibo ya existente, si desea, modifique el existente.");
            return;
        }

        if (nrecibo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el número de recibo");
            return;
        }
        if (fecha.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Fecha de ingreso no puede estar vacío");
            return;
        }
        if (descripcion.getText().isEmpty()) {
            descripcion.setText("vacio");

        }
        if (nrecibo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el número de recibo");
            return;
        }
        if (codcliente.getText().equals("0")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar proveedor válido");
            return;
        }

        if (gtotal.getText().equals("0.0")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar algún monto");
            return;
        }
        datosrecibo1 l = new datosrecibo1(Double.parseDouble(gtotal.getText()));
        Dimension internalFrameSize = l.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        l.setLocation((screenSize.width - internalFrameSize.width) / 2,
                (screenSize.height - internalFrameSize.height) / 2);
        DESKSOFTWARE.escritorio.add(l);
        clie.setEnabled(false);
        nrecibo.setEnabled(false);
        codcliente.setEnabled(false); 
        descripcion.setEnabled(false);
        recibos.setEnabled(false);
        REGRESAR.setEnabled(false);
        jButton1.setEnabled(false);
        fecha.setEnabled(false);
        l.show();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void REGRESARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REGRESARActionPerformed
        // TODO add your handling code here:

        mantenimiento_recibos_proveedores l = new mantenimiento_recibos_proveedores();
        Dimension internalFrameSize = l.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        l.setLocation((screenSize.width - internalFrameSize.width) / 2,
                (screenSize.height - internalFrameSize.height) / 2);
        DESKSOFTWARE.escritorio.add(l);
        l.show();

        dispose();
    }//GEN-LAST:event_REGRESARActionPerformed

    private void codclienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_codclienteFocusLost
        // TODO add your handling code here:
        if (bd.valor("select proveedor from proveedor where idproveedor=" + codcliente.getText() + " and cia=" + ControlSoftware.cia).equals("")) {
            JOptionPane.showMessageDialog(null, "No existe!");
            codcliente.setText("0");
            descliente.setText("seleccione");

        } else {
            descliente.setText("" + bd.valor("select proveedor from proveedor where idcliente=" + codcliente.getText() + " and cia=" + ControlSoftware.cia));
        }
    }//GEN-LAST:event_codclienteFocusLost

    private void codclienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_codclienteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_codclienteMouseClicked

    private void codclienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_codclienteMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_codclienteMouseExited

    private void codclienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_codclienteMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_codclienteMouseReleased

    private void codclienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codclienteKeyReleased

        if (evt.getKeyCode() == 8) {
            if (codcliente.getText().isEmpty()) {
                codcliente.setText("0");
                descliente.setText("seleccione");
            }
        }
    }//GEN-LAST:event_codclienteKeyReleased

    private void codclienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codclienteKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        if (!(key == '0' || key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_codclienteKeyTyped

    private void clieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clieActionPerformed
        // TODO add your handling code here:
        seleccionar_proveedor5 l = new seleccionar_proveedor5();
        Dimension internalFrameSize = l.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        l.setLocation((screenSize.width - internalFrameSize.width) / 2,
                (screenSize.height - internalFrameSize.height) / 2);
        DESKSOFTWARE.escritorio.add(l);
        l.show();

    }//GEN-LAST:event_clieActionPerformed

    private void recibosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recibosKeyTyped
        // TODO add your handling code here:
        System.out.println("hola");
    }//GEN-LAST:event_recibosKeyTyped

    private void nreciboKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nreciboKeyTyped
        // TODO add your handling code here:
         if (nrecibo.getText().length() > 254) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_nreciboKeyTyped

    private void recibosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recibosKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_recibosKeyPressed

    private void recibosHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_recibosHierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_recibosHierarchyChanged

    private void recibosInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_recibosInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_recibosInputMethodTextChanged

    private void recibosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_recibosPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_recibosPropertyChange

    private void codclienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codclienteKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == evt.VK_V)) {
            // Evitar la acción de pegar
            evt.consume();
        }

    }//GEN-LAST:event_codclienteKeyPressed

    private void nreciboKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nreciboKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == evt.VK_V)) {
            // Evitar la acción de pegar
            evt.consume();
        }

    }//GEN-LAST:event_nreciboKeyPressed

    private void descripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descripcionKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == evt.VK_V)) {
            // Evitar la acción de pegar
            evt.consume();
        }

    }//GEN-LAST:event_descripcionKeyPressed

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
            java.util.logging.Logger.getLogger(recibo1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(recibo1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(recibo1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(recibo1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new recibo1("", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton REGRESAR;
    public static javax.swing.JButton clie;
    public static javax.swing.JTextField codcliente;
    public static javax.swing.JTextField descliente;
    public static javax.swing.JTextArea descripcion;
    public static com.toedter.calendar.JDateChooser fecha;
    public static javax.swing.JTextField gtotal;
    public static javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTextField nrecibo;
    public static javax.swing.JTable recibos;
    // End of variables declaration//GEN-END:variables
}
