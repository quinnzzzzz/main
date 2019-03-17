package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Volunteer
 */
public class Volunteer {
    // Identity fields
    private final Name name;
    private final Age age;
    private final Phone phone;
    private final Email email;
    private final Race race;

    // Data fields
    private final EmergencyContact emergencyContact;
    private final Address address;
    private final DietaryPreference dietaryPreference;
    private final MedicalCondition medicalCondition;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Volunteer(Name name, Age age, Race race, Phone phone, Address address, Email email,
                     EmergencyContact emergencyContact, DietaryPreference dietaryPreference,
                     MedicalCondition medicalCondition , Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.age = age;
        this.race = race;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.emergencyContact = emergencyContact;
        this.dietaryPreference = dietaryPreference;
        this.tags.addAll(tags);
        this.medicalCondition = medicalCondition;
    }

    public Name getName() {
        return name;
    }

    public Age getAge() {
        return age;
    }

    public Race getRace() {
        return race;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() { return address; }

    public DietaryPreference getDietary_preference() {
        return dietaryPreference;
    }

    public EmergencyContact getEmergency_contact() {
        return emergencyContact;
    }

    public MedicalCondition getMedical_condition() {
        return medicalCondition;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameVolunteer(Volunteer otherVolunteer) { //change later
        if (otherVolunteer == this) {
            return true;
        }

        return otherVolunteer != null
                && otherVolunteer.getName().equals(getName())
                && (otherVolunteer.getPhone().equals(getPhone()) || otherVolunteer.getEmail().equals(getEmail()));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Volunteer)) {
            return false;
        }

        Volunteer otherVolunteer = (Volunteer) other;
        return otherVolunteer.getName().equals(getName())
                && otherVolunteer.getAge().equals(getAge())
                && otherVolunteer.getRace().equals(getRace())
                && otherVolunteer.getPhone().equals(getPhone())
                && otherVolunteer.getEmail().equals(getEmail())
                && otherVolunteer.getAddress().equals(getAddress())
                && otherVolunteer.getDietary_preference().equals(getDietary_preference())
                && otherVolunteer.getMedical_condition().equals(getMedical_condition())
                && otherVolunteer.getEmergency_contact().equals(getEmergency_contact())
                && otherVolunteer.getTags().equals(getTags());
    }

    @Override
    public int hashCode() { //To change later***
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Age: ")
                .append(getAge())
                .append(" Race: ")
                .append(getRace())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress())
                .append("Medical Condition: ")
                .append(getMedical_condition())
                .append("Emergency Contact: ")
                .append(getEmergency_contact())
                .append ("Dietary Preference: ")
                .append(getDietary_preference())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
