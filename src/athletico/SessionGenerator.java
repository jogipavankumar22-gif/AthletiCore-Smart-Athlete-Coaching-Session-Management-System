package athletico;

/**
 * Simple auto-increment generator for session IDs.
 * Produces S1, S2, ...
 */
public class SessionGenerator {
    private static int counter = 1;

    public static synchronized String generateID() {
        return "S" + (counter++);
    }
}
