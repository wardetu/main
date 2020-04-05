package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Version;
import seedu.address.logic.commands.results.ClearCommandResult;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ResumeBook;
import seedu.address.model.VersionedResumeBook;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Resume book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String type = model.getDisplayType();
        model.setResumeBook(new VersionedResumeBook(new ResumeBook()));
        model.setItemsToDisplay(type);
        model.commitResumeBook();
        return new ClearCommandResult(" ", MESSAGE_SUCCESS, model.getDisplayType());
    }
}
