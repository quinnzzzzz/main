package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntaxProject.PREFIX_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.ProjectTitle;

/**
 * Assigns a beneficiary to a project.
 */
public class AssignBeneficiaryCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assigns a beneficiary to a project "
            + "More than 1 project can be assigned to each beneficiary.\n"
            + "Parameters: "
            + "[PROJECT_TITLE] "
            + "[INDEX]...\n"
            + "Example: " + COMMAND_WORD + " "
            + "Old Folks Home ";

    public static final String MESSAGE_PARAMETERS = "[PROJECT_TITLE] "
            + PREFIX_INDEX + "INDEX ";

    public static final String MESSAGE_SUCCESS = "Beneficiary successfully assigned to project.";
    public static final String MESSAGE_DUPLICATE_BENEFICIARY =
            "There is already the same beneficiary that exists in the project.";

    private final ProjectTitle targetProject;
    private final Index targetBeneficiaryIndex;

    /**
     * Creates an AssignBeneficiaryCommand to assign beneficiary to {@code Project}
     */
    public AssignBeneficiaryCommand(ProjectTitle targetProject, Index targetBeneficiary) {
        requireNonNull(targetProject);
        requireNonNull(targetBeneficiary);

        this.targetProject = targetProject;
        this.targetBeneficiaryIndex = targetBeneficiary;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        if (model.checkBeneficiary(targetBeneficiaryIndex, targetProject)){
            return new CommandResult(MESSAGE_DUPLICATE_BENEFICIARY);
        }
        else {
            return new CommandResult(MESSAGE_DUPLICATE_BENEFICIARY);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssignBeneficiaryCommand // instanceof handles nulls
                && this.targetProject.equals(((AssignBeneficiaryCommand) other).targetProject)) // state check
                && this.targetBeneficiaryIndex.equals(((AssignBeneficiaryCommand) other).targetBeneficiaryIndex);
    }
}

