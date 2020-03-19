package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Skill;
import seedu.address.model.item.field.Level;
import seedu.address.model.item.field.Name;
import seedu.address.model.tag.Tag;

/**
 * Edits a Skill Item in the address book.
 * TODO: CONNECT LEVEL TO SKILL
 */
public class EditSkillCommand extends EditCommand {

    private static final String MESSAGE_EDIT_RESUME_SUCCESS = "Edited Skill: %1$s";

    private EditSkillDescriptor editSkillDescriptor;
    /**
     * @param index                of the person in the filtered person list to edit
     * @param editSkillDescriptor details to edit the skill with
     */
    public EditSkillCommand(Index index, EditSkillDescriptor editSkillDescriptor) {
        super(index);
        this.editSkillDescriptor = editSkillDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (index.getZeroBased() >= model.getSkillSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Skill toEdit = model.getSkill(index);

        Skill editedSkill = createEditedSkill(toEdit, editSkillDescriptor);

        model.setSkill(toEdit, editedSkill);
        model.setSkillToDisplay();
        model.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult(editedSkill.toString(), String.format(MESSAGE_EDIT_RESUME_SUCCESS, editedSkill));
    }

    /**
     * Creates the edited Skill item from the skill to be edited and the descriptor.
     * @param toEdit Skill item to be edited
     * @param editSkillDescriptor Descriptor parsed from input of user
     * @return Edited Skill item.
     */
    private static Skill createEditedSkill(Skill toEdit, EditSkillDescriptor editSkillDescriptor) {
        Name updatedName = editSkillDescriptor.getName().orElse(toEdit.getName());
        Level level = editSkillDescriptor.getLevel().orElse(toEdit.getLevel());
        Set<Tag> updatedTags = editSkillDescriptor.getTags().orElse(toEdit.getTags());
        int id = toEdit.getId();
        return new Skill(updatedName, level, updatedTags, id);
    }
}
