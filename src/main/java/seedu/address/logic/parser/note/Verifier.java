package seedu.address.logic.parser.note;

import java.util.Arrays;

import seedu.address.model.note.field.Place;
import seedu.address.model.note.field.Title;

public class Verifier {

    static final int HOUR_LOWER_RANGE = 00;
    static final int HOUR_UPPER_RANGE = 23;
    static final int MIN_LOWER_RANGE = 00;
    static final int MIN_UPPER_RANGE = 59;
    static final int YEAR_LOWER_RANGE = 1980;
    static final int YEAR_UPPER_RANGE = 2030;
    static final int MONTH_LOWER_RANGE = 01;
    static final int MONTH_UPPER_RANGE = 12;
    static final int DAY_LOWER_RANGE = 01;
    static final int DAY_UPPER_RANGE = 31;
    static final int DATE_AND_TIME_LENGTH = 15;
    private static final String ALPHANUMERIC_STRING_REGEX = "[a-zA-Z0-9]+";
    private static final String NUMBER_REGEX = "[0-9]+";

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

    public static boolean isValidDate(String date) {
        if (isNotEmpty(date) && isCorrectDateForm(date)) {
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

    /** Implement this check later
     *
     * @param date
     * @return
     */
    public static boolean isCorrectDateForm(String input) {
        if (input.length() != DATE_AND_TIME_LENGTH) {
            return false;
        }
        String[] dateAndTime = input.split(" ");
        if (dateAndTime.length != 2) {
            return false;
        }
        String time = dateAndTime[1];
        if (time.length() != 4) {
            return false;
        }
        String hourAsString = time.substring(0, 1);
        String minAsString = time.substring(2);
        String[] dates = dateAndTime[0].split("/");
        if (dates.length != 3) {
            return false;
        }
        String dayAsString = dates[0];
        String monthAsString = dates[1];
        String yearAsString = dates[2];
        boolean firstCheck = (isValidNumber(hourAsString, HOUR_LOWER_RANGE, HOUR_UPPER_RANGE)
                &&
                isValidNumber(minAsString, MIN_LOWER_RANGE, MIN_UPPER_RANGE)
                &&
                isValidNumber(dayAsString, DAY_LOWER_RANGE, DAY_UPPER_RANGE)
                &&
                isValidNumber(monthAsString, MONTH_LOWER_RANGE, MONTH_UPPER_RANGE)
                &&
                isValidNumber(yearAsString, YEAR_LOWER_RANGE, YEAR_UPPER_RANGE)
        );
        /**
         *
         */
        boolean secondCheck = isValidDayMonthYear(getInt(dayAsString),
                getInt(monthAsString), getInt(yearAsString));
        return firstCheck && secondCheck;
    }

    private static boolean isValidDayMonthYear(int day, int month, int year) {
        int[] oddMonth = {1, 3, 5, 7, 8, 10, 12}; //31 days
        int[] evenMonth = {2, 4, 6, 9, 11}; //30 days
        if (Arrays.stream(evenMonth)
                .anyMatch(x -> x == month) && day == 31) {
            return false;
        } else if (month == 2) {
            if (isLeapYear(year) && day == 29) {
                return true;
            } else if (day == 28) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    private static boolean isLeapYear(int year) {
        return year % 4 == 0;
    }


    private static boolean isValidNumber(String input, int lower, int upper) {
        if (!input.matches(NUMBER_REGEX)) {
            return false;
        }
        int test = getInt(input);
        if (test <= upper && test >= lower) {
            return true;
        } else {
            return false;
        }
    }


    private static int getInt(String input) {
        return Integer.parseInt(input);
    }

    }
