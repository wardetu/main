package seedu.address.logic.commands;

import seedu.address.model.item.field.NameContainsKeywordsPredicate;

public class FindInternshipCommand extends FindCommand {
    public FindInternshipCommand(NameContainsKeywordsPredicate predicate) {
        super(predicate);
    }
}
