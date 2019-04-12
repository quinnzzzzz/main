package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntaxBeneficiary extends CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_INDEX = new Prefix("i/");
    public static final Prefix ASSIGNED_PROJECT_TITLE = new Prefix("p/");
    public static final Prefix HARD_DELETE_MODE = new Prefix("-D");
}
