package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.Internship;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.tag.Tag;

/**
 * asdad
 */
public class JsonAdaptedInternship {

    private final String name;
    private final int id;
    private final Time from;
    private final Time to;
    private final String role;
    private final String description;

    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    @JsonCreator
    public JsonAdaptedInternship(@JsonProperty("name") String name, @JsonProperty("id") int id,
                             @JsonProperty("from") String from, @JsonProperty("to") String to,
                             @JsonProperty("role") String role, @JsonProperty("description") String description,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.id = id;
        this.from = new Time(from);
        this.to = new Time(to);
        this.role = role;
        this.description = description;
        if (tags != null) {
            this.tagged.addAll(tags);
        }
    }

    /**
     * adad
     * @param internship adsasd
     */
    public JsonAdaptedInternship(Internship internship) {
        this.name = internship.getName().fullName;
        this.id = internship.getId();
        this.from = internship.getFrom();
        this.to = internship.getTo();
        this.role = internship.getRole();
        this.description = internship.getDescription();
        tagged.addAll(internship.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));

    }

    /**
     * asdasd
     * @return adad
     * @throws IllegalValueException adsasd
     */
    public Internship toModelType() throws IllegalValueException {
        final List<Tag> tags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            tags.add(tag.toModelType());
        }

        return new Internship(new Name(name), role, from, to, description, Set.copyOf(tags), id);

    }
}
