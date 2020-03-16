package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Project;

/**
 * Adds a Project Item to the address book.
 */
public class AddProjectCommand extends AddCommand {
    private final Project toAdd;

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
