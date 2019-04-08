package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.project.ProjectTitle;

/**
 * Edits the details of an existing beneficiary in the address book.
 */
public class SummaryBeneficiaryCommand extends Command {

    public static final String COMMAND_WORD = "summariseB";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Summary statistics details of beneficiary list "
        + "based on projects attached to them";

    public static final String MESSAGE_SUMMARY_BENEFICIARY_SUCCESS = "Beneficiaries are summarised, and shown on "
        + "pop up table";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        return new CommandResult(String.format(MESSAGE_SUMMARY_BENEFICIARY_SUCCESS), true);
    }

    /**
     * Class to do summary of beneficiary
     */
    public static class SummarisedBeneficiary {

        private final String name;
        private final String numberOfProjects;
        private final List<String> projectList = new ArrayList<>();

        public SummarisedBeneficiary(Beneficiary beneficiary) {
            this.name = beneficiary.getName().fullName;
            for (ProjectTitle project : beneficiary.getAttachedProjectLists()) {
                this.projectList.add(project.fullTitle);
            }
            this.numberOfProjects = Integer.toString(this.projectList.size());
        }

        public String getName() {
            return name;
        }

        public String getNumberOfProjects() {
            return numberOfProjects;
        }

        public List<String> getProjectList() {
            return projectList;
        }
    }
}
