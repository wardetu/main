package seedu.address.logic.commands.find;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.field.NameContainsKeywordsPredicate;

/**
 * Finds Resume Items in the address book matching a keyword.
 */
public class FindResumeCommand extends FindCommand {
    public FindResumeCommand(NameContainsKeywordsPredicate predicate) {
        super(predicate);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setResumeToDisplay();
        model.updateFilteredItemList(predicate);
        return new CommandResult("",
                String.format(Messages.MESSAGE_ITEMS_LISTED, model.getFilteredItemList().size(), "Resumes"));
    }
}
