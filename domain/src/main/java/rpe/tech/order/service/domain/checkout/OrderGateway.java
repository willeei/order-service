package rpe.tech.order.service.domain.checkout;

import rpe.tech.order.service.domain.pagination.Pagination;
import rpe.tech.order.service.domain.pagination.SearchQuery;

import java.util.Optional;

public interface OrderGateway {

    Order create(Order anOrder);

    void deleteById(OrderID anId);

    Optional<Order> findById(OrderID anId);

    Pagination<OrderPreview> findAll(SearchQuery aQuery);

    Order update(Order anOrder);
}
