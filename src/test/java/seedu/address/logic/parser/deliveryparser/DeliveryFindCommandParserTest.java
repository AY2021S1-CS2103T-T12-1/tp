package seedu.address.logic.parser.deliveryparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_DAMITH;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_DAMITH;
import static seedu.address.logic.commands.CommandTestUtil.ORDER_DESC_DAMITH;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_DAMITH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_DAMITH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AARON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DAMITH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_DAMITH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_DAMITH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.deliverycommand.DeliveryFindCommand;
import seedu.address.model.delivery.DeliveryContainsKeywordsPredicate;

class DeliveryFindCommandParserTest {

    private DeliveryFindCommandParser parser = new DeliveryFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeliveryFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        DeliveryFindCommand expectedFindCommand = new DeliveryFindCommand(new DeliveryContainsKeywordsPredicate(
                                Arrays.asList(VALID_NAME_DAMITH, VALID_NAME_AARON), PREFIX_NAME));
        assertParseSuccess(parser, NAME_DESC_DAMITH + " " + VALID_NAME_AARON, expectedFindCommand);
    }

    @Test
    public void parse_validAddressArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        DeliveryFindCommand expectedFindCommand = new DeliveryFindCommand(new DeliveryContainsKeywordsPredicate(
                Arrays.asList(VALID_ADDRESS_DAMITH.split("\\s+")), PREFIX_ADDRESS));
        assertParseSuccess(parser, ADDRESS_DESC_DAMITH, expectedFindCommand);
    }

    @Test
    public void parse_validOrderArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        DeliveryFindCommand expectedFindCommand = new DeliveryFindCommand(new DeliveryContainsKeywordsPredicate(
                Arrays.asList(VALID_ORDER_DAMITH.split("\\s+")), PREFIX_ORDER));
        assertParseSuccess(parser, ORDER_DESC_DAMITH, expectedFindCommand);
    }

    @Test
    public void parse_validPhoneArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        DeliveryFindCommand expectedFindCommand = new DeliveryFindCommand(new DeliveryContainsKeywordsPredicate(
                Arrays.asList(VALID_PHONE_DAMITH), PREFIX_PHONE));
        assertParseSuccess(parser, PHONE_DESC_DAMITH, expectedFindCommand);
    }
}
