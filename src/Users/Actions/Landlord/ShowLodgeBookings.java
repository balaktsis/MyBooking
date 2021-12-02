package Users.Actions.Landlord;

import Booking.BookingEntry;
import Misc.Storage;
import Users.Actions.Command;
import Users.User;

import java.util.List;

public class ShowLodgeBookings implements Command {
    @Override
    public String getCommandName() {
        return "show_lodge_bookings";
    }

    @Override
    public String getUsage() {
        return getCommandName() + " lodgeId";
    }

    @Override
    public int getMaxParams() {
        return 1;
    }

    @Override
    public int getMinParams() {
        return 1;
    }

    /**
     * @param args The ID of a lodge.
     * @return A list of bookings of a lodge.
     */
    @Override
    public String run(User user, List<String> args) {
        String lodgeId = args.get(0);
        StringBuilder returnStr = new StringBuilder();
        for(BookingEntry bookingEntry : Storage.getBookings())
            if(bookingEntry.getLodge().getLodgeId().equals(lodgeId) && bookingEntry.getLodge().getLandlord().equals(user))
                returnStr.append(bookingEntry).append("\n");

        return returnStr.toString().equals("") ? "The requested lodge does not belong to your lodges or is bookings-free." : returnStr.toString();
    }
}
