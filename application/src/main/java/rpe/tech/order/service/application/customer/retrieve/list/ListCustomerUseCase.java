package rpe.tech.order.service.application.customer.retrieve.list;

import rpe.tech.order.service.application.UseCase;
import rpe.tech.order.service.domain.pagination.Pagination;
import rpe.tech.order.service.domain.pagination.SearchQuery;

public abstract class ListCustomerUseCase extends UseCase<SearchQuery, Pagination<CustomerListOutput>> {

}
