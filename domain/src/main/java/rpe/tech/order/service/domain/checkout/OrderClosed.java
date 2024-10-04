package rpe.tech.order.service.domain.checkout;

import rpe.tech.order.service.domain.event.DomainEvent;
import rpe.tech.order.service.domain.utils.InstantUtils;

import java.time.Instant;
import java.util.List;

public record OrderClosed(
        String id,
        String customerId,
        List<OrderItem> items,
        boolean opened,
        Instant occurredOn
) implements DomainEvent {

    public OrderClosed(final String id, final String customerId, final List<OrderItem> items, boolean opened) {
        this(id, customerId, items, opened, InstantUtils.now());
    }
}
