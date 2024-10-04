package rpe.tech.order.service.application.product.update;

import rpe.tech.order.service.domain.product.Product;

public record UpdateProductOutput(String id) {

    public static UpdateProductOutput from(final Product aProduct) {
        return new UpdateProductOutput(aProduct.id().getValue());
    }
}
