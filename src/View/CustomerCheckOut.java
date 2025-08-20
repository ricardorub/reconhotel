package View;

import Controller.ControllerCustomerCheckOut;
import ModelData.ArbolBinario;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.beans.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CustomerCheckOut extends javax.swing.JFrame {

    private ControllerCustomerCheckOut controller;

    public CustomerCheckOut() {
        initComponents();
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd ");
        Date d = new Date();
        txtoutdate.setText(date.format(d));
        s();
    }

    public void s() {
        Statement st = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        java.sql.Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoteljava", "root", "root");
            pst = con.prepareStatement("Select * from customer where status=?");
            pst.setString(1, "NULL");
            rs = pst.executeQuery();
            DefaultTableModel RecordTable = (DefaultTableModel) jTable1.getModel();
            RecordTable.setRowCount(0);
            while (rs.next()) {
                Vector columnData = new Vector();
                columnData.add(rs.getString("name"));
                columnData.add(rs.getString("mobile"));
                columnData.add(rs.getString("email"));
                columnData.add(rs.getString("date"));
                columnData.add(rs.getString("nationality"));
                columnData.add(rs.getString("gender"));
                columnData.add(rs.getString("id"));
                columnData.add(rs.getString("address"));
                columnData.add(rs.getString("roomnumber"));
                columnData.add(rs.getString("bed"));
                columnData.add(rs.getString("roomtype"));
                columnData.add(rs.getString("price"));
                RecordTable.addRow(columnData);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database error in s(): " + e.getMessage());
        }
    }

    public void setController(ControllerCustomerCheckOut controller) {
        this.controller = controller;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtroomnumber = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtdate = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtoutdate = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtmobile = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtdays = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtprice = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtamount = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/out.jpg"))); // NOI18N
        jLabel1.setText("Customer Check Out");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 70));
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 0, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Room Number");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, -1, 28));

        txtroomnumber.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        getContentPane().add(txtroomnumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 104, 40));

        jButton2.setBackground(new java.awt.Color(153, 0, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(240, 240, 240));
        jButton2.setText("Search");
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, -1, 40));

        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Mobile Number", "Email ID", "Check IN Date", "Nationality", "Gender", "Adhar Number", "Address", "Room Number", "Bed", "Room Type", "Price Per Day"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 471, 1140, 300));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Customer Name");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 232, 43));

        txtname.setEditable(false);
        txtname.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 250, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Check in Date");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 240, 36));

        txtdate.setEditable(false);
        txtdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(txtdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 249, 43));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Check out Date(Today)");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 320, 260, 36));

        txtoutdate.setEditable(false);
        txtoutdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(txtoutdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 370, 250, 37));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Customer Mobile Number");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, 250, 36));

        txtmobile.setEditable(false);
        txtmobile.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(txtmobile, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, 250, 39));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Price Per Day");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 242, 40));

        txtdays.setEditable(false);
        txtdays.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(txtdays, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 260, 249, 38));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Number of Days");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 210, 249, 50));

        txtprice.setEditable(false);
        txtprice.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(txtprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 250, 38));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Total Amount to Collect From Customer");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 220, 282, 37));

        txtamount.setEditable(false);
        txtamount.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(txtamount, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 260, 270, 40));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Email");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 120, 250, 36));

        txtemail.setEditable(false);
        txtemail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 170, 270, 40));

        jButton3.setBackground(new java.awt.Color(153, 0, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Check Out");
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 370, 130, 40));

        jButton4.setBackground(new java.awt.Color(153, 0, 0));
        jButton4.setFont(new java.awt.Font("Sitka Banner", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Clear");
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 370, 80, 40));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bac.jpg"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        DefaultTableModel RecordTable = (DefaultTableModel) jTable1.getModel();
        int SelectedRows = jTable1.getSelectedRow();
        txtname.setText(RecordTable.getValueAt(SelectedRows, 0).toString());
        txtmobile.setText(RecordTable.getValueAt(SelectedRows, 1).toString());
        txtemail.setText(RecordTable.getValueAt(SelectedRows, 2).toString());
        txtdate.setText(RecordTable.getValueAt(SelectedRows, 3).toString());
        txtroomnumber.setText(RecordTable.getValueAt(SelectedRows, 8).toString());
        txtprice.setText(RecordTable.getValueAt(SelectedRows, 11).toString());
        
            ZoneId z=ZoneId.of("Asia/Colombo");
            LocalDate todays=LocalDate.now(z);
            String s1=todays.toString();
            SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
            String f1=RecordTable.getValueAt(SelectedRows, 3).toString();
            String f2=s1;
            try{
                Date d1=sim.parse(f1);
                Date d2=sim.parse(f2);
                long diff=d2.getTime()-d1.getTime();
                int days=(int)(diff/(1000*24*60*60));
                if(days==0)
                    txtdays.setText("1");
                else
                    txtdays.setText(String.valueOf(days));
                double p=Double.parseDouble(RecordTable.getValueAt(SelectedRows,11).toString());
                double pri=days*p;
                if(days==0)
                    txtamount.setText(String.valueOf(p));
                else
                    txtamount.setText(String.valueOf(pri));
            }catch(Exception e){
            }
    }//GEN-LAST:event_jTable1MouseClicked
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerCheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            CustomerCheckOut view = new CustomerCheckOut();
            ControllerCustomerCheckOut controller = new ControllerCustomerCheckOut(view);
            view.setController(controller);
            view.setVisible(true);
        });
    }

    public JButton getBtnCheckOut() { return jButton3; }
    public JButton getBtnClear() { return jButton4; }
    public JButton getBtnClose() { return jButton1; }
    public JButton getBtnSearch() { return jButton2; }
    public JTable getJTable1() { return jTable1; }
    public JTextField getTxtAmount() { return txtamount; }
    public JTextField getTxtDate() { return txtdate; }
    public JTextField getTxtDays() { return txtdays; }
    public JTextField getTxtEmail() { return txtemail; }
    public JTextField getTxtMobile() { return txtmobile; }
    public JTextField getTxtName() { return txtname; }
    public JTextField getTxtOutDate() { return txtoutdate; }
    public JTextField getTxtPrice() { return txtprice; }
    public JTextField getTxtRoomNumber() { return txtroomnumber; }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtamount;
    private javax.swing.JTextField txtdate;
    private javax.swing.JTextField txtdays;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtmobile;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtoutdate;
    private javax.swing.JTextField txtprice;
    private javax.swing.JTextField txtroomnumber;
    // End of variables declaration//GEN-END:variables
}
