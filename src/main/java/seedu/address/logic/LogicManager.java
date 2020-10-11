package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.parser.InventoryBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.inventorymodel.InventoryModel;
import seedu.address.model.inventorymodel.ReadOnlyInventoryBook;
import seedu.address.model.item.Item;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final InventoryModel inventoryModel;
    private final Storage storage;
    private final InventoryBookParser inventoryBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(InventoryModel inventoryModel, Storage storage) {
        this.inventoryModel = inventoryModel;
        this.storage = storage;
        inventoryBookParser = new InventoryBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = inventoryBookParser.parseCommand(commandText);
        commandResult = command.execute(inventoryModel);

        try {
            storage.saveInventoryBook(inventoryModel.getInventoryBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyInventoryBook getInventoryBook() {
        return inventoryModel.getInventoryBook();
    }

    @Override
    public ObservableList<Item> getFilteredItemList() {
        return inventoryModel.getFilteredItemList();
    }

    @Override
    public Path getInventoryBookFilePath() {
        return inventoryModel.getInventoryBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return inventoryModel.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        inventoryModel.setGuiSettings(guiSettings);
    }
}
