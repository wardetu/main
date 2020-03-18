package seedu.address.logic.commands.edit;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Internship;
import seedu.address.model.item.field.Name;
import seedu.address.model.tag.Tag;

import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;

/**
 * Edits an Internship Item in the address book.
 */
public class EditInternshipCommand extends EditCommand {
    private static String MESSAGE_EDIT_INTERNSHIP_SUCCESS = "Edited Internship: %1$s";
    private EditInternshipDescriptor editInternshipDescriptor;
    /**
     * @param index                of the person in the filtered person list to edit
     * @param editInternshipDescriptor details to edit the resume with
     */
    public EditInternshipCommand(Index index, EditInternshipDescriptor editInternshipDescriptor) {
        super(index, editInternshipDescriptor);
        this.editInternshipDescriptor = editInternshipDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (index.getZeroBased() >= model.getResumeSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Internship toEdit = model.getInternship(index);

        Internship editedResume = createEditedInternship(toEdit, editInternshipDescriptor);

        // A bit weird to ask this since we always compare by id
//        if (!toEdit.isSame(editedResume) && model.hasItem(editedResume)) {
//            throw new CommandException(MESSAGE_DUPLICATE_RESUME);
//        }

        model.setInternship(toEdit, editedResume);
        model.setInternshipToDisplay();
        model.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult(editedResume.toString(), String.format(MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedResume));
    }

    private static Internship createEditedInternship(
            Internship toEdit, EditInternshipDescriptor editInternshipDescriptor) {
        Name updatedName = editInternshipDescriptor.getName().orElse(toEdit.getName());
        String updatedFrom = editInternshipDescriptor.getFrom().orElse(toEdit.getFrom());
        String updatedTo = editInternshipDescriptor.getTo().orElse(toEdit.getTo());
        String updatedDesc = editInternshipDescriptor.getDescription().orElse(toEdit.getDescription());
        String updatedRole = editInternshipDescriptor.getRole().orElse(toEdit.getRole());
        Set<Tag> updatedTags = editInternshipDescriptor.getTags().orElse(toEdit.getTags());
        int id = toEdit.getId();
        return new Internship(updatedName, updatedRole, updatedFrom, updatedTo, updatedDesc, updatedTags, id);
    }
}
