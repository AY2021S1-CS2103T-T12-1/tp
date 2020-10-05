package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import seedu.address.model.item.Item;

import java.util.Comparator;

/**
 * An UI component that displays information of a {@code Item}.
 */
public class ItemCard extends UiPart<Region> {

    private static final String FXML = "ItemListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Item item;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label quantity;
    @FXML
    private Text quantityStats;
    @FXML
    private Label supplier;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ItemCode} with the given {@code Item} and index to display.
     */
    public ItemCard(Item item, int displayedIndex) {
        super(FXML);
        this.item = item;
        String itemQuantity = item.getQuantity().value;
        String itemMaxQuantity = item.getMaxQuantity().value;

        id.setText(displayedIndex + ". ");
        name.setText(item.getName().fullName);
        quantity.setText(itemQuantity);
        if (Integer.parseInt(itemMaxQuantity) > 0) {
            float percentage = Float.parseFloat(itemQuantity) / Integer.parseInt(itemMaxQuantity) * 100;
            String stats = String.format("/%s (%.01f%%)", itemMaxQuantity, percentage);
            quantityStats.setText(stats);
            if (percentage > 100) {
                quantityStats.setFill(Color.RED);
            }
        }
        supplier.setText(item.getSupplier().value);
        item.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ItemCard)) {
            return false;
        }

        // state check
        ItemCard card = (ItemCard) other;
        return id.getText().equals(card.id.getText())
                && item.equals(card.item);
    }
}
