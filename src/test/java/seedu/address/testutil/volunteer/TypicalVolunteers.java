package seedu.address.testutil.volunteer;

import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.VALID_TAG_INJURY;
import static seedu.address.logic.commands.volunteer.VolunteerCommandTestUtil.VALID_TAG_NEW;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.volunteer.Volunteer;
import seedu.address.testutil.volunteer.VolunteerBuilder;

/**
 * A utility class containing a list of {@code Volunteer} objects to be used in tests.
 */
public class TypicalVolunteers {

    public static final Volunteer ALICE = new VolunteerBuilder().withName("Alice Pauline")
        .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
        .withPhone("94351253").withAge("19").withRace("Chinese").withMedicalCondition("nil")
        .withTags("friends").build();
    public static final Volunteer BENSON = new VolunteerBuilder().withName("Benson Meier")
        .withAddress("311, Clementi Ave 2, #02-25").withAge("17").withRace("Chinese").withMedicalCondition("injured")
        .withEmail("johnd@example.com").withPhone("98765432")
        .withTags("owesMoney", "friends").build();
    public static final Volunteer CARL = new VolunteerBuilder().withName("Carl Kurz").withPhone("95352563")
        .withEmail("heinz@example.com").withAddress("wall street")
        .withAge("19").withRace("French").withMedicalCondition("nil").build();
    public static final Volunteer DANIEL = new VolunteerBuilder().withName("Daniel Meier").withPhone("87652533")
        .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends")
        .withAge("19").withRace("Chinese").withMedicalCondition("nil").build();
    public static final Volunteer ELLE = new VolunteerBuilder().withName("Elle Meyer").withPhone("9482224")
        .withEmail("werner@example.com").withAddress("michegan ave")
        .withAge("29").withRace("Indian").withMedicalCondition("vegetable").build();
    public static final Volunteer FIONA = new VolunteerBuilder().withName("Fiona Kunz").withPhone("9482427")
        .withEmail("lydia@example.com").withAddress("little tokyo")
        .withAge("13").withRace("English").withMedicalCondition("nil").build();
    public static final Volunteer GEORGE = new VolunteerBuilder().withName("George Best").withPhone("9482442")
        .withEmail("anna@example.com").withAddress("4th street")
        .withAge("15").withRace("Chinese").withMedicalCondition("dead").build();

    // Manually added
    public static final Volunteer HOON = new VolunteerBuilder().withName("Hoon Meier").withPhone("8482424")
        .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Volunteer IDA = new VolunteerBuilder().withName("Ida Mueller").withPhone("8482131")
        .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Volunteer's details found in {@code CommandTestUtil}
    public static final Volunteer AMY = new VolunteerBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
        .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_INJURY).build();
    public static final Volunteer BOB = new VolunteerBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
        .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_NEW, VALID_TAG_INJURY)
        .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalVolunteers() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical volunteers.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Volunteer volunteer : getTypicalVolunteers()) {
            ab.addVolunteer(volunteer);
        }
        return ab;
    }

    public static List<Volunteer> getTypicalVolunteers() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }


    public static ArrayList<Integer> getTypicalVolunteersPoints() {
        ArrayList<Integer> points = new ArrayList<>();
        points.add(6);
        points.add(1);
        points.add(5);
        points.add(6);
        points.add(2);
        points.add(3);
        points.add(1);
        return points;
    }

}
