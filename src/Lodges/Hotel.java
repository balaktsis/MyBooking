package Lodges;

import Misc.Storage;
import Users.Landlord;

import java.util.HashMap;

/**
 * This class represents a hotel that consists of rooms and belongs to a landlord.
 * @author Christos Balaktsis
 */

public class Hotel extends Lodge {
    private final HashMap<String, Lodge> rooms;
    private static int roomId = 0;

    /**
     * Primary constructor of a hotel object, initializing the class fields, according to the parameters.
     * @param landlord The user owner of the hotel.
     * @param location The location of the hotel.
     */
    public Hotel(Landlord landlord, String location) {
        super(landlord, location, LodgeType.HOTEL);
        this.rooms = new HashMap<>();
    }

    /**
     * Adds a room in the room collection of the hotel.
     * @param room The new room.
     */
    public void addRoom(Lodge room) {
        rooms.put(this.getLodgeId() + "-" + roomId++, room);
        Storage.getLodges().add(room);
    }

    /**
     * Removes a room from the room collection of the hotel.
     * @param roomId The roomId of the requested room.
     */
    public boolean removeRoom(String roomId) {
        return Storage.getLodges().remove(this.rooms.remove(this.rooms.get(roomId) != null ? this.rooms.get(roomId) : null));
    }


    /**
     * @return A HashMap of rooms - the room collection of the hotel.
     */
    public HashMap<String, Lodge> getRooms() {
        return this.rooms;
    }
}
