package seedu.address.model.item;

import seedu.address.model.item.field.Name;
import seedu.address.model.tag.Tag;

import java.util.Set;

public class Internship extends Item {

    public Internship(Name name, Set<Tag> tags) {
        super(name, tags);
    }
}
