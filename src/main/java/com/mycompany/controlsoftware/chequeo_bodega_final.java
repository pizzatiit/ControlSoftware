/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.controlsoftware;

import static com.mycompany.controlsoftware.ingresar_chequeo_pedido.detalle;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
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
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

/**
 *
 * @author Usuario
 */
public class chequeo_bodega_final extends javax.swing.JInternalFrame {
    
    DefaultTableModel modelo;
    /**
     * Creates new form formato
     */
    BaseDeDatos bd = new BaseDeDatos();
    String data = "";
    String v="";
    public chequeo_bodega_final(String id) {
        super("Usuario: " + new BaseDeDatos().valor("select user from usuario where cod_user=" + ControlSoftware.us) + ", Sucursal: " + new BaseDeDatos().valor("select company from company where id_company=" + ControlSoftware.cia));
        
        initComponents();
        detalle.setDefaultRenderer(Object.class, new MiRenderercpedidos());
        
        if (!bd.versiesta("select * from chequeo_bodega where idorden=" + id + " and cia=" + ControlSoftware.cia)) {
            v=bd.doble("INSERT INTO `chequeo_bodega` (`idorden`, `fechainiciochequeo`, `fechafinalchequeo`, `idusuario`, `estado`, `cia`) VALUES (" + id + ", '" + LocalDateTime.now() + "', NULL, '" + ControlSoftware.us + "', '1', '" + ControlSoftware.cia + "')", "SELECT @@IDENTITY AS 'id'");
        }else{
            v=bd.valor("select id from chequeo_bodega where idorden=" + id + " and cia=" + ControlSoftware.cia);
        }
        
        String codcliente = bd.valor("select idcliente from orden_de_pedido where idorden=" + id + " and cia=" + ControlSoftware.cia);
        descliente.setText(bd.valor("select nombre from clientes where idcliente=" + codcliente + " and cia=" + ControlSoftware.cia));
        pedido.setText(bd.valor("select numero_orden from orden_de_pedido where idorden=" + id + " and cia=" + ControlSoftware.cia));
        /*
        String producto_a_agregar[] = bd.Llenar("select id_detalle_pedido_bodega from detalle_pedido_chequeo_bodega where id_pedido_bodega='" + id + "' and cia=" + ControlSoftware.cia + " and idusuario=" + ControlSoftware.us, "id_detalle_pedido_bodega");
        for (int i = 0; i < producto_a_agregar.length; i++) {
            String ar[] = new String[5];
            String producto = bd.valor("select id_producto from detalle_pedido_chequeo_bodega where id_detalle_pedido_bodega=" + producto_a_agregar[i] + " and cia=" + ControlSoftware.cia);
            ar[0] = producto;
            ar[1] = bd.valor("select codigo from producto where idproducto=" + producto + " and cia=" + ControlSoftware.cia);
            ar[2] = bd.valor("select descripcion from producto where idproducto=" + producto + " and cia=" + ControlSoftware.cia);
            ar[3] = bd.valor("select cantidad from detalle_pedido_chequeo_bodega where id_detalle_pedido_bodega=" + producto_a_agregar[i] + " and cia=" + ControlSoftware.cia);
            ar[4] = bd.valor("select idusuario from detalle_pedido_chequeo_bodega where id_detalle_pedido_bodega=" + producto_a_agregar[i] + " and cia=" + ControlSoftware.cia);
            modelo.addRow(ar);
        }
         */
       
        bd.LlenarTabla("select dc.idproducto as IDPRODUCTO, p.codigo AS CODLETRAS, p.descripcion AS DESCRIPCION, dc.cantidad AS CANTIDAD, u.user AS USUARIO from chequeo_bodega_detalle dc inner join producto p on p.idproducto=dc.idproducto inner join usuario u on u.cod_user=dc.idusuario where dc.cia="+ControlSoftware.cia+ " and dc.idusuario="+ControlSoftware.us+" AND dc.idchequeobodega="+v, detalle);
       modelo = (DefaultTableModel) detalle.getModel();
        data = id;
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
        codigo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CHEQUEO EN BODEGA");

        detalle = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        detalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        detalle.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(detalle);
        if (detalle.getColumnModel().getColumnCount() > 0) {
            detalle.getColumnModel().getColumn(0).setHeaderValue("IDPRODUCTO");
            detalle.getColumnModel().getColumn(1).setHeaderValue("CODLETRAS");
            detalle.getColumnModel().getColumn(2).setHeaderValue("DESCRIPCION");
            detalle.getColumnModel().getColumn(3).setHeaderValue("CANTIDAD");
            detalle.getColumnModel().getColumn(4).setHeaderValue("USUARIO");
        }

        descliente.setEditable(false);

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("N° ORDEN");

        pedido.setEditable(false);
        pedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pedidoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pedidoKeyTyped(evt);
            }
        });

        codigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                codigoFocusGained(evt);
            }
        });
        codigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codigoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                codigoKeyReleased(evt);
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
        jButton7.setText("ETIQUETAS");
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(275, 275, 275)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descliente, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addGap(41, 41, 41)
                                .addComponent(pedido, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(descliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(pedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(7, Short.MAX_VALUE))
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

    private void pedidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pedidoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_pedidoKeyReleased

    private void pedidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pedidoKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        if (!(key == '0' || key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_pedidoKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (detalle.getRowCount() < 1) {
            JOptionPane.showMessageDialog(null, "No puede enviarlo vacío");
        }
        try {
            bd.EjecutarConsultas("delete from chequeo_bodega_detalle where idchequeobodega=" + v + " and idusuario=" + ControlSoftware.us + " and cia=" + ControlSoftware.cia);
            SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < detalle.getRowCount(); i++) {
                 bd.EjecutarConsultas("INSERT INTO `chequeo_bodega_detalle` (`idchequeobodega`, `idproducto`, `cantidad`, `idusuario`, `fecha`, `estado`, `cia`) VALUES ('"+v+"', '"+detalle.getValueAt(i, 0)+"', '"+detalle.getValueAt(i, 3)+"', '"+ControlSoftware.us+"', current_timestamp(), '1', "+ControlSoftware.cia+")");
            }
            mantenimiento_chequeo_bodega_final l = new mantenimiento_chequeo_bodega_final();
            Dimension internalFrameSize = l.getSize();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            l.setLocation((screenSize.width - internalFrameSize.width) / 2,
                    (screenSize.height - internalFrameSize.height) / 2);
            DESKSOFTWARE.escritorio.add(l);
            l.show();
            dispose(); 
        } catch (Exception e) {
        }

    }//GEN-LAST:event_jButton2ActionPerformed
    
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
    

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        mantenimiento_chequeo_bodega_final l = new mantenimiento_chequeo_bodega_final();
        Dimension internalFrameSize = l.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        l.setLocation((screenSize.width - internalFrameSize.width) / 2,
                (screenSize.height - internalFrameSize.height) / 2);
        DESKSOFTWARE.escritorio.add(l);
        l.show();
        dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            int c = Integer.parseInt("" + detalle.getValueAt(detalle.getSelectedRow(), 3));
            detalle.setValueAt((c = c - 1), detalle.getSelectedRow(), 3);
            if (detalle.getValueAt(detalle.getSelectedRow(), 3).toString().equals("0")) {
                modelo.removeRow(detalle.getSelectedRow());
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void codigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (bd.versiesta("select * from producto where codigo='" + codigo.getText() + "' and cia=" + ControlSoftware.cia)) {
                
                if (bd.versiesta("select p.idproducto from producto p inner join detalle_orden_de_pedido do on do.idproducto=p.idproducto where p.codigo='" + codigo.getText() + "' and p.cia=" + ControlSoftware.cia)) {
                    String cod = bd.valor("select p.idproducto from producto p inner join detalle_orden_de_pedido do on do.idproducto=p.idproducto where p.codigo='" + codigo.getText() + "' and p.cia=" + ControlSoftware.cia);
                    System.err.println(cod);
                    String des = bd.valor("select p.descripcion from producto p inner join detalle_orden_de_pedido do on do.idproducto=p.idproducto where p.codigo='" + codigo.getText() + "' and p.cia=" + ControlSoftware.cia);
                    System.err.println(des);
                    String us = "" + ControlSoftware.us;
                    System.out.println(ControlSoftware.cia);
                    int u = 0;
                    int temp = -1;
                    for (int i = 0; i < detalle.getRowCount(); i++) {
                        
                        if (detalle.getValueAt(i, 0).equals("" + cod)) { 
                            u = 1;
                            temp = i;
                            
                        }
                    }
                    if (u == 0) {
                        String arr[] = new String[8];
                        arr[0] = cod;
                        arr[1] = codigo.getText();
                        arr[2] = des;
                        arr[3] = "" + 1;
                        arr[4] = us;
                        modelo.addRow(arr);
                        codigo.setText("");
                    }
                    if (u == 1) {
                        
                        if (Double.parseDouble(bd.valor("select cantidad from detalle_orden_de_pedido where idproducto='" + cod + "' and id_orden_de_pedido='" + pedido.getText() + "' and cia=" + ControlSoftware.cia)) == Double.parseDouble("" + detalle.getValueAt(temp, 3))) {
                            JOptionPane.showMessageDialog(null, "Este artículo es el máximo");
                            return;
                        }
                        int c = Integer.parseInt("" + detalle.getValueAt(temp, 3));
                        detalle.setValueAt((c = c + 1), temp, 3);
                        codigo.setText("");
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

    private void codigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_codigoKeyReleased

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

    private void codigoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_codigoFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_codigoFocusGained

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
            java.util.logging.Logger.getLogger(chequeo_bodega_final.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(chequeo_bodega_final.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(chequeo_bodega_final.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(chequeo_bodega_final.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new chequeo_bodega_final("0").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField codigo;
    public static javax.swing.JTextField descliente;
    public static javax.swing.JTable detalle;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextField pedido;
    // End of variables declaration//GEN-END:variables
}
