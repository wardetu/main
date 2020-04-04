package seedu.address.logic.commands.stubs;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.view.ViewCommand;
import seedu.address.model.Model;

/**
 * Stub for ViewResumeCommand.
 */
public class ViewResumeCommandStub extends ViewCommand {
    private Index targetIndex;

    public ViewResumeCommandStub(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) {
        return null;
    }
}
