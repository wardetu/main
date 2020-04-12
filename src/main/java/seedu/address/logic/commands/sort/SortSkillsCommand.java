package seedu.address.logic.commands.sort;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.SortCommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.Skill;

/**
 * Sorts Skill items in the resume book.
 */
public class SortSkillsCommand extends SortCommand {

    private final Comparator<Skill> sortComparator;
    private final String sortOrder;
    private final boolean reverse;

    public SortSkillsCommand(String sortOrder, boolean reverse) {
        this.sortOrder = sortOrder;
        this.reverse = reverse;
        Comparator<Skill> baseComparator =
                sortOrder.equalsIgnoreCase("name")
                        ? Comparator.comparing(Skill::getName)
                        : Comparator.comparing(Skill::getLevel);
        sortComparator = reverse ? baseComparator.reversed() : baseComparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.sortSkills(sortComparator);
        model.setSkillToDisplay();
        model.commitResumeBook();

        return new SortCommandResult(
                String.format(MESSAGE_SUCCESS, Skill.class.getSimpleName()), model.getDisplayType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof SortSkillsCommand
                && sortOrder.equals(((SortSkillsCommand) other).sortOrder)
                && reverse == ((SortSkillsCommand) other).reverse);
    }
}
