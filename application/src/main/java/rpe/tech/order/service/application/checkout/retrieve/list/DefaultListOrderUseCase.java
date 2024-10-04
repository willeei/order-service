package rpe.tech.order.service.application.checkout.retrieve.list;

import rpe.tech.order.service.domain.checkout.OrderGateway;
import rpe.tech.order.service.domain.pagination.Pagination;
import rpe.tech.order.service.domain.pagination.SearchQuery;

import java.util.Objects;

public class DefaultListOrderUseCase extends ListOrderUseCase {

    private final OrderGateway orderGateway;

    public DefaultListOrderUseCase(final OrderGateway orderGateway) {
        this.orderGateway = Objects.requireNonNull(orderGateway);
    }

    @Override
    public Pagination<OrderListOutput> execute(final SearchQuery aQuery) {
        return this.orderGateway.findAll(aQuery)
                .map(OrderListOutput::from);
    }
}
