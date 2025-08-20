package Controller;

import View.Home;
import View.ManageRoom;
import Controller.ControllerManageRoom;
import View.CustomerCheckIn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerHome implements ActionListener {

    private Home homeView;

    public ControllerHome(Home homeView) {
        this.homeView = homeView;
        this.homeView.getjButton1().addActionListener(this);
        this.homeView.getjButton2().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeView.getjButton1()) {
            ManageRoom view = new ManageRoom();
            ControllerManageRoom controller = new ControllerManageRoom(view);
            view.setController(controller);
            view.setVisible(true);
        } else if (e.getSource() == homeView.getjButton2()) {
            CustomerCheckIn view = new CustomerCheckIn();
            ControllerCustomerCheckIn controller = new ControllerCustomerCheckIn(view);
            view.setController(controller);
            view.setVisible(true);
        }
    }
}
