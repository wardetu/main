package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.EditUserCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.add.AddCommand;
import seedu.address.logic.commands.delete.DeleteCommand;
import seedu.address.logic.commands.edit.EditCommand;
import seedu.address.logic.commands.find.FindCommand;
import seedu.address.logic.commands.list.ListCommand;
import seedu.address.logic.commands.takenote.TakeNoteCommand;
import seedu.address.logic.commands.view.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.note.AddNoteCommandParser;
import seedu.address.logic.parser.note.DeleteNoteCommandParser;
import seedu.address.logic.parser.note.FindNoteCommandParser;

/**
 * Parses user input.
 */
public class ResumeBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        //--------------Item manipulation commands-------------
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        //-----------------Undo/Redo----------------------------

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        //-----------------User commands-----------------------
        case EditUserCommand.COMMAND_WORD: //"me"
            return new EditUserParser().parse(arguments);

        case TakeNoteCommand.COMMAND_WORD: //"note"
            requireEmptyArguments(arguments);
            return new TakeNoteCommand();

        case AddNoteCommand.COMMAND_WORD: //"addnote"
            return new AddNoteCommandParser().parse(arguments);

        case DeleteNoteCommand.COMMAND_WORD: //"delnote"
            return new DeleteNoteCommandParser().parse(arguments);

        case FindNoteCommand.COMMAND_WORD: //"findnote"
            return new FindNoteCommandParser().parse(arguments);

        //-----------------Other commands-----------------------

            case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Ensures commands not meant to have trailing arguments do not have any. Trailing spaces following commands are
     * fine and would have been trimmed off automatically by the program.
     *
     * @param arguments Argument inputs keyed in by the user following the command.
     * @throws ParseException If there are additional arguments after the command.
     */
    private void requireEmptyArguments(String arguments) throws ParseException {
        if (!arguments.isEmpty()) {
            throw new ParseException("MESSAGE_ARGUMENTS_MUST_BE_EMPTY");
        }
    }
}
