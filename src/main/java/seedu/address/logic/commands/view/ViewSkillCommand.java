package seedu.address.logic.commands.view;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * The command for viewing the details of a Skill item.
 */
public class ViewSkillCommand extends ViewCommand {
    private Index targetIndex;

    public ViewSkillCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) {
        return null;
    }
}
