package rpe.tech.order.service.infrastructure.product.presenters;

import rpe.tech.order.service.application.product.retrieve.get.ProductOutput;
import rpe.tech.order.service.application.product.retrieve.list.ProductListOutput;
import rpe.tech.order.service.infrastructure.product.models.ProductListResponse;
import rpe.tech.order.service.infrastructure.product.models.ProductResponse;

public interface ProductApiPresenter {

    static ProductResponse present(final ProductOutput output) {
        return new ProductResponse(
                output.id(),
                output.name(),
                output.price(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static ProductListResponse present(final ProductListOutput output) {
        return new ProductListResponse(
                output.id(),
                output.name(),
                output.price(),
                output.isActive()
        );
    }
}
