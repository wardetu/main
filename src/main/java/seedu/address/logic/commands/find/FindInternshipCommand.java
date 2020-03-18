package seedu.address.logic.commands.find;

import seedu.address.model.item.field.NameContainsKeywordsPredicate;

/**
 * Finds Internship Items in the address book matching a keyword.
 */
public class FindInternshipCommand extends FindCommand {
    public FindInternshipCommand(NameContainsKeywordsPredicate predicate) {
        super(predicate);
    }
}
