package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Project;
import seedu.address.model.item.Resume;
import seedu.address.model.item.Skill;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class ResumePreviewCommand extends Command {

    public static final String COMMAND_WORD = "rpreview";
    public static final String MESSAGE_SUCCESS = "Previewing successfully!";
    public static final String MESSAGE_FAILURE = "Cannot preview this resume.";

    private Index index;

    public ResumePreviewCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (index.getZeroBased() >= model.getResumeSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Resume toPreview = model.getResume(index);

        String data = getDataFromResume(toPreview, model);

        String feedback = "Previewing resume at index " + index.getOneBased();
        return new CommandResult(false, data, feedback, false, false);
    }

    private String getDataFromResume(Resume resume, Model model) {
        List<String> internshipList = resume.getInternships()
                .stream()
                .map(x -> model.hasInternshipId(x) ? model.getInternshipById(x) : null)
                .filter(Objects::nonNull)
                .map(Internship::toString)
                .collect(Collectors.toList());
        List<String> projectList = resume.getProjects()
                .stream()
                .map(x -> model.hasProjectId(x) ? model.getProjectById(x) : null)
                .filter(Objects::nonNull)
                .map(Project::toString)
                .collect(Collectors.toList());
        List<String> skillsList = resume.getSkills()
                .stream()
                .map(x ->  model.hasSkillId(x) ? model.getSkillById(x) : null)
                .filter(Objects::nonNull)
                .map(Skill::toString)
                .collect(Collectors.toList());
        StringBuilder data = new StringBuilder(resume.toString() + "\n");
        data.append("\n=========================\n");
        data.append("INTERNSHIPS\n");
        for (String internship: internshipList) {
            data.append(internship).append("\n");
        }
        data.append("\n=========================\n");
        data.append("PROJECTS\n");
        for (String project: projectList) {
            data.append(project).append("\n");
        }
        data.append("\n=========================\n");
        data.append("SKILLS\n");
        for (String skill: skillsList) {
            data.append(skill).append("\n");
        }

        return data.toString();
    }
}
