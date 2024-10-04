package rpe.tech.order.service.domain.checkout;

import rpe.tech.order.service.domain.ValueObject;
import rpe.tech.order.service.domain.utils.IdUtils;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderItem implements ValueObject {

    private final String id;
    private final String name;
    private final String productId;
    private final BigDecimal price;
    private final Integer quantity;

    private OrderItem(
            final String id,
            final String name,
            final String productId,
            final BigDecimal price,
            final Integer quantity
    ) {
        this.id = id;
        this.name = name;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public static OrderItem with(
            final String id,
            final String name,
            final String productId,
            final BigDecimal price,
            final Integer quantity
    ) {
        return new OrderItem(id, name, productId, price, quantity);
    }

    public static OrderItem with(
            final String name,
            final String productId,
            final BigDecimal price,
            final Integer quantity
    ) {
        return new OrderItem(IdUtils.uuid(), name, productId, price, quantity);
    }

    public BigDecimal orderItemTotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String productId() {
        return productId;
    }

    public BigDecimal price() {
        return price;
    }

    public Integer quantity() {
        return quantity;
    }
}
