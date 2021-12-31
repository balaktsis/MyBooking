package Lodges;


import javax.swing.*;
import java.io.Serializable;

/**
 * Class that represents the logistics (details) of a lodge.
 * @author Christos Balaktsis
 */
public class LodgeDetails implements Serializable {
    protected String location;
    protected String description;
    protected String title;
    protected int rating;
    protected int beds;
    protected int size;
    protected int numOfBookings;
    protected double price;
    protected ImageIcon image;

    /**
     * Update the title of the current lodge.
     * @param title New title for the lodge.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Update the rating score of the current lodge.
     * @param rating New rating for the lodge.
     */
    public void setRating(int rating) {
        this.rating = Math.max(rating, 0);
    }

    /**
     * Update the number of beds of the current lodge.
     * @param beds New size of the lodge.
     */
    public void setBeds(int beds) {
        this.beds = Math.max(beds, 0);
    }

    /**
     * Update the cost of accommodation per night of the current lodge.
     * @param price New price of the lodge.
     */
    public void setPrice(double price) {
        this.price = Math.max(price, 0);
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
        this.size = Math.max(size, 0);
    }

    /**
     * Update the number of rents of the current lodge.
     * @param numOfBookings New number of total Bookings of the current lodge.
     */
    public void setNumOfBookings(int numOfBookings) {
        this.numOfBookings = Math.max(numOfBookings, 0);
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
     * @return the size in m^2 of the current lodge.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * @return an image of the current lodge.
     */
    public ImageIcon getImage() {
        return this.image;
    }

    /**
     * Updates the image of the current lodge.
     * @param imageIcon An image of a lodge.
     */
    public void setImage(ImageIcon imageIcon) {
        this.image = imageIcon != null ? imageIcon : new ImageIcon("src/Misc/images/defaultLodgeImage.png");
    }

    /**
     * Updates the location of the current lodge.
     * @param location A location field (string) of a lodge.
     */
    public void setLocation(String location) {
        this.location = location;
    }

}
