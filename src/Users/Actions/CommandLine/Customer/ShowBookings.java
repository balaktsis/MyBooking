package Users.Actions.CommandLine.Customer;

import Booking.BookingEntry;
import Misc.Storage;
import Users.Actions.CommandLine.Command;
import Users.User;

import java.util.List;

public class ShowBookings implements Command {
    @Override
    public String getCommandName() {
        return "show_bookings";
    }

    @Override
    public String getUsage() {
        return getCommandName();
    }

    @Override
    public int getMaxParams() {
        return 0;
    }

    @Override
    public int getMinParams() {
        return 0;
    }

    /**
     * Show all bookings made by the user
     * @return String containing the details of every booking.
     */
    @Override
    public String run(User user, List<String> args) {
        StringBuilder returnStr = new StringBuilder();

        for (BookingEntry booking : Storage.getBookings()){
            if (booking.getTenant().equals(user)){
                returnStr.append(booking).append("\n");
            }
        }

        return returnStr.isEmpty() ? "You have made no bookings yet!" : returnStr.toString();
    }
}
