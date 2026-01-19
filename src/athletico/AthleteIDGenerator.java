package athletico;

/**
 * Simple auto-increment generator for athlete IDs.
 * Produces A101, A102, ...
 */
public class AthleteIDGenerator {
    private static int counter = 101;

    public static synchronized String generateID() {
        return "A" + (counter++);
    }
}
