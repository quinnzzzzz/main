//@@author articstranger
package seedu.address.logic.parser;


import javafx.util.Pair;
import seedu.address.logic.commands.ExportVolunteerCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.ArrayList;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntaxVolunteer.*;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_TAG;
import static seedu.address.logic.parser.ParserUtil.isValidInt;
/**
 * Parses input arguments and creates a new ExportCommand object
 */
public class ExportVolunteerCommandParser implements Parser<ExportVolunteerCommand> {

    /**
     * Returns true if the argMultimap contains any valid prefixes.
     */
    private static boolean hasPrefixes(ArgumentMultimap argMultimap) {
        if (!(argMultimap.getAllValues(PREFIX_NAME).isEmpty()) || !(argMultimap.getAllValues(PREFIX_AGE).isEmpty())
                || !(argMultimap.getAllValues(PREFIX_GENDER).isEmpty())
                || !(argMultimap.getAllValues(PREFIX_RACE).isEmpty())
                || !(argMultimap.getAllValues(PREFIX_RELIGION).isEmpty())
                || !(argMultimap.getAllValues(PREFIX_PHONE).isEmpty())
                || !(argMultimap.getAllValues(PREFIX_ADDRESS).isEmpty())
                || !(argMultimap.getAllValues(PREFIX_EMAIL).isEmpty())
                || !(argMultimap.getAllValues(PREFIX_EMERGENCY_CONTACT).isEmpty())
                || !(argMultimap.getAllValues(PREFIX_DIETARY_PREFERENCE).isEmpty())
                || !(argMultimap.getAllValues(PREFIX_MEDICAL_CONDITION).isEmpty())
                || !(argMultimap.getAllValues(PREFIX_TAG).isEmpty())) {
            return true;
        }
        return false;
    }

    public ExportVolunteerCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_AGE, PREFIX_GENDER, PREFIX_RACE, PREFIX_RELIGION,
                PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_EMERGENCY_CONTACT, PREFIX_DIETARY_PREFERENCE,
                PREFIX_MEDICAL_CONDITION, PREFIX_TAG);

        if (!hasPrefixes(argMultimap) || argMultimap.getPreamble().isEmpty()
                || !isValidInt(argMultimap.getPreamble())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportVolunteerCommand.MESSAGE_USAGE));
        }

        if (!isValidInt(argMultimap.getPreamble())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportVolunteerCommand.MESSAGE_USAGE));
        }

        ArrayList<String> prefixes = argMultimap.getPrefixes();
        Pair<Integer, ArrayList<String>> numberAndprefixes = new Pair<>(Integer.parseInt(argMultimap.getPreamble()),
                prefixes);
        return new ExportVolunteerCommand(numberAndprefixes);
    }

}
