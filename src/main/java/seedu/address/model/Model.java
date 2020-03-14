package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Item;
import seedu.address.model.item.Resume;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Item> PREDICATE_SHOW_ALL_ITEMS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' resume book file path.
     */
    Path getResumeBookFilePath();

    /**
     * Sets the user prefs' resume book file path.
     */
    void setResumeBookFilePath(Path addressBookFilePath);

    /**
     * Replaces resume book data with the data in {@code resumeBook}.
     */
    void setResumeBook(ReadOnlyResumeBook resumeBook);

    /** Returns the AddressBook */
    ReadOnlyResumeBook getResumeBook();

    //=========== Internships ================================================================================

    /**
     * Returns true if an internship with the same identity as {@code internship} exists in the resume book.
     */
    boolean hasInternship(Internship internship);

    /**
     * Deletes the given internship.
     * The internship must exist in the resume book.
     */
    void deleteInternship(Internship internship);

    /**
     * Adds the given internship.
     * {@code internship} must not already exist in the resume book.
     */
    void addInternship(Internship internship);

    /**
     * Replaces the given internship {@code target} with {@code editedInternship}.
     * {@code target} must exist in the resume book.
     * The identity of {@code editedInternship} must not be the same as another existing internship in the resume book.
     */
    void setInternship(Internship target, Internship editedInternship);

    /**
     * Updates the List as the internship list.
     */
    void setInternshipToDisplay();

    //=========== Resumes ================================================================================

    /**
     * Returns true if a resume with the same identity as {@code resume} exists in the resume book.
     */
    boolean hasResume(Resume resume);

    /**
     * Deletes the given resume.
     * The resume must exist in the resume book.
     */
    void deleteResume(Resume resume);

    /**
     * Adds the given resume.
     * {@code resume} must not already exist in the resume book.
     */
    void addResume(Resume resume);

    /**
     * Replaces the given resume {@code target} with {@code editedResume}.
     * {@code target} must exist in the resume book.
     * The identity of {@code editedResume} must not be the same as another existing resume in the resume book.
     */
    void setResume(Resume target, Resume editedResume);

    /**
     * Updates the List as the resume list.
     */
    void setResumeToDisplay();

    //=========== Item Lists ================================================================================

    /** Returns an unmodifiable view of the filtered item list */
    ObservableList<Item> getFilteredItemList();

    /**
     * Updates the filter of the filtered item list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredItemList(Predicate<Item> predicate);

    //// STUBS
    public boolean hasItem(Item item);

    public void addItem(Item item);

    public void deleteItem(Item item);

    public void setItem(Item target, Item edit);

    public void setItemsToDisplay(String type);
}
