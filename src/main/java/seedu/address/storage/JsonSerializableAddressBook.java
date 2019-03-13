package seedu.address.storage;

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

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Beneficiarys list contains duplicate beneficiary(s).";

    private final List<JsonAdaptedBeneficiary> beneficiaries = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given beneficiaries.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("beneficiaries") List<JsonAdaptedBeneficiary> beneficiaries) {
        this.beneficiaries.addAll(beneficiaries);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        beneficiaries.addAll(source.getBeneficiaryList().stream().map(JsonAdaptedBeneficiary::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedBeneficiary jsonAdaptedBeneficiary : beneficiaries) {
            Beneficiary beneficiary = jsonAdaptedBeneficiary.toModelType();
            if (addressBook.hasBeneficiary(beneficiary)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addBeneficiary(beneficiary);
        }
        return addressBook;
    }

}
