/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

// import General.Admin; // Not directly used in this controller logic after refactor, can be removed if not needed elsewhere by Admin specific logic
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
// import org.mindrot.jbcrypt.BCrypt; // BCrypt removed

public class ControllerAdmin {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    private String dbDriver;

    public ControllerAdmin() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("nbproject/project.properties")) {
            props.load(fis);
            this.dbUrl = props.getProperty("db.url");
            this.dbUser = props.getProperty("db.user");
            this.dbPassword = props.getProperty("db.password");
            this.dbDriver = props.getProperty("db.driver");

            if (this.dbDriver != null && !this.dbDriver.isEmpty()) {
                Class.forName(this.dbDriver);
            } else {
                System.err.println("Database driver not specified in project.properties for ControllerAdmin.");
                 this.dbUrl = null; // Prevent further operations if driver is missing
            }
        } catch (IOException e) {
            System.err.println("Error loading database configuration for ControllerAdmin: " + e.getMessage());
            this.dbUrl = null; 
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found for ControllerAdmin: " + e.getMessage());
            this.dbUrl = null; 
        }
    }

    /**
     * Registers a new admin user with a plain password.
     *
     * @param username The desired username for the new admin.
     * @param plainPassword The plain-text password for the new admin.
     * @param email The email for the new admin.
     * @param securityQuestion The security question for the new admin.
     * @param answer The answer to the security question.
     * @return True if registration is successful, false otherwise.
     */
    public boolean registrarAdmin(String username, String plainPassword, String email, String securityQuestion, String answer) {
        if (dbUrl == null) {
            System.err.println("Database configuration not loaded. Cannot register admin.");
            return false;
        }
        if (username == null || username.trim().isEmpty() || 
            plainPassword == null || plainPassword.isEmpty() || // Password is now plain text
            email == null || email.trim().isEmpty()) {
            System.err.println("Username, password, or email cannot be empty for admin registration.");
            return false;
        }

        if (adminExists(username, email)) {
            return false;
        }

        // The column name in the database for the password is 'password_hash'.
        // This should ideally be renamed to 'password' or similar if storing plain text.
        // For now, we'll use the existing column name.
        String sql = "INSERT INTO admin (username, password_hash, email, security_question, answer, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt()); // BCrypt removed
            String passwordToStore = plainPassword; // Storing plain password

            pstmt.setString(1, username);
            pstmt.setString(2, passwordToStore); // Storing plain password
            pstmt.setString(3, email);
            pstmt.setString(4, securityQuestion);
            pstmt.setString(5, answer);
            pstmt.setString(6, "approved"); // Default status

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Admin user '" + username + "' registered successfully (plain password).");
                return true;
            } else {
                System.err.println("Admin user registration failed for '" + username + "' (no rows affected).");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Database error during admin registration for '" + username + "': " + e.getMessage());
            return false;
        }
    }

    /**
     * Checks if an admin with the given username or email already exists in the database.
     * @param username The username to check.
     * @param email The email to check.
     * @return true if an admin with that username or email exists, false otherwise.
     */
    private boolean adminExists(String username, String email) {
        if (dbUrl == null) {
            System.err.println("Database configuration not loaded. Cannot check if admin exists.");
            return true; 
        }
        String sql = "SELECT id FROM admin WHERE username = ? OR email = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.err.println("Admin user with username '" + username + "' or email '" + email + "' already exists.");
                    return true; 
                }
                return false; 
            }
        } catch (SQLException e) {
            System.err.println("Database error checking if admin '" + username + "/" + email + "' exists: " + e.getMessage());
            return true; 
        }
    }

    /**
     * Verifies an admin's plain-text password against the stored plain password.
     *
     * @param username Admin's username.
     * @param plainPassword The plain-text password to verify.
     * @return True if the password matches, false otherwise.
     */
    public boolean verificarPasswordAdmin(String username, String plainPassword) {
        if (dbUrl == null) {
            System.err.println("Database configuration not loaded. Cannot verify admin password.");
            return false;
        }
        // Assuming login is by username for admins
        // The column name in the database for the password is 'password_hash'.
        // This should ideally be renamed to 'password' or similar if storing plain text.
        String sql = "SELECT password_hash FROM admin WHERE username = ?"; 
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password_hash"); // Actually plain password now
                    if (storedPassword == null || storedPassword.isEmpty()) {
                        System.err.println("Admin '" + username + "' has no password set or password_hash is empty.");
                        return false; 
                    }
                    // return BCrypt.checkpw(plainPassword, storedPassword); // BCrypt removed
                    return plainPassword.equals(storedPassword); // Simple string comparison
                }
                System.err.println("Admin user '" + username + "' not found for password verification.");
                return false; // Admin not found
            }
        } catch (SQLException e) {
            System.err.println("Database error during admin password verification for '" + username + "': " + e.getMessage());
            return false; 
        }
    }
    
    // Main method for testing ControllerAdmin independently
    public static void main(String[] args) {
        ControllerAdmin controller = new ControllerAdmin();
        
        if (controller.dbUrl == null) {
             System.err.println("DB Config not loaded. Tests will fail. Check nbproject/project.properties path and content, and MySQL Connector JAR.");
             return;
        }
        System.out.println("ControllerAdmin - DB URL: " + controller.dbUrl);
        System.out.println("ControllerAdmin - DB User: " + controller.dbUser);
        System.out.println("ControllerAdmin - DB Driver: " + controller.dbDriver);

        // Test Case 1: Register a new admin
        String testAdminUser = "testAdminPlain"; // Changed user for clarity
        String testAdminPass = "securePassword123";
        String testAdminEmail = "testadminplain@example.com"; // Changed email

        System.out.println("\n--- ControllerAdmin Test Case 1: Register New Admin (Plain Text Password) ---");
        boolean success1 = controller.registrarAdmin(testAdminUser, testAdminPass, testAdminEmail, "Fav IDE?", "NetBeans");
        if (success1) {
            System.out.println("Test Case 1: Registration successful for " + testAdminUser);
        } else {
            System.out.println("Test Case 1: Registration failed for " + testAdminUser);
        }

        // Test Case 2: Verify correct password for the new admin
        System.out.println("\n--- ControllerAdmin Test Case 2: Verify Correct Password ---");
        if (success1) { 
            boolean passVerify1 = controller.verificarPasswordAdmin(testAdminUser, testAdminPass);
            if (passVerify1) {
                System.out.println("Test Case 2: Password verification successful for " + testAdminUser);
            } else {
                System.out.println("Test Case 2: Password verification FAILED for " + testAdminUser);
            }
        } else {
            System.out.println("Test Case 2: Skipped password verification due to registration failure.");
        }

        // Test Case 3: Verify incorrect password for the new admin
        System.out.println("\n--- ControllerAdmin Test Case 3: Verify Incorrect Password ---");
        if (success1) {
            boolean passVerify2 = controller.verificarPasswordAdmin(testAdminUser, "wrongPassword");
            if (!passVerify2) {
                System.out.println("Test Case 3: Correctly failed to verify incorrect password for " + testAdminUser);
            } else {
                System.out.println("Test Case 3: INCORRECTLY verified incorrect password for " + testAdminUser);
            }
        } else {
            System.out.println("Test Case 3: Skipped incorrect password verification due to registration failure.");
        }
        
        // Test Case 4: Try to register the same admin username (should fail)
        System.out.println("\n--- ControllerAdmin Test Case 4: Register Duplicate Username ---");
        boolean success4 = controller.registrarAdmin(testAdminUser, "anotherPass", "anotheremail@example.com", "Q", "A");
        if (!success4) {
            System.out.println("Test Case 4: Correctly failed to register duplicate username '" + testAdminUser + "'.");
        } else {
            System.out.println("Test Case 4: INCORRECTLY registered duplicate username '" + testAdminUser + "'.");
        }

        System.out.println("\nControllerAdmin plain text password testing complete. Check 'admin' table in database for results (password_hash column now stores plain text).");
        System.out.println("REMINDER: Storing plain text passwords is a major security risk. This change is for demonstration/specific request only.");
        System.out.println("The 'admin' table's 'password_hash' column might need its data type/length adjusted if it was previously sized for BCrypt hashes.");
    }
}
