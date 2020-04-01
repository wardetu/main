package seedu.address.model.note;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.item.Item;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.item.field.Type;
import seedu.address.model.note.field.Description;
import seedu.address.model.note.field.Place;
import seedu.address.model.note.field.Title;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.ItemUtil;

public class NoteEntry extends Item {
    private Title title;
    private Time time;
    private Place place;
    private Description description;

    public NoteEntry(Name name, Title title, Time time, Place place, Description description, Set<Tag> tags) {
        this(name, title, time, place, description, tags, ItemUtil.yieldId(ItemUtil.NOTE_ALIAS));
    }

    public NoteEntry(Name name, Title title, Time time, Place place, Description description,
                     Set<Tag> tags, int id) {
        super(name, id, tags);
        requireAllNonNull(title, time, place, description);
        this.type = Type.generate(ItemUtil.NOTE_ALIAS);
        this.title = title;
        this.time = time;
        this.place = place;
        this.description = description;
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

    public Title getTitle() {
        return title;
    }

    public Time getTime() {
        return time;
    }

    public Place getPlace() {
        return place;
    }

    public Description getDescription() {
        return description;
    }

    public NoteEntry toCopy() {
        return new NoteEntry(getName(), getTitle(), getTime(), getPlace(), getDescription(),
                getTags(), getId());
    }

    @Override
    public String getSummary() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Note: ").append(getTitle()).append("\n")
                .append(getTime()).append("\n")
                .append(getPlace());
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NoteEntry // instanceof handles nulls
                && getName().equals(((NoteEntry) other).getName())
                && title.equals(((NoteEntry) other).title) // state check
                && time.equals(((NoteEntry) other).time)
                && place.equals(((NoteEntry) other).place)
                && description.equals(((NoteEntry) other).description))
                && getTags().equals(((NoteEntry) other).getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, time, place, description);
    }

    @Override
    public boolean isSame(Item otherNoteEntry) {
        return super.isSame(otherNoteEntry)
                && ((NoteEntry) otherNoteEntry).getTitle().equals(getTitle())
                && ((NoteEntry) otherNoteEntry).getTime().equals(getTime())
                && ((NoteEntry) otherNoteEntry).getPlace().equals(getPlace())
                && ((NoteEntry) otherNoteEntry).getDescription().equals(getDescription());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Title: ")
                .append(getTitle() + "\n")
                .append("Time: ")
                .append(getTime() + "\n")
                .append("Place: ")
                .append(getPlace() + "\n")
                .append("Description: ")
                .append(getDescription() + "\n");
        return builder.toString();
    }
}
