package rpe.tech.order.service.application.checkout.retrieve.list;

import rpe.tech.order.service.application.UseCase;
import rpe.tech.order.service.domain.pagination.Pagination;
import rpe.tech.order.service.domain.pagination.SearchQuery;

public abstract class ListOrderUseCase extends UseCase<SearchQuery, Pagination<OrderListOutput>> {

}
