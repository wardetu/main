package seedu.address.testutil;

import java.util.TreeSet;

import seedu.address.model.item.Skill;
import seedu.address.model.item.field.Level;
import seedu.address.model.item.field.Name;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Skill objects.
 */
public class SkillBuilder {
    public static final String DEFAULT_NAME = "Github";
    public static final Level DEFAULT_LEVEL = Level.INTERMEDIATE;

    private Name name;
    private Level level;

    public SkillBuilder() {
        name = new Name(DEFAULT_NAME);
        level = DEFAULT_LEVEL;
        // TODO: Figure out how to initialise Level from int/String
    }

    /**
     * Initializes the SkillBuilder with the data of {@code skillToCopy}.
     */
    public SkillBuilder(Skill skillToCopy) {
        name = skillToCopy.getName();
        level = skillToCopy.getLevel();
    }

    /**
     * Sets the {@code Name} of the {@code Skill} that we are building.
     */
    public SkillBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Level} of the {@code Skill} that we are building.
     */
    public SkillBuilder withLevel(Level level) {
        this.level = level;
        return this;
    }

    public Skill build() {
        return new Skill(name, level, new TreeSet<Tag>());
    }
}
