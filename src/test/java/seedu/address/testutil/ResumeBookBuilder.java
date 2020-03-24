package seedu.address.testutil;

import seedu.address.model.ResumeBook;
import seedu.address.model.item.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class ResumeBookBuilder {

    private ResumeBook resumeBook;

    public ResumeBookBuilder() {
        resumeBook = new ResumeBook();
    }

    public ResumeBookBuilder(ResumeBook addressBook) {
        this.resumeBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code ResumeBook} that we are building.
     */
    public ResumeBookBuilder withPerson(Person person) {
        resumeBook.setUser(person);
        return this;
    }

    public ResumeBook build() {
        return resumeBook;
    }
}
