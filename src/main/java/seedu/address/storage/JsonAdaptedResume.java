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
 * asdad
 */
public class JsonAdaptedResume {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final int id;

    private final List<Integer> internshipIndices = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * asdasd
     * @param name asda
     * @param id adasd
     * @param internshipIndices a
     * @param tags asd
     */
    @JsonCreator
    public JsonAdaptedResume(@JsonProperty("name") String name, @JsonProperty("id") int id,
                             @JsonProperty("internships") List<Integer> internshipIndices,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.id = id;
        if (tags != null) {
            this.tagged.addAll(tags);
        }
        if (internshipIndices != null) {
            this.internshipIndices.addAll(internshipIndices);
        }
    }

    /**
     * Construct
     * @param res filelr
     */
    public JsonAdaptedResume(Resume res) {
        this.name = res.getName().fullName;
        this.id = res.getId();
        tagged.addAll(res.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
        internshipIndices.addAll(res.getInternships());

    }

    /**
     * asdasd
     * @return sadasd
     * @throws IllegalValueException adasd
     */
    public Resume toModelType() throws IllegalValueException {
        final List<Tag> tags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            tags.add(tag.toModelType());
        }

        Resume resume = new Resume(new Name(name), id, Set.copyOf(tags));
        for (int internship : internshipIndices) {
            resume.addInternships(internship);
        }
        return resume;
    }
}
