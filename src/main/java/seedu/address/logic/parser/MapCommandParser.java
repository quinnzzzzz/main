package seedu.address.logic.parser;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.MapCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class MapCommandParser implements Parser<MapCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MapCommand
     * and returns an MapCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MapCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_YEAR, PREFIX_RACE, PREFIX_MEDICAL);

        if (noPrefixes(argMultimap) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MapCommand.MESSAGE_USAGE));
        }

        return new MapCommand();
    }

    /**
     * Returns true if the argMultimap contains any valid prefixes.1
     */
    private static boolean noPrefixes(ArgumentMultimap argMultimap){
        if(argMultimap.getAllValues(PREFIX_YEAR).isEmpty() && argMultimap.getAllValues(PREFIX_RACE).isEmpty()
                && argMultimap.getAllValues(PREFIX_MEDICAL).isEmpty()){
            return true;}
        return false;
    }

    private boolean noPrefixes(ArgumentMultimap argMultimap){
        if(argMultimap.getAllValues(PREFIX_YEAR).isEmpty() && argMultimap.getAllValues(PREFIX_RACE).isEmpty()
                && argMultimap.getAllValues(PREFIX_MEDICAL).isEmpty()){
            return true;}
        return false;
    }

}

