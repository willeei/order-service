package rpe.tech.order.service.application.customer.delete;

import rpe.tech.order.service.domain.customer.CustomerGateway;
import rpe.tech.order.service.domain.customer.CustomerID;

import java.util.Objects;

public class DefaultDeleteCustomerUseCase extends DeleteCustomerUseCase {

    private final CustomerGateway customerGateway;

    public DefaultDeleteCustomerUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public void execute(final String anIn) {
        this.customerGateway.deleteById(CustomerID.from(anIn));
    }
}
