/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.controlsoftware;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
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
public class ingresarfactura extends javax.swing.JInternalFrame {

    DefaultTableModel modelo;
    BaseDeDatos bd = new BaseDeDatos();

    public static String costonumeros = "";
    public static String pedido = "0";

    public ingresarfactura(String op) {
        super("Usuario: " + new BaseDeDatos().valor("select user from usuario where cod_user=" + ControlSoftware.us) + ", Sucursal: " + new BaseDeDatos().valor("select company from company where id_company=" + ControlSoftware.cia));

        UIManager.put("TableHeaderUI", "javax.swing.plaf.basic.BasicTableHeaderUI");
        initComponents();

        String perm = "182";
        if (!bd.versiesta("select * from role_usuario inner join role on role.id=role_usuario.roleid inner join usuario on usuario.cod_user=role_usuario.usuarioid inner join role_permiso on role_permiso.roleid=role.id inner join permisos on permisos.id=role_permiso.permisoid where permisos.id=" + perm + " and role_usuario.cia=" + ControlSoftware.cia + " and usuario.cod_user=" + ControlSoftware.us)) {
            totalfinal.setEditable(false);
        }
        
        if (cp.isSelected()) {
            if (bd.valor("select max(cast(numerofactura as unsigned))+1 as maximo from factura where cia=" + ControlSoftware.cia + " and tipo=0") == null) {
                factura.setText("1");
            } else {
                factura.setText(String.format("%.0f", (Double.parseDouble(bd.valor("select max(cast(numerofactura as unsigned))+1 as maximo from factura where cia=" + ControlSoftware.cia + " and tipo=0")))));
            }
        } else {

            if (bd.valor("select max(cast(numerofactura as unsigned))+1 as maximo from factura where cia=" + ControlSoftware.cia + " and tipo=1") == null) {
                factura.setText("1");
            } else {
                factura.setText(String.format("%.0f", (Double.parseDouble(bd.valor("select max(cast(numerofactura as unsigned))+1 as maximo from factura where cia=" + ControlSoftware.cia + " and tipo=1")))));
            }
        }

        bd.LlenarCombo1("select id_vendedor, vendedor from vendedor where cia=" + ControlSoftware.cia, vend, "id_vendedor", "vendedor");

        detalle.setDefaultRenderer(Object.class, new MiRenderer2());

        total.setText("0.0");
        totalfinal.setText("0.0");
        descuento.setText("0.0");
        isv.setText("0.0");
        subtotal.setText("0.0");
        jCheckBox1.setVisible(false);
        pedido = op;
        fecha.setDate(new Date());
        if (!op.equals("0") ) {
            if(bd.versiesta("select * from company_configuraciones inner join configuraciones on company_configuraciones.configuracionid=configuraciones.id inner join company on company.id_company=company_configuraciones.companyid where company_configuraciones.companyid=" + ControlSoftware.cia + " and company_configuraciones.configuracionid=" + "4")){
            detalle.setEnabled(false);
            agg.setEnabled(false);
            }
            String n = ""+bd.valor("select nombrereferencia from orden_de_pedido where idorden=" + op+" and cia="+ControlSoftware.cia);
            /*if (!n.equals("0")) {
            
                boolean resultado;

                try {
                    Integer.parseInt(n);
                    resultado = true;
                } catch (NumberFormatException excepcion) {
                    resultado = false;
                }

                if (resultado == true) {
                    factura.setText(n);
                } 

            } */
             if (bd.versiesta("select * from company_configuraciones inner join configuraciones on company_configuraciones.configuracionid=configuraciones.id inner join company on company.id_company=company_configuraciones.companyid where company_configuraciones.companyid=" + ControlSoftware.cia + " and company_configuraciones.configuracionid=" + "3")) { // restringir por monto definido
          factura.setText(n);
             }
            

            codcliente.setText("" + bd.valor("select idcliente from orden_de_pedido where idorden=" + pedido + " and cia=" + ControlSoftware.cia));
            descliente.setText("" + bd.valor("select nombre from clientes where idcliente=" + codcliente.getText() + " and cia=" + ControlSoftware.cia));
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(bd.valor("select fechaorden from orden_de_pedido where idorden=" + pedido + " and cia=" + ControlSoftware.cia));
                fecha.setDate(date);
            } catch (Exception e) {
                System.out.println(e);
            }
            bd.seleccionarItemPorId(vend, bd.valor("select idvendedor from orden_de_pedido where idorden=" + pedido + " and cia=" + ControlSoftware.cia));
            bd.LlenarTabla("select d.idproducto as CODIGO, p.codigo AS CODLETRAS, p.descripcion AS DESCRIPCION, d.cantidad AS CANTIDAD, d.costo AS PRECIOCOMPRA, d.preciovendido AS PRECIOVENTA, d.total AS TOTAL, d.observacion AS OBSERVACION from detalle_orden_de_pedido d inner join producto p on d.idproducto=p.idproducto where d.id_orden_de_pedido=" + pedido, detalle);
            obsfact.setText("" + bd.valor("select observacion from orden_de_pedido where idorden=" + pedido + " and cia=" + ControlSoftware.cia));
            if (bd.valor("select tipo from orden_de_pedido where idorden=" + pedido + " and cia=" + ControlSoftware.cia).equals("1")) {
                original.setSelected(true);
            }
            if (bd.valor("select condicionpago from orden_de_pedido where idorden=" + pedido + " and cia=" + ControlSoftware.cia).equals("1")) {
                contado.setSelected(true);
            }
            calcular();
        }

