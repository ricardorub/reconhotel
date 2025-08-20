package Controller;

import Model.ModelCustomerCheckIn;
import ModelData.Cola;
import View.CustomerCheckIn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JOptionPane;

public class ControllerCustomerCheckIn implements ActionListener, ItemListener {
    
    private CustomerCheckIn view;
    private ModelCustomerCheckIn model;

    public ControllerCustomerCheckIn(CustomerCheckIn view) {
        this.view = view;
        this.model = new ModelCustomerCheckIn();
        
        // Add Listeners
        this.view.getComboRoomType().addItemListener(this);
        this.view.getComboBed().addItemListener(this);
        this.view.getComboRoomNumber().addItemListener(this);
        this.view.getBtnAlloteRoom().addActionListener(this);
        this.view.getBtnClear().addActionListener(this);

        // Initial load
        updateRoomNumbers();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnAlloteRoom()) {
            allotRoom();
        } else if (e.getSource() == view.getBtnClear()) {
            clearFields();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (e.getSource() == view.getComboRoomType() || e.getSource() == view.getComboBed()) {
                updateRoomNumbers();
            } else if (e.getSource() == view.getComboRoomNumber()) {
                updatePrice();
            }
        }
    }

    private void updateRoomNumbers() {
        String roomType = (String) view.getComboRoomType().getSelectedItem();
        String bed = (String) view.getComboBed().getSelectedItem();
        Cola rooms = model.getAvailableRooms(roomType, bed);
        
        view.getComboRoomNumber().removeAllItems();
        while (!rooms.isEmpty()) {
            view.getComboRoomNumber().addItem((String) rooms.dequeue());
        }
        // The updatePrice will be called automatically by the listener on comboroomnumber
    }

    private void updatePrice() {
        String roomNumber = (String) view.getComboRoomNumber().getSelectedItem();
        if (roomNumber != null) {
            String price = model.getRoomPrice(roomNumber);
            view.getTxtPrice().setText(price);
        } else {
            view.getTxtPrice().setText("");
        }
    }

    private void allotRoom() {
        // Basic validation
        if (view.getTxtName().getText().isEmpty() || view.getTxtMob().getText().isEmpty() ||
            view.getTxtEmail().getText().isEmpty() || view.getTxtNat().getText().isEmpty() ||
            view.getTxtAdhar().getText().isEmpty() || view.getTxtAddres().getText().isEmpty() ||
            view.getTxtPrice().getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(view.getTxtMob().getText().length()!=10){
            JOptionPane.showMessageDialog(view, "Mobile Number Should be 10 Digit.");
            return;
        }
        if(view.getTxtAdhar().getText().length()!=12){
            JOptionPane.showMessageDialog(view, "Adhar Number Should be 12 Digit.");
            return;
        }

        boolean success = model.allotRoom(
            view.getTxtName().getText(),
            view.getTxtMob().getText(),
            view.getTxtEmail().getText(),
            (String) view.getComboGender().getSelectedItem(),
            view.getTxtAddres().getText(),
            view.getTxtAdhar().getText(),
            view.getTxtNat().getText(),
            view.getTxtDate().getText(),
            (String) view.getComboRoomNumber().getSelectedItem(),
            (String) view.getComboBed().getSelectedItem(),
            (String) view.getComboRoomType().getSelectedItem(),
            view.getTxtPrice().getText()
        );

        if (success) {
            JOptionPane.showMessageDialog(view, "Room Allotted Successfully!");
            clearFields();
            updateRoomNumbers();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to allot room.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        view.getTxtName().setText("");
        view.getTxtMob().setText("");
        view.getTxtEmail().setText("");
        view.getTxtNat().setText("");
        view.getTxtAdhar().setText("");
        view.getTxtAddres().setText("");
        view.getComboGender().setSelectedIndex(0);
        // We don't clear the room preference comboboxes
        // as the user might want to book another room of the same type.
        // The room number and price will be updated automatically.
    }
}
