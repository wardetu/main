package seedu.address.logic.commands.stubs;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.item.Internship;

/**
 * A stub class for AddInternshipCommand.
 */
public class AddInternshipCommandStub extends AddCommand {
    private Internship internship;

    public AddInternshipCommandStub(Internship internship) {
        this.internship = internship;
    }

    public Internship getInternship() {
        return internship;
    }
}
