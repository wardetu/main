package seedu.address.model.item.wrapper;

import java.util.Observable;

import seedu.address.model.item.Person;


/**
 * A wrapper class for the person contained in the resume book.
 */
public class ObservablePerson extends Observable {

    private Person internalPerson;

    public ObservablePerson(Person person) {
        this.internalPerson = person;
    }

    public void setPerson(ObservablePerson person) {
        this.internalPerson = person.getInternalPerson();
        this.setChanged();
        // Triggers all the observers
        this.notifyObservers();
    }

    public Person getInternalPerson() {
        return internalPerson;
    }

}
