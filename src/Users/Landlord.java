package Users;

import Booking.BookingEntry;
import Lodges.Lodge;
import Misc.Storage;
import Users.Actions.LandlordCommandLineManager;


/**
 * This is a child of the User class, which contains functionality unique
 * to the Landlord User Type
 * @author Christos Balaktsis
 */
public class Landlord extends User{
    private String base;

    /**
     * Primary constructor of a Landlord User object, takes in credentials,
     * initializes the class fields and assigns a unique ID to the object
     *
     * @param username Login username of the new user
     * @param password Login password of the new user
     */
    public Landlord(String username, String password) {
        super(username, password);
        this.commandLineManager = new LandlordCommandLineManager(this);
    }

    /**
     * Set the user's address
     * @param base String containing a landlord's base
     */
    public void setBase(String base){
        this.base = base;
    }

    /**
     * @return String containing a landlord's base
     */
    public String getBase(){
        return this.base;
    }

    /**
     * @return Total profit of all valid bookings on the current landlord's property.
     */
    protected double getTotalProfit() {
        double profit = 0;
        for(BookingEntry bookingEntry : Storage.getBookings())
            if(bookingEntry.getLodge().getLandlord().equals(this))
                profit += bookingEntry.getTotalCost();
        return profit;
    }

    /**
     * @return Number of bookings in lodges that belong to the current landlord.
     */
    protected int getNumOfBookings() {
        int num = 0;
        for(BookingEntry bookingEntry : Storage.getBookings())
            if(bookingEntry.getLodge().getLandlord().equals(this) && bookingEntry.isValid())
                num++;
        return num;
    }

    /**
     * @return Number of lodges in current landlord's property.
     */
    protected int getNumOfLodges() {
        int num = 0;
        for(Lodge lodge : Storage.getLodges())
            if(lodge.getLandlord().equals(this)) num++;
        return num;
    }

    /**
     * @return Number of cancellations in current landlord's property.
     */
    protected int getNumOfCancellations() {
        int num = 0;
        for(BookingEntry bookingEntry : Storage.getBookings())
            if(bookingEntry.getLodge().getLandlord().equals(this) && !bookingEntry.isValid()) num++;
        return num;
    }

    @Override
    public String toString(){
        StringBuilder returnStr = new StringBuilder();
        returnStr.append("Landlord\n");
        returnStr.append(super.toString()).append("\n");
        returnStr.append("Base: ");
        returnStr.append(this.getBase());
        returnStr.append("\nNumber of lodges: ");
        returnStr.append(this.getNumOfLodges());
        returnStr.append("\nNumber of Bookings: ");
        returnStr.append(this.getNumOfBookings());
        returnStr.append("\nNumber of Cancellations: ");
        returnStr.append(this.getNumOfCancellations());
        returnStr.append("\nTotal Profit: € ");
        returnStr.append(this.getTotalProfit());
        return returnStr.toString();
    }

}
