package Users.Actions.CommandLine.Customer;

import Booking.BookingEntry;
import Lodges.Lodge;
import Misc.Storage;
import Users.Actions.CommandLine.Command;
import Users.Customer;
import Users.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookLodge implements Command {
    @Override
    public String getCommandName() {
        return "book_lodge";
    }

    @Override
    public String getUsage() {
        return getCommandName() + " lodgeId check-in date check-out date (i.e. book_lodge 13 2021-12-11 2021-12-19)";
    }

    @Override
    public int getMaxParams() {
        return 3;
    }

    @Override
    public int getMinParams() {
        return 3;
    }

    /**
     * Attempt to book a lodge, given a lodge ID, check-in date and check-out date.
     * @param args List of Lodge ID, check-in date (ISO 8086), check-out date (ISO 8086)
     * @return String, Result of the operation
     */
    @Override
    public String run(User user, List<String> args) {
        String errorString = "Missing parameters: Lodge id, From Date (YYYY-MM-DD), to Date (YYYY-MM-DD)\n" +
                "Example: book_lodge 13 2021-12-24 2021-12-26";

        ArrayList<String> parameters = new ArrayList<>(args);

        ArrayList<LocalDate> reserveDates = new ArrayList<>();
        //Validated the datetime format
        for (int i=1; i <= 2; i++){
            try {
                reserveDates.add(LocalDate.parse(parameters.get(i)));
            } catch (java.time.format.DateTimeParseException e) {
                return(errorString);
            }
        }

        Lodge desiredLodge = null;
        for (Lodge lodge : Storage.getLodges()){
            if(lodge.getLodgeId().equals(parameters.get(0))){
                desiredLodge = lodge;
                break;
            }
        }

        if (desiredLodge == null) return "No lodge was found with ID: " + parameters.get(0);

        BookingEntry booking = new BookingEntry(user, desiredLodge);

        if (booking.bookLodge(reserveDates.get(0), reserveDates.get(1))){
            return "Lodge booked successfully for the period between " + reserveDates.get(0) + " and " + reserveDates.get(1)
                    + "\nAt a price of €" + booking.getTotalCost();
        } else {
            return "Unfortunately this Lodge has already been booked for this time period.";
        }

    }
}
