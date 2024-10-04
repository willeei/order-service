package rpe.tech.order.service.application.customer.retrieve.list;

import rpe.tech.order.service.domain.customer.CustomerGateway;
import rpe.tech.order.service.domain.pagination.Pagination;
import rpe.tech.order.service.domain.pagination.SearchQuery;

import java.util.Objects;

public class DefaultListCustomerUseCase extends ListCustomerUseCase {

    private final CustomerGateway customerGateway;

    public DefaultListCustomerUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public Pagination<CustomerListOutput> execute(final SearchQuery aQuery) {
        return this.customerGateway.findAll(aQuery)
                .map(CustomerListOutput::from);
    }
}
