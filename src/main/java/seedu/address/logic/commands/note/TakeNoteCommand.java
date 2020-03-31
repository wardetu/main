package seedu.address.logic.commands.note;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

public class TakeNoteCommand extends Command {
    public static final String COMMAND_WORD = "note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Opens a new Panel where user can take notes.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_NOTE_MESSAGE = "Welcome to Note Taker. Here are your notes!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult("Open Note Book!", "Open Note Book!", true);
    }
}
