package seedu.address.logic.parser;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Validations for different inputs without a separate class.
 */
public class Verifier {

    public static final int UNIVERSITY_MAX_LENGTH = 50;
    public static final String UNIVERSITY_MESSAGE_CONSTRAINTS =
            "University should only contain alphanumeric characters and spaces, with max length of 50 characters"
                    + " and it should not be blank";
    public static final String UNIVERSITY_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";


    public static final int MAJOR_MAX_LENGTH = 50;
    public static final String MAJOR_MESSAGE_CONSTRAINTS =
            "Major should only contain alphanumeric characters and spaces, with max length of 50 characters"
                    + " and it should not be blank";
    public static final String MAJOR_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public static final String CAP_MESSAGE_CONSTRAINTS =
            "CAP should only contain numeric characters and current CAP should not be greater than maximum CAP and it "
                    + "should not be blank";

    public static final int ROLE_MAX_LENGTH = 50;
    public static final String ROLE_MESSAGE_CONSTRAINTS =
            "Major should only contain alphanumeric characters and spaces, with max length of 50 characters"
                    + " and it should not be blank";
    public static final String ROLE_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    /**
     * Checks if a field is within length limit.
     *
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
     * Checks if an input is empty.
     */
    public static boolean isNotEmpty(String input) {
        if (input.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Gets the integer value of input.
     */
    public static int getInt(String input) {
        return Integer.parseInt(input);
    }

    /**
     * Checks if the University field is valid.
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
     * Checks if the Major field is valid.
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
     * Checks if the Cap field is valid.
     */
    public static boolean isValidCap(String currentCap, String maxCap) throws ParseException {
        double userCurrentCap;
        double userMaximumCap;

        try {
            userCurrentCap = Double.valueOf(currentCap);
            userMaximumCap = Double.valueOf(maxCap);
        } catch (NumberFormatException ex) {
            throw new ParseException(CAP_MESSAGE_CONSTRAINTS);
        }

        if (isNotEmpty(currentCap) && isNotEmpty(maxCap) && userCurrentCap <= userMaximumCap) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if a number is within accepted range.
     *
     * @param number the number to be tested.
     * @param min the lower bound (inclusive).
     * @param max the upper bound (inclusive).
     * @return true or false.
     */
    public static boolean isWithinRange(double number, double min, double max) {
        return number >= min && number <= max;
    }

    /**
     * Checks if the Role field is valid.
     */
    public static boolean isValidRole(String role) {
        if (isNotEmpty(role) && isCorrectLength(role, ROLE_MAX_LENGTH)
                && role.matches(ROLE_VALIDATION_REGEX)) {
            return true;
        } else {
            return false;
        }
    }
}
