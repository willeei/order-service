package rpe.tech.order.service.infrastructure.checkout.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("ERROR")
public record OrderClosingError(
        @JsonProperty("message") OrderMessage message,
        @JsonProperty("error") String error
) implements OrderClosingResult {

    public static final String ERROR = "ERROR";

    @Override
    public String getStatus() {
        return ERROR;
    }
}