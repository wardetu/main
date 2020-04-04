package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.results.ClearCommandResult;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ResumeBook;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Resume book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setResumeBook(new ResumeBook());
        return new ClearCommandResult("", MESSAGE_SUCCESS, model.getDisplayType());
    }
}
