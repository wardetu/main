package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.view.ViewCommand;
import seedu.address.logic.commands.view.ViewInternshipCommand;
import seedu.address.logic.commands.view.ViewProjectCommand;
import seedu.address.logic.commands.view.ViewResumeCommand;
import seedu.address.logic.commands.view.ViewSkillCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Project;
import seedu.address.model.item.Resume;
import seedu.address.model.item.Skill;
import seedu.address.model.util.ItemUtil;
import seedu.address.testutil.TypicalInternship;
import seedu.address.testutil.TypicalProject;
import seedu.address.testutil.TypicalResume;
import seedu.address.testutil.TypicalResumeBook;
import seedu.address.testutil.TypicalSkill;

/**
 * Contains integration tests (interaction with the Model) for {@code ViewCommand}.
 */
public class ViewCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalResumeBook.TYPICAL_WITHOUT_GOOGLE, new UserPrefs());
    }

    @Test
    public void execute_viewInternship_success() {
        Internship validInternship = TypicalInternship.NINJA_VAN;

        Model expectedModel = new ModelManager(model.getResumeBook(), new UserPrefs());
        expectedModel.setInternshipToDisplay();

        assertCommandSuccess(new ViewInternshipCommand(Index.fromOneBased(1)),
                model,
                new CommandResult(validInternship.toString(),
                        ViewInternshipCommand.MESSAGE_VIEW_SUCCESS,
                        ItemUtil.INTERNSHIP_ALIAS),
                expectedModel);
    }

    @Test
    public void execute_outOfBoundsInternship_throwsCommandException() {
        assertCommandFailure(new ViewInternshipCommand(Index.fromOneBased(2)),
                model,
                new CommandException(Messages.MESSAGE_INVALID_INDEX));
    }
//
//    @Test
//    public void execute_newResume_success() {
//        Resume validResume = TypicalResume.SE_RESUME;
//
//        Model expectedModel = new ModelManager(model.getResumeBook(), new UserPrefs());
//        expectedModel.addResume(validResume);
//        expectedModel.setResumeToDisplay();
//
//        assertCommandSuccess(new ViewResumeCommand(validResume),
//                model,
//                new CommandResult(validResume.toString(),
//                        String.format(ViewResumeCommand.MESSAGE_SUCCESS,
//                                validResume.getType().getFullType()),
//                        ItemUtil.RESUME_ALIAS),
//                expectedModel);
//    }
//
//    @Test
//    public void execute_duplicateResume_throwsCommandException() {
//        Resume validResume = TypicalResume.ME_RESUME;
//
//        assertCommandFailure(new ViewResumeCommand(validResume),
//                model,
//                new CommandException(ViewCommand.MESSAGE_DUPLICATE_ITEM));
//    }
//
//    @Test
//    public void execute_newSkill_success() {
//        Skill validSkill = TypicalSkill.GIT;
//
//        Model expectedModel = new ModelManager(model.getResumeBook(), new UserPrefs());
//        expectedModel.addSkill(validSkill);
//        expectedModel.setSkillToDisplay();
//
//        assertCommandSuccess(new ViewSkillCommand(validSkill),
//                model,
//                new CommandResult(validSkill.toString(),
//                        String.format(ViewSkillCommand.MESSAGE_SUCCESS,
//                                validSkill.getType().getFullType()),
//                        ItemUtil.SKILL_ALIAS),
//                expectedModel);
//    }
//
//    @Test
//    public void execute_duplicateSkill_throwsCommandException() {
//        Skill validSkill = TypicalSkill.REACT;
//
//        assertCommandFailure(new ViewSkillCommand(validSkill),
//                model,
//                new CommandException(ViewCommand.MESSAGE_DUPLICATE_ITEM));
//    }
//
//    @Test
//    public void execute_newProject_success() {
//        Project validProject = TypicalProject.DUKE;
//
//        Model expectedModel = new ModelManager(model.getResumeBook(), new UserPrefs());
//        expectedModel.addProject(validProject);
//        expectedModel.setProjectToDisplay();
//
//        assertCommandSuccess(new ViewProjectCommand(validProject),
//                model,
//                new CommandResult(validProject.toString(),
//                        String.format(ViewProjectCommand.MESSAGE_SUCCESS,
//                                validProject.getType().getFullType()),
//                        ItemUtil.PROJECT_ALIAS),
//                expectedModel);
//    }
//
//    @Test
//    public void execute_duplicateProject_throwsCommandException() {
//        Project validProject = TypicalProject.ORBITAL;
//
//        assertCommandFailure(new ViewProjectCommand(validProject),
//                model,
//                new CommandException(ViewCommand.MESSAGE_DUPLICATE_ITEM));
//    }

//    @Test
}
