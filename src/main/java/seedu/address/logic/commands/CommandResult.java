package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;
    private final String dataToUser;
    private final boolean userUpdated;

    /** Preview information about a resume. */
    private final boolean showPreview;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** Switch to take note*/
    private final boolean takeNote;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */

    public CommandResult(String dataToUser, String feedbackToUser, boolean userUpdated,
                         boolean showPreview, boolean showHelp, boolean exit, boolean takeNote) {
        this.userUpdated = userUpdated;
        this.dataToUser = requireNonNull(dataToUser);
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showPreview = showPreview;
        this.showHelp = showHelp;
        this.exit = exit;
        this.takeNote = takeNote;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String dataToUser, String feedbackToUser, boolean takeNote) {
        this(dataToUser, feedbackToUser, false, false, false, false, takeNote);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String dataToUser, String feedbackToUser) {

        this(dataToUser, feedbackToUser, false, false, false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public String getDataToUser() {
        return dataToUser;
    }

    public boolean isShowPreview() {
        return showPreview;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isUserUpdated() {
        return userUpdated;
    }

    public boolean hasItemChanged() {
        return !dataToUser.equals("");
    }

    public boolean isTakeNote() {
        return takeNote;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit
                && userUpdated == otherCommandResult.userUpdated
                && takeNote == otherCommandResult.takeNote
                && showPreview == otherCommandResult.showPreview;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataToUser, feedbackToUser, userUpdated, showHelp, exit, takeNote);
    }

}
