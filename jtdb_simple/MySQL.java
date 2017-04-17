/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtdb_simple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author prg
 */
public class MySQL {

    private static Connection Conexion;
    private static ResultSet rs;
    private static ResultSetMetaData metaData;

    class Fila {

        Object[] columna;

        public Fila(int nCol) {
            columna = new Object[nCol];

        }

    }

    public void MySQLConnection(String user, String pass, String db_name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_name, user, pass);
            System.out.println("Se ha iniciado la conexión con el servidor de forma exitosa");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection() {
        try {
            Conexion.close();
            System.out.println("Se ha finalizado la conexión con el servidor");
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultaSQL(String sql) {
        rs = null;
        try {
            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            rs = st.executeQuery(sql);
            metaData = rs.getMetaData();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");

        }

    }

    public String[] getCabecera() {
        String[] cabecera = null;
        try {

            int numberOfColumns = metaData.getColumnCount();
            cabecera = new String[numberOfColumns];

            for (int i = 1; i <= numberOfColumns; i++) {
                cabecera[i - 1] = metaData.getColumnName(i);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");

        }
        return cabecera;
    }

    public Object[][] getDatos() {
        Object[][] datos = null;
        try {

            int numberOfColumns = metaData.getColumnCount();
            int numberOfRows;

            rs.last();
            numberOfRows = rs.getRow();

            datos = new Object[numberOfRows][numberOfColumns];

            rs.beforeFirst();
            int nFila = 1;
            while (rs.next()) {
              
                for (int i = 1; i <= numberOfColumns; i++) 
                    datos[nFila - 1][i - 1] = rs.getObject(i);
                nFila++;
                

            }

//             try {
//                        datos[i - 1] = (String) resultSet.getObject(i);
//                    } catch (Exception e) {
//                        fila.columna[i - 1] = String.valueOf(resultSet.getObject(i));
//                    }
            return datos;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");
            return datos;
        }

    }

}
