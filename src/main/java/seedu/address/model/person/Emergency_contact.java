package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Emergency_contact {


        public static final String MESSAGE_CONSTRAINTS =
                "Phone numbers should only contain numbers, and it should be at least 3 digits long";
        public static final String VALIDATION_REGEX = "\\d{3,}";
        public final String value;

        /**
         * Constructs a {@code Phone}.
         *
         * @param emergency_contact A valid phone number.
         */
        public Emergency_contact(String emergency_contact) {
            requireNonNull(emergency_contact);
            checkArgument(isValidEmergency_contact(emergency_contact), MESSAGE_CONSTRAINTS);
            value = emergency_contact;
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
            return other == this // short circuit if same object
                    || (other instanceof seedu.address.model.person.Emergency_contact // instanceof handles nulls
                    && value.equals(((seedu.address.model.person.Emergency_contact) other).value)); // state check
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }

}
