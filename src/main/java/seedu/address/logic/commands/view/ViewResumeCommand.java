package seedu.address.logic.commands.view;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Stub for ViewResumeCommand.
 */
public class ViewResumeCommand extends ViewCommand {
    private Index targetIndex;

    public ViewResumeCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) {
        return null;
    }
}
