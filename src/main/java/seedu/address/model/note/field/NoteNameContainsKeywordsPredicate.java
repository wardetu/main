package seedu.address.model.note.field;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.note.Note;

/**
 * Predicate that checks for title when finding a Note.
 */
public class NoteNameContainsKeywordsPredicate implements Predicate<Note> {
    private final List<String> keywords;

    public NoteNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Note note) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(note.getName().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NoteNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NoteNameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
