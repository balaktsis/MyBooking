package Lodges;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import Misc.HLabel;
import Misc.Storage;
import Users.Landlord;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import static Misc.UniqueIDGenerator.getUniqueId;

/**
 * This is a Lodge class that represents a lodge for booking. It's defined by details such as
 * its owner, location, size and amenities that it offers. Depending on how many people have
 * chosen this lodge, it scores a rating on a scale 0-5, following the rating function:
 * rate = (current Lodge bookings)/(total Bookings) * 100 mod 6.
 * @author Christos Balaktsis
 */

public class Lodge implements Serializable {
    private final LodgeDetails details;
    private final String lodgeId;
    private final LodgeType type;
    private final LodgeAvailability availability;
    private Landlord landlord;
    private HashSet<Amenities> amenities;

    /**
     * Primary constructor of a Lodge object, initializing the class fields, according to the parameters.
     * @param landlord The user owner of the lodge.
     */
    public Lodge(Landlord landlord, String location, LodgeType type) {
        this.details = new LodgeDetails();
        this.landlord = landlord;
        this.details.location = location;
        this.type = type;
        this.details.beds = 0;
        this.details.size = 0;
        this.details.price = 0;
        this.amenities = new HashSet<>();
        this.availability = new LodgeAvailability();
        this.details.rating = 0;
        this.details.numOfBookings = 0;
        this.details.description = "No description has been set";
        this.lodgeId = getUniqueId();
        this.details.title = "Untitled Lodge";
    }

    /**
     * @return Access to the different detail-fields of the current lodge.
     */
    public LodgeDetails getDetails() {
        return this.details;
    }

    /**
     * Update the user owner of the current lodge.
     * @param landlord New user landlord of the lodge.
     */
    public boolean setLandlord(Landlord landlord) {
        if(landlord == null) return false;
        this.landlord = landlord;
        return true;
    }

    /**
     * Update the availability of the current lodge.
     * @param dates Dates the lodge will be available.
     */
    public void setAvailableDates(HashSet<LocalDate> dates) {
        this.availability.freeDates(dates);
    }

    /**
     * Update the amenities list of the current lodge.
     * @param amenities New amenities list of the lodge.
     */
    public void setAmenities(HashSet<Amenities> amenities) {
        this.amenities = amenities;
    }

    /**
     * Add an amenity offered the current lodge.
     * @param amenity New amenity added in the list of amenities of the lodge.
     */
    public void addAmenity(Amenities amenity) {
        this.amenities.add(amenity);
    }

    /**
     * Delete an amenity offered by the current lodge.
     * @param amenity Amenity asked to be removed of the amenities list of the lodge.
     */
    public void removeAmenity(Amenities amenity) {
        this.amenities.remove(amenity);
    }

    /**
     * @return the landlord user of the current lodge.
     */
    public Landlord getLandlord() {
        return this.landlord;
    }

    /**
     * @return the lodge type of the current lodge.
     */
    public LodgeType getType() {
        return this.type;
    }

    /**
     * @return the availability calendar (object) of the current lodge.
     */
    public LodgeAvailability getAvailability() {
        return this.availability;
    }

    /**
     * @return the list of provided amenities of the current lodge.
     */
    public HashSet<Amenities> getAmenities() {
        return this.amenities;
    }

    /**
     * @return the ID of the current lodge.
     */
    public String getLodgeId() {
        return this.lodgeId;
    }

    /**
     * Signals the booking status of the current lodge after being asked to get booked.
     * In case of new booking, it updates the availability status for the requested period
     * and rating of the lodge.
     * @param dates A list of following days, the period of booking the current lodge.
     * @return if the current lodge is available for booking for the dates in "dates".
     */
    public boolean bookLodge(HashSet<LocalDate> dates) {
        dates.remove(Collections.max(dates));
        if(this.availability.markDates(dates)) {
            this.details.rating = (int) ((1.0 * details.numOfBookings / Storage.getLodges().size() * 100) % 6);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Lodge lodge))
            return false;
        return this.landlord == lodge.landlord && this.details.location.equals(lodge.details.location) && this.type.equals(lodge.type);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + (this.landlord != null ? this.landlord.hashCode() : 0);
        hash = 31 * hash + (this.details.location != null ? this.details.location.hashCode() : 0);
        hash = 31 * hash + (this.type != null ? this.type.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Lodge #");
        str.append(this.lodgeId);
        str.append("\t\"");
        str.append(this.details.getTitle());
        str.append("\"");
        str.append("\nLandlord: ");
        str.append(this.getLandlord().getFullName());
        str.append("\nLocation: ");
        str.append(this.details.getLocation());
        str.append("\nDescription: ");
        str.append(this.details.getDescription());
        str.append("\nType of lodge: ");
        str.append(this.getType().toString());
        if(!this.getType().equals(LodgeType.HOTEL)) {
            str.append("\nSize: ");
            str.append(this.details.getSize());
            str.append("\nCost per night: € ");
            str.append(this.details.getPrice());
            str.append("\nAmenities: ");
            str.append(this.getAmenities().toString());
            str.append("\nBooked dates: ");
            ArrayList<LocalDate> dateArrayList = new ArrayList<>(this.getAvailability().getBookCalendar());
            Collections.sort(dateArrayList);
            str.append(dateArrayList);
        }
        return str.toString();
    }

