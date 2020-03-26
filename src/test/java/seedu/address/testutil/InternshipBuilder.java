package seedu.address.testutil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.item.Internship;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Internship objects.
 */
public class InternshipBuilder {
    public static final String DEFAULT_NAME = "Duke";
    public static final String DEFAULT_FROM = "03-2020";
    public static final String DEFAULT_TO = "06-2020";
    public static final String DEFAULT_ROLE = "Software engineering intern";
    public static final String DEFAULT_DESCRIPTION = "Did some work, made some money";
    public static final String[] DEFAULT_TAGS = {"java", "backend"};


    private Name name;
    private Set<Tag> tags = new HashSet<>();
    private Time from;
    private Time to;
    private String description;
    private String role;

    public InternshipBuilder() {
        name = new Name(DEFAULT_NAME);
        from = new Time(DEFAULT_FROM);
        to = new Time(DEFAULT_TO);
        role = DEFAULT_ROLE;
        description = DEFAULT_DESCRIPTION;
        tags.addAll(Arrays.stream(DEFAULT_TAGS).map(Tag::new).collect(Collectors.toList()));
    }

    /**
     * Creates a InternshipBuilder with a certain name.
     * @param name String name
     * @return InternshipBuilder with new name
     */
    public InternshipBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    public Internship build() {
        return new Internship(name, role, from, to, description, tags);
    }
}
