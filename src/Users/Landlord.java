package Users;

import Booking.BookingEntry;
import Lodges.Amenities;
import Lodges.Lodge;
import Lodges.LodgeType;
import Misc.Storage;

import java.util.*;

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
        this.commands = new String[]{"my_details", "my_lodges", "add_lodge", "edit_lodge", "delete_lodge",
                                "show_bookings", "show_lodge_bookings", "lookup_booking", "cancel_booking"};
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
    protected String getInterfaceString(String command, String parameters){
        StringBuilder str = new StringBuilder();

        switch (command) {
            case "my_details" -> str.append(this);
            case "my_lodges" -> str.append(getLodges());
            case "add_lodge" -> str.append(addLodge());
            case "edit_lodge" -> str.append(editLodge(parameters));
            case "delete_lodge" -> str.append(removeLodge(parameters));
            case "show_bookings" -> str.append(getBookings());
            case "show_lodge_bookings" -> str.append(getLodgeBookings(parameters));
            case "lookup_booking" -> str.append(getBookingDetails(parameters));
            case "cancel_booking" -> str.append(cancelBooking(parameters));
            default -> str.append("Unknown command, please try again!");
        }

        return str.toString();
    }

    /**
     * @param bookingId The ID of a booking entry.
     * @return Message string about the cancellation process (successful/unsuccessful).
     */
    private String cancelBooking(String bookingId) {
        if(bookingId.equals(""))
            return "Missing parameter: Booking id.\nExample: cancel_booking 15";

        StringBuilder returnStr = new StringBuilder();
        for(BookingEntry bookingEntry : Storage.getBookings())
            if(bookingEntry.getBookingId().equals(bookingId)) {
                if(bookingEntry.getLodge().getLandlord().equals(this)) {
                    if(bookingEntry.cancelBooking())
                        returnStr.append("Cancellation completed. Your lodge is now available on period ")
                                .append(bookingEntry.getArrivalDate()).append(" - ")
                                .append(bookingEntry.getDepartureDate()).append(".");
                    else
                        returnStr.append("Invalid booking entry. Cancellation failed.");
                    break;
                }
                else
                    return "The requested booking entry does not apply to any of your properties.";
            }

        return returnStr.toString().equals("") ? "The requested booking entry does not exist." : returnStr.toString();
    }


    /**
     * @return A list of all booking entries applied on current landlord's property.
     */
    private String getBookings() {
        StringBuilder returnStr = new StringBuilder();
        for(BookingEntry bookingEntry : Storage.getBookings())
            if(bookingEntry.getLodge().getLandlord().equals(this)) {
                returnStr.append(bookingEntry).append("\n");
            }
        return returnStr.toString().equals("") ? "There are no booking entries over your properties." : returnStr.toString();
    }

    /**
     * @param bookingId The ID of a booking entry.
     * @return The booking entry details.
     */
    private String getBookingDetails(String bookingId) {
        if(bookingId.equals("")) return "Missing parameter: Booking id.\nExample: lookup_booking 15";

        StringBuilder returnStr = new StringBuilder();
        for(BookingEntry bookingEntry : Storage.getBookings())
            if(bookingEntry.getBookingId().equals(bookingId)) {
                if(bookingEntry.getLodge().getLandlord().equals(this)) {
                    returnStr.append(bookingEntry).append("\n");
                    break;
                }
                else
                    return "The requested booking entry does not apply to any of your properties.";
            }

        return returnStr.toString().equals("") ? "The requested booking entry does not exist." : returnStr.toString();
    }

    /**
     * @param lodgeId The ID of a lodge.
     * @return A list of bookings of a Lodge.
     */
    private String getLodgeBookings(String lodgeId) {
        if(lodgeId.equals("")) return "Missing parameter: Lodge id.\nExample: show_bookings 15";

        StringBuilder returnStr = new StringBuilder();
        for(BookingEntry bookingEntry : Storage.getBookings())
            if(bookingEntry.getLodge().getLodgeId().equals(lodgeId)) {
                if(bookingEntry.getLodge().getLandlord().equals(this)) {
                    returnStr.append(bookingEntry);
                    returnStr.append("\n");
                }
                else
                    return "The requested lodge does not belong to you.";
            }

        return returnStr.toString().equals("") ? "The requested lodge does not exist or is bookings-free." : returnStr.toString();
    }

    /**
     * Removes a lodge of landlords property, if existed.
     * @param lodgeId The ID of the lodge.
     * @return Message string about the deletion process (successful/unsuccessful).
     */
    private String removeLodge(String lodgeId) {
        if(lodgeId.equals("")) return "Missing parameter: Lodge id.\nExample: remove_lodge 15";

        Lodge lodge = null;
        boolean lodgeExists = false;
        for(Lodge tempLodge : Storage.getLodges())
            if(Objects.equals(tempLodge.getLodgeId(), lodgeId) && tempLodge.getLandlord().equals(this)) {
                lodge = tempLodge;
                lodgeExists = true;
                break;
            }
        if(!lodgeExists) return "Lodge #" + lodgeId + " is not under your property or does not exist.";

        return Storage.getLodges().remove(lodge) ? "Lodge has been removed!" : "Unable to remove the requested lodge!";

    }

    /**
     * Edits details of an owned lodge.
     * @param lodgeId The ID of the lodge.
     * @return Message string about the editing process (successful/unsuccessful).
     */
    private String editLodge(String lodgeId) {
        if(lodgeId.equals("")) return "Missing parameter: Lodge id.\nExample: edit_lodge 15";

        Lodge lodge = null;
        boolean lodgeExists = false, doneExists = false;
        for(Lodge tempLodge : Storage.getLodges())
            if(Objects.equals(tempLodge.getLodgeId(), lodgeId) && tempLodge.getLandlord().equals(this)) {
                lodge = tempLodge;
                lodgeExists = true;
                break;
            }
        if(!lodgeExists) return "Lodge #" + lodgeId + " is not under your property or does not exist.";

        String options = "title description beds size price amenities done ";

        String newString;
        int newInt;
        double newDouble;

        System.out.print("Lodge found. Choose all properties you would like to edit or \"done\" to exit edit mode.\nCommands: ");
        System.out.println(options);

        Scanner inputStream = new Scanner(System.in);
        String input = inputStream.nextLine().toLowerCase(Locale.ROOT);

        String[] fields = input.split(" ");

        for(int j = 0; j < fields.length; j++) {
            if(!options.contains(fields[j] + " ")) {
                System.out.println("Unknown commands (\"" + fields[j] +"\"). Commands: " + options);
                input = inputStream.nextLine().toLowerCase(Locale.ROOT);
                fields = input.split(" ");
                j = -1;
            }
        }

        for (String field : fields)
            if (field.equals("done")) {
                doneExists = true;
                break;
            }

        while (!Objects.equals(fields[0], "done")) {
            for (int i = 0; i < fields.length; i++) {
                switch (fields[i]) {
                    case "title" -> {
                        System.out.print("New title: ");
                        newString = inputStream.nextLine();
                        lodge.setTitle(newString);
                    }
                    case "description" -> {
                        System.out.print("New description: ");
                        newString = inputStream.nextLine();
                        lodge.setDescription(newString);
                    }
                    case "beds" -> {
                        System.out.print("New number of beds: ");
                        newInt = inputStream.nextInt();
                        lodge.setBeds(newInt);
                    }
                    case "size" -> {
                        System.out.print("New size of lodge (in m2): ");
                        newInt = inputStream.nextInt();
                        lodge.setSize(newInt);
                    }
                    case "price" -> {
                        System.out.print("New price per night: € ");
                        newDouble = inputStream.nextDouble();
                        lodge.setPrice(newDouble);
                    }
                    case "amenities" -> {
                        System.out.println("Choose all amenities offered: (type \"yes\" or \"no\" for each one)");
                        for (Amenities amenity : Amenities.values()) {
                            do {
                                System.out.print(amenity.toString() + ": ");
                                newString = inputStream.nextLine().toLowerCase(Locale.ROOT);
                                if (newString.equals("yes")) {
                                    lodge.addAmenity(amenity);
                                    break;
                                } else if (newString.equals("no")) {
                                    if(lodge.getAmenities().contains(amenity))
                                        lodge.removeAmenity(amenity);
                                    break;
                                }
                                System.out.println("Unknown answer. Type \"yes\" or \"no\".");
                            } while (true);
                        }
                    }
                    case "done" -> fields[0] = "done";
                }
                if(i == fields.length - 1 && !doneExists) fields[0] = "done";
            }
        }
        return "Lodge has been updated!";
    }

    /**
     * Creation of a new lodge belonging to the current landlord.
     * @return Message string about the creation process (successful/unsuccessful).
     */
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
        lodgeType = LodgeType.valueOf(type);

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
        String dummy = input.nextLine();
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
        System.out.println("------ " + newLodge);
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
       StringBuilder returnStr = new StringBuilder();
        for (Lodge lodge : Storage.getLodges()) {
            if(lodge.getLandlord().equals(this)) {
                returnStr.append(lodge);
                returnStr.append("\n\n");
            }
        }
        return returnStr.toString().equals("") ? "There are no lodges belonging to you." : returnStr.toString();
    }

    @Override
    public String toString(){
        StringBuilder returnStr = new StringBuilder();
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
        returnStr.append("\n");
        return returnStr.toString();
    }

}
