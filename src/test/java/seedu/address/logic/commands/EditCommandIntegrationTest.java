package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FROM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERNSHIP_NAME_PAYPAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERNSHIP_ROLE_BACKEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_DUKE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESUME_NAME_SE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_NAME_GIT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEBSITE_DUKE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ITEM;
import static seedu.address.testutil.TypicalInternship.GOOGLE;
import static seedu.address.testutil.TypicalInternship.PAYPAL;
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
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Note;
import seedu.address.model.item.Project;
import seedu.address.model.item.Resume;
import seedu.address.model.item.Skill;
import seedu.address.model.util.ItemUtil;
import seedu.address.testutil.InternshipBuilder;
import seedu.address.testutil.NoteBuilder;
import seedu.address.testutil.ProjectBuilder;
import seedu.address.testutil.ResumeBookBuilder;
import seedu.address.testutil.ResumeBuilder;
import seedu.address.testutil.SkillBuilder;
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

    /**
     * A method to set fields in the edit internship descriptor.
     */
    public void setEditInternshipDescriptor(EditInternshipDescriptor editInternshipDescriptor,
                                            Internship sampleEditedInternship) {
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
    public void setEditProjectDescriptor(EditProjectDescriptor editProjectDescriptor, Project sampleEditedProject) {
        editProjectDescriptor.setName(sampleEditedProject.getName());
        editProjectDescriptor.setWebsite(sampleEditedProject.getWebsite());
        editProjectDescriptor.setTime(sampleEditedProject.getTime());
        editProjectDescriptor.setDescription(sampleEditedProject.getDescription());
        editProjectDescriptor.setTags(sampleEditedProject.getTags());
    }

    /**
     * A method to set fields in the edit skill descriptor.
     */
    public void setEditSkillDescriptor(EditSkillDescriptor editSkillDescriptor, Skill sampleEditedSkill) {
        editSkillDescriptor.setName(sampleEditedSkill.getName());
        editSkillDescriptor.setLevel(sampleEditedSkill.getLevel());
        editSkillDescriptor.setTags(sampleEditedSkill.getTags());
    }

    /**
     * A method to set fields in the edit note descriptor.
     */
    public void setEditNoteDescriptor(EditNoteDescriptor editNoteDescriptor, Note sampleEditedNote) {
        editNoteDescriptor.setName(sampleEditedNote.getName());
        editNoteDescriptor.setTime(sampleEditedNote.getTime());
    }

    /**
     * A method to set fields in the edit resume descriptor.
     */
    public void setEditResumeDescriptor(EditResumeDescriptor editResumeDescriptor, Resume sampleEditedResume) {
        editResumeDescriptor.setName(sampleEditedResume.getName());
        editResumeDescriptor.setTags(sampleEditedResume.getTags());
    }

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new ResumeBookBuilder(TypicalResumeBook.TYPICAL_WITHOUT_GOOGLE).build(),
                new UserPrefs());
        expectedModel = new ModelManager(new ResumeBookBuilder(TypicalResumeBook.TYPICAL_WITHOUT_GOOGLE).build(),
                new UserPrefs());
    }

    @Test
    public void execute_editInternship_success() {
        Internship validInternship = GOOGLE;
        EditInternshipDescriptor editInternshipDescriptor = new EditInternshipDescriptor();
        setEditInternshipDescriptor(editInternshipDescriptor, validInternship);

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
        EditInternshipDescriptor editInternshipDescriptor = new EditInternshipDescriptor();
        assertCommandFailure(new EditInternshipCommand(INDEX_FOURTH_ITEM, editInternshipDescriptor),
                model,
                new CommandException(Messages.MESSAGE_INVALID_INDEX));
    }

    @Test
    public void execute_fullDuplicateInternship_throwsCommandException() {
        Internship validInternship = PAYPAL;
        EditInternshipDescriptor editInternshipDescriptor = new EditInternshipDescriptor();
        setEditInternshipDescriptor(editInternshipDescriptor, validInternship);

        assertCommandFailure(new EditInternshipCommand(INDEX_FIRST_ITEM, editInternshipDescriptor),
                model,
                new CommandException(EditCommand.MESSAGE_DUPLICATE_ITEM));
    }

    @Test
    public void execute_isSameDuplicateInternship_throwsCommandException() {
        Internship validInternship = new InternshipBuilder().withName(VALID_INTERNSHIP_NAME_PAYPAL)
                .withRole(VALID_INTERNSHIP_ROLE_BACKEND).withFrom(VALID_FROM).withTo(VALID_TO).build();
        EditInternshipDescriptor editInternshipDescriptor = new EditInternshipDescriptor();
        setEditInternshipDescriptor(editInternshipDescriptor, validInternship);

        assertCommandFailure(new EditInternshipCommand(INDEX_FIRST_ITEM, editInternshipDescriptor),
                model,
                new CommandException(EditCommand.MESSAGE_DUPLICATE_ITEM));
    }

    @Test
    public void execute_editNote_success() {
        Note validNote = TypicalNote.FINISH_RESUME_2;
        EditNoteDescriptor editNoteDescriptor = new EditNoteDescriptor();
        setEditNoteDescriptor(editNoteDescriptor, validNote);

        expectedModel.setNote(FINISH_CS_2103, validNote);

        assertCommandSuccess(new EditNoteCommand(INDEX_FIRST_ITEM, editNoteDescriptor),
                model,
                new EditCommandResult(validNote.toString(),
                        String.format(EditNoteCommand.MESSAGE_EDIT_NOTE_SUCCESS, validNote.getName().fullName),
                        ItemUtil.NOTE_ALIAS),
                expectedModel);
    }

    @Test
    public void execute_outOfBoundsNote_throwsCommandException() {
        EditNoteDescriptor editNoteDescriptor = new EditNoteDescriptor();
        assertCommandFailure(new EditNoteCommand(INDEX_THIRD_ITEM, editNoteDescriptor),
                model,
                new CommandException(Messages.MESSAGE_INVALID_INDEX));
    }

    @Test
    public void execute_duplicateNote_throwsCommandException() {
        Note validNote = TypicalNote.FINISH_HOMEWORK;
        EditNoteDescriptor editNoteDescriptor = new EditNoteDescriptor();
        setEditNoteDescriptor(editNoteDescriptor, validNote);

        assertCommandFailure(new EditNoteCommand(INDEX_FIRST_ITEM, editNoteDescriptor),
                model,
                new CommandException(EditCommand.MESSAGE_DUPLICATE_ITEM));
    }

    @Test
    public void execute_isSameDuplicateNote_throwsCommandException() {
        Note validNote = new NoteBuilder().withName("Finish Homework").withTime("03-2020").build();
        EditNoteDescriptor editNoteDescriptor = new EditNoteDescriptor();
        setEditNoteDescriptor(editNoteDescriptor, validNote);

        assertCommandFailure(new EditNoteCommand(INDEX_FIRST_ITEM, editNoteDescriptor),
                model,
                new CommandException(EditCommand.MESSAGE_DUPLICATE_ITEM));
    }

    @Test
    public void execute_editProject_success() {
        Project validProject = TypicalProject.RESUME;
        EditProjectDescriptor editProjectDescriptor = new EditProjectDescriptor();
        setEditProjectDescriptor(editProjectDescriptor, validProject);

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
    public void execute_outOfBoundsProject_throwsCommandException() {
        EditProjectDescriptor editProjectDescriptor = new EditProjectDescriptor();
        assertCommandFailure(new EditProjectCommand(INDEX_THIRD_ITEM, editProjectDescriptor),
                model,
                new CommandException(Messages.MESSAGE_INVALID_INDEX));
    }

    @Test
    public void execute_duplicateProject_throwsCommandException() {
        Project validProject = TypicalProject.DUKE;
        EditProjectDescriptor editProjectDescriptor = new EditProjectDescriptor();
        setEditProjectDescriptor(editProjectDescriptor, validProject);

        assertCommandFailure(new EditProjectCommand(INDEX_FIRST_ITEM, editProjectDescriptor),
                model,
                new CommandException(EditCommand.MESSAGE_DUPLICATE_ITEM));
    }

    @Test
    public void execute_isSameDuplicateProject_throwsCommandException() {
        Project validProject = new ProjectBuilder().withName(VALID_PROJECT_NAME_DUKE)
                .withTime(VALID_TIME_2).withWebsite(VALID_WEBSITE_DUKE).build();
        EditProjectDescriptor editProjectDescriptor = new EditProjectDescriptor();
        setEditProjectDescriptor(editProjectDescriptor, validProject);

        assertCommandFailure(new EditProjectCommand(INDEX_FIRST_ITEM, editProjectDescriptor),
                model,
                new CommandException(EditCommand.MESSAGE_DUPLICATE_ITEM));
    }

    @Test
    public void execute_editSkill_success() {
        Skill validSkill = TypicalSkill.CODE;
        EditSkillDescriptor editSkillDescriptor = new EditSkillDescriptor();
        setEditSkillDescriptor(editSkillDescriptor, validSkill);

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
    public void execute_outOfBoundsSkill_throwsCommandException() {
        EditSkillDescriptor editSkillDescriptor = new EditSkillDescriptor();
        assertCommandFailure(new EditSkillCommand(INDEX_THIRD_ITEM, editSkillDescriptor),
                model,
                new CommandException(Messages.MESSAGE_INVALID_INDEX));
    }

    @Test
    public void execute_duplicateSkill_throwsCommandException() {
        Skill validSkill = TypicalSkill.GIT;
        EditSkillDescriptor editSkillDescriptor = new EditSkillDescriptor();
        setEditSkillDescriptor(editSkillDescriptor, validSkill);

        assertCommandFailure(new EditSkillCommand(INDEX_FIRST_ITEM, editSkillDescriptor),
                model,
                new CommandException(EditCommand.MESSAGE_DUPLICATE_ITEM));
    }

    @Test
    public void execute_isSameDuplicateSkill_throwsCommandException() {
        Skill validSkill = new SkillBuilder().withName(VALID_SKILL_NAME_GIT).build();
        EditSkillDescriptor editSkillDescriptor = new EditSkillDescriptor();
        setEditSkillDescriptor(editSkillDescriptor, validSkill);

        assertCommandFailure(new EditSkillCommand(INDEX_FIRST_ITEM, editSkillDescriptor),
                model,
                new CommandException(EditCommand.MESSAGE_DUPLICATE_ITEM));
    }

    @Test
    public void execute_editResume_success() {
        Resume validResume = TypicalResume.CE_RESUME;
        EditResumeDescriptor editResumeDescriptor = new EditResumeDescriptor();
        setEditResumeDescriptor(editResumeDescriptor, validResume);

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
    public void execute_outOfBoundsResume_throwsCommandException() {
        EditResumeDescriptor editResumeDescriptor = new EditResumeDescriptor();
        assertCommandFailure(new EditResumeCommand(INDEX_THIRD_ITEM, editResumeDescriptor),
                model,
                new CommandException(Messages.MESSAGE_INVALID_INDEX));
    }

    @Test
    public void execute_duplicateResume_throwsCommandException() {
        Resume validResume = TypicalResume.SE_RESUME;
        EditResumeDescriptor editResumeDescriptor = new EditResumeDescriptor();
        setEditResumeDescriptor(editResumeDescriptor, validResume);

        assertCommandFailure(new EditResumeCommand(INDEX_FIRST_ITEM, editResumeDescriptor),
                model,
                new CommandException(EditCommand.MESSAGE_DUPLICATE_ITEM));
    }

    @Test
    public void execute_isSameDuplicateResume_throwsCommandException() {
        Resume validResume = new ResumeBuilder().withName(VALID_RESUME_NAME_SE).build();
        EditResumeDescriptor editResumeDescriptor = new EditResumeDescriptor();
        setEditResumeDescriptor(editResumeDescriptor, validResume);

        assertCommandFailure(new EditResumeCommand(INDEX_FIRST_ITEM, editResumeDescriptor),
                model,
                new CommandException(EditCommand.MESSAGE_DUPLICATE_ITEM));
    }

    @Test
    public void equals() {
        EditInternshipDescriptor editInternshipDescriptor = new EditInternshipDescriptor();
        EditSkillDescriptor editSkillDescriptor = new EditSkillDescriptor();
        EditProjectDescriptor editProjectDescriptor = new EditProjectDescriptor();
        EditResumeDescriptor editResumeDescriptor = new EditResumeDescriptor();
        EditNoteDescriptor editNoteDescriptor = new EditNoteDescriptor();

        setEditInternshipDescriptor(editInternshipDescriptor, GOOGLE);
        setEditProjectDescriptor(editProjectDescriptor, ORBITAL);
        setEditSkillDescriptor(editSkillDescriptor, GIT);
        setEditResumeDescriptor(editResumeDescriptor, ME_RESUME);
        setEditNoteDescriptor(editNoteDescriptor, FINISH_CS_2103);
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


