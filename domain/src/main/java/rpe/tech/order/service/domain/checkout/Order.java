package rpe.tech.order.service.domain.checkout;

import rpe.tech.order.service.domain.AggregateRoot;
import rpe.tech.order.service.domain.event.DomainEvent;
import rpe.tech.order.service.domain.exceptions.DomainException;
import rpe.tech.order.service.domain.exceptions.NotificationException;
import rpe.tech.order.service.domain.utils.InstantUtils;
import rpe.tech.order.service.domain.validation.Error;
import rpe.tech.order.service.domain.validation.ValidationHandler;
import rpe.tech.order.service.domain.validation.handler.Notification;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order extends AggregateRoot<OrderID> {

    private final String customerId;
    private final Instant createdAt;
    private List<OrderItem> items;
    private boolean opened;
    private Instant updatedAt;
    private Instant deletedAt;

    protected Order(
            final OrderID anId,
            final String aCustomerId,
            final boolean isOpened,
            final Instant aCreatedAt,
            final Instant anUpdatedAt,
            final Instant aDeletedAt,
            final List<OrderItem> items,
            final List<DomainEvent> events
    ) {
        super(anId, events);
        this.customerId = aCustomerId;
        this.items = items;
        this.opened = isOpened;
        this.createdAt = aCreatedAt;
        this.updatedAt = anUpdatedAt;
        this.deletedAt = aDeletedAt;
        selfValidate();
    }

    public static Order newOrder(
            final String aCustomerId,
            final List<OrderItem> items
    ) {
        final var anId = OrderID.unique();
        final var now = InstantUtils.now();
        final var order = new Order(anId, aCustomerId, true, now, now, null, items, null);
        order.onOrderCreated();
        return order;
    }

    public static Order with(
            final OrderID anId,
            final String aCustomerId,
            final boolean isOpened,
            final Instant aCreatedAt,
            final Instant anUpdatedAt,
            final Instant aDeletedAt,
            final List<OrderItem> items
    ) {
        return new Order(anId, aCustomerId, isOpened, aCreatedAt, anUpdatedAt, aDeletedAt, items, null);
    }

    public static Order with(final Order anOrder) {
        return new Order(
                anOrder.id(),
                anOrder.customerId(),
                anOrder.isOpened(),
                anOrder.createdAt(),
                anOrder.updatedAt(),
                anOrder.deletedAt(),
                anOrder.items(),
                anOrder.domainEvents()
        );
    }

    @Override
    public void validate(ValidationHandler handler) {
        new OrderValidator(this, handler).validate();
    }

    public Order addItems(List<OrderItem> items) {
        if (items == null) {
            throw DomainException.with(new Error("Order items should not be null"));
        }

        final var newItems = new ArrayList<>(this.items);
        newItems.addAll(items);

        this.items = newItems;
        onOrderItemAdded(newItems);
        return this;
    }

    public Order removeItem(final OrderItem item) {
        if (items == null) {
            throw DomainException.with(new Error("Order items should not be null"));
        }

        items.remove(item);
        onOrderItemRemoved(item);
        return this;
    }

    public Integer totalItems() {
        final var quantity = items.stream().map(OrderItem::quantity).reduce(0, Integer::sum);
        return items.size() * quantity;
    }

    public BigDecimal total() {
        return items.stream().map(OrderItem::orderItemTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Order closing() {
        this.opened = false;
        final var now = InstantUtils.now();
        this.updatedAt = now;
        this.deletedAt = now;
        onClosing();
        return this;
    }

    public String customerId() {
        return customerId;
    }

    public boolean isOpened() {
        return opened;
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

    public List<OrderItem> items() {
        return Collections.unmodifiableList(items);
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);

        if (notification.hasError()) {
            throw new NotificationException("Failed to create a Aggregate Order", notification);
        }
    }

    private void onOrderCreated() {
        if (this.isOpened()) {
            this.registerEvent(new OrderCreated(
                    this.id().getValue(),
                    this.customerId(),
                    this.items(),
                    this.createdAt()
            ));
        }
    }

    private void onOrderItemAdded(final List<OrderItem> newItems) {
        if (this.isOpened()) {
            this.registerEvent(new OrderAddItem(
                    this.id().getValue(),
                    newItems,
                    InstantUtils.now()
            ));
        }
    }

    private void onOrderItemRemoved(final OrderItem item) {
        if (this.isOpened()) {
            this.registerEvent(new OrderRemoveItem(
                    this.id().getValue(),
                    item,
                    InstantUtils.now()
            ));
        }
    }

    private void onClosing() {
        if (!this.isOpened()) {
            this.registerEvent(new OrderClosed(
                    this.id().getValue(),
                    this.customerId(),
                    this.items(),
                    this.isOpened(),
                    InstantUtils.now()
            ));
        }
    }
}
