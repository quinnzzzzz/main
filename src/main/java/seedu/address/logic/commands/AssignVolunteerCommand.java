//@@author quinnzzzzz
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntaxBeneficiary.ASSIGNED_PROJECT_TITLE;
import static seedu.address.logic.parser.CliSyntaxVolunteer.PREFIX_REQUIRED_VOLUNTEER;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectTitle;
import seedu.address.model.volunteer.Volunteer;

/**
 * Assigns a beneficiary to a project.
 */
public class AssignVolunteerCommand extends Command {

    public static final String COMMAND_WORD = "assignV";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assigns volunteers to a project,"
            + "at least 1 volunteer should be assigned.\n"
            + "More than 1 project can be assigned to each volunteer.\n"
            + "Parameters: "
            + ASSIGNED_PROJECT_TITLE
            + "[PROJECT_TITLE] "
            + PREFIX_REQUIRED_VOLUNTEER + "5"
            + "Example: " + COMMAND_WORD + " "
            + "p/Project Sunshine"
            + "rv/5";

    public static final String MESSAGE_PARAMETERS = ASSIGNED_PROJECT_TITLE + "[PROJECT_TITLE] "
            + PREFIX_REQUIRED_VOLUNTEER + "INDEX ";
    public static final String MESSAGE_SUCCESS = "Volunteer/s successfully assigned to project.";

    private final ProjectTitle targetProject;
    private final Integer requiredVolunteers;

    private List<Volunteer> volunteersToAssign;
    private Project editedProject;
    private Project projectToAssign;

    /**
     * Creates an AssignBeneficiaryCommand to assign beneficiary to {@code Project}
     */
    public AssignVolunteerCommand(ProjectTitle targetProject, int requiredVolunteers) {
        requireNonNull(targetProject);
        requireNonNull(requiredVolunteers);

        this.targetProject = targetProject;
        this.requiredVolunteers = requiredVolunteers;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Volunteer> lastShownList = model.getFilteredVolunteerList();
        Predicate<Project> equalProjectTitle = x -> x.getProjectTitle().equals(targetProject);
        if (model.getFilteredProjectList().filtered(equalProjectTitle).size() == 0) {
            throw new CommandException("Project does not exist.");
        } else {
            projectToAssign = model.getFilteredProjectList().filtered(equalProjectTitle).get(0);
            if (requiredVolunteers == 1) {
                volunteersToAssign = lastShownList.subList(0, 1);
            } else {
                if (requiredVolunteers > lastShownList.size()) {
                    throw new CommandException(Messages.MESSAGE_NOT_ENOUGH_VOLUNTEERS);
                } else if (requiredVolunteers == lastShownList.size()) {
                    volunteersToAssign = lastShownList;
                } else {
                    volunteersToAssign = lastShownList.subList(0, (requiredVolunteers + 1));
                    for (Volunteer volunteer : volunteersToAssign) {
                        if (!volunteer.hasProjectTitle(targetProject)) {
                            volunteer.addAttachedProject(targetProject);
                        }
                        editedProject.addAttachedVolunteer(volunteer);
                    }
                }
            }
        }
        this.editedProject = new ProjectBuilder(this.projectToAssign).withVolunteer(volunteersToAssign).build();
        model.setProject(projectToAssign, editedProject);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof AssignVolunteerCommand
                && this.targetProject.equals(((AssignVolunteerCommand) other).targetProject));
    }
}
