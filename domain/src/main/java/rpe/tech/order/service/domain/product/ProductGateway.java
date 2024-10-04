package rpe.tech.order.service.domain.product;

import rpe.tech.order.service.domain.pagination.Pagination;
import rpe.tech.order.service.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface ProductGateway {

    Product create(Product aProduct);

    void deleteById(ProductID anId);

    List<ProductID> existsByIds(Iterable<ProductID> anIds);

    Optional<Product> findById(ProductID anId);

    Pagination<Product> findAll(SearchQuery aQuery);

    Product update(Product aProduct);
}
