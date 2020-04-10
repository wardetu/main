package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.testutil.NoteBuilder;
import seedu.address.testutil.TypicalNote;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.storage.JsonAdaptedNote.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

public class JsonAdaptedNoteTest {
    @Test
    public void toModelType_validNote_returnsNote() throws IllegalValueException {
        // Without tags
        JsonAdaptedNote jsonAdaptedNote = new JsonAdaptedNote(new NoteBuilder(TypicalNote.FINISH_CS_2103).build());
        assertEquals(new NoteBuilder(TypicalNote.FINISH_CS_2103).build(), jsonAdaptedNote.toModelType());

        jsonAdaptedNote =
                new JsonAdaptedNote("Finish CS2103", "1", "12-2020", "false", new ArrayList<>());
        assertEquals(new NoteBuilder(TypicalNote.FINISH_CS_2103).build(), jsonAdaptedNote.toModelType());

        // With tags
        jsonAdaptedNote = new JsonAdaptedNote(
                new NoteBuilder(TypicalNote.FINISH_CS_2103).withTags("tech", "java").build());
        assertEquals(
                new NoteBuilder(TypicalNote.FINISH_CS_2103).withTags("tech", "java").build(),
                jsonAdaptedNote.toModelType());

        List tagList = new ArrayList<>();
        tagList.add(new JsonAdaptedTag("java"));
        tagList.add(new JsonAdaptedTag("tech"));
        jsonAdaptedNote = new JsonAdaptedNote(
                "Finish CS2103", "1", "12-2020", "false", tagList);
        assertEquals(
                new NoteBuilder(TypicalNote.FINISH_CS_2103).withTags("java", "tech").build(),
                jsonAdaptedNote.toModelType());
    }

    @Test
    public void toModelType_missingName_throwsIllegalValueException() {
        JsonAdaptedNote jsonAdaptedNote =
                new JsonAdaptedNote(null, "1", "12-2020", "false", new ArrayList<>());
        assertThrows(IllegalValueException.class,
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()),
                jsonAdaptedNote::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedNote jsonAdaptedNote =
                new JsonAdaptedNote("&not a $name", "1", "12-2020", "false", new ArrayList<>());
        assertThrows(IllegalValueException.class,
                Name.MESSAGE_CONSTRAINTS,
                jsonAdaptedNote::toModelType);
    }

    @Test
    public void toModelType_missingTime_throwsIllegalValueException() {
        JsonAdaptedNote jsonAdaptedNote =
                new JsonAdaptedNote("Finish CS2103", "1", null, "false", new ArrayList<>());
        assertThrows(IllegalValueException.class,
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName()),
                jsonAdaptedNote::toModelType);
    }

    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        JsonAdaptedNote jsonAdaptedNote =
                new JsonAdaptedNote("Finish CS2103", "1", "13-2020", "false", new ArrayList<>());
        assertThrows(IllegalValueException.class,
                Time.MESSAGE_CONSTRAINTS,
                jsonAdaptedNote::toModelType);
    }

    @Test
    public void toModelType_missingDone_throwsIllegalValueException() {
        JsonAdaptedNote jsonAdaptedNote =
                new JsonAdaptedNote("Finish CS2103", "1", "11-2020", null, new ArrayList<>());
        assertThrows(IllegalValueException.class,
                String.format(MISSING_FIELD_MESSAGE_FORMAT, "Done"),
                jsonAdaptedNote::toModelType);
    }

    @Test
    public void toModelType_invalidDone_throwsIllegalValueException() {
        JsonAdaptedNote jsonAdaptedNote =
                new JsonAdaptedNote("Finish CS2103", "1", "11-2020", "tru", new ArrayList<>());
        assertThrows(IllegalValueException.class,
                "A boolean field can only be true or false.",
                jsonAdaptedNote::toModelType);
    }

    @Test
    public void toModelType_missingId_throwsIllegalValueException() {
        JsonAdaptedNote jsonAdaptedNote =
                new JsonAdaptedNote("Finish CS2103", null, "11-2020", "false", new ArrayList<>());
        assertThrows(IllegalValueException.class,
                "The id field can only be an integer.",
                jsonAdaptedNote::toModelType);
    }

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        // Id must be an integer
        JsonAdaptedNote jsonAdaptedNote =
                new JsonAdaptedNote("Finish CS2103", "a", "11-2020", "false", new ArrayList<>());
        assertThrows(IllegalValueException.class,
                "The id field can only be an integer.",
                jsonAdaptedNote::toModelType);

        // Id cannot be negative
        jsonAdaptedNote =
                new JsonAdaptedNote("Finish CS2103", "-1", "11-2020", "false", new ArrayList<>());
        assertThrows(IllegalValueException.class,
                "The id field must not be negative.",
                jsonAdaptedNote::toModelType);
    }

    @Test
    public void equals() {
        JsonAdaptedNote jsonAdaptedCS2103 = new JsonAdaptedNote(new NoteBuilder(TypicalNote.FINISH_CS_2103).build());
        JsonAdaptedNote jsonAdaptedHW = new JsonAdaptedNote(new NoteBuilder(TypicalNote.FINISH_HOMEWORK).build());

        // Two constructors gives the same result
        assertEquals(jsonAdaptedCS2103,
                new JsonAdaptedNote("Finish CS2103", "1", "12-2020", "false", new ArrayList<>()));

        // Different json adapted notes are not equal
        assertNotEquals(jsonAdaptedCS2103, jsonAdaptedHW);
    }

}
