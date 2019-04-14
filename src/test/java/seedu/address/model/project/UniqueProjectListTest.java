package seedu.address.model.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.project.TypicalProjects.PROJECT1;
import static seedu.address.testutil.project.TypicalProjects.PROJECT2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seedu.address.model.project.exceptions.DuplicateProjectException;
import seedu.address.model.project.exceptions.ProjectNotFoundException;

public class UniqueProjectListTest {
    private final UniqueProjectList uniqueProjectList = new UniqueProjectList();
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void contains_nullProject_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueProjectList.contains(null);
    }

    @Test
    public void contains_projectNotInList_returnsFalse() {
        assertFalse(uniqueProjectList.contains(PROJECT1));
    }

    @Test
    public void contains_projectInList_returnsTrue() {
        uniqueProjectList.addProject(PROJECT1);
        assertTrue(uniqueProjectList.contains(PROJECT1));
    }

    @Test
    public void add_nullProject_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueProjectList.addProject(null);
    }

    @Test
    public void add_duplicateProject_throwsDuplicateProjectException() {
        uniqueProjectList.addProject(PROJECT1);
        thrown.expect(DuplicateProjectException.class);
        uniqueProjectList.addProject(PROJECT1);
    }

    @Test
    public void setProject_nullTargetProject_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueProjectList.setProject(null, PROJECT1);
    }

    @Test
    public void setProject_targetProjectNotInList_throwsProjectNotFoundException() {
        thrown.expect(ProjectNotFoundException.class);
        uniqueProjectList.setProject(PROJECT1, PROJECT1);
    }

    @Test
    public void remove_nullProject_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueProjectList.remove(null);
    }

    @Test
    public void remove_projectDoesNotExist_throwsProjectNotFoundException() {
        thrown.expect(ProjectNotFoundException.class);
        uniqueProjectList.remove(PROJECT1);
    }

    @Test
    public void remove_existingProject_removesProject() {
        uniqueProjectList.addProject(PROJECT1);
        uniqueProjectList.remove(PROJECT1);
        UniqueProjectList expectedUniqueProjectList = new UniqueProjectList();
        assertEquals(expectedUniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProjects_nullUniqueProjectList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueProjectList.setProjects((UniqueProjectList) null);
    }

    @Test
    public void setProjects_uniqueProjectList_replacesOwnListWithProvidedUniqueProjectList() {
        uniqueProjectList.addProject(PROJECT1);
        UniqueProjectList expectedUniqueProjectList = new UniqueProjectList();
        expectedUniqueProjectList.addProject(PROJECT1);
        uniqueProjectList.setProjects(expectedUniqueProjectList);
        assertEquals(expectedUniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProjects_nullList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueProjectList.setProjects((List<Project>) null);
    }

    @Test
    public void setProjects_list_replacesOwnListWithProvidedList() {
        uniqueProjectList.addProject(PROJECT1);
        List<Project> projectList = Collections.singletonList(PROJECT2);
        uniqueProjectList.setProjects(projectList);
        UniqueProjectList expectedUniqueProjectList = new UniqueProjectList();
        expectedUniqueProjectList.addProject(PROJECT2);
        assertEquals(expectedUniqueProjectList, uniqueProjectList);
    }

    @Test
    public void setProjects_listWithDuplicateProjects_throwsDuplicateProjectException() {
        List<Project> listWithDuplicateProjects = Arrays.asList(PROJECT1, PROJECT1);
        thrown.expect(DuplicateProjectException.class);
        uniqueProjectList.setProjects(listWithDuplicateProjects);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        uniqueProjectList.asUnmodifiableObservableList().remove(0);
    }
}
