package com.fns.warehouse.service.domain.event;

import com.fns.domain.event.DomainEvent;
import com.fns.warehouse.service.domain.entity.Stock;

import java.time.ZonedDateTime;

public abstract class StockEvent implements DomainEvent<Stock> {
    private final Stock stock;
    private final ZonedDateTime createdAt;

    protected StockEvent(Stock stock, ZonedDateTime createdAt) {
        this.stock = stock;
        this.createdAt = createdAt;
    }

    @Override
    public Stock getEntity() {
        return stock;
    }

    @Override
    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
