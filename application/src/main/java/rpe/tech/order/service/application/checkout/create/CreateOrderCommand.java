package rpe.tech.order.service.application.checkout.create;

import rpe.tech.order.service.domain.checkout.OrderItem;

import java.util.List;

public record CreateOrderCommand(
        String customerId,
        List<OrderItem> items
) {

    public static CreateOrderCommand with(
            final String customerId,
            final List<OrderItem> items
    ) {
        return new CreateOrderCommand(customerId, items);
    }
}
