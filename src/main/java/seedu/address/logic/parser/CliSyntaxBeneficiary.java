package seedu.address.logic.parser;

import seedu.address.model.beneficiary.Address;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.beneficiary.Email;
import seedu.address.model.beneficiary.Name;
import seedu.address.model.beneficiary.Phone;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntaxBeneficiary extends CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_INDEX = new Prefix("i/");
    public static final Prefix ASSIGNED_PROJECT_TITLE = new Prefix("p/");
    public static final Beneficiary NULL_BENEFICIARY = new Beneficiary(new Name("null"), new Phone("12345678"),
            new Email("null@null.com"), new Address("null"));
}
