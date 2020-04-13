package seedu.address.logic.commands.sort;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.SortCommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.Resume;

/**
 * Sorts Resume items in the resume book.
 */
public class SortResumesCommand extends SortCommand {

    private final Comparator<Resume> sortComparator;
    private final String sortOrder;
    private final boolean reverse;

    public SortResumesCommand(String sortOrder, boolean reverse) {
        this.sortOrder = sortOrder;
        this.reverse = reverse;
        Comparator<Resume> baseComparator = Comparator.comparing(Resume::getName);
        sortComparator = reverse ? baseComparator.reversed() : baseComparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.sortResumes(sortComparator);
        model.setResumeToDisplay();
        model.commitResumeBook();

        return new SortCommandResult(
                String.format(MESSAGE_SUCCESS, Resume.class.getSimpleName()), model.getDisplayType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof SortResumesCommand
                && sortOrder.equals(((SortResumesCommand) other).sortOrder)
                && reverse == ((SortResumesCommand) other).reverse);
    }
}
