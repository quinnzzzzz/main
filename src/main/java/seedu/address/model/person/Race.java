package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Race {
    public static final String MESSAGE_CONSTRAINTS =
            "Age should be comprised of only positive numbers";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    //public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*"; **Edit again**

    public final String race_output;

    /**
     * Constructs a {@code race.
     *
     * @param race A valid age.
     */
    public Race(String race) {
        requireNonNull(race);
        //checkArgument(isValidRace(race), MESSAGE_CONSTRAINTS);
        race_output = race;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    /*public static boolean isValidRace(String test) {
        return test.matches(VALIDATION_REGEX);*/
}
