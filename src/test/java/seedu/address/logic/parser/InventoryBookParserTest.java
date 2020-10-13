package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.itemcommand.ItemClearCommand;
import seedu.address.logic.commands.itemcommand.ItemDeleteCommand;
import seedu.address.logic.commands.itemcommand.ItemEditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.itemcommand.ItemFindCommand;
import seedu.address.logic.commands.itemcommand.ItemAddCommand;
import seedu.address.logic.commands.itemcommand.ItemListCommand;
import seedu.address.logic.commands.itemcommand.RemoveCommand;
import seedu.address.logic.commands.help.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemContainsKeywordsPredicate;
import seedu.address.testutil.EditItemDescriptorBuilder;
import seedu.address.testutil.ItemBuilder;
import seedu.address.testutil.ItemUtil;

public class InventoryBookParserTest {

    private final InventoryBookParser parser = new InventoryBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Item item = new ItemBuilder().build();
        ItemAddCommand command = (ItemAddCommand) parser.parseCommand(ItemUtil.getAddCommand(item));
        assertEquals(new ItemAddCommand(item), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ItemClearCommand.COMMAND_WORD) instanceof ItemClearCommand);
        assertTrue(parser.parseCommand(ItemClearCommand.COMMAND_WORD + " 3") instanceof ItemClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        ItemDeleteCommand command = (ItemDeleteCommand) parser.parseCommand(
                ItemDeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_ITEM.getOneBased());
        assertEquals(new ItemDeleteCommand(INDEX_FIRST_ITEM), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Item item = new ItemBuilder().build();
        ItemEditCommand.EditItemDescriptor descriptor = new EditItemDescriptorBuilder(item).build();
        ItemEditCommand command = (ItemEditCommand) parser.parseCommand(ItemEditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_ITEM.getOneBased() + " " + ItemUtil.getEditItemDescriptorDetails(descriptor));
        assertEquals(new ItemEditCommand(INDEX_FIRST_ITEM, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Collections.singletonList("CHICKEN");
        ItemFindCommand command = (ItemFindCommand) parser.parseCommand(
                ItemFindCommand.COMMAND_WORD + " " + "n/chicken");
        assertEquals(new ItemFindCommand(new ItemContainsKeywordsPredicate(keywords, PREFIX_NAME)), command);
    }

    @Test
    public void parseCommand_remove() throws Exception {
        assertTrue(parser.parseCommand(RemoveCommand.COMMAND_WORD + " 1 q/10") instanceof RemoveCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " " + HelpCommand.COMMAND_OPTION_SUMMARY)
                instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " " + HelpCommand.COMMAND_OPTION_START)
                instanceof HelpCommand);
        assertThrows(ParseException.class,
                HelpCommand.MESSAGE_INVALID_OPTION, () -> parser.parseCommand(HelpCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ItemListCommand.COMMAND_WORD) instanceof ItemListCommand);
        assertTrue(parser.parseCommand(ItemListCommand.COMMAND_WORD + " 3") instanceof ItemListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
