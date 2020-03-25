package seedu.address.testutil;

import java.util.TreeSet;

import seedu.address.model.item.Internship;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Internship objects.
 */
public class InternshipBuilder {

    public static final String DEFAULT_NAME = "Shopee";
    public static final String DEFAULT_ROLE = "Backend intern";
    public static final String DEFAULT_FROM = "05-2020";
    public static final String DEFAULT_TO = "08-2020";
    public static final String DEFAULT_DESCRIPTION = "This is my first internship.";

    private Name name;
    private String role;
    private Time from;
    private Time to;
    private String description;

    public InternshipBuilder() {
        name = new Name(DEFAULT_NAME);
        from = new Time(DEFAULT_FROM);
        to = new Time(DEFAULT_TO);
        role = DEFAULT_ROLE;
        description = DEFAULT_DESCRIPTION;
    }

    /**
     * Initializes the InternshipBuilder with the data of {@code internshipToCopy}.
     */
    public InternshipBuilder(Internship internshipToCopy) {
        name = internshipToCopy.getName();
        from = internshipToCopy.getFrom();
        to = internshipToCopy.getTo();
        role = internshipToCopy.getRole();
        description = internshipToCopy.getDescription();
    }

    /**
     * Sets the {@code Name} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Role} in the {@code Internship} that we are building.
     */
    public InternshipBuilder withRole(String role) {
        this.role = role;
        return this;
    }

    /**
     * Sets the from {@code Time} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withFrom(String time) {
        this.from = new Time(time);
        return this;
    }

    /**
     * Sets the to {@code Time} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withTo(String time) {
        this.to = new Time(time);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Project} that we are building.
     */
    public InternshipBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public Internship build() {
        return new Internship(name, role, from, to, description, new TreeSet<Tag>());
    }
}
