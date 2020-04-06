package seedu.address.logic.commands.find;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.field.NameContainsKeywordsPredicate;

/**
 * Finds {@code Note} items in the resume book whose name contains the keyword.
 * Keyword matching is case-insensitive.
 */
public class FindNoteCommand extends FindCommand {
    public FindNoteCommand(NameContainsKeywordsPredicate predicate) {
        super(predicate);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredNoteList(predicate);
        return new CommandResult("",
                String.format(Messages.MESSAGE_ITEMS_LISTED,
                        model.getFilteredNoteList().size(), "Notes"), model.getDisplayType());
    }
}
