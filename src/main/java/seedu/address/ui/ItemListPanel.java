package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.item.Item;

/**
 * Panel containing the list of persons.
 */
public class ItemListPanel extends UiPart<Region> {
    private static final String FXML = "ItemListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ItemListPanel.class);

    @FXML
    private ListView<Item> personListView;

    public ItemListPanel(ObservableList<Item> personList) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new ItemListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ItemListViewCell extends ListCell<Item> {
        @Override
        protected void updateItem(Item person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ItemCard(person, getIndex() + 1).getRoot());
            }
        }
    }
}
