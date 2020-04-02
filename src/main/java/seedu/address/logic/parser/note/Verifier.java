package seedu.address.logic.parser.note;

import seedu.address.model.note.field.Place;
import seedu.address.model.note.field.Title;

/**
 * Validations for different input.
 */
public class Verifier {
    public static final int UNIVERSITY_MAX_LENGTH = 30;
    public static final String UNIVERSITY_MESSAGE_CONSTRAINTS =
            "University should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String UNIVERSITY_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public static final int MAJOR_MAX_LENGTH = 30;
    public static final String MAJOR_MESSAGE_CONSTRAINTS =
            "Major should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String MAJOR_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public static final String CAP_MESSAGE_CONSTRAINTS =
            "CAP should only contain numeric characters and must match actual range from 0.0 to 5.0 and it "
                    + "should not be blank";

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
    public static int getInt(String input) {
        return Integer.parseInt(input);
    }

    /**
     * Check if the University field is valid.
     * @param university
     * @return
     */
    public static boolean isValidUniversity(String university) {
        if (isNotEmpty(university) && isCorrectLength(university, UNIVERSITY_MAX_LENGTH)
                && university.matches(UNIVERSITY_VALIDATION_REGEX)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if the Major field is valid.
     * @param major
     * @return
     */
    public static boolean isValidMajor(String major) {
        if (isNotEmpty(major) && isCorrectLength(major, MAJOR_MAX_LENGTH)
                && major.matches(MAJOR_VALIDATION_REGEX)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if the Cap field is valid.
     * @param cap
     * @return
     */
    public static boolean isValidCap(String cap) {
        if (isNotEmpty(cap) && isWithinRange(Double.valueOf(cap), 0, 5)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isWithinRange(double number, double min, double max) {
        return number >= min && number <= max;
    }
}
