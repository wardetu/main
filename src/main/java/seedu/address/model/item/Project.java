package seedu.address.model.item;

import seedu.address.model.item.field.Name;
import seedu.address.model.tag.Tag;

import java.util.Set;

public class Project extends Item {

    public Project(Name name, Set<Tag> tags) {
        super(name, tags);
    }
}
