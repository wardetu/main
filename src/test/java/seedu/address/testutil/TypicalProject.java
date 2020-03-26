package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_ORBITAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEBSITE;

import seedu.address.model.item.Project;

public class TypicalProject {
    // Manually added - project's details found in {@code CommandTestUtil}
    public static final Project ORBITAL = new ProjectBuilder().withName(VALID_PROJECT_NAME_ORBITAL)
            .withTime(VALID_TIME).withWebsite(VALID_WEBSITE).withDescription(VALID_DESCRIPTION).build();
}
