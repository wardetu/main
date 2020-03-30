package seedu.address.model.note.util;

import seedu.address.logic.parser.exceptions.DateParseException;
import seedu.address.model.note.NoteEntry;
import seedu.address.model.note.field.DateFormat;
import seedu.address.model.note.field.Description;
import seedu.address.model.note.field.Place;
import seedu.address.model.note.field.Title;

public class SampleDataUtil {

    public static NoteEntry[] getSampleNoteEntry() {
        try {
            return new NoteEntry[]{
                    new NoteEntry(new Title("Interview with Shopee"), DateFormat.convertToDate("30/03/2020 1200"),
                            new Place("School"), new Description("Revise: Data Structures and Algorithms")),
                    new NoteEntry(new Title("Edit Resume 2"), DateFormat.convertToDate("30/03/2020 2300"),
                            new Place("Home"), new Description("Change internship order"))
            };
        } catch (DateParseException e) {
            return null;
        }
    }
}
