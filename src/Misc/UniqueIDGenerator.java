package Misc;

/**
 * Class that generates unique, sequential ID numbers
 * @author Neron Panagiotopoulos
 */
public class UniqueIDGenerator {
    private static long sequentialId = 0;

    /**
     * Generates a new unique ID and returns it.
     * @return UniqueID
     */
    public static String getUniqueId(){
        return String.valueOf(sequentialId++);
    }

}
