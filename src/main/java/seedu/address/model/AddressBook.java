package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import seedu.address.commons.util.InvalidationListenerManager;
import seedu.address.model.volunteer.Volunteer;
import seedu.address.model.volunteer.UniqueVolunteerList;
import seedu.address.model.project.Project;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueVolunteerList volunteers;
    private final InvalidationListenerManager invalidationListenerManager = new InvalidationListenerManager();

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        volunteers = new UniqueVolunteerList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the volunteer list with {@code volunteers}.
     * {@code volunteers} must not contain duplicate volunteers.
     */
    public void setVolunteers(List<Volunteer> volunteers) {
        this.volunteers.setVolunteers(volunteers);
        indicateModified();
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setVolunteers(newData.getVolunteerList());
    }

    //// volunteer-level operations

    /**
     * Returns true if a volunteer with the same identity as {@code volunteer} exists in the address book.
     */
    public boolean hasVolunteer(Volunteer volunteer){
        requireNonNull(volunteer);
        return volunteers.contains(volunteer);
    }
    /**
     * Adds a volunteer to the address book.
     * The volunteer must not already exist in the address book.
     */

    public void addVolunteer(Volunteer v) {
        volunteers.add(v);
        indicateModified();
    }
    /**
     * Replaces the given volunteer {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedVolunteer} must not be the same as another existing volunteer in the address book.
     */
    public void setVolunteer(Volunteer target, Volunteer editedVolunteer) {
        requireNonNull(editedVolunteer);

        volunteers.setVolunteer(target, editedVolunteer);
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeVolunteer(Volunteer key) {
        volunteers.remove(key);
        indicateModified();
    }

    @Override
    public void addListener(InvalidationListener listener) {
        invalidationListenerManager.addListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        invalidationListenerManager.removeListener(listener);
    }

    /**
     * Notifies listeners that the address book has been modified.
     */
    protected void indicateModified() {
        invalidationListenerManager.callListeners(this);
    }

    //// util methods

    @Override
    public String toString() {
        return volunteers.asUnmodifiableObservableList().size() + " volunteers";
        // TODO: refine later
    }

    @Override
    public ObservableList<Volunteer> getVolunteerList() {
        return volunteers.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && volunteers.equals(((AddressBook) other).volunteers));
    }

    @Override
    public int hashCode() {
        return volunteers.hashCode();
    }

    public void addProject(Project project) {
    }
}
