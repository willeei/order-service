package rpe.tech.order.service.infrastructure.checkout.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import rpe.tech.order.service.domain.checkout.OrderItem;

import java.math.BigDecimal;

public record OrderItemIO(
        @JsonProperty("name") String name,
        @JsonProperty("product_id") String productId,
        @JsonProperty("quantity") Integer quantity,
        @JsonProperty("price") BigDecimal price
) {

    public static OrderItemIO from(final OrderItem anItem) {
        return new OrderItemIO(
                anItem.name(),
                anItem.productId(),
                anItem.quantity(),
                anItem.price()
        );
    }
}
