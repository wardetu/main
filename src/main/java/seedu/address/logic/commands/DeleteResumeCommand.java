package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;

/**
 * Deletes a Resume item.
 */
public class DeleteResumeCommand extends DeleteCommand {

    public DeleteResumeCommand(Index targetIndex) {
        super(targetIndex);
    }
}
