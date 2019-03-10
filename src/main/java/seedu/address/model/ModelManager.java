package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.volunteer.Volunteer;
import seedu.address.model.volunteer.Volunteer;
import seedu.address.model.volunteer.exceptions.VolunteerNotFoundException;
import seedu.address.model.project.Project;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedAddressBook versionedAddressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Volunteer> filteredVolunteers;
    private final SimpleObjectProperty<Volunteer> selectedVolunteer = new SimpleObjectProperty<>();

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        versionedAddressBook = new VersionedAddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredVolunteers = new FilteredList<>(versionedAddressBook.getVolunteerList());
        filteredVolunteers.addListener(this::ensureSelectedVolunteerIsValid);
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        versionedAddressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return versionedAddressBook;
    }

    @Override
    public boolean hasVolunteer(Volunteer volunteer) {
        requireNonNull(volunteer);
        return versionedAddressBook.hasVolunteer(volunteer);
    }

    @Override
    public void deleteVolunteer(Volunteer target) {
        versionedAddressBook.removeVolunteer(target);
    }

    @Override
    public void addVolunteer(Volunteer volunteer) {
        versionedAddressBook.addVolunteer(volunteer);
        updateFilteredVolunteerList(PREDICATE_SHOW_ALL_VOLUNTEERS);
    }

    @Override
    public void addProject(Project project) {
        versionedAddressBook.addProject(project);
    }

    @Override
    public void setVolunteer(Volunteer target, Volunteer editedVolunteer) {
        requireAllNonNull(target, editedVolunteer);

        versionedAddressBook.setVolunteer(target, editedVolunteer);
    }

    //=========== Filtered Volunteer List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Volunteer} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Volunteer> getFilteredVolunteerList() {
        return filteredVolunteers;
    }

    @Override
    public void updateFilteredVolunteerList(Predicate<Volunteer> predicate) {
        requireNonNull(predicate);
        filteredVolunteers.setPredicate(predicate);
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoAddressBook() {
        return versionedAddressBook.canUndo();
    }

    @Override
    public boolean canRedoAddressBook() {
        return versionedAddressBook.canRedo();
    }

    @Override
    public void undoAddressBook() {
        versionedAddressBook.undo();
    }

    @Override
    public void redoAddressBook() {
        versionedAddressBook.redo();
    }

    @Override
    public void commitAddressBook() {
        versionedAddressBook.commit();
    }

    //=========== Selected volunteer ===========================================================================

    @Override
    public ReadOnlyProperty<Volunteer> selectedVolunteerProperty() {
        return selectedVolunteer;
    }

    @Override
    public Volunteer getSelectedVolunteer() {
        return selectedVolunteer.getValue();
    }

    @Override
    public void setSelectedVolunteer(Volunteer volunteer) {
        if (volunteer != null && !filteredVolunteers.contains(volunteer))
        selectedVolunteer.setValue(volunteer);
    }

    /**
     * Ensures {@code selectedVolunteer} is a valid volunteer in {@code filteredVolunteers}.
     */
    private void ensureSelectedVolunteerIsValid(ListChangeListener.Change<? extends Volunteer> change) {
        while (change.next()) {
            if (selectedVolunteer.getValue() == null) {
                // null is always a valid selected volunteer, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedVolunteerReplaced = change.wasReplaced() && change.getAddedSize() == change.getRemovedSize()
                    && change.getRemoved().contains(selectedVolunteer.getValue());
            if (wasSelectedVolunteerReplaced) {
                // Update selectedVolunteer to its new value.
                int index = change.getRemoved().indexOf(selectedVolunteer.getValue());
                selectedVolunteer.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedVolunteerRemoved = change.getRemoved().stream()
                    .anyMatch(removedVolunteer -> selectedVolunteer.getValue().isSameVolunteer(removedVolunteer));
            if (wasSelectedVolunteerRemoved) {
                // Select the volunteer that came before it in the list,
                // or clear the selection if there is no such volunteer.
                selectedVolunteer.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return versionedAddressBook.equals(other.versionedAddressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredVolunteers.equals(other.filteredVolunteers)
                && Objects.equals(selectedVolunteer.get(), other.selectedVolunteer.get());
    }

    @Override
    public void addBeneficiary(Beneficiary beneficiary) {

    }

    /*
    ** prototype for checking existence of beneficiary
     */
    @Override
    public boolean hasBeneficiary(Beneficiary Beneficiary) {
        return false;
    }

}
