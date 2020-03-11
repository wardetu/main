package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.item.Item;
import seedu.address.model.item.PersonalDetail;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyResumeBook {

    /**
     * Returns an unmodifiable view of the personal detail list.
     * This list will not contain any duplicate personal details.
     */
    ObservableList<Item> getPersonalDetailList();

    /**
     * Returns an unmodifiable view of the education list.
     * This list will not contain any duplicate educations.
     */
    ObservableList<Item> getEducationList();

    /**
     * Returns an unmodifiable view of the achievement list.
     * This list will not contain any duplicate achievements.
     */
    ObservableList<Item> getAchievementList();

    /**
     * Returns an unmodifiable view of the project list.
     * This list will not contain any duplicate projects.
     */
    ObservableList<Item> getProjectList();

    /**
     * Returns an unmodifiable view of the internship list.
     * This list will not contain any duplicate internships.
     */
    ObservableList<Item> getInternshipList();

    /**
     * Returns an unmodifiable view of the skill list.
     * This list will not contain any duplicate skills.
     */
    ObservableList<Item> getSkillList();

    /**
     * Returns an unmodifiable view of the resume list.
     * This list will not contain any duplicate resumes.
     */
    ObservableList<Item> getResumeList();

}
