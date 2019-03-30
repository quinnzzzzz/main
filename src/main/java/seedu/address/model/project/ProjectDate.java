package seedu.address.model.project;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * This is the Date for the project
 * format is yyyymmdd
 *
 */
public class ProjectDate {
    public static final String MESSAGE_PROJECT_DATE_CONSTRAINTS =
            "Dates should only contain numbers in yyyymmdd format, and it should not be blank";
    
    public final String fullDate;

    /**
     * Constructs a {@code ProjectDate}.
     *
     * @param projectDate A valid name.
     */
    public ProjectDate(String projectDate) {
        requireNonNull(projectDate);
        this.fullDate = projectDate;
    }
    @Override
    public String toString() {
        return "Date: "+ fullDate;
    }
}
