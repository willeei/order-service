package rpe.tech.order.service.infrastructure.checkout;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rpe.tech.order.service.domain.checkout.Order;
import rpe.tech.order.service.domain.checkout.OrderGateway;
import rpe.tech.order.service.domain.checkout.OrderID;
import rpe.tech.order.service.domain.checkout.OrderPreview;
import rpe.tech.order.service.domain.pagination.Pagination;
import rpe.tech.order.service.domain.pagination.SearchQuery;
import rpe.tech.order.service.infrastructure.checkout.persistence.OrderItemRepository;
import rpe.tech.order.service.infrastructure.checkout.persistence.OrderJpaEntity;
import rpe.tech.order.service.infrastructure.checkout.persistence.OrderRepository;
import rpe.tech.order.service.infrastructure.configuration.annotations.OrderClosingQueue;
import rpe.tech.order.service.infrastructure.configuration.annotations.OrderCreatedQueue;
import rpe.tech.order.service.infrastructure.services.EventService;
import rpe.tech.order.service.infrastructure.utils.SqlUtils;

import java.util.Objects;
import java.util.Optional;

@Component
public class OrderMySQLGateway implements OrderGateway {

    private final EventService createOrderEventService;
    private final EventService closingOrderEventService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderMySQLGateway(
            @OrderCreatedQueue final EventService createOrderEventService,
            @OrderClosingQueue final EventService closingOrderEventService,
            final OrderRepository orderRepository,
            final OrderItemRepository orderItemRepository
    ) {
        this.createOrderEventService = Objects.requireNonNull(createOrderEventService);
        this.closingOrderEventService = closingOrderEventService;
        this.orderRepository = Objects.requireNonNull(orderRepository);
        this.orderItemRepository = Objects.requireNonNull(orderItemRepository);
    }

    @Override
    @Transactional
    public Order create(final Order anOrder) {
        final var orderJpaEntity = OrderJpaEntity.from(anOrder);
        orderJpaEntity.items().forEach(item -> item.setOrder(orderJpaEntity));

        final var result = this.orderRepository.save(orderJpaEntity)
                .toAggregate();

        anOrder.publishDomainEvents(this.createOrderEventService::send);
        return result;
    }

    @Override
    public void deleteById(final OrderID anId) {
        final var anIdValue = anId.getValue();
        if (this.orderRepository.existsById(anIdValue)) {
            this.orderRepository.deleteById(anIdValue);

            final var anOrderOpt = this.orderRepository.findById(anIdValue);
            anOrderOpt.ifPresent(orderJpaEntity ->
                    orderJpaEntity.toAggregate().publishDomainEvents(this.closingOrderEventService::send)
            );
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> findById(final OrderID anId) {
        return this.orderRepository.findById(anId.getValue())
                .map(OrderJpaEntity::toAggregate);
    }

    @Override
    public Pagination<OrderPreview> findAll(final SearchQuery aQuery) {
        final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
        );

        final var actualPage = this.orderRepository.findAll(
                SqlUtils.like(SqlUtils.upper(aQuery.terms())),
                page
        );

        return new Pagination<>(
                actualPage.getNumber(),
                actualPage.getSize(),
                actualPage.getTotalElements(),
                actualPage.toList()
        );
    }

    @Override
    @Transactional
    public Order update(final Order anOrder) {
        final var anId = anOrder.id();

        final var savedOrderOpt = this.orderRepository.findById(anId.getValue());
        if (savedOrderOpt.isPresent()) {
            final var savedOrder = savedOrderOpt.get();
            final var updateOrder = OrderJpaEntity.from(anOrder);

            savedOrder.items().clear();
            updateOrder.items().forEach(item -> {
                item.setOrder(savedOrder);
                savedOrder.items().add(item);
            });

            return this.orderRepository.save(updateOrder).toAggregate();
        }

        return null;
    }
}
