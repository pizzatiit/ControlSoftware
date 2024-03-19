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
public class mantenimiento_recibos_proveedores extends javax.swing.JInternalFrame {

    /**
     * Creates new form seleccionarfactura
     */
    BaseDeDatos bd = new BaseDeDatos();

     

    public mantenimiento_recibos_proveedores() {
        super("Usuario: " + new BaseDeDatos().valor("select user from usuario where cod_user=" + ControlSoftware.us) + ", Sucursal: " + new BaseDeDatos().valor("select company from company where id_company=" + ControlSoftware.cia));

        initComponents();

        bd.LlenarTabla("select r.id_recibo AS COD, r.numero_recibo AS NRECIBO, CASE r.tipo WHEN '1' THEN 'REC-PROVEEDOR' END AS TIPO, P.PROVEEDOR as PROVEEDOR, r.total as TOTAL, r.fecha as FECHA_EMISION, r.fechaingreso as FECHA_INGRESO, CASE r.estado WHEN '0' THEN 'ANULADO' WHEN '1' THEN 'ACTIVO' END as ESTADO  FROM recibopago_proveedor r inner join proveedor p on r.idproveedor=p.idproveedor where r.tipo=1 and r.cia=" + ControlSoftware.cia + " order by r.fechaingreso desc", Productos);
        Productos.setDefaultRenderer(Object.class, new MiRendererReciboProveedor());
//        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add72.png")));
//        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh72.png")));
//        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel72.png")));
//        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer72.png")));
//        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/eliminar72.png")));
//         
//        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/show72.png")));
//        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back72.png")));
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
        jPanel4 = new javax.swing.JPanel();
        cliente = new javax.swing.JTextField();
        pcliente = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Productos = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
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
                .addComponent(dnumero)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(dfecha, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jPanel4.setBackground(new java.awt.Color(195, 180, 225));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "POR CLIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cliente)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pcliente.setBackground(new java.awt.Color(195, 180, 225));
        buttonGroup1.add(pcliente);
        pcliente.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        pcliente.setForeground(new java.awt.Color(0, 0, 0));
        pcliente.setText("POR CLIENTE");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(todos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pcliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnumero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pfecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ninguno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pcliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(todos)))
                .addGap(7, 7, 7)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        jLabel10.setText("MANTENIMIENTO DE RECIBOS PROVEEDOR");

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

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 0, 0));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete24.png"))); // NOI18N
        jButton6.setText("ELIMINAR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6);

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel24.png"))); // NOI18N
        jButton3.setText("ANULAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3);

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nuevo24.png"))); // NOI18N
        jButton2.setText("NUEVO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2);

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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 784, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                bd.LlenarTabla("select r.id_recibo AS COD, r.numero_recibo AS NRECIBO, CASE r.tipo WHEN '0' THEN 'REC' WHEN '2' THEN 'REC-CTD' END AS TIPO, c.nombre as CLIENTE, v.vendedor as VENDEDOR, r.total as TOTAL, r.fecha as FECHA_EMISION, r.fechaingreso as FECHA_INGRESO, CASE r.estado WHEN '0' THEN 'ANULADO' WHEN '1' THEN 'ACTIVO' END as ESTADO  FROM recibopago r inner join vendedor v on r.idvendedor=v.id_vendedor inner join clientes c on r.idcliente=c.idcliente where (r.tipo=0 or r.tipo=2) and r.cia=" + ControlSoftware.cia + " order by r.fechaingreso desc", Productos);

            } else if (pnumero.isSelected() && !dnumero.getText().isEmpty() && !hnumero.getText().isEmpty()) {
                bd.LlenarTabla("select r.id_recibo AS COD, r.numero_recibo AS NRECIBO, CASE r.tipo WHEN '0' THEN 'REC' WHEN '2' THEN 'REC-CTD' END AS TIPO, c.nombre as CLIENTE, v.vendedor as VENDEDOR, r.total as TOTAL, r.fecha as FECHA_EMISION, r.fechaingreso as FECHA_INGRESO, CASE r.estado WHEN '0' THEN 'ANULADO' WHEN '1' THEN 'ACTIVO' END as ESTADO  FROM recibopago r inner join vendedor v on r.idvendedor=v.id_vendedor inner join clientes c on r.idcliente=c.idcliente where (r.tipo=0 or r.tipo=2) and r.cia=" + ControlSoftware.cia + " and r.numero_recibo between " + dnumero.getText() + " and " + hnumero.getText() + " order by r.fechaingreso desc", Productos);
            } else if (pfecha.isSelected() && !(date1 == null) && !(date2 == null)) {
                if (date2.compareTo(date1) == -1) {
                    JOptionPane.showMessageDialog(null, "La fecha final no puede ser mayor a la inicial.");
                } else {
                    bd.LlenarTabla("select r.id_recibo AS COD, r.numero_recibo AS NRECIBO, CASE r.tipo WHEN '0' THEN 'REC' WHEN '2' THEN 'REC-CTD' END AS TIPO, c.nombre as CLIENTE, v.vendedor as VENDEDOR, r.total as TOTAL, r.fecha as FECHA_EMISION, r.fechaingreso as FECHA_INGRESO, CASE r.estado WHEN '0' THEN 'ANULADO' WHEN '1' THEN 'ACTIVO' END as ESTADO  FROM recibopago r inner join vendedor v on r.idvendedor=v.id_vendedor inner join clientes c on r.idcliente=c.idcliente where (r.tipo=0 or r.tipo=2) and r.cia=" + ControlSoftware.cia + " and r.fechaingreso between '" + Date_Format.format(dfecha.getDate()) + "' and '" + Date_Format.format(hfecha.getDate()) + "'" + " order by r.fechaingreso desc", Productos);
                }
            } else if (pcliente.isSelected() && !cliente.getText().isEmpty()) {
                bd.LlenarTabla("select r.id_recibo AS COD, r.numero_recibo AS NRECIBO, CASE r.tipo WHEN '0' THEN 'REC' WHEN '2' THEN 'REC-CTD' END AS TIPO, c.nombre as CLIENTE, v.vendedor as VENDEDOR, r.total as TOTAL, r.fecha as FECHA_EMISION, r.fechaingreso as FECHA_INGRESO, CASE r.estado WHEN '0' THEN 'ANULADO' WHEN '1' THEN 'ACTIVO' END as ESTADO  FROM recibopago r inner join vendedor v on r.idvendedor=v.id_vendedor inner join clientes c on r.idcliente=c.idcliente where (r.tipo=0 or r.tipo=2) and r.cia=" + ControlSoftware.cia + " and c.nombre like '%" + cliente.getText() + "%'" + " order by r.fechaingreso desc", Productos);
            } else if (todos.isSelected() && !dnumero.getText().isEmpty() && !hnumero.getText().isEmpty() && !cliente.getText().isEmpty() && !dfecha.getDate().equals("") && !hfecha.getDate().equals("")) {
                if (date2.compareTo(date1) == -1) {
                    JOptionPane.showMessageDialog(null, "La fecha final no puede ser mayor a la inicial.");
                } else {
                    bd.LlenarTabla("select r.id_recibo AS COD, r.numero_recibo AS NRECIBO, CASE r.tipo WHEN '0' THEN 'REC' WHEN '2' THEN 'REC-CTD' END AS TIPO, c.nombre as CLIENTE, v.vendedor as VENDEDOR, r.total as TOTAL, r.fecha as FECHA_EMISION, r.fechaingreso as FECHA_INGRESO, CASE r.estado WHEN '0' THEN 'ANULADO' WHEN '1' THEN 'ACTIVO' END as ESTADO  FROM recibopago r inner join vendedor v on r.idvendedor=v.id_vendedor inner join clientes c on r.idcliente=c.idcliente where (r.tipo=0 or r.tipo=2) and r.cia=" + ControlSoftware.cia + " and c.nombre like '%" + cliente.getText() + "%'" + " and r.fechaingreso between '" + Date_Format.format(dfecha.getDate()) + "' and '" + Date_Format.format(hfecha.getDate()) + "'" + " and r.numero_recibo between " + dnumero.getText() + " and " + hnumero.getText() + " order by r.fechaingreso desc", Productos);
                }

            } else {
                bd.LlenarTabla("select r.id_recibo AS COD, r.numero_recibo AS NRECIBO, CASE r.tipo WHEN '0' THEN 'REC' WHEN '2' THEN 'REC-CTD' END AS TIPO, c.nombre as CLIENTE, v.vendedor as VENDEDOR, r.total as TOTAL, r.fecha as FECHA_EMISION, r.fechaingreso as FECHA_INGRESO, CASE r.estado WHEN '0' THEN 'ANULADO' WHEN '1' THEN 'ACTIVO' END as ESTADO  FROM recibopago r inner join vendedor v on r.idvendedor=v.id_vendedor inner join clientes c on r.idcliente=c.idcliente where (r.tipo=0 or r.tipo=2) and r.cia=" + ControlSoftware.cia + " order by r.fechaingreso desc", Productos);

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
         String perm="18";
        if (!bd.versiesta("select * from role_usuario inner join role on role.id=role_usuario.roleid inner join usuario on usuario.cod_user=role_usuario.usuarioid inner join role_permiso on role_permiso.roleid=role.id inner join permisos on permisos.id=role_permiso.permisoid where permisos.id=" + perm + " and role_usuario.cia=" + ControlSoftware.cia + " and usuario.cod_user=" + ControlSoftware.us)) {
            JOptionPane.showMessageDialog(null, "Usted no tiene acceso a ingresar pagos.");
            return;
        }
        recibo1 l = new recibo1("", "");
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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
          String perm = "19";
        if (!bd.versiesta("select * from role_usuario inner join role on role.id=role_usuario.roleid inner join usuario on usuario.cod_user=role_usuario.usuarioid inner join role_permiso on role_permiso.roleid=role.id inner join permisos on permisos.id=role_permiso.permisoid where permisos.id=" + perm + " and role_usuario.cia=" + ControlSoftware.cia + " and usuario.cod_user=" + ControlSoftware.us)) {
            JOptionPane.showMessageDialog(null, "Usted no tiene permiso de anular pagos.");
            return;
        }
        if (Productos.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Ninguna fila seleccionada!");
            return;
        }
        try {

            if (Productos.getValueAt(Productos.getSelectedRow(), 8).equals("ANULADO")) {
                JOptionPane.showMessageDialog(null, "No se puede anular este pago porque ya fué anulado.");
                return;
            }
            if (Productos.getValueAt(Productos.getSelectedRow(), 2).equals("REC-CTD")) {
                JOptionPane.showMessageDialog(null, "No se puede anular este pago porque es de contado, debe anular la factura directamente.");
                return;
            }
            String rec = "" + Productos.getValueAt(Productos.getSelectedRow(), 0);
            int opc = 0;
            opc = JOptionPane.showConfirmDialog(null, "Desea anular este recibo? se eliminarán todos los pagos a todas las facturas.");
            if (opc == 0) {
                bd.LlenarTabla("select * from detalle_recibopago where idrecibo=" + rec, temp);
                for (int i = 0; i < temp.getRowCount(); i++) {
                    bd.EjecutarConsultas("update factura set saldo=saldo+" + temp.getValueAt(i, 3) + " where idfactura=" + temp.getValueAt(i, 2));
                    if (bd.valor("select saldo from factura where idfactura=" + temp.getValueAt(i, 2)).equals(bd.valor("select total from factura where idfactura=" + temp.getValueAt(i, 2)))) {
                        bd.EjecutarConsultas("update factura set estado=1 where idfactura=" + temp.getValueAt(i, 2));
                    }
                    if (!bd.valor("select saldo from factura where idfactura=" + temp.getValueAt(i, 2)).equals(bd.valor("select total from factura where idfactura=" + temp.getValueAt(i, 2))) && !bd.valor("select saldo from factura where idfactura=" + temp.getValueAt(i, 2)).equals("0.00")) {
                        bd.EjecutarConsultas("update factura set estado=2 where idfactura=" + temp.getValueAt(i, 2));
                    }
                    if (bd.valor("select saldo from factura where idfactura=" + temp.getValueAt(i, 2)).equals("0.00")) {
                        bd.EjecutarConsultas("update factura set estado=3 where idfactura=" + temp.getValueAt(i, 2));
                    }
                    System.out.println("realizado");
                    bd.EjecutarConsultas("update detalle_recibopago set nuevosaldo=nuevosaldo+abono, abono=0, estado=0 where iddetalle=" + temp.getValueAt(i, 0));
                }
                String usuario = bd.valor("select user from usuario where cod_user=" + ControlSoftware.us);
                bd.EjecutarConsultas("update recibopago_datos set estado=0, observacion='anulado por: " + usuario + ", fecha:" + LocalDateTime.now() + "' where idrecibo=" + rec + " and cia=" + ControlSoftware.cia);
                String tot = "" + bd.valor("select total from recibopago where id_recibo=" + rec);
                String cte = "" + bd.valor("select idcliente from recibopago where id_recibo=" + rec);
                bd.EjecutarConsultas("update clientes set saldo=saldo+" + tot + " where idcliente=" + cte);
                bd.EjecutarConsultas("update recibopago set total=0, estado=0 where id_recibo=" + rec);
                JOptionPane.showMessageDialog(null, "Recibo anulado con éxito");
            }
            jButton1.doClick();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
          String perm="20";
        if (!bd.versiesta("select * from role_usuario inner join role on role.id=role_usuario.roleid inner join usuario on usuario.cod_user=role_usuario.usuarioid inner join role_permiso on role_permiso.roleid=role.id inner join permisos on permisos.id=role_permiso.permisoid where permisos.id=" + perm + " and role_usuario.cia=" + ControlSoftware.cia + " and usuario.cod_user=" + ControlSoftware.us)) {
            JOptionPane.showMessageDialog(null, "Usted no tiene acceso a eliminar pagos.");
            return;
        }
        if (Productos.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Ninguna fila seleccionada!");
            return;
        }
        try {
            if (Productos.getValueAt(Productos.getSelectedRow(), 8).equals("ACTIVO")) {
                JOptionPane.showMessageDialog(null, "No se puede eliminar este pago porque no ha sido anulado, debe anularlo primero.");
                return;
            }
            if (Productos.getValueAt(Productos.getSelectedRow(), 2).equals("REC-CTD")) {
                JOptionPane.showMessageDialog(null, "No se puede eliminar este pago porque es de contado, debe eliminar la factura directamente.");
                return;
            }
            int opc = 0;
            opc = JOptionPane.showConfirmDialog(null, "Desea eliminar este recibo? se eliminarán todos los pagos a todas las facturas.");
            if (opc == 0) {

                 
                if (Productos.getValueAt(Productos.getSelectedRow(), 2).equals("2")) {
                    JOptionPane.showMessageDialog(null, "No se puede anular este pago porque es de contado, debe anular la factura directamente.");
                    return;
                }
                String r = "" + Productos.getValueAt(Productos.getSelectedRow(), 0);
                bd.EjecutarConsultas("delete from detalle_recibopago where idrecibo=" + r);
                bd.EjecutarConsultas("delete from recibopago_datos where idrecibo=" + r);
                bd.EjecutarConsultas("delete from recibopago where id_recibo=" + r);
                jButton1.doClick();
                JOptionPane.showMessageDialog(null, "Documento eliminado con éxito");

            }
        } catch (Exception e) {
        }


    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        ModuloProveedores l = new ModuloProveedores();
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
          String perm="21";
        if (!bd.versiesta("select * from role_usuario inner join role on role.id=role_usuario.roleid inner join usuario on usuario.cod_user=role_usuario.usuarioid inner join role_permiso on role_permiso.roleid=role.id inner join permisos on permisos.id=role_permiso.permisoid where permisos.id=" + perm + " and role_usuario.cia=" + ControlSoftware.cia + " and usuario.cod_user=" + ControlSoftware.us)) {
            JOptionPane.showMessageDialog(null, "Usted no tiene acceso a reimprimir pagos.");
            return;
        }
        if (Productos.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Ninguna fila seleccionada!");
            return;
        }
        
        try {
            BaseDeDatos con = new BaseDeDatos();
            Connection conn = con.getConexion();
            // Cargar el reporte desde el archivo .jasper
            String path = "/PagoFactura.jasper";
            InputStream inputStream = getClass().getResourceAsStream(path);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id_recibo", Productos.getValueAt(Productos.getSelectedRow(), 0));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);

            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setVisible(true);

        } catch (JRException ex) {
            System.out.println("Error al generar el reporte: " + ex.getMessage());
        }


    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(mantenimiento_recibos_proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mantenimiento_recibos_proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mantenimiento_recibos_proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mantenimiento_recibos_proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mantenimiento_recibos_proveedores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Productos;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField cliente;
    private com.toedter.calendar.JDateChooser dfecha;
    private javax.swing.JTextField dnumero;
    private com.toedter.calendar.JDateChooser hfecha;
    private javax.swing.JTextField hnumero;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton ninguno;
    private javax.swing.JRadioButton pcliente;
    private javax.swing.JRadioButton pfecha;
    private javax.swing.JRadioButton pnumero;
    private javax.swing.JTable temp;
    private javax.swing.JRadioButton todos;
    // End of variables declaration//GEN-END:variables
}
