package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_DIETARY_PREFERENCE;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_EMERGENCY_CONTACT;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_MEDICAL_CONDITION;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_RACE;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_RELIGION;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddVolunteerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.volunteer.Address;
import seedu.address.model.volunteer.Age;
import seedu.address.model.volunteer.DietaryPreference;
import seedu.address.model.volunteer.Email;
import seedu.address.model.volunteer.EmergencyContact;
import seedu.address.model.volunteer.Gender;
import seedu.address.model.volunteer.MedicalCondition;
import seedu.address.model.volunteer.Name;
import seedu.address.model.volunteer.Phone;
import seedu.address.model.volunteer.Race;
import seedu.address.model.volunteer.Religion;
import seedu.address.model.volunteer.Volunteer;


/**
 * Parses input arguments and creates a new AddVolunteerCommand object
 */
public class AddVolunteerCommandParser implements Parser<AddVolunteerCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddVolunteerCommand
     * and returns an AddVolunteerCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddVolunteerCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_AGE, PREFIX_GENDER, PREFIX_RACE, PREFIX_RELIGION,
                PREFIX_PHONE, PREFIX_ADDRESS,
                PREFIX_EMAIL, PREFIX_EMERGENCY_CONTACT, PREFIX_DIETARY_PREFERENCE, PREFIX_MEDICAL_CONDITION,
                PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_AGE, PREFIX_GENDER, PREFIX_RACE, PREFIX_PHONE,
            PREFIX_ADDRESS,
            PREFIX_EMAIL, PREFIX_EMERGENCY_CONTACT)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddVolunteerCommand.MESSAGE_USAGE));
        }
        if (!arePrefixesPresent(argMultimap, PREFIX_RELIGION)
            || !argMultimap.getPreamble().isEmpty()) {
            argMultimap.put(PREFIX_RELIGION, "nil");
        }
        if (!arePrefixesPresent(argMultimap, PREFIX_DIETARY_PREFERENCE)
            || !argMultimap.getPreamble().isEmpty()) {
            argMultimap.put(PREFIX_DIETARY_PREFERENCE, "nil");
        }
        if (!arePrefixesPresent(argMultimap, PREFIX_MEDICAL_CONDITION)
            || !argMultimap.getPreamble().isEmpty()) {
            argMultimap.put(PREFIX_MEDICAL_CONDITION, "nil");
        }

        Name name = ParserUtilVolunteer.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Age age = ParserUtilVolunteer.parseAge(argMultimap.getValue(PREFIX_AGE).get());
        Gender gender = ParserUtilVolunteer.parseGender(argMultimap.getValue(PREFIX_GENDER).get());
        Race race = ParserUtilVolunteer.parseRace(argMultimap.getValue(PREFIX_RACE).get());
        Religion religion = ParserUtilVolunteer.parseReligion(argMultimap.getValue(PREFIX_RELIGION).get());
        Phone phone = ParserUtilVolunteer.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Address address = ParserUtilVolunteer.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Email email = ParserUtilVolunteer.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        EmergencyContact emergencycontact =
            ParserUtilVolunteer.parseEmergencyContact(argMultimap.getValue(PREFIX_EMERGENCY_CONTACT).get());
        MedicalCondition medicalcondition =
            ParserUtilVolunteer.parseMedicalCondition(argMultimap.getValue(PREFIX_MEDICAL_CONDITION).get());
        DietaryPreference dietarypreference =
            ParserUtilVolunteer.parseDietaryPreference(argMultimap.getValue(PREFIX_DIETARY_PREFERENCE).get());
        Set<Tag> tagList =
            ParserUtilVolunteer.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Volunteer volunteer = new Volunteer(name, age, gender, race, religion, phone, address, email, emergencycontact,
            dietarypreference, medicalcondition, tagList);

        return new AddVolunteerCommand(volunteer);
    }

}
