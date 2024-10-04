package rpe.tech.order.service.application.checkout.update;

import rpe.tech.order.service.domain.checkout.OrderItem;

import java.util.List;

public record UpdateOrderCommand(
        String id,
        List<OrderItem> items
) {

    public static UpdateOrderCommand with(
            String id,
            List<OrderItem> items
    ) {
        return new UpdateOrderCommand(id, items);
    }
}
