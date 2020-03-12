package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    private String type;

    public ListCommand() {
        this.type = "res";
    }

    public ListCommand(String type) {
//        this.type = type;
        this.type = type;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setItemsToDisplay(type);
        model.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