    /**
     * Create a JPanel containing the details of the user, like in toString()
     * @return JPanel
     */
    public JPanel toJPanel(){
        JPanel lodgePanel = new JPanel();
        lodgePanel.setBackground(Color.white);
        lodgePanel.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        lodgePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        ImageIcon scaledImage = new ImageIcon(this.getDetails().getImage().getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING));
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.white);
        imagePanel.add(new JLabel(scaledImage));

        JPanel detailPanel = new JPanel();
        detailPanel.setLayout(new GridLayout(3, 2));
        detailPanel.setBackground(Color.white);
        detailPanel.add(new JLabel(getDetails().getTitle()),0,0);
        detailPanel.add(new JLabel("# " + getLodgeId(), SwingConstants.TRAILING));
        detailPanel.add(new JLabel(getDetails().getDescription()));
        detailPanel.add(new JLabel("Landlord: " + getLandlord().getUsername(), SwingConstants.TRAILING));
        detailPanel.add(new JLabel("Price/night: " + getDetails().getPrice() + "€"));
        String cutLocation;
        if (getDetails().getLocation().length() > 20){
            cutLocation = getDetails().getLocation().substring(0, 20) + "...";
        } else {
            cutLocation = getDetails().getLocation();
        }
        detailPanel.add(new JLabel("Location: " + cutLocation, SwingConstants.TRAILING));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, imagePanel, detailPanel);
        splitPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        splitPane.setDividerSize(0);
        lodgePanel.add(splitPane);

        splitPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showJFrame();
            }
        });

        lodgePanel.setMaximumSize(lodgePanel.getPreferredSize());

        return lodgePanel;
    }

    public void showJFrame(){
        JFrame frame = new JFrame(getDetails().getTitle());

        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        frame.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        ImageIcon scaledImage = new ImageIcon(this.getDetails().getImage().getImage().getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING));
        JPanel imagePanel = new JPanel();
        imagePanel.add(new JLabel(scaledImage));

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(4, 1));

        JPanel head = new JPanel();

        JLabel title = new JLabel(this.getDetails().getTitle());
        title.setFont(title.getFont().deriveFont(title.getFont().getStyle() | Font.BOLD, title.getFont().getSize() + 4f));
        detailsPanel.add(title);

        JLabel id = new JLabel("#" + this.getLodgeId(), JLabel.RIGHT);

        head.add(title);
        head.add(id);

        detailsPanel.add(head);

        detailsPanel.add(new HLabel("<b>Landlord:</b> " + this.getLandlord().getFullName(), JLabel.CENTER));

        detailsPanel.add(new JLabel(this.getDetails().getLocation(), JLabel.CENTER));

        JPanel footer = new JPanel();
        JLabel size = new HLabel("<b>Size:</b> " + this.getDetails().getSize() + "m²");
        JLabel beds = new HLabel("<b>Beds:</b> " + this.getDetails().getBeds());
        JLabel price = new HLabel("<b>Price/night:</b> " + this.getDetails().getPrice() + "€");
        footer.add(size);
        footer.add(beds);
        footer.add(price);

        detailsPanel.add(footer);



        JSplitPane topPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, imagePanel, detailsPanel);
        topPanel.setDividerSize(0);
        topPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int)topPanel.getPreferredSize().getHeight()));

        mainPanel.add(topPanel);

        JLabel description = new HLabel("<B>Description:</B> " + this.getDetails().getDescription());
        mainPanel.add(description);

        ArrayList<String> amenities = new ArrayList<>();

        for (Amenities amenity : this.getAmenities()){
            amenities.add(amenity.label);
        }

        JLabel amenitiesList = new HLabel("<B>Amenities:</B> " + String.join(", ", amenities));
        mainPanel.add(amenitiesList);



        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
