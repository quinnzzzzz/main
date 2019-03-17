package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;


import seedu.address.model.volunteer.Volunteer;
import seedu.address.model.volunteer.Name;
import seedu.address.model.volunteer.Age;
import seedu.address.model.volunteer.Race;
import seedu.address.model.volunteer.Phone;
import seedu.address.model.volunteer.Address;
import seedu.address.model.volunteer.Email;
import seedu.address.model.volunteer.Emergency_contact;
import seedu.address.model.volunteer.Dietary_preference;
import seedu.address.model.volunteer.Medical_condition;



import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Volunteer}.
 */
class JsonAdaptedVolunteer {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Volunteer's %s field is missing!";

    private final String name;
    private final String age;
    private final String race;
    private final String phone;
    private final String address;
    private final String email;
    private final String emergency_contact;
    private final String dietary_preference;
    private final String medical_condition;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedVolunteer} with the given volunteer details.
     */
    @JsonCreator
    public JsonAdaptedVolunteer(@JsonProperty("name") String name,  @JsonProperty("Age") String age, @JsonProperty("Race") String race, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,  @JsonProperty("Dietary_preference") String dietary_preference,  @JsonProperty("Emergency_contact") String emergency_contact, @JsonProperty("Medical_condition") String medical_condition,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.age = age;
        this.race = race;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.emergency_contact = emergency_contact;
        this.dietary_preference = dietary_preference;
        this.medical_condition = medical_condition;

        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Volunteer} into this class for Jackson use.
     */
    public JsonAdaptedVolunteer(Volunteer source) {
        name = source.getName().fullName;
        age = source.getAge().age_output;
        race = source.getRace().race_output;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        emergency_contact = source.getEmergency_contact().value;
        dietary_preference = source.getDietary_preference().restriction;
        medical_condition = source.getMedical_condition().status;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted volunteer object into the model's {@code Volunteer} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted volunteer.
     */
    public Volunteer toModelType() throws IllegalValueException {
        final List<Tag> volunteerTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            volunteerTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (age == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Age.class.getSimpleName()));
        }
        if (!Age.isValidAge(age)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Age modelAge = new Age(age);

        if (race == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Race.class.getSimpleName()));
        }
        if (!Race.isValidRace(race)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Race modelRace = new Race(race);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (emergency_contact == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Emergency_contact.class.getSimpleName()));
        }
        if (!Emergency_contact.isValidEmergency_contact(emergency_contact)) {
            throw new IllegalValueException(Emergency_contact.MESSAGE_CONSTRAINTS);
        }
        final Emergency_contact modelEmergency_contact = new Emergency_contact(emergency_contact);

        if (dietary_preference == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Dietary_preference.class.getSimpleName()));
        }
        if (!Dietary_preference.isValidDietary_preference(dietary_preference)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Dietary_preference modelDietary_preference = new Dietary_preference(dietary_preference);

        if (medical_condition == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Medical_condition.class.getSimpleName()));
        }
        if (!Medical_condition.isValidMedical_condition(medical_condition)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Medical_condition modelMedical_condition = new Medical_condition(medical_condition);

        final Set<Tag> modelTags = new HashSet<>(volunteerTags);
        return new Volunteer(modelName, modelAge, modelRace, modelPhone, modelAddress, modelEmail, modelEmergency_contact, modelDietary_preference, modelMedical_condition,modelTags);
    }

}
