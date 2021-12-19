package Users.Actions.CommandLine.Landlord;

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
     * @return A list of all booking entries applied on current landlord's property.
     */
    @Override
    public String run(User user, List<String> args) {
        StringBuilder returnStr = new StringBuilder();
        for(BookingEntry bookingEntry : Storage.getBookings())
            if(bookingEntry.getLodge().getLandlord().equals(user))
                returnStr.append(bookingEntry).append("\n");
        return returnStr.toString().equals("") ? "There are no booking entries over your properties." : returnStr.toString();
    }
}
