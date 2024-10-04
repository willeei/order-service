package rpe.tech.order.service.infrastructure.configuration.usecases;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rpe.tech.order.service.application.checkout.create.CreateOrderUseCase;
import rpe.tech.order.service.application.checkout.create.DefaultCreateOrderUseCase;
import rpe.tech.order.service.application.checkout.delete.DefaultDeleteOrderUseCase;
import rpe.tech.order.service.application.checkout.delete.DeleteOrderUseCase;
import rpe.tech.order.service.application.checkout.retrieve.get.DefaultGetOrderByIdUseCase;
import rpe.tech.order.service.application.checkout.retrieve.get.GetOrderByIdUseCase;
import rpe.tech.order.service.application.checkout.retrieve.list.DefaultListOrderUseCase;
import rpe.tech.order.service.application.checkout.retrieve.list.ListOrderUseCase;
import rpe.tech.order.service.application.checkout.update.DefaultUpdateOrderUseCase;
import rpe.tech.order.service.application.checkout.update.UpdateOrderUseCase;
import rpe.tech.order.service.domain.checkout.OrderGateway;

@Configuration
public class OrderUseCaseConfig {

    private final OrderGateway orderGateway;

    public OrderUseCaseConfig(final OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    @Bean
    public CreateOrderUseCase createOrderUseCase() {
        return new DefaultCreateOrderUseCase(orderGateway);
    }

    @Bean
    public UpdateOrderUseCase updateOrderUseCase() {
        return new DefaultUpdateOrderUseCase(orderGateway);
    }

    @Bean
    public DeleteOrderUseCase deleteOrderUseCase() {
        return new DefaultDeleteOrderUseCase(orderGateway);
    }

    @Bean
    public GetOrderByIdUseCase getOrderByIdUseCase() {
        return new DefaultGetOrderByIdUseCase(orderGateway);
    }

    @Bean
    public ListOrderUseCase listOrderUseCase() {
        return new DefaultListOrderUseCase(orderGateway);
    }
}
