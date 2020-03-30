package seedu.address.logic.commands.takenote;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.note.NoteEntry;
import seedu.address.model.note.NoteModel;

public class AddNoteCommand extends NoteCommand {
    public static final String COMMAND_WORD = "addnote";

    public static final String MESSAGE_SUCCESS = "New note added!";

    public static final String MESSAGE_DUPLICATE_ERROR = "This note already exists!";

    private final NoteEntry toAdd;

    public AddNoteCommand(NoteEntry note) {
        requireNonNull(note);
        this.toAdd = note;
    }

    @Override
    public CommandResult execute(NoteModel model) throws CommandException {
        requireNonNull(model);
        if (model.contains(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ERROR);
        }
        model.addNoteEntry(toAdd);
        return new CommandResult(toAdd.toString(), MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddNoteCommand)
                && toAdd.equals(((AddNoteCommand) other).toAdd);
    }
}
