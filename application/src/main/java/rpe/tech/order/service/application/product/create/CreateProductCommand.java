package rpe.tech.order.service.application.product.create;

import java.math.BigDecimal;

public record CreateProductCommand(
        String name,
        BigDecimal price,
        boolean isActive
) {

    public static CreateProductCommand with(
            final String aName,
            final BigDecimal aPrice,
            final Boolean isActive
    ) {
        return new CreateProductCommand(aName, aPrice, isActive != null ? isActive : Boolean.TRUE);
    }
}
