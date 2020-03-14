package seedu.address.logic.commands.stubs;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.item.Resume;

public class AddResumeCommandStub extends AddCommand {
    Resume resume;

    public AddResumeCommandStub(Resume resume) {
        this.resume = resume;
    }
}
