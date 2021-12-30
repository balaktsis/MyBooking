package Users.Actions.Graphical.Customer;

import Booking.BookingEntry;
import Misc.Storage;
import Users.Actions.Graphical.GUIAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyBookings extends GUIAction {
    @Override
    protected String getName() {
        return "Bookings";
    }

    @Override
    protected void invoke() {
        actionArea.removeAll();
        actionArea.revalidate();
        actionArea.repaint();
        actionArea.setLayout(new BoxLayout(actionArea, BoxLayout.Y_AXIS));

        ArrayList<BookingEntry> myBookings = new ArrayList<>();
        for (BookingEntry bookingEntry : Storage.getBookings()) {
            if (bookingEntry.getTenant().equals(parentUser) && bookingEntry.isValid()) {
                myBookings.add(bookingEntry);
            }
        }

        if (myBookings.isEmpty()){
            actionArea.add(error("No valid bookings were found!"));
            return;
        }

        for (BookingEntry bookingEntry : myBookings){
                JPanel entry = new JPanel();
                entry.add(bookingEntry.toJPanel());
                JButton cancel = new JButton("Cancel");
                cancel.setPreferredSize(new Dimension((int)cancel.getPreferredSize().getWidth(), (int)entry.getPreferredSize().getHeight()-10));

                entry.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int)entry.getPreferredSize().getHeight()));

                cancel.addActionListener(e -> {
                    bookingEntry.cancelBooking();
                    invoke();
                });

                entry.add(cancel);
                actionArea.add(entry);

        }
    }

}
