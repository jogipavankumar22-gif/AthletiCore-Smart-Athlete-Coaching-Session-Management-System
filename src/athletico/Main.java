package athletico;

import java.util.List;
import java.util.Scanner;

/**
 * Menu-driven application (Main).
 * Options:
 * 1. Create Booking (enter athlete details only; coach & session auto assigned)
 * 2. List All Sessions
 * 3. Search Sessions by Athlete ID
 * 4. Sort Sessions by Sport
 * 5. Sort Sessions by Session ID
 * 6. Exit
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SessionManager manager = new SessionManager();

        boolean running = true;
        System.out.println("=== Welcome to AthletiCore (Menu Mode) ===");

        while (running) {
            System.out.println("\n1. Create Booking (auto assign coach & session)");
            System.out.println("2. List All Sessions");
            System.out.println("3. Search Sessions by Athlete ID");
            System.out.println("4. Sort Sessions by Sport");
            System.out.println("5. Sort Sessions by Session ID");
            System.out.println("6. Exit");
            System.out.print("Choose: ");

            String line = sc.nextLine().trim();
            int ch = -1;
            try { ch = Integer.parseInt(line); } catch (Exception ignored) {}

            switch (ch) {
                case 1:
                    createBookingFlow(sc, manager);
                    break;

                case 2:
                    listAll(manager);
                    break;

                case 3:
                    searchByAthlete(sc, manager);
                    break;

                case 4:
                    manager.sortBySport();
                    System.out.println("Sessions sorted by sport.");
                    break;

                case 5:
                    manager.sortBySessionId();
                    System.out.println("Sessions sorted by session id.");
                    break;

                case 6:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Enter 1-6.");
            }
        }

        System.out.println("Exiting AthletiCore. Bye!");
        sc.close();
    }

    private static void createBookingFlow(Scanner sc, SessionManager manager) {
        try {
            System.out.print("Enter Athlete Name: ");
            String aname = sc.nextLine().trim();
            if (aname.isEmpty()) { System.out.println("Name cannot be empty."); return; }

            System.out.print("Enter Athlete Age: ");
            String ageStr = sc.nextLine().trim();
            int age = 0;
            try { age = Integer.parseInt(ageStr); } catch (NumberFormatException nfe) {
                System.out.println("Invalid age."); return;
            }

            System.out.print("Enter Athlete Sport: ");
            String sport = sc.nextLine().trim();
            if (sport.isEmpty()) { System.out.println("Sport cannot be empty."); return; }

            Athlete a = new Athlete(aname, age, sport);
            TrainingSession ts = new TrainingSession();
            ts.createSession(a); // may throw BookingException if no coach
            manager.addSession(ts);

            System.out.println("\n✅ Booking created successfully!");
            System.out.println(ts.getSessionDetails());

            // start a short notifier thread for demonstration
            SessionNotifier notifier = new SessionNotifier(ts.getSessionId());
            notifier.start();

        } catch (BookingException be) {
            System.out.println("Booking failed: " + be.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private static void listAll(SessionManager manager) {
        List<TrainingSession> all = manager.getAllSessions();
        if (all.isEmpty()) {
            System.out.println("No sessions found.");
            return;
        }
        System.out.println("\n--- All Sessions ---");
        for (TrainingSession s : all) {
            System.out.println(s);
        }
        System.out.println("Total sessions: " + manager.totalSessions());
    }

    private static void searchByAthlete(Scanner sc, SessionManager manager) {
        System.out.print("Enter Athlete ID to search (e.g., A101): ");
        String aid = sc.nextLine().trim();
        if (aid.isEmpty()) { System.out.println("Invalid ID."); return; }

        List<TrainingSession> found = manager.searchByAthleteId(aid);
        if (found.isEmpty()) {
            System.out.println("No sessions found for athlete " + aid);
        } else {
            System.out.println("Found sessions:");
            for (TrainingSession s : found) System.out.println(s);
        }
    }
}
