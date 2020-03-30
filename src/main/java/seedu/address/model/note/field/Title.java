package seedu.address.model.note.field;

/**
 * Represents the title of each Note entry.
 */
public class Title {
    public static final String MESSAGE_CONSTRAINTS = "Title has to be non-empty, contains letters and is "
            + "maximum 50 characters.";

    public static final int TITLE_MAX_LENGTH = 50;

    private final String title;

    /**
     * Constructor for title
     * @param title takes in a String.
     */
    public Title(String title) {
        this.title = title;
    }

    /**
     * Defensive programming
     */
    public Title toCopy() {
        return new Title(title);
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Title && title.equalsIgnoreCase(((Title) other).getTitle()));
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
