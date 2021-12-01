package Users.Actions.Admin;

import Booking.BookingEntry;
import Misc.Storage;
import Users.Actions.Command;
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
     * Show all booking entries
     * @return String with all bookings
     */
    @Override
    public String run(User user, List<String> args) {
        StringBuilder returnStr = new StringBuilder();
        for (BookingEntry booking : Storage.getBookings()) {
            returnStr.append(booking.toString());
            returnStr.append("\n");
        }
        return returnStr.toString();
    }
}
