package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntaxVolunteer.*;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AssignVolunteerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.ProjectTitle;

/**
 * Parses input arguments and creates a new AssignVolunteerCommand object
 */
public class AssignVolunteerCommandParser implements Parser<AssignVolunteerCommand> {

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AssignVolunteerCommand
     * and returns an AssignVolunteerCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AssignVolunteerCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, ASSIGNED_PROJECT_TITLE, PREFIX_REQUIRED_VOLUNTEER);

        if (!arePrefixesPresent(argMultimap, ASSIGNED_PROJECT_TITLE, PREFIX_REQUIRED_VOLUNTEER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AssignVolunteerCommand.MESSAGE_USAGE));
        }
        ProjectTitle projectTitle = ParserUtilProject.parseProjectTitle(argMultimap
                .getValue(ASSIGNED_PROJECT_TITLE).get());
        if (!argMultimap.getValue(PREFIX_REQUIRED_VOLUNTEER).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignVolunteerCommand.MESSAGE_USAGE));
        }
        Index indexTo = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_REQUIRED_VOLUNTEER).get());
        Integer requiredVolunteers = indexTo.getOneBased();
        return new AssignVolunteerCommand(projectTitle,requiredVolunteers);
    }
}