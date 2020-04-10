package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.storage.JsonAdaptedInternship.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.field.Description;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Role;
import seedu.address.model.item.field.Time;
import seedu.address.testutil.InternshipBuilder;
import seedu.address.testutil.TypicalInternship;

public class JsonAdaptedInternshipTest {

    @Test
    public void toModelType_validInternship_success() throws IllegalValueException {
        // Without tags
        JsonAdaptedInternship jsonAdaptedInternship =
                new JsonAdaptedInternship(new InternshipBuilder(TypicalInternship.PAYPAL).withTags().build());
        assertEquals(
                new InternshipBuilder(TypicalInternship.PAYPAL).withTags().build(),
                jsonAdaptedInternship.toModelType());

        List<JsonAdaptedTag> tags = new ArrayList<>();
        jsonAdaptedInternship =
                new JsonAdaptedInternship("PayPal",
                        "3",
                        "05-2020",
                        "07-2020",
                        "Backend Software Intern",
                        "I am learning new things",
                        tags
                );
        assertEquals(
                new InternshipBuilder(TypicalInternship.PAYPAL).withTags().build(),
                jsonAdaptedInternship.toModelType());

        // With tags
        jsonAdaptedInternship =
                new JsonAdaptedInternship(new InternshipBuilder(TypicalInternship.PAYPAL).build());
        assertEquals(new InternshipBuilder(TypicalInternship.PAYPAL).build(), jsonAdaptedInternship.toModelType());

        tags.add(new JsonAdaptedTag("tech"));
        tags.add(new JsonAdaptedTag("backend"));
        tags.add(new JsonAdaptedTag("SE"));
        jsonAdaptedInternship =
                new JsonAdaptedInternship("PayPal",
                        "3",
                        "05-2020",
                        "07-2020",
                        "Backend Software Intern",
                        "I am learning new things",
                        tags
                        );
        assertEquals(new InternshipBuilder(TypicalInternship.PAYPAL).build(), jsonAdaptedInternship.toModelType());
    }

    @Test
    public void loadToModel_invalidName_failure() {
        List<JsonAdaptedTag> tags = new ArrayList<>();
        tags.add(new JsonAdaptedTag("tech"));
        tags.add(new JsonAdaptedTag("backend"));
        tags.add(new JsonAdaptedTag("SE"));
        JsonAdaptedInternship jsonAdaptedInternship =
                new JsonAdaptedInternship("iV@liD Name$",
                        "3",
                        "05-2020",
                        "07-2020",
                        "Backend Software Intern",
                        "I am learning new things",
                        tags
                );
        assertThrows(IllegalValueException.class,
                Name.MESSAGE_CONSTRAINTS,
                jsonAdaptedInternship::toModelType);
    }

