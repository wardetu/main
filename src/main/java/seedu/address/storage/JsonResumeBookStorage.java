package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyResumeBook;

/**
 * A class to access ResumeBook data stored as a json file on the hard disk.
 */
public class JsonResumeBookStorage implements ResumeBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonResumeBookStorage.class);

    private Path filePath;

    public JsonResumeBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getResumeBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyResumeBook> readResumeBook() throws DataConversionException {
        return readResumeBook(filePath);
    }

    /**
     * Similar to {@link #readResumeBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyResumeBook> readResumeBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableResumeBook> jsonResumeBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableResumeBook.class);
        if (!jsonResumeBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonResumeBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveResumeBook(ReadOnlyResumeBook resumeBook) throws IOException {
        saveResumeBook(resumeBook, filePath);
    }

    /**
     * Similar to {@link #saveResumeBook(ReadOnlyResumeBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveResumeBook(ReadOnlyResumeBook resumeBook, Path filePath) throws IOException {
        requireNonNull(resumeBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableResumeBook(resumeBook), filePath);
    }

}
