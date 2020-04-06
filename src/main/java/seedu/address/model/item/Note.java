package seedu.address.model.item;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.item.field.Type;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.ItemUtil;

/**
 * Represents a Note.
 */
public class Note extends Item {
    private Time time;
    private boolean isDone;

    public Note(Name name, Time time, Set<Tag> tags) {
        this(name, time, false, tags, ItemUtil.yieldId(ItemUtil.NOTE_ALIAS));
    }

    /**
     * Constructor for Note.
     * @param name
     * @param time
     * @param isDone
     * @param tags
     * @param id
     */
    public Note(Name name, Time time, boolean isDone, Set<Tag> tags, int id) {
        super(name, id, tags);
        requireAllNonNull(time);
        this.type = Type.generate(ItemUtil.NOTE_ALIAS);
        this.time = time;
        this.isDone = isDone;
    }

    public Name getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public Type getType() {
        return type;
    }

    public Time getTime() {
        return time;
    }

    public boolean isDone() {
        return isDone;
    }

    public String isDoneToString() {
        return isDone() ? "Done!" : "Not Done :(";
    }

    public void markAsDone() {
        isDone = true;
    }

    /**
     * Defensive programming.
     * @return
     */
    public Note toCopy() {
        return new Note(getName(), getTime(), isDone(), getTags(), getId());
    }

    @Override
    public String getSummary() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Note: ").append(getName()).append("\n")
                .append(getTime()).append("\n");
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Note // instanceof handles nulls
                && getName().equals(((Note) other).getName())
                && time.equals(((Note) other).time)
                && isDone == (((Note) other).isDone)
                && getTags().equals(((Note) other).getTags()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(time);
    }

    @Override
    public boolean isSame(Item otherNote) {
        return super.isSame(otherNote)
                && ((Note) otherNote).getTime().equals(getTime())
                && ((Note) otherNote).isDone() == isDone();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Title: ")
                .append(getName() + "\n")
                .append("Time: ")
                .append(getTime() + "\n")
                .append("Status: ")
                .append(isDoneToString() + "\n");
        return builder.toString();
    }
}
