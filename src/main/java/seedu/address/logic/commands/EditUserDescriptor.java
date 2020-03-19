package seedu.address.logic.commands;

import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.item.field.Email;
import seedu.address.model.item.field.Github;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Phone;
import seedu.address.model.item.field.Time;
import seedu.address.model.tag.Tag;

public class EditUserDescriptor {
    private Name name;
    private Phone phone;
    private Email email;
    private Github github;
    private String university;
    private String major;
    private Time from;
    private Time to;
    private double cap;
    private Set<Tag> tags;

    public EditUserDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditUserDescriptor(EditUserDescriptor toCopy) {
        setName(toCopy.name);
        setPhone(toCopy.phone);
        setEmail(toCopy.email);
        setGithub(toCopy.github);
        setUni(toCopy.university);
        setMajor(toCopy.major);
        setFrom(toCopy.from);
        setTo(toCopy.to);
        setCap(toCopy.cap);
        setTag(toCopy.tags);
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Optional<Phone> getPhone() {
        return Optional.ofNullable(phone);
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Optional<Email> getEmail() {
        return Optional.ofNullable(email);
    }

    public void setGithub(Github github) {
        this.github = github;
    }

    public Optional<Github> getGithub() {
        return Optional.ofNullable(github);
    }

    public void setUni(String uni) {
        this.university = uni;
    }

    public Optional<String> getUniversity() {
        return Optional.ofNullable(university);
    }


    public void setMajor(String major) {
        this.major = major;
    }

    public Optional<String> getMajor() {
        return Optional.ofNullable(major);
    }

    public void setFrom(Time from) {
        this.from = from;
    }

    public Optional<Time> getFrom() {
        return Optional.ofNullable(from);
    }

    public void setTo(Time to) {
        this.to = to;
    }

    public Optional<Time> getTo() {
        return Optional.ofNullable(to);
    }

    public void setCap(double cap) {
        this.cap = cap;
    }

    public Optional<Double> getCap() {
        return Optional.ofNullable(cap);
    }

    public void setTag(Set<Tag> tags) {
        this.tags = tags;
    }

    public Optional<Set<Tag>> getTagSet() {
        return Optional.ofNullable(tags);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, phone, email, github, university,
                major, from, to, cap);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditUserDescriptor)) {
            return false;
        }

        // state check
        EditUserDescriptor e = (EditUserDescriptor) other;

        return getName().equals(e.getName())
                && getPhone().equals(e.getPhone())
                && getEmail().equals(e.getEmail())
                && getGithub().equals(e.getGithub())
                && getUniversity().equals(e.getUniversity())
                && getMajor().equals(e.getMajor())
                && getFrom().equals(e.getFrom())
                && getTo().equals(e.getTo())
                && getCap().equals(e.getCap());
    }
}
