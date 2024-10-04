package rpe.tech.order.service.application.customer.create;

import rpe.tech.order.service.domain.customer.Customer;

public record CreateCustomerOutput(String id) {

    public static CreateCustomerOutput from(final Customer aCustomer) {
        return new CreateCustomerOutput(aCustomer.id().getValue());
    }

}
