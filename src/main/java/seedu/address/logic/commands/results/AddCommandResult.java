package seedu.address.logic.commands.results;

/**
 * Represents the result of the Add command execution.
 */
public class AddCommandResult extends CommandResult {
    /**
     * Constructs a {@code CommandResult} with the specified {@code dataToUser} and {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public AddCommandResult(String dataToUser, String feedbackToUser, String displayType) {
        super(dataToUser, feedbackToUser, displayType);
        super.isShowPreview = false;
        super.isGenerate = false;
        super.isShowHelp = false;
        super.isExit = false;
    }
}
