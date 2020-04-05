package seedu.address.logic.commands;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ITEM;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.add.AddInternshipCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.ItemIndicesBuilder;
import seedu.address.testutil.TypicalResumeBook;

// Test Suite (to remove after writing for all):

/*
 * 1. Invalid Index
 * 2. Invalid Internship Index
 * 3. Invalid Skill Index
 * 4. Invalid Project Index
 * 5. Test all I, S, P, valid
 * 6. Test all pairs (IS, SP, IP) valid
 * 7. Test all solo valid
 * 8. Test removal
 * 9. Test nothing supplied nothing changes
 * 10. duplicates
 */
public class ResumeEditCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalResumeBook.TYPICAL, new UserPrefs());
    }

    @Test
    public void constructor_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddInternshipCommand(null));
    }

    @Test
    public void execute_invalidResumeIndex_throwsCommandException() {
        Index invalidIndex = INDEX_THIRD_ITEM;
        Optional<List<Integer>> internshipIndices = ItemIndicesBuilder.empty();
        Optional<List<Integer>> projectIndices = ItemIndicesBuilder.empty();
        Optional<List<Integer>> skillIndices = ItemIndicesBuilder.empty();
        ResumeEditCommand resumeEditCommand = new ResumeEditCommand(invalidIndex, internshipIndices, projectIndices,
                skillIndices);
        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_INDEX, () -> resumeEditCommand.execute(model));
    }

    @Test
    public void execute_invalidInternshipIndex_throwsCommandException() {
        Index validIndex = INDEX_FIRST_ITEM;
        Optional<List<Integer>> internshipIndices = new ItemIndicesBuilder().add(3).build();
        Optional<List<Integer>> projectIndices = Optional.empty();
        Optional<List<Integer>> skillIndices = Optional.empty();
        ResumeEditCommand resumeEditCommand = new ResumeEditCommand(validIndex, internshipIndices, projectIndices,
                skillIndices);
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_REDIT_ITEM_INDEX, "internship", "3");
        assertThrows(CommandException.class, expectedMessage, () -> resumeEditCommand.execute(model));
    }

    @Test
    public void execute_invalidInternshipIndexMixedWithValid_throwsCommandException() {
        Index validIndex = INDEX_FIRST_ITEM;
        Optional<List<Integer>> internshipIndices = new ItemIndicesBuilder().add(4).add(1).add(3).build();
        Optional<List<Integer>> projectIndices = Optional.empty();
        Optional<List<Integer>> skillIndices = Optional.empty();
        ResumeEditCommand resumeEditCommand = new ResumeEditCommand(validIndex, internshipIndices, projectIndices,
                skillIndices);
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_REDIT_ITEM_INDEX, "internship", "4 3");
        assertThrows(CommandException.class, expectedMessage, () -> resumeEditCommand.execute(model));
    }

    @Test
    public void execute_invalidProjectIndex_throwsCommandException() {
        Index validIndex = INDEX_FIRST_ITEM;
        Optional<List<Integer>> internshipIndices = Optional.empty();
        Optional<List<Integer>> projectIndices = new ItemIndicesBuilder().add(3).build();
        Optional<List<Integer>> skillIndices = Optional.empty();
        ResumeEditCommand resumeEditCommand = new ResumeEditCommand(validIndex, internshipIndices, projectIndices,
                skillIndices);
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_REDIT_ITEM_INDEX, "project", "3");
        assertThrows(CommandException.class, expectedMessage, () -> resumeEditCommand.execute(model));
    }

    @Test
    public void execute_invalidProjectIndexMixedWithValid_throwsCommandException() {
        Index validIndex = INDEX_FIRST_ITEM;
        Optional<List<Integer>> internshipIndices = Optional.empty();
        Optional<List<Integer>> projectIndices = new ItemIndicesBuilder().add(4).add(1).add(3).build();
        Optional<List<Integer>> skillIndices = Optional.empty();
        ResumeEditCommand resumeEditCommand = new ResumeEditCommand(validIndex, internshipIndices, projectIndices,
                skillIndices);
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_REDIT_ITEM_INDEX, "project", "4 3");
        assertThrows(CommandException.class, expectedMessage, () -> resumeEditCommand.execute(model));
    }

    @Test
    public void execute_invalidSkillIndex_throwsCommandException() {
        Index validIndex = INDEX_FIRST_ITEM;
        Optional<List<Integer>> internshipIndices = Optional.empty();
        Optional<List<Integer>> projectIndices = Optional.empty();
        Optional<List<Integer>> skillIndices = new ItemIndicesBuilder().add(3).build();
        ResumeEditCommand resumeEditCommand = new ResumeEditCommand(validIndex, internshipIndices, projectIndices,
                skillIndices);
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_REDIT_ITEM_INDEX, "skill", "3");
        assertThrows(CommandException.class, expectedMessage, () -> resumeEditCommand.execute(model));
    }

    @Test
    public void execute_invalidSkillIndexMixedWithValid_throwsCommandException() {
        Index validIndex = INDEX_FIRST_ITEM;
        Optional<List<Integer>> internshipIndices = Optional.empty();
        Optional<List<Integer>> projectIndices = Optional.empty();
        Optional<List<Integer>> skillIndices = new ItemIndicesBuilder().add(4).add(1).add(3).build();
        ResumeEditCommand resumeEditCommand = new ResumeEditCommand(validIndex, internshipIndices, projectIndices,
                skillIndices);
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_REDIT_ITEM_INDEX, "skill", "4 3");
        assertThrows(CommandException.class, expectedMessage, () -> resumeEditCommand.execute(model));
    }
}
