package rpe.tech.order.service.domain.event;

@FunctionalInterface
public interface DomainEventPublisher {

    void publish(DomainEvent event);
}
