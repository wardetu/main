package seedu.address.logic.commands;

import static java.lang.System.err;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.awt.*;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

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

    private static final String rootPath = "export/";
    private static final PDRectangle page = PDRectangle.A4;
    private static final int margin = 100;
    private static final PDFont FONT = PDType1Font.HELVETICA_BOLD;
    private static final int HEADING_SIZE = 14;
    private static final int BODY_SIZE = 11;
    private static final Color MAIN_COLOR = new Color(0, 0, 0);
    private static final Color ACCENT_COLOR = new Color(153, 0, 51);

    public GenerateResumeCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        title = null;
    }

    public GenerateResumeCommand(Index targetIndex, Name resumeName) {
        this.targetIndex = targetIndex;
        this.title = resumeName.toString();
    }

    public void addTitle(PDPageContentStream contentStream, String title) throws IOException {
        contentStream.setFont(FONT, HEADING_SIZE);
        contentStream.setNonStrokingColor(ACCENT_COLOR);
        float stringWidth = FONT.getStringWidth(title) * HEADING_SIZE / 1000f;
        float pageWidth = page.getWidth();
        float xOffset = (pageWidth - stringWidth) / 2f;
        float pageHeight = page.getHeight();
        float yOffset = pageHeight - margin;
        contentStream.newLineAtOffset(xOffset, yOffset);
        contentStream.showText(title.toUpperCase());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        // Get resume item
        requireNonNull(model);
        if (targetIndex.getZeroBased() >= model.getResumeSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }
        Resume toGenerate = model.getResume(targetIndex);

        // Get title
        if (this.title == null) {
            title = toGenerate.getName().toString();
        }

        final PDPage singlePage = new PDPage();
        try {
            final PDDocument resume = new PDDocument();
            resume.addPage(singlePage);
            final PDPageContentStream contentStream = new PDPageContentStream(resume, singlePage);
            contentStream.beginText();
            addTitle(contentStream, title);
            contentStream.endText();
            contentStream.close();
            resume.save(rootPath + title + ".pdf");
        } catch (IOException e) {
            err.println("Exception while trying to create simple document - " + e);
        }

        return new CommandResult(toGenerate.toString(),
                String.format(MESSAGE_GENERATE_SUCCESS, title, toGenerate.getName().toString()));
    }
}
