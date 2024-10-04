package rpe.tech.order.service.application.product.create;

import rpe.tech.order.service.domain.product.Product;

public record CreateProductOutput(String id) {

    public static CreateProductOutput from(final String id) {
        return new CreateProductOutput(id);
    }

    public static CreateProductOutput from(final Product product) {
        return new CreateProductOutput(product.id().getValue());
    }
}
