package rpe.tech.order.service.application.checkout.update;

import rpe.tech.order.service.domain.checkout.Order;

public record UpdateOrderOutput(String id) {

    public static UpdateOrderOutput from(final Order anOrder) {
        return new UpdateOrderOutput(anOrder.id().getValue());
    }
}
