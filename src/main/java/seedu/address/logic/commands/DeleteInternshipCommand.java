package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;

/**
 * Deletes an Internship item.
 */
public class DeleteInternshipCommand extends DeleteCommand {

    public DeleteInternshipCommand(Index targetIndex) {
        super(targetIndex);
    }

}
