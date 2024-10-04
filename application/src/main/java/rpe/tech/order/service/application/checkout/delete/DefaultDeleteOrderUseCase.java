package rpe.tech.order.service.application.checkout.delete;

import rpe.tech.order.service.domain.checkout.Order;
import rpe.tech.order.service.domain.checkout.OrderGateway;
import rpe.tech.order.service.domain.checkout.OrderID;
import rpe.tech.order.service.domain.exceptions.NotFoundException;

import java.util.Objects;

public class DefaultDeleteOrderUseCase extends DeleteOrderUseCase {

    private final OrderGateway orderGateway;

    public DefaultDeleteOrderUseCase(final OrderGateway orderGateway) {
        this.orderGateway = Objects.requireNonNull(orderGateway);
    }

    @Override
    public void execute(final DeleteOrderCommand aCmd) {
        final var anId = OrderID.from(aCmd.id());

        final var anOrder = this.orderGateway.findById(anId).orElseThrow(() -> notFound(anId));
        anOrder.closing();

        this.orderGateway.deleteById(anId);
    }

    private NotFoundException notFound(final OrderID anId) {
        return NotFoundException.with(Order.class, anId);
    }
}
