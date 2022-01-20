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
    public boolean addRoom(Lodge room) {
        if(room == null) return false;
        if(room.getType().equals(LodgeType.ROOM)) {
            rooms.put(room.getLodgeId(), room);
            return Storage.getLodges().add(room);
        }
        return false;
    }

    /**
     * Removes a room from the room collection of the hotel.
     * @param roomId The roomId of the requested room.
     */
    public boolean removeRoom(String roomId) {
        if(this.rooms.get(roomId)!=null) {
            Lodge tmpLodge = this.rooms.get(roomId);
            this.rooms.remove(roomId);
            return Storage.getLodges().remove(tmpLodge);
        }
        return false;
    }


    /**
     * @return A HashMap of rooms - the room collection of the hotel.
     */
    public HashMap<String, Lodge> getRooms() {
        return this.rooms;
    }
}
