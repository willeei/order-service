package rpe.tech.order.service.infrastructure.checkout.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record OrderListResponse(
        @JsonProperty("id") String id,
        @JsonProperty("customer_id") String customerId,
        @JsonProperty("is_opened") Boolean opened,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("deleted_at") Instant deletedAt
) {

}
