package rpe.tech.order.service.infrastructure.checkout.presenters;

import rpe.tech.order.service.application.checkout.retrieve.get.OrderOutput;
import rpe.tech.order.service.application.checkout.retrieve.list.OrderListOutput;
import rpe.tech.order.service.infrastructure.checkout.models.OrderItemIO;
import rpe.tech.order.service.infrastructure.checkout.models.OrderListResponse;
import rpe.tech.order.service.infrastructure.checkout.models.OrderResponse;

public interface OrderApiPresenter {

    static OrderResponse present(final OrderOutput output) {
        return new OrderResponse(
                output.id(),
                output.customerId(),
                output.items().stream().map(OrderItemIO::from).toList(),
                output.total(),
                output.isOpened(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static OrderListResponse present(final OrderListOutput output) {
        return new OrderListResponse(
                output.id(),
                output.customerId(),
                output.isOpened(),
                output.createdAt(),
                output.deletedAt()
        );
    }
}
