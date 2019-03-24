package seedu.address.model.volunteer;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

public class Volunteer {
    // Identity fields
    private final Name name;
    private final Age age;
    private final Phone phone;
    private final Email email;
    private final Race race;

    // Data fields
    private final EmergencyContact emergency_contact;
    private final Address address;
    private final DietaryPreference dietary_preference;
    private final MedicalCondition medical_condition;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Volunteer(Name name, Age age, Race race, Phone phone,Address address, Email email, EmergencyContact emergency_contact,DietaryPreference dietary_preference, MedicalCondition medical_condition , Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.age = age;
        this.race = race;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.emergency_contact = emergency_contact;
        this.dietary_preference = dietary_preference;
        this.tags.addAll(tags);
        this.medical_condition = medical_condition;
    }
    public Name getName() {
        return name;
    }

    public Age getAge() {return age;}

    public Race getRace() {return race;}

    public Phone getPhone() { return phone; }

    public Email getEmail() { return email; }

    public Address getAddress() { return address; }

    public DietaryPreference getDietaryPreference() {return dietary_preference;}

    public EmergencyContact getEmergencyContact() { return emergency_contact;}

    public MedicalCondition getMedicalCondition() {return medical_condition;}

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both volunteers of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two volunteers.
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
     * Returns true if both volunteers have the same identity and data fields.
     * This defines a stronger notion of equality between two volunteers.
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
                && otherVolunteer.getDietaryPreference().equals(getDietaryPreference())
                && otherVolunteer.getMedicalCondition().equals(getMedicalCondition())
                && otherVolunteer.getEmergencyContact().equals(getEmergencyContact())
                && otherVolunteer.getTags().equals(getTags());
    }

    @Override
    public int hashCode() { //To change later***
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, race, age, phone, address, email, emergency_contact, dietary_preference, medical_condition, tags);
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
                .append(getMedicalCondition())
                .append("Emergency Contact: ")
                .append(getEmergencyContact())
                .append ("Dietary Preference: ")
                .append(getDietaryPreference())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
