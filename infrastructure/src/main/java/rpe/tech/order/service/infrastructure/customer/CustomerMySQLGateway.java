package rpe.tech.order.service.infrastructure.customer;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import rpe.tech.order.service.domain.customer.Customer;
import rpe.tech.order.service.domain.customer.CustomerGateway;
import rpe.tech.order.service.domain.customer.CustomerID;
import rpe.tech.order.service.domain.pagination.Pagination;
import rpe.tech.order.service.domain.pagination.SearchQuery;
import rpe.tech.order.service.infrastructure.customer.persistence.CustomerJpaEntity;
import rpe.tech.order.service.infrastructure.customer.persistence.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static rpe.tech.order.service.infrastructure.utils.SpecificationUtils.like;

@Component
public class CustomerMySQLGateway implements CustomerGateway {

    private final CustomerRepository repository;

    public CustomerMySQLGateway(final CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer create(final Customer aCustomer) {
        return save(aCustomer);
    }

    @Override
    public void deleteById(final CustomerID anId) {
        final var anIdValue = anId.getValue();
        if (this.repository.existsById(anIdValue)) {
            this.repository.deleteById(anIdValue);
        }
    }

    @Override
    public List<CustomerID> existsByIds(final Iterable<CustomerID> anIds) {
        final var ids = StreamSupport.stream(anIds.spliterator(), false)
                .map(CustomerID::getValue)
                .toList();
        return this.repository.existsByIds(ids).stream()
                .map(CustomerID::from)
                .toList();
    }

    @Override
    public Optional<Customer> findById(final CustomerID anId) {
        return this.repository.findById(anId.getValue()).map(CustomerJpaEntity::toAggregate);
    }

    @Override
    public Pagination<Customer> findAll(final SearchQuery aQuery) {
        final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
        );

        final var specification = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult = this.repository.findAll(Specification.where(specification), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(CustomerJpaEntity::toAggregate).toList()
        );
    }

    @Override
    public Customer update(final Customer aCustomer) {
        return save(aCustomer);
    }

    private Customer save(final Customer aCustomer) {
        return this.repository.save(CustomerJpaEntity.from(aCustomer)).toAggregate();
    }

    private Specification<CustomerJpaEntity> assembleSpecification(final String str) {
        return like("name", str);
    }
}
