package rpe.tech.order.service.domain;

import rpe.tech.order.service.domain.event.DomainEvent;
import rpe.tech.order.service.domain.event.DomainEventPublisher;
import rpe.tech.order.service.domain.validation.ValidationHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Entity<ID extends Identifier> {

    protected final ID id;
    private final List<DomainEvent> domainEvents;

    protected Entity(final ID id, final List<DomainEvent> domainEvents) {
        Objects.requireNonNull(id, "'id' should not be null");
        this.id = id;
        this.domainEvents = new ArrayList<>(domainEvents == null ? Collections.emptyList() : domainEvents);
    }

    public abstract void validate(ValidationHandler handler);

    public ID id() {
        return id;
    }

    public List<DomainEvent> domainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    public void publishDomainEvents(final DomainEventPublisher publisher) {
        if (publisher == null) return;

        domainEvents().forEach(publisher::publish);
        this.domainEvents.clear();
    }

    public void registerEvent(final DomainEvent event) {
        if (event != null) this.domainEvents.add(event);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Entity<?> entity = (Entity<?>) o;
        return Objects.equals(id(), entity.id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id());
    }
}
