package seedu.address.model.item;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.item.field.Address;
import seedu.address.model.item.field.Email;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Phone;
import seedu.address.model.tag.Tag;

/**
 * Represents an Item in the resume book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Item {

    private static int itemCount = 0;

    // Identity fields
    protected final int id;
    protected String type = "item"; // to be changed to pd, edu, achv, proj, int, ski, res later on
    protected final Name name;

    // Data fields
    protected final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Item(Name name, Phone phone, Email email, Address address, Set<Tag> tags) { // to remove phone, email, address
        requireAllNonNull(name);
        itemCount += 1;
        this.id = itemCount;
        this.name = name;
        this.tags.addAll(tags);
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Name getName() {
        return name;
    }

    // to be removed --- phone, email, address placeholders for integration with other parts
    public Phone getPhone() {
        return new Phone("000");
    }
    public Email getEmail() {
        return new Email("000@gmail.com");
    }
    public Address getAddress() {
        return new Address("000");
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both items of the same type have the same name.
     * This defines a weaker notion of equality between two items.
     */
    public boolean isSame(Item otherItem) {
        if (otherItem == this) {
            return true;
        }

        return otherItem != null
                && otherItem.getType().equals(getType())
                && otherItem.getName().equals(getName());
    }

    /**
     * Returns true if both items have the same identity.
     * This defines a stronger notion of equality between two items.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Item)) {
            return false;
        }

        Item otherItem = (Item) other;
        return otherItem.getId() == getId();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getId())
                .append(" Type: ")
                .append(getType())
                .append(" Name: ")
                .append(getName())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
