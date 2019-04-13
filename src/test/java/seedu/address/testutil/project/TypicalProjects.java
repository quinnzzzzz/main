package seedu.address.testutil.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.project.Project;
import seedu.address.logic.commands.project.ProjectBuilder;

/**
 * A utility class containing a list of {@code Project} objects to be used in tests.
 */
public class TypicalProjects {
    public static final Project PROJECT1 = new ProjectBuilder().withProjectTitle("Sunshine")
            .withProjectDate("16/01/2020").withComplete(false).build();
    public static final Project PROJECT2 = new ProjectBuilder().withProjectTitle("iReject")
            .withProjectDate("12/08/2019").withComplete(false).build();

    //manually added
    public static final Project PROJECT3 = new ProjectBuilder().withProjectTitle("Save the Earth")
            .withProjectDate("02/04/2020").withComplete(false).build();

    private TypicalProjects() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Project project : getTypicalProjects()) {
            ab.addProject(project);
        }
        return ab;
    }

    public static List<Project> getTypicalProjects() {
        return new ArrayList<>(Arrays.asList(PROJECT1,PROJECT2));
    }
}
