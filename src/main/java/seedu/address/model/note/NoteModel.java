//package seedu.address.model.note;
//
//import static java.util.Objects.requireNonNull;
//
//import java.util.function.Predicate;
//import java.util.logging.Logger;
//
//import javafx.collections.ObservableList;
//import javafx.collections.transformation.FilteredList;
//import seedu.address.commons.core.LogsCenter;
//
//public class NoteModel {
//    private static final Predicate<NoteEntry> PREDICATE_SHOW_ALL_EVENTS = unused -> true;
//    private static final Logger logger = LogsCenter.getLogger(NoteModel.class);
//
//    private final NoteBook noteBook;
//    private final FilteredList<NoteEntry> filteredNoteBook;
//
//    public NoteModel() {
//        logger.fine("Initializing empty notebook");
//        this.noteBook = new NoteBook();
//        this.filteredNoteBook = new FilteredList<>(this.noteBook.getNoteEntryList());
//    }
//
//    public NoteModel(NoteBook noteBook) {
//        logger.fine("Initialize notebook from file");
//        this.noteBook = noteBook;
//        this.filteredNoteBook = new FilteredList<>(noteBook.getNoteEntryList());
//    }
//
//    public NoteEntry deleteNoteEntry(NoteEntry entry) {
//        return noteBook.deleteNoteEntry(entry);
//    }
//
//    public NoteEntry addNoteEntry(NoteEntry entry) {
//        return noteBook.addNoteEntry(entry);
//    }
//
//    public ObservableList<NoteEntry> getFilteredNoteEntryList() {
//        return filteredNoteBook;
//    }
//
//    public void updateFilteredNoteEntryList(Predicate<NoteEntry> predicate) {
//        requireNonNull(predicate);
//        filteredNoteBook.setPredicate(predicate);
//    }
//
//    public NoteBook getNoteBook() {
//        return this.noteBook;
//    }
//
//    public int getTotalNoteEntries() {
//        return filteredNoteBook.size();
//    }
//
//    public boolean contains(NoteEntry note) {
//        return noteBook.contains(note);
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//
//        if (!(other instanceof NoteModel)) {
//            return false;
//        }
//
//        NoteModel otherModel = (NoteModel) other;
//        return noteBook.equals(otherModel.noteBook)
//                && filteredNoteBook.equals(otherModel.filteredNoteBook);
//    }
//}
