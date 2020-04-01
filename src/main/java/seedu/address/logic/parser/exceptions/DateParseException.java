package seedu.address.logic.parser.exceptions;

/**
 * Exception for parsing Date field.
 */
public class DateParseException extends ParseException {

    public DateParseException() {
        super("Date should be in this format: ...");
    }
}
