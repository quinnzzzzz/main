package seedu.address.logic.parser.project;

import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.Prefix;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntaxProject extends CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_PROJECT_TITLE = new Prefix("n/");
    public static final Prefix PREFIX_INDEX = new Prefix("i/");
    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_YEAR = new Prefix("y/");
    public static final Prefix PREFIX_MEDICAL = new Prefix("m/");
}

