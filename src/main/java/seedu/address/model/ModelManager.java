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
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.person.Volunteer;
import seedu.address.model.beneficiary.exceptions.BeneficiaryNotFoundException;
import seedu.address.model.project.Project;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedAddressBook versionedAddressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Beneficiary> filteredBeneficiarys;
    private final SimpleObjectProperty<Beneficiary> selectedBeneficiary = new SimpleObjectProperty<>();

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        versionedAddressBook = new VersionedAddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredBeneficiarys = new FilteredList<>(versionedAddressBook.getBeneficiaryList());
        filteredBeneficiarys.addListener(this::ensureSelectedBeneficiaryIsValid);
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
    public boolean hasBeneficiary(Beneficiary beneficiary) {
        requireNonNull(beneficiary);
        return versionedAddressBook.hasBeneficiary(beneficiary);
    }

    @Override
    public void deleteBeneficiary(Beneficiary target) {
        versionedAddressBook.removeBeneficiary(target);
    }

    @Override
    public void addBeneficiary(Beneficiary beneficiary) {
        versionedAddressBook.addBeneficiary(beneficiary);
        updateFilteredBeneficiaryList(PREDICATE_SHOW_ALL_BENEFICIARIES);
    }

    @Override
    public void addProject(Project project) {
        versionedAddressBook.addProject(project);
    }

    @Override
    public void setBeneficiary(Beneficiary target, Beneficiary editedBeneficiary) {
        requireAllNonNull(target, editedBeneficiary);

        versionedAddressBook.setBeneficiary(target, editedBeneficiary);
    }

    //=========== Filtered Beneficiary List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Beneficiary} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Beneficiary> getFilteredBeneficiaryList() {
        return filteredBeneficiarys;
    }

    @Override
    public void updateFilteredBeneficiaryList(Predicate<Beneficiary> predicate) {
        requireNonNull(predicate);
        filteredBeneficiarys.setPredicate(predicate);
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

    //=========== Selected beneficiary ===========================================================================

    @Override
    public ReadOnlyProperty<Beneficiary> selectedBeneficiaryProperty() {
        return selectedBeneficiary;
    }

    @Override
    public Beneficiary getSelectedBeneficiary() {
        return selectedBeneficiary.getValue();
    }

    @Override
    public void setSelectedBeneficiary(Beneficiary beneficiary) {
        if (beneficiary != null && !filteredBeneficiarys.contains(beneficiary)) {
            throw new BeneficiaryNotFoundException();
        }
        selectedBeneficiary.setValue(beneficiary);
    }

    /**
     * Ensures {@code selectedBeneficiary} is a valid beneficiary in {@code filteredBeneficiarys}.
     */
    private void ensureSelectedBeneficiaryIsValid(ListChangeListener.Change<? extends Beneficiary> change) {
        while (change.next()) {
            if (selectedBeneficiary.getValue() == null) {
                // null is always a valid selected beneficiary, so we do not need to check that it is valid anymore.
                return;
            }

            boolean wasSelectedBeneficiaryReplaced = change.wasReplaced() && change.getAddedSize() == change.getRemovedSize()
                    && change.getRemoved().contains(selectedBeneficiary.getValue());
            if (wasSelectedBeneficiaryReplaced) {
                // Update selectedBeneficiary to its new value.
                int index = change.getRemoved().indexOf(selectedBeneficiary.getValue());
                selectedBeneficiary.setValue(change.getAddedSubList().get(index));
                continue;
            }

            boolean wasSelectedBeneficiaryRemoved = change.getRemoved().stream()
                    .anyMatch(removedBeneficiary -> selectedBeneficiary.getValue().isSameBeneficiary(removedBeneficiary));
            if (wasSelectedBeneficiaryRemoved) {
                // Select the beneficiary that came before it in the list,
                // or clear the selection if there is no such beneficiary.
                selectedBeneficiary.setValue(change.getFrom() > 0 ? change.getList().get(change.getFrom() - 1) : null);
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
                && filteredBeneficiarys.equals(other.filteredBeneficiarys)
                && Objects.equals(selectedBeneficiary.get(), other.selectedBeneficiary.get());
    }
}
