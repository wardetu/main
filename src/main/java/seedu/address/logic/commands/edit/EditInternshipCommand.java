package seedu.address.logic.commands.edit;

import seedu.address.commons.core.index.Index;

/**
 * Edits an Internship Item in the address book.
 */
public class EditInternshipCommand extends EditCommand {
    /**
     * @param index                of the person in the filtered internship list to edit
     * @param editInternshipDescriptor details to edit the internship with
     */
    public EditInternshipCommand(Index index, EditInternshipDescriptor editInternshipDescriptor) {
        super(index, editInternshipDescriptor);
    }
}
