package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.item.Person;
import seedu.address.model.item.field.Email;
import seedu.address.model.item.field.Github;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Phone;
import seedu.address.model.item.field.Time;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Github github;
    private final String university;
    private final String major;
    private final Time from;
    private final Time to;
    private final double cap;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("github") String github,
                             @JsonProperty("university") String university, @JsonProperty("major") String major,
                             @JsonProperty("from") String from, @JsonProperty("to") String to,
                             @JsonProperty("cap") String cap) {
        this.name = new Name(name);
        this.phone = new Phone(phone);
        this.email = new Email(email);
        this.github = new Github(github);
        this.university = university;
        this.major = major;
        this.from = new Time(from);
        this.to = new Time(to);
        this.cap = Double.valueOf(cap);
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName();
        phone = source.getPhone();
        email = source.getEmail();
        github = source.getGithub();
        university = source.getUniversity();
        major = source.getMajor();
        from = source.getFrom();
        to = source.getTo();
        cap = source.getCap();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     */
    public Person toModelType() {
        return new Person(name, phone, email, github, university, major, from, to, cap);
    }
}
