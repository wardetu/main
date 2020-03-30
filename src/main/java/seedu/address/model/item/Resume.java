package seedu.address.model.item;

import java.util.ArrayList;
import java.util.Set;

import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Type;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.ItemUtil;

/**
 * The Resume item.
 */
public class Resume extends Item {


    // Item-level fields

    // Data fields
    private final ArrayList<Integer> internships = new ArrayList<>();
    private final ArrayList<Integer> projects = new ArrayList<>();
    private final ArrayList<Integer> skills = new ArrayList<>();

    public Resume(Name name, Set<Tag> tags) {
        this(name, ItemUtil.yieldId(ItemUtil.RESUME_ALIAS), tags);
    }

    public Resume(Name name, int id, Set<Tag> tags) {
        super(name, id, tags);
        this.type = Type.generate(ItemUtil.RESUME_ALIAS);
        // TODO: change Resume constructor to take in existing lists of internships, projects or skills
    }

    public void addInternship(int value) {
        internships.add(value);
    }

    public void setInternships(int... values) {
        internships.clear();
        for (int i : values) {
            internships.add(i);
        }
    }

    public ArrayList<Integer> getInternships() {
        return this.internships;
    }

    public void addProject(int value) {
        projects.add(value);
    }

    public ArrayList<Integer> getProjects() {
        return this.projects;
    }

    public void setProjects(int... values) {
        projects.clear();
        for (int i : values) {
            projects.add(i);
        }
    }

    public void addSkill(int value) {
        skills.add(value);
    }

    public ArrayList<Integer> getSkills() {
        return this.skills;
    }

    public void setSkills(int... values) {
        skills.clear();
        for (int i : values) {
            skills.add(i);
        }
    }

    @Override
    public String getSummary() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getInternships().size())
                .append(" internship(s), ")
                .append(getProjects().size())
                .append(" project(s), ")
                .append(getSkills().size())
                .append(" skill(s).");
        return builder.toString();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Resume: ")
                .append(super.toString()).append("\n")
                .append(getSummary());
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Resume // instanceof handles nulls
                && getName().equals(((Resume) other).getName())
                && getTags().equals(((Resume) other).getTags()));
    }
}
