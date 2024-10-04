package rpe.tech.order.service.infrastructure.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rpe.tech.order.service.infrastructure.configuration.annotations.OrderClosingQueue;
import rpe.tech.order.service.infrastructure.configuration.annotations.OrderCreatedQueue;
import rpe.tech.order.service.infrastructure.configuration.annotations.OrderEvents;
import rpe.tech.order.service.infrastructure.configuration.properties.amqp.QueueProperties;

@Configuration
public class AmpqConfig {

    @Bean
    @ConfigurationProperties("amqp.queues.order-created")
    @OrderCreatedQueue
    public QueueProperties orderCreatedQueueProperties() {
        return new QueueProperties();
    }

    @Bean
    @ConfigurationProperties("amqp.queues.order-closing")
    @OrderClosingQueue
    public QueueProperties orderClosingQueueProperties() {
        return new QueueProperties();
    }

    @Bean
    public String queueCreateName(@OrderCreatedQueue QueueProperties props) {
        return props.getQueue();
    }

    @Bean
    public String queueCloseName(@OrderClosingQueue QueueProperties props) {
        return props.getQueue();
    }

    @Configuration
    static class Admin {

        @Bean
        @OrderEvents
        @OrderCreatedQueue
        public DirectExchange orderCreateEventsExchange(@OrderCreatedQueue QueueProperties props) {
            return new DirectExchange(props.getExchange());
        }

        @Bean
        @OrderCreatedQueue
        public Queue orderCreatedQueue(@OrderCreatedQueue QueueProperties props) {
            return new Queue(props.getQueue());
        }

        @Bean
        @OrderCreatedQueue
        public Binding videoCreatedBinding(
                @OrderEvents @OrderCreatedQueue DirectExchange exchange,
                @OrderCreatedQueue Queue queue,
                @OrderCreatedQueue QueueProperties props
        ) {
            return BindingBuilder.bind(queue).to(exchange).with(props.getRoutingKey());
        }

        @Bean
        @OrderEvents
        @OrderClosingQueue
        public DirectExchange orderClosingEventsExchange(@OrderClosingQueue QueueProperties props) {
            return new DirectExchange(props.getExchange());
        }

        @Bean
        @OrderClosingQueue
        public Queue orderClosingQueue(@OrderClosingQueue QueueProperties props) {
            return new Queue(props.getQueue());
        }

        @Bean
        @OrderClosingQueue
        public Binding videoClosingBinding(
                @OrderEvents @OrderClosingQueue DirectExchange exchange,
                @OrderClosingQueue Queue queue,
                @OrderClosingQueue QueueProperties props
        ) {
            return BindingBuilder.bind(queue).to(exchange).with(props.getRoutingKey());
        }
    }
}
