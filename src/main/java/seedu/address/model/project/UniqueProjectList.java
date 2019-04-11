package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.project.exceptions.DuplicateProjectException;
import seedu.address.model.project.exceptions.ProjectNotFoundException;

/**
 * A list of projects that enforces uniqueness between its elements and does not allow nulls.
 * A project is considered unique by comparing using {@code Project#isSameProject(Project)}.
 * As such, adding and updating of
 * projects uses Project#isSameProject(Project) for equality so as to ensure that the project being added or updated is
 * unique in terms of identity in the UniqueProjectList. However, the removal of a project uses Project#equals(Object)
 * so as to ensure that the project with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Project#isSameProject(Project)
 */
public class UniqueProjectList implements Iterable<Project> {

    private final ObservableList<Project> internalList = FXCollections.observableArrayList();
    private final ObservableList<Project> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);
    private Map<ProjectTitle, Project> projectTitleProjectHashtable = new HashMap<>();

    /**
     * Returns true if the list contains an equivalent project as the given argument.
     */
    public boolean contains(Project toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameProject);
    }

    /**
     * Adds a project to the list.
     * The project must not already exist in the list.
     */
    public void addProject(Project toAddProject) {
        requireNonNull(toAddProject);
        if (contains(toAddProject)) {
            throw new DuplicateProjectException();
        }
        internalList.add(toAddProject);
        projectTitleProjectHashtable.put(toAddProject.getProjectTitle(), toAddProject);
    }

    /**
     * Replaces the project {@code target} in the list with {@code editedProject}.
     * {@code target} must exist in the list.
     * The project identity of {@code editedProject} must not be the same as another existing project in the list.
     */
    public void setProject(Project target, Project editedProject) {
        requireAllNonNull(target, editedProject);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ProjectNotFoundException();
        }

        if (!target.isSameProject(editedProject) && contains(editedProject)) {
            throw new DuplicateProjectException();
        }

        internalList.set(index, editedProject);
        projectTitleProjectHashtable.remove(target);
        projectTitleProjectHashtable.put(editedProject.getProjectTitle(), editedProject);
    }

    /**
     * Removes the equivalent project from the list.
     * The project must exist in the list.
     */
    public void remove(Project toRemove) {
        requireNonNull(toRemove);
        boolean success = internalList.remove(toRemove);
        if (!success) {
            throw new ProjectNotFoundException();
        } else {
            projectTitleProjectHashtable.remove(toRemove);
        }
    }

    public void setProjects(UniqueProjectList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        projectTitleProjectHashtable.clear();
        for (Map.Entry<ProjectTitle, Project> entry
            : replacement.projectTitleProjectHashtable.entrySet()) {
            projectTitleProjectHashtable.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Replaces the contents of this list with {@code projects}.
     * {@code projects} must not contain duplicate projects.
     */
    public void setProjects(List<Project> projects) {
        requireAllNonNull(projects);
        boolean success = projectsAreUnique(projects);
        if (!success) {
            throw new DuplicateProjectException();
        }

        internalList.setAll(projects);
        projectTitleProjectHashtable.clear();
        for (Project entry : projects) {
            projectTitleProjectHashtable.put(entry.getProjectTitle(), entry);
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Project> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Compares the day, month, year factor of ProjectDate for sortByDate command
     */
    public void sortProjectByDate() {
        FXCollections.sort(internalList, (new Comparator<Project>() {
            public int compare(Project p1, Project p2) {
                if (p1.getProjectDate().getYear() == p2.getProjectDate().getYear()) {
                    if (p1.getProjectDate().getMonth() == p2.getProjectDate().getMonth()) {
                        return p1.getProjectDate().getDay() - p2.getProjectDate().getDay();
                    } else {
                        return p1.getProjectDate().getMonth() - p2.getProjectDate().getMonth();
                    }
                } else {
                    return p1.getProjectDate().getYear() - p2.getProjectDate().getYear();
                }
            } }));
    }

    @Override
    public Iterator<Project> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UniqueProjectList // instanceof handles nulls
            && internalList.equals(((UniqueProjectList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code projects} contains only unique projects.
     */
    private boolean projectsAreUnique(List<Project> projects) {
        for (int i = 0; i < projects.size() - 1; i++) {
            for (int j = i + 1; j < projects.size(); j++) {
                if (projects.get(i).isSameProject(projects.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
