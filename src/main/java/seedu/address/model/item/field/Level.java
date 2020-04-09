package seedu.address.model.item.field;

/**
 *  Represents a Skill proficiency level.
 */
public enum Level {
    BASIC(1),
    INTERMEDIATE(2),
    ADVANCED(3);

    public static final String MESSAGE_CONSTRAINTS = "Level of proficiency can only be one of these three types: "
            + "basic, intermediate, advanced.";

    private final int levelCode;

    Level(int levelCode) {
        this.levelCode = levelCode;
    }

    public int getLevelCode() {
        return this.levelCode;
    }
}
