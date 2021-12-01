package Users;

import Booking.BookingEntry;
import Lodges.Amenities;
import Lodges.Lodge;
import Misc.Storage;
import Users.Actions.AdminCommandLineManager;
import Users.Actions.CustomerCommandLineManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/**
 * This is a child of the User class, which contains functionality unique
 * to the Customer User Type
 * @author Neron Panagiotopoulos
 */
public class Customer extends User{

    private String address;

    /**
     * Primary constructor of a Customer User object, takes in credentials,
     * initializes the class fields and assigns a unique ID to the object
     *
     * @param username Login username of the new user
     * @param password Login password of the new user
     */
    public Customer(String username, String password) {
        super(username, password);
        this.commandLineManager = new CustomerCommandLineManager(this);
    }

    /**
     * Set the user's address
     * @param address String containing a user's address
     */
    public void setAddress(String address){
        this.address = address;
    }

    /**
     * @return String containing a user's address
     */
    public String getAddress(){
        return this.address;
    }

//    @Override
//    protected String getInterfaceString(String command, String parameters){
//        StringBuilder str = new StringBuilder();
//
//        switch (command) {
//            case "lookup_lodges" -> str.append(lookupLodges(parameters));
//            case "book_lodge" -> str.append(bookLodge(parameters));
//            case "show_bookings" -> str.append(showBookings());
//            case "cancel_booking" -> str.append(cancelBooking(parameters));
//            case "my_profile" -> str.append(this);
//            default -> str.append("Unknown command, please try again!");
//        }
//
//        return str.toString();
//    }

    /**
     * Cancel a booking
     * @param bookingID String, ID of the booking to be canceled
     * @return Result of the operation
     */
    private String cancelBooking(String bookingID){

        if(bookingID.trim().equals("")){
            return "Missing Booking ID!";
        }

        for (BookingEntry booking : Storage.getBookings()){
            if (booking.getBookingId().equals(bookingID)){
                booking.cancelBooking();
                return "Canceled booking with ID: " + bookingID;
            }
        }
        return "No bookings were found for ID: " + bookingID;
    }

    /**
     * Show all bookings made by the user
     * @return String containing the details of every booking.
     */
    private String showBookings(){
        StringBuilder returnStr = new StringBuilder();

        for (BookingEntry booking : Storage.getBookings()){
            if (booking.getTenant().equals(this)){
                returnStr.append(booking).append("\n");
            }
        }

        return returnStr.isEmpty() ? "You have made no bookings yet!" : returnStr.toString();
    }

    /**
     * Parameter Search through lodge options, based on criteria on offered amenities.
     * @param amenities String input by the user containing all necessary amenities
     * @return String , resulting lodges that fit the criteria
     */
    private String lookupLodges(String amenities){

        //Splitting the amenities required by the user into separate strings
        String[] splitParameters = amenities.toLowerCase().split(" +");
        ArrayList<String> required_amenities = new ArrayList<>(Arrays.asList(splitParameters));
        required_amenities.remove("");


        StringBuilder returnStr = new StringBuilder();

        //Looping through the registered lodges to get the ones matching the criteria.
        //If no criteria set, it returns all lodges
        for (Lodge lodge : Storage.getLodges()){

            //For every lodge, get all it's amenities
            ArrayList<String> available_amenities = new ArrayList<>();
            for (Amenities amenity : lodge.getAmenities()) {
                available_amenities.add(amenity.toString().toLowerCase(Locale.ROOT));
            }

            //If the required amenities are a subset of the available amenities, the lodge is displayed.
            if(available_amenities.containsAll(required_amenities)){
                returnStr.append(lodge).append("\n");
            }
        }

        if (returnStr.isEmpty()) returnStr.append("No lodges were found that satisfy your criteria.");

        return returnStr.toString();
    }

    /**
     * Attempt to book a lodge, given a lodge ID, check-in date and check-out date.
     * @param parameters String (Space seperated) Lodge ID, check-in date (ISO 8086), check-out date (ISO 8086)
     * @return String, Result of the operation
     */
    private String bookLodge(String parameters){

        String errorString = "Missing parameters: Lodge id, From Date (YYYY-MM-DD), to Date (YYYY-MM-DD)\n" +
                             "Example: book_lodge 13 2021-12-24 2021-12-26";

        String[] splitParameters = parameters.split(" +");
        if(splitParameters.length != 3){
            return(errorString);
        }

        ArrayList<LocalDate> reserveDates = new ArrayList<>();
        //Validated the datetime format
        for (int i=1; i <= 2; i++){
            try {
                reserveDates.add(LocalDate.parse(splitParameters[i]));
            } catch (java.time.format.DateTimeParseException e) {
                return(errorString);
            }
        }

        Lodge desiredLodge = null;
        for (Lodge lodge : Storage.getLodges()){
            if(lodge.getLodgeId().equals(splitParameters[0])){
                desiredLodge = lodge;
                break;
            }
        }

        if (desiredLodge == null) return "No lodge was found with ID: " + splitParameters[0];

        BookingEntry booking = new BookingEntry(this, desiredLodge);

        if (booking.bookLodge(reserveDates.get(0), reserveDates.get(1))){
            return "Lodge booked successfully for the period between " + reserveDates.get(0) + " and " + reserveDates.get(1)
                    + "\nAt a price of €" + booking.getTotalCost();
        } else {
            return "Unfortunately this Lodge has already been booked for this time period.";
        }

    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Customer\n");
        str.append(super.toString());
        str.append("\nAddress: ");
        str.append(this.address);
        return str.toString();
    }

}
