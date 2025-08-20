package Controller;

import DbConnection.DbConnection;
import Model.ModelManageRoom;
import ModelData.TablaHash;
import View.ManageRoom;
import java.sql.*;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class ControllerManageRoom {
    private ManageRoom view;
    private TablaHash tablaHash;

    public ControllerManageRoom(ManageRoom view) {
        this.view = view;
        this.tablaHash = new TablaHash(100);
        cargarRooms();
        this.view.populateTable(obtenerTodosLosRooms());
    }

    public void cargarRooms() {
        Connection con = DbConnection.conectar();
        if (con == null) {
            JOptionPane.showMessageDialog(view, "Failed to connect to the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement("SELECT * FROM room");
            rs = pst.executeQuery();
            while (rs.next()) {
                ModelManageRoom room = new ModelManageRoom(
                        rs.getString("roomnumber"),
                        rs.getString("roomtype"),
                        rs.getString("bed"),
                        rs.getDouble("price"),
                        rs.getString("status")
                );
                tablaHash.insertar(room);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeDbResources(con, pst, rs);
        }
    }

    public LinkedList<ModelManageRoom> obtenerTodosLosRooms() {
        return tablaHash.obtenerTodos();
    }

    public void addRoom(String roomNumber, String roomType, String bed, double price) {
        if (tablaHash.buscar(roomNumber) != null) {
            JOptionPane.showMessageDialog(view, "Room Number Already Exists");
            return;
        }

        Connection con = DbConnection.conectar();
        if (con == null) {
            JOptionPane.showMessageDialog(view, "Failed to connect to the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement("insert into room(roomnumber,roomtype,bed,price,status)values(?,?,?,?,?)");
            pst.setString(1, roomNumber);
            pst.setString(2, roomType);
            pst.setString(3, bed);
            pst.setDouble(4, price);
            pst.setString(5, "Not Booked");
            pst.executeUpdate();
            
            ModelManageRoom newRoom = new ModelManageRoom(roomNumber, roomType, bed, price, "Not Booked");
            tablaHash.insertar(newRoom);
            view.populateTable(obtenerTodosLosRooms());
            JOptionPane.showMessageDialog(view, "Room Added Successfully");

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeDbResources(con, pst, null);
        }
    }

    public void updateRoom(String roomNumber, String roomType, String bed, double price) {
        Connection con = DbConnection.conectar();
        if (con == null) {
            JOptionPane.showMessageDialog(view, "Failed to connect to the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement("update room set roomtype=?, bed=?, price=? where roomnumber=?");
            pst.setString(1, roomType);
            pst.setString(2, bed);
            pst.setDouble(3, price);
            pst.setString(4, roomNumber);
            pst.executeUpdate();

            ModelManageRoom updatedRoom = new ModelManageRoom(roomNumber, roomType, bed, price, "Not Booked");
            tablaHash.insertar(updatedRoom);
            view.populateTable(obtenerTodosLosRooms());
            JOptionPane.showMessageDialog(view, "Room Updated Successfully");

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeDbResources(con, pst, null);
        }
    }

    public void deleteRoom(String roomNumber) {
        ModelManageRoom room = tablaHash.buscar(roomNumber);
        if (room != null && room.getStatus().equalsIgnoreCase("booked")) {
            JOptionPane.showMessageDialog(view, "Sorry, Room is Booked So unable to delete it");
            return;
        }
        
        Connection con = DbConnection.conectar();
        if (con == null) {
            JOptionPane.showMessageDialog(view, "Failed to connect to the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement("delete from room where roomnumber=?");
            pst.setString(1, roomNumber);
            pst.executeUpdate();
            
            tablaHash.eliminar(roomNumber);
            view.populateTable(obtenerTodosLosRooms());
            JOptionPane.showMessageDialog(view, "Room Deleted Successfully");

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeDbResources(con, pst, null);
        }
    }

    private void closeDbResources(Connection con, PreparedStatement pst, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
