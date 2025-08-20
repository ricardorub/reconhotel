package View;

import Controller.ControllerCustomerCheckIn;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class CustomerCheckIn extends javax.swing.JFrame {

    private ControllerCustomerCheckIn controller;
    
    public CustomerCheckIn() {
        initComponents();
        SimpleDateFormat dat = new SimpleDateFormat("yyyy/MM/dd ");
        Date d = new Date();
        txtdate.setText(dat.format(d));
        txtname.requestFocus();
    }

    public void setController(ControllerCustomerCheckIn controller) {
        this.controller = controller;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        txtmob = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        combogender = new javax.swing.JComboBox<>();
        txtnat = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtadhar = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtaddres = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtdate = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        combobed = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        comboroomtype = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        comboroomnumber = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txtprice = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        lblemail = new javax.swing.JLabel();
        h = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/checked.png"))); // NOI18N
        jLabel1.setText("Customer Check IN");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 220, 70));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Name");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 380, 35));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mobile Number");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 291, 36));

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Email");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 380, 37));

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Gender");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 380, 27));

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nationality");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 440, 390, 29));
        getContentPane().add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 390, 42));
        getContentPane().add(txtmob, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 390, 40));
        getContentPane().add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 390, 40));

        combogender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Other" }));
        getContentPane().add(combogender, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, 390, 40));
        getContentPane().add(txtnat, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 480, 390, 39));

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Adhar Number");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 530, 294, 38));
        getContentPane().add(txtadhar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 570, 390, 42));

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Address");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 630, 294, 34));
        getContentPane().add(txtaddres, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 680, 390, 38));

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Check IN Date(Today)");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 80, 294, 40));
        getContentPane().add(txtdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 130, 350, 38));

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Bed");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 270, 257, 28));

        combobed.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single", "Double" }));
        getContentPane().add(combobed, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 320, 350, 37));

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Room Type");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 170, 257, 40));

        comboroomtype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "NON AC" }));
        getContentPane().add(comboroomtype, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, 350, 40));

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Room Number");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 370, 340, 40));
        getContentPane().add(comboroomnumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 420, 350, 40));

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Price");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 490, 350, 30));
        getContentPane().add(txtprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 540, 350, 39));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/close.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 0, 50, -1));

        jButton2.setBackground(new java.awt.Color(255, 0, 102));
        jButton2.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Allote Room");
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 640, -1, 40));

        jButton3.setBackground(new java.awt.Color(153, 0, 51));
        jButton3.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Clear");
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 640, 90, 40));
        getContentPane().add(lblemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 390, 20));

        h.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bac.jpg"))); // NOI18N
        getContentPane().add(h, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 770));

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        dispose();
    }                                        

    // Getters for controller
    public JComboBox<String> getComboBed() { return combobed; }
    public JComboBox<String> getComboGender() { return combogender; }
    public JComboBox<String> getComboRoomNumber() { return comboroomnumber; }
    public JComboBox<String> getComboRoomType() { return comboroomtype; }
    public JButton getBtnAlloteRoom() { return jButton2; }
    public JButton getBtnClear() { return jButton3; }
    public JTextField getTxtAddres() { return txtaddres; }
    public JTextField getTxtAdhar() { return txtadhar; }
    public JTextField getTxtDate() { return txtdate; }
    public JTextField getTxtEmail() { return txtemail; }
    public JTextField getTxtMob() { return txtmob; }
    public JTextField getTxtName() { return txtname; }
    public JTextField getTxtNat() { return txtnat; }
    public JTextField getTxtPrice() { return txtprice; }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combobed;
    private javax.swing.JComboBox<String> combogender;
    private javax.swing.JComboBox<String> comboroomnumber;
    private javax.swing.JComboBox<String> comboroomtype;
    private javax.swing.JLabel h;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblemail;
    private javax.swing.JTextField txtaddres;
    private javax.swing.JTextField txtadhar;
    private javax.swing.JTextField txtdate;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtmob;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtnat;
    private javax.swing.JTextField txtprice;
    // End of variables declaration//GEN-END:variables
}
