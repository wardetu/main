package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.edit.EditUserCommand.MESSAGE_EDIT_PERSON_SUCCESS;
import static seedu.address.testutil.TypicalPerson.AMY;
import static seedu.address.testutil.TypicalPerson.BOB;

import javafx.scene.AmbientLight;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.edit.EditUserCommand;
import seedu.address.logic.commands.edit.EditUserDescriptor;
import seedu.address.logic.commands.results.EditUserCommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Person;
import seedu.address.testutil.EditUserDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.ResumeBookBuilder;
import seedu.address.testutil.TypicalResumeBook;

public class EditUserCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(
                new ResumeBookBuilder(TypicalResumeBook.TYPICAL_WITHOUT_GOOGLE)
                        .withPerson(new PersonBuilder(AMY).build()).build(),
                new UserPrefs());
        expectedModel = new ModelManager(
                new ResumeBookBuilder(TypicalResumeBook.TYPICAL_WITHOUT_GOOGLE)
                        .withPerson(new PersonBuilder(AMY).build()).build(),
                new UserPrefs());
    }

    @Test
    public void execute_editAllFields_success() {
        expectedModel.setUser(new PersonBuilder(BOB).build());
        EditUserCommand editUserCommand = new EditUserCommand(new EditUserDescriptorBuilder(BOB).build());
        assertCommandSuccess(editUserCommand, model,
                new EditUserCommandResult(BOB.toString(), MESSAGE_EDIT_PERSON_SUCCESS, expectedModel.getDisplayType()),
                expectedModel);
    }

//    @Test
//    public void execute_editSingleField_success() {
//        // Edit only name
//        expectedModel.setUser(new PersonBuilder(AMY).withName(VALID_NAME_BOB).build());
//        EditUserCommand editUserCommand = new EditUserCommand(
//                new EditUserDescriptorBuilder(AMY).withName(VALID_NAME_BOB).build());
//        assertCommandSuccess(editUserCommand, model,
//                new EditUserCommandResult(
//                        new PersonBuilder(AMY).withName(VALID_NAME_BOB).build().toString(),
//                        MESSAGE_EDIT_PERSON_SUCCESS,
//                        expectedModel.getDisplayType()),
//                expectedModel);
//
//        // Edit only Github
//        expectedModel.setUser(new PersonBuilder(AMY).withGithub(VALID_GITHUB_BOB).build());
//        editUserCommand = new EditUserCommand(
//                new EditUserDescriptorBuilder(AMY).withGithub(VALID_GITHUB_BOB).build());
//        assertCommandSuccess(editUserCommand, model,
//                new EditUserCommandResult(
//                        new PersonBuilder(AMY).withGithub(VALID_GITHUB_BOB).build().toString(),
//                        MESSAGE_EDIT_PERSON_SUCCESS,
//                        expectedModel.getDisplayType()),
//                expectedModel);
//    }
}
