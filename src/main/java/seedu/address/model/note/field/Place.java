package seedu.address.model.note.field;

/**
 * Place
 */
public class Place {
    public static final String MESSAGE_CONSTRAINTS = "Place can be optional but if inputed it must be a String.";

    public static final int PLACE_MAX_LENGTH = 100;

    private final String place;

    public Place(String place) {
        this.place = place;
    }

    /**
     * Defensive programming.
     */
    public Place toCopy() {
        return new Place(place);
    }

    @Override
    public String toString() {
        return place;
    }

    public String getPlace() {
        return place;
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof Place)
                && place.equalsIgnoreCase(((Place) other).getPlace());
    }

    @Override
    public int hashCode() {
        return place.hashCode();
    }
}
