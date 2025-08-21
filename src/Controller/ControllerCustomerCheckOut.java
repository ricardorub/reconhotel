package Controller;

import ModelData.ArbolBinario;
import View.CustomerCheckOut;
import View.CustomerDetailsBill;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControllerCustomerCheckOut implements ActionListener, MouseListener {

    private CustomerCheckOut view;

    public ControllerCustomerCheckOut(CustomerCheckOut view) {
        this.view = view;
        this.view.getBtnSearch().addActionListener(this);
        this.view.getBtnCheckOut().addActionListener(this);
        this.view.getBtnClear().addActionListener(this);
        this.view.getBtnClose().addActionListener(this);
        this.view.getJTable1().addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnSearch()) {
            searchCustomer();
        } else if (e.getSource() == view.getBtnCheckOut()) {
            checkOutCustomer();
        } else if (e.getSource() == view.getBtnClear()) {
            clearFields();
        } else if (e.getSource() == view.getBtnClose()) {
            view.dispose();
        }
    }

    public void refreshTableData() {
        try {
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoteljava", "root", "root");
            PreparedStatement pst = con.prepareStatement("Select * from customer where status=?");
            pst.setString(1, "NULL");
            ResultSet rs = pst.executeQuery();
            
            DefaultTableModel model = (DefaultTableModel) view.getJTable1().getModel();
            model.setRowCount(0);
            
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("name"));
                row.add(rs.getString("mobile"));
                row.add(rs.getString("email"));
                row.add(rs.getString("date"));
                row.add(rs.getString("nationality"));
                row.add(rs.getString("gender"));
                row.add(rs.getString("id"));
                row.add(rs.getString("address"));
                row.add(rs.getString("roomnumber"));
                row.add(rs.getString("bed"));
                row.add(rs.getString("roomtype"));
                row.add(rs.getString("price"));
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Database error refreshing table: " + e.getMessage());
        }
    }

    public void searchCustomer() {
        view.getTxtName().setText("");
        view.getTxtEmail().setText("");
        view.getTxtMobile().setText("");
        view.getTxtPrice().setText("");
        view.getTxtDate().setText("");

        // Create and populate the binary tree
        ArbolBinario roomTree = new ArbolBinario();
        DefaultTableModel model = (DefaultTableModel) view.getJTable1().getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            // Room number is in column 8
            Object roomNumObj = model.getValueAt(i, 8);
            if (roomNumObj != null) {
                String roomNumStr = roomNumObj.toString();
                if (!roomNumStr.trim().isEmpty()) {
                    try {
                        roomTree.insert(Integer.parseInt(roomNumStr.trim()));
                    } catch (NumberFormatException e) {
                        System.err.println("Could not parse room number: " + roomNumStr);
                    }
                }
            }
        }

        String searchRoomNumStr = view.getTxtRoomNumber().getText().trim();
        if (searchRoomNumStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter a room number.");
            return;
        }

        try {
            int searchRoomNum = Integer.parseInt(searchRoomNumStr);
            if (roomTree.search(searchRoomNum) != null) {
                // Room found in tree, now get details from DB
                PreparedStatement pst = null;
                ResultSet rs = null;
                java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoteljava", "root", "root");
                pst = con.prepareStatement("select name,mobile,email,date,price from customer where roomnumber=? AND status=?");
                pst.setString(1, searchRoomNumStr);
                pst.setString(2, "NULL");
                rs = pst.executeQuery();
                if (rs.next()) {
                    view.getTxtName().setText(rs.getString("name"));
                    view.getTxtEmail().setText(rs.getString("email"));
                    view.getTxtMobile().setText(rs.getString("mobile"));
                    view.getTxtDate().setText(rs.getString("date"));
                    view.getTxtPrice().setText(rs.getString("price"));

                    ZoneId z = ZoneId.of("Asia/Colombo");
                    LocalDate todays = LocalDate.now(z);
                    String s1 = todays.toString();
                    SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
                    String f1 = rs.getString("date");
                    String f2 = s1;
                    try {
                        Date d1 = sim.parse(f1);
                        Date d2 = sim.parse(f2);
                        long diff = d2.getTime() - d1.getTime();
                        int days = (int) (diff / (1000 * 24 * 60 * 60));
                        if (days == 0) {
                            days = 1;
                        }
                        view.getTxtDays().setText(String.valueOf(days));
                        
                        double p = Double.parseDouble(rs.getString("price"));
                        double pri = days * p;
                        view.getTxtAmount().setText(String.valueOf(pri));
                    } catch (Exception ex) {
                        // ignore date parsing errors
                    }
                } else {
                     JOptionPane.showMessageDialog(view, "Record Not Found in DB, but was in table tree.");
                }

            } else {
                // Room not found in tree
                view.getTxtDays().setText("");
                view.getTxtAmount().setText("");
                JOptionPane.showMessageDialog(view, "Record Not Found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Invalid room number format.");
        } catch (SQLException ex) {
            view.getTxtDays().setText("");
            view.getTxtAmount().setText("");
            JOptionPane.showMessageDialog(view, "Database error: " + ex.getMessage());
        }
    }

    public void checkOutCustomer() {
        if (view.getTxtName().getText().equals("")) {
            JOptionPane.showMessageDialog(view, "Please Enter Room Number And Search it,Then Check Out Customer");
        } else {
            try {
                PreparedStatement pst = null;
                Class.forName("com.mysql.cj.jdbc.Driver");
                java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoteljava", "root", "root");
                pst = con.prepareStatement("update customer set status=? where roomnumber=?");
                pst.setString(1, "check out");
                pst.setString(2, view.getTxtRoomNumber().getText());
                pst.executeUpdate();
                pst = con.prepareStatement("update customer set amount=?,outdate=?,days=? where roomnumber=? AND date=?");
                pst.setString(1, view.getTxtAmount().getText());
                pst.setString(2, view.getTxtOutDate().getText());
                pst.setString(3, view.getTxtDays().getText());
                pst.setString(4, view.getTxtRoomNumber().getText());
                pst.setString(5, view.getTxtDate().getText());
                pst.executeUpdate();
                pst = con.prepareStatement("update room set status=? where roomnumber=?");
                pst.setString(1, "Not Booked");
                pst.setString(2, view.getTxtRoomNumber().getText());
                pst.executeUpdate();

                int yes = JOptionPane.showConfirmDialog(view, "Check out Successfully.\nDo you want to see & print bill?", "Check outed", JOptionPane.YES_NO_OPTION);
                if (JOptionPane.YES_OPTION == yes) {
                   new CustomerDetailsBill().setVisible(true);
                } else {
                    refreshTableData();
                    clearFields();
                }
            } catch (ClassNotFoundException | SQLException e) {
                 JOptionPane.showMessageDialog(view, "Database error during checkout: " + e.getMessage());
            }
        }
    }
    
    public void clearFields() {
        view.getTxtName().setText("");
        view.getTxtEmail().setText("");
        view.getTxtMobile().setText("");
        view.getTxtDate().setText("");
        view.getTxtPrice().setText("");
        view.getTxtDays().setText("");
        view.getTxtAmount().setText("");
        view.getTxtRoomNumber().setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == view.getJTable1()) {
            int selectedRow = view.getJTable1().getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) view.getJTable1().getModel();
            
            view.getTxtName().setText(model.getValueAt(selectedRow, 0).toString());
            view.getTxtMobile().setText(model.getValueAt(selectedRow, 1).toString());
            view.getTxtEmail().setText(model.getValueAt(selectedRow, 2).toString());
            view.getTxtDate().setText(model.getValueAt(selectedRow, 3).toString());
            view.getTxtRoomNumber().setText(model.getValueAt(selectedRow, 8).toString());
            view.getTxtPrice().setText(model.getValueAt(selectedRow, 11).toString());
            
            ZoneId z = ZoneId.of("Asia/Colombo");
            LocalDate todays = LocalDate.now(z);
            String s1 = todays.toString();
            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
            String f1 = model.getValueAt(selectedRow, 3).toString();
            String f2 = s1;
            try {
                Date d1 = sim.parse(f1);
                Date d2 = sim.parse(f2);
                long diff = d2.getTime() - d1.getTime();
                int days = (int) (diff / (1000 * 24 * 60 * 60));
                if (days == 0) {
                    days = 1;
                }
                view.getTxtDays().setText(String.valueOf(days));
                
                double p = Double.parseDouble(model.getValueAt(selectedRow, 11).toString());
                double pri = days * p;
                view.getTxtAmount().setText(String.valueOf(pri));
            } catch (Exception ex) {
                // Handle parsing exception
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
