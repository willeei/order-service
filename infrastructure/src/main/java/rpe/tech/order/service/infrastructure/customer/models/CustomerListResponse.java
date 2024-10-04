package rpe.tech.order.service.infrastructure.customer.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomerListResponse(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("reward_points") Integer rewardPoints,
        @JsonProperty("is_active") Boolean isActive
) {

}
