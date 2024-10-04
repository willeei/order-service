package rpe.tech.order.service.infrastructure.checkout.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("COMPLETED")
public record OrderClosingCompleted(
        @JsonProperty("id") String id,
        @JsonProperty("customerId") String customerId
) implements OrderClosingResult {

    public static final String CLOSED = "CLOSED";

    @Override
    public String getStatus() {
        return CLOSED;
    }
}
