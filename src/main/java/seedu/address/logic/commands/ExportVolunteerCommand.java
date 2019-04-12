package seedu.address.logic.commands;

import javafx.util.Pair;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static java.util.Objects.requireNonNull;


public class ExportVolunteerCommand extends Command {
    public static final String COMMAND_WORD = "exportv";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " Exports list of volunteers into a CSV file. "
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
        File Output = new File("Export.csv");
        List<String[]> Volunteerdata = new ArrayList<>();
        Volunteerdata = model.addData(numVolunteers, prefixToBePrinted);
        try (PrintWriter pw = new PrintWriter(Output)) {
            Volunteerdata.stream()
                    .map(this::toCSV)
                    .forEach(pw::println);
        } catch (IOException e) {
            throw new CommandException("Error writing to file");
        }

        return new CommandResult("Data Written to Export.csv");

    }

    public String toCSV(String[] data) {
        return Stream.of(data)
                .collect(Collectors.joining(","));

    }
    /*
    public void givenDataArray_whenConvertToCSV_thenOutputCreated() throws IOException {
        File Output = new File("Exported.csv");
        try (PrintWriter pw = new PrintWriter(Output)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
        assertTrue(csvOutputFile.exists());
    }
    */
}
