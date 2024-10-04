package rpe.tech.order.service.infrastructure.product.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import rpe.tech.order.service.domain.product.Product;
import rpe.tech.order.service.domain.product.ProductID;

import java.math.BigDecimal;
import java.time.Instant;

@Entity(name = "Product")
@Table(name = "products")
public class ProductJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "deleted_at", columnDefinition = "DATETIME(6)")
    private Instant deletedAt;

    public ProductJpaEntity() {
    }

    private ProductJpaEntity(
            final String anId,
            final String aName,
            final BigDecimal aPrice,
            final boolean isActive,
            final Instant aCreatedAt,
            final Instant anUpdatedAt,
            final Instant aDeletedAt
    ) {
        this.id = anId;
        this.name = aName;
        this.price = aPrice;
        this.active = isActive;
        this.createdAt = aCreatedAt;
        this.updatedAt = anUpdatedAt;
        this.deletedAt = aDeletedAt;
    }

    public static ProductJpaEntity from(final Product aProduct) {
        return new ProductJpaEntity(
                aProduct.id().getValue(),
                aProduct.name(),
                aProduct.price(),
                aProduct.isActive(),
                aProduct.createdAt(),
                aProduct.updatedAt(),
                aProduct.deletedAt()
        );
    }

    public Product toAggregate() {
        return Product.with(
                ProductID.from(this.id),
                this.name,
                this.price,
                this.active,
                this.createdAt,
                this.updatedAt,
                this.deletedAt
        );
    }

    public String id() {
        return id;
    }

    public ProductJpaEntity setId(final String id) {
        this.id = id;
        return this;
    }

    public String name() {
        return name;
    }

    public ProductJpaEntity setName(final String name) {
        this.name = name;
        return this;
    }

    public BigDecimal price() {
        return price;
    }

    public ProductJpaEntity setPrice(final BigDecimal price) {
        this.price = price;
        return this;
    }

    public boolean active() {
        return active;
    }

    public ProductJpaEntity setActive(final boolean active) {
        this.active = active;
        return this;
    }

    public Instant createdAt() {
        return createdAt;
    }

    public ProductJpaEntity setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Instant updatedAt() {
        return updatedAt;
    }

    public ProductJpaEntity setUpdatedAt(final Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Instant deletedAt() {
        return deletedAt;
    }

    public ProductJpaEntity setDeletedAt(final Instant deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }
}
