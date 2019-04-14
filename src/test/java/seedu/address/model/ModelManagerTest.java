//package seedu.address.model;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
//import static seedu.address.testutil.volunteer.TypicalVolunteers.ALICE;
//import static seedu.address.testutil.volunteer.TypicalVolunteers.BOB;
//
//
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Arrays;
//import java.util.Collections;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//
//import seedu.address.commons.core.GuiSettings;
//
//import seedu.address.model.volunteer.Volunteer;
//import seedu.address.model.volunteer.exceptions.VolunteerNotFoundException;
//import seedu.address.testutil.volunteer.VolunteerBuilder;
//
//
//public class ModelManagerTest {
//    @Rule
//    public ExpectedException thrown = ExpectedException.none();
//
//    private ModelManager modelManager = new ModelManager();
//
//    @Test
//    public void constructor() {
//        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
//        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
//        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
//
//    }
//
//    @Test
//    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
//        thrown.expect(NullPointerException.class);
//        modelManager.setUserPrefs(null);
//    }
//
//    @Test
//    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
//        UserPrefs userPrefs = new UserPrefs();
//        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
//        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
//        modelManager.setUserPrefs(userPrefs);
//        assertEquals(userPrefs, modelManager.getUserPrefs());
//
//        // Modifying userPrefs should not modify modelManager's userPrefs
//        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
//        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
//        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
//    }
//
//    @Test
//    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
//        thrown.expect(NullPointerException.class);
//        modelManager.setGuiSettings(null);
//    }
//
//    @Test
//    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
//        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
//        modelManager.setGuiSettings(guiSettings);
//        assertEquals(guiSettings, modelManager.getGuiSettings());
//    }
//
//    @Test
//    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
//        thrown.expect(NullPointerException.class);
//        modelManager.setAddressBookFilePath(null);
//    }
//
//    @Test
//    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
//        Path path = Paths.get("address/book/file/path");
//        modelManager.setAddressBookFilePath(path);
//        assertEquals(path, modelManager.getAddressBookFilePath());
//    }
//
//    @Test
//    public void hasVolunteer_nullVolunteer_throwsNullPointerException() {
//        thrown.expect(NullPointerException.class);
//        modelManager.hasVolunteer(null);
//    }
//
//    @Test
//    public void hasVolunteer_volunteerNotInAddressBook_returnsFalse() {
//        assertFalse(modelManager.hasVolunteer(ALICE));
//    }
//
//    @Test
//    public void hasVolunteer_volunteerInAddressBook_returnsTrue() {
//        modelManager.addVolunteer(ALICE);
//        assertTrue(modelManager.hasVolunteer(ALICE));
//    }
//
//    @Test
//    public void deleteVolunteer_volunteerIsSelectedAndFirstVolunteerInFilteredVolunteerList_selectionCleared() {
//        modelManager.addVolunteer(ALICE);
//        modelManager.setSelectedVolunteer(ALICE);
//        modelManager.deleteVolunteer(ALICE);
//        assertEquals(null, modelManager.getSelectedVolunteer());
//    }
//
//    @Test
//    public void deleteVolunteer_volunteerIsSelectedAndSecondVolunteerInFilteredVolunteerList_firstVolunteerSelected() {
//        modelManager.addVolunteer(ALICE);
//        modelManager.addVolunteer(BOB);
//        assertEquals(Arrays.asList(ALICE, BOB), modelManager.getFilteredVolunteerList());
//        modelManager.setSelectedVolunteer(BOB);
//        modelManager.deleteVolunteer(BOB);
//        assertEquals(ALICE, modelManager.getSelectedVolunteer());
//    }
//
//    @Test
//    public void setVolunteer_volunteerIsSelected_selectedVolunteerUpdated() {
//        modelManager.addVolunteer(ALICE);
//        modelManager.setSelectedVolunteer(ALICE);
//        Volunteer updatedAlice = new VolunteerBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
//        modelManager.setVolunteer(ALICE, updatedAlice);
//        assertEquals(updatedAlice, modelManager.getSelectedVolunteer());
//    }
//
//    @Test
//    public void getFilteredVolunteerList_modifyList_throwsUnsupportedOperationException() {
//        thrown.expect(UnsupportedOperationException.class);
//        modelManager.getFilteredVolunteerList().remove(0);
//    }
//
//    @Test
//    public void setSelectedVolunteer_volunteerNotInFilteredVolunteerList_throwsVolunteerNotFoundException() {
//        thrown.expect(VolunteerNotFoundException.class);
//        modelManager.setSelectedVolunteer(ALICE);
//    }
//
//    @Test
//    public void setSelectedVolunteer_volunteerInFilteredVolunteerList_setsSelectedVolunteer() {
//        modelManager.addVolunteer(ALICE);
//        assertEquals(Collections.singletonList(ALICE), modelManager.getFilteredVolunteerList());
//        modelManager.setSelectedVolunteer(ALICE);
//        assertEquals(ALICE, modelManager.getSelectedVolunteer());
//    }
//}
//
//    /*
//    @Test
//    public void equals() {
//        AddressBook addressBook = new AddressBookBuilder().withVolunteer(ALICE).withVolunteer(BENSON).build();
//=======
//    public void equals() {
//        AddressBook addressBook = new AddressBookBuilder().build();
//>>>>>>> 642e90cc2728cfd2ed77d867da959d328479a370
//        AddressBook differentAddressBook = new AddressBook();
//        UserPrefs userPrefs = new UserPrefs();
//
//        // same values -> returns true
//        modelManager = new ModelManager(addressBook, userPrefs);
//        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
//        assertTrue(modelManager.equals(modelManagerCopy));
//
//        // same object -> returns true
//        assertTrue(modelManager.equals(modelManager));
//
//        // null -> returns false
//        assertFalse(modelManager.equals(null));
//
//        // different types -> returns false
//        assertFalse(modelManager.equals(5));
//
//        // different addressBook -> returns false
//        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));
//
//<<<<<<< HEAD
//        // different filteredList -> returns false
//        String[] keywords = ALICE.getName().fullName.split("\\s+");
//        modelManager.updateFilteredVolunteerList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
//        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));
//
//        // resets modelManager to initial state for upcoming tests
//        modelManager.updateFilteredVolunteerList(PREDICATE_SHOW_ALL_PERSONS);
//
//=======
//>>>>>>> 642e90cc2728cfd2ed77d867da959d328479a370
//        // different userPrefs -> returns false
//        UserPrefs differentUserPrefs = new UserPrefs();
//        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
//        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
//    }
//}
