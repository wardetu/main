package seedu.address.logic.commands.results;

/**
 * Represents the result of the Undo command execution.
 */
public class UndoCommandResult extends CommandResult {

    /**
     * Constructs an {@code UndoCommandResult} with the specified {@code dataToUser} and {@code feedbackToUser},
     * {@code displayType}.
     * @param dataToUser data to show user.
     * @param feedbackToUser feedback to user.
     * @param displayType the alias of the item type.
     */
    public UndoCommandResult(String dataToUser, String feedbackToUser, String displayType) {
        super(dataToUser, feedbackToUser, displayType);
        super.isShowPreview = false;
        super.isGenerate = false;
        super.isShowHelp = false;
        super.isExit = false;
    }
}
