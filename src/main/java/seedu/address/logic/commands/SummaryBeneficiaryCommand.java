package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import java.util.List;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.ui.BeneficiaryStatsWindow;

/**
 * Edits the details of an existing beneficiary in the address book.
 */
public class SummaryBeneficiaryCommand extends Command {

    public static final String COMMAND_WORD = "summaryBeneficiary";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Summary statistics details of beneficiary list "
            + "based on projects attached to them";

    public static final String MESSAGE_SUMMARY_BENEFICIARY_SUCCESS = "Beneficiaries are summarised, and shown on "
            + "pop up table";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Beneficiary> lastShownList = model.getFilteredBeneficiaryList();

        return new CommandResult(String.format(MESSAGE_SUMMARY_BENEFICIARY_SUCCESS), true);
    }
}
