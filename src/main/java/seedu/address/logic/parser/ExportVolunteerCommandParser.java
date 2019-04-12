//@@author articstranger
package seedu.address.logic.parser;


import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.ExportVolunteerCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntaxVolunteer.*;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_TAG;

/**
 * Parses input arguments and creates a new ExportCommand object
 */
public class ExportCommandParser implements Parser<ExportVolunteerCommandCommand> {

    /**
     * Returns true if the argMultimap contains any valid prefixes.
     */
    private static boolean noPrefixes(ArgumentMultimap argMultimap) {
        if (argMultimap.getAllValues(PREFIX_YEAR).isEmpty() && argMultimap.getAllValues(PREFIX_RACE).isEmpty()
                && argMultimap.getAllValues(PREFIX_MEDICAL).isEmpty()) {
            return true;
        }
        return false;
    }
    public ExportVolunteerCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_AGE, PREFIX_GENDER, PREFIX_RACE, PREFIX_RELIGION,
                PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_EMERGENCY_CONTACT, PREFIX_DIETARY_PREFERENCE,
                PREFIX_MEDICAL_CONDITION, PREFIX_TAG);

        if (noPrefixes(argMultimap) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportVolunteerCommand.MESSAGE_USAGE));
        }

        return new ExportVolunteerCommand();
    }



}
