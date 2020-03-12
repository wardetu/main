package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

public class Item extends UiPart<Region> {
    private static final String FXML = "Item.fxml";

    @FXML
    private TextArea item;

    public Item() {
        super(FXML);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        item.setText(feedbackToUser);
    }
}
