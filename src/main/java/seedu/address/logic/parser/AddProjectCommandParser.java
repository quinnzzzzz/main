package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntaxProject.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntaxProject.PREFIX_BENEFICIARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntaxProject.PREFIX_PROJECT_TITLE;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddProjectCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.Beneficiary;
import seedu.address.model.project.Date;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectTitle;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddProjectCommand object
 */
public class AddProjectCommandParser implements Parser<AddProjectCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddProjectCommand
     * and returns an AddProjectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddProjectCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PROJECT_TITLE, PREFIX_DATE, PREFIX_BENEFICIARY, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_PROJECT_TITLE, PREFIX_DATE, PREFIX_BENEFICIARY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProjectCommand.MESSAGE_USAGE));
        }

        ProjectTitle projectTitle = ParserUtilProject.parseProjectTitle(argMultimap
                .getValue(PREFIX_PROJECT_TITLE).get());
        Date date = ParserUtilProject.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        Beneficiary beneficiary = ParserUtilProject.parseBeneficiary(argMultimap.getValue(PREFIX_BENEFICIARY).get());
        Set<Tag> tagList = ParserUtilProject.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Project Project = new Project(projectTitle, date, beneficiary, tagList);

        return new AddProjectCommand(Project);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
