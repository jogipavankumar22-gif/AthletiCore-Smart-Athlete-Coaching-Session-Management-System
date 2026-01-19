package athletico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Manages collection of TrainingSession objects (add/list/search/sort).
 */
public class SessionManager {

    private final ArrayList<TrainingSession> sessions = new ArrayList<>();

    public void addSession(TrainingSession s) {
        if (s != null) sessions.add(s);
    }

    public List<TrainingSession> getAllSessions() {
        return new ArrayList<>(sessions);
    }

    public List<TrainingSession> searchByAthleteId(String athleteId) {
        ArrayList<TrainingSession> out = new ArrayList<>();
        if (athleteId == null) return out;
        for (TrainingSession s : sessions) {
            if (s.getAthlete() != null && athleteId.equalsIgnoreCase(s.getAthlete().getAthleteId())) {
                out.add(s);
            }
        }
        return out;
    }

    public void sortBySport() {
        Collections.sort(sessions, new Comparator<TrainingSession>() {
            @Override
            public int compare(TrainingSession a, TrainingSession b) {
                return a.getSport().compareToIgnoreCase(b.getSport());
            }
        });
    }

    public void sortBySessionId() {
        Collections.sort(sessions, new Comparator<TrainingSession>() {
            @Override
            public int compare(TrainingSession a, TrainingSession b) {
                return a.getSessionId().compareToIgnoreCase(b.getSessionId());
            }
        });
    }

    public int totalSessions() {
        return sessions.size();
    }
}
