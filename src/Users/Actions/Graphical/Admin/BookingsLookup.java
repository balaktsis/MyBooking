package Users.Actions.Graphical.Admin;

import Booking.BookingEntry;
import Misc.HintedJTextField;
import Misc.Storage;
import Users.Actions.Graphical.GUIAction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import java.util.List;

public class BookingsLookup extends GUIAction {
    @Override
    protected String getName() {
        return "Look up Bookings";
    }

    @Override
    protected void invoke() {

        actionArea.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        actionArea.add(topPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        actionArea.add(scrollPane, BorderLayout.CENTER);

        topPanel.setLayout(new FlowLayout());

        HintedJTextField from_day = new HintedJTextField("DD");
        HintedJTextField from_month = new HintedJTextField("MM");
        HintedJTextField from_year = new HintedJTextField("YYYY");

        topPanel.add(new JLabel("On Day:"));
        topPanel.add(from_day);
        topPanel.add(new JLabel("/"));
        topPanel.add(from_month);
        topPanel.add(new JLabel("/"));
        topPanel.add(from_year);

        JButton search = new JButton("Search");
        JButton searchAll = new JButton("Show All");
        search.setFocusable(false);
        searchAll.setFocusable(false);
        topPanel.add(search);
        topPanel.add(searchAll);

        search.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.revalidate();
            mainPanel.repaint();

            String lookupDate = from_year.getText() + "-" + from_month.getText() + "-" + from_day.getText();
            List<BookingEntry> foundBookings;

            foundBookings = BookingEntry.getBookingsOnDate(lookupDate);

            if (foundBookings.size() == 0){
                JLabel errorLabel = new JLabel("No bookings were found for that date!");
                errorLabel.setForeground(Color.red);
                mainPanel.add(errorLabel);
                return;
            }

            for (BookingEntry bookingEntry : foundBookings){
                mainPanel.add(bookingEntry.toJPanel());
            }

        });

        searchAll.addActionListener(e -> {

            mainPanel.removeAll();
            mainPanel.revalidate();
            mainPanel.repaint();

            if (Storage.getBookings().size() == 0){
                JLabel errorLabel = new JLabel("No bookings have been made!");
                errorLabel.setForeground(Color.red);
                mainPanel.add(errorLabel);
                return;
            }

            for (BookingEntry bookingEntry : Storage.getBookings()){
                mainPanel.add(bookingEntry.toJPanel());
            }

        });

    }
}
