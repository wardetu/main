package seedu.address.logic.parser;

import seedu.address.logic.commands.sort.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and create a SortCommand object.
 */
public class SortCommandParser implements Parser<SortCommand>{

    /**
     * Parses the given {@code String} of arguments and returns a SortCommand object for execution.
     */
    @Override
    public SortCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize()
    }
}
