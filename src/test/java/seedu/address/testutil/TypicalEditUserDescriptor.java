package seedu.address.testutil;

import seedu.address.logic.commands.edit.EditUserDescriptor;
import seedu.address.model.item.field.Cap;
import seedu.address.model.item.field.Description;
import seedu.address.model.item.field.DisplayPicture;
import seedu.address.model.item.field.Email;
import seedu.address.model.item.field.Github;
import seedu.address.model.item.field.Major;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Phone;
import seedu.address.model.item.field.Time;
import seedu.address.model.item.field.University;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CAP_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CAP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DP_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FROM;
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

public class TypicalEditUserDescriptor {
    public final static EditUserDescriptor AMY_DESC = new EditUserDescriptorBuilder()
            .withDisplayPicture(VALID_DP_AMY)
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
    public final static EditUserDescriptor BOB_DESC = buildBob();

    static EditUserDescriptor buildBob() {
        EditUserDescriptor amy = new EditUserDescriptor();
        amy.setDisplayPicture(new DisplayPicture(VALID_DP_BOB));
        amy.setName(new Name(VALID_NAME_BOB));
        amy.setDescription(new Description(VALID_DESCRIPTION_BOB));
        amy.setPhone(new Phone(VALID_PHONE_BOB));
        amy.setEmail(new Email(VALID_EMAIL_BOB));
        amy.setGithub(new Github(VALID_GITHUB_BOB));
        amy.setUni(new University(VALID_UNIVERSITY_BOB));
        amy.setMajor(new Major(VALID_MAJOR_BOB));
        amy.setFrom(new Time(VALID_FROM_BOB));
        amy.setTo(new Time(VALID_TO_BOB));
        amy.setCap(new Cap(VALID_CAP_BOB));
        return amy;
    }
}
