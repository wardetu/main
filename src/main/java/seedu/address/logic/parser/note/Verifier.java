package seedu.address.logic.parser.note;

import seedu.address.model.note.field.Place;
import seedu.address.model.note.field.Title;

/**
 * Validations for different input.
 */
public class Verifier {

    /**
     * Check if a field is within length limit.
     * @param input field.
     * @param maxLength maximum length allowed.
     * @return true or false.
     */
    public static boolean isCorrectLength(String input, int maxLength) {
        if (input.length() <= maxLength) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if an input is empty.
     * @param input input.
     * @return true or false.
     */
    public static boolean isNotEmpty(String input) {
        if (input.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Check if the title field is valid.
     * @param title
     * @return
     */
    public static boolean isValidTitle(String title) {
        if (isNotEmpty(title) && isCorrectLength(title, Title.TITLE_MAX_LENGTH)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if the Place field is valid.
     * @param place
     * @return
     */
    public static boolean isValidPlace(String place) {
        if (isNotEmpty(place) && isCorrectLength(place, Place.PLACE_MAX_LENGTH)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if the Description field is valid.
     * @param description
     * @return
     */
    public static boolean isValidDescription(String description) {
        if (isNotEmpty(description) && isCorrectLength(description, Place.PLACE_MAX_LENGTH)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get integer value of input.
     * @param input
     * @return
     */
    private static int getInt(String input) {
        return Integer.parseInt(input);
    }
}
