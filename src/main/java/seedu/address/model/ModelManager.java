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
import seedu.address.model.item.Person;
import seedu.address.model.item.Project;
import seedu.address.model.item.Resume;
import seedu.address.model.item.Skill;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedResumeBook versionedResumeBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Item> filteredItems;

    /**
     * Initializes a ModelManager with the given resumeBook and userPrefs.
     */
    public ModelManager(ReadOnlyResumeBook resumeBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(resumeBook, userPrefs);

        logger.fine("Initializing with resume book: " + resumeBook + " and user prefs " + userPrefs);

        this.versionedResumeBook = new VersionedResumeBook(resumeBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredItems = new FilteredList<>(this.versionedResumeBook.getItemToDisplayList());
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
        this.versionedResumeBook.resetData(resumeBook);
    }

    @Override
    public ReadOnlyResumeBook getResumeBook() {
        return versionedResumeBook;
    }

    //=========== User ================================================================================

    @Override
    public void setUser(Person person) {
        versionedResumeBook.setUser(person);
    }

    @Override
    public Person getUser() {
        return versionedResumeBook.getUser();
    }

    //=========== Internships ================================================================================

    @Override
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return versionedResumeBook.hasInternship(internship);
    }

    @Override
    public void addInternship(Internship internship) {
        versionedResumeBook.addInternship(internship);
        updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
    }

    @Override
    public void setInternship(Internship target, Internship editedInternship) {
        requireAllNonNull(target, editedInternship);
        versionedResumeBook.setInternship(target, editedInternship);
    }

    @Override
    public void deleteInternship(Internship key) {
        versionedResumeBook.deleteInternship(key);
    }


    @Override
    public Internship getInternship(Index index) {
        return versionedResumeBook.getInternship(index);
    }

    public int getInternshipSize() {
        return versionedResumeBook.getInternshipSize();
    }

    @Override
    public void setInternshipToDisplay() {
        versionedResumeBook.setInternshipToDisplay();
    }

    //=========== Projects ================================================================================

    @Override
    public boolean hasProject(Project project) {
        requireNonNull(project);
        return versionedResumeBook.hasProject(project);
    }

    @Override
    public void addProject(Project project) {
        versionedResumeBook.addProject(project);
        updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
    }

    @Override
    public void setProject(Project target, Project editedProject) {
        requireAllNonNull(target, editedProject);
        versionedResumeBook.setProject(target, editedProject);
    }

    @Override
    public void deleteProject(Project key) {
        versionedResumeBook.deleteProject(key);
    }

    @Override
    public Project getProject(Index index) {
        return versionedResumeBook.getProject(index);
    }

    @Override
    public int getProjectSize() {
        return versionedResumeBook.getProjectSize();
    }

    @Override
    public void setProjectToDisplay() {
        versionedResumeBook.setProjectToDisplay();
    }

    //=========== Skill ================================================================================

    @Override
    public boolean hasSkill(Skill skill) {
        requireNonNull(skill);
        return versionedResumeBook.hasSkill(skill);
    }

    @Override
    public void addSkill(Skill skill) {
        versionedResumeBook.addSkill(skill);
        updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
    }

    @Override
    public void setSkill(Skill target, Skill editedSkill) {
        requireAllNonNull(target, editedSkill);
        versionedResumeBook.setSkill(target, editedSkill);
    }

    @Override
    public void deleteSkill(Skill key) {
        versionedResumeBook.deleteSkill(key);
    }

    @Override
    public Skill getSkill(Index index) {
        return versionedResumeBook.getSkill(index);
    }

    @Override
    public int getSkillSize() {
        return versionedResumeBook.getSkillSize();
    }

    @Override
    public void setSkillToDisplay() {
        versionedResumeBook.setSkillToDisplay();
    }

    //=========== Resume ================================================================================

    @Override
    public boolean hasResume(Resume resume) {
        requireNonNull(resume);
        return versionedResumeBook.hasResume(resume);
    }

    @Override
    public void addResume(Resume resume) {
        versionedResumeBook.addResume(resume);
        updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
    }

    @Override
    public void setResume(Resume target, Resume editedResume) {
        requireAllNonNull(target, editedResume);

        versionedResumeBook.setResume(target, editedResume);
    }

    @Override
    public Resume getResumeByIndex(int index) {
        return versionedResumeBook.getResumeByIndex(index);
    }

    @Override
    public boolean hasResumeId(int resumeIndex) {
        return versionedResumeBook.hasResumeId(resumeIndex);
    }

    @Override
    public void deleteResume(Resume target) {
        versionedResumeBook.deleteResume(target);
    }

    @Override
    public Resume getResume(Index index) {
        return versionedResumeBook.getResume(index);
    }

    @Override
    public int getResumeSize() {
        return versionedResumeBook.getResumeSize();
    }

    @Override
    public void setResumeToDisplay() {
        versionedResumeBook.setResumeToDisplay();
    }

    //=========== Filtered Item List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Item} backed by the internal list of
     * {@code versionedResumeBook}
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
        return versionedResumeBook.equals(other.versionedResumeBook)
                && userPrefs.equals(other.userPrefs)
                && filteredItems.equals(other.filteredItems);
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoResumeBook() {
        return versionedResumeBook.canUndo();
    }

    @Override
    public boolean canRedoResumeBook() {
        return versionedResumeBook.canRedo();
    }

    @Override
    public void undoResumeBook() {
        versionedResumeBook.undo();
    }

    @Override
    public void redoResumeBook() {
        versionedResumeBook.redo();
    }

    @Override
    public void commitResumeBook() {
        versionedResumeBook.commit();
    }


    //// STUBS
    public boolean hasItem(Item item) {
        return false;
    }

    public void addItem(Item item) {}

    public void deleteItem(Item item) {}

    public void setItem(Item target, Item edit) {}

    public void setItemsToDisplay(String type) {}

    @Override
    public boolean hasInternshipId(int i) {
        return versionedResumeBook.hasInternshipId(i);
    }

    @Override
    public boolean hasProjectId(int i) {
        return versionedResumeBook.hasProjectId(i);
    }

    @Override
    public boolean hasSkillId(int i) {
        return versionedResumeBook.hasSkillId(i);
    }

}
