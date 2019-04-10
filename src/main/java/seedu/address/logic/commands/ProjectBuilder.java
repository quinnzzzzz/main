//@@author quinnzzzzz
package seedu.address.logic.commands;

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
     * @param attachedVolunteers
     * @return ProjectBuilder
     */
    public ProjectBuilder withVolunteer(List<Volunteer> attachedVolunteers) {
        this.volunteerList = attachedVolunteers;
        return this;
    }
}
