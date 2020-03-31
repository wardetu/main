//package seedu.address.storage.note;
//
//import static java.util.Objects.requireNonNull;
//
//import java.io.IOException;
//import java.nio.file.Path;
//import java.util.Optional;
//import java.util.logging.Logger;
//
//import seedu.address.commons.core.LogsCenter;
//import seedu.address.commons.exceptions.DataConversionException;
//import seedu.address.commons.util.FileUtil;
//import seedu.address.commons.util.JsonUtil;
//import seedu.address.logic.parser.exceptions.ParseException;
//import seedu.address.model.note.NoteBook;
//
//public class JsonNoteBookStorage implements NoteBookStorage {
//    private static final Logger logger = LogsCenter.getLogger(JsonNoteBookStorage.class);
//
//    private Path filePath;
//
//    public JsonNoteBookStorage(Path filePath) {
//        this.filePath = filePath;
//    }
//
//    public Path getNoteBookFilePath() {
//        return filePath;
//    }
//
//    @Override
//    public void saveNoteBook(NoteBook noteBook) throws IOException {
//        saveNoteBook(noteBook, filePath);
//    }
//
//    @Override
//    public void saveNoteBook(NoteBook noteBook, Path filePath) throws IOException {
//        requireNonNull(filePath);
//        FileUtil.createIfMissing(filePath);
//        JsonUtil.saveJsonFile(new JsonSerializableNoteBook(noteBook), filePath);
//    }
//
//    public Optional<NoteBook> readNoteBook() throws DataConversionException {
//        return readNoteBook(filePath);
//    }
//
//    public Optional<NoteBook> readNoteBook(Path filePath) throws DataConversionException {
//        requireNonNull(filePath);
//
//        Optional<JsonSerializableNoteBook> jsonNoteBook =
//                JsonUtil.readJsonFile(filePath, JsonSerializableNoteBook.class);
//
//        if (jsonNoteBook.isEmpty()) {
//            logger.info("This is an empty book");
//            return Optional.empty();
//        }
//
//        try {
//            logger.info("This is a filled book");
//            return Optional.of(jsonNoteBook.get().toModelType());
//        } catch (ParseException ex) {
//            logger.info("Illegal values in " + filePath + ": " + ex);
//            throw new DataConversionException(ex);
//        }
//    }
//
//}
