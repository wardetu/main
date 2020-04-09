package seedu.address.testutil;

import seedu.address.model.ResumeBook;

/**
 * A utility class containing a list of {@code ResumeBook} objects to be used in tests.
 */
public class TypicalResumeBook {

    public static ResumeBook typical = buildTypical();
    public static ResumeBook typicalCopy = buildTypical();
    public static ResumeBook typicalWithoutGoogle = buildTypicalWithoutGoogle();
    public static ResumeBook typicalWithoutGoogleCopy = buildTypicalWithoutGoogle();

    // This TypicalResumeBook is used mainly for the ResumeEdit and TagPull Command tests
    // It will be best not to add more things, nor add Tags to the Items that are added
    public static ResumeBook typicalWithFilledResume = new ResumeBookBuilder()
            .withInternship(TypicalInternship.GOOGLE)
            .withInternship(TypicalInternship.NINJA_VAN)
            .withPerson(TypicalPerson.ALICE)
            .withProject(TypicalProject.ORBITAL)
            .withProject(TypicalProject.DUKE)
            .withSkill(TypicalSkill.REACT)
            .withSkill(TypicalSkill.GIT)
            .withResume(TypicalResume.ME_RESUME)
            .withResume(TypicalResume.SE_RESUME)
            .withResume(TypicalResume.FILLED_RESUME)
            .build();

    /**
     * Resets the data of typical resume book.
     */
    public static void reset() {
        typical = buildTypical();
        typicalCopy = buildTypical();
        typicalWithoutGoogle = buildTypicalWithoutGoogle();
        typicalWithoutGoogleCopy = buildTypicalWithoutGoogle();
    }

    /**
     * Builds a typical resume book.
     * @return the typical resume book.
     */
    private static ResumeBook buildTypical() {
        return new ResumeBookBuilder()
                .withInternship(new InternshipBuilder(TypicalInternship.GOOGLE).build())
                .withInternship(new InternshipBuilder(TypicalInternship.NINJA_VAN).build())
                .withInternship(new InternshipBuilder(TypicalInternship.PAYPAL).build())
                .withNote((new NoteBuilder(TypicalNote.FINISH_CS_2103).build()))
                .withPerson(new PersonBuilder(TypicalPerson.ALICE).build())
                .withProject(new ProjectBuilder(TypicalProject.ORBITAL).build())
                .withProject(new ProjectBuilder(TypicalProject.DUKE).build())
                .withSkill(new SkillBuilder(TypicalSkill.REACT).build())
                .withSkill(new SkillBuilder(TypicalSkill.GIT).build())
                .withResume(new ResumeBuilder(TypicalResume.ME_RESUME).build())
                .withResume(new ResumeBuilder(TypicalResume.SE_RESUME).build())
                .build();
    }

    /**
     * Builds a typical resume book without {@code Internship} item Google.
     * @return the typical resume book without Google.
     */
    private static ResumeBook buildTypicalWithoutGoogle() {
        return new ResumeBookBuilder()
                .withInternship(new InternshipBuilder(TypicalInternship.NINJA_VAN).build())
                .withInternship(new InternshipBuilder(TypicalInternship.PAYPAL).build())
                .withNote(new NoteBuilder(TypicalNote.FINISH_CS_2103).build())
                .withPerson(new PersonBuilder(TypicalPerson.ALICE).build())
                .withProject(new ProjectBuilder(TypicalProject.ORBITAL).build())
                .withSkill(new SkillBuilder(TypicalSkill.REACT).build())
                .withResume(new ResumeBuilder(TypicalResume.ME_RESUME).build())
                .build();
    }
}
