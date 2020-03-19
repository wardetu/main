package seedu.address.logic.commands;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Resume;

import java.util.Arrays;
import java.util.logging.Logger;

public class ResumeEditCommand extends Command {

    public static final String COMMAND_WORD = "redit";
    public final String resumes;
    public final String internships;
    public final String projects;
    public final String skills;
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);
    public ResumeEditCommand(String resumes, String internships, String projects, String skills) {
        this.resumes = resumes;
        this.internships = internships;
        this.projects = projects;
        this.skills = skills;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (resumes.split("\\s+").length == 0) {
            throw new CommandException("Zero resume");
        } else if (resumes.split("\\s+").length> 1) {
            throw new CommandException("Too many arguments");
        }
        try {
            int resumeIndex = Integer.parseInt(resumes.split("\\s+")[0]);
            int[] internshipIndices;
            int[] projectIndices;
            int[] skillIndices;

            if (internships.equals("null")) {
                internshipIndices = null;
            } else if (internships.equals("")) {
                internshipIndices = new int[0];
            } else {
                internshipIndices = Arrays.stream(internships.split("\\s+"))
                            .map(Integer::parseInt)
                            .mapToInt(Integer::intValue)
                            .toArray();
            }


            if (projects.equals("null")) {
                projectIndices = null;
            } else if (projects.equals("")) {
                projectIndices = new int[0];
            } else {
                projectIndices = Arrays.stream(projects.split("\\s+"))
                        .map(Integer::parseInt)
                        .mapToInt(Integer::intValue)
                        .toArray();
            }

            if (skills.equals("null")) {
                skillIndices = null;
            } else if (skills.equals("")) {
                skillIndices = new int[0];
            } else {
                skillIndices = Arrays.stream(skills.split("\\s+"))
                        .map(Integer::parseInt)
                        .mapToInt(Integer::intValue)
                        .toArray();
            }
            logger.info("got here");
            if (!model.hasResumeId(resumeIndex)) {
                throw new CommandException("No item");
            }
            Resume toEdit = model.getResumeByIndex(resumeIndex);
            model.editResume(toEdit, internshipIndices, projectIndices, skillIndices);
            return new CommandResult(toEdit.toString(), "Congrats!");
        } catch (NumberFormatException e) {
            throw new CommandException(e.getMessage());
        }


    }
}