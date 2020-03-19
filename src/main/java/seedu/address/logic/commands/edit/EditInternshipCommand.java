package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Internship;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.tag.Tag;

/**
 * Edits an Internship Item in the address book.
 */
public class EditInternshipCommand extends EditCommand {

    private static final String MESSAGE_EDIT_INTERNSHIP_SUCCESS = "Edited Internship: %1$s";

    private EditInternshipDescriptor editInternshipDescriptor;

    /**
     * @param index                of the person in the filtered person list to edit
     * @param editInternshipDescriptor details to edit the internship with
     */
    public EditInternshipCommand(Index index, EditInternshipDescriptor editInternshipDescriptor) {
        super(index);
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

        model.setInternship(toEdit, editedResume);
        model.setInternshipToDisplay();
        model.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult(editedResume.toString(), String.format(MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedResume));
    }

    /**
     * Creates the edited Internship item from the internship to be edited and the descriptor.
     * @param toEdit Internship item to be edited
     * @param editInternshipDescriptor Descriptor parsed from input of user
     * @return Edited Internship item.
     */
    private static Internship createEditedInternship(
            Internship toEdit, EditInternshipDescriptor editInternshipDescriptor) {
        Name updatedName = editInternshipDescriptor.getName().orElse(toEdit.getName());
        Time updatedFrom = editInternshipDescriptor.getFrom().orElse(toEdit.getFrom());
        Time updatedTo = editInternshipDescriptor.getTo().orElse(toEdit.getTo());
        String updatedDesc = editInternshipDescriptor.getDescription().orElse(toEdit.getDescription());
        String updatedRole = editInternshipDescriptor.getRole().orElse(toEdit.getRole());
        Set<Tag> updatedTags = editInternshipDescriptor.getTags().orElse(toEdit.getTags());
        int id = toEdit.getId();
        return new Internship(updatedName, updatedRole, updatedFrom, updatedTo, updatedDesc, updatedTags, id);
    }
}
