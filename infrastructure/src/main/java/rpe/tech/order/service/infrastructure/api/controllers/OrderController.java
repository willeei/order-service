package rpe.tech.order.service.infrastructure.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import rpe.tech.order.service.application.checkout.create.CreateOrderCommand;
import rpe.tech.order.service.application.checkout.create.CreateOrderUseCase;
import rpe.tech.order.service.application.checkout.delete.DeleteOrderCommand;
import rpe.tech.order.service.application.checkout.delete.DeleteOrderUseCase;
import rpe.tech.order.service.application.checkout.retrieve.get.GetOrderByIdUseCase;
import rpe.tech.order.service.application.checkout.retrieve.list.ListOrderUseCase;
import rpe.tech.order.service.application.checkout.update.UpdateOrderCommand;
import rpe.tech.order.service.application.checkout.update.UpdateOrderUseCase;
import rpe.tech.order.service.domain.checkout.OrderItem;
import rpe.tech.order.service.domain.pagination.Pagination;
import rpe.tech.order.service.domain.pagination.SearchQuery;
import rpe.tech.order.service.infrastructure.api.OrderAPI;
import rpe.tech.order.service.infrastructure.checkout.models.CreateOrderRequest;
import rpe.tech.order.service.infrastructure.checkout.models.OrderListResponse;
import rpe.tech.order.service.infrastructure.checkout.models.OrderResponse;
import rpe.tech.order.service.infrastructure.checkout.models.UpdateOrderRequest;
import rpe.tech.order.service.infrastructure.checkout.presenters.OrderApiPresenter;

import java.net.URI;
import java.util.Objects;

@RestController
public class OrderController implements OrderAPI {

    private final CreateOrderUseCase createOrderUseCase;
    private final DeleteOrderUseCase deleteOrderUseCase;
    private final GetOrderByIdUseCase getOrderByIdUseCase;
    private final ListOrderUseCase listOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;

    public OrderController(
            final CreateOrderUseCase createOrderUseCase,
            final DeleteOrderUseCase deleteOrderUseCase,
            final GetOrderByIdUseCase getOrderByIdUseCase,
            final ListOrderUseCase listOrderUseCase,
            final UpdateOrderUseCase updateOrderUseCase
    ) {
        this.createOrderUseCase = Objects.requireNonNull(createOrderUseCase);
        this.deleteOrderUseCase = Objects.requireNonNull(deleteOrderUseCase);
        this.getOrderByIdUseCase = Objects.requireNonNull(getOrderByIdUseCase);
        this.listOrderUseCase = Objects.requireNonNull(listOrderUseCase);
        this.updateOrderUseCase = Objects.requireNonNull(updateOrderUseCase);
    }

    @Override
    public ResponseEntity<?> createOrder(final CreateOrderRequest input) {
        final var aCommand = CreateOrderCommand.with(
                input.customerId(),
                input.items().stream().map(item ->
                        OrderItem.with(item.name(), item.productId(), item.price(), item.quantity())
                ).toList()
        );

        final var output = this.createOrderUseCase.execute(aCommand);

        return ResponseEntity.created(URI.create("/orders/" + output.id())).body(output);
    }

    @Override
    public Pagination<OrderListResponse> listOrders(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {
        return this.listOrderUseCase.execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(OrderApiPresenter::present);
    }

    @Override
    public OrderResponse getById(final String id) {
        return OrderApiPresenter.present(this.getOrderByIdUseCase.execute(id));
    }

    @Override
    public ResponseEntity<?> updateById(final String id, final UpdateOrderRequest input) {
        final var aCommand = UpdateOrderCommand.with(
                id,
                input.items().stream().map(item ->
                        OrderItem.with(item.name(), item.productId(), item.price(), item.quantity())
                ).toList()
        );

        final var output = this.updateOrderUseCase.execute(aCommand);

        return ResponseEntity.ok(output);
    }

    @Override
    public void deleteById(final String id) {
        this.deleteOrderUseCase.execute(DeleteOrderCommand.with(id));
    }
}
