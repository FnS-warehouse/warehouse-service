package com.fns.warehouse.service.domain;

import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.warehouse.service.domain.event.WarehouseCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationDomainEventPublisher implements ApplicationEventPublisherAware, DomainEventPublisher<WarehouseCreatedEvent> {
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(WarehouseCreatedEvent domainEvent) {
        this.applicationEventPublisher.publishEvent(domainEvent);
        log.info("OrderCreatedEvent is published for order id: {}", domainEvent.getEntity()
                .getId().getValue());
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

}
