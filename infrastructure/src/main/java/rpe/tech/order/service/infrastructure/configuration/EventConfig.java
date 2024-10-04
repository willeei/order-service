package rpe.tech.order.service.infrastructure.configuration;

import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rpe.tech.order.service.infrastructure.configuration.annotations.OrderClosingQueue;
import rpe.tech.order.service.infrastructure.configuration.annotations.OrderCreatedQueue;
import rpe.tech.order.service.infrastructure.configuration.properties.amqp.QueueProperties;
import rpe.tech.order.service.infrastructure.services.EventService;
import rpe.tech.order.service.infrastructure.services.impl.RabbitEventService;

@Configuration
public class EventConfig {

    @Bean
    @OrderCreatedQueue
    EventService orderCreatedEventService(@OrderCreatedQueue final QueueProperties props, final RabbitOperations ops) {
        return new RabbitEventService(props.getExchange(), props.getRoutingKey(), ops);
    }

    @Bean
    @OrderClosingQueue
    EventService orderClosingEventService(@OrderClosingQueue final QueueProperties props, final RabbitOperations ops) {
        return new RabbitEventService(props.getExchange(), props.getRoutingKey(), ops);
    }
}