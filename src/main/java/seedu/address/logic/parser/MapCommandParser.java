//@@author articstranger
package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_AGE_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_AGE;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_MEDICAL_CONDITION;
import static seedu.address.logic.parser.volunteer.CliSyntaxVolunteer.PREFIX_RACE;
import static seedu.address.logic.parser.ParserUtil.isValidInt;

import javafx.util.Pair;
import seedu.address.logic.commands.MapCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.MapObject;

/**
 * Parses input arguments and creates a new MapCommand object
 */
public class MapCommandParser implements Parser<MapCommand> {

    /**
     * Returns true if the argMultimap contains any valid prefixes.
     */
    private static boolean noPrefixes(ArgumentMultimap argMultimap) {
        return argMultimap.getAllValues(PREFIX_AGE).isEmpty() && argMultimap.getAllValues(PREFIX_RACE).isEmpty()
            && argMultimap.getAllValues(PREFIX_MEDICAL_CONDITION).isEmpty();
    }

    /**
     * Parses the given {@code String} of arguments in the context of the MapCommand
     * and returns an MapCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public MapCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_AGE, PREFIX_RACE, PREFIX_MEDICAL_CONDITION);

        if (noPrefixes(argMultimap) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MapCommand.MESSAGE_USAGE));
        }
        MapObject newMap = parseCriteria(argMultimap);
        if (newMap == null) {
            throw new ParseException(String.format(MESSAGE_INVALID_AGE_FORMAT, MapCommand.MESSAGE_USAGE));
        }
        return new MapCommand(newMap);
    }

    /**
     * Splits tokens into proper data for MapCommand() to handle
     */
    private MapObject parseCriteria(ArgumentMultimap argMultimap) {
        int prefixPoints;
        String prefixCriteria;
        String yearOperator = null;
        String criteriaHolder;

        Pair<Integer, Integer> localAgePair = new Pair<>(0, 0);
        Pair<Integer, String> localRacePair = new Pair<>(0, "");
        Pair<Integer, String> localMedicalPair = new Pair<>(0, "");


        if (!argMultimap.getAllValues(PREFIX_AGE).isEmpty()) {
            criteriaHolder = argMultimap.getValue(PREFIX_AGE).get();
            prefixPoints = isValidInt(criteriaHolder.substring(0, 1))
                ? Integer.parseInt(criteriaHolder.substring(0, 1)) : -1;
            if (prefixPoints == -1) {
                return null;
            }
            yearOperator = criteriaHolder.substring(1, 2);
            prefixCriteria = criteriaHolder.substring(2);
            if (notValidAgePair(yearOperator, prefixCriteria)) {
                return null;
            }
            localAgePair = new Pair<>(prefixPoints, Integer.parseInt(prefixCriteria));
        } else {
            localAgePair = new Pair<>(0, 0);
        }

        if (!argMultimap.getAllValues(PREFIX_RACE).isEmpty()) {
            criteriaHolder = argMultimap.getValue(PREFIX_RACE).get();
            prefixPoints = Integer.parseInt(criteriaHolder.substring(0, 1));
            prefixCriteria = criteriaHolder.substring(1);
            localRacePair = new Pair<>(prefixPoints, prefixCriteria);
        } else {
            localRacePair = new Pair<>(0, "");
        }

        if (!argMultimap.getAllValues(PREFIX_MEDICAL_CONDITION).isEmpty()) {
            criteriaHolder = argMultimap.getValue(PREFIX_MEDICAL_CONDITION).get();
            prefixPoints = Integer.parseInt(criteriaHolder.substring(0, 1));
            prefixCriteria = criteriaHolder.substring(1);
            localMedicalPair = new Pair<>(prefixPoints, prefixCriteria);
        } else {
            localMedicalPair = new Pair<>(0, "");
        }

        MapObject newMap = new MapObject(localAgePair, yearOperator, localRacePair, localMedicalPair);
        return newMap;
    }


    /**
     * checks the age pair to see if the comparator and year is valid
     */
    public boolean notValidAgePair(String comparator, String year) {

        if (comparator.contains("<") || comparator.contains(">") || comparator.contains("=")) {
            return !isValidInt(year);
        }
        return true;

    }


}

