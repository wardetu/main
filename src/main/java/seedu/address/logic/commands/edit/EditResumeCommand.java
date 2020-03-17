package seedu.address.logic.commands.edit;

import seedu.address.commons.core.index.Index;

/**
 * Edits a Resume Item in the address book.
 */
public class EditResumeCommand extends EditCommand {
    /**
     * @param index                of the person in the filtered person list to edit
     * @param editResumeDescriptor details to edit the resume with
     */
    public EditResumeCommand(Index index, EditResumeDescriptor editResumeDescriptor) {
        super(index, editResumeDescriptor);
    }
}
