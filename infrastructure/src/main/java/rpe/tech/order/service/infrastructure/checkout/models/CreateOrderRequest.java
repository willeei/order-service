package rpe.tech.order.service.infrastructure.checkout.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CreateOrderRequest(
        @JsonProperty("customer_id") String customerId,
        @JsonProperty("items") List<OrderItemIO> items
) {

}
