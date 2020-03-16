package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Skill;

import static java.util.Objects.requireNonNull;

/**
 * Deletes a Skill item.
 */
public class DeleteSkillCommand extends DeleteCommand {

    public DeleteSkillCommand(Index targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex.getZeroBased() >= model.getSkillSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Skill toDelete = model.getSkill(targetIndex);

        model.deleteSkill(toDelete);
        model.setSkillToDisplay();

        return new CommandResult(toDelete.toString(),
                String.format(MESSAGE_DELETE_ITEM_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteSkillCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteSkillCommand) other).targetIndex)); // state check
    }
}