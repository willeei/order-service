package rpe.tech.order.service.domain.customer;

import rpe.tech.order.service.domain.AggregateRoot;
import rpe.tech.order.service.domain.exceptions.NotificationException;
import rpe.tech.order.service.domain.utils.InstantUtils;
import rpe.tech.order.service.domain.validation.ValidationHandler;
import rpe.tech.order.service.domain.validation.handler.Notification;

import java.time.Instant;

public class Customer extends AggregateRoot<CustomerID> {

    private final Instant createdAt;
    private String name;
    private Address address;
    private Integer rewardPoints;
    private boolean active;
    private Instant updatedAt;
    private Instant deletedAt;

    protected Customer(
            final CustomerID anId,
            final String aName,
            final Address anAddress,
            final Integer aRewardPoints,
            final boolean isActive,
            final Instant aCreatedAt,
            final Instant anUpdatedAt,
            final Instant aDeletedAt
    ) {
        super(anId);
        this.name = aName;
        this.address = anAddress;
        this.rewardPoints = aRewardPoints;
        this.active = isActive;
        this.createdAt = aCreatedAt;
        this.updatedAt = anUpdatedAt;
        this.deletedAt = aDeletedAt;
        selfValidate();
    }

    public static Customer newCustomer(
            final String aName,
            final Address anAddress,
            final Integer aRewardPoints,
            final boolean isActive
    ) {
        final var anId = CustomerID.unique();
        final var now = InstantUtils.now();
        final var deletedAt = isActive ? null : now;

        return new Customer(anId, aName, anAddress, aRewardPoints, isActive, now, now, deletedAt);
    }

    public static Customer with(
            final CustomerID anId,
            final String aName,
            final Address anAddress,
            final Integer aRewardPoints,
            final boolean isActive,
            final Instant aCreatedAt,
            final Instant anUpdatedAt,
            final Instant aDeletedAt
    ) {
        return new Customer(anId, aName, anAddress, aRewardPoints, isActive, aCreatedAt, anUpdatedAt, aDeletedAt);
    }

    public static Customer with(final Customer aCustomer) {
        return new Customer(
                aCustomer.id(),
                aCustomer.name(),
                aCustomer.address(),
                aCustomer.rewardPoints(),
                aCustomer.isActive(),
                aCustomer.createdAt(),
                aCustomer.updatedAt(),
                aCustomer.deletedAt()
        );
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new CustomerValidator(this, handler).validate();
    }

    public Customer changeName(final String aName) {
        this.name = aName;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Customer changeAddress(final Address anAddress) {
        this.address = anAddress;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Customer changeActive(final boolean isActive) {
        if (isActive) {
            activate();
        } else {
            deactivate();
        }
        return this;
    }

    public Customer addRewardPoints(final int aRewardPoints) {
        this.rewardPoints += aRewardPoints;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public void deactivate() {
        if (deletedAt() == null) {
            this.deletedAt = InstantUtils.now();
        }
        this.active = false;
        this.updatedAt = InstantUtils.now();
    }

    public void activate() {
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = InstantUtils.now();
    }

    public String name() {
        return name;
    }

    public Address address() {
        return address;
    }

    public Integer rewardPoints() {
        return rewardPoints;
    }

    public boolean isActive() {
        return active;
    }

    public Instant createdAt() {
        return createdAt;
    }

    public Instant updatedAt() {
        return updatedAt;
    }

    public Instant deletedAt() {
        return deletedAt;
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);

        if (notification.hasError()) {
            throw new NotificationException("Failed to create a Aggregate Customer", notification);
        }
    }
}
