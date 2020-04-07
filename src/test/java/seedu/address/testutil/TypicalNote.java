package seedu.address.testutil;

import seedu.address.model.item.Note;

public class TypicalNote {
    public static final Note NOTE_DONE = new NoteBuilder()
            .withName("Finish Homework")
            .withTime("03-2020")
            .done()
            .build();
    public static final Note NOTE_NOT_DONE = new NoteBuilder()
            .withName("Finish CS2103")
            .withTime("12-2020")
            .withTags()
            .build();
}
