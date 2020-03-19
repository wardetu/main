package seedu.address.logic.commands.find;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.field.NameContainsKeywordsPredicate;

/**
 * Finds Skill Items in the address book matching a keyword.
 */
public class FindSkillCommand extends FindCommand {
    public FindSkillCommand(NameContainsKeywordsPredicate predicate) {
        super(predicate);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setSkillToDisplay();
        model.updateFilteredItemList(predicate);
        return new CommandResult("",
                String.format(Messages.MESSAGE_ITEMS_LISTED, model.getFilteredItemList().size(), "Skills"));
    }
}
