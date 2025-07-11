package View;

import Controller.ControllerUser; // Import ControllerUser
// import Controller.ControllerAdmin; // Only if admin login is also handled here
import java.awt.event.KeyEvent; // For VK_ENTER
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
// Removed java.sql imports as they are no longer needed for basic login
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.DriverManager;
// import java.sql.SQLException;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Sudhir Kushwaha
 */
public class SignIn extends javax.swing.JFrame {

    int flag=0;
    private ControllerUser controllerUser; // Instance of ControllerUser
    // private ControllerAdmin controllerAdmin; // If handling admin login too

    /**
     * Creates new form SignIn
     */
    public SignIn() {
        initComponents();
        txtemail.requestFocus();
        controllerUser = new ControllerUser();
        // controllerAdmin = new ControllerAdmin(); // If handling admin login too
       }

    private void performLogin() {
        String email = txtemail.getText();
        String password = new String(txtpassword.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email and Password are required.", "Error", JOptionPane.ERROR_MESSAGE);
            txtemail.requestFocus();
            return;
        }

        // Hardcoded admin check (remains as per original logic for View.Admin)
        if (email.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
            new Admin().setVisible(true); // This refers to View.Admin
            dispose();
            return;
        }

        // Attempt to login as a regular user
        boolean passwordMatches = controllerUser.verificarPassword(email, password);

        if (passwordMatches) {
            // Password is correct, now check approval status (logic adapted from original)
            // This status check ideally should be part of a more comprehensive user service
            // For now, we'll query it directly after successful password verification.
            // This part still uses direct JDBC as ControllerUser doesn't yet have a method for this.
            // This should be refactored into ControllerUser later.
            try {
                Class.forName(controllerUser.getDbDriverProperty()); // Assuming ControllerUser can provide this
                java.sql.Connection con = java.sql.DriverManager.getConnection(
                    controllerUser.getDbUrlProperty(), 
                    controllerUser.getDbUserProperty(), 
                    controllerUser.getDbPasswordProperty()
                );
                java.sql.PreparedStatement pstCheckStatus = con.prepareStatement("SELECT status FROM signup WHERE email = ?");
                pstCheckStatus.setString(1, email.toLowerCase());
                java.sql.ResultSet rsStatus = pstCheckStatus.executeQuery();

                if (rsStatus.next()) {
                    String status = rsStatus.getString("status");
                    if ("approved".equalsIgnoreCase(status)) {
                        new Home().setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Login successful, but account is pending admin approval.", "Pending Approval", JOptionPane.INFORMATION_MESSAGE);
                        txtemail.requestFocus();
                    }
                } else {
                    // This case should ideally not happen if verificarPassword was true,
                    // means user exists but somehow status couldn't be fetched.
                     JOptionPane.showMessageDialog(this, "Login failed. User data inconsistent.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                if (rsStatus != null) rsStatus.close();
                if (pstCheckStatus != null) pstCheckStatus.close();
                if (con != null) con.close();
            } catch (ClassNotFoundException | java.sql.SQLException ex) {
                Logger.getLogger(SignIn.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Database error during status check.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Incorrect Email ID or Password.", "Login Failed", JOptionPane.WARNING_MESSAGE);
            txtemail.requestFocus();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        txtpassword = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblemail = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/close.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1309, 0, 60, 40));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Sign in");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 2, -1, -1));

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setFont(new java.awt.Font("Sitka Display", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Sign Up Now");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 183, -1, 32));

        jLabel1.setFont(new java.awt.Font("Sitka Display", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Email");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 67, 80, 30));

        txtemail.setFont(new java.awt.Font("Sitka Display", 1, 18)); // NOI18N
        txtemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtemailActionPerformed(evt);
            }
        });
        txtemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtemailKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtemailKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtemailKeyTyped(evt);
            }
        });
        jPanel1.add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 62, 264, 41));

