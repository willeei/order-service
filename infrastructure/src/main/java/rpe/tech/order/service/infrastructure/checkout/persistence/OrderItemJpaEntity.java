package rpe.tech.order.service.infrastructure.checkout.persistence;

import jakarta.persistence.*;
import rpe.tech.order.service.domain.checkout.OrderItem;

import java.math.BigDecimal;

@Entity(name = "OrderItem")
@Table(name = "order_items")
public class OrderItemJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderJpaEntity order;

    public OrderItemJpaEntity() {
    }

    private OrderItemJpaEntity(
            final String anId,
            final String aName,
            final BigDecimal aPrice,
            final Integer aQuantity,
            final String aProductId,
            final OrderJpaEntity anOrder
    ) {
        this.id = anId;
        this.name = aName;
        this.price = aPrice;
        this.quantity = aQuantity;
        this.productId = aProductId;
        this.order = anOrder;
    }

    public static OrderItemJpaEntity from(final OrderItem anOrderItem) {
        return new OrderItemJpaEntity(
                anOrderItem.id(),
                anOrderItem.name(),
                anOrderItem.price(),
                anOrderItem.quantity(),
                anOrderItem.productId(),
                null
        );
    }

    public OrderItem toAggregate() {
        return OrderItem.with(
                this.id,
                this.name,
                this.productId,
                this.price,
                this.quantity
        );
    }

    public String id() {
        return id;
    }

    public OrderItemJpaEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String name() {
        return name;
    }

    public OrderItemJpaEntity setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal price() {
        return price;
    }

    public OrderItemJpaEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Integer quantity() {
        return quantity;
    }

    public OrderItemJpaEntity setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public String product() {
        return productId;
    }

    public OrderItemJpaEntity setProduct(String productId) {
        this.productId = productId;
        return this;
    }

    public OrderJpaEntity order() {
        return order;
    }

    public OrderItemJpaEntity setOrder(OrderJpaEntity order) {
        this.order = order;
        return this;
    }
}