package seedu.address.testutil;

import java.util.TreeSet;

import seedu.address.model.item.Resume;
import seedu.address.model.item.field.Name;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Resume objects.
 */
public class ResumeBuilder {

    public static final String DEFAULT_NAME = "Software Engineering Resume";
    public static final TreeSet<Integer> DEFAULT_INTERNSHIPS = new TreeSet<>();
    public static final TreeSet<Integer> DEFAULT_PROJECTS = new TreeSet<>();
    public static final TreeSet<Integer> DEFAULT_SKILLS = new TreeSet<>();

    private Name name;
    private TreeSet<Integer> internships;
    private TreeSet<Integer> projects;
    private TreeSet<Integer> skills;

    public ResumeBuilder() {
        name = new Name(DEFAULT_NAME);
        internships = DEFAULT_INTERNSHIPS;
        projects = DEFAULT_PROJECTS;
        skills = DEFAULT_SKILLS;
    }

    /**
     * Initializes the ResumeBuilder with the data of {@code resumeToCopy}.
     */
    public ResumeBuilder(Resume resumeToCopy) {
        name = resumeToCopy.getName();
        internships = resumeToCopy.getInternships();
        projects = resumeToCopy.getProjects();
        skills = resumeToCopy.getSkills();
    }

    /**
     * Sets the {@code Name} of the {@code Resume} that we are building.
     */
    public ResumeBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the internship list of the {@code Resume} that we are building.
     */
    public ResumeBuilder withInternships(TreeSet<Integer> internships) {
        this.internships = internships;
        return this;
    }

    /**
     * Sets the project list of the {@code Resume} that we are building.
     */
    public ResumeBuilder withProjects(TreeSet<Integer> projects) {
        this.projects = projects;
        return this;
    }

    /**
     * Sets the skill list of the {@code Resume} that we are building.
     */
    public ResumeBuilder withSkills(TreeSet<Integer> skills) {
        this.skills = skills;
        return this;
    }

    public Resume build() {
        return new Resume(name, new TreeSet<Tag>());
    }
}
