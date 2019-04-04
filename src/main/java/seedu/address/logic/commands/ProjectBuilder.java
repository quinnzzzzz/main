package seedu.address.logic.commands;

import seedu.address.model.beneficiary.Name;
import seedu.address.model.project.Complete;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectDate;
import seedu.address.model.project.ProjectTitle;
import seedu.address.model.volunteer.Volunteer;

/**
 * Build Project
 */
public class ProjectBuilder {

    public static final String DEFAULT_PROJECT_TITLE = "Project Title";
    public static final String DEFAULT_PROJECT_DATE = "20190401";
    public static final String DEFAULT_COMPLETE = "false";

    private ProjectTitle projectTitle;
    private ProjectDate projectDate;
    private Complete complete;
    private Name beneficiaryAssigned;
    private List<Volunteer> volunteerList;

    /**
     * Initialises the ProjectBuilder with the data of {@code projectToCopy}.
     */
    public ProjectBuilder(Project projectToCopy) {
        projectTitle = projectToCopy.getProjectTitle();
        projectDate = projectToCopy.getProjectDate();
        complete = projectToCopy.getComplete();
        beneficiaryAssigned = projectToCopy.getBeneficiaryAssigned();
        volunteerList = projectToCopy.getVolunteerList();
    }

    /**
     * Sets the {@code ProjectTitle} of the {@code Project} that we are building.
     */
    public ProjectBuilder withProjectTitle(String projectTitle) {
        this.projectTitle = new ProjectTitle(projectTitle);
        return this;
    }

    /**
     * Sets the {@code ProjectDate} of the {@code Project} that we are building.
     */
    public ProjectBuilder withProjectDate(String projectDate) {
        this.projectDate = new ProjectDate(projectDate);
        return this;
    }

    /**
     * @return a project
     */
    public Project build() {
        return new Project(projectTitle, projectDate, complete, beneficiaryAssigned, volunteerList);
    }

    /**
     * Sets the {@code ProjectTitle} of the {@code Project} that we are building.
     */
    public ProjectBuilder withComplete(Boolean complete) {
        this.complete = new Complete(complete);
        return this;
    }

    /**
     * @param beneficiary
     * @return ProjectBuilder
     */
    public ProjectBuilder withBeneficiary(Name beneficiary) {
        this.beneficiaryAssigned = beneficiary;
        return this;
    }
    /**
     * @param volunteerList
     * @return ProjectBuilder
     */
    public ProjectBuilder withVolunteer(List<Volunteer> volunteerList) {
        this.volunteerList = volunteerList;
        return this;
    }

}
