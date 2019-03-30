package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

public class BeneficiaryAssigned {
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullName;

    public BeneficiaryAssigned(String beneficiaryAssigned) {
        requireNonNull(beneficiaryAssigned);
        this.fullName= beneficiaryAssigned;
    }
    @Override
    public String toString() {
        return fullName;
    }
}
