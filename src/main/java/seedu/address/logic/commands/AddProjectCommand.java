package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;

/**
 * Adds a new project to VolunCHeer.
 */
public class AddProjectCommand extends Command {

    public static final String COMMAND_WORD = "addProject";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new project to VolunCHeer. "
            + "Parameters: "
            + PREFIX_PROJECTTITLE + "NAME "
            + PREFIX_DATE + "DATE "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Charity Run "
            + PREFIX_DATE + "020319 "
            + PREFIX_BENEFICIARY + "Bright Primary School";

    public static final String MESSAGE_SUCCESS = "New project added: %1$s";
    //public static final String MESSAGE_DUPLICATE_PROJECT = "This project already exists in VolunCHeer";

    private final Project toAddProject;

    /**
     * Creates an AddCommand to add the specified {@code Project}
     */
    public AddProjectCommand(Project project) {
        requireNonNull(project);
        toAddProject = project;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        model.addProject(toAddProject);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAddProject));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddProjectCommand // instanceof handles nulls
                && toAddProject.equals(((AddProjectCommand) other).toAddProject));
    }
}

