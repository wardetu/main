package seedu.address.logic.commands.stubs;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.view.ViewCommand;
import seedu.address.model.Model;

public class ViewInternshipCommandStub extends ViewCommand {
    private Index targetIndex;

    public ViewInternshipCommandStub(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) {
        return null;
    }
}
