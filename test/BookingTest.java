import Booking.BookingEntry;
import Lodges.Lodge;
import Lodges.LodgeType;
import Misc.Storage;
import Misc.UniqueIDGenerator;
import Users.Customer;
import Users.Landlord;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class BookingTest {
    Landlord landlord;
    Customer customer;
    BookingEntry booking;
    Lodge lodge;

    @Before
    public void initializeObjects() {
        UniqueIDGenerator.setSequentialId(0);
        Storage.getUsers().removeAll(Storage.getUsers());
        Storage.getLodges().removeAll(Storage.getLodges());
        Storage.getBookings().removeAll(Storage.getBookings());
        landlord = new Landlord("landlord","123456");
        landlord.setApprovalStatus(true);
        customer = new Customer("customer","123456");
        customer.setApprovalStatus(true);

        lodge = new Lodge(landlord, "Persia", LodgeType.ROOM);

        booking = new BookingEntry(customer, lodge);

        LocalDate arrival = LocalDate.now();
        LocalDate depart = LocalDate.now().plusDays(10);

        assertTrue(booking.bookLodge(arrival, depart));

    }


    @Test
    public void getArrivalDateTest() {
        assertEquals(booking.getArrivalDate(), LocalDate.now());
    }

    @Test
    public void getDepartureDateTest() {
        assertEquals(booking.getDepartureDate(), LocalDate.now().plusDays(10));
    }

    @Test
    public void getEntryDateTest() {

        assertEquals(booking.getEntryDate(), LocalDate.now());

    }

    @Test
    public void isValidTest() {

        assertTrue(booking.isValid());

    }

    @Test
    public void getTotalCostTest() {

        assertEquals(booking.getTotalCost(), lodge.getDetails().getPrice()*10, 0.0000d);

    }

    @Test
    public void getTenantTest() {

        assertEquals(booking.getTenant(), customer);

    }

    @Test
    public void getBookingIdTest() {

        assertEquals("3", booking.getBookingId());

    }

    @Test
    public void getLodgeTest() {
        assertEquals(lodge, booking.getLodge());
    }

    @Test
    public void cancelBookingTest() {
        assertTrue(booking.cancelBooking());
        assertFalse(booking.isValid());
    }

    @Test
    public void getBookingsOnDateTest() {
        assertEquals(booking, BookingEntry.getBookingsOnDate(LocalDate.now().toString()).get(0));
    }


}
