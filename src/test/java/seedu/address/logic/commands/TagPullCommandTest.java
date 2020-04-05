package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TECH;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ITEM;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Resume;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ResumeBuilder;
import seedu.address.testutil.TypicalInternship;
import seedu.address.testutil.TypicalProject;
import seedu.address.testutil.TypicalResume;
import seedu.address.testutil.TypicalResumeBook;
import seedu.address.testutil.TypicalSkill;

// Test Suite: To Remove after testing:
/*
 * 1. Tagpull a tag that increases all 3 counts
 * 2. Tagpull a tag that increases 1 of the counts
 * 3. Tagpull that has no effect
 * 4. Tagpull invalid index
 * 5. Tagpull twice the same thing
 * 6. Tagpull pulls something that already exists - check for distinct
 * 7. Tagpull multiple tags
 */
public class TagPullCommandTest {
    private Model model;
    private String feedbackToUser = "Items pulled:\n%1$d internship(s), %2$d project(s), %3$d skill(s).";

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalResumeBook.TYPICAL_WITH_FILLED_RESUME, new UserPrefs());
    }


    @Test
    public void execute_invalidResumeIndex_throwsCommandException() {
        Index invalidIndex = INDEX_FOURTH_ITEM;
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(VALID_TAG_TECH));
        TagPullCommand tagPullCommand = new TagPullCommand(invalidIndex, tags);
        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_INDEX, () -> tagPullCommand.execute(model));
    }

    @Test
    public void execute_validTag_successAndAllCountsIncrease() throws CommandException {
        Index firstIndex = INDEX_FIRST_ITEM;
        Index thirdIndex = INDEX_THIRD_ITEM;
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(VALID_TAG_TECH));

        Resume expectedFirstResume = new ResumeBuilder(TypicalResume.ME_RESUME)
                .withInternship(TypicalInternship.GOOGLE)
                .withInternship(TypicalInternship.NINJA_VAN)
                .withProject(TypicalProject.ORBITAL)
                .withProject(TypicalProject.DUKE)
                .withSkill(TypicalSkill.REACT)
                .withSkill(TypicalSkill.GIT)
                .build();

        Resume expectedThirdResume = new ResumeBuilder(TypicalResume.FILLED_RESUME)
                .withInternship(TypicalInternship.GOOGLE)
                .withInternship(TypicalInternship.NINJA_VAN)
                .withProject(TypicalProject.ORBITAL)
                .withProject(TypicalProject.DUKE)
                .withSkill(TypicalSkill.GIT) // Order matters here
                .withSkill(TypicalSkill.REACT)
                .build();

        // Resume at first index
        CommandResult commandResultFirstIndex = new TagPullCommand(firstIndex, tags).execute(model);
        assertEquals(String.format(feedbackToUser, 2, 2, 2), commandResultFirstIndex.getFeedbackToUser());

        Resume firstIndexResume = model.getResumeByIndex(firstIndex);
        assertEquals(firstIndexResume, expectedFirstResume);

        // Resume at third index
        CommandResult commandResultThirdIndex = new TagPullCommand(thirdIndex, tags).execute(model);
        assertEquals(String.format(feedbackToUser, 1, 1, 1), commandResultThirdIndex.getFeedbackToUser());

        Resume thirdIndexResume = model.getResumeByIndex(thirdIndex);
        assertEquals(thirdIndexResume, expectedThirdResume);
    }



    /*
     * 1. Tagpull a tag that increases all 3 counts
     * 2. Tagpull a tag that increases 1 of the counts
     * 3. Tagpull that has no effect
     * 4. Tagpull invalid index
     * 5. Tagpull twice the same thing
     * 6. Tagpull pulls something that already exists - check for distinct
     * 7. Tagpull multiple tags
     */

}
