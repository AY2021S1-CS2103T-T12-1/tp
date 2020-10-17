package seedu.address.logic.commands.deliverycommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_DELIVERIES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.testutil.TypicalDeliveries.getTypicalDeliveryBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.UserPrefs;
import seedu.address.model.delivery.Delivery;
import seedu.address.model.delivery.DeliveryContainsKeywordsPredicate;
import seedu.address.model.deliverymodel.DeliveryModel;
import seedu.address.model.deliverymodel.DeliveryModelManager;
import seedu.address.testutil.DeliveryBuilder;

class DeliveryFindCommandTest {
    private static final Delivery KELVIN = new DeliveryBuilder().withName("Kelvin")
            .withPhone("91234332")
            .withAddress("Clementi Blk 235 #11-111")
            .withOrder("Char Kway Teow")
            .build();
    private static final Delivery MARCUS = new DeliveryBuilder().withName("Marcus")
            .withPhone("8198264")
            .withAddress("Jurong Blk 231 #15-123")
            .withOrder("Seafood Hor Fun x5")
            .build();
    private DeliveryModel deliveryModel = new DeliveryModelManager(getTypicalDeliveryBook(), new UserPrefs());
    private DeliveryModel expectedDeliveryModel =
            new DeliveryModelManager(getTypicalDeliveryBook(), new UserPrefs());
    private DeliveryModel testModel;
    private DeliveryModel expectedTestModel;

    @BeforeEach
    public void setUp() {
        testModel = new DeliveryModelManager();
        expectedTestModel = new DeliveryModelManager();
        testModel.addDelivery(KELVIN);
        testModel.addDelivery(MARCUS);
        expectedTestModel.addDelivery(KELVIN);
        expectedTestModel.addDelivery(MARCUS);
    }

    @Test
    public void execute_zeroKeywords_noItemFound() {
        executeTest(0, " ", PREFIX_NAME,
                deliveryModel, expectedDeliveryModel, Collections.emptyList());
    }

    @Test
    public void execute_matchingSubstring_itemsFound() {
        executeTest(2, "Blk 23", PREFIX_ADDRESS, testModel, expectedTestModel,
                testModel.getFilteredDeliveryList());
    }

    @Test
    public void execute_matchingKeywordsNotInOrder_itemsFound() {
        executeTest(2, "23 Blk", PREFIX_ADDRESS, testModel, expectedTestModel,
                testModel.getFilteredDeliveryList());
    }

    @Test
    void equals() {
        DeliveryContainsKeywordsPredicate firstPredicate =
                new DeliveryContainsKeywordsPredicate(Collections.singletonList("first"), PREFIX_NAME);
        DeliveryContainsKeywordsPredicate secondPredicate =
                new DeliveryContainsKeywordsPredicate(Collections.singletonList("second"), PREFIX_NAME);
        DeliveryFindCommand findFirstCommand = new DeliveryFindCommand(firstPredicate);
        DeliveryFindCommand findSecondCommand = new DeliveryFindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        DeliveryFindCommand findFirstCommandCopy = new DeliveryFindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different delivery -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private DeliveryContainsKeywordsPredicate preparePredicate(String userInput, Prefix searchPrefix) {
        return new DeliveryContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s*")), searchPrefix);
    }

    /**
     * Carries out an {@code execute} test with relevant arguments.
     */
    private void executeTest(int numItemsMatched, String userInput, Prefix searchPrefix,
                             DeliveryModel actualModel, DeliveryModel expectedModel,
                             List<Delivery> expectedFilteredDeliveries) {

        String expectedMessage = String.format(MESSAGE_DELIVERIES_LISTED_OVERVIEW, numItemsMatched);
        DeliveryContainsKeywordsPredicate predicate = preparePredicate(userInput, searchPrefix);
        DeliveryFindCommand command = new DeliveryFindCommand(predicate);
        expectedModel.updateFilteredDeliveryList(predicate);

        assertCommandSuccess(command, actualModel, expectedMessage, expectedModel);
        assertEquals(expectedFilteredDeliveries, actualModel.getFilteredDeliveryList());
    }
}
