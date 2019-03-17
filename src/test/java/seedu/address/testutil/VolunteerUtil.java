package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIETARY_PREFERENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddVolunteerCommand;
import seedu.address.logic.commands.EditVolunteerCommand.EditVolunteerDescriptor;
import seedu.address.model.volunteer.Volunteer;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Volunteer.
 */
public class VolunteerUtil {

    /**
     * Returns an add command string for adding the {@code volunteer}.
     */
    public static String getAddVolunteerCommand(Volunteer volunteer) {
        return AddVolunteerCommand.COMMAND_WORD + " " + getVolunteerDetails(volunteer);
    }

    /**
     * Returns the part of command string for the given {@code volunteer}'s details.
     */
    public static String getVolunteerDetails(Volunteer volunteer) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + volunteer.getName().fullName + " ");
        sb.append(PREFIX_AGE + volunteer.getAge().age_output + " ");
        sb.append(PREFIX_RACE + volunteer.getRace().race_output + " ");
        sb.append(PREFIX_PHONE + volunteer.getPhone().value + " ");
        sb.append(PREFIX_ADDRESS + volunteer.getAddress().value + " ");
        sb.append(PREFIX_EMAIL + volunteer.getEmail().value + " ");
        sb.append(PREFIX_EMERGENCY_CONTACT + volunteer.getEmergency_contact().value + " ");
        sb.append(PREFIX_DIETARY_PREFERENCE + volunteer.getDietary_preference().restriction + " ");
        sb.append(PREFIX_MEDICAL_CONDITION + volunteer.getMedical_condition().status + " ");
        volunteer.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditVolunteerDescriptor}'s details.
     */
    public static String getEditVolunteerDescriptorDetails(EditVolunteerDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getAge().ifPresent(age -> sb.append(PREFIX_AGE).append(age.age_output).append(" "));
        descriptor.getRace().ifPresent(race -> sb.append(PREFIX_RACE).append(race.race_output).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getEmergency_contact().ifPresent(emergency_contact -> sb.append(PREFIX_EMERGENCY_CONTACT).append(emergency_contact.value).append(" "));
        descriptor.getDietary_preference().ifPresent(dietary_preference -> sb.append(PREFIX_DIETARY_PREFERENCE).append(dietary_preference.restriction).append(" "));
        descriptor.getMedical_condition().ifPresent(medical_condition -> sb.append(PREFIX_MEDICAL_CONDITION).append(medical_condition.status).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
