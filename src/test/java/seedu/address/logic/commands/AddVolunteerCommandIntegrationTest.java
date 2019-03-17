package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalVolunteers.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.volunteer.Volunteer;
import seedu.address.testutil.VolunteerBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddVolunteerCommand}.
 */
public class AddVolunteerCommandIntegrationTest {

    private Model model;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newVolunteer_success() {
        Volunteer validVolunteer = new VolunteerBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addVolunteer(validVolunteer);
        expectedModel.commitAddressBook();

        assertCommandSuccess(new AddVolunteerCommand(validVolunteer), model, commandHistory,
                String.format(AddVolunteerCommand.MESSAGE_SUCCESS, validVolunteer), expectedModel);
    }

    @Test
    public void execute_duplicateVolunteer_throwsCommandException() {
        Volunteer volunteerInList = model.getAddressBook().getVolunteerList().get(0);
        assertCommandFailure(new AddVolunteerCommand(volunteerInList), model, commandHistory,
                AddVolunteerCommand.MESSAGE_DUPLICATE_VOLUNTEER);
    }

}
