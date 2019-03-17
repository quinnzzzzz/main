package systemtests;

import static org.junit.Assert.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_VOLUNTEER_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.DeleteVolunteerCommand.MESSAGE_DELETE_VOLUNTEER_SUCCESS;
import static seedu.address.testutil.TestUtil.getLastIndex;
import static seedu.address.testutil.TestUtil.getMidIndex;
import static seedu.address.testutil.TestUtil.getVolunteer;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_VOLUNTEER;
import static seedu.address.testutil.TypicalVolunteers.KEYWORD_MATCHING_MEIER;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteVolunteerCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.model.Model;
import seedu.address.model.volunteer.Volunteer;

public class DeleteVolunteerCommandSystemTest extends AddressBookSystemTest {

    private static final String MESSAGE_INVALID_DELETE_COMMAND_FORMAT =
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteVolunteerCommand.MESSAGE_USAGE);

    @Test
    public void delete() {
        /* ----------------- Performing delete operation while an unfiltered list is being shown -------------------- */

        /* Case: delete the first volunteer in the list, command with leading spaces and trailing spaces -> deleted */
        Model expectedModel = getModel();
        String command = "     " + DeleteVolunteerCommand.COMMAND_WORD + "      " + INDEX_FIRST_VOLUNTEER.getOneBased() + "       ";
        Volunteer deletedVolunteer = removeVolunteer(expectedModel, INDEX_FIRST_VOLUNTEER);
        String expectedResultMessage = String.format(MESSAGE_DELETE_VOLUNTEER_SUCCESS, deletedVolunteer);
        assertCommandSuccess(command, expectedModel, expectedResultMessage);

        /* Case: delete the last volunteer in the list -> deleted */
        Model modelBeforeDeletingLast = getModel();
        Index lastVolunteerIndex = getLastIndex(modelBeforeDeletingLast);
        assertCommandSuccess(lastVolunteerIndex);

        /* Case: undo deleting the last volunteer in the list -> last volunteer restored */
        command = UndoCommand.COMMAND_WORD;
        expectedResultMessage = UndoCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, modelBeforeDeletingLast, expectedResultMessage);

        /* Case: redo deleting the last volunteer in the list -> last volunteer deleted again */
        command = RedoCommand.COMMAND_WORD;
        removeVolunteer(modelBeforeDeletingLast, lastVolunteerIndex);
        expectedResultMessage = RedoCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, modelBeforeDeletingLast, expectedResultMessage);

        /* Case: delete the middle volunteer in the list -> deleted */
        Index middleVolunteerIndex = getMidIndex(getModel());
        assertCommandSuccess(middleVolunteerIndex);

        /* ------------------ Performing delete operation while a filtered list is being shown ---------------------- */

        /* Case: filtered volunteer list, delete index within bounds of address book and volunteer list -> deleted */
        showVolunteersWithName(KEYWORD_MATCHING_MEIER);
        Index index = INDEX_FIRST_VOLUNTEER;
        assertTrue(index.getZeroBased() < getModel().getFilteredVolunteerList().size());
        assertCommandSuccess(index);

        /* Case: filtered volunteer list, delete index within bounds of address book but out of bounds of volunteer list
         * -> rejected
         */
        showVolunteersWithName(KEYWORD_MATCHING_MEIER);
        int invalidIndex = getModel().getAddressBook().getVolunteerList().size();
        command = DeleteVolunteerCommand.COMMAND_WORD + " " + invalidIndex;
        assertCommandFailure(command, MESSAGE_INVALID_VOLUNTEER_DISPLAYED_INDEX);

        /* --------------------- Performing delete operation while a volunteer card is selected ------------------------ */

        /* Case: delete the selected volunteer -> volunteer list panel selects the volunteer before the deleted volunteer */
        showAllVolunteers();
        expectedModel = getModel();
        Index selectedIndex = getLastIndex(expectedModel);
        Index expectedIndex = Index.fromZeroBased(selectedIndex.getZeroBased() - 1);
        selectVolunteer(selectedIndex);
        command = DeleteVolunteerCommand.COMMAND_WORD + " " + selectedIndex.getOneBased();
        deletedVolunteer = removeVolunteer(expectedModel, selectedIndex);
        expectedResultMessage = String.format(MESSAGE_DELETE_VOLUNTEER_SUCCESS, deletedVolunteer);
        assertCommandSuccess(command, expectedModel, expectedResultMessage, expectedIndex);

        /* --------------------------------- Performing invalid delete operation ------------------------------------ */

        /* Case: invalid index (0) -> rejected */
        command = DeleteVolunteerCommand.COMMAND_WORD + " 0";
        assertCommandFailure(command, MESSAGE_INVALID_DELETE_COMMAND_FORMAT);

        /* Case: invalid index (-1) -> rejected */
        command = DeleteVolunteerCommand.COMMAND_WORD + " -1";
        assertCommandFailure(command, MESSAGE_INVALID_DELETE_COMMAND_FORMAT);

        /* Case: invalid index (size + 1) -> rejected */
        Index outOfBoundsIndex = Index.fromOneBased(
                getModel().getAddressBook().getVolunteerList().size() + 1);
        command = DeleteVolunteerCommand.COMMAND_WORD + " " + outOfBoundsIndex.getOneBased();
        assertCommandFailure(command, MESSAGE_INVALID_VOLUNTEER_DISPLAYED_INDEX);

        /* Case: invalid arguments (alphabets) -> rejected */
        assertCommandFailure(DeleteVolunteerCommand.COMMAND_WORD + " abc", MESSAGE_INVALID_DELETE_COMMAND_FORMAT);

        /* Case: invalid arguments (extra argument) -> rejected */
        assertCommandFailure(DeleteVolunteerCommand.COMMAND_WORD + " 1 abc", MESSAGE_INVALID_DELETE_COMMAND_FORMAT);

        /* Case: mixed case command word -> rejected */
        assertCommandFailure("DelETE 1", MESSAGE_UNKNOWN_COMMAND);
    }

    /**
     * Removes the {@code Volunteer} at the specified {@code index} in {@code model}'s address book.
     * @return the removed volunteer
     */
    private Volunteer removeVolunteer(Model model, Index index) {
        Volunteer targetVolunteer = getVolunteer(model, index);
        model.deleteVolunteer(targetVolunteer);
        return targetVolunteer;
    }

    /**
     * Deletes the volunteer at {@code toDelete} by creating a default {@code DeleteVolunteerCommand} using {@code toDelete} and
     * performs the same verification as {@code assertCommandSuccess(String, Model, String)}.
     * @see DeleteVolunteerCommandSystemTest#assertCommandSuccess(String, Model, String)
     */
    private void assertCommandSuccess(Index toDelete) {
        Model expectedModel = getModel();
        Volunteer deletedVolunteer = removeVolunteer(expectedModel, toDelete);
        String expectedResultMessage = String.format(MESSAGE_DELETE_VOLUNTEER_SUCCESS, deletedVolunteer);

        assertCommandSuccess(
                DeleteVolunteerCommand.COMMAND_WORD + " " + toDelete.getOneBased(), expectedModel, expectedResultMessage);
    }

    /**
     * Executes {@code command} and in addition,<br>
     * 1. Asserts that the command box displays an empty string.<br>
     * 2. Asserts that the result display box displays {@code expectedResultMessage}.<br>
     * 3. Asserts that the browser url and selected card remains unchanged.<br>
     * 4. Asserts that the status bar's sync status changes.<br>
     * 5. Asserts that the command box has the default style class.<br>
     * Verifications 1 and 2 are performed by
     * {@code AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.
     * @see AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage) {
        assertCommandSuccess(command, expectedModel, expectedResultMessage, null);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(String, Model, String)} except that the browser url
     * and selected card are expected to update accordingly depending on the card at {@code expectedSelectedCardIndex}.
     * @see DeleteVolunteerCommandSystemTest#assertCommandSuccess(String, Model, String)
     * @see AddressBookSystemTest#assertSelectedCardChanged(Index)
     */
    private void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage,
            Index expectedSelectedCardIndex) {
        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);

        if (expectedSelectedCardIndex != null) {
            assertSelectedCardChanged(expectedSelectedCardIndex);
        } else {
            assertSelectedCardUnchanged();
        }

        assertCommandBoxShowsDefaultStyle();
        assertStatusBarUnchangedExceptSyncStatus();
    }

    /**
     * Executes {@code command} and in addition,<br>
     * 1. Asserts that the command box displays {@code command}.<br>
     * 2. Asserts that result display box displays {@code expectedResultMessage}.<br>
     * 3. Asserts that the browser url, selected card and status bar remain unchanged.<br>
     * 4. Asserts that the command box has the error style.<br>
     * Verifications 1 and 2 are performed by
     * {@code AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandFailure(String command, String expectedResultMessage) {
        Model expectedModel = getModel();

        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsErrorStyle();
        assertStatusBarUnchanged();
    }
}
