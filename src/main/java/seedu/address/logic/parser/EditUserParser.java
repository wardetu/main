package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CAP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIVERSITY;

import java.io.File;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditUserCommand;
import seedu.address.logic.commands.EditUserDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.field.DisplayPicture;
import seedu.address.model.item.field.Email;
import seedu.address.model.item.field.Github;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Phone;
import seedu.address.model.item.field.Time;

/**
 * Parser for EditUserCommand.
 */
public class EditUserParser implements Parser<EditUserCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditUserCommand
     * and returns an EditUserCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditUserCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DP, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_GITHUB,
                        PREFIX_UNIVERSITY, PREFIX_MAJOR, PREFIX_FROM, PREFIX_TO, PREFIX_CAP, PREFIX_TAG);

        if (!argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(EditUserCommand.MESSAGE_USAGE);
        }

        EditUserDescriptor editUserDescriptor = new EditUserDescriptor();

        if (argumentMultimap.getValue(PREFIX_DP).isPresent()) {
            String dpPath = argumentMultimap.getValue(PREFIX_DP).get();
            DisplayPicture displayProfile = new DisplayPicture(dpPath);
            if (isValidDisplayPicturePath(dpPath)) {
                editUserDescriptor.setDisplayPicture(displayProfile);
            } else {
                editUserDescriptor.setDisplayPicture(null);
            }
        }

        if (argumentMultimap.getValue(PREFIX_NAME).isPresent()) {
            editUserDescriptor.setName(new Name(argumentMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argumentMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editUserDescriptor.setPhone(new Phone(argumentMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argumentMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editUserDescriptor.setEmail(new Email(argumentMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argumentMultimap.getValue(PREFIX_GITHUB).isPresent()) {
            editUserDescriptor.setGithub(new Github(argumentMultimap.getValue(PREFIX_GITHUB).get()));
        }
        if (argumentMultimap.getValue(PREFIX_UNIVERSITY).isPresent()) {
            editUserDescriptor.setUni(argumentMultimap.getValue(PREFIX_UNIVERSITY).get());
        }
        if (argumentMultimap.getValue(PREFIX_MAJOR).isPresent()) {
            editUserDescriptor.setMajor(argumentMultimap.getValue(PREFIX_MAJOR).get());
        }
        if (argumentMultimap.getValue(PREFIX_FROM).isPresent()) {
            editUserDescriptor.setFrom(new Time(argumentMultimap.getValue(PREFIX_FROM).get()));
        }
        if (argumentMultimap.getValue(PREFIX_TO).isPresent()) {
            editUserDescriptor.setTo(new Time(argumentMultimap.getValue(PREFIX_TO).get()));
        }
        if (argumentMultimap.getValue(PREFIX_CAP).isPresent()) {
            editUserDescriptor.setCap(Double.valueOf(argumentMultimap.getValue(PREFIX_CAP).get()));
        }
        return new EditUserCommand(editUserDescriptor);
    }

    private static boolean isValidDisplayPicturePath(String dpPath) {
        File file = new File(dpPath);
        return file.exists();
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
