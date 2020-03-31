//package seedu.address.logic.parser.note;
//
//import seedu.address.commons.core.index.Index;
//import seedu.address.logic.commands.Command;
//import seedu.address.logic.commands.note.DeleteNoteCommand;
//import seedu.address.logic.parser.ParserUtil;
//import seedu.address.logic.parser.exceptions.ParseException;
//
//public class DeleteNoteCommandParser {
//    public Command parse(String args) {
//        try {
//            Index index = ParserUtil.parseIndex(args);
//            return new DeleteNoteCommand(index);
//        } catch (ParseException e) {
//            System.out.println(e.getMessage()); //Add exception such as EmptyIndexException
//        }
//        return null;
//    }
//}
//
