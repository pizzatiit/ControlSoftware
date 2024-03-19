package com.mycompany.controlsoftware;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ING. JUNIOR MORALES
 */
public class mantenimiento_ajustes_de_inventario extends javax.swing.JInternalFrame {

    /**
     * Creates new form seleccionarfactura
     */
    BaseDeDatos bd = new BaseDeDatos();

     

    public mantenimiento_ajustes_de_inventario() {
        super("Usuario: " + new BaseDeDatos().valor("select user from usuario where cod_user=" + ControlSoftware.us) + ", Sucursal: " + new BaseDeDatos().valor("select company from company where id_company=" + ControlSoftware.cia));

        initComponents();

        bd.LlenarTabla("select A.ID as COD, A.N_DOCUMENTO AS N_AJUSTE, CASE A.TIPO WHEN '1' THEN 'SALIDA' WHEN '2' THEN 'ENTRADA' END as TIPOAJUSTE, A.FECHA as FECHA, U.user as USUARIO_INGRESO, A.observacion as OBS, CASE A.estado WHEN '0' THEN 'ANULADA' WHEN '1' THEN 'INGRESADA' END AS ESTADO FROM AJUSTEINVENTARIO A inner join Usuario u on u.cod_user=a.usuarioid where A.cia=" + ControlSoftware.cia + " order by A.fecha desc", Productos);
        Productos.setDefaultRenderer(Object.class, new MiRendererAjustesInventario());
 
        temp.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        pfecha = new javax.swing.JRadioButton();
        ninguno = new javax.swing.JRadioButton();
        todos = new javax.swing.JRadioButton();
        pnumero = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        dnumero = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        hnumero = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        dfecha = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        hfecha = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        Productos = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        temp = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(195, 180, 225));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "FILTROS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        pfecha.setBackground(new java.awt.Color(195, 180, 225));
        buttonGroup1.add(pfecha);
        pfecha.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        pfecha.setForeground(new java.awt.Color(0, 0, 0));
        pfecha.setText("POR FECHA");

        ninguno.setBackground(new java.awt.Color(195, 180, 225));
        buttonGroup1.add(ninguno);
        ninguno.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        ninguno.setForeground(new java.awt.Color(0, 0, 0));
        ninguno.setSelected(true);
        ninguno.setText("NINGUNO");

        todos.setBackground(new java.awt.Color(195, 180, 225));
        buttonGroup1.add(todos);
        todos.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        todos.setForeground(new java.awt.Color(0, 0, 0));
        todos.setText("TODOS");

        pnumero.setBackground(new java.awt.Color(195, 180, 225));
        buttonGroup1.add(pnumero);
        pnumero.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        pnumero.setForeground(new java.awt.Color(0, 0, 0));
        pnumero.setText("POR NUMERO");

        jPanel2.setBackground(new java.awt.Color(195, 180, 225));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "POR NUMERO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("DESDE");

        dnumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dnumeroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dnumeroKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("HASTA");

        hnumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                hnumeroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                hnumeroKeyTyped(evt);
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
                .addComponent(dnumero, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(dnumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(hnumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(195, 180, 225));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "POR FECHA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("DESDE");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("HASTA");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(dfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hfecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(dfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnumero, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pfecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ninguno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(todos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ninguno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pfecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnumero)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(todos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
        Productos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        Productos.setSelectionBackground(new java.awt.Color(0, 0, 0));
        Productos.setSelectionForeground(new java.awt.Color(255, 255, 255));
        Productos.getTableHeader().setReorderingAllowed(false);
        Productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProductosMouseClicked(evt);
            }
        });
        Productos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ProductosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(Productos);

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("MANTENIMIENTO DE AJUSTES DE INVENTARIO");

        jPanel5.setBackground(new java.awt.Color(195, 180, 225));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setForeground(new java.awt.Color(0, 0, 0));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 0, 0));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back24.png"))); // NOI18N
        jButton7.setText("REGRESAR");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton7);

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(0, 0, 0));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print24.png"))); // NOI18N
        jButton8.setText("IMPRIMIR");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton8);

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PLUS24.png"))); // NOI18N
        jButton2.setText("AJUSTE DE ENTRADA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2);

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 0, 0));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MINUS24.png"))); // NOI18N
        jButton4.setText("AJUSTE DE SALIDA");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4);

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
        jPanel5.add(jButton1);

        temp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        temp.setEnabled(false);
        jScrollPane2.setViewportView(temp);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
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
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {

            SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
            Date date1;
            date1 = dfecha.getDate();
            Date date2;
            date2 = hfecha.getDate();
            if (ninguno.isSelected()) {
                bd.LlenarTabla("select A.ID as COD, A.N_DOCUMENTO AS N_AJUSTE, , CASE A.TIPO WHEN '1' THEN 'SALIDA' WHEN '2' THEN 'ENTRADA' END as TIPOAJUSTE, A.FECHA as FECHA, U.user as USUARIO_INGRESO, A.observacion as OBS, CASE A.estado WHEN '0' THEN 'ANULADA' WHEN '1' THEN 'INGRESADA' END AS ESTADO FROM AJUSTEINVENTARIO A inner join Usuario u on u.cod_user=a.usuarioid where A.cia=" + ControlSoftware.cia +  " order by A.fecha  desc", Productos);

            } else if (pnumero.isSelected() && !dnumero.getText().isEmpty() && !hnumero.getText().isEmpty()) {
                bd.LlenarTabla("select A.ID as COD, A.N_DOCUMENTO AS N_AJUSTE, , CASE A.TIPO WHEN '1' THEN 'SALIDA' WHEN '2' THEN 'ENTRADA' END as TIPOAJUSTE, A.FECHA as FECHA, U.user as USUARIO_INGRESO, A.observacion as OBS, CASE A.estado WHEN '0' THEN 'ANULADA' WHEN '1' THEN 'INGRESADA' END AS ESTADO FROM AJUSTEINVENTARIO A inner join Usuario u on u.cod_user=a.usuarioid where A.cia=" + ControlSoftware.cia + " and A.N_DOCUMENTO between " + dnumero.getText() + " and " + hnumero.getText() + " order by A.fecha  desc", Productos);
            } else if (pfecha.isSelected() && !(date1 == null) && !(date2 == null)) {
                if (date2.compareTo(date1) == -1) {
                    JOptionPane.showMessageDialog(null, "La fecha final no puede ser mayor a la inicial.");
                } else {
                    bd.LlenarTabla("select A.ID as COD, A.N_DOCUMENTO AS N_AJUSTE, , CASE A.TIPO WHEN '1' THEN 'SALIDA' WHEN '2' THEN 'ENTRADA' END as TIPOAJUSTE, A.FECHA as FECHA, U.user as USUARIO_INGRESO, A.observacion as OBS, CASE A.estado WHEN '0' THEN 'ANULADA' WHEN '1' THEN 'INGRESADA' END AS ESTADO FROM AJUSTEINVENTARIO A inner join Usuario u on u.cod_user=a.usuarioid where A.cia=" + ControlSoftware.cia + " and A.fecha between '" + Date_Format.format(dfecha.getDate()) + "' and '" + Date_Format.format(hfecha.getDate()) + "'" + " order by A.fecha desc", Productos);
                }
            }   else if (todos.isSelected() && !dnumero.getText().isEmpty() && !hnumero.getText().isEmpty() && !dfecha.getDate().equals("") && !hfecha.getDate().equals("")) {
                if (date2.compareTo(date1) == -1) {
                    JOptionPane.showMessageDialog(null, "La fecha final no puede ser mayor a la inicial.");
                } else {
                    bd.LlenarTabla("select A.ID as COD, A.N_DOCUMENTO AS N_AJUSTE, , CASE A.TIPO WHEN '1' THEN 'SALIDA' WHEN '2' THEN 'ENTRADA' END as TIPOAJUSTE, A.FECHA as FECHA, U.user as USUARIO_INGRESO, A.observacion as OBS, CASE A.estado WHEN '0' THEN 'ANULADA' WHEN '1' THEN 'INGRESADA' END AS ESTADO FROM AJUSTEINVENTARIO A inner join Usuario u on u.cod_user=a.usuarioid where A.cia=" + ControlSoftware.cia + " and A.fecha between '" + Date_Format.format(dfecha.getDate()) + "' and '" + Date_Format.format(hfecha.getDate()) + "'" + " and A.N_DOCUMENTO between " + dnumero.getText() + " and " + hnumero.getText() + " order by A.fecha desc", Productos);
                }

            } else {
                bd.LlenarTabla("select A.ID as COD, A.N_DOCUMENTO AS N_AJUSTE, , CASE A.TIPO WHEN '1' THEN 'SALIDA' WHEN '2' THEN 'ENTRADA' END as TIPOAJUSTE, A.FECHA as FECHA, U.user as USUARIO_INGRESO, A.observacion as OBS, CASE A.estado WHEN '0' THEN 'ANULADA' WHEN '1' THEN 'INGRESADA' END AS ESTADO FROM AJUSTEINVENTARIO A inner join Usuario u on u.cod_user=a.usuarioid where A.cia=" + ControlSoftware.cia + " order by A.fecha desc", Productos);

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProductosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ProductosKeyReleased

    private void ProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProductosMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_ProductosMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         String perm="130";
        if (!bd.versiesta("select * from role_usuario inner join role on role.id=role_usuario.roleid inner join usuario on usuario.cod_user=role_usuario.usuarioid inner join role_permiso on role_permiso.roleid=role.id inner join permisos on permisos.id=role_permiso.permisoid where permisos.id=" + perm + " and role_usuario.cia=" + ControlSoftware.cia + " and usuario.cod_user=" + ControlSoftware.us)) {
            JOptionPane.showMessageDialog(null, "Usted no tiene acceso a ingresar ajustes de entrada.");
            return;
        }
        ajustedeentrada l = new ajustedeentrada();
        Dimension internalFrameSize = l.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        l.setLocation((screenSize.width - internalFrameSize.width) / 2,
                (screenSize.height - internalFrameSize.height) / 2);
        DESKSOFTWARE.escritorio.add(l);
        l.show();
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void dnumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dnumeroKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        if (!(key == '0' || key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_dnumeroKeyTyped

    private void dnumeroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dnumeroKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_dnumeroKeyReleased

    private void hnumeroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hnumeroKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_hnumeroKeyReleased

    private void hnumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hnumeroKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        if (!(key == '0' || key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_hnumeroKeyTyped

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        Inventarios l = new Inventarios();
        Dimension internalFrameSize = l.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        l.setLocation((screenSize.width - internalFrameSize.width) / 2,
                (screenSize.height - internalFrameSize.height) / 2);
        DESKSOFTWARE.escritorio.add(l);
        l.show();
        dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
          String perm="132";
        if (!bd.versiesta("select * from role_usuario inner join role on role.id=role_usuario.roleid inner join usuario on usuario.cod_user=role_usuario.usuarioid inner join role_permiso on role_permiso.roleid=role.id inner join permisos on permisos.id=role_permiso.permisoid where permisos.id=" + perm + " and role_usuario.cia=" + ControlSoftware.cia + " and usuario.cod_user=" + ControlSoftware.us)) {
            JOptionPane.showMessageDialog(null, "Usted no tiene acceso a reimprimir pagos.");
            return;
        }
        if (Productos.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Ninguna fila seleccionada!");
            return;
        }
        
        if(Productos.getValueAt(Productos.getSelectedRow(), 2).equals("ENTRADA")){
                
        try { 
                    BaseDeDatos con = new BaseDeDatos();
                    Connection conn = con.getConexion();
                    // Cargar el reporte desde el archivo .jasper
                    String path = "/ajusteentrada.jasper";
                    InputStream inputStream = getClass().getResourceAsStream(path);
                    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
                    Map<String, Object> parametros = new HashMap<>();
                    parametros.put("codigo", Productos.getValueAt(Productos.getSelectedRow(), 0)); 
                    parametros.put("cia", ControlSoftware.cia);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);
                    JasperViewer viewer = new JasperViewer(jasperPrint, false);
                    viewer.setVisible(true);
                } catch (JRException ex) {
                    System.out.println("Error al generar el reporte: " + ex.getMessage());
                }
        }
        if(Productos.getValueAt(Productos.getSelectedRow(), 2).equals("SALIDA")){
                
         try { 
                    BaseDeDatos con = new BaseDeDatos();
                    Connection conn = con.getConexion();
                    // Cargar el reporte desde el archivo .jasper
                    String path = "/ajustesalida.jasper";
                    InputStream inputStream = getClass().getResourceAsStream(path);
                    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
                    Map<String, Object> parametros = new HashMap<>();
                    parametros.put("codigo", Productos.getValueAt(Productos.getSelectedRow(), 0)); 
                    parametros.put("cia", ControlSoftware.cia);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);
                    JasperViewer viewer = new JasperViewer(jasperPrint, false);
                    viewer.setVisible(true);
                } catch (JRException ex) {
                    System.out.println("Error al generar el reporte: " + ex.getMessage());
                }
        }


    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
         String perm="131";
        if (!bd.versiesta("select * from role_usuario inner join role on role.id=role_usuario.roleid inner join usuario on usuario.cod_user=role_usuario.usuarioid inner join role_permiso on role_permiso.roleid=role.id inner join permisos on permisos.id=role_permiso.permisoid where permisos.id=" + perm + " and role_usuario.cia=" + ControlSoftware.cia + " and usuario.cod_user=" + ControlSoftware.us)) {
            JOptionPane.showMessageDialog(null, "Usted no tiene acceso a ingresar ajustes de salida.");
            return;
        }
        ajustedesalida l = new ajustedesalida();
        Dimension internalFrameSize = l.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        l.setLocation((screenSize.width - internalFrameSize.width) / 2,
                (screenSize.height - internalFrameSize.height) / 2);
        DESKSOFTWARE.escritorio.add(l);
        l.show();
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(mantenimiento_ajustes_de_inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mantenimiento_ajustes_de_inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mantenimiento_ajustes_de_inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mantenimiento_ajustes_de_inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mantenimiento_ajustes_de_inventario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Productos;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dfecha;
    private javax.swing.JTextField dnumero;
    private com.toedter.calendar.JDateChooser hfecha;
    private javax.swing.JTextField hnumero;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton ninguno;
    private javax.swing.JRadioButton pfecha;
    private javax.swing.JRadioButton pnumero;
    private javax.swing.JTable temp;
    private javax.swing.JRadioButton todos;
    // End of variables declaration//GEN-END:variables
}
