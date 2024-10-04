package rpe.tech.order.service.infrastructure.customer.persistence;

import jakarta.persistence.*;
import rpe.tech.order.service.domain.customer.Customer;
import rpe.tech.order.service.domain.customer.CustomerID;

import java.time.Instant;

@Entity(name = "Customer")
@Table(name = "customers")
public class CustomerJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "reward_points")
    private Integer rewardPoints;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Embedded
    private CustomerAddress address;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "deleted_at", columnDefinition = "DATETIME(6)")
    private Instant deletedAt;

    public CustomerJpaEntity() {
    }

    private CustomerJpaEntity(
            final String anId,
            final String aName,
            final CustomerAddress anAddress,
            final Integer aRewardPoints,
            final boolean isActive,
            final Instant aCreatedAt,
            final Instant anUpdatedAt,
            final Instant aDeletedAt
    ) {
        this.id = anId;
        this.name = aName;
        this.address = anAddress;
        this.rewardPoints = aRewardPoints;
        this.active = isActive;
        this.createdAt = aCreatedAt;
        this.updatedAt = anUpdatedAt;
        this.deletedAt = aDeletedAt;
    }

    public static CustomerJpaEntity from(final Customer aCustomer) {
        return new CustomerJpaEntity(
                aCustomer.id().getValue(),
                aCustomer.name(),
                CustomerAddress.from(aCustomer.address()),
                aCustomer.rewardPoints(),
                aCustomer.isActive(),
                aCustomer.createdAt(),
                aCustomer.updatedAt(),
                aCustomer.deletedAt()
        );
    }

    public static CustomerJpaEntity with(final String anId) {
        return new CustomerJpaEntity(
                anId,
                null,
                null,
                null,
                false,
                null,
                null,
                null
        );
    }

    public Customer toAggregate() {
        return Customer.with(
                CustomerID.from(this.id),
                this.name,
                this.address.toDomain(),
                this.rewardPoints,
                this.active,
                this.createdAt,
                this.updatedAt,
                this.deletedAt
        );
    }

    public String id() {
        return id;
    }

    public CustomerJpaEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String name() {
        return name;
    }

    public CustomerJpaEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Integer rewardPoints() {
        return rewardPoints;
    }

    public CustomerJpaEntity setRewardPoints(Integer rewardPoints) {
        this.rewardPoints = rewardPoints;
        return this;
    }

    public Boolean active() {
        return active;
    }

    public CustomerJpaEntity setActive(Boolean active) {
        this.active = active;
        return this;
    }

    public CustomerAddress address() {
        return address;
    }

    public CustomerJpaEntity setAddress(CustomerAddress address) {
        this.address = address;
        return this;
    }

    public Instant createdAt() {
        return createdAt;
    }

    public CustomerJpaEntity setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Instant updatedAt() {
        return updatedAt;
    }

    public CustomerJpaEntity setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Instant deletedAt() {
        return deletedAt;
    }

    public CustomerJpaEntity setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }
}
