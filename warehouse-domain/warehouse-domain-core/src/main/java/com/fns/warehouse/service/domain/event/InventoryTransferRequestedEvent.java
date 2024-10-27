package com.fns.warehouse.service.domain.event;

import com.fns.warehouse.service.domain.entity.Inventory;
import com.fns.domain.valueObject.WarehouseId;
import com.fns.warehouse.service.domain.entity.InventoryTransfer;

import java.time.ZonedDateTime;

public class InventoryTransferRequestedEvent extends InventoryTransferEvent {
    private final int quantity;

    public InventoryTransferRequestedEvent(InventoryTransfer transfer, int quantity, ZonedDateTime requestedAt) {
        super(transfer, requestedAt);
        this.quantity = quantity;
    }


    public int getQuantity() {
        return quantity;
    }
}
