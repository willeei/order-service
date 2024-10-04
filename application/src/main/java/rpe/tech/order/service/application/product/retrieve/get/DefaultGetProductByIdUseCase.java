package rpe.tech.order.service.application.product.retrieve.get;

import rpe.tech.order.service.domain.exceptions.NotFoundException;
import rpe.tech.order.service.domain.product.Product;
import rpe.tech.order.service.domain.product.ProductGateway;
import rpe.tech.order.service.domain.product.ProductID;

import java.util.Objects;

public class DefaultGetProductByIdUseCase extends GetProductByIdUseCase {

    private final ProductGateway productGateway;

    public DefaultGetProductByIdUseCase(final ProductGateway productGateway) {
        this.productGateway = Objects.requireNonNull(productGateway);
    }

    @Override
    public ProductOutput execute(final String anId) {
        final var aProductId = ProductID.from(anId);
        return this.productGateway.findById(aProductId)
                .map(ProductOutput::from)
                .orElseThrow(() -> NotFoundException.with(Product.class, aProductId));
    }
}
