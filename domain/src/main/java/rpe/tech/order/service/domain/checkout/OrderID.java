package rpe.tech.order.service.domain.checkout;

import rpe.tech.order.service.domain.Identifier;
import rpe.tech.order.service.domain.utils.IdUtils;

import java.util.Objects;

public class OrderID extends Identifier {

    private final String value;

    public OrderID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static OrderID unique() {
        return OrderID.from(IdUtils.uuid());
    }

    public static OrderID from(final String anId) {
        return new OrderID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final OrderID orderID = (OrderID) o;
        return getValue().equals(orderID.getValue());
    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }
}
