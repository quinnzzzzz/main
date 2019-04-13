package seedu.address.model.project;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class ProjectTitleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ProjectTitle(null));
    }

    @Test
    public void constructor_invalidProjectTitle_throwsIllegalArgumentException() {
        String invalidProjectTitle = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new ProjectTitle(invalidProjectTitle));
    }

    @Test
    public void isValidProjectTitle() {
        // null name
        Assert.assertThrows(NullPointerException.class, () -> ProjectTitle.isValidName(null));

        // invalid name
        assertFalse(ProjectTitle.isValidName("")); // empty string
        assertFalse(ProjectTitle.isValidName(" ")); // spaces only
        assertFalse(ProjectTitle.isValidName("^")); // only non-alphanumeric characters
        assertFalse(ProjectTitle.isValidName("recycle*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(ProjectTitle.isValidName("project sunshine")); // alphabets only
        assertTrue(ProjectTitle.isValidName("12345")); // numbers only
        assertTrue(ProjectTitle.isValidName("recycle 1st")); // alphanumeric characters
        assertTrue(ProjectTitle.isValidName("Charity Run")); // with capital letters
        assertTrue(ProjectTitle.isValidName("Doctors without borders 2019")); // long names
    }
}