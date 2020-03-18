package seedu.address.ui.personbio;

import java.util.Iterator;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.MainApp;
import seedu.address.model.ResumeBook;
import seedu.address.model.item.Person;
import seedu.address.model.tag.Tag;
import seedu.address.ui.UiPart;

/**
 * The entire portion that displays user's personal details
 */
public class PersonPane extends UiPart<Region> {
    private static final String FXML = "PersonPane.fxml";
    private static String filePath = "/images/photo.png";

    private Profile profile;
    private Image profilePic;
    private String profilePicPath;
    private PersonDetailPane studentProfile;
    private ResumeBook resumeBook;
    private Person user;

    @FXML
    private HBox profilePlaceholder;

    @FXML
    private VBox personDetailsPlaceholder;

    /**
     * Constructs the entire person pane with profile and personal details table.
     */
    public PersonPane() {
        super(FXML);
        resumeBook = new ResumeBook();
        this.user = resumeBook.getUser();

        String name = this.user.getName().toString();
        String phone = this.user.getPhone().toString();
        String email = this.user.getEmail().toString();
        String github = this.user.getGithub().toString();
        String university = this.user.getUniversity();
        String major = this.user.getMajor();
        String from = this.user.getFrom().toString();
        String to = this.user.getTo().toString();
        String cap = String.valueOf(this.user.getCap());
        String tag = "";
        Set<Tag> tags = this.user.getTags();
        Iterator<Tag> iter = tags.iterator();
        while (iter.hasNext()) {
            tag += iter.next().toString() + " ";
        }

        profilePic = new Image(MainApp.class.getResourceAsStream(filePath));
        Profile profile = new Profile(profilePic, "Nham Hung",
                "What if Newton discovered gravity from a durian?");
        profilePlaceholder.getChildren().add(profile.getRoot());
        studentProfile = new PersonDetailPane(name, phone, email, github, university, major, from,
                to, cap, tag);
        personDetailsPlaceholder.getChildren().add(studentProfile.getRoot());
    }
}
