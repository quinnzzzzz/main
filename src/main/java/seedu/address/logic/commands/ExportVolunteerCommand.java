//@@author articstranger
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.util.Pair;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Takes the specified number of volunteers and writes the specified data to a CSV file
 */
public class ExportVolunteerCommand extends Command {
    public static final String COMMAND_WORD = "exportv";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " Exports list of volunteers into a CSV file.\n"
        + "If the number of volunteers specified is greater than the total number of volunteers,\n"
        + " all volunteers will be printed out.\n"
        + "Parameters: "
        + "Number of volunteers to be exported + "
        + "any number of valid volunteer prefixes e.g.(n/ y/ g/)\n"
        + "Example: " + COMMAND_WORD + " 10 "
        + "n/ y/ g/";


    private ArrayList<String> prefixToBePrinted;
    private int numVolunteers;

    public ExportVolunteerCommand(Pair<Integer, ArrayList<String>> numberAndprefixes) {
        requireNonNull(numberAndprefixes);
        numVolunteers = numberAndprefixes.getKey();
        prefixToBePrinted = numberAndprefixes.getValue();
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        File output = new File("Export.csv");
        List<String[]> volunteerData = new ArrayList<>();
        volunteerData = model.addData(numVolunteers, prefixToBePrinted);
        try (PrintWriter pw = new PrintWriter(output)) {
            volunteerData.stream()
                .map(this::toCsv)
                .forEach(pw::println);
        } catch (IOException e) {
            throw new CommandException("Error writing to file");
        }

        return new CommandResult("Data Written to Export.csv");

    }

    /**
     * formats string to csv format
     */
    public String toCsv(String[] data) {
        return Stream.of(data)
            .collect(Collectors.joining(","));

    }

}
