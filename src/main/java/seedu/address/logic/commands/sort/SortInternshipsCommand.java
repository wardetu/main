package seedu.address.logic.commands.sort;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.SortCommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.Internship;

/**
 * Sort Internship items in the resume book.
 */
public class SortInternshipsCommand extends SortCommand {

    private final Comparator<Internship> sortComparator;

    public SortInternshipsCommand(String sortOrder, boolean reverse) {
        Comparator<Internship> baseComparator =
                sortOrder.equalsIgnoreCase("name")
                        ? Comparator.comparing(Internship::getName)
                        : Comparator.comparing(Internship::getFrom);
        sortComparator = reverse ? baseComparator.reversed() : baseComparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.sortInternships(sortComparator);
        model.setInternshipToDisplay();
        model.commitResumeBook();

        return new SortCommandResult(
                String.format(MESSAGE_SUCCESS, Internship.class.getSimpleName()), model.getDisplayType());
    }
}
