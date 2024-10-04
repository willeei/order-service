package rpe.tech.order.service.infrastructure.customer.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddressModel(
        @JsonProperty("street") String street,
        @JsonProperty("number") int number,
        @JsonProperty("city") String city,
        @JsonProperty("zip_code") String zipCode
) {

    public static AddressModel with(
            final String street,
            final int number,
            final String city,
            final String zipCode
    ) {
        return new AddressModel(street, number, city, zipCode);
    }
}
