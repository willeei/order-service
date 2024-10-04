package rpe.tech.order.service.application.customer.create;

import rpe.tech.order.service.domain.customer.Address;
import rpe.tech.order.service.domain.customer.Customer;
import rpe.tech.order.service.domain.customer.CustomerGateway;
import rpe.tech.order.service.domain.exceptions.NotificationException;
import rpe.tech.order.service.domain.validation.handler.Notification;

import java.util.Objects;

public class DefaultCreateCustomerUseCase extends CreateCustomerUseCase {

    private final CustomerGateway customerGateway;

    public DefaultCreateCustomerUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public CreateCustomerOutput execute(final CreateCustomerCommand aCmd) {
        final var address = Address.with(aCmd.street(), aCmd.number(), aCmd.city(), aCmd.zipCode());

        final var notification = Notification.create();

        final var aCustomer = notification.validate(() -> Customer.newCustomer(
                aCmd.name(),
                address,
                aCmd.rewardPoints(),
                aCmd.active()
        ));

        if (notification.hasError()) {
            notify(notification);
        }

        return CreateCustomerOutput.from(customerGateway.create(aCustomer));
    }

    private void notify(Notification notification) {
        throw new NotificationException("Could not create Aggregate Customer", notification);
    }
}
