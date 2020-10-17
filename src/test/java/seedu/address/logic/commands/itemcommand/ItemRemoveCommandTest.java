package seedu.address.logic.commands.itemcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.address.testutil.TypicalItems.getTypicalInventoryBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.UserPrefs;
import seedu.address.model.deliverymodel.DeliveryModelManager;
import seedu.address.model.inventorymodel.InventoryModel;
import seedu.address.model.inventorymodel.InventoryModelManager;
import seedu.address.model.item.Item;
import seedu.address.model.item.Quantity;
import seedu.address.testutil.ItemBuilder;

import java.util.List;


public class ItemRemoveCommandTest {

    private InventoryModel inventoryModel = new InventoryModelManager(getTypicalInventoryBook(), new UserPrefs());

    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        Item currentItem = new ItemBuilder().withName("Chicken").withQuantity("12").build();
        assertThrows(NullPointerException.class, () -> new ItemRemoveCommand(null, null));
        assertThrows(NullPointerException.class, () -> new ItemRemoveCommand(null, currentItem.getQuantity()));
        assertThrows(NullPointerException.class, () -> new ItemRemoveCommand(INDEX_FIRST_ITEM, null));
    }

    @Test
    public void execute_itemAcceptedByModel_removeSuccessful() throws CommandException {
        Item afterRemoveItem = new ItemBuilder().withName("Chicken")
                .withSupplier("Giant")
                .withQuantity("2")
                .withTags("meat")
                .build();
        Quantity quantity = new ItemBuilder().withQuantity("10").build().getQuantity();

        CommandResult commandResult = new ItemRemoveCommand(INDEX_FIRST_ITEM, quantity)
                .execute(List.of(inventoryModel, new DeliveryModelManager()));

        assertEquals(String.format(ItemRemoveCommand.MESSAGE_EDIT_ITEM_SUCCESS, afterRemoveItem),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        ItemRemoveCommand removeOne = new ItemRemoveCommand(INDEX_FIRST_ITEM, new Quantity("10"));
        ItemRemoveCommand removeTwo = new ItemRemoveCommand(INDEX_SECOND_ITEM, new Quantity("10"));
        ItemRemoveCommand removeThree = new ItemRemoveCommand(INDEX_SECOND_ITEM, new Quantity("20"));
        ItemAddCommand removeFour = new ItemAddCommand(new ItemBuilder().build());

        assertTrue(removeOne.equals(removeOne));

        assertFalse(removeOne.equals(removeTwo));

        assertFalse(removeTwo.equals(removeThree));

        assertFalse(removeOne.equals(removeFour));
    }

}
