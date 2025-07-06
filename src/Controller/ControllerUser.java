package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import org.mindrot.jbcrypt.BCrypt; // Import BCrypt

public class ControllerUser {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    private String dbDriver;

    public ControllerUser() {
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
                System.err.println("Database driver not specified in project.properties for ControllerUser.");
                this.dbUrl = null; // Prevent further operations if driver is missing
            }
        } catch (IOException e) {
            System.err.println("Error loading database configuration for ControllerUser: " + e.getMessage());
            this.dbUrl = null;
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found for ControllerUser: " + e.getMessage());
            this.dbUrl = null;
        }
    }

    /**
     * Registers a new user with a hashed password.
     *
     * @param name User's full name.
     * @param email User's email address (acts as username).
     * @param plainPassword User's plain-text password.
     * @param securityQuestion User's chosen security question.
     * @param answer User's answer to the security question.
     * @return True if registration is successful, false otherwise.
     */
    public boolean registrarUsuario(String name, String email, String plainPassword, String securityQuestion, String answer) {
        if (dbUrl == null) {
            System.err.println("Database configuration not loaded. Cannot register user.");
            return false;
        }
        if (name == null || name.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            plainPassword == null || plainPassword.isEmpty() ||
            securityQuestion == null || securityQuestion.trim().isEmpty() ||
            answer == null || answer.trim().isEmpty()) {
            System.err.println("All fields are required for user registration.");
            return false;
        }

        // 1. Check if email (acting as username) already exists
        if (userExists(email)) {
            System.err.println("User with email '" + email + "' already exists.");
            return false;
        }

        // 2. Store the new user in the database
        String sql = "INSERT INTO signup (name, email, password, sq, answer) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, email.toLowerCase());
            String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
            pstmt.setString(3, hashedPassword); // Store hashed password
            pstmt.setString(4, securityQuestion);
            pstmt.setString(5, answer);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("User '" + name + "' registered successfully with email '" + email + "'.");
                return true;
            } else {
                System.err.println("User registration failed for '" + name + "' (no rows affected).");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Database error during user registration for '" + name + "': " + e.getMessage());
            return false;
        }
    }

    /**
     * Checks if a user with the given email already exists in the database.
     * @param email The email to check.
     * @return true if a user with that email exists, false otherwise.
     */
    private boolean userExists(String email) {
        if (dbUrl == null) {
            System.err.println("Database configuration not loaded. Cannot check if user exists.");
            return true; // Safer to assume exists if DB is not configured
        }
        String sql = "SELECT email FROM signup WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, email.toLowerCase());
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // True if a record is found
            }
        } catch (SQLException e) {
            System.err.println("Database error checking if user with email '" + email + "' exists: " + e.getMessage());
            return true; // Safer to assume exists on DB error to prevent duplicates
        }
    }

    /**
     * Verifies a user's plain-text password against the stored hash.
     *
     * @param email User's email address.
     * @param plainPassword The plain-text password to verify.
     * @return True if the password matches, false otherwise.
     */
    public boolean verificarPassword(String email, String plainPassword) {
        if (dbUrl == null) {
            System.err.println("Database configuration not loaded. Cannot verify password.");
            return false;
        }
        String sql = "SELECT password FROM signup WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, email.toLowerCase());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password");
                    return BCrypt.checkpw(plainPassword, storedHash);
                }
                return false; // User not found
            }
        } catch (SQLException e) {
            System.err.println("Database error during password verification for email '" + email + "': " + e.getMessage());
            return false; 
        }
    }

    // Getter methods for database properties (needed by SignIn.java for status check)
    public String getDbUrlProperty() {
        return dbUrl;
    }

    public String getDbUserProperty() {
        return dbUser;
    }

    public String getDbPasswordProperty() {
        return dbPassword;
    }

    public String getDbDriverProperty() {
        return dbDriver;
    }
    
    // Main method for basic testing of ControllerUser
    public static void main(String[] args) {
        ControllerUser controller = new ControllerUser();

        if (controller.dbUrl == null) {
            System.err.println("DB Config not loaded for ControllerUser. Tests will fail. Check nbproject/project.properties path and content, and MySQL Connector JAR.");
            return;
        }
        System.out.println("ControllerUser - DB URL: " + controller.dbUrl);
        System.out.println("ControllerUser - DB User: " + controller.dbUser);
        System.out.println("ControllerUser - DB Driver: " + controller.dbDriver);

        // Test Case 1: Register a new user
        System.out.println("\n--- ControllerUser Test Case 1: Register New User ---");
        boolean success1 = controller.registrarUsuario("Test User One", "testuser1@example.com", "pass123", "Fav Game?", "Chess");
        if (success1) {
            System.out.println("Test Case 1: Registration successful for testuser1@example.com.");
        } else {
            System.out.println("Test Case 1: Registration failed for testuser1@example.com.");
        }

        // Test Case 2: Try to register the same user email (should fail)
        System.out.println("\n--- ControllerUser Test Case 2: Register Duplicate Email ---");
        boolean success2 = controller.registrarUsuario("Another User", "testuser1@example.com", "pass456", "Fav Bike?", "Mountain");
        if (!success2) {
            System.out.println("Test Case 2: Correctly failed to register duplicate email 'testuser1@example.com'.");
        } else {
            System.out.println("Test Case 2: INCORRECTLY registered duplicate email 'testuser1@example.com'.");
        }

        // Test Case 3: Register another new user
        System.out.println("\n--- ControllerUser Test Case 3: Register Second New User ---");
        boolean success3 = controller.registrarUsuario("Test User Two", "testuser2@example.com", "secureP@ss", "Fav City?", "Paris");
        if (success3) {
            System.out.println("Test Case 3: Registration successful for testuser2@example.com.");
        } else {
            System.out.println("Test Case 3: Registration failed for testuser2@example.com.");
        }
        
        // Test Case 4: Register with empty name (should fail)
        System.out.println("\n--- ControllerUser Test Case 4: Register Empty Name ---");
        boolean success4 = controller.registrarUsuario("", "emptyname@example.com", "password", "Q", "A");
        if (!success4) {
            System.out.println("Test Case 4: Correctly failed to register with empty name.");
        } else {
            System.out.println("Test Case 4: INCORRECTLY registered with empty name.");
        }

        // Test Cases for password verification
        System.out.println("\n--- ControllerUser Test Case 5: Verify Correct Password for testuser1@example.com ---");
        if (success1) { // Check if user1 was successfully registered
            boolean verifyPass1 = controller.verificarPassword("testuser1@example.com", "pass123");
            if (verifyPass1) {
                System.out.println("Test Case 5: Password verification successful for testuser1@example.com.");
            } else {
                System.err.println("Test Case 5: Password verification FAILED for testuser1@example.com with correct password.");
            }
        } else {
            System.out.println("Test Case 5: Skipped due to registration failure of testuser1@example.com.");
        }

        System.out.println("\n--- ControllerUser Test Case 6: Verify Incorrect Password for testuser1@example.com ---");
        if (success1) {
            boolean verifyPass2 = controller.verificarPassword("testuser1@example.com", "wrongpass");
            if (!verifyPass2) {
                System.out.println("Test Case 6: Correctly failed to verify incorrect password for testuser1@example.com.");
            } else {
                System.err.println("Test Case 6: INCORRECTLY verified incorrect password for testuser1@example.com.");
            }
        } else {
            System.out.println("Test Case 6: Skipped due to registration failure of testuser1@example.com.");
        }

        System.out.println("\n--- ControllerUser Test Case 7: Verify Password for Non-existent User ---");
        boolean verifyPass3 = controller.verificarPassword("nonexistent@example.com", "anypass");
        if (!verifyPass3) {
            System.out.println("Test Case 7: Correctly failed to verify password for non-existent user.");
        } else {
            System.err.println("Test Case 7: INCORRECTLY verified password for non-existent user.");
        }


        System.out.println("\nControllerUser main method testing complete. Check 'signup' table in database for results.");
        System.out.println("Remember to clear the 'signup' table or use unique emails for each full test run for predictable results.");
    }
}

