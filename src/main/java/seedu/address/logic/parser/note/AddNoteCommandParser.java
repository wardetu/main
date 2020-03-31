//package seedu.address.logic.parser.note;
//
//import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_PLACE;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
//
//import java.util.NoSuchElementException;
//import java.util.stream.Stream;
//
//import seedu.address.logic.commands.Command;
//import seedu.address.logic.commands.note.AddNoteCommand;
//import seedu.address.logic.parser.ArgumentMultimap;
//import seedu.address.logic.parser.ArgumentTokenizer;
//import seedu.address.logic.parser.ParserUtil;
//import seedu.address.logic.parser.Prefix;
//import seedu.address.logic.parser.exceptions.ParseException;
//import seedu.address.model.item.field.Time;
//import seedu.address.model.note.NoteEntry;
//import seedu.address.model.note.field.Description;
//import seedu.address.model.note.field.Place;
//import seedu.address.model.note.field.Title;
//
//public class AddNoteCommandParser {
//
//    public Command parse(String args) throws ParseException {
//        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_TIME,
//                PREFIX_PLACE, PREFIX_DESCRIPTION);
//
//        if (!arePrefixesPresent(argumentMultimap, PREFIX_TITLE, PREFIX_DATE)
//            || !argumentMultimap.getPreamble().isEmpty()) {
//            throw new ParseException("Wrong format");
//        }
//
//        Title title;
//        Time time;
//        Place place;
//        Description description;
//
//        try {
//            title = ParserUtil.parseTitle(argumentMultimap.getValue(PREFIX_TITLE).get());
//            time = ParserUtil.parseTime(argumentMultimap.getValue(PREFIX_TIME).get());
//            if (argumentMultimap.getValue(PREFIX_PLACE).isEmpty()) {
//                place = new Place("Anywhere");
//            } else {
//                place = ParserUtil.parsePlace(argumentMultimap.getValue(PREFIX_PLACE).get());
//            }
//
//            if (argumentMultimap.getValue(PREFIX_DESCRIPTION).isEmpty()) {
//                description = new Description("No description");
//            } else {
//                description = ParserUtil.parseDescription(argumentMultimap.getValue(PREFIX_DESCRIPTION).get());
//            }
//        } catch (NullPointerException | NoSuchElementException ex) {
//            throw new ParseException("Parse error. To be more specified later");
//        }
//
//        NoteEntry noteEntry = new NoteEntry(title, time, place, description);
//        return new AddNoteCommand(noteEntry);
//    }
//
//    /**
//     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
//     * {@code ArgumentMultimap}.
//     */
//    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
//        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
//    }
//}
