package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.results.ResumePreviewCommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalResume;
import seedu.address.testutil.TypicalResumeBook;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalResumeBook.TYPICAL_WITHOUT_GOOGLE_COPY;

public class ResumePreviewCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalResumeBook.TYPICAL_WITHOUT_GOOGLE, new UserPrefs());
    }

    @Test
    public void execute_blankResume_success() {
        ResumePreviewCommand preview = new ResumePreviewCommand(Index.fromOneBased(1));
        Model expectedModel = new ModelManager(TYPICAL_WITHOUT_GOOGLE_COPY, new UserPrefs());
    }

}
