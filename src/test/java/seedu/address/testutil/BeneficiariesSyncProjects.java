package seedu.address.testutil;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.AddressBook;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.project.Complete;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectDate;
import seedu.address.model.project.ProjectTitle;

/**
 * A utility class containing a list of {@code Beneficiary and Project} objects to be used in tests.
 */
public class BeneficiariesSyncProjects {
    public static final ProjectTitle ATTACH_TO_A_TITLE = new ProjectTitle("Attach to A");
    public static final ProjectTitle ATTACH_TO_B1_TITLE = new ProjectTitle("Attach to B no 1");
    public static final ProjectTitle ATTACH_TO_B2_TITLE = new ProjectTitle("Attach to B no 2");


    public static final Set<ProjectTitle> PROJECT_LIST_OF_A = new HashSet<>(Arrays.asList(ATTACH_TO_A_TITLE));
    public static final Set<ProjectTitle> PROJECT_LIST_OF_B = new HashSet<>(
        Arrays.asList(ATTACH_TO_B1_TITLE, ATTACH_TO_B2_TITLE));

    public static final Beneficiary BENEFICIARY_A = new BeneficiaryBuilder()
        .withName("Beneficiary A").withPhone("8482131")
        .withEmail("befA@example.com")
        .withAddress("chicago ave").buildWithProjectList(PROJECT_LIST_OF_A);

    public static final Beneficiary BENEFICIARY_B = new BeneficiaryBuilder()
        .withName("Beneficiary B").withPhone("8482131")
        .withEmail("befB@example.com")
        .withAddress("chicago ave").buildWithProjectList(PROJECT_LIST_OF_B);

    public static final Project ATTACHED_PROJECT_A = buildProjectStub(ATTACH_TO_A_TITLE, BENEFICIARY_A);
    public static final Project ATTACHED_PROJECT_B1 = buildProjectStub(ATTACH_TO_B1_TITLE, BENEFICIARY_B);
    public static final Project ATTACHED_PROJECT_B2 = buildProjectStub(ATTACH_TO_B2_TITLE, BENEFICIARY_B);

    public static Project buildProjectStub(ProjectTitle projectTitle, Beneficiary beneficiary) {
        String dateString = LocalDate.now().plus(2, ChronoUnit.DAYS).toString();
        String[] arrOfStr = dateString.split("-");
        String formattedDateString = arrOfStr[2] + "/" + arrOfStr[1] + "/" + arrOfStr[0];
        ProjectDate nextDay = new ProjectDate(formattedDateString);
        return new Project(projectTitle, nextDay, new Complete(false), beneficiary.getName());
    }

    /**
     * Returns an {@code AddressBook} with all the beneficiaries and projects
     * to test interaction if a beneficiary is modified.
     */
    public static AddressBook getAddressBookForBeneficiarySyncTest(
        List<Beneficiary> beneficiaries, List<Project> projects) {
        AddressBook ab = new AddressBook();
        for (Beneficiary beneficiary : beneficiaries) {
            ab.addBeneficiary(beneficiary);
        }
        for (Project project : projects) {
            ab.addProject(project);
        }
        return ab;
    }

    /**
     * original list of beneficiaries.
     *
     * @return List of Beneficiaries.
     */
    public static List<Beneficiary> getAandBBeneficiaries() {
        return new ArrayList<>(Arrays.asList(BENEFICIARY_B, BENEFICIARY_A));
    }

    /**
     * original list of projects.
     *
     * @return List of Projects.
     */
    public static List<Project> getProjectA2B() {
        return new ArrayList<>(Arrays.asList(ATTACHED_PROJECT_A, ATTACHED_PROJECT_B1, ATTACHED_PROJECT_B2));
    }

    /**
     * list of beneficiaries after hard delete B.
     *
     * @return List of Beneficiaries.
     */
    public static List<Beneficiary> getBeneficiariesAfterDeleteB() {
        return new ArrayList<>(Arrays.asList(BENEFICIARY_A));
    }

    /**
     * list of projects after hard delete B.
     *
     * @return List of Projects.
     */
    public static List<Project> getProjectAfterDeleteB1AndB2() {
        return new ArrayList<>(Arrays.asList(ATTACHED_PROJECT_A));
    }
}
