package seedu.address.logic.commands.view;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * The command for viewing the details of an Internship item.
 */
public class ViewInternshipCommand extends ViewCommand {
    private Index targetIndex;

    public ViewInternshipCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) {
        return null;
    }
}
