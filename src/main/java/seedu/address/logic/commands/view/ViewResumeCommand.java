package seedu.address.logic.commands.view;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Resume;

/**
 * The command for viewing the details of a Resume item.
 */
public class ViewResumeCommand extends ViewCommand {

    public ViewResumeCommand(Index targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex.getZeroBased() >= model.getInternshipSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Resume toView = model.getResume(targetIndex);
        model.setResumeToDisplay();

        return new CommandResult(toView.toString(),
                String.format(MESSAGE_VIEW_SUCCESS, toView));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewResumeCommand // instanceof handles nulls
                && targetIndex.equals(((ViewResumeCommand) other).targetIndex)); // state check
    }
}
