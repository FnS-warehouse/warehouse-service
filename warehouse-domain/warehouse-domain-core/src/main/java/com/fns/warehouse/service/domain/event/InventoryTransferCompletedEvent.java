package com.fns.warehouse.service.domain.event;

import com.fns.warehouse.service.domain.entity.Inventory;
import com.fns.domain.valueObject.WarehouseId;
import java.time.ZonedDateTime;

public class InventoryTransferCompletedEvent extends InventoryEvent {
    private final WarehouseId sourceWarehouseId;
    private final WarehouseId destinationWarehouseId;
    private final int quantity;

    public InventoryTransferCompletedEvent(Inventory inventory, WarehouseId sourceWarehouseId, WarehouseId destinationWarehouseId, int quantity, ZonedDateTime completedAt) {
        super(inventory, completedAt);
        this.sourceWarehouseId = sourceWarehouseId;
        this.destinationWarehouseId = destinationWarehouseId;
        this.quantity = quantity;
    }

    public WarehouseId getSourceWarehouseId() {
        return sourceWarehouseId;
    }

    public WarehouseId getDestinationWarehouseId() {
        return destinationWarehouseId;
    }

    public int getQuantity() {
        return quantity;
    }
}
