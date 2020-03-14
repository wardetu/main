package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Item;
import seedu.address.model.item.Resume;
import seedu.address.model.item.UniqueItemList;

/**
 * Unmodifiable view of a resume book
 */
public interface ReadOnlyResumeBook {

    /**
     * Returns an unmodifiable view of the current items list.
     * This list will not contain any duplicate items.
     */
    ObservableList<Item> getItemToDisplayList();

    /**
     * Returns an unmodifiable view of the current internship list.
     * This list will not contain any duplicate internship.
     */
    UniqueItemList getInternshipList();

    /**
     * Returns an unmodifiable view of the current resume list.
     * This list will not contain any duplicate resume.
     */
    UniqueItemList getResumeList();

    ///STUB
    UniqueItemList getPersonalDetailList();

    Resume getResume(Index index);

    Internship getInternship(Index index);
}
