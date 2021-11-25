package Users;

import Booking.BookingEntry;
import Lodges.Lodge;
import Misc.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

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
        this.commands = new String[]{"lookup_lodges", "book_lodge", "cancel_booking", "show_bookings", "my_profile"};
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

    @Override
    protected String getInterfaceString(String command, String parameters){
        StringBuilder str = new StringBuilder();

        switch (command) {
            case "lookup_lodges" -> {}
            case "book_lodge" -> str.append(bookLodge(parameters));
            case "show_bookings" -> {}
            case "cancel_booking" -> {}
            case "my_profile" -> {}
            default -> str.append("Unknown command, please try again!");
        }

        return str.toString();
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
