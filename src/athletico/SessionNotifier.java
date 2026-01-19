package athletico;

/**
 * Simple notifier thread that counts down from 5 seconds for demonstration.
 */
public class SessionNotifier extends Thread {

    private final String sessionId;

    public SessionNotifier(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public void run() {
        try {
            for (int i = 5; i >= 1; i--) {
                System.out.println("[Notifier " + sessionId + "] Session starts in " + i + "s");
                Thread.sleep(1000);
            }
            System.out.println("[Notifier " + sessionId + "] Session started!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("[Notifier " + sessionId + "] Interrupted.");
        }
    }
}
