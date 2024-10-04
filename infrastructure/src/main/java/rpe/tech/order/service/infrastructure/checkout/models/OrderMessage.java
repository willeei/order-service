package rpe.tech.order.service.infrastructure.checkout.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderMessage(
        @JsonProperty("id") String id,
        @JsonProperty("customerId") String customerId
) {

}
