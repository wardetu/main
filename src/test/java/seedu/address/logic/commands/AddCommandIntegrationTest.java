package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.add.AddCommand;
import seedu.address.logic.commands.add.AddInternshipCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Internship;
import seedu.address.testutil.TypicalInternship;
import seedu.address.testutil.TypicalResumeBook;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalResumeBook.TYPICAL_WITHOUT_GOOGLE, new UserPrefs());
    }

    @Test
    public void execute_newInternship_success() {
        Internship validInternship = TypicalInternship.GOOGLE;

        Model expectedModel = new ModelManager(model.getResumeBook(), new UserPrefs());
        expectedModel.addInternship(validInternship);
        expectedModel.setInternshipToDisplay();

        assertCommandSuccess(new AddInternshipCommand(validInternship),
                model,
                new CommandResult(validInternship.toString(),
                        String.format(AddInternshipCommand.MESSAGE_SUCCESS,
                                validInternship.getType().getFullType())),
                expectedModel);
    }

    @Test
    public void execute_duplicateInternship_throwsCommandException() {
        Internship validInternship = TypicalInternship.NINJA_VAN;

        assertCommandFailure(new AddInternshipCommand(validInternship),
                model,
                new CommandException(AddCommand.MESSAGE_DUPLICATE_ITEM));
    }

    /*@Test
    public void execute_duplicatePerson_throwsCommandException() {
        PersonalDetail personInList = model.getResumeBook().getItemToDisplayList().get(0);
        assertCommandFailure(new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }
*/
}
