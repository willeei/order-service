package rpe.tech.order.service.domain.customer;

import rpe.tech.order.service.domain.pagination.Pagination;
import rpe.tech.order.service.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface CustomerGateway {

    Customer create(Customer aCustomer);

    void deleteById(CustomerID anId);

    List<CustomerID> existsByIds(Iterable<CustomerID> anIds);

    Optional<Customer> findById(CustomerID anId);

    Pagination<Customer> findAll(SearchQuery aQuery);

    Customer update(Customer aCustomer);
}
