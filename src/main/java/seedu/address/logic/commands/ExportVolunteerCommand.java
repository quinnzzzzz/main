package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntaxProject.PREFIX_MEDICAL;
import static seedu.address.logic.parser.CliSyntaxProject.PREFIX_YEAR;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_RACE;

public class ExportVolunteerCommand extends Command {
    public static final String COMMAND_WORD = "exportv";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "Exports list of volunteers into a CSV file. "
            + "Parameters: "
            + "Number of volunteers wanted + "
            + "any number of valid volunteer prefixes e.g.(n/ y/ g/)\n"
            + "Example: " + COMMAND_WORD + " 10 "
            + "n/ y/ g/";




    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        return null;
    }
}
