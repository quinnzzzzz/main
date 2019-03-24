package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

/**
 * Junru comment this
 */
public class Date {
    public final String projectDate;

    public Date(String date) {
        requireNonNull(date);
        projectDate = date;
    }
}
