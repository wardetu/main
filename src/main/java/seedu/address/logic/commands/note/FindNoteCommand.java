package seedu.address.logic.commands.note;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.find.FindCommand;
import seedu.address.model.Model;
import seedu.address.model.note.field.EntryTitleContainsKeywordsPredicate;

/**
 * Finds {@code NoteEntry} items in the address book whose title contains the keyword.
 * Keyword matching is case-insensitive.
 */
public class FindNoteCommand extends FindCommand {
    public FindNoteCommand(EntryTitleContainsKeywordsPredicate notePredicate) {
        super(notePredicate);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredNoteEntryList(notePredicate);
        return new CommandResult("",
                String.format(Messages.MESSAGE_ITEMS_LISTED,
                        model.getFilteredNoteEntryList().size(), "Note Entries"), model.getDisplayType());
    }
}
