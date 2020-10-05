package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalItems.getTypicalInventoryBook;

import java.util.Arrays;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalInventoryBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalInventoryBook(), new UserPrefs());

    //    @Test
    //    public void equals() {
    //        NameContainsKeywordsPredicate firstPredicate =
    //                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
    //        NameContainsKeywordsPredicate secondPredicate =
    //                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
    //
    //        FindCommand findFirstCommand = new FindCommand(firstPredicate);
    //        FindCommand findSecondCommand = new FindCommand(secondPredicate);
    //
    //        // same object -> returns true
    //        assertTrue(findFirstCommand.equals(findFirstCommand));
    //
    //        // same values -> returns true
    //        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
    //        assertTrue(findFirstCommand.equals(findFirstCommandCopy));
    //
    //        // different types -> returns false
    //        assertFalse(findFirstCommand.equals(1));
    //
    //        // null -> returns false
    //        assertFalse(findFirstCommand.equals(null));
    //
    //        // different item -> returns false
    //        assertFalse(findFirstCommand.equals(findSecondCommand));
    //    }

    //    @Test
    //    public void execute_zeroKeywords_noItemFound() {
    //        String expectedMessage = String.format(MESSAGE_ITEMS_LISTED_OVERVIEW, 0);
    //        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
    //        FindCommand command = new FindCommand(predicate);
    //        expectedModel.updateFilteredItemList(predicate);
    //        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    //        assertEquals(Collections.emptyList(), model.getFilteredItemList());
    //    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
