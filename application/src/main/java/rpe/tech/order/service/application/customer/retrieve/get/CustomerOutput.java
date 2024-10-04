package rpe.tech.order.service.application.customer.retrieve.get;

import rpe.tech.order.service.domain.customer.Address;
import rpe.tech.order.service.domain.customer.Customer;

import java.time.Instant;

public record CustomerOutput(
        String id,
        String name,
        boolean isActive,
        int rewardPoints,
        Address address,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {

    public static CustomerOutput from(final Customer aCustomer) {
        return new CustomerOutput(
                aCustomer.id().getValue(),
                aCustomer.name(),
                aCustomer.isActive(),
                aCustomer.rewardPoints(),
                aCustomer.address(),
                aCustomer.createdAt(),
                aCustomer.updatedAt(),
                aCustomer.deletedAt()
        );
    }
}
