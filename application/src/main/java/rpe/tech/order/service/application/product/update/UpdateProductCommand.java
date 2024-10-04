package rpe.tech.order.service.application.product.update;

import java.math.BigDecimal;

public record UpdateProductCommand(
        String id,
        String name,
        BigDecimal price,
        boolean isActive
) {

    public static UpdateProductCommand with(
            final String id,
            final String name,
            final BigDecimal price,
            final Boolean isActive
    ) {
        return new UpdateProductCommand(id, name, price, isActive != null ? isActive : Boolean.TRUE);
    }
}
