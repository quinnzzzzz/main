package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.volunteer.Volunteer;
import seedu.address.model.volunteer.Name;
import seedu.address.model.volunteer.Age;
import seedu.address.model.volunteer.Race;
import seedu.address.model.volunteer.Phone;
import seedu.address.model.volunteer.Address;
import seedu.address.model.volunteer.Email;
import seedu.address.model.volunteer.DietaryPreference;
import seedu.address.model.volunteer.EmergencyContact;
import seedu.address.model.volunteer.MedicalCondition;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.VolunteerSampleDataUtil;

/**
 * A utility class to help with building Volunteer objects.
 */
public class VolunteerBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_AGE = "19";
    public static final String DEFAULT_RACE = "American";;
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_EMERGENCY_CONTACT= "Mary 98125555";
    public static final String DEFAULT_DIETARY_PREFERENCE = "Alice Pauline";
    public static final String DEFAULT_MEDICAL_CONDITION = "NIL";

    private Name name;
    private Age age;
    private Race race;
    private Phone phone;
    private Address address;
    private Email email;
    private EmergencyContact emergency_contact;
    private DietaryPreference dietarypreference;
    private MedicalCondition medicalcondition;
    private Set<Tag> tags;

    public VolunteerBuilder() {
        name = new Name(DEFAULT_NAME);
        age = new Age(DEFAULT_AGE);
        race = new Race(DEFAULT_RACE);
        phone = new Phone(DEFAULT_PHONE);
        address = new Address(DEFAULT_ADDRESS);
        email = new Email(DEFAULT_EMAIL);
        emergency_contact = new EmergencyContact(DEFAULT_EMERGENCY_CONTACT);
        dietarypreference = new DietaryPreference(DEFAULT_DIETARY_PREFERENCE);
        medicalcondition = new MedicalCondition(DEFAULT_MEDICAL_CONDITION);
        tags = new HashSet<>();
    }

    /**
     * Initializes the VolunteerBuilder with the data of {@code volunteerToCopy}.
     */
    public VolunteerBuilder(Volunteer volunteerToCopy) {
        name = volunteerToCopy.getName();
        phone = volunteerToCopy.getPhone();
        email = volunteerToCopy.getEmail();
        address = volunteerToCopy.getAddress();
        tags = new HashSet<>(volunteerToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Volunteer} that we are building.
     */
    public VolunteerBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Volunteer} that we are building.
     */
    public VolunteerBuilder withTags(String ... tags) {
        this.tags = VolunteerSampleDataUtil.getTagSet(tags);
        return this;
    }
    public VolunteerBuilder withAge(String age) {
        this.age = new Age(age);
        return this;
    }
    public VolunteerBuilder withRace(String race) {
        this.race = new Race(race);
        return this;
    }
    public VolunteerBuilder withMedicalCondition(String medicalcondition) {
        this.medicalcondition= new MedicalCondition(medicalcondition);
        return this;
    }
    public VolunteerBuilder withDietaryPreference(String dietarypreference) {
        this.dietarypreference = new DietaryPreference(dietarypreference);
        return this;
    }
    public VolunteerBuilder withEmergencyContact(String emergency_contact) {
        this.emergency_contact = new EmergencyContact(emergency_contact);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Volunteer} that we are building.
     */
    public VolunteerBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Volunteer} that we are building.
     */
    public VolunteerBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Volunteer} that we are building.
     */
    public VolunteerBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Volunteer build() {
        return new Volunteer(name, age, race, phone, address, email, emergency_contact, dietarypreference, medicalcondition, tags);
    }

}
