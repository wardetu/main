package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ResumeEditCommand extends Command {
    public static final String COMMAND_WORD = "redit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits what an existing resume contains in the ResuMe "
            + "application with the specified index.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX "
            + "TYPE/ [ITEM_INDEX] "
            + "[MORE_TYPE/ [ITEM_ID...]]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_INTERNSHIP + " 1 3 4 "
            + PREFIX_SKILL + " 1 2 "
            + PREFIX_PROJECT + " 1 ";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
