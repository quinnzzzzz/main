//@@author quinnzzzzz
package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * This is the Date for the project
 * format is DD/MM/YYYY
 */
public class ProjectDate {
    public static final String MESSAGE_CONSTRAINTS =
        "Dates should only contain numbers in DD/MM/YYYY format and should be greater than the current date, "
                + "and it should not be blank.";
    public static final String VALIDATION_REGEX = "[0-9]{2}/[0-9]{2}/[0-9]{4}";

    private static final int DAY_INDEX = 0;
    private static final int MONTH_INDEX = 1;
    private static final int YEAR_INDEX = 2;
    private static String[] dateFormats;

    public final String fullDate;

    private int day;
    private int month;
    private int year;

    /**
     * Constructs a {@code ProjectDate}.
     *
     * @param projectDate A valid date.
     */
    public ProjectDate(String projectDate) {
        requireNonNull(projectDate);
        checkArgument(isValidDate(projectDate), MESSAGE_CONSTRAINTS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        this.fullDate = stringToDate(projectDate).format(formatter);
        setDateFormats(projectDate);
    }

    /**
     * Returns true if a given string is a valid date.
     * Checks if date is after current day
     */
    public static boolean isValidDate(String test) {
        if (isEmptyDate(test) || !test.matches(VALIDATION_REGEX) || !hasDateMonthYear(test)) {
            return false;
        }
        try {
            LocalDate inputDate = stringToDate(test);
            LocalDate current = LocalDate.now();
            LocalDate nextDay = current.plus(1, ChronoUnit.DAYS);
            return inputDate.isAfter(nextDay) || inputDate.isEqual(nextDay);
        } catch (DateTimeException dte) {
            return false;
        }
    }

    /**
     * Returns true if the given string is empty
     */
    public static boolean isEmptyDate(String str) {
        return str.trim().isEmpty();
    }

    /**
     * Returns true if the given string has Day, Month and Year
     */
    public static boolean hasDateMonthYear(String date) {
        String[] dateFormats = date.split("/");
        return dateFormats.length == 3;
    }

    public void setDateFormats(String date) {
        this.dateFormats = date.split("/");
        this.day = Integer.parseInt(dateFormats[DAY_INDEX]);
        this.month = Integer.parseInt(dateFormats[MONTH_INDEX]);
        this.year = Integer.parseInt(dateFormats[YEAR_INDEX]);
    }

    /**
     * Returns {@code LocalDate} from given {@code String} date
     */
    private static LocalDate stringToDate(String projectDate) throws DateTimeException {
        String[] dateFormats = projectDate.split("/");
        int testDay = Integer.parseInt(dateFormats[DAY_INDEX]);
        int testMonth = Integer.parseInt(dateFormats[MONTH_INDEX]);
        int testYear = Integer.parseInt(dateFormats[YEAR_INDEX]);
        return LocalDate.of(testYear, testMonth, testDay);
    }

    public int getDay() {
        return this.day;
    }
    public int getMonth() {
        return this.month;
    }
    public int getYear() {
        return this.year;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(this.day)
                .append("/")
                .append(this.month)
                .append("/")
                .append(this.year);
        return "Date: " + builder.toString();
    }
}
