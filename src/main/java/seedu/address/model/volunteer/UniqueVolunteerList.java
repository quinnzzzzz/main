package seedu.address.model.volunteer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.volunteer.exceptions.DuplicateVolunteerException;
import seedu.address.model.volunteer.exceptions.VolunteerNotFoundException;

public class UniqueVolunteerList implements Iterable<Volunteer> {

    private final ObservableList<Volunteer> internalList = FXCollections.observableArrayList();
    private final ObservableList<Volunteer> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent volunteer as the given argument.
     */
    public boolean contains(Volunteer toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameVolunteer);
    }

    /**
     * Adds a volunteer to the list.
     * The volunteer must not already exist in the list.
     */
    public void add(Volunteer toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateVolunteerException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the volunteer {@code target} in the list with {@code editedVolunteer}.
     * {@code target} must exist in the list.
     * The volunteer identity of {@code editedVolunteer} must not be the same as another existing volunteer in the list.
     */
    public void setVolunteer(Volunteer target, Volunteer editedVolunteer) {
        requireAllNonNull(target, editedVolunteer);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new VolunteerNotFoundException();
        }

        if (!target.isSameVolunteer(editedVolunteer) && contains(editedVolunteer)) {
            throw new DuplicateVolunteerException();
        }

        internalList.set(index, editedVolunteer);
    }

    /**
     * Removes the equivalent volunteer from the list.
     * The volunteer must exist in the list.
     */
    public void remove(Volunteer toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new VolunteerNotFoundException();
        }
    }

    public void setVolunteers(seedu.address.model.volunteer.UniqueVolunteerList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code volunteers}.
     * {@code volunteers} must not contain duplicate volunteers.
     */
    public void setVolunteers(List<Volunteer> volunteers) {
        requireAllNonNull(volunteers);
        if (!volunteersAreUnique(volunteers)) {
            throw new DuplicateVolunteerException();
        }

        internalList.setAll(volunteers);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Volunteer> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Volunteer> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.volunteer.UniqueVolunteerList // instanceof handles nulls
                && internalList.equals(((seedu.address.model.volunteer.UniqueVolunteerList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code volunteers} contains only unique volunteers.
     */
    private boolean volunteersAreUnique(List<Volunteer> volunteers) {
        for (int i = 0; i < volunteers.size() - 1; i++) {
            for (int j = i + 1; j < volunteers.size(); j++) {
                if (volunteers.get(i).isSameVolunteer(volunteers.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
