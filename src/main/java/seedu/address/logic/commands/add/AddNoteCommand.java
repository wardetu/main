package seedu.address.logic.commands.note;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.add.AddCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.note.NoteEntry;

/**
 * Add a NoteEntry into Note list.
 */
public class AddNoteCommand extends AddCommand {

    public static final String MESSAGE_SUCCESS = "New note added!";

    public static final String MESSAGE_DUPLICATE_ERROR = "This note already exists!";

    private final NoteEntry toAdd;

    /**
     * Constructor.
     * @param note is a NoteEntry.
     */
    public AddNoteCommand(NoteEntry note) {
        requireNonNull(note);
        this.toAdd = note;
    }

    /**
     * Add NoteEntry to the list.
     * @param model {@code Model} which the command should operate on.
     * @return Command Result.
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasNoteEntry(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ERROR);
        }
        model.addNoteEntry(toAdd);
        return new CommandResult(toAdd.toString(), MESSAGE_SUCCESS, model.getDisplayType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddNoteCommand)
                && toAdd.equals(((AddNoteCommand) other).toAdd);
    }
}
