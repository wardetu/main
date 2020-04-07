package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ResumeEditCommand;
import seedu.address.testutil.ItemIndicesBuilder;

public class ResumeEditCommandParserTest {
    private ResumeEditCommandParser parser = new ResumeEditCommandParser();

    @Test
    public void parse_allItemsSpecified_success() {
        ItemIndicesBuilder internshipIndicesBuilder = new ItemIndicesBuilder().add(1).add(3).add(4);
        ItemIndicesBuilder projectIndicesBuilder = new ItemIndicesBuilder().add(1).add(2);
        ItemIndicesBuilder skillIndicesBuilder=  new ItemIndicesBuilder().add(1);

        // Standard
        assertParseSuccess(parser,
                "1 " + PREFIX_INTERNSHIP + " " + internshipIndicesBuilder.toString() + " " + PREFIX_SKILL +
                        " " + skillIndicesBuilder.toString() + " " + PREFIX_PROJECT + " " +
                        projectIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), projectIndicesBuilder.build(),
                        skillIndicesBuilder.build()));

        // Shuffle the order of prefixes
        assertParseSuccess(parser,
                "1 " + PREFIX_PROJECT + " " + projectIndicesBuilder.toString() + " " + PREFIX_INTERNSHIP +
                        " " + internshipIndicesBuilder.toString() + " " + PREFIX_SKILL + " " +
                        skillIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), projectIndicesBuilder.build(),
                        skillIndicesBuilder.build()));

        // multiple project prefixes -- only last one accepted
        assertParseSuccess(parser,
                "1 " + PREFIX_PROJECT + " 100 200 " + PREFIX_INTERNSHIP + " " +
                        internshipIndicesBuilder.toString() + " " + PREFIX_SKILL + " " +
                        skillIndicesBuilder.toString() + " " + PREFIX_PROJECT + " " + projectIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), projectIndicesBuilder.build(),
                        skillIndicesBuilder.build()));

        // multiple internship prefixes -- only last one accepted
        assertParseSuccess(parser,
                "1 " + PREFIX_INTERNSHIP + " 100 200 " + PREFIX_INTERNSHIP + " " +
                        internshipIndicesBuilder.toString() + " " + PREFIX_SKILL + " " +
                        skillIndicesBuilder.toString() + " " + PREFIX_PROJECT + " " + projectIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), projectIndicesBuilder.build(),
                        skillIndicesBuilder.build()));

        // multiple skill prefixes -- only last one accepted
        assertParseSuccess(parser,
                "1 " + PREFIX_SKILL + " 100 200 " + PREFIX_INTERNSHIP + " " +
                        internshipIndicesBuilder.toString() + " " + PREFIX_SKILL + " " +
                        skillIndicesBuilder.toString() + " " + PREFIX_PROJECT + " " + projectIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), projectIndicesBuilder.build(),
                        skillIndicesBuilder.build()));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        // standard
        assertParseFailure(parser, "a", ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "-1", ParserUtil.MESSAGE_INVALID_INDEX);

        // with some item prefixes
        assertParseFailure(parser, "a int/ 1 2 3 proj/ 3 2 1 ski/ 2 1", ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "-1 proj/ 4 1 2", ParserUtil.MESSAGE_INVALID_INDEX);
    }
}
