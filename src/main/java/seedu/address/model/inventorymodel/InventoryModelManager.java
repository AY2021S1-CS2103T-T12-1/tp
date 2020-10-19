package seedu.address.model.inventorymodel;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Item;

/**
 * Represents the in-memory model of the inventory book data.
 */
public class InventoryModelManager implements InventoryModel {
    private static final Logger logger = LogsCenter.getLogger(InventoryModelManager.class);

    private final InventoryBook inventoryBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Item> filteredItems;

    /**
     * Initializes a InventoryModelManager with the given inventoryBook and userPrefs.
     */
    public InventoryModelManager(ReadOnlyInventoryBook inventoryBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(inventoryBook, userPrefs);

        logger.fine("Initializing with inventory book: " + inventoryBook + " and user prefs " + userPrefs);

        this.inventoryBook = new InventoryBook(inventoryBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredItems = new FilteredList<>(this.inventoryBook.getItemList());
    }

    public InventoryModelManager() {
        this(new InventoryBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getInventoryBookFilePath() {
        return userPrefs.getInventoryBookFilePath();
    }

    @Override
    public void setInventoryBookFilePath(Path inventoryBookFilePath) {
        requireNonNull(inventoryBookFilePath);
        userPrefs.setInventoryBookFilePath(inventoryBookFilePath);
    }

    //=========== InventoryBook ================================================================================

    @Override
    public void setInventoryBook(ReadOnlyInventoryBook inventoryBook) {
        this.inventoryBook.resetData(inventoryBook);
    }

    @Override
    public ReadOnlyInventoryBook getInventoryBook() {
        return inventoryBook;
    }

    @Override
    public boolean hasItem(Item item) {
        requireNonNull(item);
        return inventoryBook.hasItem(item);
    }

    @Override
    public void deleteItem(Item target) {
        inventoryBook.removeItem(target);
    }

    @Override
    public void addItem(Item item) {
        inventoryBook.addItem(item);
        updateFilteredAndSortedItemList(PREDICATE_SHOW_ALL_ITEMS);
    }

    @Override
    public Item addOnExistingItem(Item item) {
        Item combinedItem = inventoryBook.addOnExistingItem(item);
        updateFilteredAndSortedItemList(PREDICATE_SHOW_ALL_ITEMS);
        return combinedItem;
    }

    @Override
    public void setItem(Item target, Item editedItem) {
        requireAllNonNull(target, editedItem);

        inventoryBook.setItem(target, editedItem);
    }

    //=========== Item List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Item} backed by the internal list of
     * {@code versionedInventoryBook}
     */
    @Override
    public ObservableList<Item> getFilteredAndSortedItemList() {
        return new SortedList<>(filteredItems, ITEM_COMPARATOR);
    }

    @Override
    public void updateFilteredAndSortedItemList(Predicate<Item> predicate) {
        requireNonNull(predicate);
        filteredItems.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof InventoryModelManager)) {
            return false;
        }

        // state check
        InventoryModelManager other = (InventoryModelManager) obj;
        return inventoryBook.equals(other.inventoryBook)
                && userPrefs.equals(other.userPrefs)
                && filteredItems.equals(other.filteredItems);
    }

}
