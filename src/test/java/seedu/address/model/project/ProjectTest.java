package seedu.address.model.project;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalProjects.PROJECT1;
import static seedu.address.testutil.TypicalProjects.PROJECT2;

import seedu.address.logic.commands.ProjectBuilder;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ProjectTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Project project = new ProjectBuilder().build();
        thrown.expect(UnsupportedOperationException.class);
    }

    @Test
    public void isSameProject() {
        // same object -> returns true
        assertTrue(PROJECT1.isSameProject(PROJECT1));

        // null -> returns false
        assertFalse(PROJECT2.isSameProject(null ));

        // different date -> returns false
        Project editedPROJECT1 = new ProjectBuilder(PROJECT1).withProjectTitle(VALID_TITLE_RECYCLE).withProjectDate(VALID_DATE).build();
        assertFalse(PROJECT1.isSameProject(editedPROJECT1));

        // different name -> returns false
        editedPROJECT1 = new ProjectBuilder(PROJECT1).withProjectTitle(VALID_TITLE_RECYCLE).build();
        assertFalse(PROJECT1.isSameProject(editedPROJECT1));
        
        // same projectTitle, projectDate -> returns true
        editedPROJECT1 = new ProjectBuilder(PROJECT1).withProjectTitle(VALID_TITLE_SUNSHINE).withProjectDate(VALID_DATE).build();
        assertTrue(PROJECT1.isSameProject(editedPROJECT1));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Project project1Copy = new ProjectBuilder(PROJECT1).build();
        assertTrue(PROJECT1.equals(project1Copy));

        // same object -> returns true
        assertTrue(PROJECT1.equals(PROJECT1));

        // null -> returns false
        assertFalse(PROJECT1.equals(null));

        // different type -> returns false
        assertFalse(PROJECT1.equals(5));

        // different project -> returns false
        assertFalse(PROJECT1.equals(PROJECT2));

        // different title -> returns false
        Project editedProject1 = new ProjectBuilder(PROJECT1).withProjectTitle(VALID_TITLE_SUNSHINE).build();
        assertFalse(PROJECT1.equals(editedProject1));

        // different date -> returns false
        editedProject1 = new ProjectBuilder(PROJECT1).withProjectDate(VALID_DATE).build();
        assertFalse(PROJECT1.equals(editedProject1));
    }
}
