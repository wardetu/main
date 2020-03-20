package seedu.address.ui.personbio;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.MainApp;
import seedu.address.model.ResumeBook;
import seedu.address.model.item.Person;
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
    public PersonPane(Person user) {
        super(FXML);
        this.user = user;

        String name = this.user.getName().toString();
        String phone = this.user.getPhone().toString();
        String email = this.user.getEmail().toString();
        String github = this.user.getGithub().toString();
        String university = this.user.getUniversity();
        String major = this.user.getMajor();
        String from = this.user.getFrom().toString();
        String to = this.user.getTo().toString();
        String cap = String.valueOf(this.user.getCap());

        profilePic = new Image(MainApp.class.getResourceAsStream(filePath));
        Profile profile = new Profile(profilePic, "Nham Hung",
                "What if Newton discovered gravity from a durian?");
        profilePlaceholder.getChildren().add(profile.getRoot());

        studentProfile = new PersonDetailPane(name, phone, email, github, university, major, from, to, cap);
        personDetailsPlaceholder.getChildren().add(studentProfile.getRoot());
    }

    public void setProfile(Person updatedUser) {
        personDetailsPlaceholder.getChildren().clear();
        profilePlaceholder.getChildren().clear();
        String name = updatedUser.getName().toString();
        String phone = updatedUser.getPhone().toString();
        String email = updatedUser.getEmail().toString();
        String github = updatedUser.getGithub().toString();
        String university = updatedUser.getUniversity();
        String major = updatedUser.getMajor();
        String from = updatedUser.getFrom().toString();
        String to = updatedUser.getTo().toString();
        String cap = String.valueOf(updatedUser.getCap());
        personDetailsPlaceholder.getChildren().add(new PersonDetailPane(name, phone, email, github, university,
                 major, from, to, cap).getRoot());
        profilePic = new Image(MainApp.class.getResourceAsStream(filePath));
        Profile profile = new Profile(profilePic, name,
                "What if Newton discovered gravity from a durian?");
        profilePlaceholder.getChildren().add(profile.getRoot());
    }
}
