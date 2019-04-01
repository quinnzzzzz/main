package seedu.address.model.project;


import static java.util.Objects.requireNonNull;

public class Date {
    public final String projectDate;

    public Date(String date) {
        requireNonNull(date);
        projectDate = date;
    }
}
