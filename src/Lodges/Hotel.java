package Lodges;

import Users.Landlord;

import java.util.HashMap;

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

    public void addRoom(Lodge room) {
        rooms.put(this.getLodgeId() + "-" + roomId++, room);
    }

    public HashMap<String, Lodge> getRooms() {
        return this.rooms;
    }

    public boolean removeRoom(String roomId) {
        return this.rooms.remove(roomId, this.rooms.get(roomId));
    }

}
