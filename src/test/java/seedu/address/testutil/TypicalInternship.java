package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_FROM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERNSHIP_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERNSHIP_NAME_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERNSHIP_ROLE_FRONTEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TO;

import seedu.address.model.item.Internship;

/**
 * A utility class containing a list of {@code Internship} objects to be used in tests.
 */
public class TypicalInternship {
    // Manually added - internship's details found in {@code CommandTestUtil}
    public static final Internship GOOGLE = new InternshipBuilder().withName(VALID_INTERNSHIP_NAME_GOOGLE)
            .withRole(VALID_INTERNSHIP_ROLE_FRONTEND).withFrom(VALID_FROM).withTo(VALID_TO)
            .withDescription(VALID_INTERNSHIP_DESCRIPTION).build();
}
