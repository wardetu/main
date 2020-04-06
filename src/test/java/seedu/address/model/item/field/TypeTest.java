package seedu.address.model.item.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalResume;

public class TypeTest {
    Type resume = Type.generate("res");
    Type skill = Type.generate("ski");
    Type internship = Type.generate("int");
    Type project = Type.generate("proj");
    Type invalid = Type.generate("hello");

    @Test
    public void generate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Type.generate(null));
    }

    @Test
    public void getAlias() {

        assertEquals(resume.getAlias(), "res");
        assertEquals(skill.getAlias(), "ski");
        assertNotEquals(resume.getAlias(), skill.getAlias());
    }

    @Test
    public void getFullType() {
        assertEquals(internship.getFullType(), "Internship");
        assertEquals(project.getFullType(), "Project");
        assertNotEquals(internship.getFullType(), project.getFullType());
        assertEquals(invalid.getFullType(), "Not a valid type");
    }

    @Test
    public void equal() {
        assertEquals(resume, Type.generate("res"));
        assertNotEquals(resume, internship);
        assertNotEquals(resume, invalid);
    }
}