    @Test
    public void loadToModel_missingName_failure() {
        List<JsonAdaptedTag> tags = new ArrayList<>();
        tags.add(new JsonAdaptedTag("tech"));
        tags.add(new JsonAdaptedTag("backend"));
        tags.add(new JsonAdaptedTag("SE"));
        JsonAdaptedInternship jsonAdaptedInternship =
                new JsonAdaptedInternship(null,
                        "3",
                        "05-2020",
                        "07-2020",
                        "Backend Software Intern",
                        "I am learning new things",
                        tags
                );
        assertThrows(IllegalValueException.class,
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()),
                jsonAdaptedInternship::toModelType);
    }

    @Test
    public void loadToModel_invalidId_failure() {
        List<JsonAdaptedTag> tags = new ArrayList<>();
        tags.add(new JsonAdaptedTag("tech"));
        tags.add(new JsonAdaptedTag("backend"));
        tags.add(new JsonAdaptedTag("SE"));
        JsonAdaptedInternship jsonAdaptedInternship =
                new JsonAdaptedInternship("PayPal",
                        "abc",
                        "05-2020",
                        "07-2020",
                        "Backend Software Intern",
                        "I am learning new things",
                        tags
                );
        assertThrows(IllegalValueException.class,
                "The id field can only be an integer.",
                jsonAdaptedInternship::toModelType);

        jsonAdaptedInternship = new JsonAdaptedInternship("PayPal",
                "-3",
                "05-2020",
                "07-2020",
                "Backend Software Intern",
                "I am learning new things",
                tags
        );
        assertThrows(IllegalValueException.class,
                "The id field must not be negative.",
                jsonAdaptedInternship::toModelType);
    }

    @Test
    public void loadToModel_missingId_failure() {
        List<JsonAdaptedTag> tags = new ArrayList<>();
        tags.add(new JsonAdaptedTag("tech"));
        tags.add(new JsonAdaptedTag("backend"));
        tags.add(new JsonAdaptedTag("SE"));
        JsonAdaptedInternship jsonAdaptedInternship =
                new JsonAdaptedInternship("PayPal",
                        null,
                        "05-2020",
                        "07-2020",
                        "Backend Software Intern",
                        "I am learning new things",
                        tags
                );
        assertThrows(IllegalValueException.class,
                String.format(MISSING_FIELD_MESSAGE_FORMAT, "Id"),
                jsonAdaptedInternship::toModelType);
    }

    @Test
    public void loadToModel_invalidRole_failure() {
        List<JsonAdaptedTag> tags = new ArrayList<>();
        tags.add(new JsonAdaptedTag("tech"));
        tags.add(new JsonAdaptedTag("backend"));
        tags.add(new JsonAdaptedTag("SE"));
        JsonAdaptedInternship jsonAdaptedInternship =
                new JsonAdaptedInternship("PayPal",
                        "3",
                        "05-2020",
                        "07-2020",
                        "",
                        "I am learning new things",
                        tags
                );
        assertThrows(IllegalValueException.class,
                Role.MESSAGE_CONSTRAINTS,
                jsonAdaptedInternship::toModelType);
    }

    @Test
    public void loadToModel_missingRole_failure() {
        List<JsonAdaptedTag> tags = new ArrayList<>();
        tags.add(new JsonAdaptedTag("tech"));
        tags.add(new JsonAdaptedTag("backend"));
        tags.add(new JsonAdaptedTag("SE"));
        JsonAdaptedInternship jsonAdaptedInternship =
                new JsonAdaptedInternship("PayPal",
                        "3",
                        "05-2020",
                        "07-2020",
                        null,
                        "I am learning new things",
                        tags
                );
        assertThrows(IllegalValueException.class,
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName()),
                jsonAdaptedInternship::toModelType);
    }

    @Test
    public void loadToModel_invalidFrom_failure() {
        List<JsonAdaptedTag> tags = new ArrayList<>();
        tags.add(new JsonAdaptedTag("tech"));
        tags.add(new JsonAdaptedTag("backend"));
        tags.add(new JsonAdaptedTag("SE"));
        JsonAdaptedInternship jsonAdaptedInternship =
                new JsonAdaptedInternship("PayPal",
                        "3",
                        "032-2020",
                        "07-2020",
                        "Backend Software Intern",
                        "I am learning new things",
                        tags
                );
        assertThrows(IllegalValueException.class,
                Time.MESSAGE_CONSTRAINTS,
                jsonAdaptedInternship::toModelType);
    }

    @Test
    public void loadToModel_missingFrom_failure() {
        List<JsonAdaptedTag> tags = new ArrayList<>();
        tags.add(new JsonAdaptedTag("tech"));
        tags.add(new JsonAdaptedTag("backend"));
        tags.add(new JsonAdaptedTag("SE"));
        JsonAdaptedInternship jsonAdaptedInternship =
                new JsonAdaptedInternship("PayPal",
                        "3",
                        null,
                        "07-2020",
                        "Backend Software Intern",
                        "I am learning new things",
                        tags
                );
        assertThrows(IllegalValueException.class,
                String.format(MISSING_FIELD_MESSAGE_FORMAT, "From"),
                jsonAdaptedInternship::toModelType);
    }

    @Test
    public void loadToModel_invalidTo_failure() {
        List<JsonAdaptedTag> tags = new ArrayList<>();
        tags.add(new JsonAdaptedTag("tech"));
        tags.add(new JsonAdaptedTag("backend"));
        tags.add(new JsonAdaptedTag("SE"));
        JsonAdaptedInternship jsonAdaptedInternship =
                new JsonAdaptedInternship("PayPal",
                        "3",
                        "03-2020",
                        "not a time",
                        "Backend Software Intern",
                        "I am learning new things",
                        tags
                );
        assertThrows(IllegalValueException.class,
                Time.MESSAGE_CONSTRAINTS,
                jsonAdaptedInternship::toModelType);
    }

    @Test
    public void loadToModel_missingTo_failure() {
        List<JsonAdaptedTag> tags = new ArrayList<>();
        tags.add(new JsonAdaptedTag("tech"));
        tags.add(new JsonAdaptedTag("backend"));
        tags.add(new JsonAdaptedTag("SE"));
        JsonAdaptedInternship jsonAdaptedInternship =
                new JsonAdaptedInternship("PayPal",
                        "3",
                        "03-2020",
                        null,
                        "Backend Software Intern",
                        "I am learning new things",
                        tags
                );
        assertThrows(IllegalValueException.class,
                String.format(MISSING_FIELD_MESSAGE_FORMAT, "To"),
                jsonAdaptedInternship::toModelType);
    }

    @Test
    public void loadToModel_invalidFromTo_failure() {
        List<JsonAdaptedTag> tags = new ArrayList<>();
        tags.add(new JsonAdaptedTag("tech"));
        tags.add(new JsonAdaptedTag("backend"));
        tags.add(new JsonAdaptedTag("SE"));
        JsonAdaptedInternship jsonAdaptedInternship =
                new JsonAdaptedInternship("PayPal",
                        "3",
                        "03-2030",
                        "07-2020",
                        "Backend Software Intern",
                        "I am learning new things",
                        tags
                );
        assertThrows(IllegalValueException.class,
                "The \"to\" field must not precede the \"from\" field.",
                jsonAdaptedInternship::toModelType);
    }

    @Test
    public void loadToModel_invalidDescription_failure() {
        List<JsonAdaptedTag> tags = new ArrayList<>();
        tags.add(new JsonAdaptedTag("tech"));
        tags.add(new JsonAdaptedTag("backend"));
        tags.add(new JsonAdaptedTag("SE"));
        JsonAdaptedInternship jsonAdaptedInternship =
                new JsonAdaptedInternship("PayPal",
                        "3",
                        "05-2020",
                        "07-2020",
                        "Backend Software Intern",
                        "",
                        tags
                );
        assertThrows(IllegalValueException.class,
                Description.MESSAGE_CONSTRAINTS,
                jsonAdaptedInternship::toModelType);
    }

    @Test
    public void loadToModel_missingDescription_failure() {
        List<JsonAdaptedTag> tags = new ArrayList<>();
        tags.add(new JsonAdaptedTag("tech"));
        tags.add(new JsonAdaptedTag("backend"));
        tags.add(new JsonAdaptedTag("SE"));
        JsonAdaptedInternship jsonAdaptedInternship =
                new JsonAdaptedInternship("PayPal",
                        "3",
                        "03-2020",
                        "07-2020",
                        "Backend Software Intern",
                        null,
                        tags
                );
        assertThrows(IllegalValueException.class,
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()),
                jsonAdaptedInternship::toModelType);
    }

    @Test
    public void equals() {
        List<JsonAdaptedTag> tags = new ArrayList<>();
        tags.add(new JsonAdaptedTag("tech"));
        tags.add(new JsonAdaptedTag("backend"));
        tags.add(new JsonAdaptedTag("SE"));

        JsonAdaptedInternship jsonAdaptedPayPal =
                new JsonAdaptedInternship(new InternshipBuilder(TypicalInternship.PAYPAL).build());
        JsonAdaptedInternship jsonAdaptedGoogle =
                new JsonAdaptedInternship(new InternshipBuilder(TypicalInternship.GOOGLE).build());
        JsonAdaptedInternship jsonAdaptedPayPalCopy =
                new JsonAdaptedInternship("PayPal",
                        "3",
                        "05-2020",
                        "07-2020",
                        "Backend Software Intern",
                        "I am learning new things",
                        tags
                );

        // Two constructors give same copy
        assertEquals(jsonAdaptedPayPal, jsonAdaptedPayPalCopy);

        // Different json adapted internships are not equal
        assertNotEquals(jsonAdaptedGoogle, jsonAdaptedPayPal);
    }
}
