package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.item.Item;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Item> PREDICATE_SHOW_ALL_ITEMS = unused -> true;
    Predicate<Item> PREDICATE_SHOW_ALL_PERSONAL_DETAILS = unused -> true;
    Predicate<Item> PREDICATE_SHOW_ALL_RESUMES = unused -> true;

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
     * Returns the user prefs' address book file path.
     */
    Path getResumeBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setResumeBookFilePath(Path resumeBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setResumeBook(ReadOnlyResumeBook resumeBook);

    /** Returns the AddressBook */
    ReadOnlyResumeBook getResumeBook();

    //=========== Personal Detail ============================================================================

    /**
     * Returns true if a personal detail with the same identity as {@code pd} exists in the address book.
     */
    boolean hasPersonalDetail(Item pd);

    /**
     * Deletes the given personal detail.
     * The personal detail must exist in the address book.
     */
    void deletePersonalDetail(Item target);

    /**
     * Adds the given personal detail.
     * {@code pd} must not already exist in the address book.
     */
    void addPersonalDetail(Item pd);

    /**
     * Replaces the given personal detail {@code target} with {@code editedPd}.
     * {@code target} must exist in the address book.
     * The identity of {@code editedPd} must not be the same as another existing personal detail in the address book.
     */
    void setPersonalDetail(Item target, Item editedPd);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Item> getFilteredPersonalDetailList();

    /**
     * Updates the filter of the filtered personal detail list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonalDetailList(Predicate<Item> predicate);

    //=========== Resume =====================================================================================

    /**
     * Returns true if a resume with the same identity as {@code res} exists in the address book.
     */
    boolean hasResume(Item res);

    /**
     * Deletes the given resume.
     * The resume must exist in the address book.
     */
    void deleteResume(Item target);

    /**
     * Adds the given resume.
     * {@code res} must not already exist in the address book.
     */
    void addResume(Item res);

    /**
     * Replaces the given resume {@code target} with {@code editedRes}.
     * {@code target} must exist in the address book.
     * The identity of {@code editedRes} must not be the same as another existing resume in the address book.
     */
    void setResume(Item target, Item editedRes);

    /** Returns an unmodifiable view of the filtered resume list */
    ObservableList<Item> getFilteredResumeList();

    /**
     * Updates the filter of the filtered resume list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredResumeList(Predicate<Item> predicate);
}
