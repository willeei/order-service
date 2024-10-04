package rpe.tech.order.service.application.product.retrieve.list;

import rpe.tech.order.service.application.UseCase;
import rpe.tech.order.service.domain.pagination.Pagination;
import rpe.tech.order.service.domain.pagination.SearchQuery;

public abstract class ListProductUseCase extends UseCase<SearchQuery, Pagination<ProductListOutput>> {

}
