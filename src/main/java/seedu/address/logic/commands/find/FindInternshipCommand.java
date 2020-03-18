package seedu.address.logic.commands.find;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.field.NameContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;

/**
 * Finds Internship Items in the address book matching a keyword.
 */
public class FindInternshipCommand extends FindCommand {
    public FindInternshipCommand(NameContainsKeywordsPredicate predicate) {
        super(predicate);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setInternshipToDisplay();
        model.updateFilteredItemList(predicate);
        return new CommandResult("",
                String.format(Messages.MESSAGE_ITEMS_LISTED, model.getFilteredItemList().size(), "Internships"));
    }

}
