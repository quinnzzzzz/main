package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * This is the Date for the project
 * format is yyyymmdd
 */
public class ProjectDate {
    public static final String MESSAGE_CONSTRAINTS =
            "Dates should only contain numbers in yyyymmdd format, and it should not be blank.";
    public static final String VALIDATION_REGEX = "\\d{8,}";
    public final String fullDate;

    /**
     * Constructs a {@code ProjectDate}.
     *
     * @param projectDate A valid name.
     */
    public ProjectDate(String projectDate) {
        requireNonNull(projectDate);
        checkArgument(isValidDate(projectDate), MESSAGE_CONSTRAINTS);
        this.fullDate = projectDate;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "Date: " + fullDate;
    }
}
