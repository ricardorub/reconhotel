package DbConnection;
import java.sql.*;

public class DbConnection {
    static String url="jdbc:mysql://localhost:3306/hoteljava"; // Base de datos del proyecto
    static String user="root"; // Usuario por defecto de XAMPP/MySQL
    static String pass="root";    // Contraseña por defecto vacía para root en XAMPP
    static String driver = "com.mysql.cj.jdbc.Driver";

    // Bloque estático para cargar el driver una sola vez cuando la clase es cargada.
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load MySQL JDBC Driver: " + e.getMessage());
            // Podrías lanzar una RuntimeException aquí si el driver es absolutamente esencial
            // throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }
    
    public static Connection conectar() {
       Connection con = null;
       try {
           con = DriverManager.getConnection(url,user,pass);
           // System.out.println("Conexión exitosa a " + url); // Comentar para producción
       } catch(SQLException e) {
           System.err.println("Error al conectar a la base de datos: " + e.getMessage());
           // e.printStackTrace(); // Podría ser útil durante el desarrollo
       }
       return con;
    }

    // Método de prueba
    public static void main(String[] args) {
        Connection c = DbConnection.conectar();
        if (c != null) {
            System.out.println("Conexión de prueba exitosa desde DbConnection.main().");
            try {
                c.close();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar conexión de prueba: " + ex.getMessage());
            }
        } else {
            System.err.println("Conexión de prueba fallida desde DbConnection.main().");
        }
    }
}
