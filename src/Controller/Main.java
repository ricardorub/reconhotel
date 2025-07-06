package Controller;

import View.SignIn; // Ensure this import is present

/**
 *
 * @author Lenovo (or other original author)
 */
// Main.java
// Main class to launch the application

public class Main {
    public static void main(String[] args) {
        // Launch the SignIn GUI
        // SwingUtilities.invokeLater for thread safety with Swing
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SignIn signInFrame = new SignIn();
                signInFrame.setVisible(true);
            }
        });
    }
}
