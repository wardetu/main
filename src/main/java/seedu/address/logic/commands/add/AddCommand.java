package seedu.address.logic.commands.add;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.Command;

/**
 * Adds a Item to the address book.
 */
public abstract class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an Item to the address book. "
            + "Parameters: "
            + PREFIX_ITEM + "TYPE "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_TAG + "TAG]....\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ITEM + "res "
            + PREFIX_NAME + "Resume 1 "
            + PREFIX_TAG + "frontend "
            + PREFIX_TAG + "tech";

    public static final String MESSAGE_SUCCESS = " New %1$s added:\n%2$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";
}
