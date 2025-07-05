/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DbConnection;
import java.sql.*;
/**
 *
 * @author Lenovo
 */
public class DbConnection {
    static String url="jdbc:mysql://localhost:3306/hoteljava";
    static String user="root";
    static String pass="";
    
    public static Connection conectar()
    {
       Connection con = null;
       try
       {
       con=DriverManager.getConnection(url,user,pass);
           System.out.println("Conexión exitosa");
       }catch(SQLException e)
       {
        e.printStackTrace();
       }
       
       return con;
               
    }
}
