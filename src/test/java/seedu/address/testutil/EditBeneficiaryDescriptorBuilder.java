package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditBeneficiaryCommand.EditBeneficiaryDescriptor;
import seedu.address.model.beneficiary.Address;
import seedu.address.model.beneficiary.Email;
import seedu.address.model.beneficiary.Name;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.beneficiary.Phone;

/**
 * A utility class to help with building EditBeneficiaryDescriptor objects.
 */
public class EditBeneficiaryDescriptorBuilder {

    private EditBeneficiaryDescriptor descriptor;

    public EditBeneficiaryDescriptorBuilder() {
        descriptor = new EditBeneficiaryDescriptor();
    }

    public EditBeneficiaryDescriptorBuilder(EditBeneficiaryDescriptor descriptor) {
        this.descriptor = new EditBeneficiaryDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditBeneficiaryDescriptor} with fields containing {@code Beneficiary}'s details
     */
    public EditBeneficiaryDescriptorBuilder(Beneficiary Beneficiary) {
        descriptor = new EditBeneficiaryDescriptor();
        descriptor.setName(Beneficiary.getName());
        descriptor.setPhone(Beneficiary.getPhone());
        descriptor.setEmail(Beneficiary.getEmail());
        descriptor.setAddress(Beneficiary.getAddress());
    }

    /**
     * Sets the {@code Name} of the {@code EditBeneficiaryDescriptor} that we are building.
     */
    public EditBeneficiaryDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditBeneficiaryDescriptor} that we are building.
     */
    public EditBeneficiaryDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditBeneficiaryDescriptor} that we are building.
     */
    public EditBeneficiaryDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditBeneficiaryDescriptor} that we are building.
     */
    public EditBeneficiaryDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    public EditBeneficiaryDescriptor build() {
        return descriptor;
    }
}
