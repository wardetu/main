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
import seedu.address.model.item.Item;
import seedu.address.model.item.PersonalDetail;
import seedu.address.model.item.Project;
import seedu.address.model.item.Resume;
import seedu.address.model.item.Skill;
import seedu.address.model.util.ItemUtil;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "resumebook")
class JsonSerializableResumeBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPersonalDetail> persons = new ArrayList<>();
    private final List<JsonAdaptedResume> resumes = new ArrayList<>();
    private final List<JsonAdaptedInternship> internships = new ArrayList<>();
    private final List<JsonAdaptedSkill> skills = new ArrayList<>();
    private final List<JsonAdaptedProject> projects = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableResumeBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableResumeBook(@JsonProperty("persons") List<JsonAdaptedPersonalDetail> persons,
                                      @JsonProperty("resumes") List<JsonAdaptedResume> resumes,
                                      @JsonProperty("internships") List<JsonAdaptedInternship> internships,
                                      @JsonProperty("skills") List<JsonAdaptedSkill> skills,
                                      @JsonProperty("projects") List<JsonAdaptedProject> projects) {
        this.persons.addAll(persons);
        this.resumes.addAll(resumes);
        this.internships.addAll(internships);
        this.skills.addAll(skills);
        this.projects.addAll(projects);
    }



    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableResumeBook}.
     */
    public JsonSerializableResumeBook(ReadOnlyResumeBook source) {
        persons.addAll(source
                .getPersonalDetailList()
                .asUnmodifiableObservableList()
                .stream()
                .map(x -> (PersonalDetail) x)
                .map(JsonAdaptedPersonalDetail::new)
                .collect(Collectors.toList()));
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
        skills.addAll(source
                .getSkillList()
                .asUnmodifiableObservableList()
                .stream()
                .map(x -> (Skill) x)
                .map(JsonAdaptedSkill::new)
                .collect(Collectors.toList()));
        projects.addAll(source
                .getProjectList()
                .asUnmodifiableObservableList()
                .stream()
                .map(x -> (Project) x)
                .map(JsonAdaptedProject::new)
                .collect(Collectors.toList()));

    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ResumeBook toModelType() throws IllegalValueException {
        ResumeBook addressBook = new ResumeBook();


        // TODO: remove the PersonalDetail part
        for (JsonAdaptedPersonalDetail jsonAdaptedPerson : persons) {
            Item person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasItem(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addItem(person);
        }

        int maxIdValue = -1;
        for (JsonAdaptedResume jsonAdaptedResume : resumes) {
            Resume resume = jsonAdaptedResume.toModelType();
            if (addressBook.hasItem(resume)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addResume(resume);
            maxIdValue = Math.max(maxIdValue, resume.getId());
        }
        ItemUtil.setBaseIdOfItemType("res", maxIdValue + 1);

        maxIdValue = -1;
        for (JsonAdaptedInternship jsonAdaptedInternship : internships) {
            Internship internship = jsonAdaptedInternship.toModelType();
            if (addressBook.hasItem(internship)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addInternship(internship);
            maxIdValue = Math.max(maxIdValue, internship.getId());
        }
        ItemUtil.setBaseIdOfItemType("int", maxIdValue + 1);

        maxIdValue = -1;
        for (JsonAdaptedProject jsonAdaptedProject : projects) {
            Project project = jsonAdaptedProject.toModelType();
            if (addressBook.hasItem(project)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addProject(project);
            maxIdValue = Math.max(maxIdValue, project.getId());
        }
        ItemUtil.setBaseIdOfItemType("proj", maxIdValue + 1);

        maxIdValue = -1;
        for (JsonAdaptedSkill jsonAdaptedSkill : skills) {
            Skill skill = jsonAdaptedSkill.toModelType();
            if (addressBook.hasItem(skill)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addSkill(skill);
            maxIdValue = Math.max(maxIdValue, skill.getId());
        }
        ItemUtil.setBaseIdOfItemType("ski", maxIdValue + 1);

        return addressBook;
    }

}
