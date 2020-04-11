package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_CAP_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_FROM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_GITHUB_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_MAJOR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_TO_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_UNIVERSITY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CAP_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FROM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TO_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIVERSITY_AMY;
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

        // multiple description - last one accepted
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

        // multiple github - last one accepted
        assertParseSuccess(parser,
                PREFIXED_NAME_AMY + PREFIXED_DESCRIPTION_AMY + PREFIXED_PHONE_AMY + PREFIXED_GITHUB_BOB
                        + PREFIXED_EMAIL_AMY + PREFIXED_GITHUB_AMY + PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_AMY
                        + PREFIXED_FROM_AMY + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));

    }
}
