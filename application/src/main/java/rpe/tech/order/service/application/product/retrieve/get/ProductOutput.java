package rpe.tech.order.service.application.product.retrieve.get;

import rpe.tech.order.service.domain.product.Product;

import java.math.BigDecimal;
import java.time.Instant;

public record ProductOutput(
        String id,
        String name,
        BigDecimal price,
        boolean isActive,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {

    public static ProductOutput from(Product aProduct) {
        return new ProductOutput(
                aProduct.id().getValue(),
                aProduct.name(),
                aProduct.price(),
                aProduct.isActive(),
                aProduct.createdAt(),
                aProduct.updatedAt(),
                aProduct.deletedAt()
        );
    }
}
