package Users.Actions.Graphical.Customer;

import Booking.BookingEntry;
import Misc.Storage;
import Users.Actions.Graphical.GUIAction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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
        actionArea.setLayout(new GridLayout(1, 1));

        JPanel viewport = new JPanel();
        viewport.setLayout(new BoxLayout(viewport, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(viewport);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        actionArea.add(scrollPane);

        ArrayList<BookingEntry> myBookings = new ArrayList<>();
        for (BookingEntry bookingEntry : Storage.getBookings()) {
            if (bookingEntry.getTenant().equals(parentUser) && bookingEntry.isValid()) {
                myBookings.add(bookingEntry);
            }
        }

        if (myBookings.isEmpty()){
            viewport.add(error("No valid bookings were found!"));
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
                viewport.add(entry);

        }
    }

}
