package seedu.address.model.item;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.address.model.item.field.Level;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Type;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.ItemUtil;

/**
 * The Skill item.
 */
public class Skill extends Item {

    // Data fields
    private Level level;

    public Skill(Name name, Level level, Set<Tag> tags) {
        this(name, level, tags, ItemUtil.yieldId("ski"));
    }

    public Skill(Name name, Level level, Set<Tag> tags, int id) {
        super(name, id, tags);
        requireNonNull(level);
        this.type = Type.generate("ski");
        this.level = level;
    }

    public Level getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = Level.valueOf(level);
    }

    @Override
    public String getSummary() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Level: ").append(getLevel());
        return builder.toString();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString()).append("\n")
                .append(getSummary());
        return builder.toString();
    }
}
