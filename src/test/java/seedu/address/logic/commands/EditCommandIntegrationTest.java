package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ITEM;
import static seedu.address.testutil.TypicalInternship.GOOGLE;
import static seedu.address.testutil.TypicalNote.FINISH_CS_2103;
import static seedu.address.testutil.TypicalProject.ORBITAL;
import static seedu.address.testutil.TypicalResume.ME_RESUME;
import static seedu.address.testutil.TypicalSkill.GIT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.edit.EditCommand;
import seedu.address.logic.commands.edit.EditInternshipCommand;
import seedu.address.logic.commands.edit.EditInternshipDescriptor;
import seedu.address.logic.commands.edit.EditNoteCommand;
import seedu.address.logic.commands.edit.EditNoteDescriptor;
import seedu.address.logic.commands.edit.EditProjectCommand;
import seedu.address.logic.commands.edit.EditProjectDescriptor;
import seedu.address.logic.commands.edit.EditResumeCommand;
import seedu.address.logic.commands.edit.EditResumeDescriptor;
import seedu.address.logic.commands.edit.EditSkillCommand;
import seedu.address.logic.commands.edit.EditSkillDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.EditCommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ResumeBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Note;
import seedu.address.model.item.Project;
import seedu.address.model.item.Resume;
import seedu.address.model.item.Skill;
import seedu.address.model.util.ItemUtil;
import seedu.address.testutil.ResumeBookBuilder;
import seedu.address.testutil.TypicalInternship;
import seedu.address.testutil.TypicalNote;
import seedu.address.testutil.TypicalProject;
import seedu.address.testutil.TypicalResume;
import seedu.address.testutil.TypicalResumeBook;
import seedu.address.testutil.TypicalSkill;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditCommandIntegrationTest {

    private Model model;
    private Model expectedModel;
    private ResumeBook resumeBook = new ResumeBookBuilder(TypicalResumeBook.TYPICAL_WITHOUT_GOOGLE).build();
    private ResumeBook resumeBookCopy = new ResumeBookBuilder(TypicalResumeBook.TYPICAL_WITHOUT_GOOGLE).build();
    private EditInternshipDescriptor editInternshipDescriptor = new EditInternshipDescriptor();
    private EditProjectDescriptor editProjectDescriptor = new EditProjectDescriptor();
    private EditSkillDescriptor editSkillDescriptor = new EditSkillDescriptor();
    private EditResumeDescriptor editResumeDescriptor = new EditResumeDescriptor();
    private EditNoteDescriptor editNoteDescriptor = new EditNoteDescriptor();

    /**
     * A method to set fields in the edit internship descriptor.
     */
    public void setEditInternshipDescriptor(Internship sampleEditedInternship) {
        editInternshipDescriptor.setName(sampleEditedInternship.getName());
        editInternshipDescriptor.setFrom(sampleEditedInternship.getFrom());
        editInternshipDescriptor.setTo(sampleEditedInternship.getTo());
        editInternshipDescriptor.setRole(sampleEditedInternship.getRole());
        editInternshipDescriptor.setDescription(sampleEditedInternship.getDescription());
        editInternshipDescriptor.setTags(sampleEditedInternship.getTags());
    }

    /**
     * A method to set fields in the edit project descriptor.
     */
    public void setEditProjectDescriptor(Project sampleEditedProject) {
        editProjectDescriptor.setName(sampleEditedProject.getName());
        editProjectDescriptor.setWebsite(sampleEditedProject.getWebsite());
        editProjectDescriptor.setTime(sampleEditedProject.getTime());
        editProjectDescriptor.setDescription(sampleEditedProject.getDescription());
        editProjectDescriptor.setTags(sampleEditedProject.getTags());
    }

    /**
     * A method to set fields in the edit skill descriptor.
     */
    public void setEditSkillDescriptor(Skill sampleEditedSkill) {
        editSkillDescriptor.setName(sampleEditedSkill.getName());
        editSkillDescriptor.setLevel(sampleEditedSkill.getLevel());
        editSkillDescriptor.setTags(sampleEditedSkill.getTags());
    }

    /**
     * A method to set fields in the edit note descriptor.
     */
    public void setEditNoteDescriptor(Note sampleEditedNote) {
        editNoteDescriptor.setName(sampleEditedNote.getName());
        editNoteDescriptor.setTime(sampleEditedNote.getTime());
    }

    /**
     * A method to set fields in the edit resume descriptor.
     */
    public void setEditResumeDescriptor(Resume sampleEditedResume) {
        editResumeDescriptor.setName(sampleEditedResume.getName());
        editResumeDescriptor.setTags(sampleEditedResume.getTags());
    }

    @BeforeEach
    public void setUp() {
        model = new ModelManager(resumeBook, new UserPrefs());
        expectedModel = new ModelManager(resumeBookCopy, new UserPrefs());
    }

    @Test
    public void execute_newInternship_success() {
        Internship validInternship = GOOGLE;
        setEditInternshipDescriptor(validInternship);

        expectedModel.setInternship(TypicalInternship.NINJA_VAN, validInternship);
        expectedModel.setInternshipToDisplay();

        assertCommandSuccess(new EditInternshipCommand(INDEX_FIRST_ITEM, editInternshipDescriptor),
                model,
                new EditCommandResult(validInternship.toString(),
                        String.format(EditInternshipCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS,
                                validInternship.getName().fullName), ItemUtil.INTERNSHIP_ALIAS),
                expectedModel);
    }

    @Test
    public void execute_outOfBoundsInternship_throwsCommandException() {
        assertCommandFailure(new EditInternshipCommand(INDEX_THIRD_ITEM, editInternshipDescriptor),
                model,
                new CommandException(Messages.MESSAGE_INVALID_INDEX));
    }

    @Test
    public void execute_duplicateInternship_throwsCommandException() {
        Internship validInternship = TypicalInternship.PAYPAL;
        setEditInternshipDescriptor(validInternship);

        assertCommandFailure(new EditInternshipCommand(INDEX_FIRST_ITEM, editInternshipDescriptor),
                model,
                new CommandException(EditCommand.MESSAGE_DUPLICATE_ITEM));
    }

    @Test
    public void execute_newNote_success() {
        Note validNote = TypicalNote.FINISH_HOMEWORK;
        setEditNoteDescriptor(validNote);

        expectedModel.setNote(FINISH_CS_2103, validNote);

        assertCommandSuccess(new EditNoteCommand(INDEX_FIRST_ITEM, editNoteDescriptor),
                model,
                new EditCommandResult(validNote.toString(),
                        String.format(EditNoteCommand.MESSAGE_EDIT_NOTE_SUCCESS, validNote.getName().fullName)
                                , ItemUtil.NOTE_ALIAS),
                expectedModel);
    }

    @Test
    public void execute_newProject_success() {
        Project validProject = TypicalProject.DUKE;
        setEditProjectDescriptor(validProject);

        expectedModel.setProject(ORBITAL, validProject);
        expectedModel.setProjectToDisplay();

        assertCommandSuccess(new EditProjectCommand(INDEX_FIRST_ITEM, editProjectDescriptor),
                model,
                new EditCommandResult(validProject.toString(),
                        String.format(EditProjectCommand.MESSAGE_EDIT_PROJECT_SUCCESS,
                                validProject.getName().fullName), ItemUtil.PROJECT_ALIAS),
                expectedModel);
    }

    @Test
    public void execute_newSkill_success() {
        Skill validSkill = GIT;
        setEditSkillDescriptor(validSkill);

        expectedModel.setSkill(TypicalSkill.REACT, validSkill);
        expectedModel.setSkillToDisplay();

        assertCommandSuccess(new EditSkillCommand(INDEX_FIRST_ITEM, editSkillDescriptor),
                model,
                new EditCommandResult(validSkill.toString(),
                        String.format(EditSkillCommand.MESSAGE_EDIT_SKILL_SUCCESS,
                                validSkill.getName().fullName), ItemUtil.SKILL_ALIAS),
                expectedModel);
    }

    @Test
    public void execute_newResume_success() {
        Resume validResume = TypicalResume.SE_RESUME;
        setEditResumeDescriptor(validResume);

        expectedModel.setResume(ME_RESUME, validResume);
        expectedModel.setResumeToDisplay();

        assertCommandSuccess(new EditResumeCommand(INDEX_FIRST_ITEM, editResumeDescriptor),
                model,
                new EditCommandResult(validResume.toString(),
                        String.format(EditResumeCommand.MESSAGE_EDIT_RESUME_SUCCESS,
                                validResume.getName().fullName), ItemUtil.RESUME_ALIAS),
                expectedModel);
    }

    @Test
    public void equals() {
        setEditInternshipDescriptor(GOOGLE);
        setEditProjectDescriptor(ORBITAL);
        setEditSkillDescriptor(GIT);
        setEditResumeDescriptor(ME_RESUME);
        setEditNoteDescriptor(FINISH_CS_2103);
        EditCommand validEditInternship = new EditInternshipCommand(INDEX_FIRST_ITEM, editInternshipDescriptor);
        EditCommand validEditProject = new EditProjectCommand(INDEX_FIRST_ITEM, editProjectDescriptor);
        EditCommand validEditResume = new EditResumeCommand(INDEX_FIRST_ITEM, editResumeDescriptor);
        EditCommand validEditSkill = new EditSkillCommand(INDEX_FIRST_ITEM, editSkillDescriptor);
        EditCommand validEditNote = new EditNoteCommand(INDEX_FIRST_ITEM, editNoteDescriptor);

        assertNotEquals(validEditInternship, validEditNote);
        assertNotEquals(validEditInternship, validEditProject);
        assertNotEquals(validEditInternship, validEditResume);
        assertNotEquals(validEditInternship, validEditSkill);
        assertNotEquals(validEditNote, validEditProject);
        assertNotEquals(validEditNote, validEditResume);
        assertNotEquals(validEditNote, validEditSkill);
        assertNotEquals(validEditProject, validEditResume);
        assertNotEquals(validEditProject, validEditSkill);
        assertNotEquals(validEditResume, validEditSkill);
    }
}
