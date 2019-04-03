package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;

/**
 * Add a project to completes
 */
public class CompleteCommand extends Command {
    public static final String COMMAND_WORD = "complete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Indicate a project as complete. "
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Project added to completes: %1$s";

    private final Index targetProjectIndex;

    public CompleteCommand(Index targetProject) {
        requireNonNull(targetProject);
        this.targetProjectIndex = targetProject;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireAllNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (targetProjectIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }
        Project targetProject = lastShownList.get(targetProjectIndex.getZeroBased());
        Project editedProject = new ProjectBuilder(targetProject).withComplete(true).build();
        model.setProject(targetProject, editedProject);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedProject.getProjectTitle()));
    }

    @Override
    public boolean equals(Object other) {
        return other==this // short circuit if same object
            || (other instanceof CompleteCommand // instanceof handles null
            && ((CompleteCommand) other).targetProjectIndex==this.targetProjectIndex);

    }
}
