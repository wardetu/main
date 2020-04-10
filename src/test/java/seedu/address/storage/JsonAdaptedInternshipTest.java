package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.util.JsonUtil.readJsonFile;
import static seedu.address.storage.JsonAdaptedInternship.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.Internship;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.testutil.InternshipBuilder;
import seedu.address.testutil.TypicalInternship;

public class JsonAdaptedInternshipTest {
    private static final Path TEST_DATA_FOLDER = Paths
            .get("src", "test", "data", "JsonAdaptedInternshipTest");

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void load_jsonToJsonAdaptedInternship_success() throws DataConversionException {
        Optional<JsonAdaptedInternship> jsonAdaptedInternship =
                readJsonFile(addToTestDataPathIfNotNull("validInternship.json"),
                        JsonAdaptedInternship.class);
        JsonAdaptedInternship expectedJsonAdapatedInternship =
                new JsonAdaptedInternship(new InternshipBuilder(TypicalInternship.PAYPAL).build());
        assertEquals(expectedJsonAdapatedInternship, jsonAdaptedInternship.get());
    }

    @Test
    public void loadToModel_jsonToInternship_success() throws DataConversionException, IllegalValueException {
        Optional<JsonAdaptedInternship> jsonAdaptedInternship =
                readJsonFile(addToTestDataPathIfNotNull("validInternship.json"),
                        JsonAdaptedInternship.class);
        Internship expectedInternship = new InternshipBuilder(TypicalInternship.PAYPAL).build();
        assertEquals(expectedInternship, jsonAdaptedInternship.get().toModelType());
    }

    @Test
    public void loadToModel_missingName_failure() throws DataConversionException {
        Optional<JsonAdaptedInternship> jsonAdaptedInternship =
                readJsonFile(addToTestDataPathIfNotNull("missingNameInternship.json"),
                        JsonAdaptedInternship.class);
        assertThrows(IllegalValueException.class,
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()),
                jsonAdaptedInternship.get()::toModelType);
    }

    @Test
    public void loadToModel_invalidName_failure() throws DataConversionException {
        Optional<JsonAdaptedInternship> jsonAdaptedInternship =
                readJsonFile(addToTestDataPathIfNotNull("invalidNameInternship.json"),
                        JsonAdaptedInternship.class);
        assertThrows(IllegalValueException.class,
                Name.MESSAGE_CONSTRAINTS,
                jsonAdaptedInternship.get()::toModelType);
    }

    @Test
    public void loadToModel_invalidId_failure() throws DataConversionException {
        Optional<JsonAdaptedInternship> jsonAdaptedInternship =
                readJsonFile(addToTestDataPathIfNotNull("invalidIdInternship.json"),
                        JsonAdaptedInternship.class);
        assertThrows(IllegalValueException.class,
                "The id field can only be an integer.",
                jsonAdaptedInternship.get()::toModelType);

        jsonAdaptedInternship =
                readJsonFile(addToTestDataPathIfNotNull("negativeIdInternship.json"),
                        JsonAdaptedInternship.class);
        assertThrows(IllegalValueException.class,
                "The id field must not be negative.",
                jsonAdaptedInternship.get()::toModelType);
    }

    @Test
    public void loadToModel_missingRole_failure() throws DataConversionException {
        Optional<JsonAdaptedInternship> jsonAdaptedInternship =
                readJsonFile(addToTestDataPathIfNotNull("missingRoleInternship.json"),
                        JsonAdaptedInternship.class);
        assertThrows(IllegalValueException.class,
                String.format(MISSING_FIELD_MESSAGE_FORMAT, "role"),
                jsonAdaptedInternship.get()::toModelType);
    }

    @Test
    public void loadToModel_missingFrom_failure() throws DataConversionException {
        Optional<JsonAdaptedInternship> jsonAdaptedInternship =
                readJsonFile(addToTestDataPathIfNotNull("missingFromInternship.json"),
                        JsonAdaptedInternship.class);
        assertThrows(IllegalValueException.class,
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName()),
                jsonAdaptedInternship.get()::toModelType);
    }

    @Test
    public void loadToModel_invalidFrom_failure() throws DataConversionException {
        Optional<JsonAdaptedInternship> jsonAdaptedInternship =
                readJsonFile(addToTestDataPathIfNotNull("invalidFromInternship.json"),
                        JsonAdaptedInternship.class);
        assertThrows(IllegalValueException.class,
                Time.MESSAGE_CONSTRAINTS,
                jsonAdaptedInternship.get()::toModelType);
    }

    @Test
    public void loadToModel_missingTo_failure() throws DataConversionException {
        Optional<JsonAdaptedInternship> jsonAdaptedInternship =
                readJsonFile(addToTestDataPathIfNotNull("missingToInternship.json"),
                        JsonAdaptedInternship.class);
        assertThrows(IllegalValueException.class,
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName()),
                jsonAdaptedInternship.get()::toModelType);
    }

    @Test
    public void loadToModel_invalidTo_failure() throws DataConversionException {
        Optional<JsonAdaptedInternship> jsonAdaptedInternship =
                readJsonFile(addToTestDataPathIfNotNull("invalidToInternship.json"),
                        JsonAdaptedInternship.class);
        assertThrows(IllegalValueException.class,
                Time.MESSAGE_CONSTRAINTS,
                jsonAdaptedInternship.get()::toModelType);
    }

    @Test
    public void loadToModel_invalidFromTo_failure() throws DataConversionException {
        Optional<JsonAdaptedInternship> jsonAdaptedInternship =
                readJsonFile(addToTestDataPathIfNotNull("invalidFromToInternship.json"),
                        JsonAdaptedInternship.class);
        assertThrows(IllegalValueException.class,
                "The \"to\" field must not precede the \"from\" field.",
                jsonAdaptedInternship.get()::toModelType);
    }

    @Test
    public void loadToModel_missingDescription_failure() throws DataConversionException {
        Optional<JsonAdaptedInternship> jsonAdaptedInternship =
                readJsonFile(addToTestDataPathIfNotNull("missingDescriptionInternship.json"),
                        JsonAdaptedInternship.class);
        assertThrows(IllegalValueException.class,
                String.format(MISSING_FIELD_MESSAGE_FORMAT, "description"),
                jsonAdaptedInternship.get()::toModelType);
    }


}
