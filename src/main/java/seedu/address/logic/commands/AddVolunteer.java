package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.volunteer.Volunteer;

public class AddVolunteer extends Command {

    public static final String COMMAND_WORD = "addVolunteer";

    public static final String MESSAGE_SUCCESS = "New volunteer added: %1$s";
    public static final String MESSAGE_DUPLICATE_VOLUNTEER = "This volunteer already exists in the address book";

    private final Volunteer toAdd;

    public AddVolunteer(Volunteer volunteer) {
        requireNonNull(volunteer);
        toAdd = volunteer;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasVolunteer(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_VOLUNTEER);
        }

        model.addVolunteer(toAdd);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddVolunteer // instanceof handles nulls
                && toAdd.equals(((AddVolunteer) other).toAdd));
    }
}

