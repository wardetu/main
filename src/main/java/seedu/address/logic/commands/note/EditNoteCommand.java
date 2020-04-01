package seedu.address.logic.commands.note;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.edit.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.note.NoteEntry;
import seedu.address.model.note.field.Description;
import seedu.address.model.note.field.Place;
import seedu.address.model.note.field.Title;
import seedu.address.model.tag.Tag;

/**
 * Edit a specified NoteEntry.
 */
public class EditNoteCommand extends EditCommand {

    private static final String EXAMPLE = "Example: "
            + COMMAND_WORD + " 1 "
            + PREFIX_ITEM + " note "
            + PREFIX_NAME + " Reminder "
            + PREFIX_TITLE + " Finish up Resume 3 "
            + PREFIX_TIME + " 04-2020 "
            + PREFIX_PLACE + " Home "
            + PREFIX_DESCRIPTION + " Add new internships and skills  "
            + PREFIX_TAG + " Shopee ";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.\n" + EXAMPLE;
    private static final String MESSAGE_EDIT_INTERNSHIP_SUCCESS = "Edited Internship: %1$s";

    private EditNoteDescriptor editNoteDescriptor;

    public EditNoteCommand(Index index, EditNoteDescriptor editNoteDescriptor) {
        super(index);
        this.editNoteDescriptor = editNoteDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (index.getZeroBased() >= model.getNoteEntrySize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        NoteEntry toEdit = model.getNoteEntry(index);

        NoteEntry editedNoteEntry = createEditedNoteEntry(toEdit, editNoteDescriptor);

        model.setNoteEntry(toEdit, editedNoteEntry);
        model.updateFilteredNoteEntryList(Model.PREDICATE_SHOW_ALL_ENTRIES);
        model.commitResumeBook();
        return new CommandResult(editedNoteEntry.toString(),
                String.format(MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedNoteEntry), true);
    }

    /**
     * Create a new NoteEntry after it has been edited.
     * @param toEdit
     * @param editNoteDescriptor
     * @return
     */
    public NoteEntry createEditedNoteEntry(NoteEntry toEdit, EditNoteDescriptor editNoteDescriptor) {
        Name updatedName = editNoteDescriptor.getName().orElse(toEdit.getName());
        Title updateTitle = editNoteDescriptor.getTitle().orElse(toEdit.getTitle());
        Time updateTime = editNoteDescriptor.getTime().orElse(toEdit.getTime());
        Place updatedPlace = editNoteDescriptor.getPlace().orElse(toEdit.getPlace());
        Description updatedDescription = editNoteDescriptor.getDescription().orElse(toEdit.getDescription());
        Set<Tag> updatedTags = editNoteDescriptor.getTags().orElse(toEdit.getTags());
        int id = toEdit.getId();
        return new NoteEntry(updatedName, updateTitle, updateTime, updatedPlace, updatedDescription, updatedTags, id);
    }
}
