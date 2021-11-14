import java.awt.*;
import java.util.ArrayList;

/**This class represents a lodge for renting. It's defined by details such as
 * its owner, location, size and amenities that it offers.
 */

public class Lodge {
    private User landlord;
    private String location;
    private int rating;
    private int size;
    private double price;
    private boolean available;
    private LodgeType type;
    private ArrayList<Image> photos;
    private ArrayList<Amenities> amenities;

}
