package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.delivery.Delivery;
import seedu.address.model.deliverymodel.DeliveryBook;
import seedu.address.model.deliverymodel.ReadOnlyDeliveryBook;
import seedu.address.model.inventorymodel.InventoryBook;
import seedu.address.model.inventorymodel.ReadOnlyInventoryBook;
import seedu.address.model.item.Item;
import seedu.address.model.item.Metric;
import seedu.address.model.item.Name;
import seedu.address.model.item.Quantity;
import seedu.address.model.item.Supplier;
import seedu.address.model.item.Tag;

/**
 * Contains utility methods for populating {@code InventoryBook} with sample data.
 */
public class SampleDataUtil {
    public static Item[] getSampleItems() {
        return new Item[] {
            new Item(new Name("Chicken"),
                new Quantity("12"),
                new Supplier("NTUC"),
                getTagSet("meat"),
                new Quantity("200"),
                new Metric("kg")),
            new Item(new Name("Duck"),
                new Quantity("33"),
                new Supplier("NTUC"),
                getTagSet("meat"),
                new Quantity("300"),
                new Metric("kg"))
        };
    }

    // TODO
    public static Delivery[] getSampleDeliveries() {
        return new Delivery[] { new Delivery() };
    }

    public static ReadOnlyDeliveryBook getSampleDeliveryBook() {
        DeliveryBook sampleDb = new DeliveryBook();
        for (Delivery sampleDelivery : getSampleDeliveries()) {
            sampleDb.addDelivery(sampleDelivery);
        }
        return sampleDb;
    }

    public static ReadOnlyInventoryBook getSampleInventoryBook() {
        InventoryBook sampleAb = new InventoryBook();
        for (Item sampleItem : getSampleItems()) {
            sampleAb.addItem(sampleItem);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
