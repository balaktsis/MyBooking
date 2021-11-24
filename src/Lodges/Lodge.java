package Lodges;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import Users.Landlord;

import static Misc.UniqueIDGenerator.getUniqueId;

/**
 * This is a Lodge class that represents a lodge for booking. It's defined by details such as
 * its owner, location, size and amenities that it offers. Depending on how many people have
 * chosen this lodge, it scores a rating on a scale 0-5, following the rating function:
 * rate = (current Lodge bookings)/(total Bookings) * 100 mod 6.
 * @author Christos Balaktsis
 */

public class Lodge {
    private Landlord landlord;
    private final String location;
    private String description;
    private String title;
    private final String lodgeId;
    private final LodgeType type;
    private int rating;
    private int beds;
    private int size;
    private int numOfBookings;
    private double price;
    private final LodgeAvailability availability;
    private ArrayList<Amenities> amenities;

    /**
     * Primary constructor of a Lodge object, initializing the class fields, according to the parameters.
     * @param landlord The user owner of the lodge.
     */
    public Lodge(Landlord landlord, String location, LodgeType type) {
        this.landlord = landlord;
        this.location = location;
        this.type = type;
        this.beds = 0;
        this.size = 0;
        this.price = 0;
        this.amenities = null;
        this.availability = new LodgeAvailability();
        this.rating = 0;
        this.numOfBookings = 0;
        this.description = null;
        this.lodgeId = getUniqueId();
        this.title = "Untitled Lodge";
    }

    /**
     * Update the title of the current lodge.
     * @param title New title for the lodge.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Update the user owner of the current lodge.
     * @param landlord New user landlord of the lodge.
     */
    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }

    /**
     * Update the rating score of the current lodge.
     * @param rating New rating for the lodge.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Update the number of beds of the current lodge.
     * @param beds New size of the lodge.
     */
    public void setBeds(int beds) {
        this.beds = beds;
    }

    /**
     * Update the cost of accommodation per night of the current lodge.
     * @param price New price of the lodge.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Update the availability of the current lodge.
     * @param dates Dates the lodge will be available.
     */
    public void setAvailableDates(HashSet<LocalDate> dates) {
        this.availability.freeDates(dates);
    }

    /**
     * Update the description of the current lodge.
     * @param description New description of the lodge.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Update the size in m^2 of the current lodge.
     * @param size New size in m^2
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Update the amenities list of the current lodge.
     * @param amenities New amenities list of the lodge.
     */
    public void setAmenities(ArrayList<Amenities> amenities) {
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
     * Update the number of rents of the current lodge.
     * @param numOfBookings New number of total Bookings of the current lodge.
     */
    public void setNumOfBookings(int numOfBookings) {
        this.numOfBookings = numOfBookings;
    }

    /**
     * @return the number of planned bookings of the current lodge.
     */
    public int getNumOfBookings() {
        return this.numOfBookings;
    }

    /**
     * @return the title of the current lodge.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @return the landlord user of the current lodge.
     */
    public Landlord getLandlord() {
        return this.landlord;
    }

    /**
     * @return the location of the current lodge.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * @return the description of the current lodge.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return the lodge type of the current lodge.
     */
    public LodgeType getType() {
        return this.type;
    }

    /**
     * @return the rating status of the current lodge.
     */
    public int getRating() {
        return this.rating;
    }

    /**
     * @return the number of beds of the current lodge.
     */
    public int getBeds() {
        return this.beds;
    }

    /**
     * @return the accommodation cost per night of the current lodge.
     */
    public double getPrice() {
        return this.price;
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
    public ArrayList<Amenities> getAmenities() {
        return this.amenities;
    }

    /**
     * @return the ID of the current lodge.
     */
    public String getLodgeId() {
        return this.lodgeId;
    }

    /**
     * @return the size in m^2 of the current lodge.
     */
    public int getSize() {
        return this.size;
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
  //          this.rating = ((int) (1.0 * (this.numOfBookings++) / Administrator.getTotalBookings() * 100) % 6);
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
        return this.landlord == lodge.landlord && this.location.equals(lodge.location) && this.type.equals(lodge.type);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + (this.landlord != null ? this.landlord.hashCode() : 0);
        hash = 31 * hash + (this.location != null ? this.location.hashCode() : 0);
        hash = 31 * hash + (this.type != null ? this.type.hashCode() : 0);
        return hash;
    }

}
