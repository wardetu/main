package seedu.address.logic.parser;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ResumeEditCommand;
import seedu.address.logic.commands.TagPullCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new {@code ResumeEditCommand} object
 */
public class TagPullCommandParser implements Parser<TagPullCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ResumeEditCommand
     * and returns an ResumeEditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public TagPullCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);
        Index index;
        Set<Tag> tagList;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResumeEditCommand.MESSAGE_USAGE),
                    pe);
        }
        tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        return new TagPullCommand(index, tagList);
    }
}
