package com.fns.warehouse.service.domain.event;

import com.fns.domain.event.DomainEvent;
import com.fns.warehouse.service.domain.entity.Inventory;
import com.fns.warehouse.service.domain.entity.InventoryTransfer;

import java.time.ZonedDateTime;

public abstract class InventoryEvent implements DomainEvent<Inventory> {
    private final Inventory inventory;
    private final ZonedDateTime createdAt;

    protected InventoryEvent(Inventory inventory, ZonedDateTime createdAt) {
        this.inventory = inventory;
        this.createdAt = createdAt;
    }

    @Override
    public Inventory getEntity() {
        return inventory;
    }

    @Override
    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
