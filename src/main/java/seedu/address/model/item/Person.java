package seedu.address.model.item;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.item.field.Email;
import seedu.address.model.item.field.Github;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Phone;
import seedu.address.model.item.field.Time;
import seedu.address.model.item.field.Type;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {
    
    private final Type type;
    
    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Github github;

    // Data fields
    private final String university;
    private final String major;
    private final Time from;
    private final Time to;
    private final double cap;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Github github,
                  String university, String major, Time from, Time to, double cap, Set<Tag> tags) {
        this.type = Type.generate("bio");
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.github = github;
        this.university = university;
        this.major = major;
        this.from = from;
        this.to = to;
        this.cap = cap;
        this.tags.addAll(tags);
    }

    public Type getType() {
        return this.type;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Github getGithub() {
        return github;
    }

    public String getUniversity() {
        return university;
    }

    public String getMajor() {
        return major;
    }

    public Time getFrom() {
        return from;
    }

    public Time getTo() {
        return to;
    }

    public double getCap() {
        return cap;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, github, university, major, from, to, cap, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ").append(getPhone())
                .append(" Email: ").append(getEmail())
                .append(" Github: ").append(getGithub())
                .append(" University: ").append(getUniversity())
                .append(" Major: ").append(getMajor())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
