package seedu.address.logic.commands.results;

/**
 * Represents the result of the Help command execution.
 */
public class HelpCommandResult extends CommandResult {

    /**
     * Constructs a {@code HelpCommandResult} with the specified {@code dataToUser}, {@code feedbackToUser}, and
     * {@code displayType}.
     * @param dataToUser data to show user.
     * @param feedbackToUser feedback to user.
     * @param displayType the alias of the item type.
     */
    public HelpCommandResult(String dataToUser, String feedbackToUser, String displayType) {
        super(dataToUser, feedbackToUser, displayType);
        super.isShowPreview = false;
        super.isGenerate = false;
        super.isShowHelp = true;
        super.isExit = false;
    }
}
