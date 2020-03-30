package seedu.address.ui.note;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

public class NoteListPanel extends UiPart<Region> {
    private static final String FXML = "NotePage.fxml";

    @FXML
    private ListView noteListView;

    public NoteListPanel() {
        super(FXML);
    }
}
