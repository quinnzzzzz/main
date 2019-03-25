package seedu.address.logic.commands;


/**
 * Sorts the current list of volunteers and returns a list of volunteers with the
 * highest points, up to the number specified.
 */
public class SortCommand {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts and presents a list of volunteers who best fulfill "
            + "the criteria given in the map command, up to the number specified in this command.\n"
            + "Parameters: MAX_NUMBER\n"
            + "Example: sort 10";




}
