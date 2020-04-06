package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.Note;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Note}.
 */
public class JsonAdaptedNote {

    private final String name;
    private final int id;
    private final String time;
    private final boolean isDone;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    @JsonCreator
    public JsonAdaptedNote(@JsonProperty("name") String name, @JsonProperty("id") int id,
                           @JsonProperty("time") String time, @JsonProperty("isDone") boolean isDone,
                           @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.id = id;
        this.time = time;
        this.isDone = isDone;
        if (tags != null) {
            this.tagged.addAll(tags);
        }
    }

    public JsonAdaptedNote(Note note) {
        this.name = note.getName().fullName;
        this.id = note.getId();
        this.time = note.getTime().toString();
        this.isDone = note.isDone();
        tagged.addAll(note.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
    }

    /**
     * Convert Json Note to model-typed Note
     * @return
     * @throws IllegalValueException
     */
    public Note toModelType() throws IllegalValueException {
        final List<Tag> tags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            tags.add(tag.toModelType());
        }

        return new Note(new Name(name), new Time(time), isDone, Set.copyOf(tags), id);
    }
}
