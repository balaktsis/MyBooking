import Booking.BookingEntry;
import Lodges.Lodge;
import Lodges.LodgeType;
import Misc.Storage;
import Users.Administrator;
import Users.Customer;
import Users.Landlord;
import org.junit.Before;
import org.junit.Test;

public class BookingTest {
    Landlord landlord;
    Customer customer;
    BookingEntry booking;
    Lodge lodge;

    @Before
    public void initializeObjects() {
        Storage.getUsers().removeAll(Storage.getUsers());
        Storage.getLodges().removeAll(Storage.getLodges());
        Storage.getBookings().removeAll(Storage.getBookings());
        landlord = new Landlord("landlord","123456");
        landlord.setApprovalStatus(true);
        customer = new Customer("customer","123456");
        customer.setApprovalStatus(true);

        lodge = new Lodge(landlord, "Persia", LodgeType.ROOM);

        booking = new BookingEntry(customer, lodge);
    }

    @Test
    public void setPeriodTest(){

    }

}
