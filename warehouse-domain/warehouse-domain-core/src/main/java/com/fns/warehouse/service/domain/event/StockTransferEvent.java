package com.fns.warehouse.service.domain.event;

import com.fns.domain.event.DomainEvent;
import com.fns.warehouse.service.domain.entity.Stock;

import java.time.ZonedDateTime;

public abstract class StockTransferEvent implements DomainEvent<Stock> {
    private final Stock sourceStock;
    private final Stock destinationStock;
    private final int quantity;
    private final ZonedDateTime createdAt;

    protected StockTransferEvent(Stock sourceStock, Stock destinationStock, int quantity, ZonedDateTime createdAt) {
        this.sourceStock = sourceStock;
        this.destinationStock = destinationStock;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    @Override
    public Stock getEntity() {
        return sourceStock;
    }

    @Override
    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public Stock getSourceStock() {
        return sourceStock;
    }

    public Stock getDestinationStock() {
        return destinationStock;
    }

    public int getQuantity() {
        return quantity;
    }

    public abstract void fire();
}
