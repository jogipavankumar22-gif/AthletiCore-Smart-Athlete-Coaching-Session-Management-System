package athletico;

/**
 * Athlete model. ID is auto-generated when using the primary constructor.
 */
public class Athlete {
    private final String athleteId;
    private final String name;
    private final int age;
    private final String sport;

    // Constructor used for new athletes (auto id)
    public Athlete(String name, int age, String sport) {
        this.athleteId = AthleteIDGenerator.generateID();
        this.name = name;
        this.age = age;
        this.sport = sport;
    }

    // Alternate constructor (if you ever want to set ID manually)
    public Athlete(String athleteId, String name, int age, String sport) {
        this.athleteId = athleteId;
        this.name = name;
        this.age = age;
        this.sport = sport;
    }

    public String getAthleteId() {
        return athleteId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSport() {
        return sport;
    }

    @Override
    public String toString() {
        return athleteId + " | " + name + " | Age:" + age + " | Sport:" + sport;
    }
}
