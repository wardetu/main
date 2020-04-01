package seedu.address.logic.commands.note;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.edit.EditItemDescriptor;
import seedu.address.model.item.field.Time;
import seedu.address.model.note.field.Description;
import seedu.address.model.note.field.Place;
import seedu.address.model.note.field.Title;
import seedu.address.model.tag.Tag;

/**
 * Stores the details to edit the internship with. Each non-empty field value will replace the
 * corresponding field value of the internship.
 */
public class EditNoteDescriptor extends EditItemDescriptor {
    private Title title;
    private Time time;
    private Place place;
    private Description description;
    private Set<Tag> tags;

    public EditNoteDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditNoteDescriptor(EditNoteDescriptor toCopy) {
        setName(toCopy.name);
        setTitle(toCopy.title);
        setTime(toCopy.time);
        setPlace(toCopy.place);
        setDescription(toCopy.description);
        setTags(toCopy.tags);
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Optional<Title> getTitle() {
        return Optional.ofNullable(title);
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Optional<Time> getTime() {
        return Optional.ofNullable(time);
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Optional<Place> getPlace() {
        return Optional.ofNullable(place);
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Optional<Description> getDescription() {
        return Optional.ofNullable(description);
    }

    /**
     * Sets {@code tags} to this object's {@code tags}.
     * A defensive copy of {@code tags} is used internally.
     */
    public void setTags(Set<Tag> tags) {
        this.tags = (tags != null) ? new HashSet<>(tags) : null;
    }

    /**
     * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code tags} is null.
     */
    public Optional<Set<Tag>> getTags() {
        return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
    }

    /**
     * Returns true if at least one field is edited.
     */
    @Override
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, title, time, place, description);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditNoteDescriptor)) {
            return false;
        }

        // state check
        EditNoteDescriptor e = (EditNoteDescriptor) other;

        return getName().equals(e.getName())
                && getTitle().equals(e.getTitle())
                && getTime().equals(e.getTime())
                && getPlace().equals(e.getPlace())
                && getDescription().equals(e.getDescription())
                && getTags().equals(e.getTags());
    }
}
