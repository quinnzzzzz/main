package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.volunteer.Address;
import seedu.address.model.volunteer.Age;
import seedu.address.model.volunteer.Race;
import seedu.address.model.volunteer.Dietary_preference;
import seedu.address.model.volunteer.Email;
import seedu.address.model.volunteer.Emergency_contact;
import seedu.address.model.volunteer.Medical_condition;
import seedu.address.model.volunteer.Name;
import seedu.address.model.volunteer.Phone;
import seedu.address.model.project.Beneficiary;
import seedu.address.model.project.Date;
import seedu.address.model.project.ProjectTitle;
import seedu.address.model.project.Project;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }
    public static ProjectTitle parseProjectTitle(String projectTitle) throws ParseException {
        requireNonNull(projectTitle);
        return new ProjectTitle(projectTitle);
    }
    public static Date parseDate(String date) throws ParseException {
        requireNonNull(date);
        return new Date();
    }
    public static Beneficiary parseBeneficiary(String beneficiary) throws ParseException {
        requireNonNull(beneficiary);
        return new Beneficiary();
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
    /**
     * Parses a {@code String emergency_contact} into an {@code Emergency_contact}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code emergency_contact} is invalid.
     */
    public static Emergency_contact parseEmergency_contact(String emergency_contact) throws ParseException {
        requireNonNull(emergency_contact);
        String trimmedEmergency_contact = emergency_contact.trim();
        if (!Emergency_contact.isValidEmergency_contact(trimmedEmergency_contact)) {
            throw new ParseException(Emergency_contact.MESSAGE_CONSTRAINTS);
        }
        return new Emergency_contact(trimmedEmergency_contact);
    }
    /**
     * Parses a {@code String medical_condition} into an {@code Medical_condition}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Medical_condition parseMedical_condition(String medical_condition) throws ParseException {
        requireNonNull(medical_condition);
        String trimmedMedical_condition = medical_condition.trim();
        if (!Medical_condition.isValidMedical_condition(trimmedMedical_condition)) {
            throw new ParseException(Medical_condition.MESSAGE_CONSTRAINTS);
        }
        return new Medical_condition(trimmedMedical_condition);
    }
    /**
     * Parses a {@code String dietary_preference} into an {@code Dietary_preference}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Dietary_preference parseDietary_preference(String dietary_preference) throws ParseException {
        requireNonNull(dietary_preference);
        String trimmedDietary_preference = dietary_preference.trim();
        if (!Dietary_preference.isValidDietary_preference(trimmedDietary_preference)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Dietary_preference(trimmedDietary_preference);
    }
    public static Age parseAge(String age) throws ParseException {
        requireNonNull(age);
        String trimmedAge = age.trim();
        if (!Age.isValidAge(trimmedAge)) {
            throw new ParseException(Age.MESSAGE_CONSTRAINTS);
        }
        return new Age(trimmedAge);
    }
    public static Race parseRace(String race) throws ParseException {
        requireNonNull(race);
        String trimmedRace = race.trim();
        if (!Race.isValidRace(trimmedRace)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Race(trimmedRace);
    }
}