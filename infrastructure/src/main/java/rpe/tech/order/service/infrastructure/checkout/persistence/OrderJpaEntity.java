package rpe.tech.order.service.infrastructure.checkout.persistence;

import jakarta.persistence.*;
import rpe.tech.order.service.domain.checkout.Order;
import rpe.tech.order.service.domain.checkout.OrderID;
import rpe.tech.order.service.infrastructure.customer.persistence.CustomerJpaEntity;

import java.time.Instant;
import java.util.List;

@Entity(name = "Order")
@Table(name = "orders")
public class OrderJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private CustomerJpaEntity customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemJpaEntity> items;

    @Column(name = "opened", nullable = false, columnDefinition = "boolean default true")
    private Boolean opened;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "deleted_at", columnDefinition = "DATETIME(6)")
    private Instant deletedAt;

    public OrderJpaEntity() {
    }

    private OrderJpaEntity(
            final String id,
            final CustomerJpaEntity customer,
            final List<OrderItemJpaEntity> items,
            final Boolean opened,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        this.id = id;
        this.customer = customer;
        this.items = items;
        this.opened = opened;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static OrderJpaEntity from(final Order anOrder) {
        return new OrderJpaEntity(
                anOrder.id().getValue(),
                CustomerJpaEntity.with(anOrder.customerId()),
                anOrder.items().stream().map(OrderItemJpaEntity::from).toList(),
                anOrder.isOpened(),
                anOrder.createdAt(),
                anOrder.updatedAt(),
                anOrder.deletedAt()
        );
    }

    public Order toAggregate() {
        return Order.with(
                OrderID.from(id),
                customer.id(),
                opened(),
                createdAt,
                updatedAt,
                deletedAt,
                items.stream().map(OrderItemJpaEntity::toAggregate).toList()
        );
    }

    public String id() {
        return id;
    }

    public OrderJpaEntity setId(String id) {
        this.id = id;
        return this;
    }

    public CustomerJpaEntity customer() {
        return customer;
    }

    public OrderJpaEntity setCustomer(CustomerJpaEntity customer) {
        this.customer = customer;
        return this;
    }

    public List<OrderItemJpaEntity> items() {
        return items;
    }

    public OrderJpaEntity setItems(List<OrderItemJpaEntity> items) {
        this.items = items;
        return this;
    }

    public Boolean opened() {
        return opened;
    }

    public OrderJpaEntity setOpened(Boolean opened) {
        this.opened = opened;
        return this;
    }

    public Instant createdAt() {
        return createdAt;
    }

    public OrderJpaEntity setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Instant updatedAt() {
        return updatedAt;
    }

    public OrderJpaEntity setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Instant deletedAt() {
        return deletedAt;
    }

    public OrderJpaEntity setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }
}
