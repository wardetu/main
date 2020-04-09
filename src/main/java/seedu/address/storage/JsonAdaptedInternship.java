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
 * Jackson-friendly version of {@link Internship}.
 */
public class JsonAdaptedInternship {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Internship's %s field is missing!";

    private final String name;
    private final String id;
    private final String from;
    private final String to;
    private final String role;
    private final String description;

    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedInternship} with the given details.
     */
    @JsonCreator
    public JsonAdaptedInternship(@JsonProperty("name") String name, @JsonProperty("id") String id,
                             @JsonProperty("from") String from, @JsonProperty("to") String to,
                             @JsonProperty("role") String role, @JsonProperty("description") String description,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.id = id;
        this.from = from;
        this.to = to;
        this.role = role;
        this.description = description;
        if (tags != null) {
            this.tagged.addAll(tags);
        }
    }


    /**
     * Converts a given {@code Internship} into this class for Jackson use.
     */
    public JsonAdaptedInternship(Internship internship) {
        this.name = internship.getName().fullName;
        this.id = String.valueOf(internship.getId());
        this.from = internship.getFrom().toString();
        this.to = internship.getTo().toString();
        this.role = internship.getRole();
        this.description = internship.getDescription();
        tagged.addAll(internship.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));

    }


    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Internship} object.
     */
    public Internship toModelType() throws IllegalValueException {
        final List<Tag> tags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            tags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (role == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "role"));
        }

        if (from == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Time.class.getSimpleName()));
        }
        if (!Time.isValidTime(from)) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }
        final Time modelFrom = new Time(from);

        if (to == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Time.class.getSimpleName()));
        }
        if (!Time.isValidTime(to)) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }
        final Time modelTo = new Time(to);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "description"));
        }

        final int modelId;
        try {
            modelId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new IllegalValueException("The id field can only be an integer.");
        }
        if (modelId < 0) {
            throw new IllegalValueException("The id field must not be negative.");
        }

        return new Internship(modelName, role, modelFrom, modelTo, description, Set.copyOf(tags), modelId);
    }
}
