package seedu.address.model.note.field;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.note.NoteEntry;

/**
 * Predicate that checks for title when finding a Note.
 */
public class EntryTitleContainsKeywordsPredicate implements Predicate<NoteEntry> {
    private final List<String> keywords;

    public EntryTitleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(NoteEntry noteEntry) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(noteEntry.getTitle().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EntryTitleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((EntryTitleContainsKeywordsPredicate) other).keywords)); // state check
    }
}
