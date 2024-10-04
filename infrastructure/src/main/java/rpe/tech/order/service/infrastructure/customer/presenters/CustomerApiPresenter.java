package rpe.tech.order.service.infrastructure.customer.presenters;

import rpe.tech.order.service.application.customer.retrieve.get.CustomerOutput;
import rpe.tech.order.service.application.customer.retrieve.list.CustomerListOutput;
import rpe.tech.order.service.infrastructure.customer.models.AddressModel;
import rpe.tech.order.service.infrastructure.customer.models.CustomerListResponse;
import rpe.tech.order.service.infrastructure.customer.models.CustomerResponse;

public interface CustomerApiPresenter {

    static CustomerResponse present(final CustomerOutput output) {
        return new CustomerResponse(
                output.id(),
                output.name(),
                output.rewardPoints(),
                output.isActive(),
                AddressModel.with(
                        output.address().street(),
                        output.address().number(),
                        output.address().city(),
                        output.address().zipCode()
                ),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static CustomerListResponse present(final CustomerListOutput output) {
        return new CustomerListResponse(
                output.id(),
                output.name(),
                output.rewardPoints(),
                output.isActive()
        );
    }
}
