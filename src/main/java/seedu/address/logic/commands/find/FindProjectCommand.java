package seedu.address.logic.commands;

import seedu.address.model.item.field.NameContainsKeywordsPredicate;

public class FindProjectCommand extends FindCommand {
    public FindProjectCommand(NameContainsKeywordsPredicate predicate) {
        super(predicate);
    }
}
