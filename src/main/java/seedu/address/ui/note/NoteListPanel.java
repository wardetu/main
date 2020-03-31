package seedu.address.ui.note;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.note.NoteEntry;
import seedu.address.ui.UiPart;

public class NoteListPanel extends UiPart<Region> {
    private static final String FXML = "NoteListPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(NoteListPanel.class);

    @FXML
    private ListView<NoteEntry> noteListView;

    public NoteListPanel(ObservableList<NoteEntry> noteEntries) {
        super(FXML);
        noteListView.setItems(noteEntries);
        noteListView.setCellFactory(listView -> new NoteListViewCell());
    }

    class NoteListViewCell extends ListCell<NoteEntry> {
        @Override
        protected void updateItem(NoteEntry entry, boolean empty) {
            super.updateItem(entry, empty);

            if (empty || entry == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new NoteCard(entry, getIndex() + 1).getRoot());
            }
        }
    }
}
