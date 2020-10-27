package seedu.address.model.delivery.comparator;

import seedu.address.model.delivery.Delivery;

import java.util.Comparator;

public class DeliveryEndTimeComparator implements Comparator<Delivery> {
    @Override
    public int compare(Delivery delivery1, Delivery delivery2) {

        if (delivery1.getEndTime().isBefore(delivery2.getEndTime())) {
            return -1;
        } else if (delivery1.getEndTime().isAfter(delivery2.getEndTime())) {
            return 1;
        } else {
            return 0;
        }
    }
}
