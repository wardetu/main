package seedu.address.testutil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.item.Resume;
import seedu.address.model.item.field.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Resume objects.
 */
public class ResumeBuilder {
    public static final String DEFAULT_NAME = "Company A";
    public static final String[] DEFAULT_TAGS = {"backend", "naps"};

    private Name name;
    private Set<Tag> tags = new HashSet<>();

    public ResumeBuilder() {
        name = new Name(DEFAULT_NAME);
        tags.addAll(Arrays.stream(DEFAULT_TAGS).map(Tag::new).collect(Collectors.toList()));
    }

    public ResumeBuilder(Resume resumeToCopy) {
        name = resumeToCopy.getName();
        tags = new HashSet<>(resumeToCopy.getTags());
    }

    /**
     * Creates a ResumeBuilder with a certain name.
     * @param name String name
     * @return ResumeBuilder with new name
     */
    public ResumeBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Resume} that we are building.
     */
    public ResumeBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }


    public Resume build() {
        return new Resume(name, tags);
    }
}
