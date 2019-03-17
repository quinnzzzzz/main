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
import seedu.address.model.volunteer.Volunteer;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_VOLUNTEER = "Volunteers list contains duplicate volunteer(s).";

    private final List<JsonAdaptedVolunteer> volunteers = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given volunteers.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("volunteers") List<JsonAdaptedVolunteer> volunteers) {
        this.volunteers.addAll(volunteers);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        volunteers.addAll(source.getVolunteerList().stream().map(JsonAdaptedVolunteer::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
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
