package seedu.address.logic.commands.find;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.note.field.NoteNameContainsKeywordsPredicate;

/**
 * Finds {@code NoteEntry} items in the address book whose title contains the keyword.
 * Keyword matching is case-insensitive.
 */
public class FindNoteCommand extends FindCommand {
    public FindNoteCommand(NoteNameContainsKeywordsPredicate notePredicate) {
        super(notePredicate);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredNoteList(notePredicate);
        return new CommandResult("",
                String.format(Messages.MESSAGE_ITEMS_LISTED,
                        model.getFilteredNoteList().size(), "Notes"), model.getDisplayType());
    }
}
