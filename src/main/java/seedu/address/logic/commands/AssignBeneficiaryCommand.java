package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntaxProject.PREFIX_INDEX;
import static seedu.address.logic.parser.ParserUtilProject.UNSPECIFIED_FIELD;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

//import seedu.address.commons.core.EventsCenter;
//import seedu.address.commons.core.Messages;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
//import seedu.address.commons.events.ui.HighlightSelectedProjectEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.beneficiary.UniqueBeneficiaryList;
import seedu.address.model.beneficiary.exceptions.DuplicateBeneficiaryException;
import seedu.address.model.project.ProjectTitle;
import seedu.address.model.project.exceptions.ProjectNotFoundException;

/**
 * Assigns a beneficiary to a project.
 */
public class AssignBeneficiaryCommand extends UndoableCommand {

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
    public static final String MESSAGE_FAILURE = "Not all beneficiaries have been successfully processed.";
    public static final String MESSAGE_DUPLICATE_BENEFICIARY =
            "\n%1$s: There is already the same beneficiary that exists in the project.";
    public static final String MESSAGE_NO_TEAM_TO_UNASSIGN = "\n%1$s: Cannot unassign beneficiary that is not in a project.";
    //public static final String MESSAGE_TEAM_TO_TEAM_SUCCESS = "\n%1$s has been assigned from %2$s to %3$s.";
    public static final String MESSAGE_UNSPECIFIED_TEAM_SUCCESS = "\n%1$s has been assigned to %2$s.";
    public static final String MESSAGE_UNASSIGN_TEAM_SUCCESS = "\n%1$s has been unassigned from %2$s.";

    private final ProjectTitle targetProject;
    private final List<Index> targetIndex;

    private List<Beneficiary> beneficiariesToAssign;

    /**
     * Creates an AssignBeneficiaryCommand to assign beneficiary to {@code Project}
     */
    public AssignBeneficiaryCommand(ProjectTitle targetProject, List<Index> targetIndex) {
        requireNonNull(targetProject);
        requireNonNull(targetIndex);

        this.targetProject = targetProject;
        this.targetIndex = targetIndex;
    }


    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        String successfulBeneficiaryAssignedMessage = new String();

        if (targetProject.toString().equals(UNSPECIFIED_FIELD)) {
            try {
                for (Beneficiary beneficiary : beneficiariesToAssign) {
                    model.unassignBeneficiaryFromProject(beneficiary);
                    successfulBeneficiaryAssignedMessage += String.format(MESSAGE_UNASSIGN_TEAM_SUCCESS,
                            beneficiary.hasProjectTitle(targetProject));
                }
            } catch (ProjectNotFoundException pnfe) {
                successfulBeneficiaryAssignedMessage += String.format(MESSAGE_NO_TEAM_TO_UNASSIGN, pnfe.getMessage());
                throw new CommandException(MESSAGE_FAILURE + successfulBeneficiaryAssignedMessage);
            }
        } else {
            try {
                for (Beneficiary beneficiary : beneficiariesToAssign) {
                    model.assignBeneficiaryToProject(beneficiary, targetProject);
                    if (beneficiary.hasProjectTitle(targetProject)) {
                        successfulBeneficiaryAssignedMessage += String.format(MESSAGE_UNSPECIFIED_TEAM_SUCCESS,
                                beneficiary.getName().toString(), targetProject.toString());
                    }
                }
                model.updateFilteredBeneficiaryList((Predicate<Beneficiary>) targetProject);
                //EventsCenter.getInstance().post(new HighlightSelectedProjectEvent(targetProject.toString()));
            } catch (DuplicateBeneficiaryException e) {
                successfulBeneficiaryAssignedMessage += String.format(MESSAGE_DUPLICATE_BENEFICIARY, e.getMessage());
                throw new CommandException(MESSAGE_FAILURE + successfulBeneficiaryAssignedMessage);
            } catch (ProjectNotFoundException pnfe) {
                throw new AssertionError("Impossible: Project should exist in VolunCHeer");
            }
        }
        model.commitAddressBook();
        return new CommandResult(MESSAGE_SUCCESS + successfulBeneficiaryAssignedMessage);
    }

    @Override
    protected void preprocessUndoableCommand() throws CommandException {

        if (!targetProject.toString().equals(UNSPECIFIED_FIELD)
                && !model.getAddressBook().getBeneficiaryList().stream().anyMatch(t -> t.getProjectTitle().equals(targetProject))) {
            throw new CommandException(Messages.MESSAGE_PROJECT_NOT_FOUND);
            List<Beneficiary> lastShownList = model.getFilteredBeneficiaryList();
            List<Index> executableIndexes = new ArrayList<>();
            Boolean canAssignBeneficiary = false;

            for (Index idx : targetIndex) {
                if (idx.getZeroBased() < lastShownList.size()) {
                    executableIndexes.add(idx);
                    canAssignBeneficiary = true;
                }
            }

            if (!canAssignBeneficiary) {
                throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
            }

            beneficiariesToAssign = new ArrayList<>();
            executableIndexes.forEach(idx -> beneficiariesToAssign.add(lastShownList.get(idx.getZeroBased())));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssignBeneficiaryCommand // instanceof handles nulls
                && this.targetProject.equals(((AssignBeneficiaryCommand) other).targetProject)) // state check
                && this.targetIndex.equals(((AssignBeneficiaryCommand) other).targetIndex);
    }
}

