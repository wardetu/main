package seedu.address.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Project;

/**
 * Adds a Project Item to the address book.
 */
public class AddProjectCommand extends AddCommand {
    private final Project toAdd;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an project to the address book. "
            + "Parameters: "
            + PREFIX_ITEM + "TYPE "
            + PREFIX_NAME + "PROJECT NAME "
            + PREFIX_TIME + "TIME "
            + PREFIX_FROM + "FROM "
            + PREFIX_DESCRIPTION + "DESC "
            + "[" + PREFIX_TAG + "TAG]....\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ITEM + "proj "
            + PREFIX_NAME + "Duke "
            + PREFIX_FROM + "06-2020 "
            + PREFIX_DESCRIPTION + "For a little module named CS2103T. "
            + PREFIX_TAG + "java "
            + PREFIX_TAG + "tech";

    /**
     * Creates an AddCommand to add the specified {@code project}.
     */
    public AddProjectCommand(Project project) {
        requireNonNull(project);
        toAdd = project;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasProject(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addProject(toAdd);
        model.setProjectToDisplay();

        return new CommandResult(toAdd.toString(),
                String.format(MESSAGE_SUCCESS, toAdd.getType().getFullType(), toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddProjectCommand // instanceof handles nulls
                && toAdd.equals(((AddProjectCommand) other).toAdd));
    }
}
