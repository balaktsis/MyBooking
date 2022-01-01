import Lodges.*;
import Users.Landlord;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.Assert.*;


/**
 * This is a test class for Lodge subclasses/child-classes functionalities.
 * @author Christos Balaktsis
 */

public class LodgeTest {
    Lodge lodge;
    Landlord landlord;
    HashSet<LocalDate> dates;

    @Before
    public void initializeObjects() {
        landlord = new Landlord("landlord","123456");
        lodge = new Lodge(landlord,"Thessaloniki, Greece", LodgeType.APARTMENT);
        dates = new HashSet<>();
        dates.add(LocalDate.of(2022,1,8));
        dates.add(LocalDate.of(2022,1,9));
        dates.add(LocalDate.of(2022,1,10));
    }

    @Test
    public void setLodgeTest() {
        assertTrue(lodge.setLandlord(new Landlord("landlord2","123456")));
        assertFalse(lodge.setLandlord(null));
        assertTrue(lodge.setLandlord(landlord));

        assertEquals(0,lodge.getDetails().getSize());
        assertEquals(0,lodge.getDetails().getBeds());
        assertEquals(0,lodge.getDetails().getPrice(),0.000001d);
        assertEquals(0,lodge.getDetails().getRating());
        assertEquals(0,lodge.getDetails().getNumOfBookings());
        assertEquals(LodgeType.APARTMENT,lodge.getType());
        assertEquals("Untitled Lodge",lodge.getDetails().getTitle());
        assertEquals("No description has been set",lodge.getDetails().getDescription());
        assertEquals("Thessaloniki, Greece",lodge.getDetails().getLocation());
        assertNull(lodge.getDetails().getImage());

        lodge.getDetails().setSize(10);
        lodge.getDetails().setBeds(2);
        lodge.getDetails().setPrice(40.55);
        lodge.getDetails().setRating(4);
        lodge.getDetails().setNumOfBookings(7);
        lodge.getDetails().setTitle("Salonica Apartment");
        lodge.getDetails().setDescription("This apartment is a good place to live in Greece.");
        lodge.getDetails().setLocation("Athens, Greece");

        assertEquals(10,lodge.getDetails().getSize());
        assertEquals(2,lodge.getDetails().getBeds());
        assertEquals(40.55,lodge.getDetails().getPrice(),0.000001d);
        assertEquals(4,lodge.getDetails().getRating());
        assertEquals(7,lodge.getDetails().getNumOfBookings());
        assertEquals("Salonica Apartment",lodge.getDetails().getTitle());
        assertEquals("This apartment is a good place to live in Greece.",lodge.getDetails().getDescription());
        assertEquals("Athens, Greece",lodge.getDetails().getLocation());

        lodge.getDetails().setSize(-10);
        lodge.getDetails().setBeds(-2);
        lodge.getDetails().setPrice(-40.55);
        lodge.getDetails().setRating(0);
        lodge.getDetails().setNumOfBookings(-7);

        assertEquals(0,lodge.getDetails().getSize());
        assertEquals(0,lodge.getDetails().getBeds());
        assertEquals(0,lodge.getDetails().getPrice(),0.000001d);
        assertEquals(0,lodge.getDetails().getRating());
        assertEquals(0,lodge.getDetails().getNumOfBookings());
    }

    @Test
    public void setAmenitiesTest() {
        assertEquals(0,lodge.getAmenities().size());

        HashSet<Amenities> amenities = new HashSet<>();
        amenities.add(Amenities.AC);
        amenities.add(Amenities.SEA_VIEW);
        amenities.add(Amenities.REFRIGERATION);
        lodge.setAmenities(amenities);
        assertEquals(3,lodge.getAmenities().size());

        lodge.setAmenities(new HashSet<>());
        lodge.addAmenity(Amenities.PARKING);
        lodge.addAmenity(Amenities.FREE_CANCEL);
        assertEquals(2,lodge.getAmenities().size());
    }

    @Test
    public void setAvailabilityTest() {
        assertEquals(0,lodge.getAvailability().getBookCalendar().size());

        lodge.getAvailability().markDates(dates);
        assertEquals(3,lodge.getAvailability().getBookCalendar().size());

        lodge.getAvailability().freeDates(dates);
        assertEquals(0,lodge.getAvailability().getBookCalendar().size());

        lodge.getAvailability().setBookCalendar(dates);
        assertEquals(3,lodge.getAvailability().getBookCalendar().size());

        lodge.setAvailableDates(dates);
        assertEquals(0,lodge.getAvailability().getBookCalendar().size());
    }

    @Test
    public void bookLodgeTest() {
        assertTrue(lodge.bookLodge(dates));

        dates = new HashSet<>();
        dates.add(LocalDate.of(2022,1,7));
        dates.add(LocalDate.of(2022,1,8));
        dates.add(LocalDate.of(2022,1,9));
        assertFalse(lodge.bookLodge(dates));

        dates = new HashSet<>();
        dates.add(LocalDate.of(2022,1,10));
        dates.add(LocalDate.of(2022,1,11));
        dates.add(LocalDate.of(2022,1,12));
        assertTrue(lodge.bookLodge(dates));
    }

    @Test
    public void hotelTest() {
        Hotel hotel = new Hotel(landlord,lodge.getDetails().getLocation());
        assertEquals(0,hotel.getRooms().size());
        Lodge room1 = new Lodge(landlord,"Thessaloniki, Greece",LodgeType.ROOM);
        assertTrue(hotel.addRoom(room1));
        Lodge room2 = new Lodge(landlord,"Thessaloniki, Greece",LodgeType.APARTMENT);
        assertFalse(hotel.addRoom(room2));
        assertTrue(hotel.removeRoom(room1.getLodgeId()));
        assertEquals(0,hotel.getRooms().size());
    }
}
