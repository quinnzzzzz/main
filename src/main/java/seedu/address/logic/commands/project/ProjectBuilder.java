//@@author quinnzzzzz
package seedu.address.logic.commands.project;

import java.util.ArrayList;
import java.util.List;

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

    public static final String DEFAULT_PROJECT_TITLE = "Project Sunshine";
    public static final String DEFAULT_DATE = "08/11/2019";
    public static final String DEFAULT_COMPLETE = "false";
    public static final String DEFAULT_BENEFICIARY_ASSIGNED = "nil";

    private ProjectTitle projectTitle;
    private ProjectDate projectDate;
    private Complete complete;
    private Name beneficiaryAssigned;
    private List<Volunteer> volunteerList;

    public ProjectBuilder() {
        projectTitle = new ProjectTitle(DEFAULT_PROJECT_TITLE);
        projectDate = new ProjectDate(DEFAULT_DATE);
        complete = new Complete(DEFAULT_COMPLETE);
        beneficiaryAssigned = new Name(DEFAULT_BENEFICIARY_ASSIGNED);
        volunteerList = new ArrayList<>();
    }

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
     * @return a project
     */
    public Project build() {
        return new Project(projectTitle, projectDate, complete, beneficiaryAssigned, volunteerList);
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
     * Indicates the {@code Complete} status of the {@code Project} that we are building.
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
     * @param attachedVolunteers
     * @return ProjectBuilder
     */
    public ProjectBuilder withVolunteer(List<Volunteer> attachedVolunteers) {
        this.volunteerList = attachedVolunteers;
        return this;
    }
}
