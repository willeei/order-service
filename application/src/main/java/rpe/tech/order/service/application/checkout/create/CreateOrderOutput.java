package rpe.tech.order.service.application.checkout.create;

import rpe.tech.order.service.domain.checkout.Order;

public record CreateOrderOutput(String id) {

    public static CreateOrderOutput from(final Order anOrder) {
        return new CreateOrderOutput(anOrder.id().getValue());
    }
}
