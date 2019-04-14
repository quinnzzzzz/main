package guitests.guihandles;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMultiset;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.volunteer.Volunteer;

/**
 * Provides a handle to a volunteer card in the volunteer list panel.
 */
public class VolunteerCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#id";
    private static final String NAME_FIELD_ID = "#name";
    private static final String AGE_FIELD_ID = "#age";
    private static final String GENDER_FIELD_ID = "#gender";
    private static final String RACE_FIELD_ID = "#race";
    private static final String RELIGION_FIELD_ID = "#religion";
    private static final String ADDRESS_FIELD_ID = "#address";
    private static final String PHONE_FIELD_ID = "#phone";
    private static final String EMAIL_FIELD_ID = "#email";
    private static final String EMERGENCY_CONTACT_FIELD_ID = "#emergency contact";
    private static final String DIETARY_PREFERENCE_FIELD_ID = "#dietary preference";
    private static final String MEDICAL_CONDITION_FIELD_ID = "#medical condition";
    private static final String TAGS_FIELD_ID = "#tags";

    private final Label idLabel;
    private final Label nameLabel;
    private final Label ageLabel;
    private final Label genderLabel;
    private final Label raceLabel;
    private final Label religionLabel;
    private final Label addressLabel;
    private final Label phoneLabel;
    private final Label emailLabel;
    private final Label emergencyContactLabel;
    private final Label dietaryPreferenceLabel;
    private final Label medicalConditionLabel;
    private final List<Label> tagLabels;

    public VolunteerCardHandle(Node cardNode) {
        super(cardNode);

        idLabel = getChildNode(ID_FIELD_ID);
        nameLabel = getChildNode(NAME_FIELD_ID);
        ageLabel = getChildNode(AGE_FIELD_ID);
        genderLabel = getChildNode(GENDER_FIELD_ID);
        raceLabel = getChildNode(RACE_FIELD_ID);
        religionLabel = getChildNode(RELIGION_FIELD_ID);
        addressLabel = getChildNode(ADDRESS_FIELD_ID);
        phoneLabel = getChildNode(PHONE_FIELD_ID);
        emailLabel = getChildNode(EMAIL_FIELD_ID);
        emergencyContactLabel = getChildNode(EMERGENCY_CONTACT_FIELD_ID);
        dietaryPreferenceLabel = getChildNode(DIETARY_PREFERENCE_FIELD_ID);
        medicalConditionLabel = getChildNode(MEDICAL_CONDITION_FIELD_ID);


        Region tagsContainer = getChildNode(TAGS_FIELD_ID);
        tagLabels = tagsContainer
                .getChildrenUnmodifiable()
                .stream()
                .map(Label.class::cast)
                .collect(Collectors.toList());
    }

    public String getId() {
        return idLabel.getText();
    }

    public String getName() {
        return nameLabel.getText();
    }

    public String getAge() {
        return ageLabel.getText();
    }
    public String getGender() {
        return genderLabel.getText();
    }
    public String getRace() {
        return raceLabel.getText();
    }
    public String getReligion() {
        return religionLabel.getText();
    }
    public String getAddress() {
        return addressLabel.getText();
    }

    public String getPhone() {
        return phoneLabel.getText();
    }

    public String getEmail() {
        return emailLabel.getText();
    }

    public String getEmergencyContact() {
        return emergencyContactLabel.getText();
    }
    public String getDietaryPreference() {
        return dietaryPreferenceLabel.getText();
    }
    public String getMedicalCondition() {
        return medicalConditionLabel.getText();
    }

    public List<String> getTags() {
        return tagLabels
                .stream()
                .map(Label::getText)
                .collect(Collectors.toList());
    }

    /**
     * Returns true if this handle contains {@code volunteer}.
     */
    public boolean equals(Volunteer volunteer) {
        return getName().equals(volunteer.getName().fullName)
                && getAge().equals(volunteer.getAge().ageOutput)
                && getGender().equals(volunteer.getGender().genderOutput)
                && getRace().equals(volunteer.getRace().raceOutput)
                && getReligion().equals(volunteer.getReligion().religionOutput)
                && getPhone().equals(volunteer.getPhone().value)
                && getAddress().equals(volunteer.getAddress().value)
                && getEmail().equals(volunteer.getEmail().value)
                && getEmergencyContact().equals(volunteer.getEmergencyContact().value)
                && getDietaryPreference().equals(volunteer.getDietaryPreference().restriction)
                && getMedicalCondition().equals(volunteer.getMedicalCondition().status)
                && ImmutableMultiset.copyOf(getTags()).equals(ImmutableMultiset.copyOf(volunteer.getTags().stream()
                        .map(tag -> tag.tagName)
                        .collect(Collectors.toList())));
    }
}
