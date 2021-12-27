package Lodges;

import java.io.Serializable;

/**
 * This enum consists of a list of different amenities provided in a lodge (property).
 * @author Christos Balaktsis
 */

public enum Amenities implements Serializable {
    WIFI("Wifi"), PARKING("Parking"), PET_FRIENDLY("Pet-friendly"), AC("AC"), TV("TV"),
    REFRIGERATION("Refrigeration"), MOUNT_VIEW("Mountain view"), SEA_VIEW("Sea view"),
    STREET_VIEW("Street view"), FREE_CANCEL("No fees for canceling");

    public final String label;

    Amenities(String label) { this.label = label; }

    public static String getAmenities() {
        StringBuilder str = new StringBuilder();
        for (Amenities amenity : Amenities.values())
            str.append(amenity.toString()).append(" ");
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }

    public static Amenities valueOfLabel(String label) {
        for (Amenities e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}
