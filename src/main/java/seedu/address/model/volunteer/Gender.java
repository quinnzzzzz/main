package seedu.address.model.volunteer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Volunteer's name in the address book.
 * Guarantees: mutable; is valid as declared in {@link #isValidGender(String)}
 */

public class Gender {

    public static final String MESSAGE_CONSTRAINTS =
        "Gender should be only M or F";

    /*
     * the gender parameter must be m or f
     */
    public static final String VALIDATION_REGEX = "\\w";

    public final String genderOutput;

    /**
     * Gender constructor
     * @param gender
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        genderOutput = gender;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidGender(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return genderOutput;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof seedu.address.model.volunteer.Gender // instanceof handles nulls
            && genderOutput.equals(((seedu.address.model.volunteer.Gender) other).genderOutput)); // state check
    }

    @Override
    public int hashCode() {
        return genderOutput.hashCode();
    }

}
