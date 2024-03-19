package com.mycompany.controlsoftware;

import java.sql.*;
import java.io.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

public class BaseDeDatos {

    String BasedeDatos = ControlSoftware.bdatos;//Nombre de la base de datos creada en mysql
    String username = "admin"; ///Usuario de mysql
    String password = "batman123"; ///Clave que se aplico a la hora de instalar software de mysql
    String rootandport = "jdbc:mysql://" + ControlSoftware.host + "/";
    private Connection con = null;
////////Ver si Existe Usuario

    public Connection getConexion() {

        try {

            con = (Connection) DriverManager.getConnection(
                    rootandport + BasedeDatos, username, password);

        } catch (SQLException e) {
            System.err.println(e);
        } catch (Exception ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void generarhtml(String xx) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(
                    rootandport + BasedeDatos, username, password);
            Statement statement = conexion.createStatement();

            // Ejecutar la consulta SQL
            ResultSet resultSet = statement.executeQuery(xx);

            // Generar el HTML
            StringBuilder htmlBuilder = new StringBuilder();
            htmlBuilder.append("<html><body><table border='1'><tr>");

            // Obtener los nombres de las columnas
            int columnCount = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                htmlBuilder.append("<th>").append(resultSet.getMetaData().getColumnName(i)).append("</th>");
            }
            htmlBuilder.append("</tr>");

            // Llenar la tabla con los datos
            while (resultSet.next()) {
                htmlBuilder.append("<tr>");
                for (int i = 1; i <= columnCount; i++) {
                    htmlBuilder.append("<td>").append(resultSet.getString(i)).append("</td>");
                }
                htmlBuilder.append("</tr>");
            }

            htmlBuilder.append("</table></body></html>");

            // Imprimir el HTML generado
            String htmlContent = htmlBuilder.toString();
            java.nio.file.Path path = java.nio.file.Files.createTempFile("resultados", ".html");
            java.nio.file.Files.write(path, htmlContent.getBytes());

            // Abrir el navegador con el contenido HTML
            Desktop.getDesktop().browse(path.toUri());

            // Cerrar recursos
            resultSet.close();
            statement.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean versiesta(String XX) {
        boolean v = false;
        try {
            // Este driver est� en mysql-connector-java-3.1.7-bin.jar 

            // Establecemos la conexi�n con la base de datos. 
            Connection conexion = DriverManager.getConnection(
                    rootandport + BasedeDatos, username, password);
            // Preparamos la consulta 
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(XX);
            // Recorremos el resultado, mientras haya registros para leer. 
            while (rs.next()) {
                v = true;
            }
            // Cerramos la conexion a la base de datos. 
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }

    public void RegistrosTabla(String sql, JTable jtQuery) {
        try {
            //Para establecer el modelo al JTable
            DefaultTableModel modelo = new DefaultTableModel();
            jtQuery.setModel(modelo);
            //Para conectarnos a nuestra base de datos
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(
                    rootandport + BasedeDatos, username, password);
            // Preparamos la consulta 
            Statement s = conexion.createStatement();

            //Ejecutamos la consulta que escribimos en la caja de texto
            //y los datos lo almacenamos en un ResultSet
            ResultSet rs = s.executeQuery(sql);
            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsMd = rs.getMetaData();
            //La cantidad de columnas que tiene la consulta
            int cantidadColumnas = rsMd.getColumnCount();
            //Establecer como cabezeras el nombre de las colimnas

            for (int i = 1; i <= cantidadColumnas; i++) {
                modelo.addColumn(rsMd.getColumnLabel(i));

            }
            for (int i = 1; i < cantidadColumnas; i++) {
                TableColumnModel mo = jtQuery.getColumnModel();
                mo.getColumn(i).setPreferredWidth(250);

            }
            jtQuery.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            jtQuery.setColumnSelectionAllowed(true);
            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
            rs.close();
            conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    ////LLenar JTable

    public List<Object> fetchSuggestionsFromDatabase() {
        List<Object> suggestions = new ArrayList<>();

        try {
            Connection conexion = DriverManager.getConnection(
                    rootandport + BasedeDatos, username, password);
            String query = "SELECT paguese_a FROM cheque where cia=" + ControlSoftware.cia;
            PreparedStatement statement = conexion.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                suggestions.add(resultSet.getString("paguese_a"));
            }

            resultSet.close();
            statement.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return suggestions;
    }

    public InputStream obtenerImagenDesdeBD(int idCompany) throws SQLException {
        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        InputStream inputStream = null;

        try {
            // Establecer la conexión con la base de datos
            conexion = DriverManager.getConnection(rootandport + BasedeDatos, username, password);
            // Preparar la consulta SQL para obtener la imagen
            String sql = "SELECT img FROM company WHERE id_company = ?";
            pstmt = conexion.prepareStatement(sql);
            pstmt.setInt(1, idCompany);

            // Ejecutar la consulta y obtener el resultado
            rs = pstmt.executeQuery();

            // Verificar si se encontró un resultado
            if (rs.next()) {
                // Obtener los datos de la imagen como un arreglo de bytes
                byte[] imagenBytes = rs.getBytes("img");

                // Crear un ByteArrayInputStream utilizando los datos de la imagen
                inputStream = new ByteArrayInputStream(imagenBytes);
            } else {
                // Manejar el caso en el que no se encontró ninguna imagen
                System.out.println("No se encontró ninguna imagen para el id_company: " + idCompany);
            }
        } catch (SQLException e) {
            // Manejar cualquier excepción que ocurra durante la recuperación de la imagen
            e.printStackTrace();
        } finally {
            // Cerrar los recursos para liberar memoria y evitar fugas de recursos
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }

        // Devolver el InputStream de la imagen
        return inputStream;
    }

    public void LlenarTabla(String sql, JTable tabla) {
        try {
            //Para establecer el modelo al JTable
            DefaultTableModel modelo = new DefaultTableModel();
            tabla.setModel(modelo);

            //Para conectarnos a nuestra base de datos
            Connection conexion = DriverManager.getConnection(rootandport + BasedeDatos, username, password);
            //Para ejecutar la consulta
            Statement s = conexion.createStatement();
            //Ejecutamos la consulta que escribimos en la caja de texto
            //y los datos lo almacenamos en un ResultSet
            ResultSet rs = s.executeQuery(sql);
            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsMd = rs.getMetaData();
            //La cantidad de columnas que tiene la consulta
            int cantidadColumnas = rsMd.getColumnCount();
            //Establecer como cabezeras el nombre de las colimnas
            for (int i = 1; i <= cantidadColumnas; i++) {
                modelo.addColumn(rsMd.getColumnLabel(i));
            }
            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
            rs.close();
            conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void LlenarTabla2(JTable tabla, String usuario) {
        try {
            //Para establecer el modelo al JTable
            DefaultTableModel modelo = new DefaultTableModel();
            modelo = (DefaultTableModel) tabla.getModel();

            //Para conectarnos a nuestra base de datos
            Connection conexion = DriverManager.getConnection(rootandport + BasedeDatos, username, password);
            //Para ejecutar la consulta
            String rolesQuery = "SELECT id, role FROM role";
            PreparedStatement rolesStatement = conexion.prepareStatement(rolesQuery);
            ResultSet rolesResultSet = rolesStatement.executeQuery();

            while (rolesResultSet.next()) {
                Vector<Object> row = new Vector<>();
                int rolId = rolesResultSet.getInt("id");
                row.add(rolId);
                row.add(rolesResultSet.getString("role"));

                // Verificar si el usuario 1 tiene el rol asignado
                String usuarioRolesQuery = "SELECT COUNT(*) AS count FROM role_usuario "
                        + "WHERE usuarioid = " + usuario + " AND roleid = ? and cia=" + ControlSoftware.cia;
                PreparedStatement usuarioRolesStatement = conexion.prepareStatement(usuarioRolesQuery);
                usuarioRolesStatement.setInt(1, rolId);
                ResultSet usuarioRolesResultSet = usuarioRolesStatement.executeQuery();

                if (usuarioRolesResultSet.next() && usuarioRolesResultSet.getInt("count") > 0) {
                    row.add(true);  // Marcar como activo
                } else {
                    row.add(false);
                }

                usuarioRolesResultSet.close();
                usuarioRolesStatement.close();

                modelo.addRow(row);
            }

            rolesResultSet.close();
            rolesStatement.close();
            conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void LlenarTabla3(JTable tabla) {
        try {
            //Para establecer el modelo al JTable
            DefaultTableModel modelo = new DefaultTableModel();
            modelo = (DefaultTableModel) tabla.getModel();

            //Para conectarnos a nuestra base de datos
            Connection conexion = DriverManager.getConnection(rootandport + BasedeDatos, username, password);
            //Para ejecutar la consulta
            String rolesQuery = "SELECT id, role FROM role";
            PreparedStatement rolesStatement = conexion.prepareStatement(rolesQuery);
            ResultSet rolesResultSet = rolesStatement.executeQuery();

            while (rolesResultSet.next()) {
                Vector<Object> row = new Vector<>();
                int rolId = rolesResultSet.getInt("id");
                row.add(rolId);
                row.add(rolesResultSet.getString("role"));
                row.add(false);
                modelo.addRow(row);
            }

            rolesResultSet.close();
            rolesStatement.close();
            conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void LlenarTabla4(JTable tabla) {
        try {
            //Para establecer el modelo al JTable
            DefaultTableModel modelo = new DefaultTableModel();
            modelo = (DefaultTableModel) tabla.getModel();

            //Para conectarnos a nuestra base de datos
            Connection conexion = DriverManager.getConnection(rootandport + BasedeDatos, username, password);
            //Para ejecutar la consulta
            String rolesQuery = "SELECT id, permiso FROM permisos";
            PreparedStatement rolesStatement = conexion.prepareStatement(rolesQuery);
            ResultSet rolesResultSet = rolesStatement.executeQuery();

            while (rolesResultSet.next()) {
                Vector<Object> row = new Vector<>();
                int rolId = rolesResultSet.getInt("id");
                row.add(rolId);
                row.add(rolesResultSet.getString("permiso"));
                row.add(false);
                modelo.addRow(row);
            }

            rolesResultSet.close();
            rolesStatement.close();
            conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void LlenarTabla5(JTable tabla, String role) {
        try {
            //Para establecer el modelo al JTable
            DefaultTableModel modelo = new DefaultTableModel();
            modelo = (DefaultTableModel) tabla.getModel();

            //Para conectarnos a nuestra base de datos
            Connection conexion = DriverManager.getConnection(rootandport + BasedeDatos, username, password);
            //Para ejecutar la consulta
            String rolesQuery = "SELECT id, permiso FROM permisos";
            PreparedStatement rolesStatement = conexion.prepareStatement(rolesQuery);
            ResultSet rolesResultSet = rolesStatement.executeQuery();

            while (rolesResultSet.next()) {
                Vector<Object> row = new Vector<>();
                int rolId = rolesResultSet.getInt("id");
                row.add(rolId);
                row.add(rolesResultSet.getString("permiso"));

                // Verificar si el usuario 1 tiene el rol asignado
                String usuarioRolesQuery = "SELECT COUNT(*) AS count FROM role_permiso "
                        + "WHERE roleid = " + role + " AND permisoid = ? ";
                PreparedStatement usuarioRolesStatement = conexion.prepareStatement(usuarioRolesQuery);
                usuarioRolesStatement.setInt(1, rolId);
                ResultSet usuarioRolesResultSet = usuarioRolesStatement.executeQuery();

                if (usuarioRolesResultSet.next() && usuarioRolesResultSet.getInt("count") > 0) {
                    row.add(true);  // Marcar como activo
                } else {
                    row.add(false);
                }

                usuarioRolesResultSet.close();
                usuarioRolesStatement.close();

                modelo.addRow(row);
            }

            rolesResultSet.close();
            rolesStatement.close();
            conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void LlenarTabla6(JTable tabla) {
        try {
            //Para establecer el modelo al JTable
            DefaultTableModel modelo = new DefaultTableModel();
            modelo = (DefaultTableModel) tabla.getModel();

            //Para conectarnos a nuestra base de datos
            Connection conexion = DriverManager.getConnection(rootandport + BasedeDatos, username, password);
            //Para ejecutar la consulta
            String rolesQuery = "SELECT id, configuracion  FROM configuraciones";
            PreparedStatement rolesStatement = conexion.prepareStatement(rolesQuery);
            ResultSet rolesResultSet = rolesStatement.executeQuery();

            while (rolesResultSet.next()) {
                Vector<Object> row = new Vector<>();
                int rolId = rolesResultSet.getInt("id");
                row.add(rolId);
                row.add(rolesResultSet.getString("configuracion"));

                // Verificar si el usuario 1 tiene el rol asignado
                String usuarioRolesQuery = "SELECT COUNT(*) AS count FROM company_configuraciones "
                        + "WHERE companyid = " + ControlSoftware.cia + " AND configuracionid = ? ";
                PreparedStatement usuarioRolesStatement = conexion.prepareStatement(usuarioRolesQuery);
                usuarioRolesStatement.setInt(1, rolId);
                ResultSet usuarioRolesResultSet = usuarioRolesStatement.executeQuery();

                if (usuarioRolesResultSet.next() && usuarioRolesResultSet.getInt("count") > 0) {
                    row.add(true);  // Marcar como activo
                } else {
                    row.add(false);
                }

                usuarioRolesResultSet.close();
                usuarioRolesStatement.close();

                modelo.addRow(row);
            }

            rolesResultSet.close();
            rolesStatement.close();
            conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

////Para ejecutar consultas de afectar tablas como ser insert , delete y update
    public void EjecutarConsultas(String XX) {
        try {
            // Este driver est� en mysql-connector-java-3.1.7-bin.jar 

            // Establecemos la conexi�n con la base de datos. 
            Connection conexion = DriverManager.getConnection(
                    rootandport + BasedeDatos, username, password);
            // Preparamos la consulta 
            Statement s = conexion.createStatement();
            s.execute(XX);
            conexion.close();
            // JOptionPane.showMessageDialog(null,"La operacion se realizo ");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
            JOptionPane.showMessageDialog(null, "Verificar la consulta \n" + XX, "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }

    public char[] obtenerPasswordDesdeBaseDeDatos(String usernam) {
        String storedPassword = null;

        try {

            Connection conexion = DriverManager.getConnection(rootandport + BasedeDatos, username, password);
            String query = "SELECT password FROM usuario WHERE user = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, usernam);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                storedPassword = resultSet.getString("password");
            }

            resultSet.close();
            statement.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return storedPassword != null ? storedPassword.toCharArray() : null;
    }

    /*Correr Digito*/
    //Se envia la consulta con el id a correr y el texto donde saldra el nuevo codigo
    public void CorrerDigito(String XX, JTextField txt) {
        int numero = 0;
        txt.setEditable(false);
        try {
            // Este driver est� en mysql-connector-java-3.1.7-bin.jar 

            // Establecemos la conexi�n con la base de datos. 
            Connection conexion = DriverManager.getConnection(
                    rootandport + BasedeDatos, username, password);
            // Preparamos la consulta 
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(XX);
            // Recorremos el resultado, mientras haya registros para leer. 
            while (rs.next()) {
                numero = rs.getInt(1);

            }
            // Cerramos la conexion a la base de datos. 
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        txt.setText("" + (numero + 1));

    }
    //Cantidad de registros en la tabla

    public int CantidadRegistros(String XX) {
        int numero = 0;
        try {
            // Este driver est� en mysql-connector-java-3.1.7-bin.jar 

            // Establecemos la conexi�n con la base de datos. 
            Connection conexion = DriverManager.getConnection(
                    rootandport + BasedeDatos, username, password);
            // Preparamos la consulta 
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(XX);
            // Recorremos el resultado, mientras haya registros para leer. 
            while (rs.next()) {
                numero++;

            }
            // Cerramos la conexion a la base de datos. 
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numero;
    }
    ///En caso de querer solo obtener un valor se envia una consulta solo seleccionando el campo deseado

    public String doble(String XX, String XY) {
        String numero = "";

        try {
            // Este driver est� en mysql-connector-java-3.1.7-bin.jar 

            // Establecemos la conexi�n con la base de datos. 
            Connection conexion = DriverManager.getConnection(
                    rootandport + BasedeDatos, username, password);
            // Preparamos la consulta 
            Statement s = conexion.createStatement();
            s.execute(XX);
            ResultSet rs = s.executeQuery(XY);
            // Recorremos el resultado, mientras haya registros para leer. 
            while (rs.next()) {
                numero = rs.getString(1);

            }
            // Cerramos la conexion a la base de datos. 
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return numero;

    }

    public String valor(String XX) {
        String numero = "";
        try {
            // Este driver est� en mysql-connector-java-3.1.7-bin.jar 
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecemos la conexi�n con la base de datos. 
            Connection conexion = DriverManager.getConnection(
                    rootandport + BasedeDatos, username, password);
            // Preparamos la consulta 
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(XX);
            // Recorremos el resultado, mientras haya registros para leer. 
            while (rs.next()) {
                numero = rs.getString(1);

            }
            // Cerramos la conexion a la base de datos. 
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numero;
    }

    public Date fecha(String XX) {
        Date numero = null;
        try {
            // Este driver est� en mysql-connector-java-3.1.7-bin.jar 
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecemos la conexi�n con la base de datos. 
            Connection conexion = DriverManager.getConnection(
                    rootandport + BasedeDatos, username, password);
            // Preparamos la consulta 
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(XX);
            // Recorremos el resultado, mientras haya registros para leer. 
            while (rs.next()) {
                numero = rs.getDate(1);

            }
            // Cerramos la conexion a la base de datos. 
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numero;
    }

    ///Para sacar un registro completo   
    public String[][] LlenarCampos(String XX) {
        String A[][] = new String[1][1];
        int C = 0;
        int i = 0;
        int j = 0;
        try {
            // Este driver est� en mysql-connector-java-3.1.7-bin.jar 

            // Establecemos la conexi�n con la base de datos. 
            Connection conexion = DriverManager.getConnection(
                    rootandport + BasedeDatos, username, password);
            // Preparamos la consulta 
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(XX);
            C = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                i++;
            }
            A = new String[i][C];
            i = 0;
            rs = s.executeQuery(XX);
            // Recorremos el resultado, mientras haya registros para leer. 
            while (rs.next()) {
                for (int x = 1; x <= C; x++) {
                    A[i][j] = rs.getString(x);
                    j++;
                }
                j = 0;
                i++;

            }
            // Cerramos la conexion a la base de datos. 
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return A;
    }
    ///Nombre de los campos de una tabla

    public String[] ColumLlenar(String XX) {
        String A[] = new String[1];
        int i = 0;
        try {
            // Este driver est� en mysql-connector-java-3.1.7-bin.jar 

            // Establecemos la conexi�n con la base de datos. 
            Connection conexion = DriverManager.getConnection(
                    rootandport + BasedeDatos, username, password);
            // Preparamos la consulta 
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(XX);
            A = new String[rs.getMetaData().getColumnCount()];
            i = 1;
            for (int x = 0; x < A.length; x++) {
                A[x] = rs.getMetaData().getColumnName(i);
                i++;
            }

            // Cerramos la conexion a la base de datos. 
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return A;
    }
    ///////////////Nombre de los campos cargados en un combo

    public void CamposCombo(String XX, JComboBox cmb) {
        String A[] = new String[1];
        cmb.removeAllItems();
        int i = 0;
        try {
            // Este driver est� en mysql-connector-java-3.1.7-bin.jar 

            // Establecemos la conexi�n con la base de datos. 
            Connection conexion = DriverManager.getConnection(
                    rootandport + BasedeDatos, username, password);
            // Preparamos la consulta 
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(XX);
            A = new String[rs.getMetaData().getColumnCount()];
            i = 1;
            for (int x = 0; x < A.length; x++) {
                //A[x]=rs.getMetaData().getColumnName(i);
                cmb.addItem(rs.getMetaData().getColumnName(i));
                i++;
            }

            // Cerramos la conexion a la base de datos. 
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    int contar = 0;
    int contar1 = 0;
    int T = 4;

    ///Enviar la consulta el modelo y la tabla para ser llenada
    public void LlenarLista(String sqlx, JTable tablaAfiliados, DefaultTableModel afiliados) {
        JTableHeader header = tablaAfiliados.getTableHeader();
        header.setBackground(Color.yellow);

        String Noc[] = ColumLlenar(sqlx);

        System.out.println("tablaAfiliados.getColumnCount() " + tablaAfiliados.getColumnCount());

        for (int i = 0; i < tablaAfiliados.getColumnCount() - 1; i++) {
            TableColumn tcol = tablaAfiliados.getColumnModel().getColumn(i);
            tablaAfiliados.removeColumn(tcol);
        }

        /*column = mainModel.getColumn(0);
        mainModel.removeColumn(column);*/
        for (int i = 0; i < Noc.length; i++) {
            afiliados.addColumn(Noc[i]);

        }

        for (int i = 0; i < Noc.length; i++) {
            TableColumn columnId = null;

            columnId = tablaAfiliados.getColumnModel().getColumn(i);

            columnId.setPreferredWidth(150);
        }
        tablaAfiliados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaAfiliados.setColumnSelectionAllowed(true);
        tablaAfiliados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaAfiliados.setColumnSelectionAllowed(true);

        try {
            contar = 0;
            contar1 = 0;
            // Este driver est en mysql-connector-java-3.1.7-bin.jar 

            // Establecemos la conexin con la base de datos. 
            Connection conexion = DriverManager.getConnection(
                    rootandport + BasedeDatos, username, password);
            // Preparamos la consulta 
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(sqlx);
            // Recorremos el resultado, mientras haya registros para leer. 

            T = rs.getMetaData().getColumnCount();
            String a[] = new String[T];

            int cc = 0;

            for (int i = tablaAfiliados.getRowCount() - 1; i >= 0; i--) {
                afiliados.removeRow(i);
            }

            rs = s.executeQuery(sqlx);
            while (rs.next()) {

                for (int j = 1; j <= T; j++) {
                    a[contar] = rs.getString(j);
                    contar++;
                }
                //tablaAfiliados.ad
                afiliados.addRow(a);
                contar = 0;

                contar1++;

            }
            // Cerramos la conexion a la base de datos. 
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    ///Llenar un combo con los valores de un campo espeficico , select nombre from tabla 
    public void LlenarCombo(String XX, JComboBox cmb) {
        String A[] = new String[1];
        int i = 0;
        cmb.removeAllItems();
        try {
            // Este driver est� en mysql-connector-java-3.1.7-bin.jar 

            // Establecemos la conexi�n con la base de datos. 
            Connection conexion = DriverManager.getConnection(
                    rootandport + BasedeDatos, username, password);
            // Preparamos la consulta 
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(XX);
            // Recorremos el resultado, mientras haya registros para leer. 
            while (rs.next()) {

                cmb.addItem("" + rs.getObject(1));
                i++;

            }
            // Cerramos la conexion a la base de datos. 
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void LlenarCombo1(String XX, JComboBox cmb, String cod, String name) {
        cmb.removeAllItems();
        try {
            // Establecemos la conexión con la base de datos.
            Connection conexion = DriverManager.getConnection(rootandport + BasedeDatos, username, password);
            // Preparamos la consulta
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(XX);
            // Recorremos el resultado, mientras haya registros para leer.
            while (rs.next()) {
                String id = rs.getString("" + cod); // Asumiendo que el campo que contiene el id se llama "id"
                String nombre = rs.getString("" + name); // Asumiendo que el campo que contiene el nombre se llama "nombre"
                Item item = new Item(id, nombre);
                cmb.addItem(item); // Agregamos el objeto Item al JComboBox
            }
            // Cerramos la conexión a la base de datos.
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LlenarCombo2(String XX, JComboBox cmb, String cod, String name) {
        cmb.removeAllItems();
        try {
            int x = 0;
            // Establecemos la conexión con la base de datos.
            Connection conexion = DriverManager.getConnection(rootandport + BasedeDatos, username, password);
            // Preparamos la consulta
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(XX);
            // Recorremos el resultado, mientras haya registros para leer.
            while (rs.next()) {
                String id = "" + x; // Asumiendo que el campo que contiene el id se llama "id"
                String nombre = rs.getString("" + name); // Asumiendo que el campo que contiene el nombre se llama "nombre"
                Item item = new Item(id, nombre);
                cmb.addItem(item); // Agregamos el objeto Item al JComboBox
                x++;
            }
            // Cerramos la conexión a la base de datos.
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void seleccionarItemPorId(JComboBox cmb, String id) {
        for (int i = 0; i < cmb.getItemCount(); i++) {
            Item item = (Item) cmb.getItemAt(i);
            if (item.getId().equals(id)) {
                cmb.setSelectedItem(item);
                break;
            }
        }
    }

    public void seleccionar1Item(JComboBox cmb) {

        Item item = (Item) cmb.getItemAt(1);

        cmb.setSelectedItem(item);

    }

    public String[] Llenar(String XX, String Campo) {
        String A[] = new String[1];
        int i = 0;
        try {
            // Este driver est� en mysql-connector-java-3.1.7-bin.jar 

            // Establecemos la conexi�n con la base de datos. 
            Connection conexion = DriverManager.getConnection(
                    rootandport + BasedeDatos, username, password);
            // Preparamos la consulta 
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(XX);
            while (rs.next()) {
                i++;
            }
            A = new String[i];
            i = 0;
            rs = s.executeQuery(XX);
            // Recorremos el resultado, mientras haya registros para leer. 
            while (rs.next()) {
                A[i] = rs.getString(Campo);
                i++;

            }
            // Cerramos la conexion a la base de datos. 
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return A;
    }

    public void ingresoDeImagen(String x, String ruta, int nCampo) {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection conn = DriverManager.getConnection(rootandport + BasedeDatos, username, password);
            PreparedStatement ps = conn.prepareStatement(x);
            conn.setAutoCommit(false);
            //System.out.println(ruta+nombre);
            // copia(ruta+nombre,nombre);
            File file = new File(ruta);

            FileInputStream fis = new FileInputStream(file);
            ps.setBinaryStream(nCampo, fis, (int) file.length());
            //ps.setBlob(1, fis,(int) file.length());
            ps.executeUpdate();
            conn.commit();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ImageIcon[] ArregloImagen(String x, int NumeroCampo) {
        ImageIcon m[] = new ImageIcon[CantidadRegistros(x)];

        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection conn = DriverManager.getConnection(rootandport + BasedeDatos, username, password);
            PreparedStatement ps = conn.prepareStatement(x);
            conn.setAutoCommit(false);
            ResultSet r = ps.executeQuery();
            int n = 0;
            while (r.next()) {
                byte[] i = null;
                i = r.getBytes(NumeroCampo);
                m[n] = new ImageIcon(i);
                n++;
            }
            r.close();
            conn.commit();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        /*ImageIcon icon =m;
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(x1,y1,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);		*/
        return m;

    }

    public ImageIcon sacarImagen(String x, int x1, int y1, int nc) {
        ImageIcon m = new ImageIcon();

        try {

            Connection conn = DriverManager.getConnection(rootandport + BasedeDatos, username, password);
            PreparedStatement ps = conn.prepareStatement(x);
            conn.setAutoCommit(false);
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                byte[] i = null;
                i = r.getBytes(nc);
                m = new ImageIcon(i);
            }
            r.close();
            conn.commit();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageIcon icon = m;
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(x1, y1, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);

        return newIcon;

    }

    public JLabel ImagenEtiqueta(String x, int x1, int y1) {
        ImageIcon m = new ImageIcon();
        JLabel yo = new JLabel();
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection conn = DriverManager.getConnection(rootandport + BasedeDatos, username, password);
            PreparedStatement ps = conn.prepareStatement(x);
            conn.setAutoCommit(false);
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                byte[] i = null;
                System.out.println(r.getString(1));
                i = r.getBytes("imagen");
                m = new ImageIcon(i);
            }
            r.close();
            conn.commit();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageIcon icon = m;
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(x1, y1, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        yo.setIcon(newIcon);
        yo.setSize(150, 150);

        return yo;

    }

    public double Total(String XX) {
        double numero = 0;
        try {
            // Este driver est� en mysql-connector-java-3.1.7-bin.jar 

            // Establecemos la conexi�n con la base de datos. 
            Connection conexion = DriverManager.getConnection(
                    rootandport + BasedeDatos, username, password);
            // Preparamos la consulta 
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(XX);
            // Recorremos el resultado, mientras haya registros para leer. 
            while (rs.next()) {
                numero = rs.getInt(1);

            }
            // Cerramos la conexion a la base de datos. 
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numero;
    }

    public void copia(String ficheroOriginal, String ficheroCopia) {
        try {
            // Se abre el fichero original para lectura
            FileInputStream fileInput = new FileInputStream(ficheroOriginal);
            BufferedInputStream bufferedInput = new BufferedInputStream(fileInput);

            // Se abre el fichero donde se har� la copia
            FileOutputStream fileOutput = new FileOutputStream(ficheroCopia);
            BufferedOutputStream bufferedOutput = new BufferedOutputStream(fileOutput);

            // Bucle para leer de un fichero y escribir en el otro.
            byte[] array = new byte[1000];
            int leidos = bufferedInput.read(array);
            while (leidos > 0) {
                bufferedOutput.write(array, 0, leidos);
                leidos = bufferedInput.read(array);
            }

            // Cierre de los ficheros
            bufferedInput.close();
            bufferedOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
