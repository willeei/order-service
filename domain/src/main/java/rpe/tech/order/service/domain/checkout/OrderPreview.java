package rpe.tech.order.service.domain.checkout;

import java.time.Instant;

public record OrderPreview(
        String id,
        String customerId,
        Boolean isOpened,
        Instant createdAt,
        Instant deletedAt
) {

    public OrderPreview(final Order anOrder) {
        this(
                anOrder.id().getValue(),
                anOrder.customerId(),
                anOrder.isOpened(),
                anOrder.createdAt(),
                anOrder.deletedAt()
        );
    }
}
