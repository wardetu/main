package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import seedu.address.model.Model;
import seedu.address.model.item.field.Type;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all %1$s items";

    private Type itemType;

    // Default constructor that is somehow required for stubbing purposes
    public ListCommand() {

    }

    public ListCommand(String itemType) {
        this.itemType = new Type(itemType);
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setItemsToDisplay(itemType.getAlias());
        model.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult("", String.format(MESSAGE_SUCCESS, itemType.getFullType()));
    }
}
