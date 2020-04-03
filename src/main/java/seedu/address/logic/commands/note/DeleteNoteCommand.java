package seedu.address.logic.commands.note;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ENTRIES;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.delete.DeleteCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.note.NoteEntry;

/**
 * Deletes a NoteEntry item.
 */
public class DeleteNoteCommand extends DeleteCommand {

    public DeleteNoteCommand(Index targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex.getZeroBased() >= model.getNoteEntrySize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        NoteEntry toDelete = model.getNoteEntry(targetIndex);

        model.deleteNoteEntry(toDelete);
        model.updateFilteredNoteEntryList(PREDICATE_SHOW_ALL_ENTRIES);
        model.commitResumeBook();

        return new CommandResult(toDelete.toString(),
                String.format(MESSAGE_DELETE_ITEM_SUCCESS, toDelete.getType().getFullType()), model.getDisplayType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteNoteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteNoteCommand) other).targetIndex)); // state check
    }
}
