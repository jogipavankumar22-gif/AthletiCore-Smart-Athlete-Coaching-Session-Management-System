package athletico;

/**
 * Coach model.
 */
public class Coach {
    private final String coachId;
    private final String name;
    private final String speciality;

    public Coach(String coachId, String name, String speciality) {
        this.coachId = coachId;
        this.name = name;
        this.speciality = speciality;
    }

    public String getCoachId() {
        return coachId;
    }

    public String getName() {
        return name;
    }

    public String getSpeciality() {
        return speciality;
    }

    @Override
    public String toString() {
        return coachId + " - " + name + " [" + speciality + "]";
    }
}
