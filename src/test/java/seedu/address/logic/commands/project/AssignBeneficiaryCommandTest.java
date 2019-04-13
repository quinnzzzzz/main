package seedu.address.logic.commands.project;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.beneficiary.Name;
import seedu.address.model.project.Project;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.beneficiary.BeneficiaryCommandTestUtil.showBeneficiaryAtIndex;
import static seedu.address.testutil.TypicalIndexes.*;
import static seedu.address.testutil.project.TypicalProjects.*;

public class AssignBeneficiaryCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Name beneficiaryToAssign = model.getFilteredBeneficiaryList().get(INDEX_FIRST_BENEFICIARY.getZeroBased()).getName();
        Project projectForAssign = new ProjectBuilder(PROJECT1).build();
        AssignBeneficiaryCommand assignBeneficiaryCommand = new AssignBeneficiaryCommand(projectForAssign.getProjectTitle(),INDEX_FIRST_BENEFICIARY);
        String expectedMessage = String.format(AssignBeneficiaryCommand.MESSAGE_SUCCESS);
        
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.commitAddressBook();
        
        assertCommandSuccess(assignBeneficiaryCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBeneficiaryList().size() + 1);
        Name beneficiaryOutOfBound = model.getFilteredBeneficiaryList().get(outOfBoundIndex.getZeroBased()).getName();
        Project projectForAssign = new ProjectBuilder(PROJECT2).build();
        AssignBeneficiaryCommand assignBeneficiaryCommand = new AssignBeneficiaryCommand(projectForAssign.getProjectTitle(), outOfBoundIndex);

        assertCommandFailure(assignBeneficiaryCommand, model, commandHistory, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showBeneficiaryAtIndex(model, INDEX_FIRST_BENEFICIARY);

        Name beneficiaryToAssign = model.getFilteredBeneficiaryList().get(INDEX_FIRST_BENEFICIARY.getZeroBased()).getName();
        Project projectForAssign = new ProjectBuilder(PROJECT1).build();
        AssignBeneficiaryCommand assignBeneficiaryCommand = new AssignBeneficiaryCommand(projectForAssign.getProjectTitle(),INDEX_FIRST_BENEFICIARY);

        String expectedMessage = String.format(AssignBeneficiaryCommand.MESSAGE_SUCCESS);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.commitAddressBook();
        showNoBeneficiary(expectedModel);

        assertCommandSuccess(assignBeneficiaryCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    private void showNoBeneficiary(Model model) {
        model.updateFilteredBeneficiaryList(p -> false);
        
        assertTrue(model.getFilteredBeneficiaryList().isEmpty());
    }

    @Test
    public void equals() {
        AssignBeneficiaryCommand assignFirstCommand = new AssignBeneficiaryCommand(PROJECT1.getProjectTitle(),INDEX_FIRST_BENEFICIARY);
        AssignBeneficiaryCommand assignSecondCommand = new AssignBeneficiaryCommand(PROJECT2.getProjectTitle(),INDEX_SECOND_BENEFICIARY);

        // same object -> returns true
        assertTrue(assignFirstCommand.equals(assignFirstCommand));

        // same values -> returns true
        AssignBeneficiaryCommand assignFirstCommandCopy = new AssignBeneficiaryCommand(PROJECT1.getProjectTitle(),INDEX_FIRST_BENEFICIARY);
        assertTrue(assignFirstCommand.equals(assignFirstCommandCopy));

        // different types -> returns false
        assertFalse(assignFirstCommand.equals(1));

        // null -> returns false
        assertFalse(assignFirstCommand.equals(null));

        // different beneficiary -> returns false
        assertFalse(assignFirstCommand.equals(assignSecondCommand));
    }


}
