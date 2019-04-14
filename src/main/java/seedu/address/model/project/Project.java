//@@author quinnzzzzz
package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.model.beneficiary.Name;
import seedu.address.model.volunteer.Volunteer;

/**
 * Represents a Project in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Project {
    // Identity fields
    private final ProjectTitle projectTitle;
    private final ProjectDate projectDate;
    // Data fields
    private Complete complete;
    private Name beneficiaryAssigned;
    private List<Volunteer> volunteerList;

    /**
     * When complete, beneficiaryAssigned and volunteerAttached info are not initialised.
     */
    public Project(ProjectTitle projectTitle, ProjectDate projectDate) {
        requireAllNonNull(projectTitle, projectDate);
        this.projectTitle = projectTitle;
        this.projectDate = projectDate;
        //internal tags
        this.complete = new Complete(false);
        this.beneficiaryAssigned = new Name("nil");
        this.volunteerList = new ArrayList<>();
    }

    /**
     * When beneficiaryAssigned and volunteerAttached info are not initialised.
     */
    public Project(ProjectTitle projectTitle, ProjectDate projectDate, Complete complete) {
        requireAllNonNull(projectTitle, projectDate, complete);
        this.projectTitle = projectTitle;
        this.projectDate = projectDate;
        //internal tags
        this.complete = complete;
        this.beneficiaryAssigned = new Name("nil");
        this.volunteerList = new ArrayList<>();
    }

    /**
     * When volunteerAttached info is not initialised.
     */
    public Project(ProjectTitle projectTitle, ProjectDate projectDate, Complete complete, Name beneficiaryAssigned) {
        requireAllNonNull(projectTitle, projectDate, beneficiaryAssigned);
        this.projectTitle = projectTitle;
        this.projectDate = projectDate;
        //internal tags
        this.complete = complete;
        this.beneficiaryAssigned = beneficiaryAssigned;
        this.volunteerList = new ArrayList<>();
    }

    /**
     * When beneficiaryAssigned info is not initialised.
     */
    public Project(ProjectTitle projectTitle, ProjectDate projectDate, List<Volunteer> volunteerList) {
        requireAllNonNull(projectTitle, projectDate, volunteerList);
        this.projectTitle = projectTitle;
        this.projectDate = projectDate;
        //internal tags
        this.complete = new Complete(false);
        this.beneficiaryAssigned = new Name("nil");
        this.volunteerList = volunteerList;
    }

    /**
     * Every field must be present and not null when all attributes can be passed in
     */
    public Project(ProjectTitle projectTitle, ProjectDate projectDate, Complete complete, Name beneficiaryAssigned,
                   List<Volunteer> volunteerList) {
        requireAllNonNull(projectTitle, projectDate, complete, beneficiaryAssigned, volunteerList);
        this.projectTitle = projectTitle;
        this.projectDate = projectDate;
        //internal tags
        this.complete = complete;
        this.beneficiaryAssigned = beneficiaryAssigned;
        this.volunteerList = volunteerList;
    }

    /**
     * get methods to obtain various attributes of Project
     *
     * @return attribute
     */
    public ProjectTitle getProjectTitle() {
        return projectTitle;
    }

    public ProjectDate getProjectDate() {
        return projectDate;
    }

    public Complete getComplete() {
        return complete;
    }

    public Name getBeneficiaryAssigned() {
        return beneficiaryAssigned;
    }

    public Integer getVolunteerCount() {
        return volunteerList.size();
    }

    public List<Volunteer> getVolunteerList() {
        return volunteerList;
    }

    //Set the list of volunteers attached to project
    public void setVolunteerList(List<Volunteer> volunteerList) {
        this.volunteerList.addAll(volunteerList);
    }

    /**
     * Returns true if Project has completed, else returns false.
     */
    public boolean isComplete() {
        return complete.isComplete();
    }

    //Set the beneficiary attached to project
    public void setBeneficiary(Name beneficiary) {
        this.beneficiaryAssigned = beneficiary;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getProjectTitle())
            .append(getProjectDate())
            .append("\n");
        return builder.toString();
    }

    /**
     * Returns true if both Projects of the same projectTitle have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two Projects.
     */
    public boolean isSameProject(Project otherProject) {
        if (otherProject == this) {
            return true;
        }

        return otherProject != null
            && otherProject.getProjectTitle().equals(getProjectTitle());
    }

    /**
     * Returns true if both Projects have the same identity and data fields.
     * This defines a stronger notion of equality between two Projects.
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Project)) {
            return false;
        }
        Project otherProject = (Project) other;
        return otherProject.getProjectTitle().equals(getProjectTitle())
            || otherProject.getProjectDate().equals(getProjectDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectTitle, projectDate, complete, beneficiaryAssigned);
    }
}

