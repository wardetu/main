package seedu.address.logic.commands.edit;

import seedu.address.commons.core.index.Index;

/**
 * Edits a Project Item in the address book.
 */
public class EditProjectCommand extends EditCommand {
    /**
     * @param index                of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditProjectCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        super(index, editPersonDescriptor);
    }
}
