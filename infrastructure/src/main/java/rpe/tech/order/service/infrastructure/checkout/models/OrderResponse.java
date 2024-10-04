package rpe.tech.order.service.infrastructure.checkout.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderResponse(
        @JsonProperty("id") String id,
        @JsonProperty("customer_id") String customer,
        @JsonProperty("items") List<OrderItemIO> items,
        @JsonProperty("total_amount") BigDecimal totalAmount,
        @JsonProperty("is_opened") Boolean opened,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt,
        @JsonProperty("deleted_at") Instant deletedAt
) {

}
