package Controller;

import View.SignIn; 
import View.Signup;
import View.PassWord;

/**
 *
 * @author Lenovo (or other original author)
 */
// Main.java
// Main class to launch the application

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SignIn signInFrame = new SignIn();
                signInFrame.setVisible(true);
            }
        });
    }
}
