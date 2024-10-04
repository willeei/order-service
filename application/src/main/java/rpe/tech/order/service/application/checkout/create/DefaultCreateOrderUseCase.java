package rpe.tech.order.service.application.checkout.create;

import rpe.tech.order.service.domain.checkout.Order;
import rpe.tech.order.service.domain.checkout.OrderGateway;
import rpe.tech.order.service.domain.exceptions.NotificationException;
import rpe.tech.order.service.domain.validation.handler.Notification;

import java.util.Objects;

public class DefaultCreateOrderUseCase extends CreateOrderUseCase {

    private final OrderGateway orderGateway;

    public DefaultCreateOrderUseCase(final OrderGateway orderGateway) {
        this.orderGateway = Objects.requireNonNull(orderGateway);
    }

    @Override
    public CreateOrderOutput execute(final CreateOrderCommand aCmd) {

        final var notification = Notification.create();

        final var anOrder = notification.validate(() -> Order.newOrder(
                aCmd.customerId(),
                aCmd.items()
        ));

        if (notification.hasError()) {
            notify(notification);
        }

        return CreateOrderOutput.from(this.orderGateway.create(anOrder));
    }

    private void notify(Notification notification) {
        throw new NotificationException("Could not create Aggregate Order", notification);
    }
}
