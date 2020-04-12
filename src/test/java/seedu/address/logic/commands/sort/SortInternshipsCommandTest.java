package seedu.address.logic.commands.sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.SortCommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Internship;
import seedu.address.model.util.ItemUtil;
import seedu.address.testutil.TypicalResumeBook;

public class SortInternshipsCommandTest {
    private Model expectedModel;
    private Model model;

    @BeforeEach
    public void setUp() {
        expectedModel = new ModelManager(TypicalResumeBook.TYPICAL_WITHOUT_GOOGLE, new UserPrefs());
        model = new ModelManager(TypicalResumeBook.TYPICAL_WITHOUT_GOOGLE, new UserPrefs());
    }

    @Test
    public void execute_sortInternshipsByNameReverseFalse_success() {
        expectedModel.sortInternships(Comparator.comparing(Internship::getName));
        expectedModel.setInternshipToDisplay();

        assertEquals(ItemUtil.INTERNSHIP_ALIAS, expectedModel.getDisplayType());
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, Internship.class.getSimpleName());
        CommandResult commandResult =
                new SortCommandResult(expectedMessage, expectedModel.getDisplayType());
        assertCommandSuccess(new SortInternshipsCommand("name", false), model, commandResult, expectedModel);
    }

    @Test
    public void execute_sortInternshipsByTimeReverseTrue_success() {
        expectedModel.sortInternships(Comparator.comparing(Internship::getFrom).reversed());
        expectedModel.setInternshipToDisplay();

        assertEquals(ItemUtil.INTERNSHIP_ALIAS, expectedModel.getDisplayType());
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, Internship.class.getSimpleName());
        CommandResult commandResult =
                new SortCommandResult(expectedMessage, expectedModel.getDisplayType());
        assertCommandSuccess(
                new SortInternshipsCommand("time", true), model, commandResult, expectedModel);
    }

}
