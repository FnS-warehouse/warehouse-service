package com.fns.warehouse.service.domain.event;

import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.warehouse.service.domain.entity.Stock;

import java.time.ZonedDateTime;

public class StockTransferApprovedEvent extends StockTransferEvent {
    private final DomainEventPublisher<StockTransferApprovedEvent> eventPublisher;

    public StockTransferApprovedEvent(Stock sourceStock, Stock destinationStock, int quantity, ZonedDateTime createdAt, DomainEventPublisher<StockTransferApprovedEvent> eventPublisher) {
        super(sourceStock, destinationStock, quantity, createdAt);
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void fire() {
        eventPublisher.publish(this);
    }
}
