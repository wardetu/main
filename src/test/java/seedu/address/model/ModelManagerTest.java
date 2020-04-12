package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TECH;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternship.GOOGLE;
import static seedu.address.testutil.TypicalInternship.NINJA_VAN;
import static seedu.address.testutil.TypicalInternship.PAYPAL;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.item.Internship;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.ItemUtil;
import seedu.address.testutil.InternshipBuilder;
import seedu.address.testutil.PersonBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ResumeBook(), new ResumeBook(modelManager.getResumeBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setResumeBookFilePath(Paths.get("resume/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setResumeBookFilePath(Paths.get("new/resume/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setResumeBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setResumeBookFilePath(null));
    }

    @Test
    public void setResumeBookFilePath_validPath_setsResumeBookFilePath() {
        Path path = Paths.get("resume/book/file/path");
        modelManager.setResumeBookFilePath(path);
        assertEquals(path, modelManager.getResumeBookFilePath());
    }

    // Internship-related tests

    @Test
    public void hasInternship_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasInternship(null));
    }

    @Test
    public void hasInternship_internshipNotInResumeBook_returnsFalse() {
        assertFalse(modelManager.hasInternship(new InternshipBuilder(GOOGLE).build()));

        // add then delete
        modelManager.addInternship(new InternshipBuilder(GOOGLE).build());
        modelManager.deleteInternship(new InternshipBuilder(GOOGLE).build());
        assertFalse(modelManager.hasInternship(new InternshipBuilder(GOOGLE).build()));

        // add then set
        modelManager.addInternship(new InternshipBuilder(GOOGLE).build());
        modelManager.setInternship(new InternshipBuilder(GOOGLE).build(), PAYPAL);
        assertFalse(modelManager.hasInternship(new InternshipBuilder(GOOGLE).build()));
    }

    @Test
    public void hasInternship_internshipInResumeBook_returnsTrue() {
        modelManager.addInternship(new InternshipBuilder(GOOGLE).build());
        assertTrue(modelManager.hasInternship(new InternshipBuilder(GOOGLE).build()));
    }

    @Test
    public void getInternshipSize_emptyResumeBook_returnsZero() {
        assertEquals(0, modelManager.getInternshipSize());
    }

    @Test
    public void getInternshipByTag_emptyResumeBook_returnsEmptyList() {
        assertEquals(new ArrayList<Internship>(), modelManager.getInternshipsByTag(new Tag(VALID_TAG_TECH)));
    }

    @Test
    public void setInternshipsToDisplay_internshipInResumeBook_success() {
        modelManager.addInternship(new InternshipBuilder(GOOGLE).build());
        modelManager.addInternship(new InternshipBuilder(PAYPAL).build());
        modelManager.setInternshipToDisplay();
        assertEquals(modelManager.getDisplayType(), ItemUtil.INTERNSHIP_ALIAS);
        assertEquals(Arrays.asList(GOOGLE, PAYPAL), modelManager.getFilteredItemList());
    }

    @Test
    public void sortInternships_internshipInResumeBook_success() {
        modelManager.addInternship(new InternshipBuilder(GOOGLE).build());
        modelManager.addInternship(new InternshipBuilder(PAYPAL).build());
        modelManager.setInternshipToDisplay();
        assertEquals(modelManager.getDisplayType(), ItemUtil.INTERNSHIP_ALIAS);
        assertEquals(Arrays.asList(GOOGLE, PAYPAL), modelManager.getFilteredItemList());
    }

//    @Test
//    public void deletePerson_personIsSelectedAndFirstPersonInFilteredPersonList_selectionCleared() {
//        modelManager.addInternship(new InternshipBuilder(GOOGLE).build());
//        modelManager.setSelected(ALICE);
//        modelManager.deletePerson(ALICE);
//        assertEquals(null, modelManager.getSelectedPerson());
//    }
//
//    @Test
//    public void deletePerson_personIsSelectedAndSecondPersonInFilteredPersonList_firstPersonSelected() {
//        modelManager.addPerson(ALICE);
//        modelManager.addPerson(BOB);
//        assertEquals(Arrays.asList(ALICE, BOB), modelManager.getFilteredPersonList());
//        modelManager.setSelectedPerson(BOB);
//        modelManager.deletePerson(BOB);
//        assertEquals(ALICE, modelManager.getSelectedPerson());
//    }
//
//    @Test
//    public void setPerson_personIsSelected_selectedPersonUpdated() {
//        modelManager.addPerson(ALICE);
//        modelManager.setSelectedPerson(ALICE);
//        Person updatedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
//        modelManager.setPerson(ALICE, updatedAlice);
//        assertEquals(updatedAlice, modelManager.getSelectedPerson());
//    }
//
//    @Test
//    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
//        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
//    }
//
//    @Test
//    public void setSelectedPerson_personNotInFilteredPersonList_throwsPersonNotFoundException() {
//        assertThrows(PersonNotFoundException.class, () -> modelManager.setSelectedPerson(ALICE));
//    }
//
//    @Test
//    public void setSelectedPerson_personInFilteredPersonList_setsSelectedPerson() {
//        modelManager.addPerson(ALICE);
//        assertEquals(Collections.singletonList(ALICE), modelManager.getFilteredPersonList());
//        modelManager.setSelectedPerson(ALICE);
//        assertEquals(ALICE, modelManager.getSelectedPerson());
//    }



}