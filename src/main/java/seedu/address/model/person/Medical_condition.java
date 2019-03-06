package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Medical_condition {
    public static final String MESSAGE_CONSTRAINTS =
            "Medical Conditions should not contain numbers";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";;
    public final String status;

    /**
     * Constructs a {@code Medical_condition}.
     *
     */
    public Medical_condition(String medical_condition) {
        requireNonNull(medical_condition);
        checkArgument(isValidMedical_condition(medical_condition), MESSAGE_CONSTRAINTS);
        status = medical_condition;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidMedical_condition(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return status;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.person.Medical_condition // instanceof handles nulls
                && status.equals(((seedu.address.model.person.Medical_condition) other).status)); // state check
    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }
}
