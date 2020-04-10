package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.Resume;
import seedu.address.model.item.field.Name;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Resume}.
 */
public class JsonAdaptedResume {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Resume's %s field is missing!";

    private final String name;
    private final String id;

    private final List<String> containedInternshipIds = new ArrayList<>();
    private final List<String> containedProjectIds = new ArrayList<>();
    private final List<String> containedSkillIds = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedResume} with the given details.
     */
    @JsonCreator
    public JsonAdaptedResume(@JsonProperty("name") String name, @JsonProperty("id") String id,
                             @JsonProperty("internships") List<String> internshipIds,
                             @JsonProperty("projects") List<String> projectIds,
                             @JsonProperty("skills") List<String> skillIds,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.id = id;
        if (tags != null) {
            this.tagged.addAll(tags);
        }
        if (internshipIds != null) {
            this.containedInternshipIds.addAll(internshipIds);
        }
        if (projectIds != null) {
            this.containedProjectIds.addAll(projectIds);
        }
        if (skillIds != null) {
            this.containedProjectIds.addAll(skillIds);
        }
    }

    /**
     * Converts a given {@code Resume} into this class for Jackson use.
     */
    public JsonAdaptedResume(Resume res) {
        this.name = res.getName().fullName;
        this.id = String.valueOf(res.getId());
        tagged.addAll(res.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
        containedInternshipIds.addAll(
                res.getInternshipIds().stream().map(String::valueOf).collect(Collectors.toList()));
        containedProjectIds.addAll(
                res.getProjectIds().stream().map(String::valueOf).collect(Collectors.toList()));
        containedSkillIds.addAll(
                res.getSkillIds().stream().map(String::valueOf).collect(Collectors.toList()));

    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Resume} object.
     */
    public Resume toModelType() throws IllegalValueException {
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

        final int modelId;
        try {
            modelId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new IllegalValueException("The id field can only be an integer.");
        }
        if (modelId < 0) {
            throw new IllegalValueException("The id field must not be negative.");
        }

        Resume resume = new Resume(modelName, modelId, Set.copyOf(tags));

        try {
            for (String internship : containedInternshipIds) {
                resume.addInternshipId(Integer.parseInt(internship));
            }

            for (String project : containedProjectIds) {
                resume.addProjectId(Integer.parseInt(project));
            }

            for (String skill : containedSkillIds) {
                resume.addSkillId(Integer.parseInt(skill));
            }
        } catch (NumberFormatException e) {
            throw new IllegalValueException("The id field of items contained in a resume can only be integer.");
        }

        return resume;
    }
}
