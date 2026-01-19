package athletico;

/**
 * Represents a booked training session: sessionId, sport, athlete, coach.
 * The createSession() method auto-selects a coach (via DataStore) and auto-generates a session ID.
 */
public class TrainingSession {
    private String sessionId;
    private String sport;
    private Athlete athlete;
    private Coach coach;

    public TrainingSession() {
        // empty; use createSession to initialize
    }

    /**
     * Auto-creates the session: assigns sessionId, assigns coach according to athlete.sport
     * @throws BookingException if no coach exists for athlete's sport
     */
    public void createSession(Athlete athlete) throws BookingException {
        if (athlete == null) throw new BookingException("Athlete cannot be null.");

        Coach selected = DataStore.getCoachBySport(athlete.getSport());
        if (selected == null) {
            throw new BookingException("No coach available for sport: " + athlete.getSport());
        }

        this.sessionId = SessionGenerator.generateID();
        this.sport = athlete.getSport();
        this.athlete = athlete;
        this.coach = selected;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getSport() {
        return sport;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public Coach getCoach() {
        return coach;
    }

    public String getSessionDetails() {
        if (sessionId == null) return "Uninitialized Session";
        return String.format("Session:%s | Sport:%s | Athlete:%s - %s | Coach:%s - %s [%s]",
                sessionId,
                sport,
                athlete.getAthleteId(),
                athlete.getName(),
                coach.getCoachId(),
                coach.getName(),
                coach.getSpeciality());
    }

    @Override
    public String toString() {
        return getSessionDetails();
    }
}
