package seedu.address.logic.commands.edit;

import seedu.address.commons.core.index.Index;

/**
 * Edits a Resume Item in the address book.
 */
public class EditResumeCommand extends EditCommand {
    /**
     * @param index                of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditResumeCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        super(index, editPersonDescriptor);
    }
}
