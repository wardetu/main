package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Resume;
import seedu.address.model.item.field.Name;
import seedu.address.model.tag.Tag;

/**
 * Edits a Resume Item in the address book.
 */
public class EditResumeCommand extends EditCommand {
    private static final String MESSAGE_EDIT_RESUME_SUCCESS = "Edited Resume: %1$s";
    private EditResumeDescriptor editResumeDescriptor;
    /**
     * @param index                of the person in the filtered person list to edit
     * @param editResumeDescriptor details to edit the resume with
     */
    public EditResumeCommand(Index index, EditResumeDescriptor editResumeDescriptor) {
        super(index);
        this.editResumeDescriptor = editResumeDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (index.getZeroBased() >= model.getResumeSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Resume toEdit = model.getResume(index);

        Resume editedResume = createEditedResume(toEdit, editResumeDescriptor);

        model.setResume(toEdit, editedResume);
        model.setResumeToDisplay();
        model.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult(editedResume.toString(), String.format(MESSAGE_EDIT_RESUME_SUCCESS, editedResume));
    }

    /**
     * Creates the edited Resume item from the resume to be edited and the descriptor.
     * @param toEdit resume item to be edited
     * @param editResumeDescriptor Descriptor parsed from input of user
     * @return Edited Resume item.
     */
    private static Resume createEditedResume(Resume toEdit, EditResumeDescriptor editResumeDescriptor) {
        Name updatedName = editResumeDescriptor.getName().orElse(toEdit.getName());
        Set<Tag> updatedTags = editResumeDescriptor.getTags().orElse(toEdit.getTags());
        int id = toEdit.getId();
        return new Resume(updatedName, id, updatedTags);
    }

}
