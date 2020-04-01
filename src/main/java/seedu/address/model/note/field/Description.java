package seedu.address.model.note.field;

/**
 * Description field in each Note object.
 */
public class Description {
    public static final String MESSAGE_CONSTRAINTS = "Description can be optional but once inputted it "
            + "has to be a String.";

    public static final int MESSAGE_MAX_LENGTH = 100;

    private final String description;

    public Description(String description) {
        this.description = description;
    }

    /**
     * Defensive programming.
     */
    public Description toCopy() {
        return new Description(description);
    }

    @Override
    public String toString() {
        return description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof Description)
                && description.equalsIgnoreCase(((Description) other).getDescription());
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}
