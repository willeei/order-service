package rpe.tech.order.service.domain.checkout;

import rpe.tech.order.service.domain.event.DomainEvent;
import rpe.tech.order.service.domain.utils.InstantUtils;

import java.time.Instant;
import java.util.List;

public record OrderRemoveItem(
        String id,
        OrderItem item,
        Instant occurredOn
) implements DomainEvent {

    public OrderRemoveItem(final String id, final OrderItem item) {
        this(id, item, InstantUtils.now());
    }
}
