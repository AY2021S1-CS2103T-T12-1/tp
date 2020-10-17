package seedu.address.logic.commands.help;

import static seedu.address.commons.core.Messages.HELP_SUMMARY;

import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.HelpCommandResult;
import seedu.address.model.Model;

import java.util.List;

/**
 * Formats full help instructions for every command for display.
 */
public class HelpSummaryCommand extends HelpCommand {

    /**
     * Opens a pop-up window with the summary of all available commands.
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} that describes changes made when command execute runs successfully.
     */
    @Override
    public CommandResult execute(List<Model> models) {
        return new HelpCommandResult(SHOWING_HELP_MESSAGE,
                false, true, false, "", HELP_SUMMARY);
    }
}
