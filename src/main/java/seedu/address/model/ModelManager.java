package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Item;
import seedu.address.model.item.Resume;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ResumeBook resumeBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Item> filteredItems;

    /**
     * Initializes a ModelManager with the given resumeBook and userPrefs.
     */
    public ModelManager(ReadOnlyResumeBook resumeBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(resumeBook, userPrefs);

        logger.fine("Initializing with resume book: " + resumeBook + " and user prefs " + userPrefs);

        this.resumeBook = new ResumeBook(resumeBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredItems = new FilteredList<>(this.resumeBook.getItemToDisplayList());
    }

    public ModelManager() {
        this(new ResumeBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getResumeBookFilePath() {
        return userPrefs.getResumeBookFilePath();
    }

    @Override
    public void setResumeBookFilePath(Path resumeBookFilePath) {
        requireNonNull(resumeBookFilePath);
        userPrefs.setResumeBookFilePath(resumeBookFilePath);
    }

    //=========== ResumeBook ================================================================================

    @Override
    public void setResumeBook(ReadOnlyResumeBook resumeBook) {
        this.resumeBook.resetData(resumeBook);
    }

    @Override
    public ReadOnlyResumeBook getResumeBook() {
        return resumeBook;
    }

    //=========== Internships ================================================================================

    @Override
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return resumeBook.hasInternship(internship);
    }

    @Override
    public void deleteInternship(Internship target) {
        resumeBook.removeInternship(target);
    }

    @Override
    public void addInternship(Internship internship) {
        resumeBook.addInternship(internship);
        updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
    }

    @Override
    public void setInternship(Internship target, Internship editedInternship) {
        requireAllNonNull(target, editedInternship);

        resumeBook.setInternship(target, editedInternship);
    }

    @Override
    public void setInternshipToDisplay() {
        resumeBook.setInternshipToDisplay();
    }

    @Override
    public Internship getInternship(Index index) {
        return resumeBook.getInternship(index);
    }

    public int getInternshipSize() {
        return resumeBook.getInternshipSize();
    }

    //=========== Resume ================================================================================

    @Override
    public boolean hasResume(Resume resume) {
        requireNonNull(resume);
        return resumeBook.hasResume(resume);
    }

    @Override
    public void deleteResume(Resume target) {
        resumeBook.removeResume(target);
    }

    @Override
    public void addResume(Resume resume) {
        resumeBook.addResume(resume);
        updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
    }

    @Override
    public void setResume(Resume target, Resume editedResume) {
        requireAllNonNull(target, editedResume);

        resumeBook.setResume(target, editedResume);
    }

    @Override
    public void setResumeToDisplay() {
        resumeBook.setResumeToDisplay();
    }

    @Override
    public Resume getResume(Index index) {
        return resumeBook.getResume(index);
    }

    @Override
    public int getResumeSize() {
        return resumeBook.getResumeSize();
    }

    //=========== Filtered Item List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Item} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Item> getFilteredItemList() {
        return filteredItems;
    }

    @Override
    public void updateFilteredItemList(Predicate<Item> predicate) {
        requireNonNull(predicate);
        filteredItems.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return resumeBook.equals(other.resumeBook)
                && userPrefs.equals(other.userPrefs)
                && filteredItems.equals(other.filteredItems);
    }


    //// STUBS
    public boolean hasItem(Item item) {
        return false;
    }

    public void addItem(Item item) {}

    public void deleteItem(Item item) {}

    public void setItem(Item target, Item edit) {}

    public void setItemsToDisplay(String type) {}

}
