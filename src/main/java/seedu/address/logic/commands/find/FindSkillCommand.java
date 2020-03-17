package seedu.address.logic.commands.find;

import seedu.address.model.item.field.NameContainsKeywordsPredicate;

/**
 * Finds Skill Items in the address book matching a keyword.
 */
public class FindSkillCommand extends FindCommand {
    public FindSkillCommand(NameContainsKeywordsPredicate predicate) {
        super(predicate);
    }
}
