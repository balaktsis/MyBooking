package Lodges;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

import Users.Landlord;
import Users.Administrator;

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
    private String location;
    private String description;
    private final String lodgeId;
    private LodgeType type;
    private int rating;
    private int size;
    private int numOfBookings;
    private double price;
    private LodgeAvailability availability;
    private ArrayList<Image> photos;
    private ArrayList<Amenities> amenities;

    /**
     * Primary constructor of a Lodge object, initializing the class fields, according to the parameters.
     * @param landlord The user owner of the lodge.
     * @param location The place the lodge is located.
     * @param type The type of lodge.
     * @param size Depends: the number of beds.
     * @param price The cost (in euros) of accommodation per night.
     * @param amenities The different amenities offered in the lodge.
     */
    public Lodge(Landlord landlord, String location, LodgeType type, int size, double price, ArrayList<Amenities> amenities) {
        this.landlord = landlord;
        this.location = location;
        this.type = type;
        this.size = size;
        this.price = price;
        this.amenities = amenities;
        this.availability = new LodgeAvailability();
        this.rating = 0;
        this.numOfBookings = 0;
        this.description = null;
        this.lodgeId = getUniqueId();
    }

    /**
     * Update the user owner of the current lodge.
     * @param landlord New user landlord of the lodge.
     */
    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }

    /**
     * Update the location of the current lodge.
     * @param location New location of the lodge.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Update the type of the current lodge.
     * @param type New type of the lodge.
     */
    public void setType(LodgeType type) {
        this.type = type;
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
     * @param size New size of the lodge.
     */
    public void setSize(int size) {
        this.size = size;
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
    public void setAvailableDates(Date[] dates) {
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
     * Update the photograph list of the current lodge.
     * @param photos New photograph list of the lodge.
     */
    public void setPhotos(ArrayList<Image> photos) {
        this.photos = photos;
    }

    /**
     * Add a new image of the current lodge.
     * @param photo New photograph added in the list of photos of the lodge.
     */
    public void addPhoto(Image photo) {
        this.photos.add(photo);
    }

    /**
     * Delete an image of the current lodge.
     * @param photo Image asked to be removed of the photos list of the lodge.
     */
    public void removePhoto(Image photo) {
        this.photos.remove(photo);
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
    public int getSize() {
        return this.size;
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
     * @return the list of photographs of the current lodge.
     */
    public ArrayList<Image> getPhotos() {
        return this.photos;
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
     * Signals the booking status of the current lodge after being asked to get booked.
     * In case of new booking, it updates the availability status for the requested period
     * and rating of the lodge.
     * @param dates A list of following days, the period of booking the current lodge.
     * @return if the current lodge is available for booking for the dates in "dates".
     */
    public boolean bookLodge(Date[] dates) {
        if(this.availability.markDates(dates)) {
            this.rating = ((int) (1.0 * (this.numOfBookings++) / Administrator.getTotalBookings() * 100) % 6);
            return true;
        }
        return false;
    }

}
