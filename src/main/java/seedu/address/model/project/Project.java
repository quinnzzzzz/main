package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.beneficiary.Name;
import seedu.address.model.beneficiary.UniqueBeneficiaryList;
import seedu.address.model.beneficiary.exceptions.DuplicateBeneficiaryException;
import seedu.address.model.volunteer.Volunteer;


/**
 * Represents a Project in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Project {
    // Identity fields
    private final ProjectTitle projectTitle;
    private final ProjectDate projectDate;

    private Complete complete;
    private Beneficiary beneficiary;

    /**
     * Every field must be present and not null.
     */
    public Project(ProjectTitle projectTitle, ProjectDate projectDate) {
        requireAllNonNull(projectTitle, projectDate);
        this.projectTitle = projectTitle;
        this.projectDate = projectDate;
        //internal tags
        this.complete = new Complete(false);
        this.beneficiary = null;
    }

    public void setAssignedBeneficiary(Beneficiary beneficiaryAssigned) {
        this.beneficiary = beneficiaryAssigned;
    }
    public void setComplete() {
        this.complete = new Complete(true);
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

    public Beneficiary getBeneficiaryAttached() {
        return this.beneficiary;
    }

    public Name getBeneficiaryName(){
        if (beneficiary == null) return new Name("null");
        return beneficiary.getName();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getProjectTitle())
                .append(", ")
                .append(getProjectDate())
                .append(" Beneficiary: ")
                .append(getBeneficiaryAttached())
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
                && (otherProject.getProjectDate().equals(getProjectDate()));
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
                && otherProject.getProjectDate().equals(getProjectDate());
    }

    public void setBeneficiary (Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }

    public Beneficiary getBeneficiary () {
        return beneficiary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectTitle,projectDate,complete,beneficiary);
    }
}

