package rpe.tech.order.service.application.product.retrieve.list;

import rpe.tech.order.service.domain.product.Product;

import java.math.BigDecimal;

public record ProductListOutput(
        String id,
        String name,
        BigDecimal price,
        boolean isActive
) {

    public static ProductListOutput from(final Product aProduct) {
        return new ProductListOutput(
                aProduct.id().getValue(),
                aProduct.name(),
                aProduct.price(),
                aProduct.isActive()
        );
    }
}
