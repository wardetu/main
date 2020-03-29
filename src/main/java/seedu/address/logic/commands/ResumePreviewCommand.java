package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Resume;

import static java.util.Objects.requireNonNull;

public class ResumePreviewCommand extends Command {

    public static final String COMMAND_WORD = "rpreview";
    public static final String MESSAGE_SUCCESS = "Previewing successfully!";
    public static final String MESSAGE_FAILURE = "Cannot preview this resume.";

    private Index index;
    private Resume resume;

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

        String feedback = "Previewing resume at index " + index.getOneBased();
        return new CommandResult(false, toPreview.toString(), feedback, false, false);
    }

    private String getDataFromResume(Resume resume) {
        
        return "";
    }
}
