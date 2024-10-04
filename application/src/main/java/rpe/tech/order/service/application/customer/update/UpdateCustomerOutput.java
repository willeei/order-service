package rpe.tech.order.service.application.customer.update;

import rpe.tech.order.service.domain.customer.Customer;

public record UpdateCustomerOutput(String id) {

    public static UpdateCustomerOutput from(final Customer aCustomer) {
        return new UpdateCustomerOutput(aCustomer.id().getValue());
    }

}
