package seedu.address.ui.note;

import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

public class NoteCard extends UiPart<Region> {
    private static final String FXML = "NoteCard.fxml";


    public NoteCard() {
        super(FXML);
    }
}
