package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Age {

        public static final String MESSAGE_CONSTRAINTS =
                "Age should be comprised of only positive numbers";

        /*
         * The first character of the address must not be a whitespace,
         * otherwise " " (a blank string) becomes a valid input.
         */
        public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

        public final String age_output;

        /**
         * Constructs a {@code Age}.
         *
         * @param age A valid age.
         */
        public Age(String age) {
            requireNonNull(age);
            checkArgument(isValidAge(age), MESSAGE_CONSTRAINTS);
            age_output = age;
        }

        /**
         * Returns true if a given string is a valid name.
         */
        public static boolean isValidAge(String test) {
            return test.matches(VALIDATION_REGEX);
        }


        @Override
        public String toString() {
            return age_output;
        }

        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                    || (other instanceof seedu.address.model.person.Age // instanceof handles nulls
                    && age_output.equals(((seedu.address.model.person.Age) other).age_output)); // state check
        }

        @Override
        public int hashCode() {
            return age_output.hashCode();
        }

}
