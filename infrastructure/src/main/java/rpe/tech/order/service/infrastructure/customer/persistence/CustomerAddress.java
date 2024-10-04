package rpe.tech.order.service.infrastructure.customer.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import rpe.tech.order.service.domain.customer.Address;

@Embeddable
public class CustomerAddress {

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "number")
    private Integer number;

    @Column(name = "zip_code")
    private String zipCode;

    public CustomerAddress() {
    }

    private CustomerAddress(
            final String aStreet,
            final String aCity,
            final Integer aNumber,
            final String aZipCode
    ) {
        this.street = aStreet;
        this.city = aCity;
        this.number = aNumber;
        this.zipCode = aZipCode;
    }

    public static CustomerAddress from(final Address anAddress) {
        return new CustomerAddress(
                anAddress.street(),
                anAddress.city(),
                anAddress.number(),
                anAddress.zipCode()
        );
    }

    public Address toDomain() {
        return Address.with(
                street(),
                number(),
                city(),
                zipCode()
        );
    }

    public String street() {
        return street;
    }

    public CustomerAddress setStreet(final String street) {
        this.street = street;
        return this;
    }

    public String city() {
        return city;
    }

    public CustomerAddress setCity(final String city) {
        this.city = city;
        return this;
    }

    public Integer number() {
        return number;
    }

    public CustomerAddress setNumber(final Integer number) {
        this.number = number;
        return this;
    }

    public String zipCode() {
        return zipCode;
    }

    public CustomerAddress setZipCode(final String zipCode) {
        this.zipCode = zipCode;
        return this;
    }
}
