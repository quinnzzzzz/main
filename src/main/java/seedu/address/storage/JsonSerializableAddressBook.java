package seedu.address.storage;

import static seedu.address.logic.commands.EditBeneficiaryCommand.MESSAGE_DUPLICATE_BENEFICIARY;
import static seedu.address.logic.commands.EditVolunteerCommand.MESSAGE_DUPLICATE_VOLUNTEER;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.person.Person;
import seedu.address.model.volunteer.Volunteer;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_BENEFICIARY = "Beneficiary list contains duplicate beneficiary(es).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedBeneficiary> beneficiaries = new ArrayList<>();
    private final List<JsonAdaptedVolunteer> volunteers = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
        this.beneficiaries.addAll(beneficiaries);
        this.volunteers.addAll(volunteers);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        beneficiaries.addAll(source.getBeneficiaryList().stream().map(JsonAdaptedBeneficiary::new)
                .collect(Collectors.toList()));
        volunteers.addAll(source.getVolunteerList().stream().map(JsonAdaptedVolunteer::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        for (JsonAdaptedBeneficiary jsonAdaptedBeneficiary : beneficiaries) {
            Beneficiary beneficiary = jsonAdaptedBeneficiary.toModelType();
            if (addressBook.hasBeneficiary(beneficiary)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_BENEFICIARY);
            }
            addressBook.addBeneficiary(beneficiary);
        }
        for (JsonAdaptedVolunteer jsonAdaptedVolunteer : volunteers) {
            Volunteer volunteer = jsonAdaptedVolunteer.toModelType();
            if (addressBook.hasVolunteer(volunteer)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_VOLUNTEER);
            }
            addressBook.addVolunteer(volunteer);
        }
        return addressBook;
    }
}
