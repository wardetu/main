package seedu.address.logic.commands.edit;

import java.util.Optional;
import seedu.address.commons.util.CollectionUtil;

/**
 * Stores the details to edit the person with. Each non-empty field value will replace the
 * corresponding field value of the person.
 */
public class EditInternshipDescriptor extends EditItemDescriptor {
    private String role;
    private String from;
    private String to;
    private String description;

    public EditInternshipDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditInternshipDescriptor(EditInternshipDescriptor toCopy) {
        setName(toCopy.name);
        setRole(toCopy.role);
        setFrom(toCopy.from);
        setTo(toCopy.to);
        setDescription(toCopy.description);

    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Optional<String> getFrom() {
        return Optional.ofNullable(from);
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Optional<String> getTo() {
        return Optional.ofNullable(to);
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Optional<String> getRole() {
        return Optional.ofNullable(role);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    /**
     * Returns true if at least one field is edited.
     */
    @Override
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, role, from, to, description);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditInternshipDescriptor)) {
            return false;
        }

        // state check
        EditInternshipDescriptor e = (EditInternshipDescriptor) other;

        return getName().equals(e.getName())
                && getRole().equals(e.getRole())
                && getFrom().equals(e.getFrom())
                && getTo().equals(e.getTo())
                && getDescription().equals(e.getDescription());

    }
}
