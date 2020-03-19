package seedu.address.logic.parser;

import seedu.address.logic.commands.ResumeEditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Item;

import java.util.Optional;
import java.util.stream.Stream;

import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESUME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

public class ResumeEditParser implements Parser<ResumeEditCommand> {

    @Override
    public ResumeEditCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_RESUME, PREFIX_INTERNSHIP, PREFIX_PROJECT, PREFIX_SKILL);

        if (!arePrefixesPresent(argMultimap, PREFIX_RESUME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException("You need to have the compulsory keyword res/");
        }

        String resumes = argMultimap.getValue(PREFIX_RESUME).get().trim();
        if (resumes.equals("")) {
            throw new ParseException("You need to provide at least one resume id");
        }
        String internships = argMultimap.getValue(PREFIX_INTERNSHIP).orElse("null").trim();
        String projects = argMultimap.getValue(PREFIX_PROJECT).orElse("null").trim();
        String skills = argMultimap.getValue(PREFIX_SKILL).orElse("null").trim();

        return new ResumeEditCommand(resumes, internships, projects, skills);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
