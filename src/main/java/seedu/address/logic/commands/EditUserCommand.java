package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CAP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIVERSITY;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Person;
import seedu.address.model.item.field.DisplayPicture;
import seedu.address.model.item.field.Email;
import seedu.address.model.item.field.Github;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Phone;
import seedu.address.model.item.field.Time;

/**
 * This command edits the user profile information.
 */
public class EditUserCommand extends Command {

    public static final String COMMAND_WORD = "me";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the user profile in our resuMeme. "
            + "Parameters: "
            + PREFIX_DP + "DISPLAY PROFILE "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_GITHUB + "GITHUB "
            + PREFIX_UNIVERSITY + "UNIVERSITY "
            + PREFIX_MAJOR + "MAJOR "
            + PREFIX_FROM + "FROM "
            + PREFIX_TO + "TO "
            + PREFIX_CAP + "CAP\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DP + "/Users/nhamquochung/Desktop/test.png "
            + PREFIX_NAME + "HUNG "
            + PREFIX_PHONE + "91648888 "
            + PREFIX_EMAIL + "nhamhung.gttn@gmail.com "
            + PREFIX_GITHUB + "nhamhung "
            + PREFIX_UNIVERSITY + "NUS "
            + PREFIX_MAJOR + "CS "
            + PREFIX_FROM + "2018 "
            + PREFIX_TO + "2022 "
            + PREFIX_CAP + "5.0 ";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited User Profile: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private EditUserDescriptor editUserDescriptor;

    /**
     * @param editUserDescriptor details to edit the person with.
     */
    public EditUserCommand(EditUserDescriptor editUserDescriptor) {
        this.editUserDescriptor = editUserDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person userToEdit = model.getUser();
        DisplayPicture displayPicture = editUserDescriptor.getDisplayPicture().orElse(userToEdit.getDisplayPicture());
        Name name = editUserDescriptor.getName().orElse(userToEdit.getName());
        Phone phone = editUserDescriptor.getPhone().orElse(userToEdit.getPhone());
        Email email = editUserDescriptor.getEmail().orElse(userToEdit.getEmail());
        Github github = editUserDescriptor.getGithub().orElse(userToEdit.getGithub());
        String university = editUserDescriptor.getUniversity().orElse(userToEdit.getUniversity());
        String major = editUserDescriptor.getMajor().orElse(userToEdit.getMajor());
        Time from = editUserDescriptor.getFrom().orElse(userToEdit.getFrom());
        Time to = editUserDescriptor.getTo().orElse(userToEdit.getTo());
        double cap = editUserDescriptor.getCap().orElse(userToEdit.getCap());

        Person editedUser = new Person(displayPicture, name, phone, email, github, university, major, from, to, cap);
        model.setUser(editedUser);
        model.commitResumeBook();

        return new CommandResult(editedUser.toString(), String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedUser),
                true, false, false, false, false);
    }
}
