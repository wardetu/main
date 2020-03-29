package seedu.address.model.item;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;

import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.item.field.Type;
import seedu.address.model.item.field.Website;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.ItemUtil;

/**
 * The Project item.
 */
public class Project extends Item {

    // Data fields
    private Time time;
    private Website website;
    private String description;

    public Project(Name name, Time time, Website website, String description, Set<Tag> tags) {
        this(name, time, website, description, tags, ItemUtil.yieldId("proj"));
    }

    public Project(Name name, Time time, Website website, String description, Set<Tag> tags, int id) {
        super(name, id, tags);
        requireAllNonNull(time, website, description);
        this.type = Type.generate("proj");
        this.time = time;
        this.website = website;
        this.description = description;
    }

    public Time getTime() {
        return this.time;
    }

    public Website getWebsite() {
        return this.website;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String getSummary() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Time: ")
                .append(getTime());
        return builder.toString();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Project: ").append(super.toString()).append("\n")
                .append(getSummary()).append("\n")
                .append(getWebsite()).append("\n")
                .append(getDescription());
        return builder.toString();
    }

    @Override
    public boolean isSame(Item otherProject) {
        return super.isSame(otherProject)
                && ((Project) otherProject).getTime().equals(getTime());
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Project // instanceof handles nulls
                && getName().equals(((Project) other).getName())
                && time.equals(((Project) other).time) // state check
                && website.equals(((Project) other).website) // state check
                && description.equals(((Project) other).description)) // state check
                && getTags().equals(((Project) other).getTags());
    }
}
