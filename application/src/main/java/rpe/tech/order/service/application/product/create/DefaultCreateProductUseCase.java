package rpe.tech.order.service.application.product.create;

import rpe.tech.order.service.domain.exceptions.NotificationException;
import rpe.tech.order.service.domain.product.Product;
import rpe.tech.order.service.domain.product.ProductGateway;
import rpe.tech.order.service.domain.validation.handler.Notification;

import java.util.Objects;

public class DefaultCreateProductUseCase extends CreateProductUseCase {

    private final ProductGateway productGateway;

    public DefaultCreateProductUseCase(final ProductGateway productGateway) {
        this.productGateway = Objects.requireNonNull(productGateway);
    }

    @Override
    public CreateProductOutput execute(CreateProductCommand aCmd) {
        final var notification = Notification.create();

        final var aProduct =
                notification.validate(() -> Product.newProduct(aCmd.name(), aCmd.price(), aCmd.isActive()));

        if (notification.hasError()) {
            throw new NotificationException("Could not create Aggregate Product", notification);
        }

        return CreateProductOutput.from(this.productGateway.create(aProduct));
    }
}
