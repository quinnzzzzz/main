package seedu.address.model.project;


import static java.util.Objects.requireNonNull;

/**
 * This is the Date for the project
 * format is yyyymmdd
 *
 */
public class ProjectDate {
    public final String fullDate;

    public ProjectDate(String projectDate) {
        requireNonNull(projectDate);
        fullDate = projectDate;
    }
}
