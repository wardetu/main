//package seedu.address.storage.note;
//
//import java.io.IOException;
//import java.nio.file.Path;
//import java.util.Optional;
//
//import seedu.address.commons.exceptions.DataConversionException;
//import seedu.address.model.note.NoteBook;
//
//public interface NoteBookStorage {
//    Path getNoteBookFilePath();
//
//    void saveNoteBook(NoteBook noteBook) throws IOException;
//
//    void saveNoteBook(NoteBook noteBook, Path filePath) throws IOException;
//
//    Optional<NoteBook> readNoteBook(Path filePath) throws DataConversionException;
//
//    Optional<NoteBook> readNoteBook() throws DataConversionException;
//    }
