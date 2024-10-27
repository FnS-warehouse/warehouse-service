package com.fns.warehouse.service.domain.event;

import com.fns.domain.event.DomainEvent;
import com.fns.warehouse.service.domain.entity.InventoryTransfer;

import java.time.ZonedDateTime;

public abstract class InventoryTransferEvent implements DomainEvent<InventoryTransfer> {
    private final InventoryTransfer transfer;
    private final ZonedDateTime createdAt;

    protected InventoryTransferEvent(InventoryTransfer transfer, ZonedDateTime createdAt) {
        this.transfer = transfer;
        this.createdAt = createdAt;
    }

    @Override
    public InventoryTransfer getEntity() {
        return transfer;
    }

    @Override
    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
