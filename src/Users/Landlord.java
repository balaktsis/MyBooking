package Users;

import Booking.BookingEntry;
import Lodges.Lodge;
import Misc.Storage;

import java.util.ArrayList;

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
        this.commands = new String[]{"my_details", "my_lodges", "add_lodge", "edit_lodge", "delete_lodge", "show_bookings", "lookup_booking", "cancel_booking"};
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
            if(bookingEntry.getLodge().getLandlord() == this) profit += bookingEntry.getTotalCost();
        return profit;
    }

    /**
     * @return Number of bookings in lodges that belong to the current landlord.
     */
    protected int getNumOfBookings() {
        int num = 0;
        for(BookingEntry bookingEntry : Storage.getBookings())
            if(bookingEntry.getLodge().getLandlord() == this) num++;
        return num;
    }

    /**
     * @return Number of lodges in current landlord's property
     */
    protected int getNumOfLodges() {
        int num = 0;
        for(Lodge lodge : Storage.getLodges())
            if(lodge.getLandlord() == this) num++;
        return num;
    }

    protected int getNumOfCancellations() {
        int num = 0;
        for(BookingEntry bookingEntry : Storage.getBookings())
            if(bookingEntry.getLodge().getLandlord() == this && !bookingEntry.isValid()) num++;
        return num;
    }

    @Override
    protected String getInterfaceString(String command, String parameters){
        StringBuilder str = new StringBuilder();

        switch (command) {
            case "my_details" -> str.append(getDetails());
            case "my_lodges" -> str.append(getLodges());
            case "add_lodge" -> str.append(addLodge());
            case "edit_lodge" -> str.append(editLodge(parameters));
            case "delete_lodge" -> str.append(removeLodge(parameters));
            case "show_bookings" -> str.append(getBookings(parameters));    //parameters = lodge id
            case "lookup_booking" -> str.append(getBookingDetails(parameters)); //parameters = booking id
            case "cancel_booking" -> str.append(cancelBooking(parameters)); //parameters = booking id
            default -> str.append("Unknown command, please try again!");
        }

        return str.toString();
    }

    private String cancelBooking(String parameters) {return null;
    }

    private String getBookingDetails(String parameters) {return null;
    }

    private String getBookings(String parameters) {return null;
    }

    private String removeLodge(String parameters) {return null;
    }

    private String editLodge(String parameters) {return null;
    }

    private String addLodge() {
        return null;
    }

    /**
     * @return List of all lodges belonging to the current landlord.
     */
    private String getLodges() {
       StringBuilder lodges = new StringBuilder();
        for (Lodge lodge : Storage.getLodges()) {
            if(lodge.getLandlord() == this) {
                lodges.append(lodge);
                lodges.append("\n");
            }
        }
        return lodges.toString();
    }

    /**
     * @return List of personal details, number of lodges, bookings, profits, cancellations.
     */
    private String getDetails() {
        StringBuilder returnStr = new StringBuilder();
        returnStr.append(super.toString());
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
        returnStr.append("\n");
        return returnStr.toString();
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Landlord\n");
        str.append(super.toString());
        str.append("\nBase: ");
        str.append(this.base);
        return str.toString();
    }

}
