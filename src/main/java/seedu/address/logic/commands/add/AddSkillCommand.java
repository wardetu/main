package seedu.address.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Skill;

/**
 * Adds a Skill Item to the address book.
 */
public class AddSkillCommand extends AddCommand {
    private final Skill toAdd;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a skill to the address book. "
            + "Parameters: "
            + PREFIX_ITEM + "TYPE "
            + PREFIX_NAME + "SKILL NAME "
            + PREFIX_LEVEL + "LEVEL "
            + "[" + PREFIX_TAG + "TAG]....\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ITEM + "proj "
            + PREFIX_NAME + "Git and Github "
            + PREFIX_LEVEL + "INTERMEDIATE "
            + PREFIX_TAG + "VCS "
            + PREFIX_TAG + "tech";

    /**
     * Creates an AddCommand to add the specified {@code skill}.
     */
    public AddSkillCommand(Skill skill) {
        requireNonNull(skill);
        toAdd = skill;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasSkill(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addSkill(toAdd);
        model.setSkillToDisplay();

        return new CommandResult(toAdd.toString(),
                String.format(MESSAGE_SUCCESS, toAdd.getType().getFullType(), toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddSkillCommand // instanceof handles nulls
                && toAdd.equals(((AddSkillCommand) other).toAdd));
    }
}