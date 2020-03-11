package seedu.address.model.item;

import seedu.address.model.item.field.Address;
import seedu.address.model.item.field.Email;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Phone;
import seedu.address.model.tag.Tag;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class PersonalDetail extends Item {

    private final Phone phone;
    private final Email email;
    private final Address address;

    public PersonalDetail(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags); // phone, email, address to be removed from super() after integration
        this.type = "pd";
        requireAllNonNull(phone, email, address);
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
