package Lodges;

import java.awt.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import Users.Landlord;

import javax.swing.*;
import javax.swing.border.Border;
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
    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
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
        if(this.availability.markDates(dates)) {
  //TODO          this.rating = ((int) (1.0 * (this.numOfBookings++) / Administrator.getTotalBookings() * 100) % 6); <--Use Storage
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

    public JPanel toJPanel(){
        JPanel lodgePanel = new JPanel();
        lodgePanel.setBackground(Color.white);
        lodgePanel.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

        ImageIcon scaledImage = new ImageIcon(this.details.getImage().getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING));
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




        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, imagePanel, detailPanel);
        splitPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        splitPane.setDividerSize(0);
        lodgePanel.add(splitPane);

//        lodgePanel.add(new JLabel(getDetails().getTitle()));
//        lodgePanel.add(new JLabel(", Landlord:"));
//        lodgePanel.add(new JLabel(getLandlord().getUsername()));
        return lodgePanel;
    }

}
