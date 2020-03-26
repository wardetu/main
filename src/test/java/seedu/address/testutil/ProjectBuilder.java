package seedu.address.testutil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.item.Project;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.item.field.Website;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Project objects.
 */
public class ProjectBuilder {
    public static final String DEFAULT_NAME = "Duke";
    public static final String DEFAULT_TIME = "03-2020";
    public static final String DEFAULT_WEBSITE = "duke.com";
    public static final String DEFAULT_DESCRIPTION = "For a little module called CS2103T";
    public static final String[] DEFAULT_TAGS = {"java", "2103"};


    private Name name;
    private Set<Tag> tags = new HashSet<>();
    private Time time;
    private Website website;
    private String description;

    public ProjectBuilder() {
        name = new Name(DEFAULT_NAME);
        time = new Time(DEFAULT_TIME);
        website = new Website(DEFAULT_WEBSITE);
        description = DEFAULT_DESCRIPTION;
        tags.addAll(Arrays.stream(DEFAULT_TAGS).map(Tag::new).collect(Collectors.toList()));
    }

    /**
     * Creates a ProjectBuilder with a certain name.
     * @param name String name
     * @return ProjectBuilder with new name
     */
    public ProjectBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    public Project build() {
        return new Project(name, time, website, description, tags);
    }
}
