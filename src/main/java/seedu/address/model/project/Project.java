package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import seedu.address.model.beneficiary.Name;


/**
 * Represents a Project in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Project {
    // Identity fields
    private final ProjectTitle projectTitle;
    private final ProjectDate projectDate;
    private final Complete complete;
    private Name beneficiaryAssigned;

    /**
     * When complete and beneficiaryAssigned info are not initialised.
     */
    public Project(ProjectTitle projectTitle, ProjectDate projectDate) {
        requireAllNonNull(projectTitle, projectDate);
        this.projectTitle = projectTitle;
        this.projectDate = projectDate;
        //internal tags
        this.complete = new Complete(false);
        this.beneficiaryAssigned = new Name("nil");
    }

    /**
     * When beneficiaryAssigned is not initialised.
     */
    public Project(ProjectTitle projectTitle, ProjectDate projectDate,Complete complete) {
        requireAllNonNull(projectTitle, projectDate, complete);
        this.projectTitle = projectTitle;
        this.projectDate = projectDate;
        //internal tags
        this.complete = complete;
        this.beneficiaryAssigned = new Name("nil");;
    }

    public Project(ProjectTitle projectTitle, ProjectDate projectDate, Name beneficiaryAssigned) {
        requireAllNonNull(projectTitle, projectDate, beneficiaryAssigned);
        this.projectTitle = projectTitle;
        this.projectDate = projectDate;
        //internal tags
        this.complete = new Complete(false);
        this.beneficiaryAssigned = beneficiaryAssigned;
    }
    /**
     * Every field must be present and not null when all attributes can be passed in
     */
    public Project(ProjectTitle projectTitle, ProjectDate projectDate,Complete complete, Name beneficiaryAssigned) {
        requireAllNonNull(projectTitle, projectDate, complete, beneficiaryAssigned);
        this.projectTitle = projectTitle;
        this.projectDate = projectDate;
        //internal tags
        this.complete = complete;
        this.beneficiaryAssigned = beneficiaryAssigned;
    }


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

    /**
     * Returns true if Project has completed, else returns false.
     */
    public boolean isComplete() {
        return complete.isComplete();
    }

//    public void setAssignedBeneficiary(Name beneficiaryAssigned) {
//        this.beneficiaryAssigned = beneficiaryAssigned;
//    }
//    public void setComplete() {
//        this.complete = new Complete(true);
//    }
//
//    public BeneficiaryAssigned getBeneficiaryAttached() {
//
//    }
    public void setBeneficiary (Name beneficiary) {
        this.beneficiaryAssigned = beneficiary;
        System.out.println(beneficiary);
    }
//
//    public Name getBeneficiaryName(){
//        if (beneficiary == null) return new Name("null");
//        return beneficiary.getName();
//    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getProjectTitle())
                .append("Project Date: ")
                .append(getProjectDate())
                .append("Status ")
                .append(getComplete())
                .append(" Beneficiary: ")
                .append(getBeneficiaryAssigned())
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
                && otherProject.getProjectTitle().equals(getProjectTitle())
                || (otherProject.getProjectDate().equals(getProjectDate()));
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
        return Objects.hash(projectTitle,projectDate,complete,beneficiaryAssigned);
    }
}

