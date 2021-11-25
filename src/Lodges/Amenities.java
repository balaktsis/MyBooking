package Lodges;

import LoginSystem.Roles;

import java.util.ArrayList;

/**
 * This enum consists of a list of different amenities provided in a lodge (property).
 * @author Christos Balaktsis
 */

public enum Amenities {
    WIFI, PARKING, PET_FRIENDLY, AC, TV, REFRIGERATION,
    MOUNT_VIEW, SEA_VIEW, STREET_VIEW, FREE_CANCEL;

    public static String getAmenities() {
        StringBuilder str = new StringBuilder();
        for(Amenities amenity : Amenities.values())
            str.append(amenity.toString()).append(" ");
        str.deleteCharAt(str.length()-1);
        return str.toString();
    }
}
