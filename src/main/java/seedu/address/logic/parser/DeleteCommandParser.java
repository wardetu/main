package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.delete.DeleteCommand;
import seedu.address.logic.commands.delete.DeleteInternshipCommand;
import seedu.address.logic.commands.delete.DeleteProjectCommand;
import seedu.address.logic.commands.delete.DeleteResumeCommand;
import seedu.address.logic.commands.delete.DeleteSkillCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Item;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        try {
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ITEM);

            if (!argMultimap.getValue(PREFIX_ITEM).isPresent()) {
                throw new ParseException(Item.MESSAGE_CONSTRAINTS);
            }

            String preamble = argMultimap.getPreamble();
            Index index = ParserUtil.parseIndex(preamble);

            String itemType = ParserUtil.parseItemType(argMultimap.getValue(PREFIX_ITEM).get());

            switch (itemType) {
            case "res":
                return new DeleteResumeCommand(index);
            case "int":
                return new DeleteInternshipCommand(index);
            case "proj":
                return new DeleteProjectCommand(index);
            case "ski":
                return new DeleteSkillCommand(index);

            default:
                // Should not have reached here
                // TODO: Use a better Exception here
                throw new ParseException("The item type is not detected! Something is wrong");
            }
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
