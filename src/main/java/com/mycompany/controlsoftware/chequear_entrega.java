/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.controlsoftware;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

/**
 *
 * @author Usuario
 */
public class chequear_entrega extends javax.swing.JInternalFrame {

    DefaultTableModel modelo;
    /**
     * Creates new form formato
     */
    BaseDeDatos bd = new BaseDeDatos();
    String fa = "";

    public chequear_entrega(String f) {
        super("Usuario: " + new BaseDeDatos().valor("select user from usuario where cod_user=" + ControlSoftware.us) + ", Sucursal: " + new BaseDeDatos().valor("select company from company where id_company=" + ControlSoftware.cia));
        UIManager.put("TableHeaderUI", "javax.swing.plaf.basic.BasicTableHeaderUI");

        initComponents();
        fa = f;
        detalle.setDefaultRenderer(Object.class, new MiRenderercpedidos());
        descliente.setText(bd.valor("select c.nombre from factura f inner join cliente c on c.idcliente=f.idcliente where f.idfactura=" + f + " and f.cia=" + ControlSoftware.cia));
        pedido.setText(bd.valor("select numerofactura from factura where idfactura=" + f + " and cia=" + ControlSoftware.cia));
        
        bd.LlenarCombo1("SELECT ID, NOMBRE FROM C_DESPACHO WHERE CIA=" + ControlSoftware.cia, jComboBox1, "id", "nombre");
        if (bd.versiesta("select * from comprobante_entrega where facturaid=" + f + " and cia=" + ControlSoftware.cia)) {
            String com = bd.valor("select id from comprobante_entrega where facturaid=" + f + " and cia=" + ControlSoftware.cia);
            bd.LlenarTabla("select d.id_producto as IDPRODUCTO, p.codigo as CODLETRAS, p.DESCRIPCION AS DESCRIPCION, D.CANTIDAD FROM detalle_comprobante_entrega where comprobanteid=" + com + " and  cia=" + ControlSoftware.cia, detalle);
            bd.seleccionarItemPorId(jComboBox1, bd.valor("select empresaenvio from comprobante_entrega where id=" + com + " and cia=" + ControlSoftware.cia));
            descliente1.setText(bd.valor("select numeroguia from comprobante_entrega where id=" + com + " and cia=" + ControlSoftware.cia));
        }

        modelo = (DefaultTableModel) detalle.getModel();
        
        modelo.addTableModelListener(e -> {
            if (e.getColumn() == 3) {
                int row = e.getFirstRow();
                Object abonoValue = modelo.getValueAt(row, 3);

                if (abonoValue != null) {
                    String abonoString = abonoValue.toString();

                    if (!abonoString.matches("\\d+(\\.\\d*)?|\\.\\d+")) {
                        JOptionPane.showMessageDialog(
                                null,
                                "La cantidad debe ser un número válido.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        SwingUtilities.invokeLater(() -> {
                            detalle.editCellAt(row, 3);
                            detalle.getEditorComponent().requestFocusInWindow();
                        });
                    } else {
                        
                        Double qt = Double.parseDouble(bd.valor("select cantidad from detalle_factura where idproducto='" + modelo.getValueAt(row, 0) + "' and idfactura='" + fa + "' and cia=" + ControlSoftware.cia));

                        if (Double.parseDouble("" + qt) < Double.parseDouble("" + abonoValue)) {
                            JOptionPane.showMessageDialog(null, "La entrega no puede ser mayor que lo facturado.");
                            SwingUtilities.invokeLater(() -> {
                                detalle.editCellAt(row, 3);
                                detalle.getEditorComponent().requestFocusInWindow();
                            });
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
        jScrollPane1 = new javax.swing.JScrollPane();
        detalle = new javax.swing.JTable();
        descliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pedido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        fecha = new com.toedter.calendar.JDateChooser();
        codigo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        descliente1 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("COMPROBANTE ENTREGA");

        detalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDPRODUCTO", "CODLETRAS", "DESCRIPCION", "CANTIDAD"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        detalle.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(detalle);

        descliente.setEditable(false);

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("N° FACTURA");

        pedido.setEditable(false);
        pedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pedidoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pedidoKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("FECHA INGRESO");

        codigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codigoKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("CÓDIGO");

        jPanel2.setBackground(new java.awt.Color(195, 180, 225));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

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
        jPanel2.add(jButton6);

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 0, 0));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BARCODE24.png"))); // NOI18N
        jButton7.setText("ETIQUETA");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7);

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save24.png"))); // NOI18N
        jButton2.setText("GUARDAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        jButton1.setBackground(new java.awt.Color(195, 180, 225));
        jButton1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("REBAJAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("CLIENTE");

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("EMPRESA");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("N° GUIA");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                        .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel6))
                                        .addGap(11, 11, 11)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(descliente1)
                                            .addComponent(jComboBox1, 0, 359, Short.MAX_VALUE)
                                            .addComponent(descliente))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(pedido)
                                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(pedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(descliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descliente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(14, 14, 14))
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
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (detalle.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
            return;
        }

        try {
            int c = Integer.parseInt("" + detalle.getValueAt(detalle.getSelectedRow(), 3));
            detalle.setValueAt((c = c - 1), detalle.getSelectedRow(), 3);
            if (detalle.getValueAt(detalle.getSelectedRow(), 3).toString().equals("0")) {
                modelo.removeRow(detalle.getSelectedRow());
                pedido.setEnabled(true);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        if (fecha.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Fecha de ingreso no puede estar vacío");
            return;
        }
        if (detalle.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "El pedido no puede irse sin productos");
            return;
        }
        Item em = (Item) jComboBox1.getSelectedItem();
        SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
        if (bd.versiesta("select * from comprobante_entrega where facturaid=" + fa + " and cia=" + ControlSoftware.cia)) {
            String com = bd.valor("select id from comprobante_entrega where facturaid=" + fa + " and cia=" + ControlSoftware.cia);
            bd.EjecutarConsultas("delete from detalle_comprobante_entrega where comprobanteid=" + com + " and cia=" + ControlSoftware.cia);
            bd.EjecutarConsultas("update comprobante_entrega set fecha=" + Date_Format.format(fecha.getDate()) + ", numeroguia=" + descliente1.getText() + " empresaenvio=" + em.getId() + " where id=" + com + " and cia=" + ControlSoftware.cia);

            for (int i = 0; i < detalle.getRowCount(); i++) {
                bd.EjecutarConsultas("INSERT INTO `detalle_comprobante_entrega`(`comprobanteid`, `productoid`, `cantidad`, `fecha`, `idusuario`, `estado`, `cia`) VALUES ('" + com + "','" + detalle.getValueAt(i, 0) + "','" + detalle.getValueAt(i, 3) + "','" + Date_Format.format(fecha.getDate()) + "','" + ControlSoftware.us + "','1','" + ControlSoftware.cia + "')");
            }

        } else {

            try {

                String cp = bd.doble("INSERT INTO `comprobante_entrega`(`facturaid`, `fecha`, `usuarioid`, `cia`, `estado`, `numeroguia`, `empresaenvio`) VALUES ('" + fa + "','" + Date_Format.format(fecha.getDate()) + "','" + ControlSoftware.us + "','" + ControlSoftware.cia + "','1','" + descliente1.getText() + "','" + em.getId() + "')", "SELECT @@IDENTITY AS 'id'");
                for (int i = 0; i < detalle.getRowCount(); i++) {
                    bd.EjecutarConsultas("INSERT INTO `detalle_comprobante_entrega`(`comprobanteid`, `productoid`, `cantidad`, `fecha`, `usuarioid`, `estado`, `cia`) VALUES ('" + cp + "','" + detalle.getValueAt(i, 0) + "','" + detalle.getValueAt(i, 3) + "','" + Date_Format.format(fecha.getDate()) + "','" + ControlSoftware.us + "','1','" + ControlSoftware.cia + "')");
                }
                mantenimiento_comprobantes_entrega l = new mantenimiento_comprobantes_entrega();
                Dimension internalFrameSize = l.getSize();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                l.setLocation((screenSize.width - internalFrameSize.width) / 2,
                        (screenSize.height - internalFrameSize.height) / 2);
                DESKSOFTWARE.escritorio.add(l);
                l.show();
                dispose();
            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        if (detalle.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Ninguna fila seleccionada!");
            return;
        }
        try {
            int x = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el numero de etiquetas que desea de este código"));
            String p = "" + detalle.getValueAt(detalle.getSelectedRow(), 0);
            String codletras = bd.valor("select codigo from producto where idproducto=" + p);
            String descripcion = bd.valor("select descripcion from producto where idproducto=" + p);
            String nombreSucursal = bd.valor("select abreviatura from company where id_company=" + ControlSoftware.cia);
            LocalDate fechaActual = LocalDate.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");
            String fecha = fechaActual.format(formato);
            String Preciocompra = bd.valor("select preciocompra from producto where idproducto=" + p);
            String[] costoenletras1 = Preciocompra.split("", 0);
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

            try {
                PDDocument document = new PDDocument();
                for (int i = 0; i < x; i++) {
                    PDPage page = new PDPage(new PDRectangle(2 * 72, 1 * 72)); // Tamaño de la etiqueta en puntos (1 pulgada = 72 puntos)
                    document.addPage(page);

                    PDPageContentStream contentStream = new PDPageContentStream(document, page);
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10); // Tamaño de la fuente

                    // Coordenadas de inicio de la etiqueta
                    float startX = (page.getMediaBox().getWidth() - 2 * 72) / 2; // Margen izquierdo
                    float startY = 60; // Margen inferior

                    // Escribir el dato variable centrado
                    float datoVariableWidth = PDType1Font.HELVETICA_BOLD.getStringWidth("" + costoenletras + "  " + nombreSucursal + "  " + fecha) / 1000 * 10; // Ancho del texto en puntos
                    float datoVariableStartX = startX + (2 * 72 - datoVariableWidth) / 2; // Alinear al centro horizontal
                    contentStream.beginText();
                    contentStream.newLineAtOffset(datoVariableStartX, startY - 4);
                    contentStream.showText("" + costoenletras + "  " + nombreSucursal + "  " + fecha);
                    contentStream.endText();

                    // Generar código de barras
                    Code128Bean barcodeBean = new Code128Bean();
                    barcodeBean.setBarHeight(3f); // Aumenta la altura del código de barras
                    barcodeBean.setModuleWidth(0.25); // Aumenta el ancho del módulo del código de barras
                    barcodeBean.setQuietZone(2);
                    barcodeBean.setFontName("Arial Black"); // Cambiar el tipo de fuente

                    BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(1700, BufferedImage.TYPE_BYTE_BINARY, false, 0);
                    barcodeBean.generateBarcode(canvasProvider, codletras);
                    canvasProvider.finish();

                    // Guardar la imagen del código de barras en un archivo temporal
                    File tempFile = File.createTempFile("barcode", ".png");
                    ImageIO.write(canvasProvider.getBufferedImage(), "png", tempFile);

                    // Convertir la imagen del código de barras a PDImageXObject de PDFBox
                    PDImageXObject pdBarcodeImage = PDImageXObject.createFromFileByExtension(tempFile, document);

                    // Dibujar el código de barras centrado
                    float barcodeStartX = startX + (2 * 72 - 1.8f * 72) / 2; // Centro horizontal de la página
                    float barcodeStartY = startY - 45; // Ajustar la posición del código de barras
                    contentStream.drawImage(pdBarcodeImage, barcodeStartX, barcodeStartY, 1.8f * 72, 0.5f * 72);

                    // Escribir el código de barras variable centrado
                    float descriptionWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(descripcion) / 1000 * 10; // Ancho del texto en puntos

                    float fontSize = 10;
                    while (descriptionWidth > 2 * 72 - 6 && fontSize > 1) {
                        fontSize -= 1;
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, fontSize);
                        descriptionWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(descripcion) / 1000 * fontSize;
                    }
                    float descriptionStartX = startX + (2 * 72 - descriptionWidth) / 2; // Alinear al centro horizontal
                    float descriptionStartY = barcodeStartY - 10; // Ajustar la posición de la descripción del producto

                    contentStream.beginText();
                    contentStream.newLineAtOffset(descriptionStartX, descriptionStartY);
                    contentStream.showText(descripcion);
                    contentStream.endText();

                    contentStream.close();
                }
                document.save("etiquetas.pdf");
                document.close();

                System.out.println("Las etiquetas se han generado correctamente.");
            } catch (IOException e) {
                System.out.println("Error al generar las etiquetas: " + e.getMessage());
            }
            File archivoPDF = new File("etiquetas.pdf");
            if (archivoPDF.exists() && archivoPDF.isFile()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(archivoPDF);
            } else {
                System.out.println("El archivo PDF no existe o no es válido.");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        mantenimiento_comprobantes_entrega l = new mantenimiento_comprobantes_entrega();
        Dimension internalFrameSize = l.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        l.setLocation((screenSize.width - internalFrameSize.width) / 2,
                (screenSize.height - internalFrameSize.height) / 2);
        DESKSOFTWARE.escritorio.add(l);
        l.show();
        dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void codigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (bd.versiesta("select * from producto where codigo='" + codigo.getText() + "' and cia=" + ControlSoftware.cia)) {
                if (bd.versiesta("select * from detalle_factura d inner join producto p on d.idproducto=p.idproducto where p.codigo='" + codigo.getText() + "' and d.idfactura='" + fa + "' and d.cia=" + ControlSoftware.cia)) {
                    String cod = bd.valor("select idproducto from producto where codigo='" + codigo.getText() + "' and cia=" + ControlSoftware.cia);
                    String des = bd.valor("select descripcion from producto where idproducto=" + cod + " and cia=" + ControlSoftware.cia);
                    String us = "" + ControlSoftware.us;
                    int u = 0;
                    int temp = -1;
                    for (int i = 0; i < detalle.getRowCount(); i++) {

                        if (detalle.getValueAt(i, 0).equals("" + cod)) {

                            u = 1;
                            temp = i;

                        }
                    }
                    if (u == 0) {

                        String arr[] = new String[4];
                        arr[0] = cod;
                        arr[1] = codigo.getText();
                        arr[2] = des;
                        arr[3] = "" + 1;

                        modelo.addRow(arr);
                        pedido.setEnabled(false);
                        codigo.setText("");

                    }
                    if (u == 1) {
                        if (Double.parseDouble(bd.valor("select cantidad from detalle_factura where idproducto='" + cod + "' and idfactura='" + fa + "' and cia=" + ControlSoftware.cia)) == Double.parseDouble("" + detalle.getValueAt(temp, 3))) {
                            JOptionPane.showMessageDialog(null, "Ya se ingresó el máximo de este producto");
                            codigo.setText("");
                            return;
                        }
                        int c = Integer.parseInt("" + detalle.getValueAt(temp, 3));
                        detalle.setValueAt((c = c + 1), temp, 3);
                        codigo.setText("");
                        pedido.setEnabled(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Codigo inexistente en esta orden de pedido");
                    codigo.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Codigo inexistente.");
                codigo.setText("");
            }

        }
    }//GEN-LAST:event_codigoKeyPressed

    private void pedidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pedidoKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        if (!(key == '0' || key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_pedidoKeyTyped

    private void pedidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pedidoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_pedidoKeyReleased

    public void impresion(String orden) {

        try {
            String ar[] = bd.Llenar("select id_producto from detalle_pedido_chequeo_bodega where id_pedido_bodega=" + orden, "id_producto");

            PDDocument document = new PDDocument();

            for (int j = 0; j < ar.length; j++) {
                String p = ar[j];
                int x = Integer.parseInt(bd.valor("select cantidad from detalle_pedido_chequeo_bodega where id_pedido_bodega=" + orden + " and id_producto=" + p + " and cia=" + ControlSoftware.cia));
                String codletras = bd.valor("select codigo from producto where idproducto=" + p + " and cia=" + ControlSoftware.cia);
                String descripcion = bd.valor("select descripcion from producto where idproducto=" + p + " and cia=" + ControlSoftware.cia);
                String nombreSucursal = bd.valor("select abreviatura from company where id_company=" + ControlSoftware.cia);
                LocalDate fechaActual = LocalDate.now();
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");
                String fecha = fechaActual.format(formato);
                String Preciocompra = bd.valor("select preciocompra from producto where idproducto=" + p);
                String[] costoenletras1 = Preciocompra.split("", 0);
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

                for (int i = 0; i < x; i++) {
                    PDPage page = new PDPage(new PDRectangle(2 * 72, 1 * 72)); // Tamaño de la etiqueta en puntos (1 pulgada = 72 puntos)
                    document.addPage(page);

                    PDPageContentStream contentStream = new PDPageContentStream(document, page);
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10); // Tamaño de la fuente

                    // Coordenadas de inicio de la etiqueta
                    float startX = (page.getMediaBox().getWidth() - 2 * 72) / 2; // Margen izquierdo
                    float startY = 60; // Margen inferior

                    // Escribir el dato variable centrado
                    float datoVariableWidth = PDType1Font.HELVETICA_BOLD.getStringWidth("" + costoenletras + "  " + nombreSucursal + "  " + fecha) / 1000 * 10; // Ancho del texto en puntos
                    float datoVariableStartX = startX + (2 * 72 - datoVariableWidth) / 2; // Alinear al centro horizontal
                    contentStream.beginText();
                    contentStream.newLineAtOffset(datoVariableStartX, startY - 4);
                    contentStream.showText("" + costoenletras + "  " + nombreSucursal + "  " + fecha);
                    contentStream.endText();

                    // Generar código de barras
                    Code128Bean barcodeBean = new Code128Bean();
                    barcodeBean.setBarHeight(3f); // Aumenta la altura del código de barras
                    barcodeBean.setModuleWidth(0.25); // Aumenta el ancho del módulo del código de barras
                    barcodeBean.setQuietZone(2);
                    barcodeBean.setFontName("Arial Black"); // Cambiar el tipo de fuente

                    BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(1700, BufferedImage.TYPE_BYTE_BINARY, false, 0);
                    barcodeBean.generateBarcode(canvasProvider, codletras);
                    canvasProvider.finish();

                    // Guardar la imagen del código de barras en un archivo temporal
                    File tempFile = File.createTempFile("barcode", ".png");
                    ImageIO.write(canvasProvider.getBufferedImage(), "png", tempFile);

                    // Convertir la imagen del código de barras a PDImageXObject de PDFBox
                    PDImageXObject pdBarcodeImage = PDImageXObject.createFromFileByExtension(tempFile, document);

                    // Dibujar el código de barras centrado
                    float barcodeStartX = startX + (2 * 72 - 1.8f * 72) / 2; // Centro horizontal de la página
                    float barcodeStartY = startY - 45; // Ajustar la posición del código de barras
                    contentStream.drawImage(pdBarcodeImage, barcodeStartX, barcodeStartY, 1.8f * 72, 0.5f * 72);

                    // Escribir el código de barras variable centrado
                    float descriptionWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(descripcion) / 1000 * 10; // Ancho del texto en puntos

                    float fontSize = 10;
                    while (descriptionWidth > 2 * 72 - 6 && fontSize > 1) {
                        fontSize -= 1;
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, fontSize);
                        descriptionWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(descripcion) / 1000 * fontSize;
                    }
                    float descriptionStartX = startX + (2 * 72 - descriptionWidth) / 2; // Alinear al centro horizontal
                    float descriptionStartY = barcodeStartY - 10; // Ajustar la posición de la descripción del producto

                    contentStream.beginText();
                    contentStream.newLineAtOffset(descriptionStartX, descriptionStartY);
                    contentStream.showText(descripcion);
                    contentStream.endText();

                    contentStream.close();
                    System.out.println(i);
                }

            }

            document.save("etiquetas.pdf");
            document.close();

            System.out.println("Las etiquetas se han generado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al generar las etiquetas: " + e.getMessage());
        }

        try {
            File archivoPDF = new File("etiquetas.pdf");
            if (archivoPDF.exists() && archivoPDF.isFile()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(archivoPDF);
            } else {
                System.out.println("El archivo PDF no existe o no es válido.");
            }
        } catch (Exception e) {
        }
    }

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
            java.util.logging.Logger.getLogger(chequear_entrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(chequear_entrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(chequear_entrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(chequear_entrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new chequear_entrega("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField codigo;
    private javax.swing.JTextField descliente;
    public static javax.swing.JTextField descliente1;
    private javax.swing.JTable detalle;
    private com.toedter.calendar.JDateChooser fecha;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField pedido;
    // End of variables declaration//GEN-END:variables
}
