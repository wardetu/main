package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.note.NoteEntry;
import seedu.address.model.note.field.Description;
import seedu.address.model.note.field.Place;
import seedu.address.model.note.field.Title;
import seedu.address.model.tag.Tag;

public class JsonAdaptedNoteEntry {

    private final String name;
    private final int id;
    private final String title;
    private final String time;
    private final String place;
    private final String description;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    @JsonCreator
    public JsonAdaptedNoteEntry(@JsonProperty("name") String name, @JsonProperty("id") int id,
                                @JsonProperty("title") String title, @JsonProperty("time") String time,
                                @JsonProperty("place") String place, @JsonProperty("description") String description,
                                @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.id = id;
        this.title = title;
        this.time = time;
        this.place = place;
        this.description = description;
        if (tags != null) {
            this.tagged.addAll(tags);
        }
    }

    public JsonAdaptedNoteEntry(NoteEntry noteEntry) {
        this.name = noteEntry.getName().fullName;
        this.id = noteEntry.getId();
        this.title = noteEntry.getTitle().toString();
        this.time = noteEntry.getTime().toString();
        this.place = noteEntry.getPlace().toString();
        this.description = noteEntry.getDescription().toString();
        tagged.addAll(noteEntry.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
    }

    public NoteEntry toModelType() throws IllegalValueException {
        final List<Tag> tags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            tags.add(tag.toModelType());
        }

        return new NoteEntry(new Name(name), new Title(title), new Time(time), new Place(place),
                new Description(description), Set.copyOf(tags), id);
    }
}
