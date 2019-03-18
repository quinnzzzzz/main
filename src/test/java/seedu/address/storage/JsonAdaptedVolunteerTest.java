package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static seedu.address.storage.JsonAdaptedVolunteer.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.TypicalVolunteers.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.volunteer.Name;
import seedu.address.model.volunteer.Age;
import seedu.address.model.volunteer.Race;
import seedu.address.model.volunteer.Phone;
import seedu.address.model.volunteer.Address;
import seedu.address.model.volunteer.Email;
import seedu.address.model.volunteer.Emergency_contact;
import seedu.address.model.volunteer.Dietary_preference;
import seedu.address.model.volunteer.Medical_condition;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.Assert;

public class JsonAdaptedVolunteerTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_AGE = "abc";
    private static final String INVALID_RACE = "@gg";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_DIETARY_PRFERENCE = "222";
    private static final String INVALID_EMERGENCY_CONTACT_= "123444B";
    private static final String INVALID_MEDICAL_CONDITION = "21";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_AGE = BENSON.getAge().toString();
    private static final String VALID_RACE = BENSON.getRace().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_EMERGENCY_CONTACT = BENSON.getEmergency_contact().toString();
    private static final String VALID_DIETARY_PREFERENCE = BENSON.getDietary_preference().toString();
    private static final String VALID_MEDICAL_CONDITION = BENSON.getMedical_condition().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validVolunteerDetails_returnsVolunteer() throws Exception {
        JsonAdaptedVolunteer volunteer = new JsonAdaptedVolunteer(BENSON);
        assertEquals(BENSON, volunteer.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedVolunteer volunteer =
                new JsonAdaptedVolunteer(INVALID_NAME, VALID_AGE, VALID_RACE, VALID_PHONE, VALID_ADDRESS, VALID_EMAIL, VALID_EMERGENCY_CONTACT, VALID_DIETARY_PREFERENCE, VALID_MEDICAL_CONDITION, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, volunteer::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedVolunteer volunteer = new JsonAdaptedVolunteer(null,VALID_AGE, VALID_RACE, VALID_PHONE, VALID_ADDRESS, VALID_EMAIL, VALID_EMERGENCY_CONTACT, VALID_DIETARY_PREFERENCE, VALID_MEDICAL_CONDITION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, volunteer::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedVolunteer volunteer =
                new JsonAdaptedVolunteer(VALID_NAME,VALID_AGE, VALID_RACE, INVALID_PHONE, VALID_ADDRESS, VALID_EMAIL,VALID_EMERGENCY_CONTACT, VALID_DIETARY_PREFERENCE, VALID_MEDICAL_CONDITION, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, volunteer::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedVolunteer volunteer = new JsonAdaptedVolunteer(VALID_NAME,VALID_AGE, VALID_RACE, null, VALID_ADDRESS, VALID_EMAIL, VALID_EMERGENCY_CONTACT, VALID_DIETARY_PREFERENCE, VALID_MEDICAL_CONDITION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, volunteer::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedVolunteer volunteer =
                new JsonAdaptedVolunteer(VALID_NAME,VALID_AGE, VALID_RACE, VALID_PHONE, VALID_ADDRESS, INVALID_EMAIL,  VALID_EMERGENCY_CONTACT, VALID_DIETARY_PREFERENCE, VALID_MEDICAL_CONDITION, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, volunteer::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedVolunteer volunteer = new JsonAdaptedVolunteer(VALID_NAME,VALID_AGE, VALID_RACE, VALID_PHONE,VALID_ADDRESS, null,VALID_EMERGENCY_CONTACT, VALID_DIETARY_PREFERENCE, VALID_MEDICAL_CONDITION,  VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, volunteer::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedVolunteer volunteer =
                new JsonAdaptedVolunteer(VALID_NAME,VALID_AGE, VALID_RACE, VALID_PHONE,INVALID_ADDRESS, VALID_EMAIL, VALID_EMERGENCY_CONTACT, VALID_DIETARY_PREFERENCE, VALID_MEDICAL_CONDITION, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, volunteer::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedVolunteer volunteer = new JsonAdaptedVolunteer(VALID_NAME,VALID_AGE, VALID_RACE, VALID_PHONE, null, VALID_EMAIL,VALID_EMERGENCY_CONTACT, VALID_DIETARY_PREFERENCE, VALID_MEDICAL_CONDITION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, volunteer::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedVolunteer volunteer =
                new JsonAdaptedVolunteer(VALID_NAME,VALID_AGE, VALID_RACE, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_EMERGENCY_CONTACT, VALID_DIETARY_PREFERENCE, VALID_MEDICAL_CONDITION, invalidTags);
        Assert.assertThrows(IllegalValueException.class, volunteer::toModelType);
    }

}
