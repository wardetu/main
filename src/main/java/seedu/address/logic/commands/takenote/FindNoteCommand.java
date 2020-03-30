package seedu.address.logic.commands.takenote;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.note.NoteModel;

public class FindNoteCommand extends NoteCommand {
    public static final String COMMAND_WORD = "findnote";

    private static String MESSAGE_SUCCESS = "%1$d entries listed! To get the original list, type list";

    private final String userInput;
    public FindNoteCommand(String userInput) {
        requireNonNull(userInput);
        this.userInput = userInput;
    }

    @Override
    public CommandResult execute(NoteModel model) {
        requireNonNull(model);
        model.updateFilteredNoteEntryList(entry -> entry.getTitle().toString().toLowerCase()
                .contains(userInput.toLowerCase()));
        return new CommandResult("", String.format(MESSAGE_SUCCESS, model.getTotalNoteEntries()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindNoteCommand // instanceof handles nulls
                && userInput.equals(((FindNoteCommand) other).userInput)); // state check
    }
}
