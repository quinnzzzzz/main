package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    /**
     * Adds a new {@code Project} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withProject(Project project) {
        addressBook.addProject(project);
        return this;
    }

    /**
     * Adds a new {@code Beneficiary} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withBeneficiary(Beneficiary beneficiary) {
        addressBook.addBeneficiary(beneficiary);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
