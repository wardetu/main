package seedu.address.logic.parser.exceptions;

public class DateParseException extends ParseException {
    private final String USAGE_MESSAGE = "Date should be in this format: ...";

    public DateParseException() {
        super("Date should be in this format: ...");
    }
}
