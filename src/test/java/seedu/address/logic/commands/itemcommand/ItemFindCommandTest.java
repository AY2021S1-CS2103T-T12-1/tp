package seedu.address.logic.commands.itemcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_ITEMS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.testutil.TypicalItems.getTypicalInventoryBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.UserPrefs;
import seedu.address.model.inventorymodel.InventoryModel;
import seedu.address.model.inventorymodel.InventoryModelManager;
import seedu.address.model.item.ItemContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code ItemFindCommand}.
 */
public class ItemFindCommandTest {
    private InventoryModel inventoryModel = new InventoryModelManager(getTypicalInventoryBook(), new UserPrefs());
    private InventoryModel expectedInventoryModel =
            new InventoryModelManager(getTypicalInventoryBook(), new UserPrefs());

    @Test
    public void equals() {
        ItemContainsKeywordsPredicate firstPredicate =
                new ItemContainsKeywordsPredicate(Collections.singletonList("first"), PREFIX_NAME);
        ItemContainsKeywordsPredicate secondPredicate =
                new ItemContainsKeywordsPredicate(Collections.singletonList("second"), PREFIX_NAME);

        ItemFindCommand findFirstCommand = new ItemFindCommand(firstPredicate);
        ItemFindCommand findSecondCommand = new ItemFindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        ItemFindCommand findFirstCommandCopy = new ItemFindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different item -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noItemFound() {
        String expectedMessage = String.format(MESSAGE_ITEMS_LISTED_OVERVIEW, 0);
        ItemContainsKeywordsPredicate predicate = preparePredicate(" ");
        ItemFindCommand command = new ItemFindCommand(predicate);
        expectedInventoryModel.updateFilteredItemList(predicate);
        assertCommandSuccess(command, inventoryModel, expectedMessage, expectedInventoryModel);
        assertEquals(Collections.emptyList(), inventoryModel.getFilteredItemList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private ItemContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ItemContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")), PREFIX_NAME);
    }
}
