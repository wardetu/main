package seedu.address.model.item.field;

/**
 *  Represents a Skill proficiency level.
 */
public enum Level {
    BASIC(1),
    INTERMEDIATE(2),
    ADVANCED(3);

    private final int levelCode;

    Level(int levelCode) {
        this.levelCode = levelCode;
    }

    public int getLevelCode() {
        return this.levelCode;
    }
}
