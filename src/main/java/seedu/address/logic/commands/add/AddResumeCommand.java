package seedu.address.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Resume;

/**
 * Adds a Resume Item to the address book.
 */
public class AddResumeCommand extends AddCommand {

    private final Resume toAdd;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a resume to the address book. "
            + "Parameters: "
            + PREFIX_ITEM + "TYPE "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_TAG + "TAG]....\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ITEM + "res "
            + PREFIX_NAME + "Resume 1 "
            + PREFIX_TAG + "frontend "
            + PREFIX_TAG + "tech";

    /**
     * Creates an AddCommand to add the specified {@code resume}
     */
    public AddResumeCommand(Resume resume) {
        requireNonNull(resume);
        toAdd = resume;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasResume(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addResume(toAdd);
        model.setResumeToDisplay();

        return new CommandResult(toAdd.toString(),
                String.format(MESSAGE_SUCCESS, toAdd.getType().getFullType(), toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddResumeCommand // instanceof handles nulls
                && toAdd.equals(((AddResumeCommand) other).toAdd));
    }
}
