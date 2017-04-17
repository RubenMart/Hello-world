/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtdb_simple;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author prg
 */
//public class JTDB_Simple {
public class JTDB_Simple extends javax.swing.JFrame
{
  
  private javax.swing.JScrollPane jScrollPanel1;
  private javax.swing.JTable tabla;
   private DefaultTableModel modelo; 
    
  public JTDB_Simple()
  {
    initComponents();
    setTitle("Datos");
    setSize(500, 300);
  }
  
  /* Iniciar los componentes */
  private void initComponents()
  {
      
    MySQL myDb = new MySQL();
    Object[] cabecera = null;
    
    Object[][] datos;
    myDb.MySQLConnection( "pepe", "pepa","depemp2014");
    myDb.consultaSQL("select * from empleados");
    //devuelve los datos de la consulta y la cabecera de la tabla
    datos = myDb.getDatos();
    cabecera = myDb.getCabecera();
    
    
    //myDb.closeConnection();
    
   
    
    modelo = new DefaultTableModel(datos,cabecera);
    tabla = new JTable(modelo);
    
    // tabla = new JTable(datos,cabecera);

    
   
    // Asigna datos y cabecera a jTable
//    jTabla1.setModel(new javax.swing.table.DefaultTableModel(datos, cabecera)
//    {
//        // Declara los tipos de las columnas
////        Class[] tipoColumn = { java.lang.String.class,
////                             java.lang.String.class,
////                             java.lang.Long.class,
////                             java.lang.Boolean.class };
//
//        // Dice que columnas se pueden editar
//      boolean[] editColum = { false, true, true, true };
//
////      @Override
////      public Class getColumnClass(int indColum) // devuelve el tipo de la columna?
////      {
////        return tipoColumn[indColum];
////      }
//
////      @Override
////      public boolean isCellEditable(int indFila, int indColum)
////      {
////        return editColum[indColum];
////      }
//    });

    
    // Aquí se especifica la anchura de las columnas
    javax.swing.table.TableColumn colum = null;
    for (int i = 0; i < tabla.getColumnCount(); i++)
    {
      colum = tabla.getColumnModel().getColumn(i);
      if (i < 2)
        colum.setPreferredWidth(110);
      else
        colum.setPreferredWidth(40);
    }

    tabla.addMouseListener(new java.awt.event.MouseAdapter()
    {
      public void mouseClicked(java.awt.event.MouseEvent evt)
      {
        jTabla1MouseClicked(evt);
      }
    });
    
    //  instanciar un JScrollPane  y se pasa jTabla1 como parámetro
    
    jScrollPanel1 = new javax.swing.JScrollPane(tabla);
    
    

    addWindowListener(new java.awt.event.WindowAdapter()
    {
      public void windowClosing(java.awt.event.WindowEvent evt)
      {   myDb.closeConnection();
        exitForm(evt);
      }
    });

    
    // Se coloca jScrollPane1 en el panel por defecto;
   getContentPane().add(jScrollPanel1, java.awt.BorderLayout.CENTER);
  }
  
  private void jTabla1MouseClicked(java.awt.event.MouseEvent evt)
  {
    java.lang.Object datoCelda =tabla.getValueAt(
                                     tabla.getSelectedRow(),
                                     tabla.getSelectedColumn());
    System.out.println(datoCelda);
  }

  /** Salir de la aplicación */
  private void exitForm(java.awt.event.WindowEvent evt)
  {
    System.exit(0);
  }
  
  public static void main(String[] args)
  {
      
    new JTDB_Simple().setVisible(true);
  }
  
  // Declaración de variables
 
}