package Misc;

/**
 * Class that generates unique, sequential ID numbers
 * @author Neron Panagiotopoulos
 */
public class UniqueIDGenerator {
    private static long sequentialId = 6;

    /**
     * Generates a new unique ID and returns it.
     * @return UniqueID
     */
    public static String getUniqueId(){
        return String.valueOf(sequentialId++);
    }

    public static long getSequentialId() {
        return sequentialId;
    }

    public static void setSequentialId(long id) {
        sequentialId = id;
    }

}
