package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.note.Note;
import seedu.address.model.tag.Tag;

/**
 * Edit a specified NoteEntry.
 */
public class EditNoteCommand extends EditCommand {

    private static final String EXAMPLE = "Example: "
            + COMMAND_WORD + " 1 "
            + PREFIX_ITEM + " note "
            + PREFIX_NAME + " Reminder to finish up Resume 3 "
            + PREFIX_TIME + " 04-2020 ";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.\n" + EXAMPLE;
    private static final String MESSAGE_EDIT_INTERNSHIP_SUCCESS = "Edited This Note!";

    private EditNoteDescriptor editNoteDescriptor;

    public EditNoteCommand(Index index, EditNoteDescriptor editNoteDescriptor) {
        super(index);
        this.editNoteDescriptor = editNoteDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (index.getZeroBased() >= model.getNoteListSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Note toEdit = model.getNote(index);

        Note editedNote = createEditedNoteEntry(toEdit, editNoteDescriptor);

        model.setNote(toEdit, editedNote);
        model.updateFilteredNoteList(Model.PREDICATE_SHOW_ALL_ENTRIES);
        model.commitResumeBook();
        return new CommandResult(editedNote.toString(),
                String.format(MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedNote), model.getDisplayType());
    }

    /**
     * Create a new NoteEntry after it has been edited.
     * @param toEdit
     * @param editNoteDescriptor
     * @return
     */
    public Note createEditedNoteEntry(Note toEdit, EditNoteDescriptor editNoteDescriptor) {
        Name updateName = editNoteDescriptor.getName().orElse(toEdit.getName());
        Time updateTime = editNoteDescriptor.getTime().orElse(toEdit.getTime());
        boolean updateDone = editNoteDescriptor.getDone().orElse(toEdit.isDone());
        Set<Tag> updateTags = editNoteDescriptor.getTags().orElse(toEdit.getTags());
        int id = toEdit.getId();
        return new Note(updateName, updateTime, updateDone, updateTags, id);
    }
}
