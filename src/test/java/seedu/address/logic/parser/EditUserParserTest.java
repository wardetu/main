package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_CAP_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_CAP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_FROM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_FROM_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_GITHUB_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_MAJOR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_MAJOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_TO_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_TO_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_UNIVERSITY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_UNIVERSITY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CAP_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CAP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FROM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FROM_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TO_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TO_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIVERSITY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIVERSITY_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.edit.EditUserCommand;
import seedu.address.logic.commands.edit.EditUserDescriptor;
import seedu.address.testutil.EditUserDescriptorBuilder;

// NOTE: This test ignores display picture because the check in parser checks for existence of the file in the path
public class EditUserParserTest {
    private EditUserParser parser = new EditUserParser();

    @Test
    public void parse_changeAllFields_success() {
        EditUserDescriptor expectedEditUserDescriptor = new EditUserDescriptorBuilder()
                .withName(VALID_NAME_AMY)
                .withDescription(VALID_DESCRIPTION_AMY)
                .withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .withGithub(VALID_GITHUB_AMY)
                .withUniversity(VALID_UNIVERSITY_AMY)
                .withMajor(VALID_MAJOR_AMY)
                .withFrom(VALID_FROM_AMY)
                .withTo(VALID_TO_AMY)
                .withCap(VALID_CAP_AMY)
                .build();

        // Standard
        assertParseSuccess(parser,
                PREFIXED_NAME_AMY + PREFIXED_DESCRIPTION_AMY + PREFIXED_PHONE_AMY
                        + PREFIXED_EMAIL_AMY + PREFIXED_GITHUB_AMY + PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_AMY
                        + PREFIXED_FROM_AMY + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));

        // multiple names - last one accepted
        assertParseSuccess(parser,
                PREFIXED_NAME_BOB + PREFIXED_NAME_AMY + PREFIXED_DESCRIPTION_AMY + PREFIXED_PHONE_AMY
                        + PREFIXED_EMAIL_AMY + PREFIXED_GITHUB_AMY + PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_AMY
                        + PREFIXED_FROM_AMY + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));

        // multiple descriptions - last one accepted
        assertParseSuccess(parser,
                PREFIXED_NAME_AMY + PREFIXED_DESCRIPTION_BOB + PREFIXED_DESCRIPTION_AMY + PREFIXED_PHONE_AMY
                        + PREFIXED_EMAIL_AMY + PREFIXED_GITHUB_AMY + PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_AMY
                        + PREFIXED_FROM_AMY + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));

        // multiple phones - last one accepted
        assertParseSuccess(parser,
                PREFIXED_NAME_AMY + PREFIXED_DESCRIPTION_AMY + PREFIXED_PHONE_BOB + PREFIXED_PHONE_AMY
                        + PREFIXED_EMAIL_AMY + PREFIXED_GITHUB_AMY + PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_AMY
                        + PREFIXED_FROM_AMY + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));

        // multiple emails - last one accepted
        assertParseSuccess(parser,
                PREFIXED_NAME_AMY + PREFIXED_DESCRIPTION_AMY + PREFIXED_PHONE_AMY + PREFIXED_EMAIL_BOB
                        + PREFIXED_EMAIL_AMY + PREFIXED_GITHUB_AMY + PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_AMY
                        + PREFIXED_FROM_AMY + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));

        // multiple githubs - last one accepted
        assertParseSuccess(parser,
                PREFIXED_NAME_AMY + PREFIXED_DESCRIPTION_AMY + PREFIXED_PHONE_AMY + PREFIXED_GITHUB_BOB
                        + PREFIXED_EMAIL_AMY + PREFIXED_GITHUB_AMY + PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_AMY
                        + PREFIXED_FROM_AMY + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));
    }

    @Test
    public void parse_changeHalfFields_success() {
        EditUserDescriptor expectedEditUserDescriptor = new EditUserDescriptorBuilder()
                .withUniversity(VALID_UNIVERSITY_AMY)
                .withMajor(VALID_MAJOR_AMY)
                .withFrom(VALID_FROM_AMY)
                .withTo(VALID_TO_AMY)
                .withCap(VALID_CAP_AMY)
                .build();

        // Standard
        assertParseSuccess(parser,
                        PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_AMY + PREFIXED_FROM_AMY
                                + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));

        // multiple universities - last one accepted
        assertParseSuccess(parser,
                PREFIXED_UNIVERSITY_BOB + PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_AMY + PREFIXED_FROM_AMY
                        + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));

        // multiple majors - last one accepted
        assertParseSuccess(parser,
                PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_BOB + PREFIXED_MAJOR_AMY + PREFIXED_FROM_AMY
                        + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));

        // multiple froms - last one accepted
        assertParseSuccess(parser,
                PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_AMY + PREFIXED_FROM_BOB + PREFIXED_FROM_AMY
                        + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));

        // multiple tos - last one accepted
        assertParseSuccess(parser,
                PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_AMY + PREFIXED_FROM_AMY + PREFIXED_TO_BOB
                        + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));

        // multiple caps - last one accepted
        assertParseSuccess(parser,
                        PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_AMY + PREFIXED_FROM_AMY + PREFIXED_CAP_BOB
                                + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));
    }

    @Test
    public void parse_changeSingleField_success() {
        assertParseSuccess(parser,
                PREFIXED_NAME_BOB,
                new EditUserCommand(new EditUserDescriptorBuilder().withName(VALID_NAME_BOB).build()));

        assertParseSuccess(parser,
                PREFIXED_DESCRIPTION_BOB,
                new EditUserCommand(new EditUserDescriptorBuilder().withDescription(VALID_DESCRIPTION_BOB).build()));

        assertParseSuccess(parser,
                PREFIXED_PHONE_BOB,
                new EditUserCommand(new EditUserDescriptorBuilder().withPhone(VALID_PHONE_BOB).build()));

        assertParseSuccess(parser,
                PREFIXED_EMAIL_BOB,
                new EditUserCommand(new EditUserDescriptorBuilder().withEmail(VALID_EMAIL_BOB).build()));

        assertParseSuccess(parser,
                PREFIXED_GITHUB_BOB,
                new EditUserCommand(new EditUserDescriptorBuilder().withGithub(VALID_GITHUB_BOB).build()));

        assertParseSuccess(parser,
                PREFIXED_UNIVERSITY_BOB,
                new EditUserCommand(new EditUserDescriptorBuilder().withUniversity(VALID_UNIVERSITY_BOB).build()));

        assertParseSuccess(parser,
                PREFIXED_MAJOR_BOB,
                new EditUserCommand(new EditUserDescriptorBuilder().withMajor(VALID_MAJOR_BOB).build()));

        assertParseSuccess(parser,
                PREFIXED_FROM_BOB,
                new EditUserCommand(new EditUserDescriptorBuilder().withFrom(VALID_FROM_BOB).build()));

        assertParseSuccess(parser,
                PREFIXED_TO_BOB,
                new EditUserCommand(new EditUserDescriptorBuilder().withTo(VALID_TO_BOB).build()));

        assertParseSuccess(parser,
                PREFIXED_CAP_BOB,
                new EditUserCommand(new EditUserDescriptorBuilder().withCap(VALID_CAP_BOB).build()));
    }

    @Test
    public void parse_preamblePresent_failure() {
        assertParseFailure(parser,
                "a preamble" + PREFIXED_NAME_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditUserCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noArgs_failure() {
        assertParseFailure(parser,
                "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditUserCommand.MESSAGE_NO_ARGS));
    }
}
