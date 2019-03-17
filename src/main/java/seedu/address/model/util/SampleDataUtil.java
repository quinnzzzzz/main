package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
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
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Volunteer[] getSampleVolunteers() {
        return new Volunteer[] {
            new Volunteer(new Name("Alex Yeoh"), new Age("22"), new Race("Chinese"), new Phone("87438807"),
                new Address("Blk 30 Geylang Street 29, #06-40"),new Email("alexyeoh@example.com"), new Emergency_contact("Mum 83232123"), new Dietary_preference("NIL"), new Medical_condition("NIL"),
                getTagSet("friends")),
            new Volunteer(new Name("Bernice Yu"), new Age("19"), new Race("Chinese"),new Phone("99272758"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),new Email("berniceyu@example.com"), new Emergency_contact("Mum 81111123"), new Dietary_preference("NIL"), new Medical_condition("Asthma"),
                getTagSet("colleagues", "friends")),
            new Volunteer(new Name("Charlotte Oliveiro"), new Age("27"), new Race("Caucasian"),new Phone("93210283"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),new Email("charlotte@example.com"),
                new Emergency_contact("Sister 83222223"), new Dietary_preference("NIL"), new Medical_condition("NIL"),
                getTagSet("neighbours")),
            new Volunteer(new Name("David Li"), new Age("44"), new Race("Chinese"),new Phone("91031282"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),new Email("lidavid@example.com"),
                new Emergency_contact("Wife 93232123"), new Dietary_preference("NIL"), new Medical_condition("NIL"),
                getTagSet("family")),
            new Volunteer(new Name("Irfan Ibrahim"), new Age("33"), new Race("Malay"),new Phone("92492021"), new Address("Blk 47 Tampines Street 20, #17-35"),new Email("irfan@example.com"),
                new Emergency_contact("Mum 83244123"), new Dietary_preference("NIL"), new Medical_condition("Dislocated shoulder: Can't carry heavy loads"),
                getTagSet("classmates")),
            new Volunteer(new Name("Roy Balakrishnan"), new Age("20"), new Race("Indian"),new Phone("92624417"), new Address("Blk 45 Aljunied Street 85, #11-31"),new Email("royb@example.com"),
                new Emergency_contact("Brother 81142123"), new Dietary_preference("NIL"), new Medical_condition("NIL"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Volunteer sampleVolunteer : getSampleVolunteers()) {
            sampleAb.addVolunteer(sampleVolunteer);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
