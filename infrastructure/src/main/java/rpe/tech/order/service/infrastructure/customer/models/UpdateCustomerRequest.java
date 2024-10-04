package rpe.tech.order.service.infrastructure.customer.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateCustomerRequest(
        @JsonProperty("name") String name,
        @JsonProperty("address") AddressModel address,
        @JsonProperty("is_active") Boolean isActive,
        @JsonProperty("reward_points") Integer rewardPoints
) {

}
