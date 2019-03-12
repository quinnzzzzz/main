package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.volunteer.Volunteer;
import seedu.address.model.volunteer.Volunteer;
import seedu.address.model.project.Project;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Volunteer> PREDICATE_SHOW_ALL_VOLUNTEERS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a volunteer with the same identity as {@code volunteer} exists in the address book.
     */
    boolean hasVolunteer(Volunteer volunteer);


    /**
     * Deletes the given volunteer.
     * The volunteer must exist in the address book.
     */
    void deleteVolunteer(Volunteer target);

    /**
     * Adds the given project.
     * {@code project} must not already exist in the address book.
     */
    void addProject(Project project);

    /**
     * Adds the given volunteer.
     * {@code volunteer} must not already exist in the address book.
     */
    void addVolunteer(Volunteer volunteer);

    /**
     * Replaces the given volunteer {@code target} with {@code editedVolunteer}.
     * {@code target} must exist in the address book.
     * The volunteer identity of {@code editedVolunteer} must not be the same as another existing volunteer in the address book.
     */
    void setVolunteer(Volunteer target, Volunteer editedVolunteer);

    /**
     * Returns an unmodifiable view of the filtered volunteer list
     */
    ObservableList<Volunteer> getFilteredVolunteerList();

    /**
     * Updates the filter of the filtered volunteer list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredVolunteerList(Predicate<Volunteer> predicate);

    /**
     * Returns true if the model has previous address book states to restore.
     */
    boolean canUndoAddressBook();

    /**
     * Returns true if the model has undone address book states to restore.
     */
    boolean canRedoAddressBook();

    /**
     * Restores the model's address book to its previous state.
     */
    void undoAddressBook();

    /**
     * Restores the model's address book to its previously undone state.
     */
    void redoAddressBook();

    /**
     * Saves the current address book state for undo/redo.
     */
    void commitAddressBook();

    /**
     * Selected volunteer in the filtered volunteer list.
     * null if no volunteer is selected.
     */
    ReadOnlyProperty<Volunteer> selectedVolunteerProperty();

    /**
     * Returns the selected volunteer in the filtered volunteer list.
     * null if no volunteer is selected.
     */

    /**
     * gets the selected volunteer in the filtered volunteer list.
     */

    Volunteer getSelectedVolunteer();
    /**
     * Sets the selected volunteer in the filtered volunteer list.
     */

    void setSelectedVolunteer(Volunteer volunteer);

    /**
     * Add Beneficiary.
     */
    void addBeneficiary(Beneficiary beneficiary);

    /**
     * Check for the existence of the beneficiary.
     */
    boolean hasBeneficiary(Beneficiary beneficiary);
}
