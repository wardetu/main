package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.Skill;
import seedu.address.model.item.field.Level;
import seedu.address.model.item.field.Name;
import seedu.address.model.tag.Tag;


/**
 * Jackson-friendly version of {@link Skill}.
 */
public class JsonAdaptedSkill {

    private final String name;
    private final int id;
    private final String level;

    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedSkill} with the given details.
     */
    @JsonCreator
    public JsonAdaptedSkill(@JsonProperty("name") String name, @JsonProperty("id") int id,
                                 @JsonProperty("level") String level, @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.id = id;
        this.level = level;
        if (tags != null) {
            this.tagged.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Skill} into this class for Jackson use.
     */
    public JsonAdaptedSkill(Skill skill) {
        this.name = skill.getName().fullName;
        this.id = skill.getId();
        this.level = skill.getLevel().toString();
        tagged.addAll(skill.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));

    }


    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Skill} object.
     */
    public Skill toModelType() throws IllegalValueException {
        final List<Tag> tags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            tags.add(tag.toModelType());
        }

        return new Skill(new Name(name), Level.valueOf(level), Set.copyOf(tags), id);

    }
}
