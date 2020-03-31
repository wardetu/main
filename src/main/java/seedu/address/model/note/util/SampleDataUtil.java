//package seedu.address.model.note.util;
//
//import seedu.address.model.item.field.Time;
//import seedu.address.model.note.NoteBook;
//import seedu.address.model.note.NoteEntry;
//import seedu.address.model.note.field.Description;
//import seedu.address.model.note.field.Place;
//import seedu.address.model.note.field.Title;
//
//public class SampleDataUtil {
//
//    public static NoteEntry[] getSampleNoteEntry() {
//        return new NoteEntry[]{
//                new NoteEntry(new Title("Interview with Shopee"), new Time("10-2020"),
//                        new Place("School"), new Description("Revise: Data Structures and Algorithms")),
//                new NoteEntry(new Title("Edit Resume 2"), new Time("01-2020"),
//                        new Place("Home"), new Description("Change internship order"))
//        };
//    }
//
//    public static NoteBook getSampleNoteBook() {
//        NoteBook sampleNoteBook = new NoteBook();
//        sampleNoteBook.loadData(getSampleNoteEntry());
//        return sampleNoteBook;
//    }
//}
