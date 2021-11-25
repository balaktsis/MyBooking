package Users;

import Booking.BookingEntry;
import Lodges.Amenities;
import Lodges.Lodge;
import Lodges.LodgeType;
import LoginSystem.Roles;
import Misc.Storage;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

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
            case "my_details" -> str.append(this);
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
        Scanner input = new Scanner(System.in);
        String location, type, answer;
        LodgeType lodgeType;
        System.out.println("In order to add a new Lodge for bookings, please, complete the following fields...");
        System.out.print("Location: ");
        location = input.nextLine();
        do {
            System.out.print("Type (" + LodgeType.getLodgeTypes() + "): ");
            type = input.nextLine().toUpperCase(Locale.ROOT);
            if (!LodgeType.isLodgeType(type)) System.out.println("Unknown type of Lodge. Try something else.");
        } while (!LodgeType.isLodgeType(type));
        switch (type) {
            case "APARTMENT" -> lodgeType = LodgeType.APARTMENT;
            case "ROOM" -> lodgeType = LodgeType.ROOM;
            case "TRAILER" -> lodgeType = LodgeType.TRAILER;
            case "HOTEL" -> lodgeType = LodgeType.HOTEL;
            default -> lodgeType = null;
        }
        Lodge newLodge = new Lodge(this, location, lodgeType);
        System.out.print("Title: ");
        newLodge.setTitle(input.nextLine());
        System.out.print("Price (per night): € ");
        newLodge.setPrice(input.nextDouble());
        System.out.print("Size: (m2) ");
        newLodge.setSize(input.nextInt());
        System.out.print("Beds: ");
        newLodge.setBeds(input.nextInt());
        System.out.print("Description: ");
        answer = input.nextLine();          //takes "\n"
        answer = input.nextLine();
        newLodge.setDescription(answer);
        System.out.println("Amenities: (type \"yes\" or \"no\" for each one)");
        for(Amenities amenity : Amenities.values()) {
           do {
               System.out.print(amenity.toString() + ": ");
               answer = input.nextLine().toLowerCase(Locale.ROOT);
               if (answer.equals("yes")) {
                   newLodge.addAmenity(amenity);
                   break;
               } else if (answer.equals("no"))
                   break;
               System.out.println("Unknown answer. Type \"yes\" or \"no\".");
           } while(true);
        }
        System.out.println("Are you sure you want to add the following lodge to your lodges list?");
        System.out.println("|||||||||||| " + newLodge);
        do {
            System.out.println("Type \"yes\" or \"no\".");
            answer = input.nextLine().toLowerCase(Locale.ROOT);
            if (answer.equals("yes")) {
                Storage.getLodges().add(newLodge);
                return "Lodge #" + newLodge.getLodgeId() + " is added to your lodges!";
            } else if (answer.equals("no"))
                return "Lodge addition cancelled!";
            System.out.println("Unknown answer. Type \"yes\" or \"no\".");
        } while(true);
    }

    /**
     * @return List of all lodges belonging to the current landlord.
     */
    private String getLodges() {
       StringBuilder lodges = new StringBuilder();
        for (Lodge lodge : Storage.getLodges()) {
            if(lodge.getLandlord() == this) {
                lodges.append(lodge);
                lodges.append("\n\n");
            }
        }
        return lodges.toString();
    }

    @Override
    public String toString(){
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

}
