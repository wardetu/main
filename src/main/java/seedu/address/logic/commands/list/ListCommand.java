package seedu.address.logic.commands.list;

import seedu.address.logic.commands.Command;

/**
 * Lists all items in the address book to the user.
 */
public abstract class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all %1$s items";

}
