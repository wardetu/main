package seedu.address.model.item.field;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Cumulative Average Point in the resume book.
 */
public class Cap {
    public static final String MESSAGE_CONSTRAINTS = "CAP should only contain numeric characters and must be in the "
            + "format of \"current maximum\", where the current CAP must not be greater than maximum CAP, and they "
            + "must not be blank. Example 4.50 5.00";

    public static final String VALIDATION_REGEX = "^(-?\\d*\\.\\d*|-?\\d)\\s+(-?\\d*\\.\\d*|-?\\d)";
    public final Double current;
    public final Double max;

    /**
     * Constructs a {@code Cap}.
     *
     * @param cap a valid CAP value.
     */
    public Cap(String cap) {
        requireNonNull(cap);
        checkArgument(isValidCap(cap), MESSAGE_CONSTRAINTS);
        String[] values = cap.split(" ");
        current = Double.valueOf(values[0]);
        max = Double.valueOf(values[1]);
    }

    /**
     * Returns true if the cap is a valid CAP.
     * @param cap the cap value to be validated.
     * @return true if the cap is a valid CAP.
     */
    public static boolean isValidCap(String cap) {
        return cap.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return current + "/" + max;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Cap) // instanceof handles nulls
                && current.equals(((Cap) other).current) // state check
                && max.equals(((Cap) other).max);
    }
}
