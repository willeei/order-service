package rpe.tech.order.service.infrastructure.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import rpe.tech.order.service.application.checkout.delete.DeleteOrderCommand;
import rpe.tech.order.service.application.checkout.delete.DeleteOrderUseCase;
import rpe.tech.order.service.infrastructure.checkout.models.OrderClosingCompleted;
import rpe.tech.order.service.infrastructure.checkout.models.OrderClosingError;
import rpe.tech.order.service.infrastructure.checkout.models.OrderClosingResult;
import rpe.tech.order.service.infrastructure.configuration.json.Json;

import java.util.Objects;

@Component
public class OrderClosingListener {

    static final String LISTENER_ID = "orderClosingListener";
    private static final Logger log = LoggerFactory.getLogger(OrderClosingListener.class);
    private final DeleteOrderUseCase deleteOrderUseCase;

    public OrderClosingListener(final DeleteOrderUseCase deleteOrderUseCase) {
        this.deleteOrderUseCase = Objects.requireNonNull(deleteOrderUseCase);
    }

    @RabbitListener(id = LISTENER_ID, queues = "${amqp.queues.order-closing.queue}")
    public void onOrderClosingMessage(@Payload final String message) {
        final var aResult = Json.readValue(message, OrderClosingResult.class);

        switch (aResult) {
            case OrderClosingCompleted dto -> {
                final var aCmd = DeleteOrderCommand.with(dto.id());

                this.deleteOrderUseCase.execute(aCmd);
            }
            case OrderClosingError dto ->
                    log.error("[message:order.listener.income] [status:error] [payload:{}]", message);
            default -> log.error("[message:order.listener.income] [status:unknown] [payload:{}]", message);
        }
    }
}
