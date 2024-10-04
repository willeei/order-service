package rpe.tech.order.service.application.customer.retrieve.get;

import rpe.tech.order.service.domain.customer.Customer;
import rpe.tech.order.service.domain.customer.CustomerGateway;
import rpe.tech.order.service.domain.customer.CustomerID;
import rpe.tech.order.service.domain.exceptions.NotFoundException;

import java.util.Objects;

public non-sealed class DefaultGetCustomerByIdUseCase extends GetCustomerByIdUseCase {

    private final CustomerGateway customerGateway;

    public DefaultGetCustomerByIdUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public CustomerOutput execute(final String anId) {
        final var aCustomerId = CustomerID.from(anId);
        return this.customerGateway.findById(aCustomerId)
                .map(CustomerOutput::from)
                .orElseThrow(() -> NotFoundException.with(Customer.class, aCustomerId));
    }

}
