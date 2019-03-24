package seedu.address.model.volunteer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class DietaryPreference {

    public static final String MESSAGE_CONSTRAINTS =
            "Dietary Preference should not contain numbers";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";;
    public final String restriction;

    /**
     * Constructs a {@code DietaryPreference}.
     *
     */
    public DietaryPreference(String dietary_preference) {
        requireNonNull(dietary_preference);
        checkArgument(isValidDietaryPreference(dietary_preference), MESSAGE_CONSTRAINTS);
        restriction = dietary_preference;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidDietaryPreference(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return restriction;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.volunteer.DietaryPreference
                // instanceof handles nulls
                && restriction.equals(((seedu.address.model.volunteer.DietaryPreference) other).restriction));
                // state check
    }

    @Override
    public int hashCode() {
        return restriction.hashCode();
    }
}
