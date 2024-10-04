package rpe.tech.order.service.application.product.retrieve.list;

import rpe.tech.order.service.domain.pagination.Pagination;
import rpe.tech.order.service.domain.pagination.SearchQuery;
import rpe.tech.order.service.domain.product.ProductGateway;

import java.util.Objects;

public class DefaultListProductUseCase extends ListProductUseCase {

    private final ProductGateway productGateway;

    public DefaultListProductUseCase(final ProductGateway productGateway) {
        this.productGateway = Objects.requireNonNull(productGateway);
    }

    @Override
    public Pagination<ProductListOutput> execute(final SearchQuery aQuery) {
        return this.productGateway.findAll(aQuery)
                .map(ProductListOutput::from);
    }
}
