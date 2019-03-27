package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;

import seedu.address.model.beneficiary.Beneficiary;


/**
 * Represents a Project in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Project extends UniqueProjectList{

    // Identity fields
    private final ProjectTitle projectTitle;
    private final ProjectDate projectDate;

    /**
     * Every field must be present and not null.
     */
    public Project(ProjectTitle projectTitle, ProjectDate projectDate) {
        requireAllNonNull(projectTitle, projectDate);
        this.projectTitle = projectTitle;
        this.projectDate = projectDate;
    }

    public ProjectTitle getProjectTitle() {
        return projectTitle;
    }

    public ProjectDate getProjectDate() {
        return projectDate;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getProjectTitle())
                .append(", ")
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
                && otherProject.getProjectTitle().equals(getProjectTitle())
                && (otherProject.getProjectDate().equals(getProjectDate()));
    }

//    public void setBeneficiary(Beneficiary attachedBeneficiary) {
//        this.attachedProjectList.addAll(projectList);
//    }
    /**
     * Returns true if both Projects have the same identity and data fields.
     * This defines a stronger notion of equality between two Projects.
     */
    @Override
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

}
