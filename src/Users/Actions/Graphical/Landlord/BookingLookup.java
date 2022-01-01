package Users.Actions.Graphical.Landlord;

import Booking.BookingEntry;
import Misc.HintedJTextField;
import Misc.Storage;
import Users.Actions.Graphical.GUIAction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a searching interface for a Landlord to get a
 * filtered list of the booking entries that apply to their property.
 * Double-clicking on an entry, it can get set to cancelled/invalid.
 * @author Christos Balaktsis
 */

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

        JButton refresh = new JButton("Refresh");
        topPanelRight.add(refresh);

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

            addBookingsToMainPanel(mainPanel, foundBookings);
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

            addBookingsToMainPanel(mainPanel, foundBookings);

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

            addBookingsToMainPanel(mainPanel, foundBookings);

        });

        refresh.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.revalidate();
            mainPanel.repaint();
        });

    }

    private void addBookingsToMainPanel(JPanel mainPanel, List<BookingEntry> foundBookings) {
        for (BookingEntry bookingEntry : foundBookings){
            JPanel tmp = bookingEntry.toJPanel();
            tmp.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (JOptionPane.showConfirmDialog(mainPanel, "Do you want to change the validation of booking entry #" + bookingEntry.getBookingId() + "?",
                            "Validation of booking entry",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        if(bookingEntry.isValid()) {
                            if(bookingEntry.cancelBooking())
                                JOptionPane.showMessageDialog(mainPanel,"Setting entry to invalid.");
                            else
                                JOptionPane.showMessageDialog(mainPanel, "Cancellation failed!");
                        } else {
                            JOptionPane.showMessageDialog(mainPanel, "This booking entry is already cancelled!");
                        }
                    }
                    actionArea.removeAll();
                    actionArea.revalidate();
                    actionArea.repaint();
                    invoke();
                }
            });
            mainPanel.add(tmp);
        }
    }
}