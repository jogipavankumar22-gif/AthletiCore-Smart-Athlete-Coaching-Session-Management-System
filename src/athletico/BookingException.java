package athletico;

/**
 * Custom exception thrown when booking cannot be completed.
 */
public class BookingException extends Exception {
    public BookingException(String message) {
        super(message);
    }
}
