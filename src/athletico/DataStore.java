package athletico;

/**
 * Simple in-memory data store for coaches (acts like a tiny DB).
 */
public class DataStore {

    private static final Coach[] coaches = {
        new Coach("C101", "Ravi", "Cricket"),
        new Coach("C102", "Anita", "Badminton"),
        new Coach("C103", "Suresh", "Football"),
        new Coach("C104", "Meena", "Tennis"),
        new Coach("C105", "Arjun", "Athletics")
    };

    /**
     * Returns the first coach whose speciality matches the requested sport (case-insensitive).
     * Returns null if none found.
     */
    public static Coach getCoachBySport(String sport) {
        if (sport == null) return null;
        for (Coach c : coaches) {
            if (c.getSpeciality().equalsIgnoreCase(sport.trim())) {
                return c;
            }
        }
        return null;
    }

    /**
     * Optional: list available coaches (useful for debugging or menu).
     */
    public static Coach[] getAllCoaches() {
        return coaches.clone();
    }
}
