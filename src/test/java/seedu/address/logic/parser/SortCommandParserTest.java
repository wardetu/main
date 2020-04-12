package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sort.SortInternshipsCommand;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();


    @Test
    public void parse_sortInternshipByNameWithoutReverseArgument_success() {
        assertParseSuccess(
                parser, " i/ int order/ name" , new SortInternshipsCommand("name", false));
    }
}
