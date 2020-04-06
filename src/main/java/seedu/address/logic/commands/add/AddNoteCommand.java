package seedu.address.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.note.Note;

/**
 * Add a NoteEntry into Note list.
 */
public class AddNoteCommand extends AddCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a note to your note list.\n"
            + COMMAND_WORD + " "
            + PREFIX_ITEM + "TYPE "
            + PREFIX_NAME + "TITLE "
            + PREFIX_TIME + "TIME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ITEM + "note "
            + PREFIX_NAME + "Finish Resume 3 "
            + PREFIX_TIME + "04-2020 ";

    public static final String MESSAGE_SUCCESS = "New note added!";

    public static final String MESSAGE_DUPLICATE_ERROR = "This note already exists!";

    public static final String MESSAGE_CONSTRAINTS = "Every note should have a name and a time so "
            + "it can serve you better!";

    private final Note toAdd;

    /**
     * Constructor.
     * @param note is a NoteEntry.
     */
    public AddNoteCommand(Note note) {
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
        if (model.hasNote(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ERROR);
        }
        model.addNote(toAdd);
        return new CommandResult(toAdd.toString(), MESSAGE_SUCCESS, model.getDisplayType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddNoteCommand)
                && toAdd.equals(((AddNoteCommand) other).toAdd);
    }
}
