package seedu.address.ui;

import static java.time.Duration.ofMillis;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_VOLUNTEER;
import static seedu.address.testutil.TypicalVolunteers.getTypicalVolunteers;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysVolunteer;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardEquals;

import java.util.Collections;

import org.junit.Test;

import guitests.guihandles.VolunteerCardHandle;
import guitests.guihandles.VolunteerListPanelHandle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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


public class VolunteerListPanelTest extends GuiUnitTest {
    private static final ObservableList<Volunteer> TYPICAL_VOLUNTEERS =
            FXCollections.observableList(getTypicalVolunteers());

    private static final long CARD_CREATION_AND_DELETION_TIMEOUT = 2500;

    private final SimpleObjectProperty<Volunteer> selectedVolunteer = new SimpleObjectProperty<>();
    private VolunteerListPanelHandle volunteerListPanelHandle;

    @Test
    public void display() {
        initUi(TYPICAL_VOLUNTEERS);

        for (int i = 0; i < TYPICAL_VOLUNTEERS.size(); i++) {
            volunteerListPanelHandle.navigateToCard(TYPICAL_VOLUNTEERS.get(i));
            Volunteer expectedVolunteer = TYPICAL_VOLUNTEERS.get(i);
            VolunteerCardHandle actualCard = volunteerListPanelHandle.getVolunteerCardHandle(i);

            assertCardDisplaysVolunteer(expectedVolunteer, actualCard);
            assertEquals(Integer.toString(i + 1) + ". ", actualCard.getId());
        }
    }

    @Test
    public void selection_modelSelectedVolunteerChanged_selectionChanges() {
        initUi(TYPICAL_VOLUNTEERS);
        Volunteer secondVolunteer = TYPICAL_VOLUNTEERS.get(INDEX_SECOND_VOLUNTEER.getZeroBased());
        guiRobot.interact(() -> selectedVolunteer.set(secondVolunteer));
        guiRobot.pauseForHuman();

        VolunteerCardHandle expectedVolunteer = volunteerListPanelHandle.getVolunteerCardHandle(INDEX_SECOND_VOLUNTEER.getZeroBased());
        VolunteerCardHandle selectedVolunteer = volunteerListPanelHandle.getHandleToSelectedCard();
        assertCardEquals(expectedVolunteer, selectedVolunteer);
    }

    /**
     * Verifies that creating and deleting large number of volunteers in {@code VolunteerListPanel} requires lesser than
     * {@code CARD_CREATION_AND_DELETION_TIMEOUT} milliseconds to execute.
     */
    @Test
    public void performanceTest() {
        ObservableList<Volunteer> backingList = createBackingList(10000);

        assertTimeoutPreemptively(ofMillis(CARD_CREATION_AND_DELETION_TIMEOUT), () -> {
            initUi(backingList);
            guiRobot.interact(backingList::clear);
        }, "Creation and deletion of volunteer cards exceeded time limit");
    }

    /**
     * Returns a list of volunteers containing {@code volunteerCount} volunteers that is used to populate the
     * {@code VolunteerListPanel}.
     */
    private ObservableList<Volunteer> createBackingList(int volunteerCount) {
        ObservableList<Volunteer> backingList = FXCollections.observableArrayList();
        for (int i = 0; i < volunteerCount; i++) {
            Name name = new Name(i + "a");
            Age age = new Age("00");
            Race race = new Race("a");
            Phone phone = new Phone("000");
            Email email = new Email("a@aa");
            Address address = new Address("a");
            Emergency_contact emergency_contact =new Emergency_contact("a 000");
            Dietary_preference dietary_preference = new Dietary_preference("a");
            Medical_condition medical_condition = new Medical_condition("a");
            Volunteer volunteer = new Volunteer(name, age, race, phone, address,email,emergency_contact, dietary_preference, medical_condition,  Collections.emptySet());
            backingList.add(volunteer);
        }
        return backingList;
    }

    /**
     * Initializes {@code volunteerListPanelHandle} with a {@code VolunteerListPanel} backed by {@code backingList}.
     * Also shows the {@code Stage} that displays only {@code VolunteerListPanel}.
     */
    private void initUi(ObservableList<Volunteer> backingList) {
        VolunteerListPanel volunteerListPanel =
                new VolunteerListPanel(backingList, selectedVolunteer, selectedVolunteer::set);
        uiPartRule.setUiPart(volunteerListPanel);

        volunteerListPanelHandle = new VolunteerListPanelHandle(getChildNode(volunteerListPanel.getRoot(),
                VolunteerListPanelHandle.VOLUNTEER_LIST_VIEW_ID));
    }
}
