package seedu.address.logic.commands;

import seedu.address.model.item.field.NameContainsKeywordsPredicate;

public class FindResumeCommand extends FindCommand {
    public FindResumeCommand(NameContainsKeywordsPredicate predicate) {
        super(predicate);
    }
}
