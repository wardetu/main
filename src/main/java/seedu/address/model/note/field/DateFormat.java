package seedu.address.model.note.field;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import seedu.address.logic.parser.exceptions.DateParseException;

public class DateFormat {

    private static SimpleDateFormat formatter;
    private static final String pattern = "dd/MM/yyyy HHmm";

    /**
     * Parse a date String into a Date object.
     * @param date
     * @return
     * @throws DateParseException
     */
    public static Date convertToDate(String date) throws DateParseException {
        try {
            formatter = new SimpleDateFormat(pattern);
            return formatter.parse(date);
        } catch (ParseException ex) {
            throw new DateParseException();
        }
    }

    /**
     * Format a Date object date into a String date for storage.
     * @param date
     * @return
     */
    public static String getStringForStorage(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        return formatter.format(date);
    }

    /**
     * Format Date object into a String date for display.
     * @param date
     * @return
     */
    public static String getStringForDisplay(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
        return formatter.format(date);
    }
}
