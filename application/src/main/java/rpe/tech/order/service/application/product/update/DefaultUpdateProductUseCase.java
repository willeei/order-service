package rpe.tech.order.service.application.product.update;

import rpe.tech.order.service.domain.Identifier;
import rpe.tech.order.service.domain.exceptions.DomainException;
import rpe.tech.order.service.domain.exceptions.NotFoundException;
import rpe.tech.order.service.domain.exceptions.NotificationException;
import rpe.tech.order.service.domain.product.Product;
import rpe.tech.order.service.domain.product.ProductGateway;
import rpe.tech.order.service.domain.product.ProductID;
import rpe.tech.order.service.domain.validation.handler.Notification;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultUpdateProductUseCase extends UpdateProductUseCase {

    private final ProductGateway productGateway;

    public DefaultUpdateProductUseCase(final ProductGateway productGateway) {
        this.productGateway = Objects.requireNonNull(productGateway);
    }

    @Override
    public UpdateProductOutput execute(final UpdateProductCommand aCmd) {
        final var anId = ProductID.from(aCmd.id());
        final var aName = aCmd.name();
        final var aPrice = aCmd.price();
        final var isActive = aCmd.isActive();

        final var aProduct = this.productGateway.findById(anId)
                .orElseThrow(notFound(anId));

        final var notification = Notification.create();
        notification.validate(() -> aProduct.update(aName, aPrice, isActive));

        if (notification.hasError()) {
            throw new NotificationException(
                    "Could not update Aggregate Product %s".formatted(aCmd.id()), notification
            );
        }
        return UpdateProductOutput.from(this.productGateway.update(aProduct));
    }

    private Supplier<DomainException> notFound(final Identifier anId) {
        return () -> NotFoundException.with(Product.class, anId);
    }
}
