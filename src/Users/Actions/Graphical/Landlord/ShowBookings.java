package Users.Actions.Graphical.Landlord;

import Booking.BookingEntry;
import Lodges.Lodge;
import Misc.HintedJTextField;
import Misc.Storage;
import Users.Actions.Graphical.GUIAction;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.Objects;


/**
 * This class represents α panel that contains a table of booking entries that
 * apply to the current landlord's property. Double-clicking on some entry's
 * validation field, the user can see change its validation state.
 * @author Christos Balaktsis
 */

public class ShowBookings extends GUIAction implements Serializable {

    @Override
    protected String getName() {
        return "Show Bookings";
    }

    @Override
    protected void invoke() {
        actionArea.setLayout(new BoxLayout(actionArea, BoxLayout.Y_AXIS));
        String[] columnNames = {"ID",
                "Lodge",
                "Tenant",
                "Period",
                "Validation",
                "Total Cost"};

        String[][] data = new String[Storage.getBookings().size()][];
        int k = 0;
        for (BookingEntry bookingEntry : Storage.getBookings()) {
            if (bookingEntry.getLodge().getLandlord().equals(parentUser)) {
                data[k] = new String[]{bookingEntry.getBookingId(), bookingEntry.getLodge().getDetails().getTitle(),
                        bookingEntry.getTenant().getFullName(), bookingEntry.getArrivalDate() + " to " + bookingEntry.getDepartureDate(),
                        bookingEntry.isValid() ? "Valid" : "Invalid", "€ " + bookingEntry.getTotalCost()};
                if (!bookingEntry.isValid())
                    data[k][3] = data[k][5] = "-";
                k++;
            }
        }

        bookingList = new JTable(setData(null),setColumns());
        ShowBookings = new JScrollPane();
        Panel = new JPanel();
        noteLabel = new JLabel();
        bookingTable = new JScrollPane();

        ShowBookings.setVisible(true);

        //======== Panel ========
        Panel.setLayout(null);

        //---- noteLabel ----
        noteLabel.setText("Here is a list of all the bookings applied on your lodges.");
        Panel.add(noteLabel);
        noteLabel.setBounds(20, 5, 300, 40);


        //---- lodgeFiled ----
        lodgeField = new HintedJTextField(" # ");
        lodgeLabel = new JLabel("Select a specific lodge:");
        Panel.add(lodgeField);
        lodgeLabel.setBounds(20,25,150,40);
        Panel.add(lodgeLabel);
        lodgeField.setBounds(135,35,20,20);
        lodgeField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(!lodgeField.getText().isBlank()) {
                    try {
                        Integer.parseInt(lodgeField.getText());
                    } catch (NumberFormatException ex) {
                        bookingList = new JTable();
                        bookingTable.repaint();
                        JOptionPane.showMessageDialog(ShowBookings, "That's not a valid lodgeId.", "Error",JOptionPane.ERROR_MESSAGE);
                    }
                    boolean flag = false;
                    for (Lodge lodge : Storage.getLodges()) {
                        if (lodge.getLodgeId().equals(lodgeField.getText()) && lodge.getLandlord().equals(parentUser)) {
                            bookingList = new JTable(setData(lodgeField.getText()), columnNames);
                            bookingTable.repaint();
                            flag = true;
                            break;
                        }
                    }
                    if(flag) JOptionPane.showMessageDialog(ShowBookings,"Showing booking entries applied on lodge #" + lodgeField.getText() + ".", "Filtered search", JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(ShowBookings,"There is not any booking entry on the requested lodge.", "Search failed", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    bookingList = new JTable(setData(null), setColumns());
                    bookingTable.repaint();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(!lodgeField.getText().isBlank()) {
                    try {
                        Integer.parseInt(lodgeField.getText());
                    } catch (NumberFormatException ex) {
                        bookingList = new JTable();
                        bookingTable.repaint();
                        JOptionPane.showMessageDialog(ShowBookings, "That's not a valid lodgeId.", "Error",JOptionPane.ERROR_MESSAGE);
                    }
                    boolean flag = false;
                    for (Lodge lodge : Storage.getLodges()) {
                        if (lodge.getLodgeId().equals(lodgeField.getText()) && lodge.getLandlord().equals(parentUser)) {
                            bookingList = new JTable(setData(lodgeField.getText()), columnNames);
                            bookingTable.repaint();
                            flag = true;
                            break;
                        }
                    }
                    if(flag) JOptionPane.showMessageDialog(ShowBookings,"Showing booking entries applied on lodge #" + lodgeField.getText() + ".", "Filtered search", JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(ShowBookings,"There is not any booking entry on the requested lodge.", "Search failed", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    bookingList = new JTable(setData(null), setColumns());
                    bookingTable.repaint();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if(!lodgeField.getText().isBlank()) {
                    try {
                        Integer.parseInt(lodgeField.getText());
                    } catch (NumberFormatException ex) {
                        bookingList = new JTable();
                        bookingTable.repaint();
                        JOptionPane.showMessageDialog(ShowBookings, "That's not a valid lodgeId.", "Error",JOptionPane.ERROR_MESSAGE);
                    }
                    boolean flag = false;
                    for (Lodge lodge : Storage.getLodges()) {
                        if (lodge.getLodgeId().equals(lodgeField.getText()) && lodge.getLandlord().equals(parentUser)) {
                            bookingList = new JTable(setData(lodgeField.getText()), columnNames);
                            bookingTable.repaint();
                            flag = true;
                            break;
                        }
                    }
                    if(flag) JOptionPane.showMessageDialog(ShowBookings,"Showing booking entries applied on lodge #" + lodgeField.getText() + ".", "Filtered search", JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(ShowBookings,"There is not any booking entry on the requested lodge.", "Search failed", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    bookingList = new JTable(setData(null), setColumns());
                    bookingTable.repaint();
                }
            }
        });



        //---- lodgeList ----
        bookingList.setAutoCreateRowSorter(true);
        bookingList.setCellSelectionEnabled(true);
        bookingList.setShowVerticalLines(false);
        bookingList.setDefaultEditor(Object.class, null);
        bookingTable.setViewportView(bookingList);

        bookingList.getColumnModel().getColumn(0).setMaxWidth(40);
        bookingList.getColumnModel().getColumn(1).setMaxWidth(200);
        bookingList.getColumnModel().getColumn(1).setPreferredWidth(100);
        bookingList.getColumnModel().getColumn(2).setMaxWidth(200);
        bookingList.getColumnModel().getColumn(3).setMaxWidth(250);
        bookingList.getColumnModel().getColumn(4).setMaxWidth(80);
        bookingList.getColumnModel().getColumn(5).setMaxWidth(80);

        Panel.add(bookingTable);
        bookingTable.setBounds(20, 60, 800, 600);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < Panel.getComponentCount(); i++) {
                Rectangle bounds = Panel.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = Panel.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            Panel.setMinimumSize(preferredSize);
            Panel.setPreferredSize(preferredSize);
        }

        ShowBookings.setViewportView(Panel);

        bookingList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    for (BookingEntry bookingEntry : Storage.getBookings())
                        if (bookingEntry.getBookingId().equals(data[row][0])) {
                            if (JOptionPane.showConfirmDialog(ShowBookings, "Do you want to edit booking entry #" + bookingEntry.getBookingId() + "?",
                                    "Edit booking details",
                                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                if(column != 4) JOptionPane.showMessageDialog(ShowBookings, columnNames[column] + " is an immutable field!", "Unauthorized action",JOptionPane.ERROR_MESSAGE);
                                else {
                                    if(Objects.equals(data[row][column], "Valid")) {
                                        JOptionPane.showMessageDialog(ShowBookings,"Setting entry to invalid.");
                                        data[row][column] = "Invalid";
                                        bookingEntry.cancelBooking();
                                    } else {
                                        JOptionPane.showMessageDialog(ShowBookings,"Setting entry to valid.");
                                        data[row][column] = "Valid";
                                        BookingEntry tmpBooking = new BookingEntry(bookingEntry.getTenant(),bookingEntry.getLodge());
                                        tmpBooking.bookLodge(bookingEntry.getArrivalDate(),bookingEntry.getDepartureDate());
                                        Storage.getBookings().add(tmpBooking);
                                    }
                                }
                            }
                            break;
                        }
                }
            }
        });
        actionArea.add(ShowBookings);
        actionArea.add(Box.createRigidArea(new Dimension(0, 5)));

    }

    private JScrollPane ShowBookings;
    private JPanel Panel;
    private JLabel noteLabel;
    private JScrollPane bookingTable;
    private JTable bookingList;
    private HintedJTextField lodgeField;
    private JLabel lodgeLabel;

    private String[] setColumns() {
        return new String[]{"ID",
                "Lodge",
                "Tenant",
                "Period",
                "Validation",
                "Total Cost"};
    }

    private String[][] setData(String lodgeId) {
        String[][] data = new String[Storage.getBookings().size()][];
        boolean flag = lodgeId == null;
        int k = 0;
        for (BookingEntry bookingEntry : Storage.getBookings()) {
            if (bookingEntry.getLodge().getLandlord().equals(parentUser) && (bookingEntry.getLodge().getLodgeId().equals(lodgeId) || flag)) {
                data[k] = new String[]{bookingEntry.getBookingId(), bookingEntry.getLodge().getDetails().getTitle(),
                        bookingEntry.getTenant().getFullName(), bookingEntry.getArrivalDate() + " to " + bookingEntry.getDepartureDate(),
                        bookingEntry.isValid() ? "Valid" : "Invalid", "€ " + bookingEntry.getTotalCost()};
                if (!bookingEntry.isValid())
                    data[k][3] = data[k][5] = "-";
                k++;
            }
        }
        return data;
    }
}
