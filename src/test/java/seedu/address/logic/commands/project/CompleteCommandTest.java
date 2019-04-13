package seedu.address.logic.commands.project;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.project.TypicalProjects.PROJECT1;
import static seedu.address.testutil.project.TypicalProjects.getTypicalAddressBook;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectTitle;

public class CompleteCommandTest {

    private CommandHistory commandHistory = new CommandHistory();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        CompleteCommand completeCommand = new CompleteCommand(INDEX_FIRST);
        ProjectTitle projectTitle = model.getFilteredProjectList().get(0).getProjectTitle();
        String expectedMessage = String.format(CompleteCommand.MESSAGE_SUCCESS,projectTitle );
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.commitAddressBook();
        assertCommandSuccess(completeCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_InvalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProjectList().size() + 1);
        CompleteCommand completeCommand = new CompleteCommand(outOfBoundIndex);
        assertCommandFailure(completeCommand, model, commandHistory, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

}
