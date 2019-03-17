package seedu.address.model.project;


import static java.util.Objects.requireNonNull;

public class Date {
    public final String fullDate;

    public Date(String projectDate) {
        requireNonNull(projectDate);
        fullDate = projectDate;
    }

}