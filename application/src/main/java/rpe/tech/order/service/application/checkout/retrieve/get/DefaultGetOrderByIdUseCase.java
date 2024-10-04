package rpe.tech.order.service.application.checkout.retrieve.get;

import rpe.tech.order.service.domain.checkout.OrderGateway;
import rpe.tech.order.service.domain.checkout.OrderID;
import rpe.tech.order.service.domain.customer.Customer;
import rpe.tech.order.service.domain.exceptions.NotFoundException;

import java.util.Objects;

public non-sealed class DefaultGetOrderByIdUseCase extends GetOrderByIdUseCase {

    private final OrderGateway orderGateway;

    public DefaultGetOrderByIdUseCase(final OrderGateway orderGateway) {
        this.orderGateway = Objects.requireNonNull(orderGateway);
    }

    @Override
    public OrderOutput execute(final String anId) {
        final var aCustomerId = OrderID.from(anId);
        return this.orderGateway.findById(aCustomerId)
                .map(OrderOutput::from)
                .orElseThrow(() -> NotFoundException.with(Customer.class, aCustomerId));
    }

}
