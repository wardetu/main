package seedu.address.logic.commands.note;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ENTRIES;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.list.ListCommand;
import seedu.address.model.Model;

/**
 * List all notes currently in the list.
 */
public class ListNoteCommand extends ListCommand {

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredNoteEntryList(PREDICATE_SHOW_ALL_ENTRIES);
        return new CommandResult("", String.format(MESSAGE_SUCCESS, "NoteEntry"), model.getDisplayType());
    }
}
