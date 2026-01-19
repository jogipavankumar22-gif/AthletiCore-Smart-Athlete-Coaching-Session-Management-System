package athletico;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Persister {

    private static final String SESSION_FILE = "sessions.txt";

    public void save(String data) {
        try (FileWriter fw = new FileWriter("session_data.txt", true)) {
            fw.write(data + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // save all sessions to a simple text file (overwrite)
    public void saveSessions(List<TrainingSession> sessions) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(SESSION_FILE))) {
            for (TrainingSession s : sessions) {
                // store as pipe-separated: sessionId|athleteId|athleteName|age|sport
                String line = String.join("|",
                        s.getSessionId(),
                        s.getAthlete().getAthleteId(),
                        s.getAthlete().getName(),
                        Integer.toString(s.getAthlete().getAge()),
                        s.getSport()
                );
                pw.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error saving sessions: " + e.getMessage());
        }
    }

    // simple loader that reconstructs TrainingSession objects (coach auto-assigned)
    public List<TrainingSession> loadSessions() {
        List<TrainingSession> out = new ArrayList<>();
        File f = new File(SESSION_FILE);
        if (!f.exists()) return out;

        try (Scanner sc = new Scanner(new FileInputStream(f))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length < 5) continue;
                String sid = parts[0];
                String aid = parts[1];
                String aname = parts[2];
                int age = Integer.parseInt(parts[3]);
                String sport = parts[4];

                Athlete a = new Athlete(aid, aname, age, sport); // use alternate constructor below
                TrainingSession ts = new TrainingSession();
                // restore session id manually (SessionGenerator will still advance; we set directly)
                try {
                    ts.createSession(a); // will auto-assign a new sessionId; we'll overwrite to keep original
                } catch (BookingException be) {
                    // if no coach now, skip
                    continue;
                }
                // override with original ID for consistency
                setPrivateFieldSessionId(ts, sid);
                out.add(ts);
            }
        } catch (Exception e) {
            System.out.println("Error loading sessions: " + e.getMessage());
        }
        return out;
    }

    // reflection helper to set sessionId (keeps code simple for this teaching example)
    private void setPrivateFieldSessionId(TrainingSession ts, String sid) {
        try {
            java.lang.reflect.Field f = TrainingSession.class.getDeclaredField("sessionId");
            f.setAccessible(true);
            f.set(ts, sid);
        } catch (Exception ignored) {}
    }
}
