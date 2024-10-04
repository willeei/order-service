package rpe.tech.order.service.application.checkout.retrieve.list;

import rpe.tech.order.service.domain.checkout.Order;
import rpe.tech.order.service.domain.checkout.OrderPreview;

import java.math.BigDecimal;
import java.time.Instant;

public record OrderListOutput(
        String id,
        String customerId,
        boolean isOpened,
        Integer totalItems,
        BigDecimal totalAmount,
        Instant createdAt,
        Instant deletedAt
) {

    public static OrderListOutput from(final Order anOrder) {
        return new OrderListOutput(
                anOrder.id().getValue(),
                anOrder.customerId(),
                anOrder.isOpened(),
                anOrder.totalItems(),
                anOrder.total(),
                anOrder.createdAt(),
                anOrder.deletedAt()
        );
    }

    public static OrderListOutput from(final OrderPreview anOrder) {
        return new OrderListOutput(
                anOrder.id(),
                anOrder.customerId(),
                anOrder.isOpened(),
                null,
                null,
                anOrder.createdAt(),
                anOrder.deletedAt()
        );
    }
}
