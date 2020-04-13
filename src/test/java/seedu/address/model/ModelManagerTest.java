package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TECH;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternship.GOOGLE;
import static seedu.address.testutil.TypicalInternship.NINJA_VAN;
import static seedu.address.testutil.TypicalInternship.PAYPAL;
import static seedu.address.testutil.TypicalNote.FINISH_CS_2103;
import static seedu.address.testutil.TypicalNote.FINISH_HOMEWORK;
import static seedu.address.testutil.TypicalNote.FINISH_RESUME_2;
import static seedu.address.testutil.TypicalPerson.ALICE;
import static seedu.address.testutil.TypicalPerson.BOB;
import static seedu.address.testutil.TypicalProject.DUKE;
import static seedu.address.testutil.TypicalProject.ORBITAL;
import static seedu.address.testutil.TypicalProject.RESUME;
import static seedu.address.testutil.TypicalResume.CE_RESUME;
import static seedu.address.testutil.TypicalResume.ME_RESUME;
import static seedu.address.testutil.TypicalResume.SE_RESUME;
import static seedu.address.testutil.TypicalSkill.CODE;
import static seedu.address.testutil.TypicalSkill.GIT;
import static seedu.address.testutil.TypicalSkill.REACT;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Note;
import seedu.address.model.item.Project;
import seedu.address.model.item.Resume;
import seedu.address.model.item.Skill;
import seedu.address.model.item.exceptions.DuplicateItemException;
import seedu.address.model.item.exceptions.ItemNotFoundException;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.ItemUtil;
import seedu.address.testutil.InternshipBuilder;
import seedu.address.testutil.NoteBuilder;
import seedu.address.testutil.ProjectBuilder;
import seedu.address.testutil.ResumeBookBuilder;
import seedu.address.testutil.ResumeBuilder;
import seedu.address.testutil.SkillBuilder;
import seedu.address.testutil.TypicalResumeBook;

public class ModelManagerTest {

    private ModelManager modelManager;

