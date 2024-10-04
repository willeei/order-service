package rpe.tech.order.service.domain.customer;

import rpe.tech.order.service.domain.ValueObject;

import java.util.Objects;

public class Address implements ValueObject {

    private final String street;
    private final Integer number;
    private final String city;
    private final String zipCode;

    private Address(
            final String street,
            final Integer number,
            final String city,
            final String zipCode
    ) {
        this.street = Objects.requireNonNull(street, "street is required");
        this.number = Objects.requireNonNull(number, "number is required");
        this.city = Objects.requireNonNull(city, "city is required");
        this.zipCode = Objects.requireNonNull(zipCode, "zipCode is required");
    }

    public static Address with(
            final String street,
            final Integer number,
            final String city,
            final String zipCode
    ) {
        return new Address(street, number, city, zipCode);
    }

    public String street() {
        return street;
    }

    public int number() {
        return number;
    }

    public String city() {
        return city;
    }

    public String zipCode() {
        return zipCode;
    }
}
