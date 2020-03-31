//package seedu.address.logic.commands.note;
//
//import static java.util.Objects.requireNonNull;
//
//import java.util.List;
//
//import seedu.address.commons.core.index.Index;
//import seedu.address.logic.commands.Command;
//import seedu.address.logic.commands.CommandResult;
//import seedu.address.logic.commands.exceptions.CommandException;
//import seedu.address.model.Model;
//import seedu.address.model.note.NoteEntry;
//import seedu.address.model.note.NoteModel;
//
//public class DeleteNoteCommand extends Command {
//    public static final String COMMAND_WORD = "delnote";
//
//    public static final String MESSAGE_SUCCESS = "Deleted note: %1$s";
//
//    public static final String MESSAGE_ERROR = "To delete, index of deleted item has to be within range!";
//
//    private final Index indexToDelete;
//
//    public DeleteNoteCommand(Index index) {
//        requireNonNull(index);
//        this.indexToDelete = index;
//    }
//
//    @Override
//    public CommandResult execute(Model model) throws CommandException {
//        return null;
//    }
//
//    @Override
//    public CommandResult execute(NoteModel model) throws CommandException {
//        requireNonNull(model);
//        List<NoteEntry> list = model.getFilteredNoteEntryList();
//
//        if (indexToDelete.getZeroBased() >= list.size()) {
//            throw new CommandException(MESSAGE_ERROR);
//        }
//
//        NoteEntry toDelete = list.get(indexToDelete.getZeroBased());
//        NoteEntry deletedEntry = model.deleteNoteEntry(toDelete);
//        return new CommandResult(deletedEntry.toString(), String.format(MESSAGE_SUCCESS, deletedEntry));
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        return other == this
//                || (other instanceof DeleteNoteCommand)
//                && indexToDelete.equals(((DeleteNoteCommand) other).indexToDelete);
//    }
//}
