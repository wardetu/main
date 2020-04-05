package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.ResumeEditCommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Project;
import seedu.address.model.item.Resume;
import seedu.address.model.item.Skill;
import seedu.address.model.tag.Tag;

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
    protected final Set<Tag> tagList;
    protected final boolean isTagPull;
    protected final Optional<List<Integer>> internshipIndices;
    protected final Optional<List<Integer>> projectIndices;
    protected final Optional<List<Integer>> skillIndices;

    public ResumeEditCommand(Index index, Optional<List<Integer>> internshipIndices,
                             Optional<List<Integer>> projectIndices, Optional<List<Integer>> skillIndices,
                             Set<Tag> tagList, boolean isTagPull) {
        this.index = index;
        this.internshipIndices = internshipIndices;
        this.projectIndices = projectIndices;
        this.skillIndices = skillIndices;
        this.tagList = tagList;
        this.isTagPull = isTagPull;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (index.getZeroBased() >= model.getResumeSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        checkIndicesValidity(model);

        Resume toEdit = model.getResumeByIndex(index);

        List<Integer> internshipIds = toEdit.getInternshipIds();
        List<Integer> projectIds = toEdit.getProjectIds();
        List<Integer> skillIds = toEdit.getSkillIds();

        // Temporary implementation: Either you do a tag pull, or you do normally:
        if (isTagPull) {
            List<List<Integer>> listOfLists = tagPull(internshipIds, projectIds, skillIds, model);
            internshipIds = listOfLists.get(0);
            projectIds = listOfLists.get(1);
            skillIds = listOfLists.get(2);
        } else {
            List<List<Integer>> listOfLists = normalImplementation(internshipIds, projectIds, skillIds, model);
            internshipIds = listOfLists.get(0);
            projectIds = listOfLists.get(1);
            skillIds = listOfLists.get(2);
        }



        Resume editedResume = new Resume(toEdit.getName(), toEdit.getId(), toEdit.getTags());
        model.editResume(editedResume, internshipIds, projectIds, skillIds);
        model.setResume(toEdit, editedResume);
        model.setResumeToDisplay();
        model.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
        model.commitResumeBook();
        return new ResumeEditCommandResult(editedResume.toString(), "Resume is updated", model.getDisplayType());
    }

    private List<List<Integer>> normalImplementation(List<Integer> internshipIds, List<Integer> projectIds,
                                                    List<Integer> skillIds, Model model) {
        // If any of the indices are present (user keys in the prefix), then use what the user uses
        // Else, use the one currently being used by the resume

        if (internshipIndices.isPresent()) {
            internshipIds = internshipIndices
                    .get()
                    .stream()
                    .distinct()
                    .map(x -> model.getInternshipByIndex(Index.fromOneBased(x)).getId())
                    .collect(Collectors.toList());
        }

        if (projectIndices.isPresent()) {
            projectIds = projectIndices
                    .get()
                    .stream()
                    .distinct()
                    .map(x -> model.getProjectByIndex(Index.fromOneBased(x)).getId())
                    .collect(Collectors.toList());
        }

        if (skillIndices.isPresent()) {
            skillIds = skillIndices
                    .get()
                    .stream()
                    .distinct()
                    .map(x -> model.getSkillByIndex(Index.fromOneBased(x)).getId())
                    .collect(Collectors.toList());
        }
        List<List<Integer>> newList = new ArrayList<>();
        newList.add(internshipIds);
        newList.add(projectIds);
        newList.add(skillIds);
        return newList;
    }

    private List<List<Integer>> tagPull(List<Integer> internshipIds, List<Integer> projectIds, List<Integer> skillIds,
                                      Model model) {
        List<List<Integer>> newList = new ArrayList<>();
        internshipIds = tagList
                .stream()
                .map(model::getInternshipsByTag)
                .flatMap(Collection::stream)
                .map(Internship::getId)
                .collect(Collectors.toList());

        projectIds = tagList
                .stream()
                .map(model::getProjectsByTag)
                .flatMap(Collection::stream)
                .map(Project::getId)
                .collect(Collectors.toList());

        skillIds = tagList
                .stream()
                .map(model::getSkillsByTag)
                .flatMap(Collection::stream)
                .map(Skill::getId)
                .collect(Collectors.toList());

        newList.add(internshipIds);
        newList.add(projectIds);
        newList.add(skillIds);
        return newList;
    }

    /**
     * Checks for whether the specified indices are valid.
     */
    private void checkIndicesValidity(Model model) throws CommandException {
        // Internships
        if (internshipIndices.isPresent()) {
            List<Integer> unboxedIndices = internshipIndices.get();
            StringBuilder invalidIndices = new StringBuilder();
            boolean isInvalidIndexPresent = false;
            for (Integer i: unboxedIndices) {
                if (Index.fromOneBased(i).getZeroBased() >= model.getInternshipSize()) {
                    isInvalidIndexPresent = true;
                    invalidIndices.append(i.toString()).append(" ");
                }
            }

            if (isInvalidIndexPresent) {
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_REDIT_ITEM_INDEX, "internship",
                        invalidIndices.toString().trim()));
            }
        }

        // Projects
        if (projectIndices.isPresent()) {
            List<Integer> unboxedIndices = projectIndices.get();
            StringBuilder invalidIndices = new StringBuilder();
            boolean isInvalidIndexPresent = false;
            for (Integer i: unboxedIndices) {
                if (Index.fromOneBased(i).getZeroBased() >= model.getProjectSize()) {
                    isInvalidIndexPresent = true;
                    invalidIndices.append(i.toString()).append(" ");
                }
            }

            if (isInvalidIndexPresent) {
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_REDIT_ITEM_INDEX, "project",
                        invalidIndices.toString().trim()));
            }
        }

        // Skills
        if (skillIndices.isPresent()) {
            List<Integer> unboxedIndices = skillIndices.get();
            StringBuilder invalidIndices = new StringBuilder();
            boolean isInvalidIndexPresent = false;
            for (Integer i: unboxedIndices) {
                if (Index.fromOneBased(i).getZeroBased() >= model.getSkillSize()) {
                    isInvalidIndexPresent = true;
                    invalidIndices.append(i.toString()).append(" ");
                }
            }

            if (isInvalidIndexPresent) {
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_REDIT_ITEM_INDEX, "skill",
                        invalidIndices.toString().trim()));
            }
        }
    }
}
