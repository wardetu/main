package seedu.address.model.note;

import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NoteEntryList {
    private final ObservableList<NoteEntry> entries = FXCollections.observableArrayList();
    private final ObservableList<NoteEntry> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(entries);

    /**
     * Add a new note entry.
     * @param entry
     * @return
     */
    public NoteEntry addNoteEntry(NoteEntry entry) {
        entries.add(entry);
        return entry;
    }

    public NoteEntry deleteNoteEntry(NoteEntry entry) {
        if (entries.remove(entry)) {
            return entry.toCopy();
        }
        return null;
    }

    public int getSize() {
        return entries.size();
    }

    public boolean isEmpty() {
        if (entries.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getEntriesAsString() {
        StringBuilder build = new StringBuilder();
        int i = 0;
        for (NoteEntry entry: entries) {
            build.append("Note Entry: " + i + "\n");
            build.append(entry.toString());
            build.append("\n");
        }
        return build.toString();
    }

    public void loadData(NoteEntry[] arrayOfEntries) {
        Arrays.stream(arrayOfEntries).forEach(x -> addNoteEntry(x));
    }

    public ObservableList<NoteEntry> getInternalUnmodifiableList() {
        return internalUnmodifiableList;
    }

    public boolean contains(NoteEntry entry) {
        return entries.contains(entry);
    }
}
