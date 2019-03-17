package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showVolunteerAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_VOLUNTEER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_VOLUNTEER;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_VOLUNTEER;
import static seedu.address.testutil.TypicalVolunteers.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code SelectVolunteerCommand}.
 */
public class SelectVolunteerCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Index lastVolunteerIndex = Index.fromOneBased(model.getFilteredVolunteerList().size());

        assertExecutionSuccess(INDEX_FIRST_VOLUNTEER);
        assertExecutionSuccess(INDEX_THIRD_VOLUNTEER);
        assertExecutionSuccess(lastVolunteerIndex);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredVolunteerList().size() + 1);

        assertExecutionFailure(outOfBoundsIndex, Messages.MESSAGE_INVALID_VOLUNTEER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showVolunteerAtIndex(model, INDEX_FIRST_VOLUNTEER);
        showVolunteerAtIndex(expectedModel, INDEX_FIRST_VOLUNTEER);

        assertExecutionSuccess(INDEX_FIRST_VOLUNTEER);
    }

    @Test
    public void execute_invalidIndexFilteredList_failure() {
        showVolunteerAtIndex(model, INDEX_FIRST_VOLUNTEER);
        showVolunteerAtIndex(expectedModel, INDEX_FIRST_VOLUNTEER);

        Index outOfBoundsIndex = INDEX_SECOND_VOLUNTEER;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundsIndex.getZeroBased() < model.getAddressBook().getVolunteerList().size());

        assertExecutionFailure(outOfBoundsIndex, Messages.MESSAGE_INVALID_VOLUNTEER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        SelectVolunteerCommand selectFirstCommand = new SelectVolunteerCommand(INDEX_FIRST_VOLUNTEER);
        SelectVolunteerCommand selectSecondCommand = new SelectVolunteerCommand(INDEX_SECOND_VOLUNTEER);

        // same object -> returns true
        assertTrue(selectFirstCommand.equals(selectFirstCommand));

        // same values -> returns true
        SelectVolunteerCommand selectFirstCommandCopy = new SelectVolunteerCommand(INDEX_FIRST_VOLUNTEER);
        assertTrue(selectFirstCommand.equals(selectFirstCommandCopy));

        // different types -> returns false
        assertFalse(selectFirstCommand.equals(1));

        // null -> returns false
        assertFalse(selectFirstCommand.equals(null));

        // different volunteer -> returns false
        assertFalse(selectFirstCommand.equals(selectSecondCommand));
    }

    /**
     * Executes a {@code SelectVolunteerCommand} with the given {@code index},
     * and checks that the model's selected volunteer is set to the volunteer at {@code index} in the filtered volunteer list.
     */
    private void assertExecutionSuccess(Index index) {
        SelectVolunteerCommand selectCommand = new SelectVolunteerCommand(index);
        String expectedMessage = String.format(SelectVolunteerCommand.MESSAGE_SELECT_VOLUNTEER_SUCCESS, index.getOneBased());
        expectedModel.setSelectedVolunteer(model.getFilteredVolunteerList().get(index.getZeroBased()));

        assertCommandSuccess(selectCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    /**
     * Executes a {@code SelectVolunteerCommand} with the given {@code index}, and checks that a {@code CommandException}
     * is thrown with the {@code expectedMessage}.
     */
    private void assertExecutionFailure(Index index, String expectedMessage) {
        SelectVolunteerCommand selectCommand = new SelectVolunteerCommand(index);
        assertCommandFailure(selectCommand, model, commandHistory, expectedMessage);
    }
}
