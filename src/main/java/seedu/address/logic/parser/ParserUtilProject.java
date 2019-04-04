package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.ProjectDate;
import seedu.address.model.project.ProjectTitle;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtilProject extends ParserUtil {

    public static final String UNSPECIFIED_FIELD = "<UNSPECIFIED>";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws IllegalValueException if the specified index is invalid (not non-zero unsigned integer).
     */
    /**
     * parse project date
     */
    public static ProjectDate parseProjectDate(String projectDate) throws ParseException {
        requireNonNull(projectDate);
        return new ProjectDate(projectDate);
    }
    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws IllegalValueException if the specified index is invalid (not non-zero unsigned integer).
     */
    /**
     * parse project title
     */
    public static ProjectTitle parseProjectTitle(String projectTitle) throws ParseException {
        requireNonNull(projectTitle);
        String trimmedProjectTitle = projectTitle.trim();
        if (!ProjectTitle.isValidName(trimmedProjectTitle)) {
            throw new ParseException(ProjectTitle.MESSAGE_PROJECT_TITLE_CONSTRAINTS);
        }
        return new ProjectTitle(trimmedProjectTitle);
    }
    /**
     * Parses {@code String oneBasedIndexes} into a {@code List<Index>} and returns it. Leading and trailing
     * whitespaces will be trimmed.
     */
    public static List<Index> parseIndexes(String oneBasedIndexes) throws ParseException {
        String trimmedIndexes = oneBasedIndexes.trim();

        String[] splitOneBasedIndexes = trimmedIndexes.split("\\s+");

        Set<String> uniqueIndexes = new HashSet<>(Arrays.asList(splitOneBasedIndexes));

        List<Index> indexList = new ArrayList<>();

        for (String index : uniqueIndexes) {
            indexList.add(parseIndex(index));
        }
        return indexList;
    }

    /**
     * Parses a {@code Optional<String> value} into the specified value or {@code UNSPECIFIED_FIELD} if is empty
     */
    public static Optional<String> parseValue(Optional<String> value, String messageConstraints)
        throws IllegalValueException {
        if (value.isPresent() && value.get().equals(UNSPECIFIED_FIELD)) {
            throw new IllegalValueException(messageConstraints);
        } else {
            return Optional.of(value.orElse(UNSPECIFIED_FIELD));
        }
    }
}
