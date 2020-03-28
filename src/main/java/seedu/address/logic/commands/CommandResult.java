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

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(boolean userUpdated, String dataToUser, String feedbackToUser,
                         boolean showHelp, boolean exit) {
        this.userUpdated = userUpdated;
        this.dataToUser = requireNonNull(dataToUser);
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String dataToUser, String feedbackToUser) {
        this(false, dataToUser, feedbackToUser, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public String getDataToUser() {
        return dataToUser;
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
                && userUpdated == otherCommandResult.userUpdated;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userUpdated, dataToUser, feedbackToUser, showHelp, exit);
    }

}