    @BeforeEach
    public void setUp() {
        modelManager = new ModelManager(new ResumeBookBuilder(TypicalResumeBook.TYPICAL_WITHOUT_GOOGLE).build(),
                new UserPrefs());
    }

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ResumeBook(TypicalResumeBook.TYPICAL_WITHOUT_GOOGLE),
                new ResumeBook(modelManager.getResumeBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setResumeBookFilePath(Paths.get("resume/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setResumeBookFilePath(Paths.get("new/resume/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setResumeBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setResumeBookFilePath(null));
    }

    @Test
    public void setResumeBookFilePath_validPath_setsResumeBookFilePath() {
        Path path = Paths.get("resume/book/file/path");
        modelManager.setResumeBookFilePath(path);
        assertEquals(path, modelManager.getResumeBookFilePath());
    }

    // Internship-related tests

    @Test
    public void hasInternship_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasInternship(null));
    }

    @Test
    public void hasInternship_internshipNotInResumeBook_returnsFalse() {
        assertFalse(modelManager.hasInternship(new InternshipBuilder(GOOGLE).build()));
    }

    @Test
    public void hasInternship_internshipInResumeBook_returnsTrue() {
        assertTrue(modelManager.hasInternship(new InternshipBuilder(PAYPAL).build()));
    }

    @Test
    public void addInternship_internshipNotInResumeBook_success() {
        modelManager.addInternship(new InternshipBuilder(GOOGLE).build());
        assertTrue(modelManager.hasInternship(new InternshipBuilder(GOOGLE).build()));
    }

    @Test
    public void addInternship_internshipAlreadyInResumeBook_throwsDuplicateItemException() {
        assertThrows(DuplicateItemException.class, () ->
                modelManager.addInternship(new InternshipBuilder(PAYPAL).build()));
    }

    @Test
    public void deleteInternship_internshipExists_success() {
        modelManager.deleteInternship(new InternshipBuilder(PAYPAL).build());
        assertFalse(modelManager.hasInternship(new InternshipBuilder(PAYPAL).build()));
    }

    @Test
    public void deleteInternship_internshipNotInResumeBook_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () ->
                modelManager.deleteInternship(new InternshipBuilder(GOOGLE).build()));
    }

    @Test
    public void setInternship_internshipInResumeBook_success() {
        modelManager.setInternship(new InternshipBuilder(PAYPAL).build(), new InternshipBuilder(GOOGLE).build());
        assertTrue(modelManager.hasInternship(new InternshipBuilder(GOOGLE).build()));
    }

    @Test
    public void setInternship_toEditNotInResumeBook_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> modelManager.setInternship(GOOGLE, PAYPAL));
    }

    @Test
    public void getInternshipSize_internshipsInResumeBook_returnsInternshipListSize() {
        assertEquals(2, modelManager.getInternshipSize());
    }

    @Test
    public void getInternshipByIndex_withinBounds_returnsInternship() {
        assertEquals(new InternshipBuilder(PAYPAL).build(), modelManager.getInternshipByIndex(Index.fromOneBased(2)));
    }

    @Test
    public void getInternshipByIndex_outOfBounds_throwsOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> modelManager.getInternshipByIndex(Index.fromOneBased(3)));
    }

    @Test
    public void getInternshipByTag_internshipWithTagInResumeBook_returnsInternshipList() {
        assertEquals(Arrays.asList(NINJA_VAN, PAYPAL), modelManager.getInternshipsByTag(new Tag(VALID_TAG_TECH)));
    }

    @Test
    public void getInternshipByTag_noInternshipWithTagInResumeBook_returnsEmptyList() {
        assertEquals(Collections.emptyList(), modelManager.getInternshipsByTag(new Tag("abc")));
    }

    @Test
    public void setInternshipsToDisplay_internshipInResumeBook_success() {
        modelManager.setInternshipToDisplay();
        assertEquals(modelManager.getDisplayType(), ItemUtil.INTERNSHIP_ALIAS);
        assertEquals(Arrays.asList(NINJA_VAN, PAYPAL), modelManager.getFilteredItemList());
    }

    @Test
    public void sortInternships_internshipInResumeBook_success() {
        modelManager.sortInternships(Comparator.comparing(Internship::getName).reversed());
        modelManager.setInternshipToDisplay();
        assertEquals(modelManager.getDisplayType(), ItemUtil.INTERNSHIP_ALIAS);
        assertEquals(Arrays.asList(PAYPAL, NINJA_VAN), modelManager.getFilteredItemList());
    }

    // Note-related tests

    @Test
    public void hasNote_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasNote(null));
    }

    @Test
    public void hasNote_noteNotInResumeBook_returnsFalse() {
        assertFalse(modelManager.hasNote(new NoteBuilder(FINISH_RESUME_2).build()));
    }

    @Test
    public void hasNote_noteInResumeBook_returnsTrue() {
        assertTrue(modelManager.hasNote(new NoteBuilder(FINISH_HOMEWORK).build()));
    }

    @Test
    public void addNote_noteNotInResumeBook_success() {
        modelManager.addNote(new NoteBuilder(FINISH_RESUME_2).build());
        assertTrue(modelManager.hasNote(new NoteBuilder(FINISH_RESUME_2).build()));
    }

    @Test
    public void addNote_noteAlreadyInResumeBook_throwsDuplicateItemException() {
        assertThrows(DuplicateItemException.class, () ->
                modelManager.addNote(new NoteBuilder(FINISH_HOMEWORK).build()));
    }

    @Test
    public void deleteNote_noteExists_success() {
        modelManager.deleteNote(new NoteBuilder(FINISH_HOMEWORK).build());
        assertFalse(modelManager.hasNote(new NoteBuilder(FINISH_HOMEWORK).build()));
    }

    @Test
    public void deleteNote_noteNotInResumeBook_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () ->
                modelManager.deleteNote(new NoteBuilder(FINISH_RESUME_2).build()));
    }

    @Test
    public void setNote_noteInResumeBook_success() {
        modelManager.setNote(new NoteBuilder(FINISH_HOMEWORK).build(), new NoteBuilder(FINISH_RESUME_2).build());
        assertTrue(modelManager.hasNote(new NoteBuilder(FINISH_RESUME_2).build()));
    }

    @Test
    public void setNote_toEditNotInResumeBook_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> modelManager.setNote(FINISH_RESUME_2, FINISH_HOMEWORK));
    }

    @Test
    public void getNoteByIndex_withinBounds_returnsNote() {
        assertEquals(new NoteBuilder(FINISH_HOMEWORK).build(), modelManager.getNoteByIndex(Index.fromOneBased(2)));
    }

    @Test
    public void getNoteByIndex_outOfBounds_throwsOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> modelManager.getNoteByIndex(Index.fromOneBased(3)));
    }

    @Test
    public void getNoteSize_notesInResumeBook_returnsNoteListSize() {
        assertEquals(2, modelManager.getNoteListSize());
    }

    @Test
    public void sortNotes_noteInResumeBook_success() {
        modelManager.sortNotes(Comparator.comparing(Note::getName).reversed());
        assertEquals(Arrays.asList(FINISH_HOMEWORK, FINISH_CS_2103), modelManager.getFilteredNoteList());
    }

    // Project-related tests

    @Test
    public void hasProject_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasProject(null));
    }

    @Test
    public void hasProject_projectNotInResumeBook_returnsFalse() {
        assertFalse(modelManager.hasProject(new ProjectBuilder(RESUME).build()));
    }

    @Test
    public void hasProject_projectInResumeBook_returnsTrue() {
        assertTrue(modelManager.hasProject(new ProjectBuilder(DUKE).build()));
    }

    @Test
    public void addProject_projectNotInResumeBook_success() {
        modelManager.addProject(new ProjectBuilder(RESUME).build());
        assertTrue(modelManager.hasProject(new ProjectBuilder(RESUME).build()));
    }

    @Test
    public void addProject_projectAlreadyInResumeBook_throwsDuplicateItemException() {
        assertThrows(DuplicateItemException.class, () -> modelManager.addProject(new ProjectBuilder(DUKE).build()));
    }

    @Test
    public void deleteProject_projectExists_success() {
        modelManager.deleteProject(new ProjectBuilder(DUKE).build());
        assertFalse(modelManager.hasProject(new ProjectBuilder(DUKE).build()));
    }

    @Test
    public void deleteProject_projectNotInResumeBook_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> modelManager.deleteProject(new ProjectBuilder(RESUME).build()));
    }

    @Test
    public void setProject_projectInResumeBook_success() {
        modelManager.setProject(new ProjectBuilder(DUKE).build(), new ProjectBuilder(RESUME).build());
        assertTrue(modelManager.hasProject(new ProjectBuilder(RESUME).build()));
    }

    @Test
    public void setProject_toEditNotInResumeBook_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> modelManager.setProject(RESUME, DUKE));
    }

    @Test
    public void getProjectByIndex_withinBounds_returnsProject() {
        assertEquals(new ProjectBuilder(DUKE).build(), modelManager.getProjectByIndex(Index.fromOneBased(2)));
    }

    @Test
    public void getProjectByIndex_outOfBounds_throwsOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> modelManager.getProjectByIndex(Index.fromOneBased(3)));
    }

    @Test
    public void getProjectSize_projectsInResumeBook_returnsProjectListSize() {
        assertEquals(2, modelManager.getProjectSize());
    }

    @Test
    public void getProjectByTag_projectWithTagInResumeBook_returnsProjectList() {
        assertEquals(Arrays.asList(ORBITAL, DUKE), modelManager.getProjectsByTag(new Tag(VALID_TAG_TECH)));
    }

    @Test
    public void getProjectByTag_noProjectWithTagInResumeBook_returnsEmptyList() {
        assertEquals(Collections.emptyList(), modelManager.getProjectsByTag(new Tag("abc")));
    }

    @Test
    public void setProjectsToDisplay_projectInResumeBook_success() {
        modelManager.setProjectToDisplay();
        assertEquals(modelManager.getDisplayType(), ItemUtil.PROJECT_ALIAS);
        assertEquals(Arrays.asList(ORBITAL, DUKE), modelManager.getFilteredItemList());
    }

    @Test
    public void sortProjects_projectInResumeBook_success() {
        modelManager.sortProjects(Comparator.comparing(Project::getName));
        modelManager.setProjectToDisplay();
        assertEquals(modelManager.getDisplayType(), ItemUtil.PROJECT_ALIAS);
        assertEquals(Arrays.asList(DUKE, ORBITAL), modelManager.getFilteredItemList());
    }

    // Resume-related tests

    @Test
    public void hasResume_nullResume_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasResume(null));
    }

    @Test
    public void hasResume_resumeNotInResumeBook_returnsFalse() {
        assertFalse(modelManager.hasResume(new ResumeBuilder(CE_RESUME).build()));
    }

    @Test
    public void hasResume_resumeInResumeBook_returnsTrue() {
        assertTrue(modelManager.hasResume(new ResumeBuilder(SE_RESUME).build()));
    }

    @Test
    public void addResume_resumeNotInResumeBook_success() {
        modelManager.addResume(new ResumeBuilder(CE_RESUME).build());
        assertTrue(modelManager.hasResume(new ResumeBuilder(CE_RESUME).build()));
    }

    @Test
    public void addResume_resumeAlreadyInResumeBook_throwsDuplicateItemException() {
        assertThrows(DuplicateItemException.class, () -> modelManager.addResume(new ResumeBuilder(SE_RESUME).build()));
    }

    @Test
    public void deleteResume_resumeExists_success() {
        modelManager.deleteResume(new ResumeBuilder(SE_RESUME).build());
        assertFalse(modelManager.hasResume(new ResumeBuilder(SE_RESUME).build()));
    }

    @Test
    public void deleteResume_resumeNotInResumeBook_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () ->
                modelManager.deleteResume(new ResumeBuilder(CE_RESUME).build()));
    }

    @Test
    public void setResume_resumeInResumeBook_success() {
        modelManager.setResume(new ResumeBuilder(SE_RESUME).build(), new ResumeBuilder(CE_RESUME).build());
        assertTrue(modelManager.hasResume(new ResumeBuilder(CE_RESUME).build()));
    }

    @Test
    public void setResume_toEditNotInResumeBook_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> modelManager.setResume(CE_RESUME, SE_RESUME));
    }

    @Test
    public void getResumeByIndex_withinBounds_returnsResume() {
        assertEquals(new ResumeBuilder(SE_RESUME).build(), modelManager.getResumeByIndex(Index.fromOneBased(2)));
    }

    @Test
    public void getResumeByIndex_outOfBounds_throwsOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> modelManager.getResumeByIndex(Index.fromOneBased(3)));
    }

    @Test
    public void getResumeSize_resumesInResumeBook_returnsResumeListSize() {
        assertEquals(2, modelManager.getResumeSize());
    }

    @Test
    public void setResumesToDisplay_resumeInResumeBook_success() {
        modelManager.setResumeToDisplay();
        assertEquals(modelManager.getDisplayType(), ItemUtil.RESUME_ALIAS);
        assertEquals(Arrays.asList(ME_RESUME, SE_RESUME), modelManager.getFilteredItemList());
    }

    @Test
    public void sortResumes_resumeInResumeBook_success() {
        modelManager.sortResumes(Comparator.comparing(Resume::getName).reversed());
        modelManager.setResumeToDisplay();
        assertEquals(modelManager.getDisplayType(), ItemUtil.RESUME_ALIAS);
        assertEquals(Arrays.asList(SE_RESUME, ME_RESUME), modelManager.getFilteredItemList());
    }

    // Skill-related tests

    @Test
    public void hasSkill_nullSkill_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasSkill(null));
    }

    @Test
    public void hasSkill_internshipNotInResumeBook_returnsFalse() {
        assertFalse(modelManager.hasSkill(new SkillBuilder(CODE).build()));
    }

    @Test
    public void hasSkill_internshipInResumeBook_returnsTrue() {
        assertTrue(modelManager.hasSkill(new SkillBuilder(GIT).build()));
    }

    @Test
    public void addSkill_internshipNotInResumeBook_success() {
        modelManager.addSkill(new SkillBuilder(CODE).build());
        assertTrue(modelManager.hasSkill(new SkillBuilder(CODE).build()));
    }

    @Test
    public void addSkill_internshipAlreadyInResumeBook_throwsDuplicateItemException() {
        assertThrows(DuplicateItemException.class, () -> modelManager.addSkill(new SkillBuilder(GIT).build()));
    }

    @Test
    public void deleteSkill_internshipExists_success() {
        modelManager.deleteSkill(new SkillBuilder(GIT).build());
        assertFalse(modelManager.hasSkill(new SkillBuilder(GIT).build()));
    }

    @Test
    public void deleteSkill_internshipNotInResumeBook_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> modelManager.deleteSkill(new SkillBuilder(CODE).build()));
    }

    @Test
    public void setSkill_internshipInResumeBook_success() {
        modelManager.setSkill(new SkillBuilder(GIT).build(), new SkillBuilder(CODE).build());
        assertTrue(modelManager.hasSkill(new SkillBuilder(CODE).build()));
    }

    @Test
    public void setSkill_toEditNotInResumeBook_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> modelManager.setSkill(CODE, GIT));
    }

    @Test
    public void getSkillSize_internshipsInResumeBook_returnsSkillListSize() {
        assertEquals(2, modelManager.getSkillSize());
    }

    @Test
    public void getSkillByIndex_withinBounds_returnsSkill() {
        assertEquals(new SkillBuilder(GIT).build(), modelManager.getSkillByIndex(Index.fromOneBased(2)));
    }

    @Test
    public void getSkillByIndex_outOfBounds_throwsOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> modelManager.getSkillByIndex(Index.fromOneBased(3)));
    }

    @Test
    public void getSkillByTag_internshipWithTagInResumeBook_returnsSkillList() {
        assertEquals(Arrays.asList(REACT, GIT), modelManager.getSkillsByTag(new Tag(VALID_TAG_TECH)));
    }

    @Test
    public void getSkillByTag_noSkillWithTagInResumeBook_returnsEmptyList() {
        assertEquals(Collections.emptyList(), modelManager.getSkillsByTag(new Tag("abc")));
    }

    @Test
    public void setSkillsToDisplay_internshipInResumeBook_success() {
        modelManager.setSkillToDisplay();
        assertEquals(modelManager.getDisplayType(), ItemUtil.SKILL_ALIAS);
        assertEquals(Arrays.asList(REACT, GIT), modelManager.getFilteredItemList());
    }

    @Test
    public void sortSkills_internshipInResumeBook_success() {
        modelManager.sortSkills(Comparator.comparing(Skill::getName));
        modelManager.setSkillToDisplay();
        assertEquals(modelManager.getDisplayType(), ItemUtil.SKILL_ALIAS);
        assertEquals(Arrays.asList(GIT, REACT), modelManager.getFilteredItemList());
    }

    // User related methods

    @Test
    public void getUser_defaultUserInResumeBook_returnsUser() {
        assertEquals(ALICE, modelManager.getUser());
    }

    @Test
    public void setUser_defaultUserInResumeBook_success() {
        modelManager.setUser(BOB);
        assertEquals(BOB, modelManager.getUser());
    }
}
