package seedu.address.testutil;

import seedu.address.logic.commands.edit.EditInternshipDescriptor;
import seedu.address.model.item.Internship;

/**
 * A utility class to help with building EditInternshipDescriptor objects.
 */
public class EditInternshipDescriptorBuilder {

    private EditInternshipDescriptor descriptor = new EditInternshipDescriptor();

    public EditInternshipDescriptorBuilder(Internship internshipToCopy) {
        descriptor.setName(internshipToCopy.getName());
        descriptor.setRole(internshipToCopy.getRole());
        descriptor.setFrom(internshipToCopy.getFrom());
        descriptor.setTo(internshipToCopy.getTo());
        descriptor.setDescription(internshipToCopy.getDescription());
        descriptor.setTags(internshipToCopy.getTags());
    }

    public EditInternshipDescriptor build() {
        return this.descriptor;
    }
}
