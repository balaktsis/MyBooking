package Users.Actions.Graphical.Customer;

import Booking.BookingEntry;
import Lodges.Amenities;
import Lodges.Lodge;
import Misc.HintedJTextField;
import Misc.Storage;
import Users.Actions.Graphical.GUIAction;
import Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

public class LookupLodges extends GUIAction {
    @Override
    protected String getName() {
        return "Lodges";
    }

    @Override
    protected void invoke() {

        actionArea.setLayout(new BorderLayout());



        JPanel sidepanel = new JPanel();
        sidepanel.setLayout(new BorderLayout());
//        actionArea.add(sidepanel, BorderLayout.WEST);
        JPanel optionsArea = new JPanel();
        optionsArea.setLayout(new BoxLayout(optionsArea, BoxLayout.Y_AXIS));
        sidepanel.add(optionsArea, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel();
//        actionArea.add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new FlowLayout());
        display(Storage.getLodges(), mainPanel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidepanel, mainPanel);
//        sidepanel.setMinimumSize(new Dimension(10, 10));
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
        sidepanel.add(search, BorderLayout.SOUTH);

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

            entry.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int)entry.getPreferredSize().getHeight()));
            entry.add(book);

            book.addActionListener(e -> BookingEntry.bookingDialog(lodge, parentUser));

            panel.add(entry);
        }
    }

}
