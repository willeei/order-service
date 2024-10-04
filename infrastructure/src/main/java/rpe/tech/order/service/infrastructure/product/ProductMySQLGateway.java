package rpe.tech.order.service.infrastructure.product;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import rpe.tech.order.service.domain.pagination.Pagination;
import rpe.tech.order.service.domain.pagination.SearchQuery;
import rpe.tech.order.service.domain.product.Product;
import rpe.tech.order.service.domain.product.ProductGateway;
import rpe.tech.order.service.domain.product.ProductID;
import rpe.tech.order.service.infrastructure.product.persistence.ProductJpaEntity;
import rpe.tech.order.service.infrastructure.product.persistence.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static rpe.tech.order.service.infrastructure.utils.SpecificationUtils.like;

@Component
public class ProductMySQLGateway implements ProductGateway {

    private final ProductRepository repository;

    public ProductMySQLGateway(final ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product create(final Product aProduct) {
        return save(aProduct);
    }

    @Override
    public void deleteById(final ProductID anId) {
        final var anIdValue = anId.getValue();
        if (this.repository.existsById(anIdValue)) {
            this.repository.deleteById(anIdValue);
        }
    }

    @Override
    public List<ProductID> existsByIds(final Iterable<ProductID> productIds) {
        final var ids = StreamSupport.stream(productIds.spliterator(), false)
                .map(ProductID::getValue)
                .toList();
        return this.repository.existsByIds(ids).stream()
                .map(ProductID::from)
                .toList();
    }

    @Override
    public Optional<Product> findById(final ProductID anId) {
        return this.repository.findById(anId.getValue()).map(ProductJpaEntity::toAggregate);
    }

    @Override
    public Pagination<Product> findAll(final SearchQuery aQuery) {
        // Paginação
        final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Direction.fromString(aQuery.direction()), aQuery.sort())
        );

        // Busca dinamica pelo criterio terms (name)
        final var specifications = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult = this.repository.findAll(Specification.where(specifications), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(ProductJpaEntity::toAggregate).toList()
        );
    }

    @Override
    public Product update(final Product aProduct) {
        return save(aProduct);
    }

    private Product save(final Product aProduct) {
        return this.repository.save(ProductJpaEntity.from(aProduct)).toAggregate();
    }

    private Specification<ProductJpaEntity> assembleSpecification(final String str) {
        return like("name", str);
    }
}
