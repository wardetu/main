//package seedu.address.model.note;
//
//import javafx.collections.ObservableList;
//
//public class NoteBook {
//    private final NoteEntryList entries;
//
//    public NoteBook() {
//        entries = new NoteEntryList();
//    }
//
//    public NoteBook loadData(NoteEntry[] arrayOfEntries) {
//        entries.loadData(arrayOfEntries);
//        return this;
//    }
//
//    public NoteEntry addNoteEntry(NoteEntry noteEntry) {
//        return entries.addNoteEntry(noteEntry);
//    }
//
//    public NoteEntry deleteNoteEntry(NoteEntry noteEntry) {
//        return entries.deleteNoteEntry(noteEntry);
//    }
//
//    public String getEntriesAsString() {
//        return entries.getEntriesAsString();
//    }
//
//    public int getOriginalSize() {
//        return entries.getInternalUnmodifiableList().size();
//    }
//
//    public ObservableList<NoteEntry> getNoteEntryList() {
//        return entries.getInternalUnmodifiableList();
//    }
//
//    public boolean contains(NoteEntry entry) {
//        return entries.contains(entry);
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        return other == this
//                || (other instanceof NoteBook
//                && entries.equals(((NoteBook) other).entries));
//    }
//
//    @Override
//    public int hashCode() {
//        return entries.hashCode();
//    }
//
//}
