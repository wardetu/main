package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Resume;
import seedu.address.model.item.field.Name;

/**
 * Generate pdf file from a Resume item
 */
public class GenerateResumeCommand extends Command {

    public static final String COMMAND_WORD = "rgen";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Generate a pdf file from a Resume item identified by the index number used in the resume list. "
            + "If no name is provided for the output .pdf file, default name is the same as resume name.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME]\n"
            + "Example: " + COMMAND_WORD + " 1 [" + PREFIX_NAME + "MyResume";

    public static final String MESSAGE_GENERATE_SUCCESS = "Generated %s from %s";

    protected final Index targetIndex;
    protected String title;

    public GenerateResumeCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        title = null;
    }

    public GenerateResumeCommand(Index targetIndex, Name resumeName) {
        this.targetIndex = targetIndex;
        this.title = resumeName.toString();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex.getZeroBased() >= model.getResumeSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Resume toGenerate = model.getResume(targetIndex);
        if (this.title == null) {
            title = toGenerate.getName().toString();
        }
        Document pdfResume = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(pdfResume, new FileOutputStream(title + ".pdf"));
            pdfResume.open();
            pdfResume.add(new Paragraph(title));
            pdfResume.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new CommandResult(toGenerate.toString(),
                String.format(MESSAGE_GENERATE_SUCCESS, title, toGenerate.getName().toString()));
    }
}
