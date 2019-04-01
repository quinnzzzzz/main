package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represent Project's completion attribute i.e. whether the Project has Completed
 */
public class Complete {
    public static final String MESSAGE_CONSTRAINTS = "Complete should be 'true' or 'false' only";

    public final String value;

    public Complete(boolean isComplete) {
        this.value = Boolean.toString(isComplete);
    }

    public Complete(String isComplete) {
        requireNonNull(isComplete);
        checkArgument(isValidBoolean(isComplete), MESSAGE_CONSTRAINTS);
        this.value = isComplete;
    }

    /**
     * Returns true if a given string is a valid boolean string.
     * @param test
     */
    public static Boolean isValidBoolean(String test) {
        return test.equals("true") || test.equals("false");
    }

    public boolean isComplete() {
        return this.value.equals("true");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this // short circuit if same object
                || obj instanceof Complete
                && value.equals(((Complete) obj).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
