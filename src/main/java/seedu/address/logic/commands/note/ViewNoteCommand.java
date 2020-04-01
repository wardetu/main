package seedu.address.logic.commands.note;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.view.ViewCommand;
import seedu.address.model.Model;
import seedu.address.model.note.NoteEntry;

public class ViewNoteCommand extends ViewCommand {

    public ViewNoteCommand(Index targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex.getZeroBased() >= model.getNoteEntrySize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        NoteEntry toView = model.getNoteEntry(targetIndex);
        model.updateFilteredNoteEntryList(Model.PREDICATE_SHOW_ALL_ENTRIES);

        return new CommandResult(toView.toString(),
                String.format(MESSAGE_VIEW_SUCCESS, toView), true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewNoteCommand // instanceof handles nulls
                && targetIndex.equals(((ViewNoteCommand) other).targetIndex)); // state check
    }
}
