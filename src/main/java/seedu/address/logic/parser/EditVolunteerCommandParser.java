package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
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

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;

import seedu.address.logic.commands.EditVolunteerCommand;
import seedu.address.logic.commands.EditVolunteerCommand.EditVolunteerDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditVolunteerCommand object
 */
public class EditVolunteerCommandParser implements Parser<EditVolunteerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditVolunteerCommand
     * and returns an EditVolunteerCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditVolunteerCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_AGE, PREFIX_GENDER, PREFIX_RACE, PREFIX_RELIGION,
                        PREFIX_PHONE, PREFIX_ADDRESS,
                        PREFIX_EMAIL, PREFIX_EMERGENCY_CONTACT, PREFIX_DIETARY_PREFERENCE, PREFIX_MEDICAL_CONDITION,
                        PREFIX_TAG);

        Index index;

        try {
            index = ParserUtilVolunteer.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format
                    (MESSAGE_INVALID_COMMAND_FORMAT, EditVolunteerCommand.MESSAGE_USAGE), pe);
        }

        EditVolunteerDescriptor editVolunteerDescriptor = new EditVolunteerDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editVolunteerDescriptor.setName(
                    ParserUtilVolunteer.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_AGE).isPresent()) {
            editVolunteerDescriptor.setAge
                    (ParserUtilVolunteer.parseAge(argMultimap.getValue(PREFIX_AGE).get()));
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            editVolunteerDescriptor.setGender
                    (ParserUtilVolunteer.parseGender(argMultimap.getValue(PREFIX_GENDER).get()));
        }
        if (argMultimap.getValue(PREFIX_RACE).isPresent()) {
            editVolunteerDescriptor.setRace
                    (ParserUtilVolunteer.parseRace(argMultimap.getValue(PREFIX_RACE).get()));
        }
        if (argMultimap.getValue(PREFIX_RELIGION).isPresent()) {
            editVolunteerDescriptor.setReligion
                    (ParserUtilVolunteer.parseReligion(argMultimap.getValue(PREFIX_RELIGION).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editVolunteerDescriptor.setPhone(
                    ParserUtilVolunteer.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editVolunteerDescriptor.setAddress
                    (ParserUtilVolunteer.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editVolunteerDescriptor.setEmail(
                    ParserUtilVolunteer.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }

        if (argMultimap.getValue(PREFIX_EMERGENCY_CONTACT).isPresent()) {
            editVolunteerDescriptor.setEmergencyContact
                    (ParserUtilVolunteer.parseEmergencyContact(argMultimap.getValue(PREFIX_EMERGENCY_CONTACT).get()));
        }
        if (argMultimap.getValue(PREFIX_DIETARY_PREFERENCE).isPresent()) {
            editVolunteerDescriptor.setDietaryPreference
                    (ParserUtilVolunteer.parseDietaryPreference(argMultimap.getValue(PREFIX_DIETARY_PREFERENCE).get()));
        }
        if (argMultimap.getValue(PREFIX_MEDICAL_CONDITION).isPresent()) {
            editVolunteerDescriptor.setMedicalCondition
                    (ParserUtilVolunteer.parseMedicalCondition(argMultimap.getValue(PREFIX_MEDICAL_CONDITION).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editVolunteerDescriptor.setAddress(
                    ParserUtilVolunteer.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }

        return new EditVolunteerCommand(index, editVolunteerDescriptor);
    }
}
