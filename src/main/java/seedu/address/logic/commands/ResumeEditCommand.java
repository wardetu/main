package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Resume;

/**
 * Edits the content of a Resume.
 */
public class ResumeEditCommand extends Command {
    public static final String COMMAND_WORD = "redit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits what an existing resume contains in the ResuMe "
            + "application with the specified index.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX "
            + "TYPE/ [ITEM_INDEX] "
            + "[MORE_TYPE/ [ITEM_ID...]]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_INTERNSHIP + " 1 3 4 "
            + PREFIX_SKILL + " 1 2 "
            + PREFIX_PROJECT + " 1 ";

    protected final Index index;
    protected final Optional<List<Integer>> internshipIndices;
    protected final Optional<List<Integer>> projectIndices;
    protected final Optional<List<Integer>> skillsIndices;

    public ResumeEditCommand(Index index, Optional<List<Integer>> internshipIndices,
                             Optional<List<Integer>> projectIndices, Optional<List<Integer>> skillsIndices) {
        this.index = index;
        this.internshipIndices = internshipIndices;
        this.projectIndices = projectIndices;
        this.skillsIndices = skillsIndices;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (index.getZeroBased() >= model.getResumeSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        checkIndicesValidity(model);
        Resume toEdit = model.getResume(index);
        if (internshipIndices.isPresent()) {
            // Will change this to a more appropriate name
            List<Integer> listOfId = internshipIndices
                    .get()
                    .stream()
                    .map(x -> model.getInternship(Index.fromOneBased(x)).getId())
                    .collect(Collectors.toList());
            model.editResume(toEdit, listOfId);
        }

        model.setResumeToDisplay();
        model.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
        model.commitResumeBook();
        return new CommandResult(toEdit.toString(), "Resume is updated");
    }

    /**
     * Checks for whether the specified indices are valid.
     */
    private void checkIndicesValidity(Model model) throws CommandException {
        // Internships
        if (internshipIndices.isPresent()) {
            List<Integer> unboxedIndices = internshipIndices.get();
            for (Integer i: unboxedIndices) {
                if (Index.fromOneBased(i).getZeroBased() >= model.getInternshipSize()) {
                    // TODO: Use something from Message here
                    throw new CommandException("Invalid internship index " + i + " detected");
                }
            }
        }

        // Projects
        if (projectIndices.isPresent()) {
            List<Integer> unboxedIndices = projectIndices.get();
            for (Integer i: unboxedIndices) {
                if (Index.fromOneBased(i).getZeroBased() >= model.getInternshipSize()) {
                    // TODO: Use something from Message here
                    throw new CommandException("Invalid project index " + i + " detected");
                }
            }
        }

        // Skills
        if (skillsIndices.isPresent()) {
            List<Integer> unboxedIndices = skillsIndices.get();
            for (Integer i: unboxedIndices) {
                if (Index.fromOneBased(i).getZeroBased() >= model.getInternshipSize()) {
                    // TODO: Use something from Message here
                    throw new CommandException("Invalid skills index " + i + " detected");
                }
            }
        }
    }
}
