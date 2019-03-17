package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * race
 */
public class Race {
    //field
    public static final String MESSAGE_CONSTRAINTS =
            "Age should be comprised of only positive numbers";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    //public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*"; **Edit again**

    public final String raceOutput;

    /**
     * Constructs a race.
     *
     * @param race A valid age.
     */
    public Race(String race) {
        requireNonNull(race);
        //checkArgument(isValidRace(race), MESSAGE_CONSTRAINTS);
        raceOutput = race;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    /*public static boolean isValidRace(String test) {
        return test.matches(VALIDATION_REGEX);*/
}
