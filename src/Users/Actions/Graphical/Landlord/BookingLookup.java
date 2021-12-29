package Users.Actions.Graphical.Landlord;

import Booking.BookingEntry;
import Misc.HintedJTextField;
import Misc.Storage;
import Users.Actions.Graphical.GUIAction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BookingLookup extends GUIAction {
    @Override
    protected String getName() {
        return "Booking Lookup";
    }

    @Override
    protected void invoke() {
        actionArea.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        actionArea.add(topPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        actionArea.add(mainPanel, BorderLayout.CENTER);

        JPanel topPanelRight = new JPanel();
        topPanelRight.setLayout(new FlowLayout());
        JPanel topPanelLeft = new JPanel();
        topPanelLeft.setLayout(new FlowLayout());


        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanelLeft, topPanelRight);
        splitPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        splitPane.setDividerSize(0);
        topPanel.add(splitPane);

        HintedJTextField from_day = new HintedJTextField("DD");
        HintedJTextField from_month = new HintedJTextField("MM");
        HintedJTextField from_year = new HintedJTextField("YYYY");
        HintedJTextField from_bookingId = new HintedJTextField("  #  ");
        HintedJTextField from_lodgeId = new HintedJTextField("  #  ");

        topPanelLeft.add(new JLabel("On Day:"));
        topPanelLeft.add(from_day);
        topPanelLeft.add(new JLabel("/"));
        topPanelLeft.add(from_month);
        topPanelLeft.add(new JLabel("/"));
        topPanelLeft.add(from_year);
        topPanelLeft.add(new JLabel("Booking Id:"));
        topPanelLeft.add(from_bookingId);
        topPanelLeft.add(new JLabel("Lodge Id:"));
        topPanelLeft.add(from_lodgeId);

        JButton searchFromDate = new JButton("Date Search");
        JButton searchFromBooking = new JButton("Booking Id Search");
        JButton searchFromLodge = new JButton("Lodge Id Search");

        searchFromDate.setFocusable(false);
        searchFromBooking.setFocusable(false);
        searchFromLodge.setFocusable(false);
        topPanelRight.add(searchFromDate);
        topPanelRight.add(searchFromBooking);
        topPanelRight.add(searchFromLodge);

        searchFromDate.addActionListener(e -> {
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

        searchFromBooking.addActionListener(e -> {

            mainPanel.removeAll();
            mainPanel.revalidate();
            mainPanel.repaint();

            List<BookingEntry> foundBookings = new ArrayList<>();

            for(BookingEntry bookingEntry : Storage.getBookings())
                if(bookingEntry.getBookingId().equals(from_bookingId.getText())) {
                    if(bookingEntry.getLodge().getLandlord().equals(parentUser)) foundBookings.add(bookingEntry);
                    else {
                        JLabel errorLabel = new JLabel("The requested booking does not apply to any of your lodges.");
                        errorLabel.setForeground(Color.red);
                        mainPanel.add(errorLabel);
                        return;
                    }
                    break;
                }



            if (foundBookings.size() == 0){
                JLabel errorLabel = new JLabel("There is not any booking with id #" + from_bookingId.getText() +"!");
                errorLabel.setForeground(Color.red);
                mainPanel.add(errorLabel);
                return;
            }

            for (BookingEntry bookingEntry : foundBookings){
                mainPanel.add(bookingEntry.toJPanel());
            }

        });

        searchFromLodge.addActionListener(e -> {

            mainPanel.removeAll();
            mainPanel.revalidate();
            mainPanel.repaint();

            List<BookingEntry> foundBookings = new ArrayList<>();

            for(BookingEntry bookingEntry : Storage.getBookings())
                if(bookingEntry.getLodge().getLodgeId().equals(from_lodgeId.getText())) {
                    if(bookingEntry.getLodge().getLandlord().equals(parentUser))
                        foundBookings.add(bookingEntry);
                    else {
                        JLabel errorLabel = new JLabel("The requested lodge does not belong to you.");
                        errorLabel.setForeground(Color.red);
                        mainPanel.add(errorLabel);
                        return;
                    }
                }



            if (foundBookings.size() == 0){
                JLabel errorLabel = new JLabel("There are not any bookings on lodge #" + from_lodgeId.getText() +"!");
                errorLabel.setForeground(Color.red);
                mainPanel.add(errorLabel);
                return;
            }

            for (BookingEntry bookingEntry : foundBookings){
                mainPanel.add(bookingEntry.toJPanel());
            }

        });
    }
}
