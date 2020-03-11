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

public class AddressBookTest {

    private final ResumeBook addressBook = new ResumeBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonalDetailList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        ResumeBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Item editedAlice = new PersonalDetailBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Item> newItems = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newItems);

        assertThrows(DuplicateItemException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPersonalDetail(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPersonalDetail(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPersonalDetail(ALICE);
        assertTrue(addressBook.hasPersonalDetail(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPersonalDetail(ALICE);
        Item editedAlice = new PersonalDetailBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPersonalDetail(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonalDetailList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyResumeBook {
        private final ObservableList<Item> items = FXCollections.observableArrayList();

        AddressBookStub(Collection<Item> items) {
            this.items.setAll(items);
        }

        @Override
        public ObservableList<Item> getPersonalDetailList() {
            return items;
        }
    }

}
