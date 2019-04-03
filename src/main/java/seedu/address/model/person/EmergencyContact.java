package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Emergency Contact
 */
public class EmergencyContact {
    //field
    public static final String MESSAGE_CONSTRAINTS =
        "Phone numbers should only contain numbers, and it should be at least 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param emergencyContact A valid phone number.
     */
    public EmergencyContact(String emergencyContact) {
        requireNonNull(emergencyContact);
        checkArgument(isValidEmergency_contact(emergencyContact), MESSAGE_CONSTRAINTS);
        value = emergencyContact;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidEmergency_contact(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other==this // short circuit if same object
            || (other instanceof EmergencyContact // instanceof handles nulls
            && value.equals(((EmergencyContact) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
