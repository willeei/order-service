package rpe.tech.order.service.application.checkout.retrieve.get;

import rpe.tech.order.service.domain.checkout.Order;
import rpe.tech.order.service.domain.checkout.OrderItem;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderOutput(
        String id,
        String customerId,
        boolean isOpened,
        List<OrderItem> items,
        BigDecimal total,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {

    public static OrderOutput from(final Order anOrder) {
        return new OrderOutput(
                anOrder.id().getValue(),
                anOrder.customerId(),
                anOrder.isOpened(),
                anOrder.items(),
                anOrder.total(),
                anOrder.createdAt(),
                anOrder.updatedAt(),
                anOrder.deletedAt()
        );
    }
}
