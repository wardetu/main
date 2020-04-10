package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DUKE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ORBITAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_DUKE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_ORBITAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_JAVA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TECH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEBSITE_DUKE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEBSITE_ORBITAL;
import static seedu.address.storage.JsonAdaptedProject.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.field.Name;
import seedu.address.testutil.ProjectBuilder;
import seedu.address.testutil.TypicalProject;

public class JsonAdaptedProjectTest {
    public static final String INVALID_NAME = "&badname";
    @Test
    public void toModelType_validProject_returnsProject() throws IllegalValueException {
        // Without tags
        JsonAdaptedProject jsonAdaptedProject = new JsonAdaptedProject(new ProjectBuilder(TypicalProject.DUKE).build());
        assertEquals(new ProjectBuilder(TypicalProject.DUKE).build(), jsonAdaptedProject.toModelType());

        jsonAdaptedProject =
                new JsonAdaptedProject(VALID_PROJECT_NAME_DUKE, "1", VALID_TIME_2, VALID_WEBSITE_DUKE,
                        VALID_DESCRIPTION_DUKE, new ArrayList<>());
        assertEquals(new ProjectBuilder(TypicalProject.DUKE).withTags().build(), jsonAdaptedProject.toModelType());

        // With tags
        jsonAdaptedProject = new JsonAdaptedProject(
                new ProjectBuilder(TypicalProject.DUKE).withTags(VALID_TAG_TECH, VALID_TAG_JAVA).build());
        assertEquals(
                new ProjectBuilder(TypicalProject.DUKE).build(),
                jsonAdaptedProject.toModelType());

        List<JsonAdaptedTag> tagList = new ArrayList<>();
        tagList.add(new JsonAdaptedTag(VALID_TAG_JAVA));
        tagList.add(new JsonAdaptedTag(VALID_TAG_TECH));
        jsonAdaptedProject = new JsonAdaptedProject(VALID_PROJECT_NAME_DUKE, "1", VALID_TIME_2,
                VALID_WEBSITE_DUKE, VALID_DESCRIPTION_DUKE, tagList);
        assertEquals(
                new ProjectBuilder(TypicalProject.DUKE).build(),
                jsonAdaptedProject.toModelType());
    }

    @Test
    public void toModelType_missingName_throwsIllegalValueException() {
        JsonAdaptedProject jsonAdaptedProject =
                new JsonAdaptedProject(null, "1", VALID_TIME_2, VALID_WEBSITE_DUKE, VALID_DESCRIPTION_DUKE,
                        new ArrayList<>());
        assertThrows(IllegalValueException.class,
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()),
                jsonAdaptedProject::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedProject jsonAdaptedProject =
                new JsonAdaptedProject(INVALID_NAME, "1", VALID_TIME_2, VALID_WEBSITE_DUKE, VALID_DESCRIPTION_DUKE,
                        new ArrayList<>());
        assertThrows(IllegalValueException.class,
                Name.MESSAGE_CONSTRAINTS,
                jsonAdaptedProject::toModelType);
    }
}
