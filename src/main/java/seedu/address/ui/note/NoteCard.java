package seedu.address.ui.note;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.note.Note;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code NoteEntry}.
 */
public class NoteCard extends UiPart<Region> {

    private static final String FXML = "NoteCard.fxml";

    private final Note note;

    @FXML
    private HBox noteCardPane;
    @FXML
    private Label id;
    @FXML
    private Label tag;
    @FXML
    private Label title;
    @FXML
    private Label time;
    @FXML
    private ImageView tickBox;

    public NoteCard(Note note, int displayIndex) {
        super(FXML);
        this.note = note;
        id.setText(displayIndex + ". ");
        title.setText(note.getName().toString());
        time.setText(note.getTime().toString());

        Image image;

        if (note.isDone()) {
            image = new Image("/images/check-mark.png");
        } else {
            image = new Image("/images/remove.png");
        }

        tickBox.setImage(image);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoteCard)) {
            return false;
        }

        // state check
        NoteCard card = (NoteCard) other;
        return id.getText().equals(card.id.getText())
                && note.equals(card.note);
    }
}
