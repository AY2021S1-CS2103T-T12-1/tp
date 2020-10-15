package seedu.address.model.delivery;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a delivery in the delivery book
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Delivery {

    //Identity fields
    private final DeliveryName name;
    private final Phone phone;

    //Data fields
    private final Address address;
    private final Order order;

    /**
     * Every field must be present and not null.
     */
    public Delivery(DeliveryName name, Phone phone, Address address, Order order) {
        requireAllNonNull(name, phone, address, order);
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.order = order;
    }

    public DeliveryName getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }

    public Order getOrder() {
        return order;
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameDelivery(Delivery otherDelivery) {
        if (otherDelivery == this) {
            return true;
        }

        return otherDelivery != null
                && otherDelivery.getName().equals(getName())
                && otherDelivery.getPhone().equals(getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, address, order);
    }

    /**
     * Returns true if both delivery have the same identity and data fields.
     * This defines a stronger notion of equality between two delivery.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Delivery)) {
            return false;
        }

        Delivery otherDelivery = (Delivery) other;
        return otherDelivery.getName().equals(getName())
                && otherDelivery.getPhone().equals(getPhone())
                && otherDelivery.getAddress().equals(getAddress())
                && otherDelivery.getOrder().equals(getOrder());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Address: ")
                .append(getAddress())
                .append(" Order: ")
                .append(getOrder());
        return builder.toString();

    }

}
