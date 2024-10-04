package rpe.tech.order.service.application.customer.update;

import rpe.tech.order.service.domain.Identifier;
import rpe.tech.order.service.domain.customer.Address;
import rpe.tech.order.service.domain.customer.Customer;
import rpe.tech.order.service.domain.customer.CustomerGateway;
import rpe.tech.order.service.domain.customer.CustomerID;
import rpe.tech.order.service.domain.exceptions.DomainException;
import rpe.tech.order.service.domain.exceptions.NotFoundException;
import rpe.tech.order.service.domain.exceptions.NotificationException;
import rpe.tech.order.service.domain.validation.handler.Notification;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultUpdateCustomerUseCase extends UpdateCustomerUseCase {

    private final CustomerGateway customerGateway;

    public DefaultUpdateCustomerUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public UpdateCustomerOutput execute(final UpdateCustomerCommand aCmd) {
        final var anId = CustomerID.from(aCmd.id());
        final var name = aCmd.name();
        final var rewardPoints = aCmd.rewardPoints();
        final var active = aCmd.active();
        final var address = Address.with(aCmd.street(), aCmd.number(), aCmd.city(), aCmd.zipCode());

        final var aCustomer = this.customerGateway.findById(anId)
                .orElseThrow(notFound(anId));

        final var notification = Notification.create();
        notification.validate(() ->
                aCustomer.changeAddress(address)
                        .changeName(name)
                        .addRewardPoints(rewardPoints)
                        .changeActive(active)
        );

        if (notification.hasError()) {
            notify(notification);
        }

        return UpdateCustomerOutput.from(this.customerGateway.update(aCustomer));
    }

    private void notify(Notification notification) {
        throw new NotificationException("Could not update Aggregate Customer", notification);
    }

    private Supplier<DomainException> notFound(final Identifier anId) {
        return () -> NotFoundException.with(Customer.class, anId);
    }
}
