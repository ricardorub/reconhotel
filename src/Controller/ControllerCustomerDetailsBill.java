package Controller;

import Model.ModelCustomerBill;
import ModelData.ListaSimple;
import ModelData.Nodo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class ControllerCustomerDetailsBill {

    private ListaSimple billList;

    public ControllerCustomerDetailsBill() {
        billList = new ListaSimple();
        loadBillData();
    }

    private void loadBillData() {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "Sudhir@123");
            pst = con.prepareStatement("SELECT * FROM customer WHERE status=?");
            pst.setString(1, "check out");
            rs = pst.executeQuery();
            while (rs.next()) {
                ModelCustomerBill bill = new ModelCustomerBill(
                    rs.getString("billid"),
                    rs.getString("roomnumber"),
                    rs.getString("name"),
                    rs.getString("mobile"),
                    rs.getString("nationality"),
                    rs.getString("gender"),
                    rs.getString("email"),
                    rs.getString("id"),
                    rs.getString("address"),
                    rs.getString("date"),
                    rs.getString("outdate"),
                    rs.getString("bed"),
                    rs.getString("roomtype"),
                    rs.getString("price"),
                    rs.getString("days"),
                    rs.getString("amount")
                );
                billList.insert(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public DefaultTableModel getAllBills() {
        return createTableModel(billList.getHead());
    }

    public DefaultTableModel searchBillsByDate(String date) {
        ListaSimple filteredList = new ListaSimple();
        Nodo current = billList.getHead();
        while (current != null) {
            ModelCustomerBill bill = (ModelCustomerBill) current.getData();
            if (bill.getOutdate().equals(date)) {
                filteredList.insert(bill);
            }
            current = current.getNext();
        }
        return createTableModel(filteredList.getHead());
    }

    private DefaultTableModel createTableModel(Nodo head) {
        DefaultTableModel model = new DefaultTableModel();
        String[] columns = {"Bill ID", "Room Number", "Name", "Mobile Number", "Nationality", "Gender", "Email", "Adhar", "Address", "Check in Datel", "Ckeck out Date", "Bed", "Room Type", "Price", "Number of Days", "Total Amount"};
        for (String column : columns) {
            model.addColumn(column);
        }

        Nodo current = head;
        while (current != null) {
            ModelCustomerBill bill = (ModelCustomerBill) current.getData();
            Vector<String> row = new Vector<>();
            row.add(bill.getBillid());
            row.add(bill.getRoomnumber());
            row.add(bill.getName());
            row.add(bill.getMobile());
            row.add(bill.getNationality());
            row.add(bill.getGender());
            row.add(bill.getEmail());
            row.add(bill.getId());
            row.add(bill.getAddress());
            row.add(bill.getDate());
            row.add(bill.getOutdate());
            row.add(bill.getBed());
            row.add(bill.getRoomtype());
            row.add(bill.getPrice());
            row.add(bill.getDays());
            row.add(bill.getAmount());
            model.addRow(row);
            current = current.getNext();
        }
        return model;
    }
}

