package seedu.address.logic.commands.delete;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    // TODO: Double check if this command format is all right
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) i/ (item type)\n"
            + "Example: " + COMMAND_WORD + " 1 i/ res";

    public static final String MESSAGE_DELETE_ITEM_SUCCESS = "Deleted Item: %1$s";

    protected final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

}
