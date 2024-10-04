package rpe.tech.order.service.application.product.delete;

import rpe.tech.order.service.domain.product.ProductGateway;
import rpe.tech.order.service.domain.product.ProductID;

import java.util.Objects;

public class DefaultDeleteProductUseCase extends DeleteProductUseCase {

    private final ProductGateway productGateway;

    public DefaultDeleteProductUseCase(final ProductGateway productGateway) {
        this.productGateway = Objects.requireNonNull(productGateway);
    }

    @Override
    public void execute(final String anIn) {
        this.productGateway.deleteById(ProductID.from(anIn));
    }
}
