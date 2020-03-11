package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.item.Item;
import seedu.address.model.item.UniqueItemList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class ResumeBook implements ReadOnlyResumeBook {

    private final UniqueItemList personalDetails;
    private final UniqueItemList educations;
    private final UniqueItemList achievements;
    private final UniqueItemList projects;
    private final UniqueItemList internships;
    private final UniqueItemList skills;
    private final UniqueItemList resumes;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        personalDetails = new UniqueItemList();
        educations = new UniqueItemList();
        achievements = new UniqueItemList();
        projects = new UniqueItemList();
        internships = new UniqueItemList();
        skills = new UniqueItemList();
        resumes = new UniqueItemList();
    }

    public ResumeBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public ResumeBook(ReadOnlyResumeBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the personal detail list with {@code personalDetails}.
     * {@code personalDetails} must not contain duplicate personal details.
     */
    public void setPersonalDetails(List<Item> personalDetails) {
        this.personalDetails.setItems(personalDetails);
    }

    /**
     * Replaces the contents of the education list with {@code educations}.
     * {@code educations} must not contain duplicate educations.
     */
    public void setEducations(List<Item> educations) {
        this.educations.setItems(educations);
    }

    /**
     * Replaces the contents of the achievement list with {@code achievements}.
     * {@code achievements} must not contain duplicate achievements.
     */
    public void setAchievements(List<Item> achievements) {
        this.achievements.setItems(achievements);
    }

    /**
     * Replaces the contents of the project list with {@code projects}.
     * {@code projects} must not contain duplicate projects.
     */
    public void setProjects(List<Item> projects) {
        this.projects.setItems(projects);
    }

    /**
     * Replaces the contents of the internship list with {@code internships}.
     * {@code internships} must not contain duplicate internships.
     */
    public void setInternships(List<Item> internships) {
        this.internships.setItems(internships);
    }

    /**
     * Replaces the contents of the skill list with {@code skills}.
     * {@code skills} must not contain duplicate skills.
     */
    public void setSkills(List<Item> skills) {
        this.skills.setItems(skills);
    }

    /**
     * Replaces the contents of the resume list with {@code resumes}.
     * {@code resumes} must not contain duplicate resumes.
     */
    public void setResumes(List<Item> resumes) {
        this.resumes.setItems(resumes);
    }

    /**
     * Resets the existing data of this {@code ResumeBook} with {@code newData}.
     */
    public void resetData(ReadOnlyResumeBook newData) {
        requireNonNull(newData);
        setPersonalDetails(newData.getPersonalDetailList());
        setEducations(newData.getEducationList());
        setAchievements(newData.getAchievementList());
        setProjects(newData.getProjectList());
        setInternships(newData.getProjectList());
        setSkills(newData.getSkillList());
        setResumes(newData.getResumeList());
    }

    //// item-level operations

    /// personal details

    /**
     * Returns true if a Personal Detail with the same identity as {@code pd} exists in the resume book.
     */
    public boolean hasPersonalDetail(Item pd) {
        requireNonNull(pd);
        return personalDetails.contains(pd);
    }

    /**
     * Adds a PersonalDetail to the resume book.
     * The personal detail must not already exist in the resume book.
     */
    public void addPersonalDetail(Item pd) {
        personalDetails.add(pd);
    }

    /**
     * Replaces the given personal detail {@code target} in the personalDetails list with {@code editedPd}.
     * {@code target} must exist in the resume book.
     * The identity of {@code editedPd} must not be the same as another existing person detail in the resume book.
     */
    public void setPersonalDetail(Item target, Item editedPd) {
        requireNonNull(editedPd);
        personalDetails.setItem(target, editedPd);
    }

    /**
     * Removes {@code key} from this {@code ResumeBook}.
     * {@code key} must exist in the resume book.
     */
    public void removePersonalDetail(Item key) {
        personalDetails.remove(key);
    }

    /// educations

    /**
     * Returns true if an Education with the same identity as {@code edu} exists in the resume book.
     */
    public boolean hasEducation(Item edu) {
        requireNonNull(edu);
        return educations.contains(edu);
    }

    /**
     * Adds an Education to the resume book.
     * The education must not already exist in the resume book.
     */
    public void addEducation(Item edu) {
        educations.add(edu);
    }

    /**
     * Replaces the given education {@code target} in the educations list with {@code editedEdu}.
     * {@code target} must exist in the resume book.
     * The identity of {@code editedEdu} must not be the same as another existing education in the resume book.
     */
    public void setEducation(Item target, Item editedEdu) {
        requireNonNull(editedEdu);
        educations.setItem(target, editedEdu);
    }

    /**
     * Removes {@code key} from this {@code ResumeBook}.
     * {@code key} must exist in the resume book.
     */
    public void removeEducation(Item key) {
        educations.remove(key);
    }

    /// similarly, implement for achievements, projects, internship, skill

    /// resumes

    /**
     * Returns true if a Resume with the same identity as {@code res} exists in the resume book.
     */
    public boolean hasResume(Item res) {
        requireNonNull(res);
        return resumes.contains(res);
    }

    /**
     * Adds a Resume to the resume book.
     * The resume must not already exist in the resume book.
     */
    public void addResume(Item res) {
        resumes.add(res);
    }

    /**
     * Replaces the given resume {@code target} in the resumes list with {@code editedRes}.
     * {@code target} must exist in the resume book.
     * The identity of {@code editedRes} must not be the same as another existing resume in the resume book.
     */
    public void setResume(Item target, Item editedRes) {
        requireNonNull(editedRes);
        resumes.setItem(target, editedRes);
    }

    /**
     * Removes {@code key} from this {@code ResumeBook}.
     * {@code key} must exist in the resume book.
     */
    public void removeResume(Item key) {
        resumes.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return personalDetails.asUnmodifiableObservableList().size() + " personal details";
        // TODO: refine later
    }

    @Override
    public ObservableList<Item> getPersonalDetailList() {
        return personalDetails.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Item> getEducationList() {
        return educations.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Item> getAchievementList() {
        return achievements.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Item> getProjectList() {
        return projects.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Item> getInternshipList() {
        return internships.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Item> getSkillList() {
        return skills.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Item> getResumeList() {
        return resumes.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ResumeBook // instanceof handles nulls
                && personalDetails.equals(((ResumeBook) other).personalDetails)
                && educations.equals(((ResumeBook) other).educations)
                && achievements.equals(((ResumeBook) other).achievements)
                && projects.equals(((ResumeBook) other).projects)
                && internships.equals(((ResumeBook) other).internships)
                && skills.equals(((ResumeBook) other).skills)
                && resumes.equals(((ResumeBook) other).resumes));
    }

    @Override
    public int hashCode() {
        return personalDetails.hashCode();
    }
}
