package View;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Sudhir Kushwaha
 */
public class PassWord extends javax.swing.JFrame {

    /**
     * Creates new form PassWord
     */
    public PassWord() {
        initComponents();
        txtsq.setEditable(false);
    }
PreparedStatement pst;
    Statement st=null;
    ResultSet rs;
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtpassword = new javax.swing.JTextField();
        txtans = new javax.swing.JTextField();
        txtsq = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnsearch = new javax.swing.JButton();
        lblemail = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel6.setFont(new java.awt.Font("Sitka Small", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Forgot Password  ?");

        jLabel1.setFont(new java.awt.Font("Sitka Small", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Email");

        jLabel2.setFont(new java.awt.Font("Sitka Small", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sequrity Question");

        jLabel3.setFont(new java.awt.Font("Sitka Small", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Answer");

        jLabel4.setFont(new java.awt.Font("Sitka Small", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Set Password");

        txtpassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtpassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpasswordKeyPressed(evt);
            }
        });

        txtans.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtans.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtansKeyPressed(evt);
            }
        });

        txtsq.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        txtemail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtemailKeyReleased(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 0, 51));
        jButton2.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(240, 240, 240));
        jButton2.setText("Submit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(204, 0, 51));
        jButton3.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(240, 240, 240));
        jButton3.setText("Back To Login Page");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnsearch.setBackground(new java.awt.Color(204, 0, 51));
        btnsearch.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        btnsearch.setForeground(new java.awt.Color(240, 240, 240));
        btnsearch.setText("Search");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        lblemail.setForeground(new java.awt.Color(204, 204, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txtsq, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtans, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jButton2)
                        .addGap(23, 23, 23)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnsearch))
                            .addComponent(lblemail, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(158, 158, 158))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblemail, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnsearch)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsq, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtans, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 270, 530, 340));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/green-natural-background-vector-illustration-59110.jpg"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 new SignIn().setVisible(true);     
 
//dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","Sudhir@123");
            //st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst=con.prepareStatement("select sq from signup where email=?");
            pst.setString(1, txtemail.getText());
            rs=pst.executeQuery();
            if(rs.next()){
                txtsq.setText(rs.getString("sq"));
                }
            else
                JOptionPane.showMessageDialog(this,"Email id not exist");
            txtans.setText("");
            txtpassword.setText("");
        } catch (ClassNotFoundException | SQLException ex) {
           // Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsearchActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    if(txtsq.getText().equals("")){
        JOptionPane.showMessageDialog(this,"Enter Email and Search it");   
        txtemail.requestFocus();
    }
    else if(txtans.getText().equals("")){
        JOptionPane.showMessageDialog(this,"Enter vaild Answer");
        txtans.requestFocus();
    }
    else if(txtpassword.getText().equals("")){
        JOptionPane.showMessageDialog(this," Password Field Not Empity");
        txtpassword.requestFocus();
    }
    else{
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        java.sql.Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","Sudhir@123");
        //st=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        pst=conn.prepareStatement("select * from signup where answer=? and email=?");
        pst.setString(1, txtans.getText());
        pst.setString(2, txtemail.getText());
        rs=pst.executeQuery();
        if(rs.next()){
            try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","Sudhir@123");
            pst=con.prepareStatement("update signup set password=? where email=?");
            pst.setString(1, txtpassword.getText());
            pst.setString(2, txtemail.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Password Reset\nLogin Now","Success",JOptionPane.INFORMATION_MESSAGE);
            new SignIn().setVisible(true); 
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PassWord.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else{
            JOptionPane.showMessageDialog(this, "Wrong Answer Entery","Wrong",JOptionPane.WARNING_MESSAGE);
            txtpassword.setText("");
            txtans.setText("");
        }
    }catch(Exception e){
        
    }
        
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtansKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtansKeyPressed
if(evt.getKeyCode()==KeyEvent.VK_ENTER) 
    txtpassword.requestFocus();// TODO add your handling code here:
    }//GEN-LAST:event_txtansKeyPressed

    private void txtpasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpasswordKeyPressed
if(evt.getKeyCode()==KeyEvent.VK_ENTER){
    if(txtsq.getText().equals("")){
        JOptionPane.showMessageDialog(this,"Enter Email and Search it");   
        txtemail.requestFocus();
    }
    else if(txtsq.getText().equals("")){
        JOptionPane.showMessageDialog(this,"Enter vaild Answer");
        txtans.requestFocus();
    }
    else if(txtpassword.getText().equals("")){
        JOptionPane.showMessageDialog(this,"set Password Field Not Empity");
        txtpassword.requestFocus();
    }
    else{
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        java.sql.Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","Sudhir@123");
        st=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        pst=conn.prepareStatement("select * from signup where answer=?");
        pst.setString(1, txtans.getText());
        rs=pst.executeQuery();
        if(rs.next()){
           //txtsq.setText(rs.getString("answer"));
           //}
            try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","Sudhir@123");
            pst=con.prepareStatement("update signup set password=? where email=?");
            pst.setString(1, txtpassword.getText());
            pst.setString(2, txtemail.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Password Reset\nLogin now","Success",JOptionPane.INFORMATION_MESSAGE);
            new SignIn().setVisible(true); 

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PassWord.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else{
            JOptionPane.showMessageDialog(this, "Wrong Answer Entery","Wrong",JOptionPane.WARNING_MESSAGE);
            txtpassword.setText("");
            txtans.setText("");
            txtans.requestFocus();
        }
    }catch(Exception e){
        
    }
        
    }
}// TODO add your handling code here:
    }//GEN-LAST:event_txtpasswordKeyPressed

    private void txtemailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyReleased
txtemail.setText(txtemail.getText().toLowerCase()); 

     int a=txtemail.getText().indexOf('@');
     int b=txtemail.getText().length();
     
      if(a == -1){
          lblemail.setText("Invalied Email id");
      }
      else if (b>a+1){
      String s=txtemail.getText();
      String[] splitString = s.split("@");
      if(splitString[1].equalsIgnoreCase("gmail.com")){
      lblemail.setText("");
      txtpassword.requestFocus();
      }
      else
         lblemail.setText("Invalied Email id");
      }  
      if(txtemail.getText().equals(""))
          lblemail.setText("");

    }//GEN-LAST:event_txtemailKeyReleased

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
            java.util.logging.Logger.getLogger(PassWord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PassWord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PassWord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PassWord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PassWord().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnsearch;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblemail;
    private javax.swing.JTextField txtans;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtpassword;
    private javax.swing.JTextField txtsq;
    // End of variables declaration//GEN-END:variables
}
