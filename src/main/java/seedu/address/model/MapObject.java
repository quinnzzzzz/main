package seedu.address.model;

import javafx.util.Pair;

/**
 * Represents a MapCommand.
 * Guarantees: at least one criteria exists for mapping.
 */
public class MapObject {

    //pair field for points ranking and criteria
    private Pair<Integer, Integer> agePair = new Pair<>(0, 0);
    private Pair<Integer, String> racePair = new Pair<>(0, "");
    private Pair<Integer, String> medicalPair = new Pair<>(0, "");
    private String comparator;

    //empty pairs for nil criterion
    private final Pair<Integer, Integer> nullAgePair = new Pair<>(0, 0);
    private final Pair<Integer, String> nullRacePair = new Pair<>(0, "");
    private final Pair<Integer, String> nullMedicalPair = new Pair<>(0, "");
    private final String nullComparator = "";

    /**
     * at least one field must be present and not null.
     */
    public MapObject(Pair<Integer, Integer> agePair, String comparator,
                     Pair<Integer, String> racePair, Pair<Integer, String> medicalPair) {
        this.agePair = (agePair.getKey() != 0) ? agePair : nullAgePair;
        this.comparator = (agePair.getKey() != 0) ? comparator : nullComparator;
        this.racePair = (racePair.getKey() != 0) ? racePair : nullRacePair;
        this.medicalPair = (medicalPair.getKey() != 0) ? medicalPair : nullMedicalPair;
    }

    public String getComparator() {
        return comparator;
    }

    public Pair<Integer, Integer> getAgePair() {
        return agePair;
    }

    public Pair<Integer, String> getRacePair() {
        return racePair;
    }

    public Pair<Integer, String> getMedicalPair() {
        return medicalPair;
    }

}
