package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.ResumeEditCommandResult;
import seedu.address.logic.commands.results.TagPullCommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Project;
import seedu.address.model.item.Resume;
import seedu.address.model.item.Skill;
import seedu.address.model.tag.Tag;

/**
 * Edits the content of a Resume.
 */
public class TagPullCommand extends Command {
    public static final String COMMAND_WORD = "tagpull";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Pulls all the items with the desired tag into the "
            + "resume in the application with the specified index.\n"
            + "Existing values will be added on top the new items to be added"
            + "Parameters: INDEX "
            + "[#/ TAG}\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + " tech";

    protected final Index index;
    protected final Set<Tag> tagList;

    public TagPullCommand(Index index, Set<Tag> tagList) {
        this.index = index;
        this.tagList = tagList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (index.getZeroBased() >= model.getResumeSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Resume toEdit = model.getResumeByIndex(index);

        // The item IDs of specified resume
        List<Integer> currInternshipIds = toEdit.getInternshipIds();
        List<Integer> currProjectIds = toEdit.getProjectIds();
        List<Integer> currSkillIds = toEdit.getSkillIds();

        // The item IDs with the desired tags
        List<Integer> pulledInternshipIds = tagList
                .stream()
                .map(model::getInternshipsByTag)
                .flatMap(Collection::stream)
                .map(Internship::getId)
                .collect(Collectors.toList());

        List<Integer> pulledProjectIds = tagList
                .stream()
                .map(model::getProjectsByTag)
                .flatMap(Collection::stream)
                .map(Project::getId)
                .collect(Collectors.toList());

        List<Integer> pulledSkillIds = tagList
                .stream()
                .map(model::getSkillsByTag)
                .flatMap(Collection::stream)
                .map(Skill::getId)
                .collect(Collectors.toList());

        // Concatenate both sets of items as we are adding on top
        List<Integer> internshipIds = Stream
                .concat(currInternshipIds.stream(), pulledInternshipIds.stream())
                .collect(Collectors.toList());

        List<Integer> projectIds = Stream
                .concat(currProjectIds.stream(), pulledProjectIds.stream())
                .collect(Collectors.toList());

        List<Integer> skillIds = Stream
                .concat(currSkillIds.stream(), pulledSkillIds.stream())
                .collect(Collectors.toList());

        Resume editedResume = new Resume(toEdit.getName(), toEdit.getId(), toEdit.getTags());
        model.editResume(editedResume, internshipIds, projectIds, skillIds);
        model.setResume(toEdit, editedResume);
        model.setResumeToDisplay();
        model.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
        model.commitResumeBook();
        return new TagPullCommandResult(editedResume.toString(), "Tag Pull is successful!",
                model.getDisplayType());
    }
}
