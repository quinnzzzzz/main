package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

public class UniqueVolunteerList implements Iterable<Volunteer> {

        private final ObservableList<Volunteer> internalList = FXCollections.observableArrayList();
        private final ObservableList<Volunteer> internalUnmodifiableList =
                FXCollections.unmodifiableObservableList(internalList);

        /**
         * Returns true if the list contains an equivalent person as the given argument.
         */
        public boolean contains(Volunteer toCheck) {
            requireNonNull(toCheck);
            return internalList.stream().anyMatch(toCheck::isSameVolunteer);
        }

        /**
         * Adds a person to the list.
         * The person must not already exist in the list.
         */
        public void add(Volunteer toAdd) {
            requireNonNull(toAdd);
            if (contains(toAdd)) {
                throw new DuplicatePersonException();
            }
            internalList.add(toAdd);
        }

        /**
         * Replaces the person {@code target} in the list with {@code editedPerson}.
         * {@code target} must exist in the list.
         * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
         */
        public void setVolunteer(Volunteer target, Volunteer editedVolunteer) {
            requireAllNonNull(target, editedVolunteer);

            int index = internalList.indexOf(target);
            if (index == -1) {
                throw new PersonNotFoundException();
            }

            if (!target.isSameVolunteer(editedVolunteer) && contains(editedVolunteer)) {
                throw new DuplicatePersonException();
            }

            internalList.set(index, editedVolunteer);
        }

        /**
         * Removes the equivalent person from the list.
         * The person must exist in the list.
         */
        public void remove(Person toRemove) {
            requireNonNull(toRemove);
            if (!internalList.remove(toRemove)) {
                throw new PersonNotFoundException();
            }
        }

        public void setVolunteers(seedu.address.model.person.UniqueVolunteerList replacement) {
            requireNonNull(replacement);
            internalList.setAll(replacement.internalList);
        }

        /**
         * Replaces the contents of this list with {@code persons}.
         * {@code persons} must not contain duplicate persons.
         */
        public void setVolunteers(List<Volunteer> volunteers) {
            requireAllNonNull(volunteers);
            if (!volunteersAreUnique(volunteers)) {
                throw new DuplicatePersonException();
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
                    || (other instanceof seedu.address.model.person.UniqueVolunteerList // instanceof handles nulls
                    && internalList.equals(((seedu.address.model.person.UniqueVolunteerList) other).internalList));
        }

        @Override
        public int hashCode() {
            return internalList.hashCode();
        }

        /**
         * Returns true if {@code persons} contains only unique persons.
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
