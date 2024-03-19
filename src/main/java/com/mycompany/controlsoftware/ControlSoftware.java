/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.controlsoftware;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

 
 
public class ControlSoftware {
   public static int cia=1;
   public static int us=1;
   public static int us_temp=0;
   public static String url="http://192.168.100.21:9096/";
   public static String host="192.168.100.21";
   public static String bdatos="controlsoft";
   BaseDeDatos bd=new BaseDeDatos();
   public static void main(String[] args) {
   try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        
        for (int i = 0; i < args.length; i++) {
            
          //   bd.EjecutarConsultas("update ");
            
        }
        
        new DESKSOFTWARE().setVisible(true);
        }
}
