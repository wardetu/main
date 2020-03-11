package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersonalDetails.ALICE;
import static seedu.address.testutil.TypicalPersonalDetails.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.item.Item;
import seedu.address.model.item.exceptions.DuplicateItemException;
import seedu.address.testutil.PersonalDetailBuilder;

public class ResumeBookTest {

    private final ResumeBook resumeBook = new ResumeBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), resumeBook.getPersonalDetailList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> resumeBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyResumeBook_replacesData() {
        ResumeBook newData = getTypicalAddressBook();
        resumeBook.resetData(newData);
        assertEquals(newData, resumeBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Item editedAlice = new PersonalDetailBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Item> newItems = Arrays.asList(ALICE, editedAlice);
        ResumeBookStub newData = new ResumeBookStub(newItems);

        assertThrows(DuplicateItemException.class, () -> resumeBook.resetData(newData));
    }

    @Test
    public void hasPersonalDetail_nullPersonalDetail_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> resumeBook.hasPersonalDetail(null));
    }

    @Test
    public void hasPersonalDetail_personalDetailNotInAddressBook_returnsFalse() {
        assertFalse(resumeBook.hasPersonalDetail(ALICE));
    }

    @Test
    public void hasPersonalDetail_personalDetailInAddressBook_returnsTrue() {
        resumeBook.addPersonalDetail(ALICE);
        assertTrue(resumeBook.hasPersonalDetail(ALICE));
    }

    @Test
    public void hasPersonalDetail_personalDetailWithSameIdentityFieldsInAddressBook_returnsTrue() {
        resumeBook.addPersonalDetail(ALICE);
        Item editedAlice = new PersonalDetailBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(resumeBook.hasPersonalDetail(editedAlice));
    }

    @Test
    public void getPersonalDetailList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> resumeBook.getPersonalDetailList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class ResumeBookStub implements ReadOnlyResumeBook {
        private final ObservableList<Item> personalDetails = FXCollections.observableArrayList();
        private final ObservableList<Item> educations = FXCollections.observableArrayList();
        private final ObservableList<Item> achievements = FXCollections.observableArrayList();
        private final ObservableList<Item> projects = FXCollections.observableArrayList();
        private final ObservableList<Item> internships = FXCollections.observableArrayList();
        private final ObservableList<Item> skills = FXCollections.observableArrayList();
        private final ObservableList<Item> resumes = FXCollections.observableArrayList();

        ResumeBookStub(Collection<Item> personalDetails) {
            this.personalDetails.setAll(personalDetails);
        }

        @Override
        public ObservableList<Item> getPersonalDetailList() {
            return personalDetails;
        }

        @Override
        public ObservableList<Item> getEducationList() {
            return educations;
        }

        @Override
        public ObservableList<Item> getAchievementList() {
            return achievements;
        }

        @Override
        public ObservableList<Item> getProjectList() {
            return projects;
        }

        @Override
        public ObservableList<Item> getInternshipList() {
            return internships;
        }

        @Override
        public ObservableList<Item> getSkillList() {
            return skills;
        }

        @Override
        public ObservableList<Item> getResumeList() {
            return resumes;
        }
    }

}
