package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.Project;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.item.field.Website;
import seedu.address.model.tag.Tag;


/**
 * Jackson-friendly version of {@link Project}.
 */
public class JsonAdaptedProject {

    private final String name;
    private final int id;
    private final String website;
    private final String time;
    private final String description;

    private final List<JsonAdaptedTag> tagged = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedProject} with the given details.
     */
    @JsonCreator
    public JsonAdaptedProject(@JsonProperty("name") String name, @JsonProperty("id") int id,
                              @JsonProperty("time") String time, @JsonProperty("website") String website,
                              @JsonProperty("description") String description,
                              @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.id = id;
        this.website = website;
        this.time = time;
        this.description = description;
        if (tags != null) {
            this.tagged.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Project} into this class for Jackson use.
     */
    public JsonAdaptedProject(Project project) {
        this.name = project.getName().fullName;
        this.id = project.getId();
        this.time = project.getTime().toString();
        this.website = project.getWebsite().toString();
        this.description = project.getDescription();
        tagged.addAll(project.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));

    }


    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Project} object.
     */
    public Project toModelType() throws IllegalValueException {
        final List<Tag> tags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            tags.add(tag.toModelType());
        }

        return new Project(new Name(name), new Time(time), new Website(website), description, Set.copyOf(tags), id);

    }
}
