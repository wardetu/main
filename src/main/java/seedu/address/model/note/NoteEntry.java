package seedu.address.model.note;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Date;
import java.util.Objects;

import seedu.address.model.note.field.DateFormat;
import seedu.address.model.note.field.Description;
import seedu.address.model.note.field.Place;
import seedu.address.model.note.field.Title;

public class NoteEntry {
    private final Title title;
    private final Date date;
    private final Place place;
    private final Description description;

    public NoteEntry(Title title, Date date, Place place, Description description) {
        requireAllNonNull(title, date, place, description);
        this.title = title;
        this.date = date;
        this.place = place;
        this.description = description;
    }

    public Title getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public Place getPlace() {
        return place;
    }

    public Description getDescription() {
        return description;
    }

    public String getDateAsStringForPrint() {
        return DateFormat.getStringForDisplay(date);
    }

    public String getDateAsStringForStorage() {
        return DateFormat.getStringForStorage(date);
    }

    public NoteEntry toCopy() {
        return new NoteEntry(getTitle(), getDate(), getPlace(), getDescription());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof NoteEntry)) {
            return false;
        }

        return getTitle().equals(((NoteEntry) other).getTitle())
                && getDateAsStringForPrint().equalsIgnoreCase(((NoteEntry) other).getDateAsStringForPrint());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, date);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Title: ")
                .append(getTitle() + "\n")
                .append("Date: ")
                .append(getDateAsStringForPrint() + "\n")
                .append("Place: ")
                .append(getPlace() + "\n")
                .append("Description ")
                .append(getDescription() + "\n");
        return builder.toString();
    }
}
