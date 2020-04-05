package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_FROM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERNSHIP_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERNSHIP_NAME_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERNSHIP_ROLE_FRONTEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BACKEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRONTEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_JAVA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ResumeBook;
import seedu.address.model.item.Internship;

/**
 * A utility class containing a list of {@code Internship} objects to be used in tests.
 */
public class TypicalInternship {
    public static final Internship PAYPAL = new InternshipBuilder().withName("PayPal")
            .withRole("Backend Software Intern").withFrom("05-2020").withTo("07-2020")
            .withDescription("I am learning new things").withTags(VALID_TAG_BACKEND, "SE").build();

    public static final Internship NINJA_VAN = new InternshipBuilder().withName("Ninja Van")
            .withRole("Ninja Intern").withFrom("05-2020").withTo("07-2020")
            .withDescription("My journey to become a better ninja").withTags("UX").build();

    // Manually added - internship's details found in {@code CommandTestUtil}
    public static final Internship GOOGLE = new InternshipBuilder().withName(VALID_INTERNSHIP_NAME_GOOGLE)
            .withRole(VALID_INTERNSHIP_ROLE_FRONTEND).withFrom(VALID_FROM).withTo(VALID_TO)
            .withDescription(VALID_INTERNSHIP_DESCRIPTION).withTags(VALID_TAG_FRONTEND, VALID_TAG_JAVA).build();

    public static final String KEYWORD_MATCHING_SOFTWARE = "software"; // A keyword that matches SOFTWARE

    private TypicalInternship() {} // prevents instantiation

    /**
     * Returns a {@code ResumeBook} with all the typical internships.
     */
    public static ResumeBook getTypicalAddressBook() {
        ResumeBook rb = new ResumeBook();
        for (Internship internship : getTypicalInternships()) {
            rb.addInternship(internship);
        }
        return rb;
    }

    public static List<Internship> getTypicalInternships() {
        return new ArrayList<>(Arrays.asList(PAYPAL, NINJA_VAN, GOOGLE));
    }


}
