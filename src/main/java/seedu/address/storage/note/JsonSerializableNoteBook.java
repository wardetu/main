//package seedu.address.storage.note;
//
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonRootName;
//
//import seedu.address.commons.exceptions.IllegalValueException;
//import seedu.address.model.note.NoteBook;
//import seedu.address.storage.JsonAdaptedNoteEntry;
//
//@JsonRootName(value = "notebook")
//
//public class JsonSerializableNoteBook {
//
//    private final List<JsonAdaptedNoteEntry> entries = new ArrayList<>();
//
//    @JsonCreator
//    public JsonSerializableNoteBook(@JsonProperty("entries") List<JsonAdaptedNoteEntry> entries) {
//        this.entries.addAll(entries);
//    }
//
//    public JsonSerializableNoteBook(NoteBook source) {
//        entries.addAll(
//                source.getNoteEntryList().stream().
//                        map(JsonAdaptedNoteEntry::new).
//                        collect(Collectors.toList()));
//    }
//
//    public NoteBook toModelType() throws IllegalValueException {
//        NoteBook noteBook = new NoteBook();
//        for (JsonAdaptedNoteEntry jsonEntry: entries) {
//            noteBook.addNoteEntry(jsonEntry.toModelType());
//        }
//        return noteBook;
//    }
//}
