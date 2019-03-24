package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_DIETARY_PREFERENCE;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_EMERGENCY_CONTACT;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_MEDICAL_CONDITION;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_RACE;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddVolunteerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.volunteer.Volunteer;
import seedu.address.model.volunteer.Name;
import seedu.address.model.volunteer.Age;
import seedu.address.model.volunteer.Race;
import seedu.address.model.volunteer.Phone;
import seedu.address.model.volunteer.Address;
import seedu.address.model.volunteer.Email;
import seedu.address.model.volunteer.EmergencyContact;
import seedu.address.model.volunteer.DietaryPreference;
import seedu.address.model.volunteer.MedicalCondition;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddVolunteerCommand object
 */
public class AddVolunteerCommandParser implements Parser<AddVolunteerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddVolunteerCommand
     * and returns an AddVolunteerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddVolunteerCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddVolunteerCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtilVolunteer.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Age age = ParserUtilVolunteer.parseAge(argMultimap.getValue(PREFIX_AGE).get());
        Race race = ParserUtilVolunteer.parseRace(argMultimap.getValue(PREFIX_RACE).get());
        Phone phone = ParserUtilVolunteer.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Address address = ParserUtilVolunteer.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Email email = ParserUtilVolunteer.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        EmergencyContact emergency_contact = ParserUtilVolunteer.parseEmergencyContact(argMultimap.getValue(PREFIX_EMERGENCY_CONTACT).get());
        MedicalCondition medicalcondition = ParserUtilVolunteer.parseMedicalCondition(argMultimap.getValue(PREFIX_MEDICAL_CONDITION).get());
        DietaryPreference dietary_preference = ParserUtilVolunteer.parseDietaryPreference(argMultimap.getValue(PREFIX_DIETARY_PREFERENCE).get());
        Set<Tag> tagList = ParserUtilVolunteer.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Volunteer volunteer = new Volunteer(name, age, race, phone, address, email, emergency_contact, dietary_preference, medicalcondition, tagList);

        return new AddVolunteerCommand(volunteer);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
