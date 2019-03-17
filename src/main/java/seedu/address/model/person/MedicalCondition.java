package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/*
 * Medical Condition of volunteer
 */
public class MedicalCondition {
    //field
    public static final String MESSAGE_CONSTRAINTS =
            "Medical Conditions should not contain numbers";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String status;

    /**
     * Constructs a {@code MedicalCondition}.
     *
     */
    public MedicalCondition(String medicalCondition) {
        requireNonNull(medicalCondition);
        checkArgument(isValidMedicalCondition(medicalCondition), MESSAGE_CONSTRAINTS);
        status = medicalCondition;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidMedicalCondition(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return status;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MedicalCondition // instanceof handles nulls
                && status.equals(((MedicalCondition) other).status)); // state check
    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }
}
