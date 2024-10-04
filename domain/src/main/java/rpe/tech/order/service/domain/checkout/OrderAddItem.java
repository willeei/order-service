package rpe.tech.order.service.domain.checkout;

import rpe.tech.order.service.domain.event.DomainEvent;
import rpe.tech.order.service.domain.utils.InstantUtils;

import java.time.Instant;
import java.util.List;

public record OrderAddItem(
        String id,
        List<OrderItem> items,
        Instant occurredOn
) implements DomainEvent {

    public OrderAddItem(final String id, final List<OrderItem> items) {
        this(id, items, InstantUtils.now());
    }
}
