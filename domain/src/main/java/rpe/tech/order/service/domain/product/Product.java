package rpe.tech.order.service.domain.product;

import rpe.tech.order.service.domain.AggregateRoot;
import rpe.tech.order.service.domain.exceptions.NotificationException;
import rpe.tech.order.service.domain.utils.InstantUtils;
import rpe.tech.order.service.domain.validation.ValidationHandler;
import rpe.tech.order.service.domain.validation.handler.Notification;

import java.math.BigDecimal;
import java.time.Instant;

public class Product extends AggregateRoot<ProductID> {

    private final Instant createdAt;
    private String name;
    private BigDecimal price;
    private boolean active;
    private Instant updatedAt;
    private Instant deletedAt;

    protected Product(
            final ProductID anId,
            final String aName,
            final BigDecimal aPrice,
            final boolean isActive,
            final Instant aCreatedAt,
            final Instant anUpdatedAt,
            final Instant aDeletedAt
    ) {
        super(anId);
        this.name = aName;
        this.price = aPrice;
        this.active = isActive;
        this.createdAt = aCreatedAt;
        this.updatedAt = anUpdatedAt;
        this.deletedAt = aDeletedAt;
        selfValidate();
    }

    public static Product newProduct(
            final String aName,
            final BigDecimal aPrice,
            final boolean isActive
    ) {
        final var anId = ProductID.unique();
        final var now = InstantUtils.now();
        final var deletedAt = isActive ? null : now;

        return new Product(anId, aName, aPrice, isActive, now, now, deletedAt);
    }

    public static Product with(
            final ProductID anId,
            final String aName,
            final BigDecimal aPrice,
            final boolean isActive,
            final Instant aCreatedAt,
            final Instant anUpdatedAt,
            final Instant aDeletedAt
    ) {
        return new Product(anId, aName, aPrice, isActive, aCreatedAt, anUpdatedAt, aDeletedAt);
    }

    public static Product with(final Product aProduct) {
        return new Product(
                aProduct.id(),
                aProduct.name(),
                aProduct.price(),
                aProduct.isActive(),
                aProduct.createdAt(),
                aProduct.updatedAt(),
                aProduct.deletedAt()
        );
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new ProductValidator(this, handler).validate();
    }

    public Product update(
            final String aName,
            final BigDecimal aPrice,
            final boolean isActive
    ) {
        if (isActive) activate();
        else deactivate();

        this.name = aName;
        this.price = aPrice;
        this.active = isActive;
        this.updatedAt = InstantUtils.now();
        selfValidate();
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

    public BigDecimal price() {
        return price;
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
            throw new NotificationException("Failed to create a Aggregate Product", notification);
        }
    }
}
