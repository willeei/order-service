package rpe.tech.order.service.application.checkout.update;

import rpe.tech.order.service.domain.Identifier;
import rpe.tech.order.service.domain.checkout.OrderGateway;
import rpe.tech.order.service.domain.checkout.OrderID;
import rpe.tech.order.service.domain.customer.Customer;
import rpe.tech.order.service.domain.exceptions.DomainException;
import rpe.tech.order.service.domain.exceptions.NotFoundException;
import rpe.tech.order.service.domain.exceptions.NotificationException;
import rpe.tech.order.service.domain.validation.handler.Notification;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultUpdateOrderUseCase extends UpdateOrderUseCase {

    private final OrderGateway orderGateway;

    public DefaultUpdateOrderUseCase(final OrderGateway orderGateway) {
        this.orderGateway = Objects.requireNonNull(orderGateway);
    }

    @Override
    public UpdateOrderOutput execute(final UpdateOrderCommand aCmd) {
        final var anId = OrderID.from(aCmd.id());
        final var items = aCmd.items();

        final var anOrder = this.orderGateway.findById(anId)
                .orElseThrow(notFound(anId));

        final var notification = Notification.create();
        notification.validate(() ->
                anOrder.addItems(items)
        );

        if (notification.hasError()) {
            notify(notification);
        }

        return UpdateOrderOutput.from(this.orderGateway.update(anOrder));
    }

    private void notify(Notification notification) {
        throw new NotificationException("Could not update Aggregate Customer", notification);
    }

    private Supplier<DomainException> notFound(final Identifier anId) {
        return () -> NotFoundException.with(Customer.class, anId);
    }
}
