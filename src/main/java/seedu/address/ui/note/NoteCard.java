package seedu.address.ui.note;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.note.NoteEntry;
import seedu.address.ui.UiPart;

public class NoteCard extends UiPart<Region> {

    private static final String FXML = "NoteCard.fxml";

    private final NoteEntry noteEntry;

    @FXML
    private HBox noteCardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label tag;
    @FXML
    private Label title;
    @FXML
    private Label time;
    @FXML
    private Label place;
    @FXML
    private Label description;
    @FXML
    private ImageView checkBox;

    public NoteCard(NoteEntry noteEntry, int displayIndex) {
        super(FXML);
        this.noteEntry = noteEntry;
        id.setText(displayIndex + ". ");
        title.setText(noteEntry.getTitle().toString());
        name.setText(noteEntry.getName().toString());
        time.setText(noteEntry.getTime().toString());
        place.setText(noteEntry.getPlace().toString());
        description.setText(noteEntry.getDescription().toString());
//        tag.setText(noteEntry.getTags().toString());
        tag.setText("Priority: HIGH");

//        Image image = new Image("/docs/images/check-mark.png");
//        checkBox.setImage(image);
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
                && noteEntry.equals(card.noteEntry);
    }
}