        txtpassword.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        txtpassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpasswordKeyPressed(evt);
            }
        });
        jPanel1.add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 109, 264, 40));

        jLabel2.setFont(new java.awt.Font("Sitka Display", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Password");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 131, -1, 30));

        jButton3.setBackground(new java.awt.Color(255, 51, 51));
        jButton3.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Forgot Password");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 183, -1, 32));

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setFont(new java.awt.Font("Sitka Display", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Login");
        jButton1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1MouseMoved(evt);
            }
        });
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 184, -1, 30));

        // Assuming h.png should be in /image/ like other icons. User needs to add h.png to src/image/
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/h.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, 36, 31));

        lblemail.setForeground(new java.awt.Color(255, 255, 0));
        jPanel1.add(lblemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 39, 264, 14));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 270, 420, 230));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/green-natural-background-vector-illustration-59110.jpg"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtemailActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        performLogin();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    int yes=JOptionPane.showConfirmDialog(this, "Are You Really Close this Application ?","Exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(yes==JOptionPane.YES_OPTION){
            System.exit(0);
        }       
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    new Signup().setVisible(true);       
    //dispose(); 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    new PassWord().setVisible(true);

    //dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtemailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyPressed
    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        txtpassword.requestFocus();
    }// TODO add your handling code here:
    }//GEN-LAST:event_txtemailKeyPressed

    private void txtpasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpasswordKeyPressed
    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        performLogin();
    }// TODO add your handling code here:
    }//GEN-LAST:event_txtpasswordKeyPressed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked

    // User needs to add s.png and h.png to src/image/ directory
    if(flag==0){
       // Attempt to load s.png from classpath
       java.net.URL sPngUrl = getClass().getResource("/image/s.png");
       if (sPngUrl != null) {
           jLabel4.setIcon(new ImageIcon(sPngUrl));
       } else {
           System.err.println("Error: /image/s.png not found!");
           // Optionally, set text or a default icon if image is missing
           // jLabel4.setText("S"); 
       }
       flag=1;
       txtpassword.setEchoChar((char)0);
    }
    else
    {
        // Attempt to load h.png from classpath
        java.net.URL hPngUrl = getClass().getResource("/image/h.png");
        if (hPngUrl != null) {
            jLabel4.setIcon(new ImageIcon(hPngUrl));
        } else {
            System.err.println("Error: /image/h.png not found!");
            // Optionally, set text or a default icon if image is missing
            // jLabel4.setText("H");
        }
        flag=0;
        txtpassword.setEchoChar('*');
         
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jButton1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseMoved
    }//GEN-LAST:event_jButton1MouseMoved

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
    }//GEN-LAST:event_jButton1MouseExited

    private void txtemailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyReleased
         txtemail.setText(txtemail.getText().toLowerCase()); 

         int a=txtemail.getText().indexOf('@');
         int b=txtemail.getText().length();
         
          if(a == -1 && !txtemail.getText().equalsIgnoreCase("admin")){ // Allow "admin" without @
              lblemail.setText("Invalid Email id");
          }
          else if (b>a+1 || txtemail.getText().equalsIgnoreCase("admin")){ // Allow "admin" or check domain
            if (txtemail.getText().equalsIgnoreCase("admin")) {
                 lblemail.setText("");
                 txtpassword.requestFocus();
            } else {
              String s=txtemail.getText();
              String[] splitString = s.split("@");
              if(splitString.length > 1 && splitString[1].equalsIgnoreCase("gmail.com")){ // Basic validation
                  lblemail.setText("");
                  txtpassword.requestFocus();
              } else {
                 lblemail.setText("Invalid Email id");
              }
            }
          }  
          if(txtemail.getText().equals(""))
              lblemail.setText("");

    }//GEN-LAST:event_txtemailKeyReleased

    private void txtemailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyTyped
          
    }//GEN-LAST:event_txtemailKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new SignIn().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblemail;
    private javax.swing.JTextField txtemail;
    private javax.swing.JPasswordField txtpassword;
    // End of variables declaration//GEN-END:variables
}
