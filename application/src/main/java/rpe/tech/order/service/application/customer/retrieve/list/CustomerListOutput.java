package rpe.tech.order.service.application.customer.retrieve.list;

import rpe.tech.order.service.domain.customer.Customer;

public record CustomerListOutput(
        String id,
        String name,
        boolean isActive,
        int rewardPoints
) {

    public static CustomerListOutput from(final Customer aCustomer) {
        return new CustomerListOutput(
                aCustomer.id().getValue(),
                aCustomer.name(),
                aCustomer.isActive(),
                aCustomer.rewardPoints()
        );
    }
}
