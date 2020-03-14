package seedu.address.ui.personbio;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Item;
import seedu.address.model.item.field.Name;
import seedu.address.ui.UiPart;

public class PersonPane extends UiPart<Region> {
    private static final String FXML = "PersonPane.fxml";
    private static String filePath = "/images/person.png";

    private Profile profile;
    private Image image;
    private String profilePicPath;

    @FXML
    private HBox profilePlaceholder;

    @FXML
    private VBox personDetailsPlaceholder;

    public PersonPane(Image img) {
        super(FXML);

        Item person = new Internship(new Name("ABC"), );
    }
}
