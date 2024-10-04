package rpe.tech.order.service.infrastructure.checkout.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderCustomerResponse(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("reward_points") Integer rewardPoints
) {

}
