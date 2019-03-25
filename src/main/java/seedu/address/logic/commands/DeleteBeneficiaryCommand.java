package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.beneficiary.Beneficiary;

/**
 * Deletes a beneficiary identified using it's displayed index from the address book.
 */
public class DeleteBeneficiaryCommand extends Command {

    public static final String COMMAND_WORD = "deleteBeneficiary";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the beneficiary identified by the index number used in the displayed beneficiary list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_BENEFICIARY_SUCCESS = "Deleted Beneficiary: %1$s";

    public static final String MESSAGE_BENEFICIARY_HAS_PROJECTS_ATTACHED = "%1$s has this/these projects: %2$s"
            + " attached to it, please delete them before delete the beneficiary.";

    private final Index targetIndex;

    public DeleteBeneficiaryCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute() throws CommandException {
        requireNonNull(model);
        List<Beneficiary> lastShownList = model.getFilteredBeneficiaryList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BENEFICIARY_DISPLAYED_INDEX);
        }

        Beneficiary beneficiaryToDelete = lastShownList.get(targetIndex.getZeroBased());
        if (beneficiaryToDelete.hasAttachedProjects()) {
            throw new CommandException(String.format(MESSAGE_BENEFICIARY_HAS_PROJECTS_ATTACHED,
                    beneficiaryToDelete.getName(),
                    beneficiaryToDelete.getAttachedProjectLists()));
        }

        model.deleteBeneficiary(beneficiaryToDelete);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_DELETE_BENEFICIARY_SUCCESS, beneficiaryToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteBeneficiaryCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteBeneficiaryCommand) other).targetIndex)); // state check
    }
}
