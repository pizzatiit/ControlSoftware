/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.controlsoftware;

import java.awt.Dimension;
import java.awt.Toolkit;
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
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Usuario
 */
public class ingresar_devolucion extends javax.swing.JInternalFrame {

    /**
     * Creates new form formato
     */
    BaseDeDatos bd = new BaseDeDatos();
    public static DefaultTableModel modelo;

    public ingresar_devolucion() {
        super("Usuario: " + new BaseDeDatos().valor("select user from usuario where cod_user=" + ControlSoftware.us) + ", Sucursal: " + new BaseDeDatos().valor("select company from company where id_company=" + ControlSoftware.cia));

        initComponents();

        modelo = (DefaultTableModel) detalle_devolucion.getModel();

        if (bd.valor("select max(cast(d.n_devolucion as unsigned))+1 as maximo from devoluciones d inner join factura f on d.id_factura=f.idfactura where d.cia=" + ControlSoftware.cia + " and f.tipo=1 and f.condicionpago=0") == null) {
            nc_fiscal.setText("1");
        } else {
            nc_fiscal.setText(String.format("%.0f", (Double.parseDouble(bd.valor("select max(cast(d.n_devolucion as unsigned))+1 as maximo from devoluciones d inner join factura f on d.id_factura=f.idfactura where d.cia=" + ControlSoftware.cia + " and f.tipo=1 and f.condicionpago=0")))));
        }
       subtotal2.setText("0.00");
       isv2.setText("0.00");
       subtotal2.setText("0.00");
       total2.setText("0.00");
       saldo_text.setText("0.00");
       nuevo_saldo_text.setText("0.00");
        modelo.addTableModelListener(e -> {
            if (e.getColumn() == 4) {
                int row = e.getFirstRow();
                Object abonoValue = modelo.getValueAt(row, 4);

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
                            detalle_devolucion.editCellAt(row, 4);
                            detalle_devolucion.getEditorComponent().requestFocusInWindow();
                        });
                    } else {
                        Object qty = modelo.getValueAt(row, 3);
                        
                        if (Double.parseDouble("" + qty) < Double.parseDouble("" + abonoValue)) {
                            JOptionPane.showMessageDialog(null, "La cantidad a devolver no puede ser mayor a la que contiene la factura.");
                            SwingUtilities.invokeLater(() -> {
                                detalle_devolucion.editCellAt(row, 4);
                                detalle_devolucion.getEditorComponent().requestFocusInWindow();
                            });
                        } else {
                            Object prc = modelo.getValueAt(row, 5);
                            double t=Double.parseDouble(""+prc)*Double.parseDouble(""+abonoValue);
                            double st=t/1.15;
                            double isv=t-st;
                            modelo.setValueAt(String.format("%.2f",isv), row, 6);
                            modelo.setValueAt(String.format("%.2f",st), row, 7);
                            modelo.setValueAt(String.format("%.2f",t), row, 8);
                            double sumaAbono = 0.0;
                            for (int u = 0; u < modelo.getRowCount(); u++) {
                                Object abonoValue1 = modelo.getValueAt(u, 8);
                                if (abonoValue1 != null) {
                                    try {
                                        double abono = Double.parseDouble(abonoValue1.toString());
                                        sumaAbono += abono;
                                    } catch (NumberFormatException ignored) { 
                                    }
                                }
                            }
                            total2.setText("" + String.format("%.2f",sumaAbono));
                            double abono = Double.parseDouble(""+abonoValue);
                            if(abono>0){
                            isv2.setText(""+String.format("%.2f",(sumaAbono-(sumaAbono/1.15))));
                            subtotal2.setText("" + String.format("%.2f",sumaAbono/1.15));
                            double saldo = Double.parseDouble(bd.valor("select saldo from factura where idfactura=" + idfact.getText() + " and cia=" + ControlSoftware.cia));
                            saldo_text.setText(""+String.format("%.2f",saldo));
                            nuevo_saldo_text.setText(""+String.format("%.2f",(saldo-sumaAbono)));
                            }
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
        idfact = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        nfact = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        fecha = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        descripcion_cliente = new javax.swing.JTextField();
        panel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        detalle_devolucion = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        nc_fiscal = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        obsdevolucion = new javax.swing.JTextArea();
        jPanel8 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        total2 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        isv2 = new javax.swing.JTextField();
        subtotal2 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        saldo_text = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        nuevo_saldo_text = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("INGRESAR DEVOLUCIÓN");

        idfact.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                idfactFocusLost(evt);
            }
        });
        idfact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                idfactMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                idfactMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                idfactMouseReleased(evt);
            }
        });
        idfact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idfactKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                idfactKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idfactKeyTyped(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(195, 180, 225));
        jButton3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("SELECCIONAR FACTURA");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        nfact.setEditable(false);

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("FECHA INGRESO");

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("CLIENTE");

        descripcion_cliente.setEditable(false);

        panel.setBackground(new java.awt.Color(195, 180, 225));
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "DETALLE DEVOLUCIÓN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        detalle_devolucion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDPRODUCTO", "CODIGOPRODUCTO", "DESCRIPCION", "CANTIDAD", "DEVOLVER", "PRECIO", "ISV", "SUBTOTAL", "TOTAL", "OBSERVACION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        detalle_devolucion.getTableHeader().setReorderingAllowed(false);
        detalle_devolucion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                detalle_devolucionMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(detalle_devolucion);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("N° DEVOLUCIÓN");

        nc_fiscal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nc_fiscalKeyPressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("OBSERVACIÓN");

        obsdevolucion.setColumns(20);
        obsdevolucion.setRows(5);
        jScrollPane3.setViewportView(obsdevolucion);

        jPanel8.setBackground(new java.awt.Color(195, 180, 225));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "DESGLOSE DEVOLUCION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel8.setForeground(new java.awt.Color(0, 0, 0));

        jLabel23.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("TOTAL");

        total2.setEditable(false);
        total2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total2ActionPerformed(evt);
            }
        });
        total2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                total2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                total2KeyTyped(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("ISV");

        isv2.setEditable(false);

        subtotal2.setEditable(false);

        jLabel25.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("SUBTOTAL");

        saldo_text.setEditable(false);
        saldo_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                saldo_textKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                saldo_textKeyTyped(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("SALDO FACTURA");

        jLabel27.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("NUEVO SALDO");

        nuevo_saldo_text.setEditable(false);
        nuevo_saldo_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nuevo_saldo_textKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nuevo_saldo_textKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel24)
                    .addComponent(jLabel23)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(isv2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                    .addComponent(total2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nuevo_saldo_text, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(saldo_text, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(subtotal2))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(subtotal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(isv2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(total2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(saldo_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(nuevo_saldo_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(195, 180, 225));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

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
        jPanel3.add(jButton6);

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
        jPanel3.add(jButton2);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idfact, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nfact))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descripcion_cliente))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nc_fiscal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(idfact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nfact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nc_fiscal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descripcion_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
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
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        /*
        SI LA FACTURA ES ORIGINAL EL RECIBO Y LA NOTA DE CRÉDITO GENERADA ES ORIGINAL, 
        SI ES PROFORMA EL RECIBO Y LA NOTA DE CRÉDITO SERÁ PROFORMA
        EN EL CASO DE ORIGINAL SE TOMA EN CUENTA CAI Y FECHA LIMITE DE LA NC
        SE TOMA EN CUENTA EL LIMITE DE PRODUCTOS PERMITIDOS EN UNA NC
        
        
        */
        
        
        
        
        if (total2.getText().isEmpty()|| Double.parseDouble(total2.getText())<=0) {
            JOptionPane.showMessageDialog(null, "Primero debe agregar artículos a devolver, el total está en cero.");
            return;
        }
        if (nc_fiscal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se puede guardar sin numero de NC, ingreselo primero");
            return;
        }
        if (fecha.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Fecha de ingreso no puede estar vacío");
            return;
        }
        String idcai = bd.valor("select caiid from company where id_company=" + ControlSoftware.cia);
        String tipo=""+bd.valor("SELECT TIPO FROM FACTURA WHERE IDFACTURA="+idfact.getText()+" and cia="+ControlSoftware.cia);
        if(bd.versiesta("select d.n_devolucion from devoluciones inner join factura f on f.idfactura=d.id_factura where f.tipo="+tipo+" and f.condicionpago=0")){
             JOptionPane.showMessageDialog(null, "Numero de nc ya ingresado, intente con otro número");
            return;
        }
        
        String typ="3";
        if (tipo.equals("1")) {
            typ="1";
            try {
                long rangoi = Long.parseLong(bd.valor("select rango_inicial from historico_cainotacredito where id=" + idcai + " and cia=" + ControlSoftware.cia));
                long rangof = Long.parseLong(bd.valor("select rango_final from historico_cainotacredito where id=" + idcai + " and cia=" + ControlSoftware.cia));

                String facturaText = nc_fiscal.getText();

                if (!facturaText.isEmpty() && facturaText.matches("\\d+")) {
                    long facturaValue = Long.parseLong(facturaText);

                    if (facturaValue < rangoi || facturaValue > rangof) {
                        JOptionPane.showMessageDialog(null, "Número de factura fuera de rango");
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El número de factura no es válido");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error al convertir valores numéricos");
                e.printStackTrace();
                return;
            }

            Date fechaDesdeBD = bd.fecha("select fecha_final from historico_cainotacredito where id=" + idcai + " and cia=" + ControlSoftware.cia);
            java.sql.Date sqlFechaDesdeBD = new java.sql.Date(fechaDesdeBD.getTime());
            java.sql.Date sqlFechatexto = new java.sql.Date(fecha.getDate().getTime());

            if (sqlFechaDesdeBD.compareTo(sqlFechatexto) < 0 && !("" + sqlFechaDesdeBD).equals("" + sqlFechatexto)) {
                JOptionPane.showMessageDialog(null, "Fecha inválida; fuera de rango del CAI");
                return;
            }
            

        } 
        
        String st = subtotal2.getText().replace(",", "");
        String im = isv2.getText().replace(",", "");
        String to = total2.getText().replace(",", "");
        double total_devolucion = Double.parseDouble(to);
        double saldo = Double.parseDouble(bd.valor("select saldo from factura where idfactura=" + idfact.getText() + " and cia=" + ControlSoftware.cia));
        if (total_devolucion > saldo) {
            JOptionPane.showMessageDialog(null, "No es posible ingresar esta nota de crédito puesto que el saldo de esta factura es menor que la cantidad a acreditar a la cuenta.");
            return;

        }
        int opc = JOptionPane.showConfirmDialog(null, "Desea ingresar la siguiente nota de crédito para la factura: " + nfact.getText() + "?");
        if (opc == 0) {

            //////////GUARDAMOS NOTA DE CREDITO//////////////
            SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String caf = bd.valor("select caincid from company where id_company=" + ControlSoftware.cia);
                    
            String id_nota_credito_guardada = bd.doble("INSERT INTO `devoluciones`(`n_devolucion`, `id_factura`, `cia`, `subtotal`, `isv`, `total`, `fecha_devolucion`, `fecha_sistema`, `idusuario`, `observacion`, `estado`, `caiid`) VALUES ('" + nc_fiscal.getText() + "','" + idfact.getText() + "','" + ControlSoftware.cia + "','" + st + "','" + im + "','" + to + "','" + Date_Format.format(fecha.getDate()) + "','" + date.format(formatter) + "','" + ControlSoftware.us + "','" + obsdevolucion.getText() + "','1','"+caf+"')", "SELECT @@IDENTITY AS 'id'");
            for (int i = 0; i < detalle_devolucion.getRowCount(); i++) {
                bd.EjecutarConsultas("INSERT INTO `detalle_devolucion`(`id_devolucion`, `id_producto`, `cantidad`, `precio`, `isv`, `subtotal`, `total`, `estado`, `cia`, `observacion`, `fecha`) VALUES ('" + id_nota_credito_guardada + "','" + detalle_devolucion.getValueAt(i, 0) + "','" + detalle_devolucion.getValueAt(i, 4) + "','" + detalle_devolucion.getValueAt(i, 5) + "','" + detalle_devolucion.getValueAt(i, 6) + "','" + detalle_devolucion.getValueAt(i, 7) + "','" + detalle_devolucion.getValueAt(i, 8) + "','1','" + ControlSoftware.cia + "','" + detalle_devolucion.getValueAt(i, 9) + "','" + date.format(formatter) + "')");

                insertartransaccion(id_nota_credito_guardada, "4", Double.parseDouble(bd.valor("select existencia from producto where idproducto=" + detalle_devolucion.getValueAt(i, 0))), Double.parseDouble("" + detalle_devolucion.getValueAt(i, 4)), Double.parseDouble(bd.valor("select existencia from producto where idproducto=" + detalle_devolucion.getValueAt(i, 0))) + Double.parseDouble("" + detalle_devolucion.getValueAt(i, 4)), "" + detalle_devolucion.getValueAt(i, 0));

                bd.EjecutarConsultas("Update producto set existencia=existencia+" + detalle_devolucion.getValueAt(i, 4) + " where idproducto=" + detalle_devolucion.getValueAt(i, 0) + " and cia=" + ControlSoftware.cia);
            }
            bd.EjecutarConsultas("update factura set saldo=saldo-" + total_devolucion + " where idfactura=" + idfact.getText() + " and cia=" + ControlSoftware.cia);
            String cliente = bd.valor("select idcliente from factura where idfactura=" + idfact.getText() + " and cia=" + ControlSoftware.cia);
            bd.EjecutarConsultas("update clientes set saldo=saldo-" + total_devolucion + " where idcliente=" + cliente + " and cia=" + ControlSoftware.cia);
            String vendedor = bd.valor("select idvendedor from factura where idfactura=" + idfact.getText() + " and cia=" + ControlSoftware.cia);
            String v = bd.doble("INSERT INTO `recibopago`(`numero_recibo`, `tipo`, `fecha`, `descripcion`, `idcliente`, `idvendedor`, `cia`, `total`, `fechaingreso`, `estado`) VALUES ('" + nc_fiscal.getText() + "','" + typ + "','" + Date_Format.format(fecha.getDate()) + "','" + "Nota de crédito, "+obsdevolucion.getText() + "','" + cliente + "','" + vendedor + "','" + ControlSoftware.cia + "','" + total_devolucion + "','" + date.format(formatter) + "','1')", "SELECT @@IDENTITY AS 'id'");

            double s = Double.parseDouble("" + bd.valor("select saldo from factura where idfactura=" + idfact.getText() + " and cia=" + ControlSoftware.cia));
            double t = Double.parseDouble("" + bd.valor("select total from factura where idfactura=" + idfact.getText() + " and cia=" + ControlSoftware.cia));
            if (s < t && s != 0.0) {
                bd.EjecutarConsultas("update factura set estado=2 where idfactura=" + idfact.getText() + " and cia=" + ControlSoftware.cia);
            }
            if (s == 0.0) {
                bd.EjecutarConsultas("update factura set estado=3 where idfactura=" + idfact.getText() + " and cia=" + ControlSoftware.cia);
            }
            bd.EjecutarConsultas("INSERT INTO `detalle_recibopago`(`idrecibo`, `idfactura`, `abono`, `saldoanterior`, `nuevosaldo`, `cia`, `estado`, `fechasistema`) VALUES ('" + v + "','" + idfact.getText() + "','" + total_devolucion + "','" + saldo + "','" + (saldo - total_devolucion) + "','" + ControlSoftware.cia + "','1','" + date.format(formatter) + "')");
            dispose();
            mantenimiento_devoluciones l1 = new mantenimiento_devoluciones();
            Dimension internalFrameSize = l1.getSize();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            l1.setLocation((screenSize.width - internalFrameSize.width) / 2,
                    (screenSize.height - internalFrameSize.height) / 2);
            DESKSOFTWARE.escritorio.add(l1);
            l1.show();
            JOptionPane.showMessageDialog(null, "Devolución realizada!");
            int l = 0;
            if (bd.valor("select tipo from factura where idfactura=" + idfact.getText()+" and cia="+ControlSoftware.cia).equals("1")) {
                l = 1;
            }
            try {
                if (l == 0) {
                    try {
                        NumeroEnLetras n = new NumeroEnLetras();
                        BaseDeDatos con = new BaseDeDatos();
                        Connection conn = con.getConexion();
                        // Cargar el reporte desde el archivo .jasper
                        String path = "/nc_proforma.jasper";
                        InputStream inputStream = getClass().getResourceAsStream(path);
                        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
                        Map<String, Object> parametros = new HashMap<>();
                        parametros.put("factura", id_nota_credito_guardada);
                        parametros.put("MONTO", n.Convertir(to, true));
                        parametros.put("cia", ControlSoftware.cia);
                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);
                        JasperViewer viewer = new JasperViewer(jasperPrint, false);
                        viewer.setVisible(true);
                    } catch (JRException ex) {
                        System.out.println("Error al generar el reporte: " + ex.getMessage());
                    }
                } else {
                    try {
                        NumeroEnLetras n = new NumeroEnLetras();
                        BaseDeDatos con = new BaseDeDatos();
                        Connection conn = con.getConexion();
                        // Cargar el reporte desde el archivo .jasper
                        String path = "/nc_original.jasper";
                        InputStream inputStream = getClass().getResourceAsStream(path);
                        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
                        Map<String, Object> parametros = new HashMap<>();
                        parametros.put("factura", id_nota_credito_guardada);
                        parametros.put("MONTO", n.Convertir(to, true));
                        parametros.put("cia", ControlSoftware.cia);
                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);
                        JasperViewer viewer = new JasperViewer(jasperPrint, false);
                        viewer.setVisible(true);
                    } catch (JRException ex) {
                        System.out.println("Error al generar el reporte: " + ex.getMessage());
                    }
                }
            } catch (Exception e) {
            }

            try {
                BaseDeDatos con = new BaseDeDatos();
                Connection conn = con.getConexion();
                // Cargar el reporte desde el archivo .jasper
                String path = "/PagoFactura.jasper";
                InputStream inputStream = getClass().getResourceAsStream(path);
                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);

                // Crear el objeto JasperPrint con los datos y el reporte
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("id_recibo", v);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);

                // Crear el objeto JasperViewer y mostrar el reporte en pantalla
                JasperViewer viewer = new JasperViewer(jasperPrint, false);
                viewer.setVisible(true);

            } catch (JRException ex) {
                System.out.println("Error al generar el reporte: " + ex.getMessage());
            }

        }
    }//GEN-LAST:event_jButton2ActionPerformed
    public void insertartransaccion(String id, String tipo, Double saldoactual, Double cantidad, Double saldo_final, String producto) {
        bd.EjecutarConsultas("INSERT INTO `kardex`(`fecha`, `id_documento`, `tipo_documento`, `saldo_actual`, `cantidad`, `saldo_final`, `id_producto`, `cia`, `idusuario`, `estado`) VALUES ('" + LocalDateTime.now() + "','" + id + "','" + tipo + "','" + saldoactual + "','" + cantidad + "','" + saldo_final + "','" + producto + "','" + ControlSoftware.cia + "','" + ControlSoftware.us + "','1')");
    }
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        mantenimiento_devoluciones l1 = new mantenimiento_devoluciones();
        Dimension internalFrameSize = l1.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        l1.setLocation((screenSize.width - internalFrameSize.width) / 2,
                (screenSize.height - internalFrameSize.height) / 2);
        DESKSOFTWARE.escritorio.add(l1);
        l1.show();
        dispose();
    }//GEN-LAST:event_jButton6ActionPerformed
    public void calcular() {
        DecimalFormat formatea = new DecimalFormat("###,###.##");
        double sum = 0.0;
        double sum2 = 0.0;
        for (int i = 0; i < detalle_devolucion.getRowCount(); i++) {
            sum += Double.parseDouble("" + detalle_devolucion.getValueAt(i, 6));
        }
        total2.setText("" + formatea.format(sum));
        subtotal2.setText("" + formatea.format((sum / 1.15)));
        isv2.setText("" + formatea.format((sum - (sum / 1.15))));
    }

    
    private void detalle_devolucionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detalle_devolucionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_detalle_devolucionMouseClicked

    private void total2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_total2KeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_total2KeyReleased

    private void total2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_total2KeyTyped
        // TODO add your handling code here:


    }//GEN-LAST:event_total2KeyTyped

    private void saldo_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_saldo_textKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_saldo_textKeyReleased

    private void saldo_textKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_saldo_textKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_saldo_textKeyTyped

    private void nuevo_saldo_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nuevo_saldo_textKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_nuevo_saldo_textKeyReleased

    private void nuevo_saldo_textKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nuevo_saldo_textKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_nuevo_saldo_textKeyTyped

    private void total2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_total2ActionPerformed

    private void nc_fiscalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nc_fiscalKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == evt.VK_V)) {
            // Evitar la acción de pegar
            evt.consume();
        }
    }//GEN-LAST:event_nc_fiscalKeyPressed

    private void idfactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idfactKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        if (!(key == '0' || key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_idfactKeyTyped

    private void idfactKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idfactKeyReleased

        if (evt.getKeyCode() == 8) {
            if (idfact.getText().isEmpty()) {
                idfact.setText("0");
                nfact.setText("seleccione");
            }
        }
    }//GEN-LAST:event_idfactKeyReleased

    private void idfactKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idfactKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == evt.VK_V)) {
            // Evitar la acción de pegar
            evt.consume();
        }
    }//GEN-LAST:event_idfactKeyPressed

    private void idfactMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_idfactMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_idfactMouseReleased

    private void idfactMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_idfactMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_idfactMouseExited

    private void idfactMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_idfactMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_idfactMouseClicked

    private void idfactFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_idfactFocusLost
        // TODO add your handling code here:
        if (bd.valor("select numerofactura from factura where cia=" + ControlSoftware.cia + " and idfactura=" + idfact.getText()).equals("")) {
            JOptionPane.showMessageDialog(null, "No existe!");
            idfact.setText("0");
            nfact.setText("seleccione");

        } else {
            nfact.setText("" + bd.valor("select numerofactura from factura where cia=" + ControlSoftware.cia + " and idfactura=" + idfact.getText()));
        }
    }//GEN-LAST:event_idfactFocusLost

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        seleccionarfactura_devolucion l1 = new seleccionarfactura_devolucion();
        Dimension internalFrameSize = l1.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        l1.setLocation((screenSize.width - internalFrameSize.width) / 2,
            (screenSize.height - internalFrameSize.height) / 2);
        DESKSOFTWARE.escritorio.add(l1);
        l1.show();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(ingresar_devolucion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ingresar_devolucion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ingresar_devolucion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ingresar_devolucion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ingresar_devolucion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField descripcion_cliente;
    public static javax.swing.JTable detalle_devolucion;
    public static com.toedter.calendar.JDateChooser fecha;
    public static javax.swing.JTextField idfact;
    public static javax.swing.JTextField isv2;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JTextField nc_fiscal;
    public static javax.swing.JTextField nfact;
    public static javax.swing.JTextField nuevo_saldo_text;
    private javax.swing.JTextArea obsdevolucion;
    private javax.swing.JPanel panel;
    public static javax.swing.JTextField saldo_text;
    public static javax.swing.JTextField subtotal2;
    public static javax.swing.JTextField total2;
    // End of variables declaration//GEN-END:variables
}
