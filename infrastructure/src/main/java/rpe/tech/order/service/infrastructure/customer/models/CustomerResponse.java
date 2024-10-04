package rpe.tech.order.service.infrastructure.customer.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record CustomerResponse(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("reward_points") Integer rewardPoints,
        @JsonProperty("is_active") Boolean isActive,
        @JsonProperty("address") AddressModel address,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt,
        @JsonProperty("deleted_at") Instant deletedAt
) {

}
