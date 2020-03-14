package seedu.address.logic.commands.stubs;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Resume;

public class AddInternshipCommandStub extends AddCommand {
    Internship internship;

    public AddInternshipCommandStub(Internship internship) {
        this.internship = internship;
    }
}
