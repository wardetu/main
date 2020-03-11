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
import seedu.address.model.item.Item;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ResumeBook resumeBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Item> filteredPersonalDetails;
    private final FilteredList<Item> filteredResumes;

    /**
     * Initializes a ModelManager with the given resumeBook and userPrefs.
     */
    public ModelManager(ReadOnlyResumeBook resumeBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(resumeBook, userPrefs);

        logger.fine("Initializing with resume book: " + resumeBook + " and user prefs " + userPrefs);

        this.resumeBook = new ResumeBook(resumeBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersonalDetails = new FilteredList<>(this.resumeBook.getPersonalDetailList());
        filteredResumes = new FilteredList<>(this.resumeBook.getResumeList());
        // do the same for edu, achv, proj, int, ski
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

    //=========== ResumeBook ==================================================================================

    @Override
    public void setResumeBook(ReadOnlyResumeBook resumeBook) {
        this.resumeBook.resetData(resumeBook);
    }

    @Override
    public ReadOnlyResumeBook getResumeBook() {
        return resumeBook;
    }

    //=========== Personal Detail ============================================================================

    @Override
    public boolean hasPersonalDetail(Item pd) {
        requireNonNull(pd);
        return resumeBook.hasPersonalDetail(pd);
    }

    @Override
    public void deletePersonalDetail(Item target) {
        resumeBook.removePersonalDetail(target);
    }

    @Override
    public void addPersonalDetail(Item pd) {
        resumeBook.addPersonalDetail(pd);
        updateFilteredPersonalDetailList(PREDICATE_SHOW_ALL_PERSONAL_DETAILS);
    }

    @Override
    public void setPersonalDetail(Item target, Item editedPd) {
        requireAllNonNull(target, editedPd);
        resumeBook.setPersonalDetail(target, editedPd);
    }

    //=========== Filtered Personal Detail List Accessors ===================================================

    /**
     * Returns an unmodifiable view of the list of {@code PersonalDetail} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Item> getFilteredPersonalDetailList() {
        return filteredPersonalDetails;
    }

    @Override
    public void updateFilteredPersonalDetailList(Predicate<Item> predicate) {
        requireNonNull(predicate);
        filteredPersonalDetails.setPredicate(predicate);
    }

    //=========== Resume =====================================================================================

    @Override
    public boolean hasResume(Item res) {
        requireNonNull(res);
        return resumeBook.hasResume(res);
    }

    @Override
    public void deleteResume(Item target) {
        resumeBook.removeResume(target);
    }

    @Override
    public void addResume(Item res) {
        resumeBook.addResume(res);
        updateFilteredResumeList(PREDICATE_SHOW_ALL_RESUMES);
    }

    @Override
    public void setResume(Item target, Item editedRes) {
        requireAllNonNull(target, editedRes);
        resumeBook.setResume(target, editedRes);
    }

    //=========== Filtered Resume List Accessors ==============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Resume} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Item> getFilteredResumeList() {
        return filteredResumes;
    }

    @Override
    public void updateFilteredResumeList(Predicate<Item> predicate) {
        requireNonNull(predicate);
        filteredResumes.setPredicate(predicate);
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
                && filteredPersonalDetails.equals(other.filteredPersonalDetails)
                && filteredResumes.equals(other.filteredResumes); // to add filtered edu, achv, proj, int, ski
    }

}
