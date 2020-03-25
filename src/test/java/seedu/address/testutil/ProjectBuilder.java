package seedu.address.testutil;

import java.util.TreeSet;

import seedu.address.model.item.Project;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.item.field.Website;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Project objects.
 */
public class ProjectBuilder {

    public static final String DEFAULT_NAME = "Orbital";
    public static final String DEFAULT_TIME = "05-2019";
    public static final String DEFAULT_WEBSITE = "www.myoribtalproject.com";
    public static final String DEFAULT_DESCRIPTION = "This is my first software development project.";

    private Name name;
    private Time time;
    private Website website;
    private String description;

    public ProjectBuilder() {
        name = new Name(DEFAULT_NAME);
        time = new Time(DEFAULT_TIME);
        website = new Website(DEFAULT_WEBSITE);
        description = DEFAULT_DESCRIPTION;
    }

    /**
     * Initializes the ProjectBuilder with the data of {@code projectToCopy}.
     */
    public ProjectBuilder(Project projectToCopy) {
        name = projectToCopy.getName();
        time = projectToCopy.getTime();
        website = projectToCopy.getWebsite();
        description = projectToCopy.getDescription();
    }

    /**
     * Sets the {@code Name} of the {@code Project} that we are building.
     */
    public ProjectBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code Project} that we are building.
     */
    public ProjectBuilder withTime(String time) {
        this.time = new Time(time);
        return this;
    }

    /**
     * Sets the {@code Website} of the {@code Project} that we are building.
     */
    public ProjectBuilder withWebsite(String website) {
        this.website = new Website(website);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Project} that we are building.
     */
    public ProjectBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public Project build() {
        return new Project(name, time, website, description, new TreeSet<Tag>());
    }

}
