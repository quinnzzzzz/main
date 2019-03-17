package seedu.address.model.volunteer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Race {
    public static final String MESSAGE_CONSTRAINTS =
            "Race should not contain any spaces or numbers";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String race_output;

    /**
     * Constructs a {@code race.
     *
     * @param race A valid race.
     */
    public Race(String race) {
        requireNonNull(race);
        //checkArgument(isValidRace(race), MESSAGE_CONSTRAINTS);
        race_output = race;
    }

    /**
     * Returns true if a given string is a valid race.
     */
    public static boolean isValidRace(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return race_output;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.volunteer.Race // instanceof handles nulls
                && race_output.equals(((seedu.address.model.volunteer.Race) other).race_output)); // state check
    }

    @Override
    public int hashCode() {
        return race_output.hashCode();
    }

}

