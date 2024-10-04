package rpe.tech.order.service.domain.product;

import rpe.tech.order.service.domain.Identifier;
import rpe.tech.order.service.domain.utils.IdUtils;

import java.util.Objects;

public class ProductID extends Identifier {

    private final String value;

    public ProductID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static ProductID unique() {
        return ProductID.from(IdUtils.uuid());
    }

    public static ProductID from(final String anId) {
        return new ProductID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final ProductID productID = (ProductID) o;
        return getValue().equals(productID.getValue());
    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }
}
