package seedu.address.logic.commands.deliverycommand;

import static seedu.address.model.deliverymodel.DeliveryModel.PREDICATE_SHOW_ALL_DELIVERIES;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.deliverymodel.DeliveryModel;

/**
 * Lists all items in the delivery book to the user.
 */
public class DeliveryListCommand extends DeliveryCommand {

    public static final String COMMAND_WORD = "list-d";

    public static final String MESSAGE_SUCCESS = "Listed all items";


    @Override
    public CommandResult execute(List<Model> models) throws CommandException {
        DeliveryModel deliveryModel = getDeliveryModel(models);

        deliveryModel.updateFilteredDeliveryList(PREDICATE_SHOW_ALL_DELIVERIES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
