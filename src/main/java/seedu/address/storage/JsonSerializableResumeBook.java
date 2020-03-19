package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyResumeBook;
import seedu.address.model.ResumeBook;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Person;
import seedu.address.model.item.Resume;
import seedu.address.model.util.ItemUtil;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "resumebook")
class JsonSerializableResumeBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final JsonAdaptedPerson user;
    private final List<JsonAdaptedResume> resumes = new ArrayList<>();
    private final List<JsonAdaptedInternship> internships = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableResumeBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableResumeBook(@JsonProperty("user") JsonAdaptedPerson user,
                                      @JsonProperty("resumes") List<JsonAdaptedResume> resumes,
                                      @JsonProperty("internships") List<JsonAdaptedInternship> internships) {
        this.user = user;
        this.resumes.addAll(resumes);
        this.internships.addAll(internships);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableResumeBook}.
     */
    public JsonSerializableResumeBook(ReadOnlyResumeBook source) {
        user = new JsonAdaptedPerson(source.getUser());
        resumes.addAll(source
                .getResumeList()
                .asUnmodifiableObservableList()
                .stream()
                .map(x -> (Resume) x)
                .map(JsonAdaptedResume::new)
                .collect(Collectors.toList()));
        internships.addAll(source
                .getInternshipList()
                .asUnmodifiableObservableList()
                .stream()
                .map(x -> (Internship) x)
                .map(JsonAdaptedInternship::new)
                .collect(Collectors.toList()));

    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ResumeBook toModelType() throws IllegalValueException {
        ResumeBook resumeBook = new ResumeBook();
        Person person = user.toModelType();
        resumeBook.setUser(person);

        int maxIdValue = -1;
        for (JsonAdaptedResume jsonAdaptedResume : resumes) {
            Resume resume = jsonAdaptedResume.toModelType();
            if (resumeBook.hasItem(resume)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            resumeBook.addResume(resume);
            maxIdValue = Math.max(maxIdValue, resume.getId());
        }
        ItemUtil.setBaseIdOfItemType("res", maxIdValue + 1);

        maxIdValue = -1;
        for (JsonAdaptedInternship jsonAdaptedInternship : internships) {
            Internship internship = jsonAdaptedInternship.toModelType();
            if (resumeBook.hasItem(internship)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            resumeBook.addInternship(internship);
            maxIdValue = Math.max(maxIdValue, internship.getId());
        }
        ItemUtil.setBaseIdOfItemType("int", maxIdValue + 1);

        return resumeBook;
    }

}
