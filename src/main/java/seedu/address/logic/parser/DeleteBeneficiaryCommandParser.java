package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteBeneficiaryCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteBeneficiaryCommand object
 */
public class DeleteBeneficiaryCommandParser implements Parser<DeleteBeneficiaryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteBeneficiaryCommand
     * and returns an DeleteBeneficiaryCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteBeneficiaryCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtilBeneficiary.parseIndex(args);
            return new DeleteBeneficiaryCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteBeneficiaryCommand.MESSAGE_USAGE), pe);
        }
    }

}
