package Users.Actions.Graphical.Customer;

import Booking.BookingEntry;
import Lodges.Amenities;
import Lodges.Lodge;
import Lodges.LodgeType;
import Misc.HintedJTextField;
import Misc.Storage;
import Users.Actions.Graphical.GUIAction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import java.util.HashMap;
import java.util.HashSet;

public class LookupLodges extends GUIAction {
    @Override
    protected String getName() {
        return "Lodges";
    }

    @Override
    protected void invoke() {

        actionArea.setLayout(new BorderLayout());



        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BorderLayout());
//        actionArea.add(sidePanel, BorderLayout.WEST);
        JPanel optionsArea = new JPanel();
        optionsArea.setLayout(new BoxLayout(optionsArea, BoxLayout.Y_AXIS));
        sidePanel.add(optionsArea, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        display(Storage.getLodges(), mainPanel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidePanel, scrollPane);
//        sidePanel.setMinimumSize(new Dimension(10, 10));
        actionArea.add(splitPane);

        HashMap<JCheckBox, Amenities> checkBoxes = new HashMap<>();
        for (Amenities amenity : Amenities.values()){
            JCheckBox checkBox = new JCheckBox(amenity.label);
            checkBox.setFocusable(false);
            optionsArea.add(checkBox);
            checkBoxes.put(checkBox, amenity);
        }

        HintedJTextField place = new HintedJTextField("Where?");
        place.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) place.getPreferredSize().getHeight()));
        optionsArea.add(place);

        JButton search = new JButton("Search");
        sidePanel.add(search, BorderLayout.SOUTH);

        search.addActionListener(e -> {
            HashSet<Amenities> selectedAmenities = new HashSet<>();
            for (JCheckBox checkBox : checkBoxes.keySet()){
                if (checkBox.isSelected()){
                    selectedAmenities.add(checkBoxes.get(checkBox));
                }
            }

            HashSet<Lodge> lodges = new HashSet<>();
            for (Lodge lodge : Storage.getLodges()){
                if (lodge.getAmenities().containsAll(selectedAmenities) && (place.getText().isBlank() ||
                        place.getText().equalsIgnoreCase(lodge.getDetails().getLocation()))){
                    lodges.add(lodge);
                }
            }
            display(lodges, mainPanel);
        });


    }

    private void display(HashSet<Lodge> lodges, JPanel panel){
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        if (lodges.size() == 0){
            panel.add(error("No lodges found!"));
        }
        for (Lodge lodge : lodges){
            JPanel entry = new JPanel();
            entry.add(lodge.toJPanel());

            JButton book = new JButton("Book");
            book.setPreferredSize(new Dimension((int)book.getPreferredSize().getWidth(), (int)entry.getPreferredSize().getHeight()-10));

            if (lodge.getType() == LodgeType.HOTEL){
                book.setEnabled(false);
            }

            entry.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int)entry.getPreferredSize().getHeight()));
            entry.add(book);

            book.addActionListener(e -> BookingEntry.bookingDialog(lodge, parentUser));

            panel.add(entry);
        }
    }

}
