package seedu.address.logic.parser.note;

import seedu.address.model.note.field.Place;
import seedu.address.model.note.field.Title;

public class Verifier {

    public static boolean isCorrectLength(String input, int maxLength) {
        if (input.length() <= maxLength) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(String input) {
        if (input.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isValidTitle(String title) {
        if (isNotEmpty(title) && isCorrectLength(title, Title.TITLE_MAX_LENGTH)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidPlace(String place) {
        if (isNotEmpty(place) && isCorrectLength(place, Place.PLACE_MAX_LENGTH)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidDescription(String description) {
        if (isNotEmpty(description) && isCorrectLength(description, Place.PLACE_MAX_LENGTH)) {
            return true;
        } else {
            return false;
        }
    }

    private static int getInt(String input) {
        return Integer.parseInt(input);
    }

    }
