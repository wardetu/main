package seedu.address.logic.commands.view;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.note.Note;

/**
 * View a specified note.
 */
public class ViewNoteCommand extends ViewCommand {

    public ViewNoteCommand(Index targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex.getZeroBased() >= model.getNoteListSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Note toView = model.getNote(targetIndex);
        model.updateFilteredNoteList(Model.PREDICATE_SHOW_ALL_NOTES);

        return new CommandResult(toView.toString(),
                String.format(MESSAGE_VIEW_SUCCESS, toView), model.getDisplayType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewNoteCommand // instanceof handles nulls
                && targetIndex.equals(((ViewNoteCommand) other).targetIndex)); // state check
    }
}
