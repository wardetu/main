package seedu.address.logic.commands.find;

import seedu.address.model.item.field.NameContainsKeywordsPredicate;

public class FindProjectCommand extends FindCommand {
    public FindProjectCommand(NameContainsKeywordsPredicate predicate) {
        super(predicate);
    }
}
