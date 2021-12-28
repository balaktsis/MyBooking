package Users.Actions.Graphical.Landlord;

import Booking.BookingEntry;
import Misc.HintedJTextField;
import Misc.Storage;
import Users.Actions.Graphical.GUIAction;

import javax.swing.*;
import java.awt.*;


public class ShowBookings extends GUIAction {
    @Override
    protected String getName() {
        return "Show Bookings";
    }

    @Override
    protected void invoke() {

        actionArea.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        actionArea.add(topPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        actionArea.add(mainPanel, BorderLayout.CENTER);

        topPanel.setLayout(new FlowLayout());


        HintedJTextField reqLodge = new HintedJTextField(" ID ");

        topPanel.add(new JLabel("On lodge: #"));
        topPanel.add(reqLodge);

        JButton search = new JButton("Search");
        JButton showAll = new JButton("Show All");
        search.setFocusable(false);
        showAll.setFocusable(false);
        topPanel.add(search);
        topPanel.add(showAll);

        search.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.revalidate();
            mainPanel.repaint();
            boolean flag = false;
            for (BookingEntry bookingEntry : Storage.getBookings())
                if(bookingEntry.isValid() && bookingEntry.getLodge().getLandlord().equals(parentUser) && bookingEntry.getLodge().getLodgeId().equals(reqLodge.getText())) {
                    mainPanel.add(bookingEntry.toJPanel());
                    flag = true;
                }
            if(!flag) {
                JLabel errorLabel = new JLabel("No bookings have been made on the requested lodge!");
                errorLabel.setForeground(Color.red);
                mainPanel.add(errorLabel);
            }

        });

        showAll.addActionListener(e -> {

            mainPanel.removeAll();
            mainPanel.revalidate();
            mainPanel.repaint();

            if (Storage.getBookings().size() == 0){
                JLabel errorLabel = new JLabel("No bookings have been made on your lodges!");
                errorLabel.setForeground(Color.red);
                mainPanel.add(errorLabel);
                return;
            }

            boolean flag = false;
            for (BookingEntry bookingEntry : Storage.getBookings())
                if(bookingEntry.isValid() && bookingEntry.getLodge().getLandlord().equals(parentUser)) {
                    mainPanel.add(bookingEntry.toJPanel());
                    flag = true;
                }
            if(!flag) {
                JLabel errorLabel = new JLabel("No bookings have been made!");
                errorLabel.setForeground(Color.red);
                mainPanel.add(errorLabel);
            }
        });

    }
}
