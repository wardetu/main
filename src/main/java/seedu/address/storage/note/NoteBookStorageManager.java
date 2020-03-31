//package seedu.address.storage.note;
//
//import java.io.IOException;
//import java.nio.file.Path;
//import java.util.Optional;
//import java.util.logging.Logger;
//
//import seedu.address.commons.core.LogsCenter;
//import seedu.address.commons.exceptions.DataConversionException;
//import seedu.address.model.note.NoteBook;
//import seedu.address.storage.StorageManager;
//
//public class NoteBookStorageManager implements NoteBookStorage {
//    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
//    private NoteBookStorage noteBookStorage;
//
//    public NoteBookStorageManager(NoteBookStorage noteBookStorage) {
//        super();
//        this.noteBookStorage = noteBookStorage;
//    }
//
//    @Override
//    public Path getNoteBookFilePath() {
//        return noteBookStorage.getNoteBookFilePath();
//    }
//
//    @Override
//    public Optional<NoteBook> readNoteBook() throws DataConversionException {
//        return readNoteBook(noteBookStorage.getNoteBookFilePath());
//    }
//
//    @Override
//    public Optional<NoteBook> readNoteBook(Path filePath) throws DataConversionException {
//        logger.fine("Attempting to read data from file: " + filePath);
//        return noteBookStorage.readNoteBook(filePath);
//    }
//
//    @Override
//    public void saveNoteBook(NoteBook noteBook) throws IOException {
//        saveNoteBook(noteBook, noteBookStorage.getNoteBookFilePath());
//    }
//
//    @Override
//    public void saveNoteBook(NoteBook noteBook, Path filePath) throws IOException {
//        logger.fine("Attempting to write to data file: " + filePath);
//        noteBookStorage.saveNoteBook(noteBook, filePath);
//    }
//}
