package seedu.address.logic.commands.find;

import seedu.address.model.item.field.NameContainsKeywordsPredicate;

/**
 * Finds Project Items in the address book matching a keyword.
 */
public class FindProjectCommand extends FindCommand {
    public FindProjectCommand(NameContainsKeywordsPredicate predicate) {
        super(predicate);
    }
}