        modelo = (DefaultTableModel) detalle.getModel();
//        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png")));
//        
//        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png")));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cp = new javax.swing.JRadioButton();
        original = new javax.swing.JRadioButton();
        fecha = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        factura = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        isv = new javax.swing.JTextField();
        subtotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        totalfinal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        descuento = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        credito = new javax.swing.JRadioButton();
        contado = new javax.swing.JRadioButton();
        descliente = new javax.swing.JTextField();
        codcliente = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
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
        jLabel21 = new javax.swing.JLabel();
        vend = new javax.swing.JComboBox<>();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        art = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(195, 180, 225));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "TIPO DE FACTURA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        cp.setBackground(new java.awt.Color(195, 180, 225));
        buttonGroup1.add(cp);
        cp.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cp.setForeground(new java.awt.Color(0, 0, 0));
        cp.setSelected(true);
        cp.setText("PROFORMA");
        cp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpActionPerformed(evt);
            }
        });

        original.setBackground(new java.awt.Color(195, 180, 225));
        buttonGroup1.add(original);
        original.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        original.setForeground(new java.awt.Color(0, 0, 0));
        original.setText("ORIGINAL");
        original.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                originalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cp, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(original, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(cp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(original)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        fecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaMouseClicked(evt);
            }
        });
        fecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fechaKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("FECHA INGRESO");

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
        jLabel2.setText("N° FACTURA");

        jPanel2.setBackground(new java.awt.Color(195, 180, 225));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "DESGLOSE FACTURA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(0, 0, 0));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("TOTAL DE LA FACTURA");

        total.setEditable(false);
        total.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                totalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                totalKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("ISV");

        isv.setEditable(false);

        subtotal.setEditable(false);

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("SUBTOTAL");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("TOTAL FINAL");

        totalfinal.setForeground(new java.awt.Color(153, 0, 0));
        totalfinal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                totalfinalFocusLost(evt);
            }
        });
        totalfinal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                totalfinalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                totalfinalKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("DESCUENTO");

        descuento.setEditable(false);
        descuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                descuentoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descuentoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subtotal, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(isv)
                    .addComponent(total)
                    .addComponent(totalfinal)
                    .addComponent(descuento, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(isv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalfinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(195, 180, 225));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "CONDICIÓN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        credito.setBackground(new java.awt.Color(195, 180, 225));
        buttonGroup2.add(credito);
        credito.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        credito.setForeground(new java.awt.Color(0, 0, 0));
        credito.setSelected(true);
        credito.setText("CRÉDITO");

        contado.setBackground(new java.awt.Color(195, 180, 225));
        buttonGroup2.add(contado);
        contado.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        contado.setForeground(new java.awt.Color(0, 0, 0));
        contado.setText("CONTADO");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(credito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(contado, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(credito)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contado)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        descliente.setEditable(false);

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

        jButton3.setBackground(new java.awt.Color(195, 180, 225));
        jButton3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("CLIENTE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

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
        jLabel10.setText("INGRESO DE FACTURA");

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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        jLabel21.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("VENDEDOR");

        vend.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                vendItemStateChanged(evt);
            }
        });

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
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(185, 185, 185)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(art, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(factura, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel21)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(vend, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descliente)))
                .addGap(16, 16, 16))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(codcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jCheckBox1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(art, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        seleccionarcliente l = new seleccionarcliente();
        Dimension internalFrameSize = l.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        l.setLocation((screenSize.width - internalFrameSize.width) / 2,
                (screenSize.height - internalFrameSize.height) / 2);
        DESKSOFTWARE.escritorio.add(l);
        l.show();

    }//GEN-LAST:event_jButton3ActionPerformed

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
                isv.setText("0.0");
                subtotal.setText("0.0");
            }
            double t = Double.parseDouble(total.getText());
            isv.setText("" + String.format("%.2f", t - (t / 1.15)));
            subtotal.setText("" + String.format("%.2f", t / 1.15));
        } else {
            double t = Double.parseDouble(total.getText());

            isv.setText("" + String.format("%.2f", t - (t / 1.15)));
            subtotal.setText("" + String.format("%.2f", t / 1.15));

        }

    }//GEN-LAST:event_totalKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            if (pedido.equals("0")) {
                for (int i = 0; i < detalle.getRowCount(); i++) {
                    Double q = Double.parseDouble(bd.valor("select existencia-reserva from producto where idproducto=" + detalle.getValueAt(i, 0) + " and cia=" + ControlSoftware.cia));
                    Double qp = Double.parseDouble("" + detalle.getValueAt(i, 3));
                    if (q < qp) {
                        JOptionPane.showMessageDialog(null, "Favor modificar cantidad del producto:" + "" + detalle.getValueAt(i, 1) + ", la existencia cambió.");
                        System.out.println("entro1");
                        return;
                    }
                }
            }

            if (!pedido.equals("0")) {//si viene de pedido
                String codproductoreservado[] = bd.Llenar("select idproducto from detalle_orden_de_pedido where id_orden_de_pedido=" + pedido + " and cia=" + ControlSoftware.cia, "idproducto");
                String canproductoreservado[] = bd.Llenar("select cantidad from detalle_orden_de_pedido where id_orden_de_pedido=" + pedido + " and cia=" + ControlSoftware.cia, "cantidad");
                for (int i = 0; i < codproductoreservado.length; i++) {
                    for (int j = 0; j < detalle.getRowCount(); j++) {
                        if (("" + detalle.getValueAt(j, 0)).equals("" + codproductoreservado[i])) {//si la fila contiene el mismo codigo con un registro de tabla
                            if (Double.parseDouble("" + detalle.getValueAt(j, 3)) > Double.parseDouble(canproductoreservado[i])) {//si pedimos mas que lo reservado pasa esto revisamos existencia
                                Double q = Double.parseDouble(bd.valor("select existencia-reserva from producto where idproducto=" + codproductoreservado[i] + " and cia=" + ControlSoftware.cia));
                                Double qp = Double.parseDouble("" + detalle.getValueAt(j, 3)) - Double.parseDouble(canproductoreservado[i]);
                                System.out.println(q);
                                System.out.println(qp);
                                if (q < qp) {
                                    JOptionPane.showMessageDialog(null, "Favor modificar cantidad del producto:" + "" + detalle.getValueAt(j, 1) + ", la existencia cambió.");
                                    System.out.println("entro2");
                                    return;
                                }
                            }
                        }

                    }
                }
                for (int i = 0; i < detalle.getRowCount(); i++) {
                    int x = 1;
                    for (int j = 0; j < codproductoreservado.length; j++) {
                        System.out.println(detalle.getValueAt(i, 0));
                        System.out.println(codproductoreservado[j]);
                        if (("" + detalle.getValueAt(i, 0)).equals("" + codproductoreservado[j])) {//si la fila contiene el mismo codigo con un registro de tabla
                            x = 0;

                        }
                    }

                    if (x == 1) {
                        Double q = Double.parseDouble(bd.valor("select existencia-reserva from producto where idproducto=" + detalle.getValueAt(i, 0) + " and cia=" + ControlSoftware.cia));
                        Double qp = Double.parseDouble("" + detalle.getValueAt(i, 3));

                        if (q < qp) {
                            JOptionPane.showMessageDialog(null, "Favor modificar cantidad del producto:" + "" + detalle.getValueAt(i, 1) + ", la existencia cambió.");

                            return;
                        }
                    }
                }

            }

            int l = 0;
            int m = 0;

            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            double cantidad_articulos = Double.parseDouble("" + art.getText());
            double cantidad_articulos_cia = Double.parseDouble(bd.valor("select limiteci from company where id_company=" + ControlSoftware.cia));

            if (original.isSelected()) {
                l = 1;
                cantidad_articulos_cia = Double.parseDouble(bd.valor("select limitefac from company where id_company=" + ControlSoftware.cia));
            }
            if (cantidad_articulos > cantidad_articulos_cia) {
                JOptionPane.showMessageDialog(null, "La cantidad de articulos en esta factura es mayor que la disponible para facturar.");
                return;
            }
            if (contado.isSelected()) {
                m = 1;

            }
            if (detalle.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "No se puede guardar una factura sin productos.");
                return;
            }

            if (codcliente.getText().equals("0") || codcliente.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cliente no puede estar vacío");
                return;
            }
            if (!bd.versiesta("select * from clientes where idcliente=" + codcliente.getText() + " and cia=" + ControlSoftware.cia)) {
                JOptionPane.showMessageDialog(null, "Este cliente no existe.");
                return;
            }
            if (bd.versiesta("select numerofactura from factura where numerofactura=" + factura.getText() + " and cia=" + ControlSoftware.cia + " and tipo=" + l)) {
                JOptionPane.showMessageDialog(null, "Factura no puede repetirse");
                return;
            }

            if (fecha.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Fecha de ingreso no puede estar vacío");
                return;
            }
            String idcai = bd.valor("select caiid from company where id_company=" + ControlSoftware.cia);
            if (!cp.isSelected()) {
                try {
                    long rangoi = Long.parseLong(bd.valor("select rango_inicial from historico_caifactura where id=" + idcai + " and cia=" + ControlSoftware.cia));
                    long rangof = Long.parseLong(bd.valor("select rango_final from historico_caifactura where id=" + idcai + " and cia=" + ControlSoftware.cia));

                    String facturaText = factura.getText();

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

                Date fechaDesdeBD = bd.fecha("select fecha_final from historico_caifactura where id=" + idcai + " and cia=" + ControlSoftware.cia);
                java.sql.Date sqlFechaDesdeBD = new java.sql.Date(fechaDesdeBD.getTime());
                java.sql.Date sqlFechatexto = new java.sql.Date(fecha.getDate().getTime());

                if (sqlFechaDesdeBD.compareTo(sqlFechatexto) < 0 && !("" + sqlFechaDesdeBD).equals("" + sqlFechatexto)) {
                    JOptionPane.showMessageDialog(null, "Fecha inválida; fuera de rango del CAI");
                    return;
                }

            }

            if (factura.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Factura no puede estar vacío");
                return;
            } else {
                String st = subtotal.getText().replace(",", "");
                String im = isv.getText().replace(",", "");
                String t = total.getText().replace(",", "");
                String tt = totalfinal.getText().replace(",", "");
                String des = descuento.getText().replace(",", "");
                double saldocte = Double.parseDouble(bd.valor("select coalesce(sum(saldo),0.00) from factura where idcliente=" + codcliente.getText() + " and cia=" + ControlSoftware.cia + " and estado!=0"));
                double tot = Double.parseDouble(t);
                double limitecte = Double.parseDouble(bd.valor("select limite from clientes where idcliente=" + codcliente.getText() + " and cia=" + ControlSoftware.cia));
                double dlimitecte = Double.parseDouble(bd.valor("select diaslimite from clientes where idcliente=" + codcliente.getText() + " and cia=" + ControlSoftware.cia));

                if (bd.versiesta("select * from company_configuraciones inner join configuraciones on company_configuraciones.configuracionid=configuraciones.id inner join company on company.id_company=company_configuraciones.companyid where company_configuraciones.companyid=" + ControlSoftware.cia + " and company_configuraciones.configuracionid=" + "1")) { // restringir por monto definido
                    limitecte = Double.parseDouble(bd.valor("select saldolimitecredito from company where id_company=" + ControlSoftware.cia));
                }
                if (bd.versiesta("select * from company_configuraciones inner join configuraciones on company_configuraciones.configuracionid=configuraciones.id inner join company on company.id_company=company_configuraciones.companyid where company_configuraciones.companyid=" + ControlSoftware.cia + " and company_configuraciones.configuracionid=" + "2")) { // restringir por monto definido
                    dlimitecte = Double.parseDouble(bd.valor("select diaslimitecredito from company where id_company=" + ControlSoftware.cia));
                }

                if (saldocte + tot > limitecte && m == 0) {
                    JOptionPane.showMessageDialog(null, "El total de la factura sobrepasa el limite de facturación de este cliente, ingrese credenciales para proseguir");
                    Ingreso_de_credenciales dialog = new Ingreso_de_credenciales("77");
                    dialog.setModal(true);
                    dialog.setVisible(true);  // AQUI DEBE ESPERAR A QUE INGRESE LOS DATOS PARA HACER LA SIGUIENTE CONDICION
                    if (dialog.paso == false) {
                        return;
                    }

                } else if (bd.versiesta("select * from FACTURA INNER JOIN CLIENTES ON FACTURA.idcliente=CLIENTES.idcliente WHERE DATEDIFF(CURRENT_DATE(), factura.fechafactura)>" + dlimitecte + " AND FACTURA.idcliente=" + codcliente.getText() + " AND FACTURA.CIA=" + ControlSoftware.cia + " and factura.saldo>0;") && m == 0) {
                    JOptionPane.showMessageDialog(null, "El cliente tiene facturas vencidas, ingrese credenciales para proseguir");
                    Ingreso_de_credenciales dialog = new Ingreso_de_credenciales("115");
                    dialog.setModal(true);
                    dialog.setVisible(true);  // AQUI DEBE ESPERAR A QUE INGRESE LOS DATOS PARA HACER LA SIGUIENTE CONDICION
                    if (dialog.paso == false) {
                        return;
                    }
                }
                if (contado.isSelected()) {
                    jCheckBox1.setSelected(false);
                    pagocontado l1 = new pagocontado(Double.parseDouble(t));
                    Dimension internalFrameSize = l1.getSize();
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    l1.setLocation((screenSize.width - internalFrameSize.width) / 2,
                            (screenSize.height - internalFrameSize.height) / 2);
                    DESKSOFTWARE.escritorio.add(l1);
                    l1.show();

                } else {
                    SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
                    double sal = Double.parseDouble(total.getText().replace(",", ""));
                    int opc = 0;
                    opc = JOptionPane.showConfirmDialog(null, "Desea guardar la factura con el siguiente numero: " + factura.getText() + "?");
                    if (opc == 0) {
                        Item ve = (Item) vend.getSelectedItem();
                        String caf = bd.valor("select  caiid from company where id_company=" + ControlSoftware.cia);
                        String cf = bd.doble("INSERT INTO `factura`(`numerofactura`, `tipo`, `cia`, `idcliente`, `idvendedor`, `subtotal`, `isv`, `total`, `fechafactura`, `fechaingreso`, `saldo`, `idusuario`, `observacion`, `condicionpago`, `transporte`, `guia`, `estado`, `comprobado`, `op`, `caiid`, `cajaid`, `totalfinal`, `descuento`) VALUES ('" + factura.getText() + "','" + l + "','" + ControlSoftware.cia + "','" + codcliente.getText() + "','" + ve.getId() + "','" + st + "','" + im + "','" + t + "','" + Date_Format.format(fecha.getDate()) + "','" + date.format(formatter) + "','" + sal + "','" + ControlSoftware.us + "','" + obsfact.getText() + "','" + m + "','0','pendiente','1', '0','" + pedido + "', '" + caf + "', '" + bd.valor("select id from caja where idusuario=" + ControlSoftware.us + " and cia=" + ControlSoftware.cia + " and estado=1") + "','" + tt+ "','" + des + "')", "SELECT @@IDENTITY AS 'id'");
                        System.out.println(cf);
                        bd.EjecutarConsultas("update clientes set saldo=saldo+" + t + " where idcliente=" + codcliente.getText());
                        for (int i = 0; i < detalle.getRowCount(); i++) {
                            String v = bd.valor("select precio1 from producto where idproducto=" + detalle.getValueAt(i, 0));
                            bd.EjecutarConsultas("INSERT INTO `detalle_factura`(`idfactura`, `idproducto`, `cantidad`, `preciounitario`, `preciovendido`, `costo`, `total`, `fechadetalle`, `idusuario`, `observacion`, `estado`, `cia`) VALUES ('" + cf + "','" + detalle.getValueAt(i, 0) + "','" + detalle.getValueAt(i, 3) + "','" + v + "','" + detalle.getValueAt(i, 5) + "','" + detalle.getValueAt(i, 4) + "','" + detalle.getValueAt(i, 6) + "','" + date.format(formatter) + "','" + ControlSoftware.us + "','" + detalle.getValueAt(i, 7) + "','1','" + ControlSoftware.cia + "')");
                            insertartransaccion("" + cf, "2", Double.parseDouble(bd.valor("select existencia from producto where idproducto=" + detalle.getValueAt(i, 0))), -Double.parseDouble("" + detalle.getValueAt(i, 3)), Double.parseDouble(bd.valor("select existencia from producto where idproducto=" + detalle.getValueAt(i, 0))) - Double.parseDouble("" + detalle.getValueAt(i, 3)), "" + detalle.getValueAt(i, 0));

                            bd.EjecutarConsultas("update producto set existencia=existencia-" + detalle.getValueAt(i, 3) + " where idproducto=" + detalle.getValueAt(i, 0));
                            if (!pedido.equals("0")) {
                                if (bd.versiesta("select idproducto from detalle_orden_de_pedido where idproducto=" + detalle.getValueAt(i, 0) + " and id_orden_de_pedido=" + pedido + " and cia=" + ControlSoftware.cia)) {
                                    bd.EjecutarConsultas("update producto set reserva=reserva-" + bd.valor("select cantidad from detalle_orden_de_pedido where idproducto=" + detalle.getValueAt(i, 0) + " and id_orden_de_pedido=" + pedido + " and cia=" + ControlSoftware.cia) + " where idproducto=" + detalle.getValueAt(i, 0));
                                }
                            }

                        }
                        if (!pedido.equals("0")) {
                            bd.EjecutarConsultas("update orden_de_pedido set estado=2 where idorden=" + pedido);
                        }
                        if (!pedido.equals("0")) {
                            dispose();
                            mantenimiento_ordenes l1 = new mantenimiento_ordenes();
                            Dimension internalFrameSize = l1.getSize();
                            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                            l1.setLocation((screenSize.width - internalFrameSize.width) / 2,
                                    (screenSize.height - internalFrameSize.height) / 2);
                            DESKSOFTWARE.escritorio.add(l1);
                            l1.show();
                        } else {
                            dispose();
                            mantenimiento_facturas l1 = new mantenimiento_facturas();
                            Dimension internalFrameSize = l1.getSize();
                            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                            l1.setLocation((screenSize.width - internalFrameSize.width) / 2,
                                    (screenSize.height - internalFrameSize.height) / 2);
                            DESKSOFTWARE.escritorio.add(l1);
                            l1.show();
                        }

                        try {
                            if (l == 0) {
                                try {
                                    NumeroEnLetras n = new NumeroEnLetras();
                                    BaseDeDatos con = new BaseDeDatos();
                                    Connection conn = con.getConexion();
                                    // Cargar el reporte desde el archivo .jasper
                                    String path = "/facturaproforma.jasper";
                                    InputStream inputStream = getClass().getResourceAsStream(path);
                                    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
                                    Map<String, Object> parametros = new HashMap<>();
                                    parametros.put("factura", cf);
                                    parametros.put("MONTO", n.Convertir(t, true));

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
                                    String path = "/facturaoriginal.jasper";
                                    InputStream inputStream = getClass().getResourceAsStream(path);
                                    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
                                    Map<String, Object> parametros = new HashMap<>();
                                    parametros.put("factura", cf);
                                    parametros.put("MONTO", n.Convertir(t, true));
                                    parametros.put("cia", ControlSoftware.cia);
                                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);
                                    JasperViewer viewer = new JasperViewer(jasperPrint, false);
                                    viewer.setVisible(true);
                                } catch (JRException ex) {
                                    System.out.println("Error al generar el reporte: " + ex.getMessage());
                                }
                            }

                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error: " + e.getMessage());
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
    }//GEN-LAST:event_facturaKeyTyped

    private void facturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_facturaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_facturaKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (!pedido.equals("0")) {
            dispose();
            mantenimiento_ordenes l1 = new mantenimiento_ordenes();
            Dimension internalFrameSize = l1.getSize();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            l1.setLocation((screenSize.width - internalFrameSize.width) / 2,
                    (screenSize.height - internalFrameSize.height) / 2);
            DESKSOFTWARE.escritorio.add(l1);
            l1.show();
        } else {
            dispose();
            mantenimiento_facturas l1 = new mantenimiento_facturas();
            Dimension internalFrameSize = l1.getSize();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            l1.setLocation((screenSize.width - internalFrameSize.width) / 2,
                    (screenSize.height - internalFrameSize.height) / 2);
            DESKSOFTWARE.escritorio.add(l1);
            l1.show();
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void cpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpActionPerformed
        // TODO add your handling code here:
        if (bd.valor("select max(cast(numerofactura as unsigned))+1 as maximo from factura where cia=" + ControlSoftware.cia + " and tipo=0") == null) {
            factura.setText("1");
        } else {
            factura.setText(String.format("%.0f", (Double.parseDouble(bd.valor("select max(cast(numerofactura as unsigned))+1 as maximo from factura where cia=" + ControlSoftware.cia + " and tipo=0")))));
        }

    }//GEN-LAST:event_cpActionPerformed

    private void originalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_originalActionPerformed
        // TODO add your handling code here:
        if (bd.valor("select max(cast(numerofactura as unsigned))+1 as maximo from factura where cia=" + ControlSoftware.cia + " and tipo=1") == null) {
            factura.setText("1");
        } else {
            factura.setText(String.format("%.0f", (Double.parseDouble(bd.valor("select max(cast(numerofactura as unsigned))+1 as maximo from factura where cia=" + ControlSoftware.cia + " and tipo=1")))));
        }

    }//GEN-LAST:event_originalActionPerformed

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

    private void codclienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_codclienteFocusLost
        // TODO add your handling code here:
        if (bd.valor("select nombre from clientes where idcliente=" + codcliente.getText() + " and cia=" + ControlSoftware.cia).equals("")) {
            JOptionPane.showMessageDialog(null, "No existe!");
            codcliente.setText("0");
            descliente.setText("seleccione");

        } else {
            descliente.setText("" + bd.valor("select nombre from clientes where idcliente=" + codcliente.getText() + " and cia=" + ControlSoftware.cia));
        }

    }//GEN-LAST:event_codclienteFocusLost

    private void aggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aggActionPerformed
        // TODO add your handling code here:
        seleccionar_producto1 l = new seleccionar_producto1();
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

        if (!pedido.equals("0")) {
            if (bd.versiesta("select idproducto from detalle_orden_de_pedido where idproducto=" + idprod.getText() + " and id_orden_de_pedido=" + pedido + " and cia=" + ControlSoftware.cia)) {
                existencia += Double.parseDouble(bd.valor("select cantidad from detalle_orden_de_pedido where idproducto=" + idprod.getText() + " and id_orden_de_pedido=" + pedido + " and cia=" + ControlSoftware.cia));
            }
        }
        double qty = Double.parseDouble(cantidad.getText());
        double pv = Double.parseDouble(pventa.getText());
        if (existencia < qty) {
            JOptionPane.showMessageDialog(null, "La existencia es menor que lo que está solicitando");
            return;
        }
        double pc = Double.parseDouble(bd.valor("select preciocompra from producto where idproducto=" + idprod.getText()));
        if (pv < pc) {
            JOptionPane.showMessageDialog(null, "Se hace énfasis en que el precio de venta es menor que el costo, para proceder ingrese credenciales.");
            Ingreso_de_credenciales dialog = new Ingreso_de_credenciales("78");
            dialog.setModal(true);
            dialog.setVisible(true);  // AQUI DEBE ESPERAR A QUE INGRESE LOS DATOS PARA HACER LA SIGUIENTE CONDICION
            if (dialog.paso == false) {
                return;
            }
        }
        String arr[] = new String[8];
        arr[0] = idprod.getText();
        arr[1] = codprod.getText();
        arr[2] = desprod.getText();
        arr[3] = cantidad.getText();
        arr[4] = bd.valor("select preciocompra from producto where idproducto=" + idprod.getText());
        arr[5] = pventa.getText();
        arr[6] = "" + String.format("%.2f", (Double.parseDouble(cantidad.getText()) * Double.parseDouble(pventa.getText())));
        arr[7] = "" + obs.getText();

        modelo.addRow(arr);

        calcular();
        limpiar();
    }//GEN-LAST:event_jButton6ActionPerformed
    public void calcular() {
        DecimalFormat formatea = new DecimalFormat("###,###.##",new DecimalFormatSymbols(Locale.ENGLISH));

        double sum = 0.0;
        double sum2 = 0.0;
        for (int i = 0; i < detalle.getRowCount(); i++) {
            sum += Double.parseDouble("" + detalle.getValueAt(i, 6));

        }
        total.setText("" + formatea.format(sum));
        totalfinal.setText("" + formatea.format(sum));
        System.err.println(total.getText());
       // System.err.println(""+new BigDecimal(total.getText()).doubleValue());
       // descuento.setText("" + (new BigDecimal(total.getText().replace(',', '_')).doubleValue() - new BigDecimal(totalfinal.getText().replace(',', '_')).doubleValue()));
        descuento.setText("0.00");
       subtotal.setText("" + formatea.format((sum / 1.15)));
        isv.setText("" + formatea.format((sum - (sum / 1.15))));
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
    }//GEN-LAST:event_cantidadKeyTyped

    private void pventaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pventaKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == evt.VK_V)) {
            // Evitar la acción de pegar
            evt.consume();
        }
    }//GEN-LAST:event_pventaKeyPressed

    private void pventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pventaKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        if (!(key == '0' || key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9' || key == '.') || (key == '.' && pventa.getText().contains("."))) {

            evt.consume();
        }
        if (pventa.getText().length() > 29) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_pventaKeyTyped

    private void vendItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_vendItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_vendItemStateChanged

    private void artKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_artKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_artKeyReleased

    private void artKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_artKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_artKeyTyped

    private void codclienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codclienteKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == evt.VK_V)) {
            // Evitar la acción de pegar
            evt.consume();
        }
    }//GEN-LAST:event_codclienteKeyPressed

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

    private void obsfactKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_obsfactKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == evt.VK_V)) {
            // Evitar la acción de pegar
            evt.consume();
        }
    }//GEN-LAST:event_obsfactKeyPressed

    private void totalfinalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalfinalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_totalfinalKeyReleased

    private void totalfinalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalfinalKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        if (!(key == '0' || key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9' || key == '.') || (key == '.' && totalfinal.getText().contains("."))) {
            evt.consume();
        }
        if (totalfinal.getText().length() > 29) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_totalfinalKeyTyped

    private void descuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descuentoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_descuentoKeyReleased

    private void descuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descuentoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_descuentoKeyTyped

    private void totalfinalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_totalfinalFocusLost
        // TODO add your handling code here:
        if (totalfinal.getText().isEmpty()) {
            totalfinal.setText(total.getText());
            descuento.setText("0.0");
            

        } else if (Double.parseDouble(totalfinal.getText().replace(",", "")) > Double.parseDouble(total.getText().replace(",", ""))) {
            totalfinal.setText(total.getText());
            descuento.setText("" + (new BigDecimal(total.getText().replace(",", "")).doubleValue() - new BigDecimal(totalfinal.getText().replace(",", "")).doubleValue()));

        } else {
            descuento.setText("" + (new BigDecimal(total.getText().replace(",", "")).doubleValue() - new BigDecimal(totalfinal.getText().replace(",", "")).doubleValue()));

        }
    }//GEN-LAST:event_totalfinalFocusLost

    private void fechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fechaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaKeyReleased

    private void fechaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            evt.consume();

        }
    }//GEN-LAST:event_fechaMouseClicked

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
            java.util.logging.Logger.getLogger(ingresarfactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ingresarfactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ingresarfactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ingresarfactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ingresarfactura("0").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agg;
    public static javax.swing.JTextField art;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    public static javax.swing.JTextField cantidad;
    public static javax.swing.JTextField codcliente;
    public static javax.swing.JTextField codprod;
    public static javax.swing.JRadioButton contado;
    public static javax.swing.JTextField costo;
    public static javax.swing.JRadioButton cp;
    public static javax.swing.JRadioButton credito;
    public static javax.swing.JTextField descliente;
    public static javax.swing.JTextField descuento;
    public static javax.swing.JTextField desprod;
    public static javax.swing.JTable detalle;
    public static javax.swing.JTextField existencia;
    public static javax.swing.JTextField factura;
    public static com.toedter.calendar.JDateChooser fecha;
    public static javax.swing.JTextField idprod;
    public static javax.swing.JTextField isv;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea obs;
    public static javax.swing.JTextArea obsfact;
    public static javax.swing.JRadioButton original;
    public static javax.swing.JTextField p1;
    public static javax.swing.JTextField p2;
    public static javax.swing.JTextField p3;
    public static javax.swing.JTextField pventa;
    public static javax.swing.JTextField subtotal;
    public static javax.swing.JTextField total;
    public static javax.swing.JTextField totalfinal;
    public static javax.swing.JComboBox<String> vend;
    // End of variables declaration//GEN-END:variables
}
