package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.testutil.NoteBuilder;
import seedu.address.testutil.TypicalNote;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonAdaptedNoteTest {
    @Test
    public void toModelType_validNote_returnsNote() throws IllegalValueException {
        JsonAdaptedNote jsonAdaptedNote = new JsonAdaptedNote(new NoteBuilder(TypicalNote.FINISH_CS_2103).build());
        assertEquals(new NoteBuilder(TypicalNote.FINISH_CS_2103).build(), jsonAdaptedNote.toModelType());

        jsonAdaptedNote = new JsonAdaptedNote("Finish CS2103", "1", "12-2020", "false", new ArrayList<>());
        assertEquals(new NoteBuilder(TypicalNote.FINISH_CS_2103).build(), jsonAdaptedNote.toModelType());
    }

    @Test
    public void toModelType_missingName_throwsIllegalValueException() throws IllegalValueException {
        JsonAdaptedNote jsonAdaptedNote = new JsonAdaptedNote(null, "1", "12-2020", "false", new ArrayList<>());
        assertEquals(new NoteBuilder(TypicalNote.FINISH_CS_2103).build(), jsonAdaptedNote.toModelType());
    }
}
