package rpe.tech.order.service.domain.customer;

import rpe.tech.order.service.domain.Identifier;
import rpe.tech.order.service.domain.utils.IdUtils;

import java.util.Objects;

public class CustomerID extends Identifier {

    private final String value;

    public CustomerID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static CustomerID unique() {
        return CustomerID.from(IdUtils.uuid());
    }

    public static CustomerID from(final String anId) {
        return new CustomerID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final CustomerID CustomerID = (CustomerID) o;
        return getValue().equals(CustomerID.getValue());
    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }
}
