package seedu.address.model.item;

import java.util.Observable;

/**
 * A wrapper class for the person contained in the resume book.
 */
public class ObservablePerson extends Observable {

    private Person internalPerson;

    public ObservablePerson(Person person) {
        this.internalPerson = person;
    }

    public void setInternalPerson (Person person) {
        this.internalPerson = person;
        this.setChanged();
        this.notifyObservers();
    }

    public Person getInternalPerson() {
        return internalPerson;
    }

}
