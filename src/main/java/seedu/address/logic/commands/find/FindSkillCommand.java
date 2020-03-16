package seedu.address.logic.commands;

import seedu.address.model.item.field.NameContainsKeywordsPredicate;

public class FindSkillCommand extends FindCommand {
    public FindSkillCommand(NameContainsKeywordsPredicate predicate) {
        super(predicate);
    }
}
