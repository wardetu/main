package seedu.address.model.item.field;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GithubTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Github(null));
    }

    @Test
    public void constructor_invalidGitHub_throwsIllegalArgumentException() {
        String invalidGithub = "";
        assertThrows(IllegalArgumentException.class, () -> new Github(invalidGithub));
    }

    @Test
    public void isValidGithub() {
        // null github
        assertThrows(NullPointerException.class, () -> Github.isValidGithub(null));

        // blank github
        assertFalse(Github.isValidGithub("")); // empty string
        assertFalse(Github.isValidGithub(" ")); // spaces only

        // invalid parts
        assertFalse(Github.isValidGithub("duong#$!")); // special characters
        assertFalse(Github.isValidGithub("duo---ng")); // multiple consecutive hyphens
        assertFalse(Github.isValidGithub("-duong")); // begins with a hyphen
        assertFalse(Github.isValidGithub("duong-")); // ends with a hyphen
        assertFalse(Github.isValidGithub("phamthuyduongphamthuyduongphamthuyduongg")); // more than 39 characters

        // valid github
        assertTrue(Github.isValidGithub("duongphammmm"));
        assertTrue(Github.isValidGithub("helloImHai"));
        assertTrue(Github.isValidGithub("wardetu"));
        assertTrue(Github.isValidGithub("chrisjwelly"));
        assertTrue(Github.isValidGithub("nhamhung"));
    }

}
