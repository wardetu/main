package seedu.address.model;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Item;
import seedu.address.model.item.Resume;
import seedu.address.model.item.UniqueItemList;

/**
 * Wraps all data at the resume-book level
 * Duplicates are not allowed (by .isSame comparison)
 */
public class ResumeBook implements ReadOnlyResumeBook {

    // Should be all caps but check style complain
    private final UniqueItemList itemsToDisplay;
    private final UniqueItemList internships;
    private final UniqueItemList resumes;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        itemsToDisplay = new UniqueItemList();
        internships = new UniqueItemList();
        resumes = new UniqueItemList();
    }

    public ResumeBook() {}

    /**
     * Creates an ResumeBook using the Items in the {@code toBeCopied}
     */
    public ResumeBook(ReadOnlyResumeBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the item list with {@code items}.
     * {@code items} must not contain duplicate items.
     */
    public void setItemsToDisplay(UniqueItemList itemsToDisplay) {
        this.itemsToDisplay.setItems(itemsToDisplay);
    }

    // STUB
    public void setItemsToDisplay(String type) {};

    /**
     * Replaces the contents of the item list to the content of the internship list.
     */
    public void setInternshipToDisplay() {
        setItemsToDisplay(internships);
    }

    /**
     * Replaces the contents of the item list to the content of the resume list.
     */
    public void setResumeToDisplay() {
        setItemsToDisplay(resumes);
    }

    /**
     * Replaces the contents of the internship list with {@code internships}.
     * {@code internship} must not contain duplicate items.
     */
    public void setInternships(UniqueItemList internships) {
        this.internships.setItems(internships);
    }

    /**
     * Replaces the contents of the resume list with {@code resumes}.
     * {@code resumes} must not contain duplicate items.
     */
    public void setResumes(UniqueItemList resumes) {
        this.resumes.setItems(resumes);
    }

    /**
     * Resets the existing data of this {@code ResumeBook} with {@code newData}.
     */
    public void resetData(ReadOnlyResumeBook newData) {
        requireNonNull(newData);
        setInternships(newData.getInternshipList());
        setResumes(newData.getResumeList());
    }

    //// item-level operations

    //=========== Internships ================================================================================

    /**
     * Returns true if an internship with the same identity as {@code internship} exists in the resume book.
     */
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return internships.contains(internship);
    }

    /**
     * Adds an internship to the resume book.
     * The internship must not already exist in the resume book.
     */
    public void addInternship(Internship internship) {
        requireNonNull(internship);
        internships.add(internship);
    }

    /**
     * Replaces the given internship {@code target} in the list with {@code editedInternship}.
     * {@code target} must exist in the resume book.
     * The identity of {@code editedInternship} must not be the same as another existing internship in the resume book.
     */
    public void setInternship(Internship target, Internship editedInternship) {
        internships.setItem(target, editedInternship);
    }

    /**
     * Removes {@code key} from this {@code ResumeBook}.
     * {@code key} must exist in the resume book.
     */
    public void removeInternship(Internship key) {
        internships.remove(key);
    }

    //=========== Resumes ================================================================================

    /**
     * Returns true if a resume with the same identity as {@code resume} exists in the resume book.
     */
    public boolean hasResume(Resume resume) {
        requireNonNull(resume);
        return resumes.contains(resume);
    }

    /**
     * Adds a resume to the resume book.
     * The resume must not already exist in the resume book.
     */
    public void addResume(Resume resume) {
        requireNonNull(resume);
        resumes.add(resume);
    }

    /**
     * Replaces the given resume {@code target} in the list with {@code editedResume}.
     * {@code target} must exist in the resume book.
     * The identity of {@code editedResume} must not be the same as another existing resume in the resume book.
     */
    public void setResume(Resume target, Resume editedResume) {
        resumes.setItem(target, editedResume);
    }

    /**
     * Removes {@code key} from this {@code ResumeBook}.
     * {@code key} must exist in the resume book.
     */
    public void removeResume(Resume key) {
        resumes.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return itemsToDisplay.asUnmodifiableObservableList().size() + " items";
        // TODO: refine later
    }

    @Override
    public ObservableList<Item> getItemToDisplayList() {
        return itemsToDisplay.asUnmodifiableObservableList();
    }

    @Override
    public UniqueItemList getInternshipList() {
        return internships;
    }

    @Override
    public UniqueItemList getResumeList() {
        return resumes;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ResumeBook // instanceof handles nulls
                && itemsToDisplay.equals(((ResumeBook) other).itemsToDisplay))
                && internships.equals(((ResumeBook) other).internships)
                && resumes.equals(((ResumeBook) other).resumes);
    }

    @Override
    public int hashCode() {
        return itemsToDisplay.hashCode();
    }

    //STUBS
    public UniqueItemList getPersonalDetailList() {
        return new UniqueItemList();
    }
    public boolean hasItem(Item item) {
        return false;
    }
    public void addItem(Item item) {}

    public void deleteItem(Item item) {}

    public void setItem(Item target, Item edit) {}
}
