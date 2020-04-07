package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ResumeEditCommand;
import seedu.address.model.item.Item;
import seedu.address.testutil.ItemIndicesBuilder;

public class ResumeEditCommandParserTest {
    private ResumeEditCommandParser parser = new ResumeEditCommandParser();

    @Test
    public void parse_allItemsSpecified_success() {
        ItemIndicesBuilder internshipIndicesBuilder = new ItemIndicesBuilder().add(1).add(3).add(4);
        ItemIndicesBuilder skillIndicesBuilder =  new ItemIndicesBuilder().add(1);
        ItemIndicesBuilder projectIndicesBuilder = new ItemIndicesBuilder().add(1).add(2);

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
    public void parse_intAndProjSpecified_success() {
        ItemIndicesBuilder internshipIndicesBuilder = new ItemIndicesBuilder().add(1).add(3).add(4);
        ItemIndicesBuilder projectIndicesBuilder = new ItemIndicesBuilder().add(1).add(2);

        // Standard
        assertParseSuccess(parser,
                "1 " + PREFIX_INTERNSHIP + " " + internshipIndicesBuilder.toString() + " " +
                        PREFIX_PROJECT + " " + projectIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), projectIndicesBuilder.build(),
                        ItemIndicesBuilder.empty()));

        // Shuffle the order of prefixes
        assertParseSuccess(parser,
                "1 " + PREFIX_PROJECT + " " + projectIndicesBuilder.toString() + " " + PREFIX_INTERNSHIP +
                        " " + internshipIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), projectIndicesBuilder.build(),
                        ItemIndicesBuilder.empty()));

        // multiple internship prefixes -- only last one accepted
        assertParseSuccess(parser,
                "1 " + PREFIX_INTERNSHIP + " 100 200 " + PREFIX_INTERNSHIP + " " +
                        internshipIndicesBuilder.toString() + " " + PREFIX_PROJECT + " " +
                        projectIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), projectIndicesBuilder.build(),
                        ItemIndicesBuilder.empty()));

        // multiple project prefixes -- only last one accepted
        assertParseSuccess(parser,
                "1 " + PREFIX_PROJECT + " 100 200 " + PREFIX_INTERNSHIP + " " +
                        internshipIndicesBuilder.toString() + " " + PREFIX_PROJECT + " " +
                        projectIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), projectIndicesBuilder.build(),
                        ItemIndicesBuilder.empty()));
    }

    @Test
    public void parse_intAndSkiSpecified_success() {
        ItemIndicesBuilder internshipIndicesBuilder = new ItemIndicesBuilder().add(1).add(3).add(4);
        ItemIndicesBuilder skillIndicesBuilder = new ItemIndicesBuilder().add(1).add(2);

        // Standard
        assertParseSuccess(parser,
                "1 " + PREFIX_INTERNSHIP + " " + internshipIndicesBuilder.toString() + " " +
                        PREFIX_SKILL + " " + skillIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), ItemIndicesBuilder.empty(),
                        skillIndicesBuilder.build()));

        // Shuffle the order of prefixes
        assertParseSuccess(parser,
                "1 " + PREFIX_SKILL + " " + skillIndicesBuilder.toString() + " " + PREFIX_INTERNSHIP +
                        " " + internshipIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), ItemIndicesBuilder.empty(),
                        skillIndicesBuilder.build()));

        // multiple internship prefixes -- only last one accepted
        assertParseSuccess(parser,
                "1 " + PREFIX_INTERNSHIP + " 100 200 " + PREFIX_INTERNSHIP + " " +
                        internshipIndicesBuilder.toString() + " " + PREFIX_SKILL + " " +
                        skillIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), ItemIndicesBuilder.empty(),
                        skillIndicesBuilder.build()));

        // multiple skill prefixes -- only last one accepted
        assertParseSuccess(parser,
                "1 " + PREFIX_SKILL + " 100 200 " + PREFIX_INTERNSHIP + " " +
                        internshipIndicesBuilder.toString() + " " + PREFIX_SKILL + " " +
                        skillIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), ItemIndicesBuilder.empty(),
                        skillIndicesBuilder.build()));
    }

    @Test
    public void parse_onlyProjSpecified_success() {
        ItemIndicesBuilder projectIndicesBuilder = new ItemIndicesBuilder().add(1).add(2);

        // Standard
        assertParseSuccess(parser,
                "1 " + PREFIX_PROJECT + " " + projectIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, ItemIndicesBuilder.empty() ,projectIndicesBuilder.build(),
                        ItemIndicesBuilder.empty()));

        // multiple project prefixes -- only last one accepted
        assertParseSuccess(parser,
                "1 " + PREFIX_PROJECT + " 100 200 " + PREFIX_PROJECT + " " + projectIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, ItemIndicesBuilder.empty(), projectIndicesBuilder.build(),
                        ItemIndicesBuilder.empty()));
    }

    @Test
    public void parse_onlySkiSpecified_success() {
        ItemIndicesBuilder skillIndicesBuilder = new ItemIndicesBuilder().add(1).add(2);

        // Standard
        assertParseSuccess(parser,
                "1 " + PREFIX_SKILL + " " + skillIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, ItemIndicesBuilder.empty(), ItemIndicesBuilder.empty(),
                        skillIndicesBuilder.build()));

        // multiple skill prefixes -- only last one accepted
        assertParseSuccess(parser,
                "1 " + PREFIX_SKILL + " 100 200 " + PREFIX_SKILL + " " + skillIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, ItemIndicesBuilder.empty(), ItemIndicesBuilder.empty(),
                        skillIndicesBuilder.build()));
    }

    @Test
    public void parse_nothingSpecified_success() {
        assertParseSuccess(parser,
                "1",
                new ResumeEditCommand(INDEX_FIRST_ITEM, ItemIndicesBuilder.empty(), ItemIndicesBuilder.empty(),
                        ItemIndicesBuilder.empty()));

        assertParseSuccess(parser,
                "4",
                new ResumeEditCommand(INDEX_FOURTH_ITEM, ItemIndicesBuilder.empty(), ItemIndicesBuilder.empty(),
                        ItemIndicesBuilder.empty()));
    }

    @Test
    public void parse_invalidResumeIndex_throwsParseException() {
        // standard
        assertParseFailure(parser, "a", ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "-1", ParserUtil.MESSAGE_INVALID_INDEX);

        // with some item prefixes
        assertParseFailure(parser, "a int/ 1 2 3 proj/ 3 2 1 ski/ 2 1", ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "-1 proj/ 4 1 2", ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "1 1 proj/ 4 1 2", ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidReditItemIndices_throwsParseException() {
        // standard
        assertParseFailure(parser, "1 int/ a", ParserUtil.MESSAGE_INVALID_REDIT_ITEM_INDEX);
        assertParseFailure(parser, "1 int/ -1", ParserUtil.MESSAGE_INVALID_REDIT_ITEM_INDEX);
        assertParseFailure(parser, "2 proj/ a", ParserUtil.MESSAGE_INVALID_REDIT_ITEM_INDEX);
        assertParseFailure(parser, "2 proj/ -1", ParserUtil.MESSAGE_INVALID_REDIT_ITEM_INDEX);
        assertParseFailure(parser, "3 ski/ 0", ParserUtil.MESSAGE_INVALID_REDIT_ITEM_INDEX);
        assertParseFailure(parser, "3 ski/ 100@", ParserUtil.MESSAGE_INVALID_REDIT_ITEM_INDEX);

        // mix valid and invalid
        assertParseFailure(parser, "2 proj/ -1 ski/ 1 2 3", ParserUtil.MESSAGE_INVALID_REDIT_ITEM_INDEX);
        assertParseFailure(parser, "2 int/ 3 1 2 ski/ @", ParserUtil.MESSAGE_INVALID_REDIT_ITEM_INDEX);
        assertParseFailure(parser, "2 int/ 3 2 proj/ a", ParserUtil.MESSAGE_INVALID_REDIT_ITEM_INDEX);

        // all invalid
        assertParseFailure(parser, "2 int/ -1 proj/ a", ParserUtil.MESSAGE_INVALID_REDIT_ITEM_INDEX);
        assertParseFailure(parser, "3 int/ -1 proj/ a ski/ 0", ParserUtil.MESSAGE_INVALID_REDIT_ITEM_INDEX);
    }
}
