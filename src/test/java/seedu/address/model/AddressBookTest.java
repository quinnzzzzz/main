package seedu.address.model;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.project.Project;
import seedu.address.model.volunteer.Volunteer;

public class AddressBookTest {
    private final AddressBook addressBook = new AddressBook();
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getVolunteerList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        addressBook.resetData(null);
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Beneficiary> beneficiaries = FXCollections.observableArrayList();
        private final ObservableList<Project> projects = FXCollections.observableArrayList();
        private final ObservableList<Volunteer> volunteers = FXCollections.observableArrayList();

        @Override
        public ObservableList<Project> getProjectList() {
            return projects;
        }

        @Override
        public ObservableList<Beneficiary> getBeneficiaryList() {
            return beneficiaries;
        }

        @Override
        public ObservableList<Volunteer> getVolunteerList() {
            return volunteers;
        }

        @Override
        public void addListener(InvalidationListener listener) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeListener(InvalidationListener listener) {
            throw new AssertionError("This method should not be called.");
        }
    }

}
