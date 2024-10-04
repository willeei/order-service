package rpe.tech.order.service.infrastructure.checkout.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record UpdateOrderRequest(
        @JsonProperty("items") List<OrderItemIO> items
) {

}
