package rpe.tech.order.service.domain.checkout;

import rpe.tech.order.service.domain.event.DomainEvent;
import rpe.tech.order.service.domain.utils.InstantUtils;

import java.time.Instant;
import java.util.List;

public record OrderCreated(
        String id,
        String customerId,
        List<OrderItem> items,
        Instant occurredOn
) implements DomainEvent {

    public OrderCreated(final String id, final String customerId, final List<OrderItem> items) {
        this(id, customerId, items, InstantUtils.now());
    }
}
