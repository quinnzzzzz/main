package seedu.address.model;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.project.Project;
import seedu.address.model.volunteer.Volunteer;

/**
 * Unmodifiable view of an address book
 */

public interface ReadOnlyAddressBook extends Observable {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Beneficiary> getBeneficiaryList();

    ObservableList<Project> getProjectList();

    ObservableList<Volunteer> getVolunteerList();
}
