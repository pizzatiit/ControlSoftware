/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.controlsoftware;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.util.Matrix;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code128.Code128Constants;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

/**
 *
 * @author Usuario
 */
public class ajustedeentrada extends javax.swing.JInternalFrame {

    DefaultTableModel modelo;
    BaseDeDatos bd = new BaseDeDatos();

    public static String costonumeros = "";
    public static String pedido = "0";

    public ajustedeentrada() {
        super("Usuario: " + new BaseDeDatos().valor("select user from usuario where cod_user=" + ControlSoftware.us) + ", Sucursal: " + new BaseDeDatos().valor("select company from company where id_company=" + ControlSoftware.cia));

        UIManager.put("TableHeaderUI", "javax.swing.plaf.basic.BasicTableHeaderUI");
        initComponents();

        detalle.setDefaultRenderer(Object.class, new MiRenderer2());

        jCheckBox1.setVisible(false);
        if (bd.valor("select max(cast(n_documento as unsigned))+1 as maximo from ajusteinventario where cia=" + ControlSoftware.cia + " and tipo=2") == null) {
            factura.setText("1");
        } else {
            factura.setText(String.format("%.0f", (Double.parseDouble(bd.valor("select max(cast(n_documento as unsigned))+1 as maximo from ajusteinventario where cia=" + ControlSoftware.cia + " and tipo=2")))));
        }
        modelo = (DefaultTableModel) detalle.getModel();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        factura = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        agg = new javax.swing.JButton();
        idprod = new javax.swing.JTextField();
        codprod = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cantidad = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        costo = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        desprod = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        pventa = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        p1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        p2 = new javax.swing.JTextField();
        p3 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        obs = new javax.swing.JTextArea();
        jLabel20 = new javax.swing.JLabel();
        existencia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        detalle = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        obsfact = new javax.swing.JTextArea();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        art = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        factura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                facturaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                facturaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                facturaKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("N° AJUSTE");

        jPanel2.setBackground(new java.awt.Color(195, 180, 225));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "DESGLOSE FACTURA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(0, 0, 0));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("TOTAL DEL COSTO");

        total.setEditable(false);
        total.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                totalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                totalKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(total)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 62, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(195, 180, 225));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 0, 0));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back24.png"))); // NOI18N
        jButton5.setText("REGRESAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5);

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
        jPanel3.add(jButton1);

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("AJUSTE DE ENTRADA");

        jPanel6.setBackground(new java.awt.Color(195, 180, 225));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "INGRESO DE PRODUCTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        agg.setBackground(new java.awt.Color(255, 255, 255));
        agg.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        agg.setForeground(new java.awt.Color(0, 0, 0));
        agg.setText("PRODUCTO");
        agg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aggActionPerformed(evt);
            }
        });

        idprod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                idprodMouseClicked(evt);
            }
        });
        idprod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idprodKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idprodKeyTyped(evt);
            }
        });

        codprod.setEditable(false);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("CANTIDAD");

        cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cantidadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantidadKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("COSTO LTR");

        costo.setEditable(false);

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 0, 0));
        jButton6.setText("AGREGAR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 0, 0));
        jButton7.setText("ELIMINAR");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(0, 0, 0));
        jButton8.setText("EDITAR");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        desprod.setEditable(false);
        desprod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                desprodMouseClicked(evt);
            }
        });
        desprod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                desprodKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("PRECIO VENTA");

        pventa.setEditable(false);
        pventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pventaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pventaKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("P1");

        p1.setEditable(false);

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("P2");

        p2.setEditable(false);

        p3.setEditable(false);

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("P3");

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("OBS.");

        obs.setColumns(20);
        obs.setRows(5);
        obs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                obsKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                obsKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(obs);

        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(153, 0, 0));
        jLabel20.setText("EXISTENCIA");

        existencia.setEditable(false);
        existencia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        existencia.setForeground(new java.awt.Color(153, 0, 0));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                            .addComponent(existencia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel18))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(costo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pventa, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(p2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(p3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(agg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idprod, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codprod))
                    .addComponent(desprod))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(agg, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(codprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(desprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(costo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)
                                    .addComponent(pventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15)
                                    .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel16)
                                                .addComponent(p2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel20)
                                                .addComponent(existencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel18)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(p3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel17)))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addComponent(jLabel11))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        detalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "CODLETRAS", "DESCRIPCION", "CANTIDAD", "PRECIOCOMPRA", "PRECIOVENTA", "TOTAL", "OBSERVACION"
            }
        ));
        detalle.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(detalle);

        jPanel8.setBackground(new java.awt.Color(195, 180, 225));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "OBSERVACION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        obsfact.setColumns(20);
        obsfact.setRows(5);
        obsfact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                obsfactKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                obsfactKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(obsfact);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jCheckBox1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(0, 0, 0));
        jCheckBox1.setSelected(true);
        jCheckBox1.setText("HABILITADO");
        jCheckBox1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox1StateChanged(evt);
            }
        });
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 0, 0));
        jLabel7.setText("ARTÍCULOS:");

        art.setEditable(false);
        art.setText("0");
        art.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                artKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                artKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(36, 36, 36)
                        .addComponent(factura, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(272, 272, 272)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(art, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(art, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

//        for (int i = 0; i < detalle.getRowCount(); i++) {
//
//            Double q = Double.parseDouble(bd.valor("select existencia-reserva from producto where idproducto=" + detalle.getValueAt(i, 0) + " and cia=" + ControlSoftware.cia));
//            Double qp = Double.parseDouble("" + detalle.getValueAt(i, 3));
//
//            if (q < qp) {
//                JOptionPane.showMessageDialog(null, "Favor modificar cantidad del producto:" + "" + detalle.getValueAt(i, 1) + ", la existencia cambió.");
//
//                return;
//            }
//        } 
        int l = 0;
        int m = 0;
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (detalle.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No se puede guardar un ajuste sin productos.");
            return;
        }

        if (bd.versiesta("select n_documento from ajusteinventario where n_documento=" + factura.getText() + " and cia=" + ControlSoftware.cia + " and tipo=" + 2)) {
            JOptionPane.showMessageDialog(null, "Ajuste no puede repetirse");
            return;
        }
        if (factura.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "N Ajuste no puede estar vacío");
            return;
        } else {

            int opc = 0;
            opc = JOptionPane.showConfirmDialog(null, "Desea guardar el ajuste con el siguiente numero: " + factura.getText() + "?");
            if (opc == 0) {
                String t = total.getText().replace(",", "");
                String cf = bd.doble("INSERT INTO `ajusteinventario`(`n_documento`, `tipo`, `fecha`, `totalcosto`, `cia`, `usuarioid`, `estado`, `observacion`) VALUES ('" + factura.getText() + "','2','" + LocalDateTime.now() + "','" + t + "','" + ControlSoftware.cia + "','" + ControlSoftware.us + "','1','" + obsfact.getText() + "')", "SELECT @@IDENTITY AS 'id'");

                for (int i = 0; i < detalle.getRowCount(); i++) {
                    String v = bd.valor("select precio1 from producto where idproducto=" + detalle.getValueAt(i, 0));
                    bd.EjecutarConsultas("INSERT INTO `detalle_ajusteinventario`(`ajusteid`, `idproducto`, `cantidad`, `fecha`, `costo`, `p1`, `cia`, `estado`) VALUES ('" + cf + "','" + detalle.getValueAt(i, 0) + "','" + detalle.getValueAt(i, 3) + "','" + LocalDateTime.now() + "','" + detalle.getValueAt(i, 4) + "','" + v + "','" + ControlSoftware.cia + "','1')");
                    insertartransaccion("" + cf, "7", Double.parseDouble(bd.valor("select existencia from producto where idproducto=" + detalle.getValueAt(i, 0))), Double.parseDouble("" + detalle.getValueAt(i, 3)), Double.parseDouble(bd.valor("select existencia from producto where idproducto=" + detalle.getValueAt(i, 0))) + Double.parseDouble("" + detalle.getValueAt(i, 3)), "" + detalle.getValueAt(i, 0));

                    bd.EjecutarConsultas("update producto set existencia=existencia+" + detalle.getValueAt(i, 3) + " where idproducto=" + detalle.getValueAt(i, 0));

                }
                dispose();
                mantenimiento_ajustes_de_inventario l1 = new mantenimiento_ajustes_de_inventario();
                Dimension internalFrameSize = l1.getSize();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                l1.setLocation((screenSize.width - internalFrameSize.width) / 2,
                        (screenSize.height - internalFrameSize.height) / 2);
                DESKSOFTWARE.escritorio.add(l1);
                l1.show();

                try {
                    BaseDeDatos con = new BaseDeDatos();
                    Connection conn = con.getConexion();
                    // Cargar el reporte desde el archivo .jasper
                    String path = "/ajusteentrada.jasper";
                    InputStream inputStream = getClass().getResourceAsStream(path);
                    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
                    Map<String, Object> parametros = new HashMap<>();
                    parametros.put("codigo", cf);
                    parametros.put("cia", ControlSoftware.cia);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);
                    JasperViewer viewer = new JasperViewer(jasperPrint, false);
                    viewer.setVisible(true);
                } catch (JRException ex) {
                    System.out.println("Error al generar el reporte: " + ex.getMessage());
                }

            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed
    public void insertartransaccion(String id, String tipo, Double saldoactual, Double cantidad, Double saldo_final, String producto) {
        bd.EjecutarConsultas("INSERT INTO `kardex`(`fecha`, `id_documento`, `tipo_documento`, `saldo_actual`, `cantidad`, `saldo_final`, `id_producto`, `cia`, `idusuario`, `estado`) VALUES ('" + LocalDateTime.now() + "','" + id + "','" + tipo + "','" + saldoactual + "','" + cantidad + "','" + saldo_final + "','" + producto + "','" + ControlSoftware.cia + "','" + ControlSoftware.us + "','1')");
    }
    private void facturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_facturaKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        if (!(key == '0' || key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9')) {
            evt.consume();
        }
        if (factura.getText().length() > 254) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_facturaKeyTyped

    private void facturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_facturaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_facturaKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        mantenimiento_ajustes_de_inventario l1 = new mantenimiento_ajustes_de_inventario();
        Dimension internalFrameSize = l1.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        l1.setLocation((screenSize.width - internalFrameSize.width) / 2,
                (screenSize.height - internalFrameSize.height) / 2);
        DESKSOFTWARE.escritorio.add(l1);
        l1.show();
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jCheckBox1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox1StateChanged
        // TODO add your handling code here:
        if (jCheckBox1.isSelected())
            this.setEnabled(true);
        else
            this.setEnabled(false);
    }//GEN-LAST:event_jCheckBox1StateChanged

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void aggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aggActionPerformed
        // TODO add your handling code here:
        seleccionar_producto_ajusteentrada l = new seleccionar_producto_ajusteentrada();
        Dimension internalFrameSize = l.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        l.setLocation((screenSize.width - internalFrameSize.width) / 2,
                (screenSize.height - internalFrameSize.height) / 2);
        DESKSOFTWARE.escritorio.add(l);
        l.show();
    }//GEN-LAST:event_aggActionPerformed

    private void idprodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_idprodMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_idprodMouseClicked

    private void idprodKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idprodKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_idprodKeyTyped

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:

        if (idprod.getText().isEmpty() || idprod.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un producto");
            return;
        }

        if (pventa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El precio de venta no puede ser ni nulo ni cero");
            return;
        }
        if (cantidad.getText().isEmpty() || cantidad.getText().equals("0")) {
            JOptionPane.showMessageDialog(null, "La cantidad no puede ser ni nula ni cero");
            return;
        }
        if (obs.getText().isEmpty()) {
            obs.setText("ninguna");
        }
        double existencia = Double.parseDouble(bd.valor("select existencia from producto where idproducto=" + idprod.getText())) - Double.parseDouble(bd.valor("select reserva from producto where idproducto=" + idprod.getText()));

        double qty = Double.parseDouble(cantidad.getText());

//        if (existencia < qty) {
//            JOptionPane.showMessageDialog(null, "La existencia es menor que lo que está solicitando");
//            return;
//        }
        String arr[] = new String[8];
        arr[0] = idprod.getText();
        arr[1] = codprod.getText();
        arr[2] = desprod.getText();
        arr[3] = cantidad.getText();
        arr[4] = bd.valor("select preciocompra from producto where idproducto=" + idprod.getText() + " and cia=" + ControlSoftware.cia);
        arr[5] = pventa.getText();
        arr[6] = "" + String.format("%.2f", (Double.parseDouble(cantidad.getText()) * Double.parseDouble(bd.valor("select preciocompra from producto where idproducto=" + idprod.getText()))));
        arr[7] = "" + obs.getText();

        modelo.addRow(arr);

        calcular();
        limpiar();
    }//GEN-LAST:event_jButton6ActionPerformed
    public void calcular() {
        DecimalFormat formatea = new DecimalFormat("###,###.##");

        double sum = 0.0;
        double sum2 = 0.0;
        for (int i = 0; i < detalle.getRowCount(); i++) {
            sum += Double.parseDouble("" + detalle.getValueAt(i, 6));

        }
        total.setText("" + formatea.format(sum));
        art.setText("" + detalle.getRowCount());
    }

    public void limpiar() {
        idprod.setText("");
        codprod.setText("");
        desprod.setText("");

        cantidad.setText("");
        existencia.setText("");
        costo.setText("");
        obs.setText("");
        pventa.setText("");
        p1.setText("");
        p2.setText("");
        p3.setText("");
    }
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if (detalle.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Ninguna fila seleccionada!");
            return;
        }
        try {

            idprod.setText("" + detalle.getValueAt(detalle.getSelectedRow(), 0));
            codprod.setText(bd.valor("select codigo from producto where idproducto=" + idprod.getText()));
            desprod.setText(bd.valor("select descripcion from producto where idproducto=" + idprod.getText()));
            cantidad.setText("" + detalle.getValueAt(detalle.getSelectedRow(), 3));
            costonumeros = "" + detalle.getValueAt(detalle.getSelectedRow(), 4);
            obs.setText("" + detalle.getValueAt(detalle.getSelectedRow(), 7));
            p1.setText(bd.valor("select precio1 from producto where idproducto=" + idprod.getText()));
            p2.setText(bd.valor("select precio2 from producto where idproducto=" + idprod.getText()));
            p3.setText(bd.valor("select precio3 from producto where idproducto=" + idprod.getText()));
            double et = Double.parseDouble(bd.valor("select existencia from producto where idproducto=" + idprod.getText())) - Double.parseDouble(bd.valor("select reserva from producto where idproducto=" + idprod.getText()));
            existencia.setText("" + et);
            pventa.setText("" + detalle.getValueAt(detalle.getSelectedRow(), 5));
            String u = costonumeros;
            String[] costoenletras1 = u.split("", 0);
            String costoenletras = "";

            for (int k = 0; k < costoenletras1.length; k++) {
                if (costoenletras1[k].equals("1")) {
                    costoenletras += "F";
                }
                if (costoenletras1[k].equals("2")) {
                    costoenletras += "R";
                }
                if (costoenletras1[k].equals("3")) {
                    costoenletras += "A";
                }
                if (costoenletras1[k].equals("4")) {
                    costoenletras += "N";
                }
                if (costoenletras1[k].equals("5")) {
                    costoenletras += "K";
                }
                if (costoenletras1[k].equals("6")) {
                    costoenletras += "L";
                }
                if (costoenletras1[k].equals("7")) {
                    costoenletras += "I";
                }
                if (costoenletras1[k].equals("8")) {
                    costoenletras += "H";
                }
                if (costoenletras1[k].equals("9")) {
                    costoenletras += "E";
                }
                if (costoenletras1[k].equals("0")) {
                    costoenletras += "C";
                }
                if (costoenletras1[k].equals(".")) {
                    costoenletras += ".";
                }
            }
            costo.setText(costoenletras);
            modelo.removeRow(detalle.getSelectedRow());
            calcular();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void desprodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desprodMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_desprodMouseClicked

    private void desprodKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_desprodKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_desprodKeyTyped

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        if (detalle.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Ninguna fila seleccionada!");
            return;
        }
        try {

            modelo.removeRow(detalle.getSelectedRow());
            calcular();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void cantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_cantidadKeyReleased

    private void cantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        if (!(key == '0' || key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9')) {
            evt.consume();
        }
        if (cantidad.getText().length() > 9) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_cantidadKeyTyped

    private void pventaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pventaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pventaKeyPressed

    private void pventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pventaKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        if (!(key == '0' || key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9' || key == '.') || (key == '.' && pventa.getText().contains("."))) {

            evt.consume();
        }
    }//GEN-LAST:event_pventaKeyTyped

    private void artKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_artKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_artKeyReleased

    private void artKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_artKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_artKeyTyped

    private void totalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalKeyTyped
        // TODO add your handling code here:

        int key = evt.getKeyChar();
        if (!(key == '0' || key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9' || key == '.') || (key == '.' && total.getText().contains("."))) {

            evt.consume();
        }
    }//GEN-LAST:event_totalKeyTyped

    private void totalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == 8) {

            if (total.getText().isEmpty()) {
                total.setText("0.0");

            }
            double t = Double.parseDouble(total.getText());

        } else {
            double t = Double.parseDouble(total.getText());

        }
    }//GEN-LAST:event_totalKeyReleased

    private void facturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_facturaKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == evt.VK_V)) {
            // Evitar la acción de pegar
            evt.consume();
        }

    }//GEN-LAST:event_facturaKeyPressed

    private void idprodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idprodKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == evt.VK_V)) {
            // Evitar la acción de pegar
            evt.consume();
        }
    }//GEN-LAST:event_idprodKeyPressed

    private void cantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == evt.VK_V)) {
            // Evitar la acción de pegar
            evt.consume();
        }
    }//GEN-LAST:event_cantidadKeyPressed

    private void obsfactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_obsfactKeyTyped
        // TODO add your handling code here:
         if (obsfact.getText().length() > 254) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_obsfactKeyTyped

    private void obsfactKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_obsfactKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == evt.VK_V)) {
            // Evitar la acción de pegar
            evt.consume();
        }
    }//GEN-LAST:event_obsfactKeyPressed

    private void obsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_obsKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == evt.VK_V)) {
            // Evitar la acción de pegar
            evt.consume();
        }
    }//GEN-LAST:event_obsKeyPressed

    private void obsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_obsKeyTyped
        // TODO add your handling code here:
         if (obs.getText().length() > 254) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_obsKeyTyped

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
            java.util.logging.Logger.getLogger(ajustedeentrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ajustedeentrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ajustedeentrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ajustedeentrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ajustedeentrada().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agg;
    public static javax.swing.JTextField art;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    public static javax.swing.JTextField cantidad;
    public static javax.swing.JTextField codprod;
    public static javax.swing.JTextField costo;
    public static javax.swing.JTextField desprod;
    public static javax.swing.JTable detalle;
    public static javax.swing.JTextField existencia;
    public static javax.swing.JTextField factura;
    public static javax.swing.JTextField idprod;
    private javax.swing.JButton jButton1;
    public static javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    public static javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea obs;
    public static javax.swing.JTextArea obsfact;
    public static javax.swing.JTextField p1;
    public static javax.swing.JTextField p2;
    public static javax.swing.JTextField p3;
    public static javax.swing.JTextField pventa;
    public static javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables
}
