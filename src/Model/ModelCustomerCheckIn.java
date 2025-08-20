package Model;

import DbConnection.DbConnection;
import ModelData.Cola;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelCustomerCheckIn {

    public Cola getAvailableRooms(String roomType, String bed) {
        Cola rooms = new Cola();
        Connection con = DbConnection.conectar();
        PreparedStatement pst = null;
        ResultSet rs = null;
        if (con != null) {
            try {
                pst = con.prepareStatement("SELECT roomnumber FROM room WHERE status = ? AND roomtype = ? AND bed = ?");
                pst.setString(1, "Not Booked");
                pst.setString(2, roomType);
                pst.setString(3, bed);
                rs = pst.executeQuery();
                while (rs.next()) {
                    rooms.enqueue(rs.getString("roomnumber"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pst != null) pst.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return rooms;
    }

    public String getRoomPrice(String roomNumber) {
        String price = "";
        Connection con = DbConnection.conectar();
        PreparedStatement pst = null;
        ResultSet rs = null;
        if (con != null) {
            try {
                pst = con.prepareStatement("SELECT price FROM room WHERE roomnumber = ?");
                pst.setString(1, roomNumber);
                rs = pst.executeQuery();
                if (rs.next()) {
                    price = rs.getString("price");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pst != null) pst.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return price;
    }

    public boolean allotRoom(String name, String mobile, String email, String gender, String address, String id, String nationality, String date, String roomNumber, String bed, String roomType, String price) {
        boolean success = false;
        Connection con = DbConnection.conectar();
        PreparedStatement pst = null;
        if (con != null) {
            try {
                // Insert into customer table
                pst = con.prepareStatement("INSERT INTO customer(name, mobile, email, gender, address, id, nationality, date, roomnumber, bed, roomtype, price, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                pst.setString(1, name);
                pst.setString(2, mobile);
                pst.setString(3, email.toLowerCase());
                pst.setString(4, gender);
                pst.setString(5, address);
                pst.setString(6, id);
                pst.setString(7, nationality);
                pst.setString(8, date);
                pst.setString(9, roomNumber);
                pst.setString(10, bed);
                pst.setString(11, roomType);
                pst.setString(12, price);
                pst.setString(13, "NULL");
                pst.executeUpdate();
                pst.close();

                // Update room status
                pst = con.prepareStatement("UPDATE room SET status = ? WHERE roomnumber = ?");
                pst.setString(1, "Booked");
                pst.setString(2, roomNumber);
                pst.executeUpdate();
                
                success = true;

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (pst != null) pst.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }
}
